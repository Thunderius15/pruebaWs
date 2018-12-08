package com.clizzaws.prueba;
//Clase ingrediente (modelo)
public class Ingrediente {
	//Atributos: nombreIngrediente, nombreImagen
	private String nombreIngrediente;
	private String nombreImagen;
	//Metodo constructor que recibe parametros
	public Ingrediente(String nombreIngrediente,String nombreImagen)
	{
		setNombreIngrediente(nombreIngrediente);
		setNombreImagen(nombreImagen);
	}
	//Getters y Setters
	public String getNombreIngrediente()
	{
		return nombreIngrediente;
	}
	public void setNombreIngrediente(String nombreIngrediente)
	{
		this.nombreIngrediente=nombreIngrediente;
	}
	public String getNombreImagen()
	{
		return nombreImagen;
	}
	public void setNombreImagen(String nombreImagen)
	{
		this.nombreImagen=nombreImagen;
	}
}
