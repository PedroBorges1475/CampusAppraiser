package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Avaliacao;
import model.Resultados;

public class AvaliadorView extends JFrame {

	private JPanel contentPane;
	private JTextField txtOpiniao;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboBoxServico,comboBoxTipoServico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvaliadorView frame = new AvaliadorView();
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
	public AvaliadorView() {
		setTitle("Avaliar servi\u00E7o");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnLimpar.setBounds(236, 227, 89, 23);
		contentPane.add(btnLimpar);
		
		comboBoxServico = new JComboBox();
		comboBoxServico.setBounds(124, 11, 300, 20);
		contentPane.add(comboBoxServico);
		
		JRadioButton rdbtnNota0 = new JRadioButton("0");
		buttonGroup.add(rdbtnNota0);
		rdbtnNota0.setBounds(10, 102, 46, 23);
		contentPane.add(rdbtnNota0);
		
		JRadioButton rdbtnNota1 = new JRadioButton("1");
		buttonGroup.add(rdbtnNota1);
		rdbtnNota1.setBounds(84, 102, 46, 23);
		contentPane.add(rdbtnNota1);
		
		JRadioButton rdbtnNota2 = new JRadioButton("2");
		buttonGroup.add(rdbtnNota2);
		rdbtnNota2.setBounds(159, 102, 40, 23);
		contentPane.add(rdbtnNota2);
		
		JRadioButton rdbtnNota3 = new JRadioButton("3");
		buttonGroup.add(rdbtnNota3);
		rdbtnNota3.setBounds(236, 102, 46, 23);
		contentPane.add(rdbtnNota3);
		
		JRadioButton rdbtnNota4 = new JRadioButton("4");
		buttonGroup.add(rdbtnNota4);
		rdbtnNota4.setBounds(310, 102, 46, 23);
		contentPane.add(rdbtnNota4);
		
		JRadioButton rdbtnNota5 = new JRadioButton("5");
		buttonGroup.add(rdbtnNota5);
		rdbtnNota5.setBounds(393, 102, 41, 23);
		contentPane.add(rdbtnNota5);
		
		comboBoxTipoServico = new JComboBox();
		comboBoxTipoServico.setBounds(124, 42, 300, 20);
		contentPane.add(comboBoxTipoServico);
		
		JLabel lblServico = new JLabel("Servi\u00E7o:");
		lblServico.setBounds(10, 14, 104, 14);
		contentPane.add(lblServico);
		
		JLabel lblTipoDeServico = new JLabel("Tipo de servi\u00E7o:");
		lblTipoDeServico.setBounds(10, 45, 94, 14);
		contentPane.add(lblTipoDeServico);
		
		txtOpiniao = new JTextField();
		txtOpiniao.setText("Digite aqui sua opini\u00E3o...");
		txtOpiniao.setHorizontalAlignment(SwingConstants.LEFT);
		txtOpiniao.setBounds(10, 132, 414, 84);
		contentPane.add(txtOpiniao);
		txtOpiniao.setColumns(10);
		
		
		JLabel lblNota = new JLabel("Nota:");
		lblNota.setBounds(10, 81, 46, 14);
		contentPane.add(lblNota);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String servico = comboBoxServico.getSelectedItem().toString();
				String tipoServico = comboBoxTipoServico.getSelectedItem().toString();
				String nota = buttonGroup.getSelection().toString();
				String opiniao = txtOpiniao.getText();
				Avaliacao avaliacao = new Avaliacao(servico,tipoServico,nota,opiniao);
				Resultados.adicionarVoto(avaliacao);
				JOptionPane.showMessageDialog(null, "Obrigado por responder, seu feedback é muito importante!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
				clear();
			}
		});
		btnSalvar.setBounds(335, 227, 89, 23);
		contentPane.add(btnSalvar);
	}
	
private void clear() {
		txtOpiniao.setText("");
		buttonGroup.clearSelection();
		comboBoxServico.setSelectedItem(null);
		comboBoxTipoServico.setSelectedItem(null);
		
	}
}
