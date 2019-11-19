package model;

import java.util.ArrayList;

public class Resultados {
	
	private static int numVotos = 0;
	
	private static ArrayList<Avaliacao> lista = new ArrayList<Avaliacao>();
	
	public static int getNumVotos() {
		return numVotos;
	}
	
	public static void adicionarVoto(Avaliacao avaliacao) {
		lista.add(avaliacao);
		numVotos++;
	}
	
	public static ArrayList<Avaliacao> getResultado() {
		return lista;
	}

}
