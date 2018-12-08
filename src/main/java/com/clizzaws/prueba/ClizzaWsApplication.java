package com.clizzaws.prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

//Se indica que es una aplicacion de spring boot
@SpringBootApplication
//Tiene Controlador Rest (no entiendo muy bien esta linea)
@RestController
//Para que se autoconfigure
@EnableAutoConfiguration
//Para que acepte peticiones de react
@CrossOrigin(origins = "http://localhost:3000")
//Nombre de la clase
public class ClizzaWsApplication {
	//Si se utiliza la url localhost:8080/ accede al metodo home
	@RequestMapping("/")
	//Metodo home que retorna un string
	String home() {
		//Retorna un Hello World
		return "Hello World!";
	}
	//Si se utiliza la url localhost:8080/roger accede al metodo roger
	@RequestMapping("/roger")
	//Metodo roger que retorna un string
	String roger() {
		//Retorna un Hello Roger
		return "Hello Roger";
	}
	//Si se utiliza la url localhost:8080/prueba accede al metodo prueba
	@RequestMapping("/prueba")
	//Metodo prueba que retorna un string
	String prueba() {
		//Se generan los objetos para la conexion a la base de datos
		Connection conn = null;
		PreparedStatement pstmnt = null;
		ResultSet datos = null;
		//Se genera un arraylist de Ingrediente, que seria un modelo donde se almacenaria la informacion de la base de datos
		ArrayList<Ingrediente> lista = new ArrayList<Ingrediente>();
		try
		{
			//Se carga el driver
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			//Se genera la conexion
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
			//Se configura el pstmnt y se le asigna la consulta (selecciona nombreIngrediente y nombreImagen de la tabla ingredientes mientras esten activos)		
			pstmnt = conn.prepareStatement("select nombreIngrediente,nombreImagen from ingredientes where activo=1");
			//Se obtiene el resultado en datos
			datos = pstmnt.executeQuery();
			//Se repite para cada resultado
			while(datos.next())
			{
				//Se genera un objeto Ingrediente, se le envian los datos en el metodo contructor y se agrega al arraylist
				lista.add(new Ingrediente(datos.getString("nombreIngrediente"),datos.getString("nombreImagen")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//Se genera un objeto gson
		Gson gson = new Gson();
		//Se convierte el arraylist en una cadena json utilizando el objeto gson
		String json = gson.toJson(lista);
		//Se retorna la cadena json
		return json;
	}
	//Si se utiliza la url localhost:8080/pruebaParametro accede al metodo prueba
	@RequestMapping("/pruebaParametro")
	//Metodo pruebaParametro que retorna un string y requiere un parametro con name=valor y lo recibe como un entero llamado valor
	public String pruebaParametro(@RequestParam("valor") int valor){
		//Se genera un objeto json
		JsonObject object = new JsonObject();
		//Se agrega una propiedad con name=mensaje y el contenido
		object.addProperty("mensaje", "el valor es: "+valor);
		//Se retorna el objeto json convertido a cadena
		return object.toString();
	}
	//Metodo main que encarga de inicializar el web service
	public static void main(String[] args) {
		SpringApplication.run(ClizzaWsApplication.class, args);
	}
}
