package model;

public class Usuario {
	String nome,senha,permissao;
	
	public Usuario(String nome,String senha,String permissao) {
		this.nome = nome;
		this.senha = senha;
		this.permissao = permissao;
	}
	
	public String getNomeUsuario() {
		return nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getPermissao() {
		return permissao;
	}
}
