package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientChat {
	private static String ip = null;
	private static int porta;

	public static void main(String[] args) {
		System.out.println("Olá, seja bem vindo ao ClientChat Java POOII - v1.0");
		System.out.println("Para conectar-se digite o ip do servidor de chat.");
		ip = new Scanner(System.in).nextLine();
		System.out.println("Agora a porta.");
		porta = Integer.parseInt(new Scanner(System.in).nextLine());
		try {
			Socket s = new Socket(ip, porta);// conecta no server
			RecebeServer recebeServer = new RecebeServer(s);
			EnviaServer enviaServer = new EnviaServer(s);

			System.out.println("Para entrar no chat digite ENTRAR e seu nickname.");
			System.out.println("Para enviar mensagens digite MSG seguido da mensagem.");
			System.out.println("Para sair digite SAIR.");

			enviaServer.start();
			recebeServer.start();
		} catch (UnknownHostException ex) {
			System.out.println("Host desconhecido");
		} catch (IOException  ex) {
			System.out.println("Erro de conexão: " + ex.getMessage());
		} 

	}

}
