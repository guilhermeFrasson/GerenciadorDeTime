package objeto;

import java.time.LocalDate;

public class Jogo {
	
	private int id;
    private String timeAdversario;
    private String resultado;
    private LocalDate dataJogo;
    private int golsFeitos;
    private int golsSofridos;

    
    public Jogo (int id, String timeAdversario, String resultado, LocalDate dataJogo, int golsFeitos, int golsSofridos) {
    	this.id = id;
    	this.timeAdversario = timeAdversario;
        this.resultado = resultado;
        this.dataJogo = dataJogo;
        this.golsFeitos = golsFeitos;
        this.golsSofridos = golsSofridos;
    }
    
	public int getId() {
    	return id; 
    }
    
    public String getTimeAdversario() {
    	return timeAdversario; 
    }
    
    public String getResultado() {
    	return resultado; 
    }
    
    public LocalDate getDataJogo() {
    	return dataJogo;
    }
    
    public int getGolsSofridos() {
    	return golsSofridos;
    }
    
    public int getGolsFeitos() {
    	return golsFeitos;
    }
}
