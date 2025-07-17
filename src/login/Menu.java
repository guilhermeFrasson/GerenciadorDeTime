package login;

import Lista.ListJogador;
import Lista.ListJogos;
import Tela.Tela;
import cadastro.CadJogador;
import cadastro.CadJogo;

public class Menu extends Tela {

	public Menu() {
        setTitulo("Menu Principal", 130, 10);
        criarBotaoSoIcone("❌", "Sair", 330, 10, e -> System.exit(0));

        criarBotao("Listar Jogos", 40, 100, 320, 70, e -> {new ListJogos().setVisible(true);});
        criarBotao("Cadastrar Jogo", 40, 175, 320, 70, e -> {new CadJogo().setVisible(true);});
        criarBotao("Listar Jogador", 40, 250, 320, 70, e -> {new ListJogador().setVisible(true);});
        criarBotao("Cadastrar Jogador", 40, 325, 320, 70, e -> {new CadJogador().setVisible(true);});
        criarBotao("Estatisticas", 40, 400, 320, 70, e -> {new CadJogador().setVisible(true);});
        criarBotao("Mensalidades", 40, 475, 320, 70, e -> {new CadJogador().setVisible(true);});
	
        // Adiciona o painel à janela
        add(painel);
    }

}
