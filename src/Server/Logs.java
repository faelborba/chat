package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logs {

	private int mostra = 5;
	File arquivo = null;

	public ArrayList<String> mostraTexto() {
		ArrayList<String> texto = new ArrayList<>();
		FileWriter criaArquivo = null;
		Scanner pegaTexto = null;
		arquivo = new File("logs.log");
		if (arquivo.exists()) {
			System.out.println("Abrindo arquivo de palavras...");
			try {
				pegaTexto = new Scanner(arquivo);
			} catch (FileNotFoundException ex) {
				Logger.getLogger(Logs.class.getName()).log(Level.SEVERE, null, ex);
			}
			int i = 0;
			while (pegaTexto.hasNextLine()) {
				texto.add(pegaTexto.nextLine());
				// System.out.println("####"+texto.get(i));
				i++;
			}

			pegaTexto.close();
		} else {
			System.out.println("Opa, Arquivo não existe criando um novo.");
			try {
				criaArquivo = new FileWriter(arquivo);// aqui cria um arquivo conforme o objeto arquivo.
				criaArquivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return texto;
	}

	public void gravaArquivo(String txt) {
		ArrayList<String> texto = new ArrayList<>();
		PrintWriter gravaArquivo = null;
		Scanner pegaTexto = null;
		System.out.println("Gravando arquivo de logs...");
		try {
			if (arquivo.exists()) {
				pegaTexto = new Scanner(arquivo);
				while (pegaTexto.hasNextLine()) {
					texto.add(pegaTexto.nextLine());
				}

				FileWriter arq = new FileWriter(arquivo);
				gravaArquivo = new PrintWriter(arq);
				for (int i = 0; i < texto.size(); i++) {
					gravaArquivo.printf(texto.get(i) + "%n");
				}
				gravaArquivo.printf(txt + "%n");
				pegaTexto.close();
				gravaArquivo.close();
			}
		} catch (IOException ex) {
			Logger.getLogger(Logs.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public int getMostra() {
		return mostra;
	}

	public void setMostra(int mostra) {
		this.mostra = mostra;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

}
