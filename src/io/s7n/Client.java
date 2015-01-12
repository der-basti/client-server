package io.s7n;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client for a simple client/server notification application.
 * 
 * @author Sebastian Nemak
 *
 */
public class Client {

	private final String host;
	private final int port;

	public static void main(String[] args) {
		Client client = new Client("localhost", Server.DEFAULT_PORT);
		client.sendMessage("Test message.");
	}

	/**
	 * A simple constructor.
	 * 
	 * @param host server
	 * @param port server
	 */
	public Client(final String host, final int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * Send a message to the server.
	 * 
	 * @param message
	 *            String
	 */
	public void sendMessage(final String message) {
		try (Socket socket = new Socket(InetAddress.getByName(this.host),
				this.port);) {
			// send the message to the server
			OutputStream os = socket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

			bw.write(message);
			bw.flush();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
