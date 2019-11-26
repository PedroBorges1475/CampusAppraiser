package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Main;
import model.Avaliacao;
import model.Resultados;
import model.Servico;
import model.TipoServico;
import model.Usuario;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class AdminView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView frame = new AdminView();
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
	public AdminView() {
		setTitle("Painel de Administrador - Campus Appraiser");
		setResizable(false);
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
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<Servico> options = Main.getListaServicos();
				String[] opcoes = new String[Main.getListaServicos().size()];
				int i = 0;
				for(Servico s : options) {
					opcoes[i] = s.getNome();
					i++;
				}
				String enquete = "";
				comboBox.removeAllItems();
			//	Main.getListaServicos().clear();
			//	Main.importaServicos();
				for(Servico s : Main.getListaServicos()) { //COLOCAR CONDICIONAL para limpar a lista e não repetir
					for(i=0;i<s.getListaTipoServico().size();i++) {
						enquete = s.getNome().concat(" - " + s.getListaTipoServico().get(i).getNomeTipoServico());
						comboBox.addItem(enquete);
					}
				}
			}
		});
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
		        Main.callAvaliadorFrame(nomeServico,tipoServico);
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
					Servico s = new Servico(nome,lista);
					FileWriter arq = null;
					File file = new File("./serv.db");
					try {
						if(file.exists() == false) {
							arq = new FileWriter("./serv.db");
							PrintWriter serv = new PrintWriter(arq);
						    serv.println(""+s.getNome()+";null");
							Main.getListaServicos().add(s);
							serv.close();
							arq.close();
						} else {
							arq = new FileWriter("./serv.db",true);
							PrintWriter serv = new PrintWriter(arq);
						    serv.println(""+s.getNome()+";null");
							Main.getListaServicos().add(s);
							serv.close();
							arq.close();
						}
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
					String tipoServico = JOptionPane.showInputDialog(contentPane,"Nome do tipo de serviço:","Criar tipo de serviço",JOptionPane.INFORMATION_MESSAGE);
					if(tipoServico != null) {
						Servico pesquisaservico = Main.procuraListaServicos(servico);
						pesquisaservico.getListaTipoServico().add(new TipoServico(tipoServico));
						try {
							appendTipoServico(servico,tipoServico);                      //REVISAAAR
							JOptionPane.showMessageDialog(null, "Criado com sucesso!","Criar enquete", JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				String enquete = "";
				comboBox.removeAllItems();
			//	Main.getListaServicos().clear();
			//	Main.importaServicos();
				for(Servico s : Main.getListaServicos()) { //COLOCAR CONDICIONAL para limpar a lista e não repetir
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
					try {
						if(!Main.getListaServicos().isEmpty()) {
							Main.procuraListaServicos(servico).setNome(nome);
							JOptionPane.showMessageDialog(null, "Componente alterado com sucesso!","Alterar componente", JOptionPane.INFORMATION_MESSAGE);
							String enquete = "";
							for(Servico s : Main.getListaServicos()) {
								for(i=0;i<s.getListaTipoServico().size();i++) {
									enquete = s.getNome().concat(" - " + s.getListaTipoServico().get(i).getNomeTipoServico());
									comboBox.addItem(enquete);
								}
							}
						}
						else {
							throw new Exception();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Serviço não existente/lista de serviços vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(componente.equals("Tipo de serviço")) {
					ArrayList<Servico> options = Main.getListaServicos();
					String[] op = new String[Main.getListaServicos().size()];
					int i = 0;
					for(Servico s : options) {
						op[i] = s.getNome();
						i++;
					}
					String servico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o serviço",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
					if(servico != null && !Main.procuraListaServicos(servico).getListaTipoServico().isEmpty()) 
					{
						i = 0;
						for(TipoServico t : Main.procuraListaServicos(servico).getListaTipoServico()) {
							op[i] = t.getNomeTipoServico();
							i++;
						}
						String tiposervico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o tipo de serviço",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
						String nome = JOptionPane.showInputDialog(contentPane,"Nome do tipo de serviço:","Alterar componente",JOptionPane.INFORMATION_MESSAGE);
						try {
							if(!Main.procuraListaServicos(servico).getListaTipoServico().isEmpty()) {
								Main.procuraListaServicos(servico).procuraListaTipoServico(tiposervico).setNomeTipoServico(nome);
								JOptionPane.showMessageDialog(null, "Componente alterado com sucesso!","Alterar componente", JOptionPane.INFORMATION_MESSAGE);
								String enquete = "";
								for(Servico s : Main.getListaServicos()) {
									for(i=0;i<s.getListaTipoServico().size();i++) {
										enquete = s.getNome().concat(" - " + s.getListaTipoServico().get(i).getNomeTipoServico());
										comboBox.addItem(enquete);
									}
								}
							}
							else {
								throw new Exception();
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Não existem tipos para este serviço/lista de tipos de serviço vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
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
						op[i] = s.getNome();
						i++;
					}
					String servico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o serviço",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
					for(Servico s : options) {
						op[i] = s.getListaTipoServico().get(i).getNomeTipoServico();
						i++;
					}
					String tiposervico = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o tipo de serviço",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
					Main.procuraListaServicos(servico).getListaTipoServico().remove(Main.procuraListaServicos(servico).procuraListaTipoServico(tiposervico));
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
		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio"); //Não faz nada
		menuBar.add(mnRelatorio);
		
		JMenuItem mntmGerar = new JMenuItem("Gerar relat\u00F3rio");	//Não faz nada
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
					JOptionPane.showMessageDialog(null, "Relatório exportado com sucesso!","Exportar relatório", JOptionPane.INFORMATION_MESSAGE);
					relatorio.close();
					arq.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Impossível exportar relatório!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnRelatorio.add(mntmExportar);
		
		JMenu mnUsuario = new JMenu("Usu\u00E1rio");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmAdicionarUsuario = new JMenuItem("Adicionar usuário");
		mntmAdicionarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = JOptionPane.showInputDialog(contentPane,"Nome de usuário:","Cadastrar usuário",JOptionPane.INFORMATION_MESSAGE);
				String senha = JOptionPane.showInputDialog(contentPane,"Senha:","Cadastrar usuário",JOptionPane.INFORMATION_MESSAGE);
				String[] op = {"Avaliador","Gerente de votação","Administrador"};
				String permissao = (String) JOptionPane.showInputDialog(contentPane,null,"Escolha o tipo de permissão",JOptionPane.INFORMATION_MESSAGE,null, op,op[0]);
				FileWriter arq = null;
				try {
					arq = new FileWriter("./user.db",true);
					PrintWriter db = new PrintWriter(arq);
					switch(permissao) {
						case "Avaliador":
							db.println("" + usuario + ";" + senha+ ";AV");
							db.close();
							arq.close();
							break;
						case "Gerente de votação":
							db.println("" + usuario + ";" + senha+ ";GER");
							db.close();
							arq.close();
							break;
						case "Administrador":
							db.println("" + usuario + ";" + senha+ ";ADM");
							db.close();
							arq.close();
							break;
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"Impossível cadastrar usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnUsuario.add(mntmAdicionarUsuario);
		
		JMenuItem mntmRemoverUsuario = new JMenuItem("Remover usuário");
		mntmRemoverUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileReader arq = null;
				try {
					arq = new FileReader("./user.db");
					BufferedReader br = new BufferedReader(arq);
					String pesquisa = JOptionPane.showInputDialog(contentPane,"Nome de usuário:","Remover usuário",JOptionPane.INFORMATION_MESSAGE);
		            String lido = "";
		            while((lido = br.readLine()) != null) {
		            	StringTokenizer tokenizer = new StringTokenizer(lido,";");
		            	while(tokenizer.hasMoreTokens())
		            	{
		            		String encontrado = tokenizer.nextToken();
		            		tokenizer.nextToken();
		            		tokenizer.nextToken();
		            		if(pesquisa.equals(encontrado)) {
		            			int confirmacao = JOptionPane.showConfirmDialog(null,"Remover usuário '" + pesquisa + "'?",("Tem certeza de que deseja remover o usuário " + pesquisa.toUpperCase() + "?"),JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		            			if(confirmacao == 0) {
		            				rem(pesquisa);
		            			}
		            		}
		            	}
		            }
		            br.close();
		            arq.close();
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		mnUsuario.add(mntmRemoverUsuario);
	}
	
	public void rem(String usu) {
		FileReader fl;
		try {
			fl = new FileReader("./user.db");
			BufferedReader br = new BufferedReader(fl);
			String lido = "",usuario = "",senha = "", permissao = "";
			ArrayList<Usuario> arr = new ArrayList<Usuario>();
	        while((lido = br.readLine()) != null) {
	        	StringTokenizer tokenizer = new StringTokenizer(lido,";");
	        	while(tokenizer.hasMoreTokens()) {
	        	    usuario = tokenizer.nextToken();
	        	    senha = tokenizer.nextToken();
	        	    permissao = tokenizer.nextToken();
	        	    Usuario u = new Usuario(usuario, senha, permissao);
	        	    arr.add(u);
	        	}
	        }
	        br.close();
	        fl.close();
	        FileWriter fl1 = new FileWriter("./user.db");
	        BufferedWriter br1 = new BufferedWriter(fl1);
	        for(Usuario user: arr) {
	        	if(!user.getNomeUsuario().equals(usu)) {
	        		br1.write(user.getNomeUsuario() +";" + user.getSenha()+";" + user.getPermissao());
	        		br1.newLine();
	        	}
	        }
	        br1.close();
	        fl1.close();
	        JOptionPane.showMessageDialog(null, "Excluido com sucesso!","Remover Usuário", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void appendTipoServico(String servicoadd,String tipoadd) throws IOException {
		FileReader fl;
		try {
			fl = new FileReader("./serv.db");
			BufferedReader br = new BufferedReader(fl);
			String lido = "",nomeServico = "",tipoServico = "";
			ArrayList<Servico> arr = new ArrayList<Servico>();
	        while((lido = br.readLine()) != null) {
	        	StringTokenizer tokenizer = new StringTokenizer(lido,";");
	        	while(tokenizer.hasMoreTokens()) {
	        		nomeServico = tokenizer.nextToken();
	        	    tipoServico = tokenizer.nextToken();
	        	}
	        	    TipoServico t = new TipoServico(tipoServico);
	        	    ArrayList<TipoServico> tipo = new ArrayList<TipoServico>();
	        	    tipo.add(t);
	        	    Servico s = new Servico(nomeServico,tipo);
	        	    arr.add(s);
	        	
	        }
	        br.close();
	        fl.close();
	        FileWriter fl1 = new FileWriter("./serv.db");
	        BufferedWriter br1 = new BufferedWriter(fl1);
	        boolean naoAdicionou = true;
	        for(Servico s: arr) {
	        	if(s.getNome().equals(servicoadd)) {
	        		TipoServico t = s.getListaTipoServico().get(0);
        			if(t.getNomeTipoServico().equals("null")) {
        				t.getNomeTipoServico().replace("null",tipoadd);
        				br1.write(s.getNome() +";" + tipoadd);
		        		br1.newLine();
		        		naoAdicionou = false;
        			}else {
    	        	    t = s.getListaTipoServico().get(0); 
            			br1.write(s.getNome() +";" + t.getNomeTipoServico());
    		        	br1.newLine(); 
    	        		
    	        	}
	        			
	        	} else {
	        		TipoServico t = s.getListaTipoServico().get(0); 
        			br1.write(s.getNome() +";" + t.getNomeTipoServico());
		        	br1.newLine(); 
	        		
	        	}
	        }
	        
	        
	        if(naoAdicionou) {
	        	br1.write(servicoadd +";" + tipoadd);
	        	br1.newLine(); 
	        }	        
	        br1.close();
	        fl1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
