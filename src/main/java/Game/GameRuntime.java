package Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Game.MenuCreator;
import GameData.GameScore;
import GameData.WordDatabase;

public class GameRuntime extends Thread{
	
	private Boggle boggle;
	private Scanner userInput;
	
	
	public GameRuntime() throws IOException {
		this.boggle = new Boggle();
		WordDatabase.getInstance().deserialize();
		this.userInput = new Scanner( System.in );
	}
	
	public void addPlayers() { 
		ArrayList<Player> players = new ArrayList<Player>();
		System.out.println( "How many players are going to be playing?" );
		Integer amountOfPlayers = userInput.nextInt();
		for( int x = 0 ; x < amountOfPlayers; x++ ) { 
			System.out.println( "What will player " + String.valueOf(x) + " username be? " );
			String playerUsername = userInput.next();
			players.add( new Player( playerUsername ) );
		}
		this.boggle.addPlayers( players );
	}
	
	public void run(){ 
		try {
			//this.addPlayers();
			while(true) {
				boggle.playTurn();
				boggle.printBoard();
				Thread.sleep(3000);
				WordDatabase.getInstance().serialize();
				GameScore.getInstance().scoreGame(boggle);
				GameScore.getInstance().printLatestGameStandings();
			}
			
		} catch (/*InterruptedException*/ Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
