package Tela;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import javax.swing.JButton;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import cadastro.CadJogador;

public class Tela extends JFrame{
	
    public JPanel painel = new JPanel();
	
    public Tela(){
    	setTitle("Sistema Moderno");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza na tela

        // Painel principal
        painel.setLayout(null);
        painel.setBackground(UIManager.getColor("Panel.background"));
        add(painel);
    }
    
    public void adicionarEspaco(int altura) {
        painel.add(Box.createRigidArea(new Dimension(0, altura)));
    }
    
    public void mostrar() {
        setVisible(true);
    }
    
    public void setTitulo(String titulo, int posicaoX, int posicaoY ) {
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbl.setBounds(posicaoX, posicaoY, 300, 30); // posição manual
        painel.add(lbl);
    }

    public JButton criarBotaoSoIcone(String txtBotao, String txtToolTip, int posicaoX, int posicaoY, ActionListener acao) {
        JButton botao = new JButton(txtBotao);
    	botao.setFocusPainted(false);
    	botao.setBorderPainted(false);
    	botao.setContentAreaFilled(false);
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setToolTipText(txtToolTip);
        botao.setBounds(posicaoX, posicaoY, 50, 30); 
        botao.addActionListener(acao); // usa a ação recebida
        painel.add(botao);
        return botao;
    }
    
    public JButton criarBotao(String txtBotao, int posicaoX, int posicaoY, int largura, int altura, ActionListener acao) {
        JButton botao = new JButton(txtBotao);
        botao.setBounds(posicaoX, posicaoY, largura, altura);
        painel.add(botao);
        botao.addActionListener(acao);
		return null;
    }
    
    public JLabel apresentaInfo(String nomeCampo, Object infoCampo, int posicaoY) {
    	JLabel lblInformacao = new JLabel(nomeCampo + infoCampo);
    	lblInformacao.setBounds(20, posicaoY, 300, 30);
    	painel.add(lblInformacao);
		return lblInformacao;
    }

    
    
 
}
