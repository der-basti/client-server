package io.s7n;

public class SampleRun {

	public static void main(String[] args) {
		final int port = Server.DEFAULT_PORT;

		// start server in a separate thread
		new Thread(new Server(port)).start();
		// init client
		Client client = new Client("localhost", port);

		client.sendMessage("Test");
		client.sendMessage("Alert! It is windy.");
		client.sendMessage(Server.TERMINATE_COMMAND);

	}
}
