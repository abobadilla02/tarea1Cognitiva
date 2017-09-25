package servlet;

import java.io.FileReader;
import java.io.IOException;
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
 * Servlet implementation class ner
 */
@WebServlet("/ner")
public class Ner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String entidad = request.getParameter("entidad");

		String [] array=null;

		//arreglo con las entidades
		String [] entidades ={"location","person"};

		if(entidad.equals("location")){
		//arreglo con las categorias de sustantivos
			array=new String[] {"LOCATION"};
		}

		if(entidad.equals("person")){
		//arreglo con las categorias de adjetivos
			array=new String[] {"PERSON"};
		}

	
		
	
       //Obtener json del archivo y hacer un arreglo con las palabras pertenecientes a la categoría
		JSONParser parser = new JSONParser();
		try {
			JSONObject a = (JSONObject) parser.parse(new FileReader("/home/darkaliensky/Escritorio/apache-tomcat-7.0.81/lib/json/archivo.json"));
			
			//arreglo de palabras del documento
			JSONArray palabras = (JSONArray) a.get("palabras");
			
			for(int k=0;k<entidades.length;k++){			
				if(entidad.equals(entidades[k])){

					String respuesta="";

					for(int i=0;i<palabras.size();i++){
						
						JSONObject palabra1=(JSONObject) palabras.get(i);
					
						String ent=(String) palabra1.get("entidad");
						
						for(int j=0;j<array.length;j++){

							if(ent.equals(array[j])){
								String palabra=(String) palabra1.get("palabra");
						
								respuesta+=palabra+"_"+entidades[k]+",";
								
								break;
							}
						}			
					}
					
					if(respuesta.length()>0){
						respuesta=respuesta.substring(0, respuesta.length()-1);
					}else{
						respuesta="";
					}
					
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