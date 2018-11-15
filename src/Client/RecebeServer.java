package Client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class RecebeServer extends Thread {
	Socket s = null;// conecta no server

	RecebeServer(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			Scanner entrada = null;
			try {
				entrada = new Scanner(new InputStreamReader(s.getInputStream()));
				while (entrada.hasNextLine()) {
					System.out.println(entrada.nextLine());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//entrada.close();
				break;
			}

			// System.out.println("SAIUD WHILE");
		}
	}
}
