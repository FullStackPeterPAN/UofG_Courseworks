package commandline;

import java.sql.*;

public class Database {

	private Connection connection = null;

	// connection
	public void connect(String dbname, String username, String password) {
		dbname = "m_17_2294163p";
		username = "m_17_2294163p";
		password = "2294163p";
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + dbname, username,
					password);
		} catch (SQLException e) {
			System.err.println("Connection Failed!");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			System.out.println("\nDatabase Connection successful\n");
		} else {
			System.err.println("Failed to make connection!");
		}
	}

	// close
	public void close() {
		try {
			connection.close();
			System.out.println("\nDatabase Connection closed\n");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Connection could not be closed � SQL exception");
		}
	}

	// add info to database game
	public void insertGame(int ID, String winner, int draws, int rounds) {
		Statement stmt = null;

		// insert
		String query = " INSERT INTO top_trump_team_project.game VALUES('" + ID + "' , '" + winner + "', '" + draws
				+ "' ,'" + rounds + "');";
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(query);

		}

		// catch errors
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
	}

	// update info to database winner
	public void updateWinner(int playerWin, int AI1Win, int AI2Win, int AI3Win, int AI4Win) {
		int count = 0;
		Statement stmt1 = null;

		// insert
		String query1 = "SELECT COUNT(name) FROM top_trump_team_project.winner;";
		try {
			stmt1 = connection.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);
			while (rs1.next()) {
				String c = rs1.getString("count");
				count = Integer.parseInt(c);
			}
		}

		// catch errors
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (count > 3) {
			Statement stmt2 = null;

			// insert
			String query2 = " UPDATE top_trump_team_project.winner SET win=" + "win+" + playerWin
					+ " WHERE name = 'Player';" + " UPDATE top_trump_team_project.winner SET win=" + "win+" + AI1Win
					+ " WHERE name = 'AI1';" + " UPDATE top_trump_team_project.winner SET win=" + "win+" + AI2Win
					+ " WHERE name = 'AI2';" + " UPDATE top_trump_team_project.winner SET win=" + "win+" + AI3Win
					+ " WHERE name = 'AI3';" + " UPDATE top_trump_team_project.winner SET win=" + "win+" + AI4Win
					+ " WHERE name = 'AI4';";
			try {
				stmt2 = connection.createStatement();
				stmt2.executeUpdate(query2);
			}

			// catch errors
			catch (SQLException e) {
				e.printStackTrace();
				System.err.println("error executing query " + query2);
			}
		}
		else {
			Statement stmt2 = null;

			// insert
			String query2 = "INSERT INTO top_trump_team_project.winner VALUES('Player','" + playerWin+ "');"
					+ "INSERT INTO top_trump_team_project.winner VALUES('AI1','" + AI1Win+ "');"
					+ "INSERT INTO top_trump_team_project.winner VALUES('AI2','" + AI2Win+ "');"
					+ "INSERT INTO top_trump_team_project.winner VALUES('AI3','" + AI3Win+ "');"
					+ "INSERT INTO top_trump_team_project.winner VALUES('AI4','" + AI4Win+ "');";
			try {
				stmt2 = connection.createStatement();
				stmt2.executeUpdate(query2);
			}

			// catch errors
			catch (SQLException e) {
				e.printStackTrace();
				System.err.println("error executing query " + query2);
			}
		}
	}

	// count over all games
	public int countOverAll() {
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
		return Integer.parseInt(count);
	}

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

	// count draws
	public double countDraws() {
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
		return draws;
	}

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

}
