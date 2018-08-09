package commandline;

public class Statistics {
	
		private int gameRound=0;
		private int draw=0;
		private int [] playerWinRound;
		private String winner="";
		
		public Statistics(int playerNumber) {
			this.playerWinRound=new int[playerNumber];
		}
		//setter
		public void setPlayerWinRound(int index) {
			playerWinRound[index]++;
		}
		public void setGameRound(int round) {
			gameRound=round;
		}
		public void setDraw(int draw) {
			this.draw=draw;
		}
		public void setWinner(String winner) {
			this.winner=winner;
		}
		
		//getter
		public int getGameRound() {
			return gameRound;
		}
		public int getDraw() {
			return draw;
		}
		public int getPlayerWinRound(int index) {
			return playerWinRound[index];
		}
		public String getWinner() {
			return winner;
		}
	}


