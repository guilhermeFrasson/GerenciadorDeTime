package conexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import objeto.Jogador;

public class ConexaoBanco {
	
	   public static Connection conectar() {
	        try {
	        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        	
	            String url = "jdbc:sqlserver://DESKTOP-N0PEAKH:1433;databaseName=teste;encrypt=true;trustServerCertificate=true";
	            String usuario = "sa";
	            String senha = "db97!#!!";

	            return DriverManager.getConnection(url, usuario, senha);

	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	   
		public static boolean validarLogin(String usuario, String senha) {

			String sql = "SELECT nome FROM Usuarios WHERE usuario = ? AND senha = ?";

			try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setString(1, usuario);
				stmt.setString(2, senha);

				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return true;
				} else {
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
	    public static boolean inserirJogador(String nome, LocalDate dataNascimento, String posicao, String perna) {
	    	
	        Connection conn = conectar();
	        if (conn == null) return false;

	        String sql = "INSERT INTO Jogador (nome, datanascimento, posicao, pernadominante) VALUES (?, ?, ?, ?)";

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, nome);
	            stmt.setString(2, dataNascimento.toString());
	            stmt.setString(3, posicao);
	            stmt.setString(4, perna);
	            stmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public static boolean inserirJogo(String timeAdiversario, String resultado, LocalDate dataJogo, int golsFeitos, int golsSofridos) {
	    	
	        Connection conn = conectar();
	        if (conn == null) return false;

	        String sql = "INSERT INTO Jogo (TIMEADVERSARIO, RESULTADO, DATADOJOGO, QTDGOLSFEITOS, QTDGOLSSOFRIDOS) VALUES (?, ?, ?, ?, ?)";
	        
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, timeAdiversario);
	            stmt.setString(2, resultado);
	            stmt.setString(3, dataJogo.toString());
	            stmt.setInt(4, golsFeitos);
	            stmt.setInt(5, golsSofridos);
	            stmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public static boolean inserirEstatisticasJogador( int idJogador, int qtdGols, int qtdAssistencias) {
	    	
	    	Connection conn = conectar();
	    	if (conn == null) return false;
	    	
	    	String sql = "INSERT INTO ESTATISTICAS (IDJOGO, IDJOGADOR, QTDGOLS, QTDASSISTENCIAS) VALUES (?, ?, ?, ?)";
	    	
	    	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	    		stmt.setInt(1, buscaUltimoJogo());
	    		stmt.setInt(2, idJogador);
	    		stmt.setInt(3, qtdGols);
	    		stmt.setInt(4, qtdAssistencias);
	    		stmt.executeUpdate();
	    		return true;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    
		public static List<String> cdComboBox(String coluna, String tabela) {
			List<String> combo = new ArrayList<>();
			String sql = "SELECT " + coluna + " FROM " + tabela + " ORDER BY ID";
			
			try (Connection conn = conectar(); 
					PreparedStatement stmt = conn.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					combo.add(rs.getString(coluna));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return combo;
		}

		public static int buscaUltimoJogo() {
			int idJogo= 0;
			String sql = "SELECT TOP 1 * FROM JOGO ORDER BY id DESC";
			
			try (Connection conn = ConexaoBanco.conectar(); 
					PreparedStatement stmt = conn.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery()) {
				
				
		        while (rs.next()) {
		        	idJogo = rs.getInt("ID");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Erro ao buscar o ultimo jogo no banco.");
		    }
			return idJogo;
		}
		
		public static int buscaIdJogador(String nomeJogador) {
			int idJogador= 0;
			String sql = "SELECT * FROM JOGADOR WHERE NOME = '" + nomeJogador + "'";
			
			try (Connection conn = ConexaoBanco.conectar(); 
					PreparedStatement stmt = conn.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery()) {
				
				
		        while (rs.next()) {
		        	idJogador = rs.getInt("ID");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Erro ao buscar o id do jogador no banco.");
		    }
			return idJogador;
		}
		
		
		

	    
}
