package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnComponente = new JMenu("Componente");
		menuBar.add(mnComponente);
		
		JMenuItem mntmAdicionar = new JMenuItem("Adicionar");
		mnComponente.add(mntmAdicionar);
		
		JMenuItem mntmAlterar = new JMenuItem("Alterar");
		mnComponente.add(mntmAlterar);
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mnComponente.add(mntmExcluir);
		
		JMenu mnUsuario = new JMenu("Usu\u00E1rio");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmAdicionarUsuario = new JMenuItem("Adicionar");
		mnUsuario.add(mntmAdicionarUsuario);
		
		JMenuItem mntmExcluirUsuario = new JMenuItem("Excluir");
		mnUsuario.add(mntmExcluirUsuario);
		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatorio);
		
		JMenuItem mntmGerar = new JMenuItem("Gerar relat\u00F3rio");
		mnRelatorio.add(mntmGerar);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar relat\u00F3rio");
		mnRelatorio.add(mntmExportar);
	}

}
