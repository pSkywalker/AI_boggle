package GameData;

import java.util.ArrayList;

public class AllBoards {
	
	
	public static AllBoards instance = null;
	
	private ArrayList< ArrayList<String> > boards;
	
	
	public AllBoards() { 
		this.boards = new ArrayList< ArrayList<String> >();
	}
	
	public static AllBoards getInstance() { 
		if( instance == null ) { 
			instance = new AllBoards();
		}
		return instance;
	}
	
	public void addNewBoard( ArrayList<String> newBoard ) { 
		this.boards.add( newBoard );
	}
	
	public void getBoards() { 
		
	}
	
}
