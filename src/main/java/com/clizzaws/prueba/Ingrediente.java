package com.clizzaws.prueba;

public class Ingrediente {
	private String nombreIngrediente;
	public Ingrediente(String nombreIngrediente)
	{
		setNombreIngrediente(nombreIngrediente);
	}
	public String getNombreIngrediente()
	{
		return nombreIngrediente;
	}
	public void setNombreIngrediente(String nombreIngrediente)
	{
		this.nombreIngrediente=nombreIngrediente;
	}
}
