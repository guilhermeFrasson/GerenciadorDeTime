package cadastroJogador;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Tela.Tela;
import conexaoBanco.ConexaoBanco;

public class CadJogador  extends Tela{

    private JTextField txtNome;
    private JTextField txtDataNasc;
    private JComboBox<String> cdPosicao;
    private JComboBox<String> cbPerna;


    public CadJogador() {
    	
    	super();
    	
        criarBotaoSoIcone("←", "Voltar", 10, 10, e -> dispose());
        criarBotaoSoIcone("✅", "Salvar", 330, 10, e -> salvarJogador());
    	setTitulo("Cadastrar jogador", 100, 10);

        // Campo Nome
        painel.add(new JLabel("Nome:")).setBounds(25, 100, 250, 30);
        txtNome = new JTextField();
        txtNome.setBounds(25, 125, 340, 30); 
        painel.add(txtNome);

        // Campo Data de nascimento
        painel.add(new JLabel("Data de Nascimento (dd/MM/yyyy):")).setBounds(25, 150, 250, 30);
        txtDataNasc = new JTextField();
        txtDataNasc.setBounds(25, 175, 340, 30); 
        painel.add(txtDataNasc);

        // ComboBox Posição
        cdPosicao = new JComboBox<>();
        List<String> listaPosicao = ConexaoBanco.cdComboBox("DSPOSICAO","POSICAO");
        for (String pos : listaPosicao) {
            cdPosicao.addItem(pos);
        }
        painel.add(new JLabel("Posição:")).setBounds(25, 200, 250, 30);
        cdPosicao.setBounds(25, 225, 340, 30); 
        painel.add(cdPosicao);

        // ComboBox Perna dominante
        painel.add(new JLabel("Perna Dominante:")).setBounds(25, 250, 250, 30);
        cbPerna = new JComboBox<>(new String[] {
            "Direita", "Esquerda", "Ambidestro"
        });
        cbPerna.setBounds(25, 275, 340, 30); 
        painel.add(cbPerna);
    }

    public void salvarJogador() {
    	String nome = txtNome.getText().trim();
    	String dataTexto = txtDataNasc.getText().trim();

    	LocalDate dataNasc;
    	try {
    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	    dataNasc = LocalDate.parse(dataTexto, formatter);
    	} catch (Exception e) {
    	    JOptionPane.showMessageDialog(this, "Data de nascimento inválida!");
    	    return;
        }
        
        String posicao = (String) cdPosicao.getSelectedItem();
        String perna = (String) cbPerna.getSelectedItem();
        
        boolean sucesso = ConexaoBanco.inserirJogador(nome, dataNasc, posicao, perna);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Jogador cadastrado com sucesso!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar jogador.");
        }
    }

}
