package commandline;

import java.util.*;

public class Players {
	Cards[] myCards;
	private String playerName;

	public Players(String name, int numCard) {
		playerName = name;
		myCards = new Cards[numCard];

	}

	// set card method
	public void setMyCards(int index, Cards cards) {
		myCards[index] = cards;
	}

	// return the player name
	public String getName() {
		return playerName;
	}

	// show the cards of players
	public String showMyCards() {
		String showcard = playerName + "==>";
		for (int i = 0; i < myCards.length; i++) {
			showcard += " " + myCards[i].getDescription(); // display cards names
		}
		return showcard;
	}

}
