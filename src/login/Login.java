package login;

import java.awt.Dimension;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import Tela.Tela;
import cadastro.CadJogador;
import conexaoBanco.ConexaoBanco;

public class Login extends Tela {
	
		ConexaoBanco conexao = new ConexaoBanco();

		JTextField txtUsuario = new JTextField();
        JPasswordField txtSenha = new JPasswordField();

	public Login() {
		setTitulo("Bem Vindo!", 130, 10);

		adicionarEspaco(40);
		
		// Usuário
		JLabel lblUsuario = new JLabel("Usuário");
		txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		txtUsuario.setBounds(70, 275, 250, 30); 
		lblUsuario.setBounds(70, 250, 250, 30); 
		painel.add(lblUsuario);
		painel.add(txtUsuario);
		
		// Label "Senha"
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(70, 325, 300, 25);
        painel.add(lblSenha);

        // Campo de senha
        txtSenha.setEchoChar('●');
        txtSenha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtSenha.setBounds(70, 350, 210, 30);
        painel.add(txtSenha);

        // Botão olhinho
        JButton btnOlho = new JButton("👁");
        btnOlho.setFocusable(false);
        btnOlho.setBounds(280, 350, 40, 30);
        btnOlho.setToolTipText("Mostrar/ocultar senha");
        painel.add(btnOlho);
        
        // Alternar visibilidade da senha
        btnOlho.addActionListener(e -> {
            if (txtSenha.getEchoChar() == 0) {
                txtSenha.setEchoChar('●');
                btnOlho.setText("👁");
            } else {
                txtSenha.setEchoChar((char) 0);
                btnOlho.setText("🚫");
            }
        });

		painel.add(Box.createVerticalGlue());

		add(painel);

        // Botão Login
        criarBotao("Entrar", 120, 400, 150, 40, e -> verificaUsuarioBanco());

	}
	
	public void verificaUsuarioBanco() {
		String usuario = txtUsuario.getText();
		String senha = new String(txtSenha.getPassword());

		if (ConexaoBanco.validarLogin(usuario, senha)) {
			new Menu().setVisible(true);

		} else {
			JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf()); // ou FlatDarkLaf, FlatIntelliJLaf...
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> {
			Login login = new Login();
			login.setVisible(true);
		});
	}

}
