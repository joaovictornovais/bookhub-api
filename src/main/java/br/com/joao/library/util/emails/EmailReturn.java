package br.com.joao.library.util.emails;

public class EmailReturn {

    public static String returnBook() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\" />\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin />\n" +
                "    <link\n" +
                "      href=\"https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap\"\n" +
                "      rel=\"stylesheet\"\n" +
                "    />\n" +
                "    <style>\n" +
                "      * {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "        box-sizing: border-box;\n" +
                "        font-family: \"Roboto\";\n" +
                "      }\n" +
                "      .container {\n" +
                "        max-width: 600px;\n" +
                "      }\n" +
                "      .header-img {\n" +
                "        width: 100%;\n" +
                "        height: 150px;\n" +
                "        object-fit: cover;\n" +
                "        border-radius: 5px 5px 0 0;\n" +
                "      }\n" +
                "      .body {\n" +
                "        padding: 20px;\n" +
                "        background-color: #efefef;\n" +
                "        display: flex;\n" +
                "        flex-direction: column;\n" +
                "        gap: 30px;\n" +
                "      }\n" +
                "      h2 {\n" +
                "        color: rgb(18, 46, 122);\n" +
                "        text-align: center;\n" +
                "        font-weight: 600;\n" +
                "      }\n" +
                "      .content {\n" +
                "        text-align: center;\n" +
                "        align-items: center;\n" +
                "      }\n" +
                "      .content p {\n" +
                "        margin: 15px 0 15px 0;\n" +
                "      }\n" +
                "      .capa {\n" +
                "        max-height: 200px;\n" +
                "        max-width: 150px;\n" +
                "      }\n" +
                "      footer {\n" +
                "        text-align: center;\n" +
                "        background-color: rgb(18, 46, 122);\n" +
                "        font-size: 14px;\n" +
                "        color: #dfdfdf;\n" +
                "        padding: 10px;\n" +
                "        border-radius: 0 0 5px 5px;\n" +
                "      }\n" +
                "      .please {\n" +
                "        font-size: 14px;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"container\">\n" +
                "      <img\n" +
                "        class=\"header-img\"\n" +
                "        src=\"https://cat-milk.github.io/Anime-Girls-Holding-Programming-Books/static/a6c38430026f9719ac9c69fd892dbb78/47126/Aharen_Reina_Holding_C_Programming_Language.png\"\n" +
                "      />\n" +
                "      <main class=\"body\">\n" +
                "        <h2>Obrigado por utilizar nossos serviços!</h2>\n" +
                "        <div class=\"content\">\n" +
                "          <p>Olá, [[NOME]]</p>\n" +
                "          <p>\n" +
                "            O livro <strong>[[TITULO]]</strong> foi devolvido com sucesso à\n" +
                "            Livraria BookHub. Agradecemos por sua colaboração!\n" +
                "          </p>\n" +
                "          <img class=\"capa\" src=\"[[CAPA]]\" alt=\"[[TITULO]]\" />\n" +
                "          <p class=\"please\">\n" +
                "            Esperamos que tenha aproveitado a leitura. Se precisar de mais algum\n" +
                "            livro, não hesite em nos visitar.\n" +
                "          </p>\n" +
                "        </div>\n" +
                "      </main>\n" +
                "      <footer>\n" +
                "        <p>Atenciosamente,</p>\n" +
                "        <p class=\"bookhub\">Livraria BookHub</p>\n" +
                "      </footer>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n";
    }

}
