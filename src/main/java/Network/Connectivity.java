package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import GameData.WordDatabase;

public class Connectivity {

	public static Connectivity instance;
	
	private Integer port = 9881;
	
	private PrintWriter socketOutput;
	private ServerSocket socketConnection;
	public Socket socketListener;
	
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
				);
	}
	
	public String getUserInput() throws IOException { 
		
		//if( firstMenuShown ) { 
		//	this.socketOutput.println(this.menu()
		//			);
		//}
		
		BufferedReader socketInputStream = 
				new BufferedReader(
						new InputStreamReader(
								this.socketListener.getInputStream()
								)
						);
		
		this.firstMenuShown = true;
		return socketInputStream.readLine().toLowerCase();
	}
	public String menu() { 
		return "\n\t1.Play: --play "
				+ "\n\t2.Train --train \n\t"
				+ "3.List all known words --list\n\t"
				+ "4.Set gameboard --set-board <board>\n\t"
				+ "5.Generate new Board --gen-board\n\t"
				+ "6.Show board --show-board\n\t";		
	}
	
	public void printAllKnownWords() { 
		StringBuilder allKnownWords = new StringBuilder();
		for( int x = 0; x < WordDatabase.getInstance().getKnownWords().size(); x++ ) { 
			allKnownWords.append( WordDatabase.getInstance().getKnownWords().get(x) + " " );
		}
	}
	public void printToClient(String data) { 
		this.socketOutput.println( data );
	}
	public void printMenu() { 
		this.socketOutput.println( this.menu() ); 
	}
	public void printNoWordsError() { 
		this.socketOutput.println( "AI doesn't know any words!\nSelect train to teach the AI!" );
	}
	public void printNoCommandError() { 
		this.socketOutput.println( "Error, command not found" );
	}
	
	public static Connectivity getInstance() { 
		if( instance == null ) { 
			instance = new Connectivity();
		}
		return instance;
	}
	
	
	
	
	
}
