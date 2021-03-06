package guiAdministrador;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import excepitonRepositorioArray.AdministradorJaCadastradoException;
import excepitonRepositorioArray.AdministradorVazioException;
import excepitonRepositorioArray.UsuarioNaoCadastradoException;
import excepitonRepositorioArray.UsuarioVazioException;
import negocio.Fachada;
import negocioClassesBasicas.Administrador;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaCadastrarAdministrador extends JFrame {

	private static TelaCadastrarAdministrador instance;

	private JPanel contentPane;
	private JTextField textNomeDeLogin;
	private JPasswordField pwdSenha;
	private JTextField textId;
	private JButton btnVoltar;
	private JButton btnCadastrar;
	private JButton btnLimpar;

	public static TelaCadastrarAdministrador getInstance() {
		if (TelaCadastrarAdministrador.instance == null) {
			TelaCadastrarAdministrador.instance = new TelaCadastrarAdministrador();
		}
		return TelaCadastrarAdministrador.instance;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastrarAdministrador frame = new TelaCadastrarAdministrador();
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
	public TelaCadastrarAdministrador() {
		setTitle("Tô com fome - O aplicativo de comida mais próximo de você");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textNomeDeLogin = new JTextField();
		textNomeDeLogin.setBounds(204, 92, 160, 25);
		getContentPane().add(textNomeDeLogin);
		textNomeDeLogin.setColumns(10);

		JLabel lblNomeLogin = new JLabel("Nome de Login:");
		lblNomeLogin.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNomeLogin.setForeground(new Color(255, 255, 255));
		lblNomeLogin.setBounds(78, 97, 118, 15);
		getContentPane().add(lblNomeLogin);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 13));
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(146, 134, 50, 15);
		getContentPane().add(lblSenha);

		pwdSenha = new JPasswordField();
		pwdSenha.setBounds(204, 129, 160, 25);
		getContentPane().add(pwdSenha);

		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Dialog", Font.BOLD, 13));
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setBounds(177, 173, 25, 15);
		getContentPane().add(lblId);

		textId = new JTextField();
		textId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!(Character.isDigit(c))) {
					e.consume();
				}

			}
		});
		textId.setBounds(204, 168, 160, 25);
		getContentPane().add(textId);
		textId.setColumns(10);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnVoltar.setBackground(new Color(255, 255, 255));
		btnVoltar.setForeground(new Color(128, 0, 0));
		btnVoltar.setBounds(250, 272, 114, 25);
		contentPane.add(btnVoltar);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String login = textNomeDeLogin.getText();
					String senha = new String(pwdSenha.getPassword());
					int id = Integer.parseInt(textId.getText());

					Fachada.getInstance().inserirAdmnistrador(new Administrador(login, senha, id));
					JOptionPane.showMessageDialog(contentPane, "Administrador cadastrado com sucesso!!", "",
							JOptionPane.INFORMATION_MESSAGE);
					TelaLoginAdministrador tela = new TelaLoginAdministrador();
					tela.setVisible(true);
					dispose();

				} catch (AdministradorVazioException e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "", JOptionPane.ERROR_MESSAGE);
				} catch (AdministradorJaCadastradoException e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "Insira um numero maior que zero", "erro",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnCadastrar.setForeground(new Color(128, 0, 0));
		btnCadastrar.setBackground(Color.WHITE);
		btnCadastrar.setBounds(250, 235, 114, 25);
		contentPane.add(btnCadastrar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textId.setText("");
				textNomeDeLogin.setText("");
				pwdSenha.setText("");

			}
		});
		btnLimpar.setForeground(new Color(128, 0, 0));
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(124, 272, 114, 25);
		contentPane.add(btnLimpar);

	}
}
