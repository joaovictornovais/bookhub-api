<h1 align="center" style="font-weight: bold;">BookHub-API 📚</h1>

<p align="center">
 <a href="#technologies">Tecnologias</a> • 
 <a href="#practices">Práticas adotadas</a> • 
 <a href="#started">Iniciando</a> • 
 <a href="#routes">API Endpoints</a>
</p>

<p align="center">
    <b>API de uma biblioteca que empresta livros e dispara e-mails para os usuários que pegarem/devolverem um livro</b>
</p>

<h2 id="technologies">💻 Tecnologias</h2>

- Java;
- Spring;
- Docker
- AWS S3;
- AWS EC2;
- PostgreSQL.

<h2 id="practices">🧭 Práticas adotadas</h2>

- API Rest;
- SOLID;
- Testes unitários;
- Consultas com Spring Data JPA;
- Tratamento de Exceções;
- Documentação Swagger com SpringDoc OpenAPI 3;
- Deploy na AWS EC2;
- Armazenamento de Imagens com AWS S3


<h2 id="started">🚀 Iniciando</h2>

Você pode acessar o repositório do Front-ent da aplicação a partir [deste link](https://github.com/joaovictornovais/bookhub/)

<h2>Rodando com Docker</h2>

<h4>Pré-requisitos</h4>

- [Docker](https://www.docker.com/)

```bash
$ docker container run -p 80:8080 -e DB_URL={URL_DO_SEU_BANCO} -e DB_USERNAME={USERNAME_DO_SEU_BANCO} -e DB_PASSWORD={SENHA_DO_SEU_BANCO} -e MAIL_USERNAME={SEU_EMAIL@GMAIL.COM} -e MAIL_PASSWORD={SENHA_DE_APP_DO_SEU_EMAIL} joaovictornovais/bookhub-api
```

<h2>Rodando sem docker</h2>

<h4>Pré-requisitos</h4>

- [Java](https://www.java.com/pt-BR/)
- [PostgreSQL](https://www.postgresql.org)

```bash
git clone git@github.com:joaovictornovais/BookHub-API.git
```

<h3>Configurando variáveis de ambiente</h2>

```bash
$ DB_URL={URL_DO_SEU_BANCO}
$ DB_USERNAME={USERNAME_DO_SEU_BANCO}
$ DB_PASSWORD={SENHA_DO_SEU_BANCO}
$ MAIL_USERNAME={SEU_EMAIL@GMAIL.COM}
$ MAIL_PASSWORD={SENHA_DE_APP_DO_SEU_EMAIL}
```

<p>Para descobrir a senha de app do seu email, acesse o artigo: https://support.google.com/accounts/answer/185833?hl=pt-BR </p>

<h3>Iniciando</h3>

```bash
$ cd bookhub-api
$ ./mvnw clean package
```

Executar a aplicação
```bash
$ java -jar target/library-0.0.1-SNAPSHOT.jar
```

<h2 id="routes">📍 API Endpoints</h2>
​

| Rota                                                     | Descrição                                          
|----------------------------------------------------------|-----------------------------------------------------
| <kbd>GET /swagger-ui/index.html                          | Página da documentação do SWAGGER
| <kbd>GET /users</kbd>                                    | retorna lista de usuários
| <kbd>GET /users?email={email}                            | retorna usuário específico a partir do e-mail
| <kbd>POST /users</kbd>                                   | cadastra um novo usuário
| <kbd>GET /users/{id}</kbd>                               | retorna um usuário específico
| <kbd>GET /categories</kbd>                               | retorna lista de categorias
| <kbd>POST /categories</kbd>                              | cadastrar nova categoria no banco de dados
| <kbd>GET /categories?name={categoryName}</kbd>           | retorna uma categoria específica
| <kbd>GET /books</kbd>                                    | retorna lista de livros
| <kbd>POST /books</kbd>                                   | cadastra um novo livro
| <kbd>GET /books/{id}</kbd>                               | retorn um livro específico
| <kbd>POST /books/{bookId}/borrow</kbd>                   | vincular (emprestar) livro para um usuário por ID especificado no corpo da requisição (userId)
| <kbd>DELETE /books/{bookId}/borrow?userId={userId}</kbd> | desvincular (confirmar devolução) livro de um usuário (userId)
| <kbd>POST /books/{id}/categories</kbd>                   | vincular categoria a um livro
| <kbd>DELETE /books/{id}/categories?categoryId={categoryId}</kbd> | desvincular categoria de um livro
