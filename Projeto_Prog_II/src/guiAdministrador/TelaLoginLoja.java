package guiAdministrador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepitonRepositorioArray.LojaNaoCadastradaException;
import excepitonRepositorioArray.UsuarioNaoCadastradoException;
import exception.CnpjNaoCadastradoException;
import exception.CpfNaoCadastradoException;
import exception.SenhaIncorretaException;
import guiCliente.TelaCadastrarCliente;
import negocio.Fachada;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TelaLoginLoja extends JFrame {

	private static TelaLoginLoja instance;

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JPasswordField passwordFieldSenha;

	public static TelaLoginLoja getInstance() {
		if (TelaLoginLoja.instance == null) {
			TelaLoginLoja.instance = new TelaLoginLoja();
		}
		return TelaLoginLoja.instance;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			System.err.println(ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLoginLoja frame = new TelaLoginLoja();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLoginLoja() {
		setTitle("Tô com fome - O aplicativo de comida mais próximo de você");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLogin.setBounds(70, 84, 65, 23);
		lblLogin.setForeground(Color.WHITE);
		contentPane.add(lblLogin);

		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(127, 83, 160, 25);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSenha.setBounds(70, 117, 65, 23);
		lblSenha.setForeground(Color.WHITE);
		contentPane.add(lblSenha);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnEntrar.setForeground(new Color(128, 0, 0));
		btnEntrar.setBackground(new Color(255, 255, 255));
		btnEntrar.setBounds(87, 158, 101, 25);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Fachada.getInstance().loginLoja(textFieldLogin.getText(),
							new String(passwordFieldSenha.getPassword()));
				} catch (LojaNaoCadastradaException e) {
					JOptionPane.showMessageDialog(contentPane, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
					// e1.printStackTrace();
				} catch (CnpjNaoCadastradoException e) {
					JOptionPane.showMessageDialog(contentPane, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
					// e1.printStackTrace();
				} catch (SenhaIncorretaException e) {
					JOptionPane.showMessageDialog(contentPane, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
					// e1.printStackTrace();
				}

			}
		});
		contentPane.add(btnEntrar);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCadastrar.setBackground(new Color(255, 255, 255));
		btnCadastrar.setForeground(new Color(128, 0, 0));
		btnCadastrar.setBounds(210, 158, 101, 25);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastrarCliente telaCadastro = new TelaCadastrarCliente();
				telaCadastro.Cadastro.setVisible(true);
				dispose();

			}
		});
		contentPane.add(btnCadastrar);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(127, 116, 160, 25);
		contentPane.add(passwordFieldSenha);

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setBackground(new Color(255, 255, 255));
		btnSair.setForeground(new Color(128, 0, 0));
		btnSair.setBounds(155, 195, 101, 25);
		contentPane.add(btnSair);

		JLabel lblNewLabel = new JLabel("TO COM FOME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Chilanka", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(122, 30, 147, 48);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Entre com suas informacoes acima");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(81, 291, 260, 19);
		contentPane.add(lblNewLabel_1);
	}
}