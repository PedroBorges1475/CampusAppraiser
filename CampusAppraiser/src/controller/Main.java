package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import model.Avaliacao;
import model.Resultados;
import model.Servico;
import model.TipoServico;
import model.Usuario;
import view.AdminView;
import view.AvaliadorView;
import view.GerenteView;
import view.LoginView;
import view.RelatorioView;

public class Main {
	
	private static LoginView loginView = new LoginView();
	private static AdminView adminView = new AdminView();
	private static AvaliadorView avaliadorView = new AvaliadorView();
	private static GerenteView gerenteView = new GerenteView();
	private static RelatorioView relatorioView = new RelatorioView();
	private static ArrayList<Servico> listaServicos = new ArrayList<Servico>();
	private static ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	public static boolean flagVoto = false;
	
	public static void main(String[] args) {
		importaServicos();
		importaUsuarios();
		importaResultados();
		loginView.setVisible(true);
	}
	
	public static void callAdminFrame(String votarservico,String votartipo) {
		if(flagVoto) {
			avaliadorView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			avaliadorView.votarEnquete(votarservico,votartipo);
			avaliadorView.clear();
		}
		adminView.setVisible(true);
	}
	
	public static void callAvaliadorFrame(String votarservico,String votartipo) {
		if(flagVoto) {
			avaliadorView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			avaliadorView.votarEnquete(votarservico,votartipo);
			avaliadorView.clear();
		}
		avaliadorView.setVisible(true);
	}
	
	public static void callGerenteFrame(String votarservico,String votartipo) {
		if(flagVoto) {
			avaliadorView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			avaliadorView.votarEnquete(votarservico,votartipo);
			avaliadorView.clear();
		}
		gerenteView.setVisible(true);
	}
	
	public static void callRelatorioView(boolean visible) {
		if(visible) {
		relatorioView.setVisible(true);
		} else {
		relatorioView.setVisible(false); 
		}
		
	}
	
	public static ArrayList<Servico> getListaServicos() {
		return listaServicos;
	}
	
	public static Servico procuraListaServicos(String nome){
		for(int i=0; i<listaServicos.size(); i++) {
			Servico servico = listaServicos.get(i);
			if (servico.getNome() == nome){
				return listaServicos.get(i);
		    }
		}
		return null;
	}
	
	public static ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	
	public static Usuario procuraListaUsuarios(String nome){
		for(int i=0; i<listaUsuarios.size(); i++) {
			Usuario u = listaUsuarios.get(i);
			if (u.getNomeUsuario() == nome){
				return listaUsuarios.get(i);
		    }
		}
		return null;
	}
	
	public static void importaServicos() {
        try {
        	File arquivo = new File("./serv.db");
        	if(arquivo.exists() == true) {
        		FileReader arq = new FileReader(arquivo);
    		    BufferedReader br = new BufferedReader(arq);
                String lido = "",nomeServico = "",tipoServico = "";
                while((lido = br.readLine()) != null) {
                	StringTokenizer tokenizer = new StringTokenizer(lido,";");
                	while(tokenizer.hasMoreTokens())
                	{
                	    nomeServico = tokenizer.nextToken();
                	    tipoServico = tokenizer.nextToken();
                	}
                	TipoServico t = new TipoServico(tipoServico);
                	boolean naoAdicionado = true;
                	for(Servico servico:Main.getListaServicos()) {
                		if(servico.getNome().equals(nomeServico)) {
                			try {
                			Main.procuraListaServicos(nomeServico).getListaTipoServico().add(t); //ta dando pau, mas nada que um try-catch não resolva
                			} catch  (NullPointerException e) {
                				
                			}
                			naoAdicionado = false;
                			break;
                		}
                	}
                	if(naoAdicionado) {
                		ArrayList<TipoServico> arr = new ArrayList<TipoServico>();
                    	arr.add(t);
                    	Servico s = new Servico(nomeServico,arr);
                		Main.getListaServicos().add(s);
                	}
                }
                br.close();
                arq.close();
        	} else {
        		
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void importaUsuarios() {
		try {
			File arquivo = new File("./user.db");
			if(arquivo.exists() == true) {
				FileReader arq = new FileReader(arquivo);
			    BufferedReader br = new BufferedReader(arq);
	            String lido = "",usuario = "",senha = "",permissao = "";
	            while((lido = br.readLine()) != null) {
	            	StringTokenizer tokenizer = new StringTokenizer(lido,";");
	            	while(tokenizer.hasMoreTokens())
	            	{
	            	    usuario = tokenizer.nextToken();
	            	    senha = tokenizer.nextToken();
	            	    permissao = tokenizer.nextToken();
	            	}
	            	Usuario u = new Usuario(usuario,senha,permissao);
	            	Main.getListaUsuarios().add(u);
	            }
	            br.close();
	            arq.close();
			} else {
				
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void importaResultados() {
		try {
			File arquivo = new File("./result.db");
			if(arquivo.exists() == true) {
				FileReader arq = new FileReader(arquivo);
			    BufferedReader br = new BufferedReader(arq);
	            String lido = "",nomeServico = "",tipoServico = "",nota = "",txtOpiniao = "";
	            while((lido = br.readLine()) != null) {
	            	StringTokenizer tokenizer = new StringTokenizer(lido,";");
	            	while(tokenizer.hasMoreTokens())
	            	{
	            	    nomeServico = tokenizer.nextToken();
	            	    tipoServico = tokenizer.nextToken();
	            	    nota = tokenizer.nextToken();
	            	    txtOpiniao = tokenizer.nextToken();
	            	}
	            	Avaliacao a = new Avaliacao(nomeServico,tipoServico,nota,txtOpiniao);
	            	ArrayList<Avaliacao> arr = new ArrayList<Avaliacao>();
	            	arr.add(a);
	            	Resultados r = new Resultados(arr);
	            }
			} else {
				
			}		
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}