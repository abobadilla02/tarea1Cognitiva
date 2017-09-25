package servlet;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class pos
 */
@WebServlet("/pos")
public class Pos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Pos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tag = request.getParameter("categoria");

		String[] array = null;

		// arreglo con las categorías
		String[] categorias = { "noun", "adjective", "verb", "adverb", "pronoun", "conjunction", "cardinalnum",
				"determiner", "preposition", "to", "foreignword", "possessive", "existential", "listItem", "modalTag",
				"predeterminer", "particle", "symbol", "interjection" };

		if (tag.equals("noun")) {
			// arreglo con las categorias de sustantivos
			array = new String[] { "NN", "NNS", "NNP", "NNPS" };
		}

		if (tag.equals("adjective")) {
			// arreglo con las categorias de adjetivos
			array = new String[] { "JJ", "JJR", "JJS" };
		}

		if (tag.equals("verb")) {
			// arreglo con las categorias de verbos
			array = new String[] { "VB", "VBD", "VBG", "VBN", "VBP", "VBZ" };
		}

		if (tag.equals("adverb")) {
			// arreglo con las categorias de adverbios
			array = new String[] { "RB", "RBR", "RBS", "WRB" };
		}

		if (tag.equals("pronoun")) {
			// arreglo con las categorías de pronombres
			array = new String[] { "PRP", "PRP$", "WP", "WP$" };
		}

		if (tag.equals("conjunction")) {
			array = new String[] { "CC" };
		}

		if (tag.equals("cardinalnum")) {
			array = new String[] { "CD" };
		}

		if (tag.equals("determiner")) {
			array = new String[] { "DT", "WDT" };
		}

		if (tag.equals("preposition")) {
			array = new String[] { "IN" };
		}

		if (tag.equals("to")) {
			array = new String[] { "TO" };
		}

		if (tag.equals("foreignword")) {
			array = new String[] { "FW" };
		}

		if (tag.equals("possessive")) {
			array = new String[] { "POS" };
		}

		if (tag.equals("existential")) {
			array = new String[] { "EX" };
		}

		if (tag.equals("listItem")) {
			array = new String[] { "LS" };
		}

		if (tag.equals("modalTag")) {
			array = new String[] { "MD" };
		}

		if (tag.equals("predeterminer")) {
			array = new String[] { "PDT" };
		}

		if (tag.equals("particle")) {
			array = new String[] { "RP" };
		}

		if (tag.equals("symbol")) {
			array = new String[] { "SYM" };
		}

		if (tag.equals("interjection")) {
			array = new String[] { "UH" };
		}

		// Obtener json del archivo y hacer un arreglo con las palabras
		// pertenecientes a la categoría
		JSONParser parser = new JSONParser();
		try {
			JSONObject a = (JSONObject) parser
					.parse(new FileReader("/home/darkaliensky/Escritorio/apache-tomcat-7.0.81/lib/json/archivo.json"));

			// arreglo de palabras del documento
			JSONArray palabras = (JSONArray) a.get("palabras");

			for (int k = 0; k < categorias.length; k++) {
				if (tag.equals(categorias[k])) {

					String respuesta = "";

					for (int i = 0; i < palabras.size(); i++) {

						JSONObject palabra1 = (JSONObject) palabras.get(i);

						String categoria = (String) palabra1.get("categoria");
						for (int j = 0; j < array.length; j++) {

							if (categoria.equals(array[j])) {
								// se agrega la repetida al array
								String palabra = (String) palabra1.get("palabra");

								respuesta += palabra + "_" + categorias[k] + "*";

								break;
							}
						}

					}

					//System.out.println(respuesta);

					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(respuesta);
				}
			}

		} catch (ParseException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}