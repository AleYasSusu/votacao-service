# Votação API


### Acesso ao Swagger

## AWS
* [Swagger AWS url](http://ec2-3-138-190-214.us-east-2.compute.amazonaws.com:8080/swagger-ui/index.html)

* [Swagger AWS ip](http://3.138.190.214:8080/swagger-ui/index.html)


### Desenvolvido com:

* [Springboot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - Framework web utilizado
* [Lombok](https://projectlombok.org/features/all) - Ferramenta de construção de projetos
* [Hibernate](https://hibernate.org/orm/documentation/5.4/) - Framework de mapeamento objeto/relacional (ORM)
* [Maven](https://maven.apache.org/) - Gerenciamento de dependências
* [PostgreSQL](https://www.postgresql.org/) - Banco de dados PostgreSQL
* [Swagger](https://swagger.io/docs/) - Framework de design de API
* [Flyway ](https://flywaydb.org/) - Controle de versionamento de banco de dados


## Autores

***Alessandro Borges de Souza**  - [AleYasSusu](https://github.com/AleYasSusu/votacao-service)


Não sei se isso estava previsto no teste ou não, mas ao tentar consumir a API conforme documentação, o retorno sempre era "UNABLE_TO_VOTE". Diante disso, uma abordagem adotada foi criar um mock para simular essa chamada e realizar a validação do CPF.

### Abaixo uma lista de cpf que estao ABLE_TO_VOTE
    * 86130113064
    * 22226377085
    * 46575609058
    * 87273338095
    * 86702142021
    * 22210459044
    * 48105049058
    * 92997746094