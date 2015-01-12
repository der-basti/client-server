package io.s7n;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Server for a simple client/server notification application.
 * 
 * @author Sebastian Nemak
 *
 */
public class Server implements Runnable {

	public static final int DEFAULT_PORT = 1337;
	public static final String TERMINATE_COMMAND = "terminate";

	private final int port;

	public static void main(String[] args) {
		new Server(DEFAULT_PORT).run();
	}

	/**
	 * A simple constructor.
	 * 
	 * @param port listen
	 */
	public Server(final int port) {
		this.port = port;
	}

	/**
	 * Start server. You can stop it with the message "terminate".
	 */
	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(this.port);) {
			final String host = InetAddress.getLocalHost().getHostName();
			System.out.println("Server started and listening to " + host + ":"
					+ this.port);

			boolean run = true;
			while (run) {
				// Reading messages from the client
				try {
					InputStream is = serverSocket.accept().getInputStream();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is));
					String msg = br.readLine();
					System.out.println("Message received from client: " + msg);
					if (msg.equalsIgnoreCase(TERMINATE_COMMAND)) {
						run = false;
					}
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Server terminated");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
