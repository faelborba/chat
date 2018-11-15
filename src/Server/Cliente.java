package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Cliente extends Thread implements Serializable {
	// variáveis do server
	int posicao = 0;
	private ControleDeClientes controle = null;
	private Socket socket = null;
	private Scanner entrada = null;
	private PrintWriter saida = null;
	private String[] protocolo = null;
	private String nick = null, msg = null;
	boolean entrou = false;

	// preparando entrada e saída do server
	public Cliente(Socket socket, ControleDeClientes controle) throws IOException {
		this.socket = socket;
		this.entrada = new Scanner(this.socket.getInputStream());
		this.saida = new PrintWriter(this.socket.getOutputStream());
		this.controle = controle;
	}

	public void run() {
		while (true) {
			try {
				ajustaProtocolo(this.entrada.nextLine());// pega as palavras do protocolo.;
				synchronized (this.controle) {
					
					if (this.protocolo[0].equals("ENTRAR")) {
						System.out.println("Comando Entrar");
						if (this.entrou) {
							System.out.println("ERRO já entrou");
							this.saida.println("ERRO voce já entrou");
							this.saida.flush();
							continue;
						}
						if (controle.verificaNick(this.protocolo[1])) {
							System.out.println("ERRO nickname já existe");
							this.saida.println("ERRO nickname já existe.");
							this.saida.flush();
							continue;
						}
						if (this.protocolo[1] == null) {
							System.out.println("ERRO Não inforou um nickname.");
							this.saida.println("ERRO Não inforou um nickname.");
							this.saida.flush();
						} else {
							ArrayList<String> log = this.controle.getLogs().mostraTexto();
							if (log.size() >= 20) {// verificando se o log > que 20 linhas
								for (int i = log.size() - 20; i < log.size(); i++) {
									this.saida.println(log.get(i));
								}
							} else {
								for (int i = 0; i < log.size(); i++) {
									this.saida.println(log.get(i));
								}
							}
							this.nick = this.protocolo[1];
							System.out.println(this.nick + " Entrou ");
							this.controle.getLogs().gravaArquivo(this.nick + " Entrou ");// grava arquivo de logs
							this.controle.notificaEntrada(this.nick, " Entrou no grupo.");

							this.entrou = true;
							this.saida.flush();
						}
					} else if (this.protocolo[0].equals("MSG") && this.nick != null) {
						if (this.protocolo[1] != null) {
							this.msg = this.protocolo[0] + " " + this.nick + " " + this.protocolo[1];
							this.controle.getLogs().gravaArquivo(this.msg);// grava arquivo de logs
							this.controle.enviaParaTodos(this.msg);

							System.out.println(this.msg);

						}

					} else if (this.protocolo[0].equals("SAIR")) {
						if (this.nick != null) {
							System.out.println("MSG " + this.nick + " Saiu ");
							this.controle.getLogs().gravaArquivo("MSG "+this.nick + " Saiu do grupo.");// grava arquivo de logs
							posicao = this.controle.notificaSaida(this.nick, " Saiu do grupo.");
						} else {
							posicao = this.controle.saidaNull(this);
							// this.controle.getClientes().equals(this);
						}
						this.saida.flush();
						this.controle.getClientes().remove(posicao);
						this.entrada.close();
						this.saida.close();
						this.socket.close();
						break;
					} else {
						System.out.println("ERRO Protocolo Incorreto.");
						this.saida.println("ERRO Protocolo Incorreto.");
						this.saida.flush();
					}
				}

			} catch (IllegalStateException | NoSuchElementException | IOException  e) {
				if (this.nick != null) {
					System.out.println("MSG " + this.nick + " Saiu ");
					this.controle.getLogs().gravaArquivo(this.nick + " Saiu do grupo.");// grava arquivo de logs
					posicao = this.controle.notificaSaida(this.nick, " Saiu do grupo.");
				} else {
					posicao = this.controle.saidaNull(this);
					// this.controle.getClientes().equals(this);
				}
				this.saida.flush();
				this.controle.getClientes().remove(posicao);
				this.entrada.close();
				this.saida.close();
				try {
					this.socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// e.printStackTrace();
				break;
			}
		}
	}

	public void ajustaProtocolo(String txt) {
		this.protocolo = new String[2];

		if (txt.indexOf(' ') != -1) {
			this.protocolo[0] = txt.substring(0, txt.indexOf(' '));
			this.protocolo[1] = txt.substring((txt.indexOf(' ') + 1), txt.length());
		} else {
			this.protocolo[0] = txt;
		}
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public ControleDeClientes getControle() {
		return controle;
	}

	public void setControle(ControleDeClientes controle) {
		this.controle = controle;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Scanner getEntrada() {
		return entrada;
	}

	public void setEntrada(Scanner entrada) {
		this.entrada = entrada;
	}

	public PrintWriter getSaida() {
		return saida;
	}

	public void setSaida(PrintWriter saida) {
		this.saida = saida;
	}

	public String[] getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String[] protocolo) {
		this.protocolo = protocolo;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isEntrou() {
		return entrou;
	}

	public void setEntrou(boolean entrou) {
		this.entrou = entrou;
	}

}
