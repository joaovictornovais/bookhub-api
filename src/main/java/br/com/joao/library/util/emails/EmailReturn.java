package br.com.joao.library.util.emails;

public class EmailReturn {

    public static String returnBook() {
        return "<html lang=\"pt-br\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
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
                "        background: url(\"https://c4.wallpaperflare.com/wallpaper/286/891/609/anime-c-programming-blue-eyes-book-cover-hd-wallpaper-preview.jpg\")\n" +
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
                "        <h2>Confirmação de Devolução de Livro</h2>\n" +
                "      </div>\n" +
                "      <div class=\"content\">\n" +
                "        <p>Prezado(a) [[NOME]],</p>\n" +
                "        <p>\n" +
                "          O livro <strong>[[TITULO]]</strong> foi devolvido com sucesso à\n" +
                "          Livraria BookHub. Agradecemos por sua colaboração!\n" +
                "        </p>\n" +
                "        <div class=\"book-info\">\n" +
                "          <img class=\"book\" src=\"[[CAPA]]\" alt=\"[[TITULO]]\" />\n" +
                "        </div>\n" +
                "        <p>\n" +
                "          Esperamos que tenha aproveitado a leitura. Se precisar de mais algum\n" +
                "          livro, não hesite em nos visitar.\n" +
                "        </p>\n" +
                "      </div>\n" +
                "      <div class=\"footer\">\n" +
                "        <p>Atenciosamente,<br />Livraria BookHub</p>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n";
    }

}
