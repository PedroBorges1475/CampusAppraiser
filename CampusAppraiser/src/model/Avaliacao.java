package model;

public class Avaliacao {
	
	private String servico;
	private String tiposervico;
	private String nota;
	private String opiniao;
	
	public Avaliacao(String servico,String tiposervico,String nota,String opiniao) {
		this.servico = servico;
		this.tiposervico = tiposervico;
		this.nota = nota;
		this.opiniao = opiniao;
	}
	
	public String getServico() {
		return servico;
	}
	public String getTipoServico() {
		return tiposervico;
	}
	public String getNota() {
		return nota;
	}
	public String getOpiniao() {
		return opiniao;
	}
}
