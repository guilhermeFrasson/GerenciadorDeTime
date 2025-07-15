package Lista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Tela.Tela;
import conexaoBanco.ConexaoBanco;
import objeto.Jogador;

public class ListJogos extends Tela{
	public ListJogos() {

		super();

        criarBotaoSoIcone("←", "Voltar", 10, 10, e -> dispose());
		setTitulo("Lista De Jogos", 130, 10);
		
		
	}
	
	private List<Jogador> listarJogosDoBanco() {
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
