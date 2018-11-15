package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class ControleDeClientes implements Serializable {
	Logs logs = null;
	private ArrayList<Cliente> clientes = new ArrayList<>();

	ControleDeClientes(Logs logs) {
		this.logs = logs;
	}

	public void addCliente(Cliente cliente) {
		this.clientes.add(cliente);// ADICIONA cliente ao arraylist
	}

	public boolean verificaNick(String nick) {
		for (int i = 0; i < clientes.size(); i++) {
			if (this.clientes.get(i).getNick() == null) {// se não tem nome não entrou, não precisa verificar.
				continue;
			}
			if (clientes.get(i).getNick().equals(nick)) {
				return true;
			}
		}
		return false;
	}

	public void enviaParaTodos(String msg) {
		for (int i = 0; i < clientes.size(); i++) {
			if (this.clientes.get(i).getNick() == null) {// se não tem nome não entrou, não precisa ser notificado.
				continue;
			}
			clientes.get(i).getSaida().println(msg);
			clientes.get(i).getSaida().flush();
		}

	}

	public void notificaEntrada(String nick, String msg) {// notificação de cliente entrando
		for (int i = 0; i < this.clientes.size(); i++) {
			if (this.clientes.get(i).getNick() == null) {// se não tem nome não entrou, não precisa ser notificado.
				continue;
			}
			if (!this.clientes.get(i).getNick().equals(nick)) {// notifico todos menos o próprio que entrou
				this.clientes.get(i).getSaida().println("MSG " + nick + " " + msg);
				this.clientes.get(i).getSaida().flush();
			}
		}
	}

	public int notificaSaida(String nick, String msg) {// notificação de cliente saindo
		int response = 0;
		for (int i = 0; i < this.clientes.size(); i++) {
			if (this.clientes.get(i).getNick() == null) {// evitando clientes que ainda não entraram.
				continue;
			}
			if (!this.clientes.get(i).getNick().equals(nick)) {
				this.clientes.get(i).getSaida().println("MSG " + nick + msg);
				this.clientes.get(i).getSaida().flush();
			} else {
				response = i;// enconctrando posicção do cliente que vai sair
			}
		}
		return response;
	}

	public int saidaNull(Cliente cliente) {
		int response = 0;
		for (int i = 0; i < this.clientes.size(); i++) {
			if (this.clientes.get(i).equals(cliente)) {
				response = i;// encontrando cliente que conectou mans nao entrou
			}
		}
		return response;
	}

	public void conectaCliente(Cliente cliente) {

	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Logs getLogs() {
		return logs;
	}

	public void setLogs(Logs logs) {
		this.logs = logs;
	}

}
