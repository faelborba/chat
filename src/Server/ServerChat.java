package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerChat {

	public static void main(String[] args) {
		int conexoes = 100, qtd = 0;
		Logs logs = new Logs();
		ControleDeClientes controle = new ControleDeClientes(logs);
		int porta = 8976;
		boolean fechar = false;

		try {
			System.out.println("Iniciando server...");
			ServerSocket server = new ServerSocket(porta);
			while (true) {
				System.out.println("Esperando conexão... Porta " + porta);
				Socket soc = server.accept();
				System.out.println("Conectado na porta" + porta + "!\nTratando requisição...");
				synchronized (controle) {
					Cliente clientChat = new Cliente(soc, controle);
					clientChat.setPosicao(qtd);

					if (controle.getClientes().size() < conexoes) {
						controle.addCliente(clientChat);
						clientChat.start();
					} else {
						clientChat.getSaida().println("ERRO Servidor Lotado!");
						clientChat.getSaida().close();
						clientChat.getEntrada().close();
					}
				}

				if (fechar) {
					break;
				}
			}

			server.close();

		} catch (UnknownHostException ex) {
			System.out.println("Host desconhecido");
		} catch (IOException ex) {
			System.out.println("Erro na conexao: " + ex.getMessage());
		}
	}
}