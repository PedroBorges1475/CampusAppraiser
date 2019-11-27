package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.Avaliacao;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class RelatorioView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static DefaultTableModel model;
	private JButton btnAtualizar;
	

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		FileReader fl;
		table = new JTable(new DefaultTableModel(new Object[][] {},new String[] {"Serviço","Tipo de serviço","Nota","Opinião"}));
		model = (DefaultTableModel) table.getModel();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 414, 206);
		contentPane.add(scrollPane);
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
	        	    model.addRow(new Object[]{nomeServico,tipoServico,nota,opiniao});
	        	}
			}
			br.close();
			fl.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JButton btnOk = new JButton("Fechar");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RelatorioView.super.setVisible(false);
			}
		});
		btnOk.setBounds(335, 227, 89, 23);
		contentPane.add(btnOk);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileReader fl;
				try {
					fl = new FileReader("./result.db");
					BufferedReader br = new BufferedReader(fl);
					String lido = "", nomeServico = "",tipoServico = "", nota = "", opiniao ="";
					ArrayList<Avaliacao> avaliacao = new ArrayList<Avaliacao>();
					while((lido = br.readLine()) != null) {
						StringTokenizer tokenizer = new StringTokenizer(lido,";");
						while(tokenizer.hasMoreTokens()) {
			        		nomeServico = tokenizer.nextToken();
			        	    tipoServico = tokenizer.nextToken();
			        		nota = tokenizer.nextToken();
			        	    opiniao = tokenizer.nextToken();
							avaliacao.add(new Avaliacao(nomeServico, tipoServico, nota, opiniao));
			        	}
					}
					table.revalidate();
					br.close();
					fl.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAtualizar.setBounds(236, 228, 89, 23);
		contentPane.add(btnAtualizar);
	}
}
