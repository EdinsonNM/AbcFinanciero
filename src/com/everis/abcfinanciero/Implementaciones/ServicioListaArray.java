package com.everis.abcfinanciero.Implementaciones;

import java.util.ArrayList;

import com.everis.abcfinanciero.Dominio.ItemWord;
import com.everis.abcfinanciero.Interfaces.IServicioLista;

public class ServicioListaArray implements IServicioLista {

	@Override
	public ArrayList<ItemWord> GetListaAll() {
		ArrayList<ItemWord> listado=new ArrayList<ItemWord>();
		listado.add(new ItemWord("Todas las palabras","nro de resultados",""));
		listado.add(new ItemWord("Todas las palabras","nro de resultados",""));
		listado.add(new ItemWord("Todas las palabras","nro de resultados",""));
		return listado;
	}

}
