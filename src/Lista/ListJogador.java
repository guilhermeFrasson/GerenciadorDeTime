package Lista;

import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Tela.Tela;
import conexaoBanco.ConexaoBanco;
import infoJogador.InfoJogador;
import objeto.Jogador;

public class ListJogador extends Tela {
	
    List<Jogador> jogadores = listarJogadoresDoBanco();
	
	public ListJogador() {

		super();

        criarBotaoSoIcone("←", "Voltar", 10, 10, e -> dispose());
		setTitulo("Lista De Jogador", 130, 10);
		
		listarJogadoresDoBanco();
		
		int y = 50; // posição vertical inicial
		
		for (Jogador jogador : jogadores) {
		    String textoBotao = jogador.getNome() + " - " + jogador.getPosicao();

		    JButton btnJogador = new JButton(textoBotao);
		    btnJogador.setBounds(0, y, 400, 40); // define posição e tamanho
		    btnJogador.setHorizontalAlignment(SwingConstants.LEFT);
		    btnJogador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    btnJogador.addActionListener(e -> {new InfoJogador(jogador).setVisible(true);});
		    painel.add(btnJogador); // adiciona no painel (ou no JFrame)
		    y += 40; // espaço entre botões
		    
		}
	}

	private List<Jogador> listarJogadoresDoBanco() {
	    List<Jogador> lista = new ArrayList<>();

	    String sql = "SELECT * FROM Jogador";

		try (Connection conn = ConexaoBanco.conectar(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			
			
	        while (rs.next()) {
	            Jogador jogador = new Jogador(
	                rs.getInt("ID"),
	                rs.getString("NOME"),
	                rs.getDate("DATANASCIMENTO").toLocalDate(),
	                rs.getString("POSICAO"),
	                rs.getString("PERNADOMINANTE"),
	                rs.getInt("QTDGOLS"),
	                rs.getInt("QTDASSISTENCIA")
	            );
	            lista.add(jogador);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao buscar jogadores do banco.");
	    }

	    return lista;
	}
}
