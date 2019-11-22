package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Main;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField usuarioField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
					Main.converteArquivo();
					FileWriter arq = new FileWriter("./user.db",true);
					PrintWriter db = new PrintWriter(arq);
					db.println("usuario;senha");
					db.close();
					arq.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public LoginView() {
		setTitle("Campus Appraiser");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/icone.png")));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usuarioField = new JTextField();
		usuarioField.setBounds(116, 136, 180, 20);
		contentPane.add(usuarioField);
		usuarioField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(163, 237, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String user = usuarioField.getText(); 
					String passwd = passwordField.getText();
					File arquivo = new File("./user.db");
					FileReader arq = new FileReader(arquivo);
				    BufferedReader reader = new BufferedReader(arq);
				    String read1 = reader.readLine();
				    String read2 = reader.readLine();
				    String lido = "";
				    while((lido = reader.readLine()) != null){
					    if(user.equals(read1) && passwd.equals(read2)) {			    	
					    	JOptionPane.showMessageDialog(null, "Logado com sucesso!","", JOptionPane.INFORMATION_MESSAGE);
					    	dispose();
					    	Main.callAvaliadorFrame(null,null);
					    	reader.close();
					    	arq.close();
					    } else if(user.equals("admin") && passwd.equals("admin")) {
					    	JOptionPane.showMessageDialog(null, "Logado com sucesso!","", JOptionPane.INFORMATION_MESSAGE);
					    	dispose();
					    	Main.callAdminFrame(null,null);
					    } else if(user.equals("gerente") && passwd.equals("gay")) {
					    	JOptionPane.showMessageDialog(null, "Logado com sucesso!","", JOptionPane.INFORMATION_MESSAGE);
					    	dispose();
					    	Main.callGerenteFrame();
					   	} else if(user.equals("")) {
					   		reader.close();
					   		arq.close();
					   		throw new Exception();				   		
					    } else if(!user.equals(read1) || !passwd.equals(read2)) {
					    	reader.close();
					    	arq.close();
					    	throw new Exception();
					    }
				    }
				    reader.close();
				    arq.close();
				}
				catch(Exception loginerror) {
					JOptionPane.showMessageDialog(null, "Dados incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		contentPane.add(btnLogin);
		
		JLabel lblLogin = new JLabel("Usuário");
		lblLogin.setBounds(116, 111, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(116, 167, 46, 14);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(116, 192, 180, 20);
		contentPane.add(passwordField);
		
		JLabel lblimg = new JLabel("");
		lblimg.setIcon(new ImageIcon(LoginView.class.getResource("/logo.png")));
		lblimg.setBounds(10, 11, 424, 89);
		contentPane.add(lblimg);
	}
}
