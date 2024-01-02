# API Rest pour la gestion des Utilisateurs

Voir les end-points sur Open API / Swagger :

- Liste des utilisateurs : http://localhost:8080/swagger-ui/index.html#/Users%20managment%20Rest%20API/users
- Recherche par email : http://localhost:8080/swagger-ui/index.html#/Users%20managment%20Rest%20API/findUserByEmail
- Ajout/Modification d'un utilisateur :http://localhost:8080/swagger-ui/index.html#/Users%20managment%20Rest%20API/saveUser
- Suppression d'un utilisateur : http://localhost:8080/swagger-ui/index.html#/Users%20managment%20Rest%20API/deleteIntervenant


## Infos techniques 

- Java v 17
- Spring-boot v 3.2.0
- Postgres v 15
- Il faut créer une base de donnée "user-kata-bd", le reste est créé par liquibase
- @: http://localhost:8080/ (Il faut garder ce port car il est utilisé pour les appels API depuis le front)