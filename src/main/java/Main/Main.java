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

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		//Boggle boggle = new Boggle();
		//boggle.playTurn();
		//boggle.generateBoard();
		//boggle.printBoard();
		
		Word word = new Word("software");
		
		WordDatabase.getInstance().addNewWord(word);
		WordDatabase.getInstance().searchForWord(word);
		
		System.out.print( word.isValid()+"\n");
		
		
		
		//GameRuntime gameRuntime = new GameRuntime();
		//gameRuntime.start();
		
		
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




