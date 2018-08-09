package commandline;

import java.util.*;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that
	 * they want to run in command line mode. The contents of args[0] is whether we
	 * should write game logs to a file.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true"))
			writeGameLogsToFile = true; // Command line selection

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		// database
		Database database = new Database();

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			boolean flag = true;
			while (flag) {
				System.out.println("Print the statistics of past games(P) or start a game?(S)? (P/S)");
				Scanner scanner = new Scanner(System.in);
				String s = scanner.next();
				if (s.equals("P")) {
					// load statistics of past games
					flag = false;

					// connect
					String dbname = null;
					String username = null;
					String password = null;
					database.connect(dbname, username, password);

					// print
					System.out.println("Number of games played overall: " + database.countOverAll());
					System.out.println("How many times the AI has won: " + database.countAIWin());
					System.out.println("How many times the human has won: " + database.countHumanWin());

					// calculate average draws
					double average = database.countDraws() / database.countOverAll();
					System.out.println("The average number of draws: " + String.format("%.2f", average));
					System.out.println(
							"The largest number of rounds played in a single game: " + database.largestRounds());

					// close database
					database.close();
					break;
				} else if (s.equals("S")) {
					flag = false;

					// start the game
					Game game = new Game(4);
					game.logSetting(writeGameLogsToFile);
					game.startTheGame();
					break;
				} else {
					System.out.println("invalid input please try again.");

				}
			}
			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			boolean flag2 = true;
			while (flag2) {
				System.out.println("Do you want to quit the game?(Y/N)");
				Scanner scanner4 = new Scanner(System.in);
				String s2 = scanner4.next(); // AIs and player
				if (s2.equals("Y")) {
					flag2 = false;
					userWantsToQuit = true; // use this when the user wants to exit the game
					break;
				} else if (s2.equals("N")) {
					flag2 = false;
					userWantsToQuit = false;
					break;
				} else {
					System.out.println("invalid input please try again.");
				}
			}
		}

	}

}
