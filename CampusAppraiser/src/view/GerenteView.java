package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class GerenteView extends JFrame {

	private JPanel contentPane;

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
		setTitle("Gerente de Vota\u00E7\u00E3o - Campus Appraiser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnquetesAbertas = new JLabel("Enquetes abertas:");
		lblEnquetesAbertas.setFont(new Font("Impact", Font.PLAIN, 20));
		lblEnquetesAbertas.setBounds(10, 0, 143, 42);
		contentPane.add(lblEnquetesAbertas);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEnquete = new JMenu("Enquete");
		menuBar.add(mnEnquete);
		
		JMenuItem mntmCriarEnquete = new JMenuItem("Criar enquete");
		mnEnquete.add(mntmCriarEnquete);
		
		JMenuItem mntmAlterarComponente = new JMenuItem("Alterar componente");
		mnEnquete.add(mntmAlterarComponente);
		
		JMenuItem mntmExcluirComponente = new JMenuItem("Excluir componente");
		mnEnquete.add(mntmExcluirComponente);
		
		JMenu mnServicos = new JMenu("Servi\u00E7os");
		menuBar.add(mnServicos);
		
		JMenuItem mntmAdicionarServico = new JMenuItem("Adicionar servi\u00E7o");
		mnServicos.add(mntmAdicionarServico);
		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatorio);
		
		JMenuItem mntmGerar = new JMenuItem("Gerar relat\u00F3rio");
		mnRelatorio.add(mntmGerar);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar relat\u00F3rio");
		mnRelatorio.add(mntmExportar);

	}
}
