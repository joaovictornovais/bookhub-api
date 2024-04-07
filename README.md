<h1 align="center" style="font-weight: bold;">BookHub-API 📚</h1>

<p align="center">
 <a href="#tech">Tecnologias</a> • 
 <a href="#started">Começando</a> • 
 <a href="#routes">API Endpoints</a>
</p>

<p align="center">
    <b>API de uma biblioteca que empresta livros e dispara e-mails para os usuários que pegarem/devolverem um livro</b>
</p>

<h2 id="technologies">💻 Technologies</h2>

- Java
- Spring
- PostgreSQL

<h2 id="started">🚀 Começando</h2>
<h3>Pré-requisitos</h3>

- [Java](https://www.java.com/pt-BR/)
- [PostgreSQL](https://www.postgresql.org)

<h3>Clonando</h3>

```bash
git clone git@github.com:joaovictornovais/BookHub-API.git
```

<h3>Configurando variáveis de ambiente</h2>

```yaml
DB_URL={URL_DO_SEU_BANCO}
DB_USERNAME={USERNAME_DO_SEU_BANCO}
DB_PASSWORD={SENHA_DO_SEU_BANCO}
MAIL_USERNAME={SEU_EMAIL@GMAIL.COM}
MAIL_PASSWORD={SENHA_DE_APP_DO_SEU_EMAIL}
```

<p>Para descobrir a senha de app do seu email, acesse o artigo: https://support.google.com/accounts/answer/185833?hl=pt-BR </p>

<h3>Iniciando</h3>

```bash
$ cd project-name
$ ./mvnw clean-package
```

Executar a aplicação
```bash
$ java -jar target/library-0.0.1-SNAPSHOT.jar
```

<h2 id="routes">📍 API Endpoints</h2>
​

| Rota               | Descrição                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /users</kbd>     | retorna lista de usuários
| <kbd>POST /users</kbd>     | cadastra um novo usuário
| <kbd>GET /users/{id}</kbd>     | retorna um usuário específico
| <kbd>GET /books</kbd>     | retorna lista de livros
| <kbd>POST /books</kbd>     | cadastra um novo livro
| <kbd>GET /books/{id}</kbd>     | retorn um livro específico
| <kbd>POST /books/{id}/borrow</kbd>     | vincular (emprestar) livro para um usuário(userId) especificado no corpo da requisição
| <kbd>DELETE /books/{id}/borrow?userId={userId}</kbd>     | desvincular (confirmar devolução) livro de um usuário (userId)
| <kbd>POST /books/categories</kbd>     | cadastrar nova categoria 
| <kbd>GET /books/categories</kbd>     | retorna lista de categorias
| <kbd>POST /books/id/categories</kbd>     | vincular categoria a um livro
| <kbd>DELETE /books/id/categories</kbd>     | desvincular categoria de um livro

<h3 id="get-users-details">GET /users</h3>

**RESPONSE**
```json
[
    {
        "id": 4,
        "firstName": "João Victor",
        "lastName": "Novais",
        "email": "joaovkt.novais@gmail.com",
        "borrows": []
    }
]
```

<h3 id="post-user-detail">POST /users</h3>

**REQUEST**
```json
{
    "id": 4,
    "firstName": "João Victor",
    "lastName": "Novais",
    "email": "joaovkt.novais@gmail.com",
    "borrows": []
}
```

<h3 id="get-user-detail">GET /users/id</h3>

**RESPONSE**
```json
{
    "id": 4,
    "firstName": "João Victor",
    "lastName": "Novais",
    "email": "joaovkt.novais@gmail.com",
    "borrows": []
}
```

<h3 id="get-books-details">GET /books</h3>

**RESPONSE**
```json
[
    {
        "id": 2,
        "title": "Jujutsu Kaisen, Vol. 1",
        "author": "Gege Akutami",
        "publisher": "Viz Media",
        "pages": 192,
        "cover": "https://m.media-amazon.com/images/I/81TmHlRleJL._SY466_.jpg",
        "categories": [],
        "borrow": null
    }
]
```

<h3 id="get-books-details">GET /books</h3>

**RESPONSE**
```json
[
    {
        "id": 2,
        "title": "Jujutsu Kaisen, Vol. 1",
        "author": "Gege Akutami",
        "publisher": "Viz Media",
        "pages": 192,
        "cover": "https://m.media-amazon.com/images/I/81TmHlRleJL._SY466_.jpg",
        "categories": [],
        "borrow": null
    }
]
```
<h3 id="post-book-details">POST /books</h3>

**REQUEST**
```json
{
    "id": 2,
    "title": "Jujutsu Kaisen, Vol. 1",
    "author": "Gege Akutami",
    "publisher": "Viz Media",
    "pages": 192,
    "cover": "https://m.media-amazon.com/images/I/81TmHlRleJL._SY466_.jpg",
    "categories": [],
    "borrow": null
}
```

<h3 id="get-book-details">GET /books/id</h3>

**RESPONSE**
```json
{
    "id": 2,
    "title": "Jujutsu Kaisen, Vol. 1",
    "author": "Gege Akutami",
    "publisher": "Viz Media",
    "pages": 192,
    "cover": "https://m.media-amazon.com/images/I/81TmHlRleJL._SY466_.jpg",
    "categories": [],
    "borrow": null
}
```

<h3 id="post-borrow-details">POST /books/id/borrow</h3>

**REQUEST**
```json
{
    "userId": 4
}
```

<h3 id="post-borrow-details">DELETE /books/id/borrow?userId=4</h3>

**RESPONSE**
```
204 - No content
```

<h3 id="post-category-details">POST /books/categories</h3>

**REQUEST**
```
{
    "name": "Mangá"
}
```

<h3 id="get-categories-details">GET /books/categories</h3>

**RESPONSE**
```
[
    {
        "id": 1,
        "name": "Mangá"
    }
]
```

<h3 id="post-bookcategory-details">POST /books/id/categories</h3>

**REQUEST**
```
{
    "categoryId": 1
}
```

<h3 id="post-bookcategory-details">POST /books/id/categories</h3>

**REQUEST**
```
{
    "categoryId": 1
}
```

<h3 id="delete-bookcategory-details">DELETE /books/id/categories</h3>

**REQUEST**
```
{
    "categoryId": 1
}
```
