package objeto;

public class Estatisticas {
	
    private String nome;
    private int gols;
    private int assistencias;
    
    public Estatisticas(String nome, int gols, int assistencias) {
    	this.nome = nome;
        this.gols = gols;
        this.assistencias = assistencias;
    }

	public String getNome() {
		return nome;
	}
    
    public int getGols() {
		return gols;
	}
    
    public int getAssistencias() {
		return assistencias;
	}
    
    public void setNome(String nome) {
		this.nome = nome;
	}
    
    public void setGols(int gols) {
		this.gols = gols;
	}
    
    public void setAssistencias(int assistencias) {
		this.assistencias = assistencias;
	}

    @Override
    public String toString() {
        return nome + " - Gols: " + gols + " - Assistências: " + assistencias;
    }
}
