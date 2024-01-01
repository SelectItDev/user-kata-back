package com.company.my_package.controller;

import com.company.my_package.JsonUtil;
import com.company.my_package.dto.UserDto;
import com.company.my_package.service.UserService;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.reset;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@WebMvcTest(value = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class UserRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void givenUsers_whenGetUserss_thenReturnJsonArray() throws Exception {

        UserDto user1 = UserDto.builder().id(1L).firstName("fn1").lastName("ln1").email("email1@compagny.com").build();
        UserDto user2 = UserDto.builder().id(2L).firstName("fn2").lastName("ln2").email("email2@compagny.com").build();
        UserDto user3 = UserDto.builder().id(3L).firstName("fn3").lastName("ln3").email("email3@compagny.com").build();
        Iterable<UserDto> allUsers = Arrays.asList(user1, user2, user3);

        given(userService.getUsers()).willReturn(allUsers);

        mvc.perform(get("/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)))
                        .andExpect(jsonPath("$[0].firstName", is(user1.firstName())))
                        .andExpect(jsonPath("$[0].lastName", is(user1.lastName())))
                        .andExpect(jsonPath("$[1].firstName", is(user2.firstName())))
                        .andExpect(jsonPath("$[1].lastName", is(user2.lastName())));
    }

    @Test
    public void givenUser_whenGetUsersByEmail_thenReturnJsonObject() throws Exception {

        UserDto user = UserDto.builder().id(2L).firstName("fn2").lastName("ln2").email("email2@compagny.com").build();

        given(userService.findUserByEmail(user.email())).willReturn(user);

        mvc.perform(get("/user/email/email2@compagny.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.firstName())))
                .andExpect(jsonPath("$.lastName", is(user.lastName())));
    }

    @Test
    public void whenPostUser_thenCreateUser() throws Exception {
        UserDto user = UserDto.builder().id(1L).firstName("fn1").lastName("ln1").email("email1@compagny.com").build();
        given(userService.saveUser(Mockito.any())).willReturn(user);

        mvc.perform(post("/user/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("fn1")))
                .andExpect(jsonPath("$.lastName", is("ln1")));

        verify(userService, VerificationModeFactory.times(1)).saveUser(Mockito.any());

        reset(userService);
    }

}
