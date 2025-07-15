package Lista;

import java.util.List;

import javax.swing.JLabel;

import Tela.Tela;
import cadastroJogador.CadMarcadorGols;
import objeto.Estatisticas;

public class ListMarcadorGols extends Tela {

	public ListMarcadorGols() {
		super();

		criarBotaoSoIcone("←", "Voltar", 10, 10, e -> dispose());
		setTitulo("Estatísticas Jogador", 100, 10);

		List<Estatisticas> lista = CadMarcadorGols.getListaEstatisticas();

		int y = 60;
		for (Estatisticas estat : lista) {
			JLabel lbl = new JLabel(estat.toString()); // usa o toString da classe Estatisticas
			lbl.setBounds(20, y, 400, 30);
			painel.add(lbl);
			y += 35;
		}
	}
}
