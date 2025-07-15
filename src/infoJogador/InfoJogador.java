package infoJogador;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JLabel;

import Tela.Tela;
import objeto.Jogador;

public class InfoJogador extends Tela {

	public InfoJogador(Jogador jogador) {

		criarBotaoSoIcone("←", "Voltar", 10, 10, e -> dispose());
		setTitulo("Informação Do Jogador", 100, 10);

		JLabel lblNome = new JLabel("Nome: " + jogador.getNome());
		lblNome.setBounds(20, 60, 300, 30);
		painel.add(lblNome);

		JLabel lblIdade = new JLabel("Idade: " + jogador.getIdade());
		lblIdade.setBounds(20, 100, 300, 30);
		painel.add(lblIdade);

		JLabel lblPosicao = new JLabel("Posição: " + jogador.getPosicao());
		lblPosicao.setBounds(20, 140, 300, 30);
		painel.add(lblPosicao);

		JLabel lblPerna = new JLabel("Perna dominante: " + jogador.getPernaDominante());
		lblPerna.setBounds(20, 180, 300, 30);
		painel.add(lblPerna);

		JLabel lblGols = new JLabel("Gols: " + jogador.getGols());
		lblGols.setBounds(20, 220, 300, 30);
		painel.add(lblGols);

		JLabel lblAssist = new JLabel("Assistências: " + jogador.getAssistencias());
		lblAssist.setBounds(20, 260, 300, 30);
		painel.add(lblAssist);

	}
}
