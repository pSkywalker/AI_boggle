package GameData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Game.ExternalWordSource;
import Game.Word;
import Network.Connectivity;

public class WordDatabase implements Serializable{

	public String filePath_forSerialization = "src/main/java/wordDataBase.ser";
	
	public static WordDatabase instance = null;
	private ArrayList<Word> words; 

	public WordDatabase() { 
		this.words = new ArrayList<Word>();
	}
	
	public static WordDatabase getInstance() { 
		if( instance == null ) { 
			instance = new WordDatabase();
		}
		return instance;
	}
	
	public void serialize() throws IOException { 
		FileOutputStream fileOutput = new FileOutputStream(this.filePath_forSerialization);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream( fileOutput );
		objectOutputStream.writeObject(this);
		objectOutputStream.close();
		fileOutput.close();
	}
	public void deserialize() {	
		try { 
			FileInputStream fileIn = new FileInputStream(this.filePath_forSerialization);
			ObjectInputStream objectStream = new ObjectInputStream( fileIn );
			instance = (WordDatabase) objectStream.readObject();
		}
		catch(Exception exception) { 
			
		}
	}
	
	public void searchLearnedWords( Word word ) { 
		for( int x = 0; x < this.words.size(); x++ ) { 
			if( this.words.get(x).getWord().charAt(0) < word.getWord().charAt(0) ) { 
				break;
			}
			if( this.words.get(x).getWord().toLowerCase().equals(word.getWord().toLowerCase()) ){ 
				word.setValid(true);
				//System.out.println( word.getWord() );
			}
		}
	}
	
	public void searchForWord( Word word ) throws FileNotFoundException {
		//System.out.println( word.getWord() );

		for( int x = 0; x < this.words.size(); x++ ) { 
			if( this.words.get(x).getWord().charAt(0) < word.getWord().charAt(0) ) { 
				break;
			}
			if( this.words.get(x).getWord().toLowerCase().equals(word.getWord().toLowerCase()) ){ 
				word.setValid(true);
				//System.out.println( word.getWord() );
			}
		}
		if( !word.isValid() ) { 
			//System.out.println( word.getWord() );
			ExternalWordSource.getInstance().findWord(word);
			//System.out.println( word.getWord()+ " " + word.isValid() );
			if( word.isValid() ) {
				boolean wordAlreadyExists = false;
				for( int x = 0; x < this.words.size(); x++ ) { 
					if( this.words.get(x).getWord().toLowerCase().charAt(0) < word.getWord().toLowerCase().charAt(0) ) { 
						//break;
					}
					if( this.words.get(x).getWord().toLowerCase().equals( word.getWord().toLowerCase() ) ) { 
						wordAlreadyExists = true;
					}
				}
				if( !wordAlreadyExists ) { 
					this.words.add(word);
					Connectivity.getInstance().printToClient( word.getWord()  );
				}
			}
		}
		
	}
	
	public void addNewWord( Word word ) { 
		this.words.add(word);
	}
	public ArrayList<Word> getKnownWords(){ 
		return this.words;
	}
	public void emptyDataBase() { 
		this.words.clear();
	}
	
	public Integer getSizeOfWordDatabase() { 
		return this.words.size();
	}
	
	public void printAllFoundWords() { 
		for( int x = 0; x < this.words.size(); x++ ) { 
			System.out.println( this.words.get(x) );
		}
	}
	
		
}

