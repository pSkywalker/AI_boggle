package Game;

import java.util.ArrayList;

public class MenuCreator {
	
	public ArrayList<Menu> everyMenu;
	
	public MenuCreator(){ 
		
	}
	public void createMenu( String menuQuestions,ArrayList<String> menuOptions ) { 
		Menu menu = new Menu(menuQuestions, menuOptions);
		this.everyMenu.add(menu);
	}
	public void getSelectedOption(Integer option) { 
		
	}
	public class Menu{ 
		public String menuQuestion; 
		public ArrayList<String> menuOptions;
		public Integer optionSelected;
		public Menu( String menuQuestion, ArrayList< String > menuOptions) { 
			this.menuQuestion = menuQuestion;
		}
	}
}
