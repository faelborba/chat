package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EnviaServer extends Thread {
	Socket s = null;// conecta no server
	String protocolo[] = null;
	String txt = null;

	EnviaServer(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {

			try {
				PrintWriter saida = new PrintWriter(s.getOutputStream());
				this.txt = new Scanner(System.in).nextLine();
				verificaProtocolo(this.txt);
				saida.println(this.txt);// envia protocolo ao server
				saida.flush();
				if (protocolo[0].equals("SAIR")) {
					saida.close();
					this.s.close();
					break;
				}
			} catch (IOException e) {
				break;
				// TODO Auto-generated catch block
			}

		}
	}

	public void verificaProtocolo(String txt) {
		this.protocolo = new String[2];

		if (txt.indexOf(' ') != -1) {
			this.protocolo[0] = txt.substring(0, txt.indexOf(' '));
			this.protocolo[1] = txt.substring((txt.indexOf(' ') + 1), txt.length());
		} else {
			this.protocolo[0] = txt;
		}
	}
}
