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
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:3000")
public class ClizzaWsApplication {
	
	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
	@RequestMapping("/roger")
	String roger() {
		return "Hello Roger";
	}
	@RequestMapping("/prueba")
	String prueba() {
		Connection conn = null;
		PreparedStatement pstmnt = null;
		ResultSet datos = null;
		ArrayList<Ingrediente> lista = new ArrayList<Ingrediente>();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
			pstmnt = conn.prepareStatement("select nombreIngrediente from ingredientes where activo=1");
			datos = pstmnt.executeQuery();
			while(datos.next())
			{
				lista.add(new Ingrediente(datos.getString("nombreIngrediente")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		return json;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClizzaWsApplication.class, args);
	}
}
