package cadastro;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Lista.ListMarcadorGols;
import Tela.Tela;
import conexaoBanco.ConexaoBanco;
import objeto.Estatisticas;

public class CadJogo extends Tela{
	
    private JTextField txtTimeAdiversario;
    private JComboBox<String> cdResultado;
    private JTextField txtDatajogo;
    private JTextField txtGolsSofridos;
    private JTextField txtGolsFeitos;
	
	public CadJogo() {
		super();

		criarBotaoSoIcone("←", "Voltar", 10, 10, e -> dispose());
		criarBotaoSoIcone("✅", "Salvar", 330, 10, e -> salvarDadosJogo());
		setTitulo("Cadastrar Jogo", 100, 10);
		
		
		// Campo Time Adiversario
        painel.add(new JLabel("Time Adiversario:")).setBounds(25, 100, 250, 30);
        txtTimeAdiversario = new JTextField();
        txtTimeAdiversario.setBounds(25, 125, 340, 30); 
        painel.add(txtTimeAdiversario);
        
        // ComboBox Resultado
        painel.add(new JLabel("Resultado:")).setBounds(25, 150, 250, 30);
        cdResultado = new JComboBox<>(new String[] {
            "Vitoria", "Empate", "Derrota"
        });
        cdResultado.setBounds(25, 175, 340, 30); 
        painel.add(cdResultado);

        // Campo Data do jogo
        painel.add(new JLabel("Data do jogo (dd/MM/yyyy):")).setBounds(25, 200, 250, 30);
        txtDatajogo = new JTextField();
        txtDatajogo.setBounds(25, 225, 340, 30); 
        painel.add(txtDatajogo);
        
        // Campo Gols Marcados
        painel.add(new JLabel("Gols Marcadso:")).setBounds(25, 250, 250, 30);
        txtGolsFeitos = new JTextField();
        txtGolsFeitos.setBounds(25, 275, 340, 30); 
        painel.add(txtGolsFeitos);
        
        // Campo Gols Sofridos
        painel.add(new JLabel("Gols Sofridos:")).setBounds(25, 300, 250, 30);
        txtGolsSofridos = new JTextField();
        txtGolsSofridos.setBounds(25, 325, 340, 30); 
        painel.add(txtGolsSofridos);


        criarBotao("Cadastra Marcadores", 25, 500, 340, 50, e -> {new CadMarcadorGols().setVisible(true);});
        criarBotao("Listar marcador de gol", 25, 570, 340, 50, e -> {new ListMarcadorGols().setVisible(true);});
    }

    public void salvarDadosJogo() {
    	List<Estatisticas> lista = CadMarcadorGols.getListaEstatisticas();
    	if (lista.size() > 0) {
			
		}
    	String timeAdiversario = txtTimeAdiversario.getText().trim();
    	
        String resultado = (String) cdResultado.getSelectedItem();

    	String dataTexto = txtDatajogo.getText().trim();
    	LocalDate dataJogo;
    	int qtdGols = 0;
    	int qtdAssistencias = 0;
    	try {
    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	    dataJogo = LocalDate.parse(dataTexto, formatter);
    	} catch (Exception e) {
    	    JOptionPane.showMessageDialog(this, "Data do jogo inválida!");
    	    return;
        }
        
    	int golsFeitos, golsSofridos;
    	

    	try {
    		golsFeitos = Integer.parseInt(txtGolsFeitos.getText().trim());
    	} catch (NumberFormatException e) {
    	    JOptionPane.showMessageDialog(this, "Digite um número válido para Gols Marcados.");
    	    return;
    	}
    	
    	try {
    		golsSofridos = Integer.parseInt(txtGolsSofridos.getText().trim());
    	} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(this, "Digite um número válido para Gols Sofridos.");
    		return;
    	}
		int totalGols = 0;
		for (Estatisticas e : lista) {
		    totalGols += e.getGols(); // supondo que tenha o método getGols()
		}
		if (golsFeitos == totalGols) {
			verificaResultado(timeAdiversario, resultado, dataJogo, golsFeitos, golsSofridos);
		}else {
			JOptionPane.showMessageDialog(this, "o numero de gols margados no jogo, tem que ser igual a somatória dos gols dos jogadores.");
		}
    	
	}
    
    
    public void inserirJogoBd(String timeAdiversario, String resultado, LocalDate dataJogo, int golsFeitos, int golsSofridos) {
		boolean sucessoJogo = ConexaoBanco.inserirJogo(timeAdiversario, resultado, dataJogo, golsFeitos, golsSofridos);
    	if (sucessoJogo) {
            JOptionPane.showMessageDialog(this, "Jogo cadastrado com sucesso!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar jogo.");
        }
    }

    public void inserirEstatisticasJogador() {
    	
    	ConexaoBanco conexaoBanco = new ConexaoBanco();

    	int qtdGols = 0;
    	int qtdAssistencias = 0;
    	int idJogador = 0;

		List<Estatisticas> lista = CadMarcadorGols.getListaEstatisticas();
		


		for (Estatisticas estat : lista) {
			qtdGols = estat.getGols();
			qtdAssistencias = estat.getAssistencias();
			idJogador = conexaoBanco.buscaIdJogador(estat.getNome());
			conexaoBanco.inserirEstatisticasJogador(idJogador, qtdGols, qtdAssistencias);
			conexaoBanco.alterarDadosJogador(idJogador, qtdGols, qtdAssistencias);
		}
		lista.clear();
    }
    
    
    
	public void verificaResultado(String timeAdiversario, String resultado, LocalDate dataJogo, int golsFeitos,
			int golsSofridos) {

		if (rootPaneCheckingEnabled) {

		}

		if (resultado.equals("Vitoria")) {
			if (golsFeitos > golsSofridos) {
				inserirJogoBd(timeAdiversario, resultado, dataJogo, golsFeitos, golsSofridos);
				inserirEstatisticasJogador();
			} else {
				JOptionPane.showMessageDialog(this,
						"O numero de gols marcados tem que ser maior que o numero de gols sofridos");
			}
		}

		if (resultado.equals("Empate")) {
			if (golsFeitos == golsSofridos) {
				inserirJogoBd(timeAdiversario, resultado, dataJogo, golsFeitos, golsSofridos);
				inserirEstatisticasJogador();
			} else {
				JOptionPane.showMessageDialog(this,
						"O numero de gols marcados tem que ser igual ao numero de gols sofridos");
			}
		}

		if (resultado.equals("Derrota")) {
			if (golsFeitos < golsSofridos) {
				inserirJogoBd(timeAdiversario, resultado, dataJogo, golsFeitos, golsSofridos);
				inserirEstatisticasJogador();
			} else {
				JOptionPane.showMessageDialog(this,
						"O numero de gols sofridos tem que ser maior que o numero de gols marcados");
			}
		}
	}
}
