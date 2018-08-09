package online.dwResources;

import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import javax.validation.constraints.Past;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.ArrayUtils;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import online.dwResources.Cards;
import commandline.Database;
import online.dwResources.Players;
import online.dwResources.Statistics;

import java.sql.*;
import java.util.*;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands to
 * the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create REST API
 * methods in Dropwizard. You will need to replace these with methods that allow
 * a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/**
	 * A Jackson Object writer. It allows us to turn Java objects into JSON strings
	 * easily.
	 */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	/**
	 * Contructor method for the REST API. This is called first. It provides a
	 * TopTrumpsJSONConfiguration from which you can get the location of the deck
	 * file and the number of AI players.
	 * 
	 * @param conf
	 */

	// new database connection
	Connection connection = null;

	Cards[] cards;
	Players[] player;
	Statistics statistics;
	final int max = 40;
	int index = 0;
	int k = 0;// number of winner
	int round = 0;
	int draw = 0;
	int games = 0;// how many games have been played
	int playerWin = 0;// player winning rounds
	int end = 0;
	// AIs winning rounds
	int AI1Win = 0;
	int AI2Win = 0;
	int AI3Win = 0;
	int AI4Win = 0;
	String winner = "";

	// create communal pile
	ArrayList<Cards> communalPile = new ArrayList<>();

	// database
	Database database = new Database();

	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
	}

	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------

	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String. We
	 * also illustrate here how we can convert Java objects to JSON strings.
	 * 
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");

		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	// database
	@GET
	@Path("/connectData")

	public String connect() throws IOException {

		// access to database
		String dbname = "m_17_2294163p";
		String username = "m_17_2294163p";
		String password = "2294163p";
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + dbname, username,
					password);
		} catch (SQLException e) {
			System.err.println("Connection Failed!");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("\nDatabase Connection successful\n");
		} else {
			System.err.println("Failed to make connection!");
		}
		return "";
	}

	@GET
	@Path("/countOverAll")
	// count over all games
	public String countOverAll() {
		Statement stmt = null;
		String query = "SELECT COUNT(gameid) FROM top_trump_team_project.game;";
		String count = "";
		// get count
		try {

			// insert
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				count = rs.getString("count");
			}

		}

		// catch errors
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		return count;
	}

	@GET
	@Path("/countAIWin")
	// count over AI win
	public String countAIWin() {
		Statement stmt = null;
		String query = "SELECT COUNT(winners) FROM top_trump_team_project.game WHERE winners IN('AI1', 'AI2', 'AI3', 'AI4');";
		String count = "";
		// get count
		try {

			// insert
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				count = rs.getString("count");
			}

		}

		// catch errors
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		return count;
	}

	@GET
	@Path("/countHumanWin")
	// count over human win
	public String countHumanWin() {
		Statement stmt = null;
		String query = "SELECT COUNT(winners) FROM top_trump_team_project.game WHERE winners = 'Player';";
		String count = "";
		// get count
		try {

			// insert
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				count = rs.getString("count");
			}

		}

		// catch errors
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		return count;
	}

	@GET
	@Path("/countDraws")
	// count draws
	public String countDraws() {
		Statement stmt = null;
		String query = " SELECT draws FROM top_trump_team_project.game ";
		double draws = 0;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				draws = draws + Double.parseDouble(rs.getString("draws"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		return String.format("%.2f", (draws / Integer.parseInt(countOverAll())));
	}

	@GET
	@Path("/largestRounds")
	// largest rounds
	public int largestRounds() {
		Statement stmt = null;
		String query = " SELECT rounds FROM top_trump_team_project.game ";
		int rounds = 0;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int i = Integer.parseInt(rs.getString("rounds"));
				if (i > rounds) {
					rounds = i;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		return rounds;
	}

	// game method
	// game method
	// game method
	// game method
	// game method
	// game method
	// game method
	// game method

	// start the game
	@GET
	@Path("/startTheGame")
	public String startTheGame(@QueryParam("numPlayer") int numPlayer) throws IOException {
		cards = new Cards[max];
		player = new Players[numPlayer];
		statistics = new Statistics(numPlayer);
		index = 0;
		k = 0;// number of winner
		round = 0;
		draw = 0;
		games = 0;// how many games have been played
		playerWin = 0;// player winning rounds
		end = 0;
		// AIs winning rounds
		AI1Win = 0;
		AI2Win = 0;
		AI3Win = 0;
		AI4Win = 0;
		winner = "";

		loadCards();
		index = 0;
		communalPile.clear();
		// get someone to start
		Random random = new Random();
		k = random.nextInt(numPlayer);
		winner = player[k].getName();
		return winner;
	}

	@GET
	@Path("/getK")
	public int getK() throws IOException {
		// current winner player[k]
		int k = this.k;
		return k;
	}

	@GET
	@Path("/playerNum")
	//count number of player currently
	public int playerNum() throws IOException {
		int countPlayer = 0;
		for (int i = 0; i < player.length; i++) {
			if (player[i].myCards.length > 0) {
				countPlayer++;
			}
		}
		return countPlayer;
	}

	@GET
	@Path("/getWinner")
	public String getWinner() throws IOException {
		// winner
		winner = this.winner;
		return winner;
	}

	@GET
	@Path("/showName")
	public String showName(@QueryParam("i") int i) {
		try {
			// show the top card of player
			String description = player[i].myCards[0].getDescription();
			return description;
		} catch (ArrayIndexOutOfBoundsException e) {
			return "";
		}
	}

	@GET
	@Path("/showSize")
	public int showSize(@QueryParam("i") int i) {
		try {
			// show the top card of player
			int size = player[i].myCards[0].getSize();
			return size;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	@GET
	@Path("/showSpeed")
	public int showSpeed(@QueryParam("i") int i) {
		try {
			// show the top card of player
			int speed = player[i].myCards[0].getSpeed();
			return speed;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	@GET
	@Path("/showRange")
	public int showRange(@QueryParam("i") int i) {
		try {
			// show the top card of player
			int range = player[i].myCards[0].getRange();
			return range;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	@GET
	@Path("/showHandLeft")
	public int showHandLeft(@QueryParam("i") int i) {
		try {
			// show the top card of player
			int left = player[i].myCards.length;
			return left;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	@GET
	@Path("/showFirepower")
	public int showFirepower(@QueryParam("i") int i) {
		try {
			// show the top card of player
			int firepower = player[i].myCards[0].getFirepower();
			return firepower;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	@GET
	@Path("/showCargo")
	public int showCargo(@QueryParam("i") int i) {
		try {
			// show the top card of player
			int cargo = player[i].myCards[0].getCargo();
			return cargo;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	// get cards from the text file
	public void loadCards() throws IOException {
		try {
			FileReader reader = new FileReader("StarCitizenDeck.txt");
			Scanner in = new Scanner(reader);

			int index = 0;
			in.nextLine(); // no need to read fist line

			while (in.hasNextLine()) {

				// read a line from file
				String line = in.nextLine();

				// split the word with space or more times
				String[] tokens = line.split("[ ]+");
				cards[index] = new Cards(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),
						Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
				index++;
			}
			shuffleCards(cards);
		}

		catch (IOException e) {
			System.err.println("Card file not found.");
		}
	}

	// shuffle cards method
	public void shuffleCards(Cards[] cards) throws IOException {
		Random random = new Random();
		for (int i = cards.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);

			// Simple swap
			Cards temp = cards[index];
			cards[index] = cards[i];
			cards[i] = temp;
		}
		divideCards();
	}

	public void divideCards() throws IOException {
		int cardnum = max / player.length;
		int cardLeft = max % player.length;

		for (int i = 0; i < player.length; i++) {

			// give players names
			String playerName = "AI" + i;

			// divide the name of player and AI
			if (playerName != null && playerName.equals("AI0")) {
				playerName = "Player";
			}

			// if can not separate cards equally
			if (cardLeft != 0) {
				cardnum = (max / player.length) + 1;
				cardLeft--;
			} else {
				cardnum = (max / player.length);
			}

			player[i] = new Players(playerName, cardnum);
			setPlayerCard(player[i], cardnum);
		}
	}

	// set cards
	public void setPlayerCard(Players player, int cardNum) throws IOException {
		for (int i = 0; i < cardNum; i++) {
			player.setMyCards(i, cards[index++]);
		}

	}

	// game logic
	// choose the category to compare

	@GET
	@Path("/rules")
	public String rules(@QueryParam("category") String category) throws IOException {
		try {
			//count current number of player
			int countPlayer = 0;
			for (int i = 0; i < player.length; i++) {
				if (player[i].myCards.length > 0) {
					countPlayer++;
				}
			}
			// insert info to database
			if (countPlayer < 2) {
				statistics.setGameRound(round);
				statistics.setDraw(draw);
				statistics.setWinner(player[k].getName());

				// connect to the database
				String dbname = null;
				String username = null;
				String password = null;
				database.connect(dbname, username, password);

				// get the game id in integer
				games = 1 + database.countOverAll();

				// get winning rounds per player
				if (player.length > 0) {
					playerWin = statistics.getPlayerWinRound(0);
				}
				if (player.length > 1) {
					AI1Win = statistics.getPlayerWinRound(1);
				}
				if (player.length > 2) {
					AI2Win = statistics.getPlayerWinRound(2);
				}
				if (player.length > 3) {
					AI3Win = statistics.getPlayerWinRound(3);
				}
				if (player.length > 4) {
					AI4Win = statistics.getPlayerWinRound(4);
				}

				// insert info to database game
				database.insertGame(games, statistics.getWinner(), statistics.getDraw(), statistics.getGameRound());

				// insert info to database winner
				database.updateWinner(playerWin, AI1Win, AI2Win, AI3Win, AI4Win);
			}

			String rules = "";

			// if choose size
			if (category.equals("Size")) {

				// boolean flag
				boolean flag = true;
				int largest = 0;
				int num = 0;
				int same = 0;

				// get the name and the largest size
				outterLoop: for (int i = 0; i < player.length; i++) {
					if (player[i].myCards.length != 0) {

						// get the largest
						for (int j = 0; j < player.length; j++) {
							if (player[j].myCards.length != 0) {
								if (num < player[j].myCards[0].getSize()) {
									num = player[j].myCards[0].getSize();
								}
							}
						}

						// then check draw
						for (int j = 0; j < player.length; j++) {
							if (player[j].myCards.length != 0) {
								if (player[j].myCards[0].getSize() == num) {
									same++;
								}
								if (same > 1) {
									flag = false;
									draw++;
									break outterLoop;
								}
							}
						}
						same = 0;
					}
				}

				if (flag) {
					// run compare
					for (int i = 0; i < player.length; i++) {
						if (player[i].myCards.length != 0) {
							int n = player[i].myCards[0].getSize();
							// get larger number
							if (n > largest) {
								largest = n;
								this.k = i;
							}
						}
					}
				}

				communalPile();
				rules = "We have a draw here.\r\n The number of cards in communal pile is " + communalPile.size()+"<br>Please press New Round to continue.";
				if (flag) {
					moveCards();
					rules = "The winner of this round is: " + player[k].getName()+"<br>Please press New Round to continue.";
				}
			}

			// if choose speed
			if (category.equals("Speed")) {

				// boolean flag
				boolean flag = true;
				int largest = 0;
				int num = 0;
				int same = 0;

				// get the name and the largest speed
				outterLoop: for (int i = 0; i < player.length; i++) {
					if (player[i].myCards.length != 0) {
						// get the largest
						for (int j = 0; j < player.length; j++) {
							if (player[j].myCards.length != 0) {
								if (num < player[j].myCards[0].getSpeed()) {
									num = player[j].myCards[0].getSpeed();
								}
							}
						}

						// then check draw
						for (int j = 0; j < player.length; j++) {
							if (player[j].myCards.length != 0) {
								if (player[j].myCards[0].getSpeed() == num) {
									same++;
								}
								if (same > 1) {
									flag = false;
									draw++;
									break outterLoop;
								}
							}
						}
						same = 0;
					}
				}

				if (flag) {
					// run compare
					for (int i = 0; i < player.length; i++) {
						if (player[i].myCards.length != 0) {
							int n = player[i].myCards[0].getSpeed();
							// get larger number
							if (n > largest) {
								largest = n;
								this.k = i;
							}
						}
					}
				}

				communalPile();
				rules = "We have a draw here.\r\n The number of cards in communal pile is " + communalPile.size()+"<br>Please press New Round to continue.";
				if (flag) {
					moveCards();
					rules = "The winner of this round is: " + player[k].getName()+"<br>Please press New Round to continue.";
				}
			}

			// if choose range
			if (category.equals("Range")) {

				// boolean flag
				boolean flag = true;
				int largest = 0;
				int num = 0;
				int same = 0;

				// get the name and the largest range
				outterLoop: for (int i = 0; i < player.length; i++) {
					if (player[i].myCards.length != 0) {
						// get the largest
						for (int j = 0; j < player.length; j++) {
							if (player[j].myCards.length != 0) {
								if (num < player[j].myCards[0].getRange()) {
									num = player[j].myCards[0].getRange();
								}
							}
						}

						// then check draw
						for (int j = 0; j < player.length; j++) {
							if (player[j].myCards.length != 0) {
								if (player[j].myCards[0].getRange() == num) {
									same++;
								}
								if (same > 1) {
									flag = false;
									draw++;
									break outterLoop;
								}
							}
						}
						same = 0;
					}
				}

				if (flag) {
					// run compare
					for (int i = 0; i < player.length; i++) {
						if (player[i].myCards.length != 0) {
							int n = player[i].myCards[0].getRange();
							// get larger number
							if (n > largest) {
								largest = n;
								this.k = i;
							}
						}
					}
				}

				communalPile();
				rules = "We have a draw here.\r\n The number of cards in communal pile is " + communalPile.size()+"<br>Please press New Round to continue.";
				if (flag) {
					moveCards();
					rules = "The winner of this round is: " + player[k].getName()+"<br>Please press New Round to continue.";
				}
			}

			// if choose fire power
			if (category.equals("Firepower")) {

				// boolean flag
				boolean flag = true;
				int largest = 0;
				int num = 0;
				int same = 0;

				// get the name and the largest fire power
				outterLoop: for (int i = 0; i < player.length; i++) {
					if (player[i].myCards.length != 0) {

						// get the largest
						for (int j = 0; j < player.length; j++) {
							if (player[j].myCards.length != 0) {
								if (num < player[j].myCards[0].getFirepower()) {
									num = player[j].myCards[0].getFirepower();
								}
							}
						}

						// then check draw
						for (int j = 0; j < player.length; j++) {
							if (player[j].myCards.length != 0) {
								if (player[j].myCards[0].getFirepower() == num) {
									same++;
								}
								if (same > 1) {
									flag = false;
									draw++;
									break outterLoop;
								}
							}
						}
						same = 0;
					}
				}

				if (flag) {
					// run compare
					for (int i = 0; i < player.length; i++) {
						if (player[i].myCards.length != 0) {
							int n = player[i].myCards[0].getFirepower();
							// get larger number
							if (n > largest) {
								largest = n;
								this.k = i;
							}
						}
					}
				}

				communalPile();
				rules = "We have a draw here.\r\n The number of cards in communal pile is " + communalPile.size()+"<br>Please press New Round to continue.";
				if (flag) {
					moveCards();
					rules = "The winner of this round is: " + player[k].getName()+"<br>Please press New Round to continue.";
				}
			}

			// if choose cargo
			if (category.equals("Cargo")) {

				// boolean flag
				boolean flag = true;
				int largest = 0;
				int num = 0;
				int same = 0;

				// get the name and the largest cargo
				outterLoop: for (int i = 0; i < player.length; i++) {
					if (player[i].myCards.length != 0) {
						// get the largest
						for (int j = 0; j < player.length; j++) {
							if (player[j].myCards.length != 0) {
								if (num < player[j].myCards[0].getCargo()) {
									num = player[j].myCards[0].getCargo();
								}
							}
						}

						// then check draw
						for (int j = 0; j < player.length; j++) {
							if (player[j].myCards.length != 0) {
								if (player[j].myCards[0].getCargo() == num) {
									same++;
								}
								if (same > 1) {
									flag = false;
									draw++;
									break outterLoop;
								}
							}
						}
						same = 0;
					}
				}

				if (flag) {
					// run compare
					for (int i = 0; i < player.length; i++) {
						if (player[i].myCards.length != 0) {
							int n = player[i].myCards[0].getCargo();
							// get larger number
							if (n > largest) {
								largest = n;
								this.k = i;
							}
						}
					}
				}

				communalPile();
				rules = "We have a draw here.\r\n The number of cards in communal pile is " + communalPile.size()+"<br>Please press New Round to continue.";
				if (flag) {
					moveCards();
					rules = "The winner of this round is: " + player[k].getName()+"<br>Please press New Round to continue.";
				}
			}
			round++;
			winner = player[k].getName();
			
			//only one player left , end of game
			if (playerNum() < 2) {
				if (end > 0) {
					rules = "The winner is: " + player[k].getName() + ". Please press Selection Page to play a new game.";
				}
				end++;
			}
			return rules;
		} catch (ArrayIndexOutOfBoundsException e) {
			return "";
		}
	}

	public void communalPile() {
		for (int i = 0; i < player.length; i++) {
			if (player[i].myCards.length != 0) {
				// put cards in communal pile
				communalPile.add(player[i].myCards[0]);
				player[i].myCards = ArrayUtils.remove(player[i].myCards, 0);
			}

		}
	}

	public void moveCards() {
		// move cards from communal pile to winner
		int size = communalPile.size();
		ArrayList<Cards> transfer = new ArrayList<>();
		int length = player[k].myCards.length;
		for (int l = 0; l < length; l++) {
			transfer.add(player[k].myCards[l]);
		}
		for (int j = 0; j < size; j++) {
			transfer.add(communalPile.get(0));
			communalPile.remove(0);

		}
		player[k].myCards = new Cards[transfer.size()];
		for (int i = 0; i < transfer.size(); i++) {
			player[k].myCards[i] = transfer.get(i);
		}
	}

	// AI will choose the largest number it has
	@GET
	@Path("/choiceAI")
	public String choiceAI() {
		int size = player[k].myCards[0].getSize();
		int speed = player[k].myCards[0].getSpeed();
		int range = player[k].myCards[0].getRange();
		int firepower = player[k].myCards[0].getFirepower();
		int cargo = player[k].myCards[0].getCargo();
		int[] number = { size, speed, range, firepower, cargo };

		// get the largest number among them
		int max = 0;
		int c = 0;
		for (int i = 0; i < 5; i++) {
			if (number[i] > max) {
				max = number[i];
				c = i;
			}
		}
		String choice = "";

		// the most logical category
		if (c < 1) {
			choice = "Size";
		} else if (c < 2) {
			choice = "Speed";
		} else if (c < 3) {
			choice = "Range";
		} else if (c < 4) {
			choice = "Firepower";
		} else {
			choice = "Cargo";
		}
		return choice;
	}

}
