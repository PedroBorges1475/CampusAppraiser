package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Main;
import model.Avaliacao;
import model.Resultados;
import model.Servico;
import model.TipoServico;

public class GerenteView extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenteView frame = new GerenteView();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerenteView() {
		setResizable(false);
		setTitle("Gerente de Vota\u00E7\u00E3o - Campus Appraiser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/icone.png")));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnquetesAbertas = new JLabel("Enquetes abertas:");
		lblEnquetesAbertas.setBounds(10, 0, 143, 42);
		lblEnquetesAbertas.setFont(new Font("Impact", Font.PLAIN, 20));
		contentPane.add(lblEnquetesAbertas);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(157, 11, 267, 24);
		contentPane.add(comboBox);
		
		JButton btnVotar = new JButton("Votar");
		btnVotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeServico = "",tipoServico = "";
		        StringTokenizer tokenizer = new StringTokenizer(comboBox.getSelectedItem().toString()," - ");
		        while(tokenizer.hasMoreTokens())
		        {
		        	nomeServico = tokenizer.nextToken();
		            tipoServico = tokenizer.nextToken();
		        }
				Main.flagVoto = true;
		        Main.callAvaliadorFrame();
		        Main.flagVoto = false;
			}
		});
		btnVotar.setBounds(335, 46, 89, 23);
		contentPane.add(btnVotar);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnServicos = new JMenu("Servi\u00E7os");
		menuBar.add(mnServicos);
		
		JMenuItem mntmAdicionarServico = new JMenuItem("Adicionar servi\u00E7o");
		mntmAdicionarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = JOptionPane.showInputDialog(contentPane,"Nome do serviço:","Adicionar serviço",JOptionPane.INFORMATION_MESSAGE);
				if(nome != null) {
					ArrayList<TipoServico> lista = new ArrayList<TipoServico>();
					Main.getListaServicos().add(new Servico(nome,lista));
					FileWriter arq = null;
					try {
						arq = new FileWriter("./serv.db");
						PrintWriter serv = new PrintWriter(arq);
					    ArrayList<Servico> listaServ = Main.getListaServicos();
						listaServ.forEach((s)->{
							for(int i=0;i<s.getListaTipoServico().size();i++) {
								serv.println(""+s.getNome()+";"+s.getListaTipoServico().get(i));
							}
						});
						serv.close();
						arq.close();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Impossível adicionar serviço!", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mnServicos.add(mntmAdicionarServico);
		
		JMenu mnEnquete = new JMenu("Enquete");
		menuBar.add(mnEnquete);
		
		JMenuItem mntmCriarEnquete = new JMenuItem("Criar enquete");
		mntmCriarEnquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Servico> options = Main.getListaServicos();
				String[] opcoes = new String[Main.getListaServicos().size()];
				int i = 0;
				for(Servico s : options) {
					opcoes[i] = s.getNome();
					i++;
				}
				String servico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o serviço",JOptionPane.INFORMATION_MESSAGE,null, opcoes,opcoes[0]);
				if(servico != null) 
				{
					String nome = JOptionPane.showInputDialog(contentPane,"Nome do tipo de serviço:","Criar tipo de serviço",JOptionPane.INFORMATION_MESSAGE);
					if(nome != null) {
						Servico pesquisaservico = Main.procuraListaServicos(servico);
						pesquisaservico.getListaTipoServico().add(new TipoServico(nome));
						JOptionPane.showMessageDialog(null, "Criado com sucesso!","Criar enquete", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				String enquete = "";
				for(Servico s : Main.getListaServicos()) {
					for(i=0;i<s.getListaTipoServico().size();i++) {
						enquete = s.getNome().concat(" - " + s.getListaTipoServico().get(i).getNomeTipoServico());
						comboBox.addItem(enquete);
					}
				}
			}
		});
		mnEnquete.add(mntmCriarEnquete);
		
		JMenuItem mntmAlterarComponente = new JMenuItem("Alterar componente");
		mntmAlterarComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] opcoes = new String[]{"Serviço","Tipo de serviço"}; 
				String componente = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o componente que deseja alterar",JOptionPane.INFORMATION_MESSAGE,null, opcoes,opcoes[0]);
				if(componente.equals("Serviço")) {
					ArrayList<Servico> options = Main.getListaServicos();
					String[] op = new String[Main.getListaServicos().size()];
					int i = 0;
					for(Servico s : options) {
						op[i] = s.getNome();
						i++;
					}
					String servico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o serviço",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
					String nome = JOptionPane.showInputDialog(contentPane,"Nome do serviço:","Alterar componente",JOptionPane.INFORMATION_MESSAGE);
					Main.procuraListaServicos(servico).setNome(nome);
					String enquete = "";
					for(Servico s : Main.getListaServicos()) {
						for(i=0;i<s.getListaTipoServico().size();i++) {
							enquete = s.getNome().concat(" - " + s.getListaTipoServico().get(i).getNomeTipoServico());
							comboBox.addItem(enquete);
						}
					}
				}
				else if(componente.equals("Tipo de serviço")) {
					ArrayList<Servico> options = Main.getListaServicos();
					String[] op = new String[Main.getListaServicos().size()];
					int i = 0;
					for(Servico s : options) {
						op[i] = s.getListaTipoServico().get(i).getNomeTipoServico();
						i++;
					}
					String servico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o serviço",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
					if(servico != null) 
					{
						String tiposervico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o tipo de serviço",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
						String nome = JOptionPane.showInputDialog(contentPane,"Nome do tipo de serviço:","Alterar componente",JOptionPane.INFORMATION_MESSAGE);
						Main.procuraListaServicos(servico).procuraListaTipoServico(tiposervico).setNomeTipoServico(nome);
					}
					String enquete = "";
					for(Servico s : Main.getListaServicos()) {
						for(i=0;i<s.getListaTipoServico().size();i++) {
							enquete = s.getNome().concat(" - " + s.getListaTipoServico().get(i).getNomeTipoServico());
							comboBox.addItem(enquete);
						}
					}
				}
			}
		});
		mnEnquete.add(mntmAlterarComponente);
		
		JMenuItem mntmExcluirComponente = new JMenuItem("Excluir componente");
		mntmExcluirComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] opcoes = new String[]{"Serviço","Tipo de serviço"}; 
				String componente = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o componente que deseja alterar",JOptionPane.INFORMATION_MESSAGE,null, opcoes,opcoes[0]);
				if(componente.equals("Serviço")) {
					ArrayList<Servico> options = Main.getListaServicos();
					String[] op = new String[Main.getListaServicos().size()];
					int i = 0;
					for(Servico s : options) {
						op[i] = s.getNome();
						i++;
					}
					String servico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o serviço",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
					Main.getListaServicos().remove(Main.procuraListaServicos(servico));
					String enquete = "";
					for(Servico s : Main.getListaServicos()) {
						for(i=0;i<s.getListaTipoServico().size();i++) {
							enquete = s.getNome().concat(" - " + s.getListaTipoServico().get(i).getNomeTipoServico());
							comboBox.addItem(enquete);
						}
					}
				}
				else if(componente.equals("Tipo de serviço")) {
					ArrayList<Servico> options = Main.getListaServicos();
					String[] op = new String[Main.getListaServicos().size()];
					int i = 0;
					for(Servico s : options) {
						op[i] = s.getListaTipoServico().get(i).getNomeTipoServico();
						i++;
					}
					String servico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o serviço",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
					if(servico != null) 
					{
						String tiposervico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o tipo de serviço",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
//						Main.getListaServicos().remove(Main.procuraListaServicos(servico).procuraListaTipoServico(tiposervico));
					}
					String enquete = "";
					for(Servico s : Main.getListaServicos()) {
						for(i=0;i<s.getListaTipoServico().size();i++) {
							enquete = s.getNome().concat(" - " + s.getListaTipoServico().get(i).getNomeTipoServico());
							comboBox.addItem(enquete);
						}
					}
				}
			}
		});
		mnEnquete.add(mntmExcluirComponente);
		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatorio);
		
		JMenuItem mntmGerar = new JMenuItem("Gerar relat\u00F3rio");
		mntmGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnRelatorio.add(mntmGerar);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar relat\u00F3rio");
		mntmExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileWriter arq = null;
				try {
					arq = new FileWriter("./relatorio.csv");
					PrintWriter relatorio = new PrintWriter(arq);
					relatorio.println("servico;tiposervico;nota;opiniao");
				    ArrayList<Avaliacao> lista = Resultados.getListaResultados();
					lista.forEach((avaliacao)->{
						relatorio.println(""+avaliacao.getServico()+";"+avaliacao.getTipoServico()+";"+avaliacao.getNota()+";"+avaliacao.getOpiniao());
					});
					relatorio.close();
					arq.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Impossível exportar relatório!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnRelatorio.add(mntmExportar);

	}
}
