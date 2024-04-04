package br.com.joao.library.util.emails;

public class EmailBorrow {

    public static String borrow() {
        return "<html lang=\"pt-br\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <title>Lembrete de empréstimo de livro</title>\n" +
                "    <style>\n" +
                "      body {\n" +
                "        font-family: Arial, sans-serif;\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "        background-color: #f4f4f4;\n" +
                "      }\n" +
                "      .container {\n" +
                "        max-width: 600px;\n" +
                "        margin: 0 auto;\n" +
                "        padding: 20px;\n" +
                "        background-color: #fff;\n" +
                "        border-radius: 10px;\n" +
                "        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "      }\n" +
                "      .header {\n" +
                "        text-align: center;\n" +
                "        margin-bottom: 20px;\n" +
                "        background: url(\"https://i.pinimg.com/originals/02/25/a4/0225a431e5b2637204873584848f3949.png\")\n" +
                "          no-repeat center center;\n" +
                "        background-size: cover;\n" +
                "        border-top-left-radius: 10px;\n" +
                "        border-top-right-radius: 10px;\n" +
                "        padding: 50px 20px;\n" +
                "        color: #0061c9;\n" +
                "        text-shadow: 1px 1px 2px black, 0 0 1em black, 0 0 0.2em black;\n" +
                "        font-size: 1.2rem;\n" +
                "      }\n" +
                "      .content {\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "      .book-info {\n" +
                "        margin-bottom: 20px;\n" +
                "      }\n" +
                "      .footer {\n" +
                "        text-align: center;\n" +
                "        margin-top: 20px;\n" +
                "        color: #d3d3d3;\n" +
                "        background-color: #007bff;\n" +
                "        padding: 20px;\n" +
                "        border-bottom-left-radius: 10px;\n" +
                "        border-bottom-right-radius: 10px;\n" +
                "      }\n" +
                "      .book {\n" +
                "        max-height: 200px;\n" +
                "        max-width: 150px;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"container\">\n" +
                "      <div class=\"header\">\n" +
                "        <h2>Você pegou um livro emprestado na BookHub!</h2>\n" +
                "      </div>\n" +
                "      <div class=\"content\">\n" +
                "        <p>Prezado(a) [[NOME]],</p>\n" +
                "        <p>\n" +
                "          Você pegou emprestado o livro <strong>[[TITULO]]</strong> na Livraria\n" +
                "          BookHub no dia\n" +
                "          <strong>[[DATAEMPRESTIMO]]</strong> e deve devolver até o dia \n" +
                "          <strong>[[DATADEVOLVER]]</strong>.\n" +
                "        </p>\n" +
                "        <div class=\"book-info\">\n" +
                "          <img\n" +
                "            class=\"book\"\n" +
                "            src=\"https://m.media-amazon.com/images/I/61NPpt5HC6L._AC_UF1000,1000_QL80_.jpg\"\n" +
                "            alt=\"[[TITULO]]\"\n" +
                "          />\n" +
                "        </div>\n" +
                "        <p>Por favor, lembre-se de devolver o livro até a data especificada.</p>\n" +
                "      </div>\n" +
                "      <div class=\"footer\">\n" +
                "        <p>Atenciosamente,<br />Livraria BookHub</p>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n";
    }


}
