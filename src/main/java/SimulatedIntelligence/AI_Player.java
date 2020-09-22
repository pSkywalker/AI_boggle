package SimulatedIntelligence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.google.common.collect.Collections2;

import Game.Word;
import GameData.WordDatabase;
import Misc.Debugging;


public class AI_Player {

	private Integer connectedToPort = 9882;
	private String username;
	
	private ArrayList< ArrayList<String> > allboards; 
	private ArrayList<String> currentBoard;
	
	private ArrayList<Word> wordsFound;
	
	private Integer currentWordBeingChecked = 0;
	
	private ServerSocket socketConnection;
	private Socket socketListener;
	
	public AI_Player() throws IOException {
		System.out.println("AI is running on port: " + connectedToPort);
		username = "AI";
		this.allboards = new ArrayList<ArrayList<String>>();
		this.currentBoard = new ArrayList<String>();
		
		this.socketConnection = new ServerSocket( this.connectedToPort );
		this.socketListener = socketConnection.accept();
	}
	
	public void getBoardAccess( ArrayList<String> board ) { 
		if( this.currentBoard.isEmpty() ) { 
			this.allboards.add(this.currentBoard);
		}
		this.currentBoard = board;

		//this.play();
		System.out.println( "Ai is about to start training" );
		this.train();
	}
	public ArrayList<String> getCurrentBoard(){ 
		return this.allboards.get(this.allboards.size()-1);
	}
	
	//public void networkCommunication( String data ) throws IOException { 
	//	Debugging.printDebug( "From the netword communication method" );
	//	PrintWriter socketOutput = new PrintWriter( socketListener.getOutputStream(), true );
	//	socketOutput.println( data );
	//}
	
	
	public void play() { 
		
	}

	public void train() { 
		System.out.println( "AI started training" );
		
		// 788204889
		
		Collection<List<String>> allCombinations = Collections2.permutations(this.currentBoard);
		//Object[] allCombos = allCombinations.toArray();
		
		Stream<List<String>> comboStream = allCombinations.stream();
		//Object comboString = comboStream.iterator().next().toString();
				
		
		comboStream.forEach(new Consumer<Object>() {
			@Override
			public void accept(Object t) {
				// TODO Auto-generated method stub
				
				try {
					String wordAsString = t.toString().replace("[", "").replace("]", "").replace(",","").replace(" ", "");
					Integer multiple = 3;
					while( true ) {
						Integer currentVal = 0 ;
						//System.out.println( wordAsString );
						//System.out.println( multiple );
						while(true) { 
							currentWordBeingChecked++;
							//System.out.println("Current word:" + String.valueOf( currentWordBeingChecked ) );
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
				}catch( Exception ex ) { 
					
				}	
			} 
			
		});
		
		WordDatabase.getInstance().printAllFoundWords();
		
	}
	
	public void updateAI_metaData() throws FileNotFoundException {
		//for( int x = 0; x < this.allPossibleCombinations.size(); x++ ) { 
		//	WordDatabase.getInstance().searchForWord( this.allPossibleCombinations.get(x) );
		//}
		this.allboards.add(this.currentBoard);
		this.currentBoard.clear();
	}
	
}
