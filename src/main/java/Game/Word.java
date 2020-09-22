package Game;

import java.io.Serializable;

public class Word implements Serializable{

	private String word;
	private Integer points;
	private Boolean valid;
	
	public Word( String word ) { 
		this.word = word;
		this.points = this.word.length();
		this.valid = false;
	}
	
	public String getWord() { 
		return this.word;
	}
	public Integer getPoints() { 
		return this.points;
	}
	public String toString() { 
		return this.word + " for " + this.points;
	}
	public void setValid( boolean valid ) { 
		this.valid = true;
	}
	public Boolean isValid() { 
		return this.valid;
	}
	
	
	
}
