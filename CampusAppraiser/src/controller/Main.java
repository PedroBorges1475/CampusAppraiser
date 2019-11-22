package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import model.Avaliacao;
import model.Resultados;
import model.Servico;
import model.TipoServico;
import view.AdminView;
import view.AvaliadorView;
import view.GerenteView;
import view.LoginView;

public class Main {
	
	private static LoginView loginView = new LoginView();
	private static AdminView adminView = new AdminView();
	private static AvaliadorView avaliadorView = new AvaliadorView();
	private static GerenteView gerenteView = new GerenteView();
	private static ArrayList<Servico> listaServicos = new ArrayList<Servico>();
	public static boolean flagVoto = false;
	
	public static void main(String[] args) {
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
	
	public static void callGerenteFrame() {
		gerenteView.setVisible(true);
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
	
	public static void converteArquivo() {
        try(BufferedReader br = new BufferedReader(new FileReader("/serv.db")))
        {
            String lido = "",nomeServico = "",tipoServico = "";
            while((lido = br.readLine()) != null) {
            	StringTokenizer tokenizer = new StringTokenizer(lido,";");
            	while(tokenizer.hasMoreTokens())
            	{
            	    nomeServico = tokenizer.nextToken();
            	    tipoServico = tokenizer.nextToken();
            	}
            	TipoServico t = new TipoServico(tipoServico);
            	ArrayList<TipoServico> arr = new ArrayList<TipoServico>();
            	arr.add(t);
            	Servico s = new Servico(nomeServico,arr);
            	Main.getListaServicos().add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
//	public static void initResult() {
//		try(BufferedReader br = new BufferedReader(new FileReader("/result.db")))
//        {
//            String lido = "",nomeServico = "",tipoServico = "",nota = "",txtOpiniao = "";
//            while((lido = br.readLine()) != null) {
//            	StringTokenizer tokenizer = new StringTokenizer(lido,";");
//            	while(tokenizer.hasMoreTokens())
//            	{
//            	    nomeServico = tokenizer.nextToken();
//            	    tipoServico = tokenizer.nextToken();
//            	    nota = tokenizer.nextToken();
//            	    txtOpiniao = tokenizer.nextToken();
//            	}
//            	Avaliacao a = new Avaliacao(nomeServico,tipoServico,nota,txtOpiniao);
//            	ArrayList<Avaliacao> arr = new ArrayList<Avaliacao>();
//            	arr.add(a);
//            	Resultados r = new Resultados(arr);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//	}
}