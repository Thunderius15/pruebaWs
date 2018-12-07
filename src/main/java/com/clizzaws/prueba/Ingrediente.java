package com.clizzaws.prueba;

public class Ingrediente {
	private String nombreIngrediente;
	private String nombreImagen;
	public Ingrediente(String nombreIngrediente,String nombreImagen)
	{
		setNombreIngrediente(nombreIngrediente);
		setNombreImagen(nombreImagen);
	}
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
