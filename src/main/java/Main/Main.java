package Main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import Game.Boggle;
import Game.ExternalWordSource;
import Game.GameRuntime;
import Game.Word;
import GameData.WordDatabase;
import Misc.Debugging;
import Network.Connectivity;
import SimulatedIntelligence.AI_Player;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		

		Boggle boggle = new Boggle();
		boggle.generateBoard();
		AI_Player ai = new AI_Player(boggle.getGameBoard());
		WordDatabase.getInstance().deserialize();	
		Connectivity.getInstance().awaitConnection();
		
		while( true ) {

			Connectivity.getInstance().printMenu();
			
			String userInput = Connectivity.getInstance().getUserInput();

			if( userInput.equals("--play") ) { 
				ai.play();
			}
			else if( userInput.equals( "--train" ) ) { 
				ai.train();
			}
			else if( userInput.equals( "--list" ) ) { 
				Connectivity.getInstance().printAllKnownWords();
			}
			else if(userInput.equals("--gen-board") ) { 
				boggle.generateBoard();
				Connectivity.getInstance().printToClient( "New game board: " + boggle.getGameBoardAsString() );
			}
			else if( userInput.equals( "--show-board" ) ) { 
				Connectivity.getInstance().printToClient( boggle.getGameBoardAsString() );
			}
			else if( userInput.substring(0, 11).equals("--set-board") ) { 
				Connectivity.getInstance().printToClient( " The user tried to set the board with following board detals : " + userInput.substring(11, userInput.length()) );
				//Connectivity.getInstance().printToClient( " The user tried to set the board with following board detals : " + userInput.charAt(11) );

				boggle.setGameBoard(userInput.substring(11, userInput.length()));
			}
		}
		
	}
	/*
	switch( userInput.toLowerCase().replace(" ", "") ) { 
	case "--play":
		ai.play();
		break;
	case "--train":
		ai.train();
		WordDatabase.getInstance().serialize();
		break;
	case "--list":
		WordDatabase.getInstance().printAllFoundsWords_net();
		break;
	case "--set-board":
		Connectivity.getInstance().printToClient("Add a boggle board. Needs to be in array format");
		boggle.setGameBoard( Connectivity.getInstance().getUserInput() );
		break;
	case "current-board":

		break;
	default: 
		Connectivity.getInstance().printNoCommandError();
		break;
	}
	*/
	//Boggle boggle = new Boggle();
	//boggle.playTurn();
	//boggle.generateBoard();
	//boggle.printBoard();
	
	
	//WordDatabase.getInstance().deserialize();
	//WordDatabase.getInstance().emptyDataBase();
	//WordDatabase.getInstance().printAllFoundWords();
	//Word word = new Word("four");
	
	//ArrayList<Word> words = new ArrayList<>();
	//words.add(new Word("four"));
	//words.add(new Word("four"));
	//words.add(new Word("four"));
	//words.add(new Word("four"));
	
	//for( int x = 0; x < words.size(); x++ ) { 
	//	WordDatabase.getInstance().searchForWord( words.get(x) );
	//}
	//System.out.println( WordDatabase.getInstance().getSizeOfWordDatabase() );
	
	//WordDatabase.getInstance().searchForWord(words.get(0));
	//WordDatabase.getInstance().printAllFoundWords();
	
	//WordDatabase.getInstance().serialize();
	
	//ExternalWordSource.getInstance().findWord(new Word( "Software" ));
	//GameRuntime gameRuntime = new GameRuntime();
	//gameRuntime.start();
	
	/*
	ArrayList<String> board = new ArrayList<String>();
	board.add("f");
	board.add("o");
	board.add("u");
	board.add("r");
	board.add("a");
	board.add("s");
	board.add("o");
	board.add("f");
	board.add("t");
	board.add("w");
	board.add("a");
	board.add("r");
	board.add("e");
	board.add("r");
	board.add("s");
	board.add("qu");
	*/
	//wordBuilder( board );
	
	//WordDatabase.getInstance().printAllFoundWords();
	
	
	public static void wordBuilder(ArrayList<String> board) throws FileNotFoundException { 
		String wordAsString = board.toString().replace("[", "").replace("]", "").replace(",","").replace(" ", "");
		Integer multiple = 3;
		Integer currentWordBeingChecked = 0;
		while( true ) {
			Integer currentVal = 0 ;
			//System.out.println( wordAsString );
			//System.out.println( multiple );
			while(true) { 
				currentWordBeingChecked++;
				//System.out.println("Current word:" + String.valueOf( currentWordBeingChecked ) );
				//System.out.println( "while loop from the top : " + wordAsString.substring(currentVal, currentVal+multiple) );
				WordDatabase.getInstance().searchForWord(
						new Word(wordAsString.substring(currentVal, currentVal+multiple)));
				//networkCommunication(
				//		"Current word:" + String.valueOf( currentWordBeingChecked ) + " / " + allCombinations.size()
				//		);
				//System.out.println( wordAsString.substring( currentVal, currentVal+multiple) );

				currentVal++;
				if( currentVal > 15 || currentVal + multiple > 15 ) { 
					break;
				}
			}
			multiple++;
			if( multiple > 8 ) {
				break;
			}
		}
	}
	
	static class TestClass implements Serializable{ 
		
		public String filePath_forSerialization = "src/main/java/wordDataBase.ser";
		
		public static TestClass instance = null;
		private String testClassString;
		public TestClass( String testClassString ) { 
			this.testClassString = testClassString;
		}
		public TestClass() { 
			
		}
		public static TestClass getInstance() { 
			if( instance == null ) { 
				instance = new TestClass( "" );
			}
			return instance;
		}
		public String getTestString(  ) { 
			return this.testClassString;
		}
		public void addString(String string) { 
			this.testClassString = string;
		}
		public void serialize() throws IOException { 
			FileOutputStream fileOutput = new FileOutputStream(this.filePath_forSerialization);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream( fileOutput );
			objectOutputStream.writeObject(this);
			objectOutputStream.close();
			fileOutput.close();
		}
		public void deserialize() throws ClassNotFoundException, IOException, EOFException { 
			try { 
				FileInputStream fileIn = new FileInputStream(this.filePath_forSerialization);
				ObjectInputStream objectStream = new ObjectInputStream( fileIn );
				instance = (TestClass) objectStream.readObject();
			}catch( Exception ex ) { 
				
			}
		}

	}
	
	
}




