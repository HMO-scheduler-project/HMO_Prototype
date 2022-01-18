package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.server.ocsf.clinicClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class SimpleServer extends AbstractServer {
	private static ArrayList<clinicClient> waitingRoomList = new ArrayList<>();

	public static ArrayList<clinicClient> getWaitingRoomList() {
		return waitingRoomList;
	}

	public void setWaitingRoomList(ArrayList<clinicClient> waitingRoomList) {
		waitingRoomList = waitingRoomList;
	}

	//public void addToWaitingRoomList(c)

	public SimpleServer(int port) {
		super(port);

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	synchronized protected void clientDisconnected(
			ConnectionToClient client) {
		if(waitingRoomList.contains(client)){
			waitingRoomList.remove(client);
		}
	}

}
