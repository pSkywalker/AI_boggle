package Game;

import java.util.ArrayList;

public class Player {

	private String username;
	private ArrayList<Word> words;
	private Integer points; 
	
	public Player( String username ){ 
		this.username = username;
		this.points = 0;
	}
	
	public String getUsername() { 
		return this.username;
	}
	public void addWord( Word word ) { 
		this.words.add(word);
	}
	public void addWords(ArrayList<Word> words ) { 
		this.words = words;
	}
	public ArrayList<Word> getWords(){ 
		return this.words;
	}
	
	public void addPoint() { 
		this.points++;
	}
	public void addScore( Integer points ) { 
		this.points = points;
	}
	
}
