package servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//IMPORTS DE LIBRERIA STANFORD

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.Triple;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;

/**
 * Servlet implementation class UploadArchivo
 */
@WebServlet("/UploadArchivo")

public class UploadArchivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadArchivo() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String texto = request.getParameter("texto");
		ArrayList<String> taggs = new ArrayList<String>();
		ArrayList<String> entities = new ArrayList<String>();

		PrintWriter out = new PrintWriter("/home/darkaliensky/Escritorio/apache-tomcat-7.0.81/lib/json/archivo.txt");
		out.println(texto);
		out.close();

		// Classifier

		String serializedClassifier = "/home/darkaliensky/Escritorio/apache-tomcat-7.0.81/lib/classifiers/english.all.3class.distsim.crf.ser.gz";
		AbstractSequenceClassifier<CoreLabel> classifier;
		List<Triple<String, Integer, Integer>> listaEntidades;
		String fileContents = IOUtils.slurpFile("/home/darkaliensky/Escritorio/apache-tomcat-7.0.81/lib/json/archivo.txt");

		try {
			classifier = CRFClassifier.getClassifier(serializedClassifier);

			listaEntidades = classifier.classifyToCharacterOffsets(fileContents);

			for (Triple<String, Integer, Integer> item : listaEntidades) {
				entities.add(fileContents.substring(item.second(), item.third()) + "_" + item.first());
				System.out.println(item.first() + ": " + fileContents.substring(item.second(), item.third()));
				System.out.println(fileContents.substring(item.second(), item.third()) + "_" + item.first());
			}
			
		} catch (ClassCastException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MaxentTagger tagger = new MaxentTagger(

				"/home/darkaliensky/Escritorio/apache-tomcat-7.0.81/lib/taggers/english-caseless-left3words-distsim.tagger");

		TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
				"untokenizable=noneKeep");
		PrintWriter pw = new PrintWriter("/home/darkaliensky/Escritorio/apache-tomcat-7.0.81/lib/json/archivoP.txt");

		BufferedReader r = new BufferedReader(new InputStreamReader(
				new FileInputStream("/home/darkaliensky/Escritorio/apache-tomcat-7.0.81/lib/json/archivo.txt"),
				"utf-8"));

		DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
		documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);

		String cadena = "";
		for (List<HasWord> sentence : documentPreprocessor) {
			List<TaggedWord> tSentence = tagger.tagSentence(sentence);
			//pw.println(SentenceUtils.listToString(tSentence, false));
			cadena = cadena + SentenceUtils.listToString(tSentence, false);
		}

		pw.close();
		r.close();

		String[] dividida = cadena.split("\\s+");

		for (int i = 0; i < dividida.length; i++) {
			String[] separada = dividida[i].split("/+");
			
			if (separada[0].equals(",") || separada[1].equals(",") || separada[0].equals(".") || separada[1].equals(".")
					|| separada[0].equals(":") || separada[1].equals(":") || separada[0].equals(";") || separada[1].equals(";") ||
					separada[0].equals("'") || separada[1].equals("'") || separada[0].equals("``") || separada[1].equals("``") 
					|| separada[0].equals("''") || separada[1].equals("''") || separada[0].equals("-RRB-") || separada[1].equals("-RRB-")
					|| separada[0].equals("-LSB-") || separada[1].equals("-LSB-") || separada[0].equals("-LRB-") || separada[1].equals("-LRB-")
					|| separada[0].equals("-RSB-") || separada[1].equals("-RSB-") || separada[0].equals("--") || separada[1].equals("--")
					|| separada[0].equals("-LRB-") || separada[1].equals("-LRB-") || separada[0].equals("-") || separada[1].equals("-")
					|| separada[0].equals(";") || separada[1].equals(";") || separada[0].equals("/") || separada[1].equals("/")
					|| separada[0].equals("`") || separada[1].equals("`") || separada[0].equals("§") || separada[1].equals("§")
					
					) {
				System.out.println("saltado");
			} else {
				taggs.add(separada[0]);
				taggs.add(separada[1]);
			} 
			/*
			if (!separada[0].matches("[a-zA-Z]+") || !separada[1].matches("[a-zA-Z]+")) {
				System.out.println("saltado");
			} else {
				taggs.add(separada[0]);
				taggs.add(separada[1]);
			} */
			
			//System.out.println(taggs.get(i));
		}

		String respuesta = "{\"palabras\" : [";

		for (int i = 0; i < taggs.size(); i++) {
			String entidad = "";
			int cont = 0;
			String palabra = taggs.get(i);
			String categoria = taggs.get(i+1);
			
			//System.out.println(palabra + " : " + categoria);
			
			for (int k = 0; k < taggs.size(); k++) {
				// no habrá diferencia entre palabras con letras mayúsculas y
				// minúsculas
				if (taggs.get(k).toLowerCase().equals(taggs.get(i).toLowerCase())) {
					cont++;
				}
			}

			for (int j = 0; j < entities.size(); j++) {

				String[] parts2 = entities.get(j).split("_");
				String palabraEnt = parts2[0];
				String ent = parts2[1];
				if (palabra.equals(palabraEnt)) {
					entidad = ent;
					break;
				}

			}

			respuesta += "{ \"palabra\" : \"" + palabra + "\",\"categoria\" : \"" + categoria + "\",\"repetida\": "
					+ cont + ",\"entidad\" : \"" + entidad + "\"},";
			i++;

		}

		respuesta = respuesta.substring(0, respuesta.length() - 1);

		respuesta += "]}";

		// el json se guarda
		PrintWriter json = new PrintWriter("/home/darkaliensky/Escritorio/apache-tomcat-7.0.81/lib/json/archivo.json");
		json.println(respuesta);
		json.close();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(respuesta);

	}
}