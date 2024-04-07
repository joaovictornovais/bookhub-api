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
