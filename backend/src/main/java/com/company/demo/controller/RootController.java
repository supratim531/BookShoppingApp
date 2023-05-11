package com.company.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	@GetMapping("/")
	public String intro() {
		return "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
				+ "  <!-- Required meta tags -->\r\n" + "  <meta charset=\"utf-8\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + "\r\n"
				+ "  <!-- Bootstrap CSS and JS -->\r\n"
				+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n"
				+ "    integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\r\n"
				+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"\r\n"
				+ "    integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\"\r\n"
				+ "    crossorigin=\"anonymous\"></script>\r\n" + "\r\n"
				+ "  <title>IEM | Book Shopping Application</title>\r\n" + "\r\n" + "  <style>\r\n"
				+ "    @import url('https://fonts.googleapis.com/css2?family=Fira+Code:wght@300;400;500;600;700&display=swap');\r\n"
				+ "  </style>\r\n" + "</head>\r\n" + "\r\n" + "<body>\r\n" + "  <div class=\"container my-5\">\r\n"
				+ "    <h1>Book Application (Backend)</h1>\r\n" + "    <hr class=\"text-danger\">\r\n"
				+ "    <h3 style=\"color: rgb(228, 101, 51);\">All Crew Members</h3>\r\n" + "    <big>\r\n"
				+ "      <ul style=\"font-size: 20px; font-family: fira code;\">\r\n" + "        <li>Ankita De</li>\r\n"
				+ "        <li>Arpan Ghosh</li>\r\n" + "        <li>Dipika Mondal</li>\r\n"
				+ "        <li>Ishani Dhar</li>\r\n" + "      </ul>\r\n" + "    </big>\r\n" + "    <b>\r\n"
				+ "      <big style=\"font-family: 'Fira Code', monospace;\">Test all REST-API <a\r\n"
				+ "          href=\"https://localhost:8888/swagger-ui/index.html\" style=\"text-decoration: none;\" target=\"_blank\">here</a>\r\n"
				+ "        (N.B.: Frontend part is ready)</big>\r\n" + "    </b>\r\n" + "  </div>\r\n" + "</body>\r\n"
				+ "\r\n" + "</html>";
	}

}
