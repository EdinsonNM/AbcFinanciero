package com.everis.abcfinanciero.Dominio;

public class ItemWord {
 private int id;
 private String titulo;
 private String detalle;
 private String descripcion;
 private String srcImg;
 public ItemWord(){ }
public ItemWord(String titulo,String detalle,String descripcion){
	this.titulo=titulo;
	this.detalle=detalle;
	this.descripcion=descripcion;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getDetalle() {
	return detalle;
}
public void setDetalle(String detalle) {
	this.detalle = detalle;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public String getSrcImg() {
	return srcImg;
}
public void setSrcImg(String srcImg) {
	this.srcImg = srcImg;
}
 
 
}
