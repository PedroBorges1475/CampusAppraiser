package model;

import java.util.ArrayList;

import controller.Main;

public class Servico {
	
	private String nome;
	private static ArrayList<TipoServico> lista;
	
	public Servico(String nome,ArrayList<TipoServico> lista) {
		this.nome = nome;
		this.lista = lista;
	}
	
	public void addTipoServico(String nome) {
		TipoServico tiposervico = new TipoServico(nome);
		lista.add(tiposervico);
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<TipoServico> getListaTipoServico() {
		return lista;
	}
	
	public TipoServico procuraListaTipoServico(String nome){
		for(int i=0; i<lista.size(); i++) {
			TipoServico tiposervico = lista.get(i);
			if (tiposervico.getNomeTipoServico() == nome){
				return lista.get(i);
		    }
		}
		return null;
	}
}
