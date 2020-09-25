package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connectivity {

	public static Connectivity instance;
	
	private Integer port = 9881;
	
	private PrintWriter socketOutput;
	private ServerSocket socketConnection;
	private Socket socketListener;
	
	private Boolean firstMenuShown;
	
	public Connectivity() { 
		try {
			this.socketConnection = new ServerSocket( this.port );
			this.firstMenuShown = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void awaitConnection() throws IOException { 
		System.out.println( "AI is now listening on port : " + String.valueOf( this.port ) );
		this.socketListener = socketConnection.accept();
		this.socketOutput = new PrintWriter( socketListener.getOutputStream(), true );
		this.socketOutput.println( "Welcome to boggle ai!"
				+ this.menu() );
	}
	
	public String getUserInput() throws IOException { 
		
		if( firstMenuShown ) { 
			this.socketOutput.println(this.menu()
					);
		}
		
		BufferedReader socketInputStream = 
				new BufferedReader(
						new InputStreamReader(
								this.socketListener.getInputStream()
								)
						);
		
		this.firstMenuShown = true;
		return socketInputStream.readLine().toLowerCase();
	}
	public void printToClient(String data) { 
		this.socketOutput.println( data );
	}
	
	public String menu() { 
		return "\n\t\t1.Play: --play \n\t\t2.Train --train \n\t\t3.List all known words --list";		
	}
	public void printNoWordsError() { 
		this.socketOutput.println( "AI doesn't know any words!\\nSelect train to teach the AI!" );
	}
	
	public static Connectivity getInstance() { 
		if( instance == null ) { 
			instance = new Connectivity();
		}
		return instance;
	}
	
	
	
	
	
}
