package info;

import Tela.Tela;
import objeto.Jogador;

public class InfoJogador extends Tela {

	public InfoJogador(Jogador jogador) {

		criarBotaoSoIcone("←", "Voltar", 10, 10, e -> dispose());
		setTitulo("Informação Do Jogador", 100, 10);
		
		apresentaInfo("Nome: ", jogador.getNome(), 55);
		apresentaInfo("Idade: ", jogador.getIdade(), 90);
		apresentaInfo("Posição: ", jogador.getPosicao(), 125);
		apresentaInfo("Perna dominante: ", jogador.getPernaDominante(), 160);
		apresentaInfo("Gols: ", jogador.getGols(), 195);
		apresentaInfo("Assistências: ", jogador.getAssistencias(), 230);

	}
}
