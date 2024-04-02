package br.com.joao.library.util.emails;

public class EmailBorrow {

    public static String borrow() {
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
                "        src=\"https://raw.githubusercontent.com/cat-milk/Anime-Girls-Holding-Programming-Books/master/Java/Kuriyama_Mirai_reading_OOP_and_Java.png\"\n" +
                "      />\n" +
                "      <main class=\"body\">\n" +
                "        <h2>Você pegou um livro emprestado na BookHub!</h2>\n" +
                "        <div class=\"content\">\n" +
                "          <p>Olá, [[NOME]]</p>\n" +
                "          <p>\n" +
                "            Você pegou emprestado o livro <strong>[[TITULO]]</strong> na\n" +
                "            Livraria BookHub no dia <strong>[[DATAEMPRESTIMO]]</strong> e deve\n" +
                "            devolver até o dia <strong>[[DATADEVOLVER]]</strong>.\n" +
                "          </p>\n" +
                "          <img class=\"capa\" src=\"[[CAPA]]\" alt=\"[[TITULO]]\" />\n" +
                "          <p class=\"please\">\n" +
                "            Por favor, lembre-se de devolver o livro até a data especificada.\n" +
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
