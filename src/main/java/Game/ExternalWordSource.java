package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExternalWordSource {

	public static ExternalWordSource instance;

	private String fileName = "src/main/java/wordList.txt";
	
	private File file;
	private Scanner fileReader;
	
	public ExternalWordSource() throws FileNotFoundException { 
		this.file = new File( fileName );
		this.fileReader = new Scanner( this.file );
	}
	public ExternalWordSource( String fileName ) throws FileNotFoundException { 
		this.fileName = fileName;
		this.fileReader = new Scanner( this.file );
	}
	
	public static ExternalWordSource getInstance() throws FileNotFoundException { 
		if( instance == null ) { 
			instance = new ExternalWordSource();
		}
		return instance;
	}
	
	public void findWord( Word word ) { 
		// replace with more efficient algo
		while( fileReader.hasNextLine() ) { 
			String externalWord = fileReader.nextLine();
			if(  word.getWord().toLowerCase().charAt(0) < externalWord.toLowerCase().charAt(0) ) { 
				break;
			}
			if( word.getWord().toLowerCase().equals(externalWord.toLowerCase())  ) { 
				word.setValid(true);
			}
		}
	}
	
	public void printEntireFile() { 
		while ( fileReader.hasNextLine() ) {
			System.out.println( fileReader.nextLine() );
		}
	}
	
	
}

