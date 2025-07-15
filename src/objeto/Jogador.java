package objeto;

import java.time.LocalDate;

public class Jogador {
	
	private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String posicao;
    private String pernaDominante;
    private int gols;
    private int assistencias;

    public Jogador(int id, String nome, LocalDate dataNascimento, String posicao, String pernaDominante, int gols, int assistencias) {
    	this.id = id;
    	this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.posicao = posicao;
        this.pernaDominante = pernaDominante;
        this.gols = gols;
        this.assistencias = assistencias;
    }
    
	public int getId() {
    	return id; 
    }
    
    public String getNome() {
    	return nome; 
    }
    
    public LocalDate getDataNascimento() {
    	return dataNascimento;
    }
    
    public String getPosicao() {
    	return posicao;
    }
    
    public String getPernaDominante() {
    	return pernaDominante;
    }
    
    public int getGols() {
    	return gols;
    }
    
    public int getAssistencias() {
    	return assistencias;
    }
    
	public int getIdade() {
        return LocalDate.now().getYear() - getDataNascimento().getYear();
    }
}
