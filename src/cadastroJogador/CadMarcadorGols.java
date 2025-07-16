package cadastroJogador;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Tela.Tela;
import conexaoBanco.ConexaoBanco;
import objeto.Estatisticas;

public class CadMarcadorGols extends Tela {

    private JComboBox<String> cdJogador;
    private JTextField txtGols;
    private JTextField txtAssistencias;

    // Agora usamos objetos reais, não arrays genéricos
    private static List<Estatisticas> listaEstatisticas = new ArrayList<>();
    private static int contadorId = 1; // Para gerar IDs únicos

    public CadMarcadorGols() {
        criarBotaoSoIcone("←", "Voltar", 10, 10, e -> dispose());
        criarBotaoSoIcone("✅", "Salvar", 330, 10, e -> salvarJogadorList());
        setTitulo("Estatísticas", 100, 10);

        // ComboBox Jogador
        cdJogador = new JComboBox<>();
        List<String> listaJogador = ConexaoBanco.cdComboBox("NOME", "JOGADOR");
        for (String nome : listaJogador) {
            cdJogador.addItem(nome);
        }
        painel.add(new JLabel("Nome Jogador:")).setBounds(25, 200, 250, 30);
        cdJogador.setBounds(25, 225, 340, 30);
        painel.add(cdJogador);

        // Campo Gols Marcados
        painel.add(new JLabel("Gols:")).setBounds(25, 250, 250, 30);
        txtGols = new JTextField();
        txtGols.setBounds(25, 275, 340, 30);
        painel.add(txtGols);

        // Campo Assistências
        painel.add(new JLabel("Assistências:")).setBounds(25, 300, 250, 30);
        txtAssistencias = new JTextField();
        txtAssistencias.setBounds(25, 325, 340, 30);
        painel.add(txtAssistencias);
    }

    public void salvarJogadorList() {
        String nome = (String) cdJogador.getSelectedItem();
        int gols = 0, assistencias = 0;

        try {
            gols = Integer.parseInt(txtGols.getText().trim());
        } catch (NumberFormatException e) {
            gols = 0;
        }
        
        try {
        	assistencias = Integer.parseInt(txtAssistencias.getText().trim());
        } catch (NumberFormatException e) {
        	assistencias = 0;
        }
        Estatisticas estat = new Estatisticas(nome, gols, assistencias);
        listaEstatisticas.add(estat);

        dispose(); 
        
    }

    public static List<Estatisticas> getListaEstatisticas() {
        return listaEstatisticas;
    }

    public int contaTamanhoList() {
        return listaEstatisticas.size();
    }
}
