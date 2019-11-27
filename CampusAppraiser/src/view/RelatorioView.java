package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Avaliacao;
import model.AvaliacaoTableModel;
import model.Servico;
import model.TipoServico;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;

public class RelatorioView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioView frame = new RelatorioView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public RelatorioView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		FileReader fl;
		
		try {
			fl = new FileReader("./result.db");
			BufferedReader br = new BufferedReader(fl);
			String lido = "", nomeServico = "",tipoServico = "", nota = "", opiniao ="";
			Avaliacao av;
			ArrayList<Avaliacao> avaliacao = new ArrayList<Avaliacao>();
			while((lido = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(lido,";");
				while(tokenizer.hasMoreTokens()) {
	        		nomeServico = tokenizer.nextToken();
	        	    tipoServico = tokenizer.nextToken();
	        		nota = tokenizer.nextToken();
	        	    opiniao = tokenizer.nextToken();
	        	    av = new Avaliacao(nomeServico, tipoServico, nota, opiniao);
					avaliacao.add(av);
	        	}
				
			}
			AvaliacaoTableModel atm = new AvaliacaoTableModel(avaliacao);
			table = new JTable();
			table.setBounds(10, 11, 414, 206);
			contentPane.add(table);		
			table.setModel(atm);
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Exemplo pra rodar e preencher a tabela
		
		
		
//		FileReader fl;
//		try {
//			fl = new FileReader("./serv.db");
//			BufferedReader br = new BufferedReader(fl);
//			String lido = "",nomeServico = "",tipoServico = "";
//			ArrayList<Servico> arr = new ArrayList<Servico>();
//	        while((lido = br.readLine()) != null) {
//	        	StringTokenizer tokenizer = new StringTokenizer(lido,";");
//	        	while(tokenizer.hasMoreTokens()) {
//	        		nomeServico = tokenizer.nextToken();
//	        	    tipoServico = tokenizer.nextToken();
//	        	}
//	        	    TipoServico t = new TipoServico(tipoServico);
//	        	    ArrayList<TipoServico> tipo = new ArrayList<TipoServico>();
//	        	    tipo.add(t);
//	        	    Servico s = new Servico(nomeServico,tipo);
//	        	    arr.add(s);
//	        	
//	        }
//	        br.close();
//	        fl.close();
//	        FileWriter fl1 = new FileWriter("./serv.db");
//	        BufferedWriter br1 = new BufferedWriter(fl1);
//	        boolean naoAdicionou = true;
//	        for(Servico s: arr) {
//	        	if(s.getNome().equals(servicoadd)) {
//	        		TipoServico t = s.getListaTipoServico().get(0);
//        			if(t.getNomeTipoServico().equals("null")) {
//        				t.getNomeTipoServico().replace("null",tipoadd);
//        				br1.write(s.getNome() +";" + tipoadd);
//		        		br1.newLine();
//		        		naoAdicionou = false;
//        			}else {
//    	        	    t = s.getListaTipoServico().get(0); 
//            			br1.write(s.getNome() +";" + t.getNomeTipoServico());
//    		        	br1.newLine(); 
//    	        		
//    	        	}
//	        			
//	        	} else {
//	        		TipoServico t = s.getListaTipoServico().get(0); 
//        			br1.write(s.getNome() +";" + t.getNomeTipoServico());
//		        	br1.newLine(); 
//	        		
//	        	}
//	        }
//	        
//	        
//	        if(naoAdicionou) {
//	        	br1.write(servicoadd +";" + tipoadd);
//	        	br1.newLine(); 
//	        }	        
//	        br1.close();
//	        fl1.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RelatorioView.super.setVisible(false);
			}
		});
		btnOk.setBounds(335, 227, 89, 23);
		contentPane.add(btnOk);
	}
}
