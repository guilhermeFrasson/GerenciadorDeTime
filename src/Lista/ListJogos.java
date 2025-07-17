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
import info.InfoJogo;
import objeto.Jogo;

public class ListJogos extends Tela{
    List<Jogo> jogos = listarJogosDoBanco();
	
	public ListJogos() {

		super();

        criarBotaoSoIcone("←", "Voltar", 10, 10, e -> dispose());
		setTitulo("Lista De Jogador", 130, 10);
		
		listarJogosDoBanco();
		
		int y = 50; // posição vertical inicial
		
		for (Jogo jogo : jogos) {
		    String textoBotao = jogo.getTimeAdversario() + " - " + jogo.getResultado();

		    JButton btnJogo = new JButton(textoBotao);
		    btnJogo.setBounds(0, y, 400, 40);
		    btnJogo.setHorizontalAlignment(SwingConstants.LEFT);
		    btnJogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    btnJogo.addActionListener(e -> {new InfoJogo(jogo).setVisible(true);});
		    painel.add(btnJogo); 
		    y += 40; 
		    
		}
	}

	private List<Jogo> listarJogosDoBanco() {
	    List<Jogo> lista = new ArrayList<>();

	    String sql = "SELECT * FROM Jogo";

		try (Connection conn = ConexaoBanco.conectar(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			
			
	        while (rs.next()) {
	            Jogo jogo = new Jogo(
	                rs.getInt("ID"),
	                rs.getString("TIMEADVERSARIO"),
	                rs.getString("RESULTADO"),
	                rs.getDate("DATADOJOGO").toLocalDate(),
	                rs.getInt("QTDGOLSFEITOS"),
	                rs.getInt("QTDGOLSSOFRIDOS")
	            );
	            lista.add(jogo);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao buscar jogo do banco.");
	    }

	    return lista;
	}
		
}
