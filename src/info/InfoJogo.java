package info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Tela.Tela;
import conexaoBanco.ConexaoBanco;
import objeto.Estatisticas;
import objeto.Jogo;

public class InfoJogo extends Tela{
	
    List<Estatisticas> lista = new ArrayList<>();
	
	public InfoJogo(Jogo jogo) {

		criarBotaoSoIcone("←", "Voltar", 10, 10, e -> dispose());
		setTitulo("Informação Do Jogador", 100, 10);

		
		apresentaInfo("Time adversario: ", jogo.getTimeAdversario(), 55);
		apresentaInfo("Resultado: ", jogo.getResultado(), 90);
		apresentaInfo("Data do jogo: ", jogo.getDataJogo(), 125);
		apresentaInfo("Gols Feitos: ", jogo.getGolsFeitos(), 160);
		apresentaInfo("Gols Sofridos: ", jogo.getGolsSofridos(), 195);
		
		setTitulo("Destaques", 20, 230);
		
		
		listarmarcadores(jogo.getId());

		int y = 265;
		for (Estatisticas estat : lista) {
			JLabel lbl = new JLabel(estat.toString()); 
			lbl.setBounds(20, y, 400, 30);
			painel.add(lbl);
			y += 35;
		}



	}
	
	private List<Estatisticas> listarmarcadores ( int idJogo) {
	    String sql = "SELECT JG.ID, JG.TIMEADVERSARIO, JG.DATADOJOGO, JO.NOME, E.QTDGOLS, E.QTDASSISTENCIAS FROM JOGO JG INNER JOIN ESTATISTICAS E ON JG.ID = E.IDJOGO INNER JOIN JOGADOR JO ON E.IDJOGADOR = JO.ID WHERE IDJOGO = " + idJogo + ";";

		try (Connection conn = ConexaoBanco.conectar(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			
			
	        while (rs.next()) {
	            Estatisticas est = new Estatisticas(
	                rs.getString("NOME"),
	                rs.getInt("QTDGOLS"),
	                rs.getInt("QTDASSISTENCIAS")
	            );
	            lista.add(est);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao buscar jogo do banco.");
	    }

	    return lista;
	}
	
}
