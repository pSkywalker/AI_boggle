package GameData;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Game.Boggle;
import Game.Player;
import Game.Word;
import SimulatedIntelligence.AI_Player;

public class GameScore {

	public static GameScore instance = null;
	private ArrayList< Game > allScores;
	
	public GameScore() { 
		this.allScores = new ArrayList<Game>();
	}
	
	public static GameScore getInstance() { 
		if( instance == null ) { 
			instance = new GameScore();
		}
		return instance;
	}
	
	public void scoreGame( Boggle boggle ) throws FileNotFoundException { 
		Game currentGame = new Game();
		for( int x = 0; x < boggle.getPlayers().size(); x++ ) { 
			for( int y = 0; y < boggle.getPlayers().get(x).getWords().size(); y++ ) { 
				WordDatabase.getInstance().searchForWord(
						boggle.getPlayers().get(x).getWords().get(y));
			}
			currentGame.addStanding( new Score( boggle.getPlayers().get(x) ) );
		}
		this.allScores.add( currentGame );
	}
	
	public void printLatestGameStandings() { 
		this.allScores.get( this.allScores.size() -1 ).printStandings();
	}
	
	private class Game{ 
		private ArrayList<Score> standings;
		public Game(  ) { 
			
		}
		public void addStanding( Score score ) {
			this.standings = new ArrayList<Score>();
			// sort scores by points 
			for( int x = 0; x < this.standings.size(); x++ ) { 
				if( this.standings.get(x).getPoints() < score.getPoints() ) { 
					this.standings.add(x, score);
					break;
				}
			}
			this.standings.add(score);
		}
		public Player getWinner() { 
			return this.standings.get( this.standings.size()-1 ).getPlayer();
		}
		public void printStandings() { 
			StringBuilder standings = new StringBuilder();
			for( int x = 0; x < this.standings.size(); x++ ) { 
				standings.append( String.valueOf(x+1) + ". " + this.standings.get(x).getPlayer().getUsername() +"\n" );
			}
			System.out.println( standings.toString() );
		}
	}
	
	private class Score{
		private Player player;
		private ArrayList<Word> words;
		private Integer points;
		private Integer AI_playerScore;
		
		public Score( Player players ) { 
			this.player = player;
			this.words = this.player.getWords();
			for( int x = 0; x < this.words.size();x++ ) { 
				if( this.words.get(x).isValid() ) { 
					this.points += this.words.get(x).getPoints();
				}
			}
		}
		public Player getPlayer() { 
			return this.player;
		}
		public Integer getPoints() { 
			return this.points;
		}
		public void addAI_playerScore( Integer ai_score ) { 
			this.AI_playerScore = ai_score;
		}
		public Integer getAI_playerScore() { 
			return this.AI_playerScore;
		}
	}
	
}
