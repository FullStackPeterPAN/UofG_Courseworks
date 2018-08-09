package online.dwResources;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Cards {

	private String Description = "";
	private int Size = 0;
	private int Speed = 0;
	private int Range = 0;
	private int Firepower = 0;
	private int Cargo = 0;

	// define category
	public Cards(String description, int size, int speed, int range, int firepower, int cargo) {
		Description = description;
		Size = size;
		Speed = speed;
		Range = range;
		Firepower = firepower;
		Cargo = cargo;
	}
	
	// getter
	public String getDescription() {
		return Description;
	}

	public int getSize() {
		return Size;
	}

	public int getSpeed() {
		return Speed;
	}

	public int getRange() {
		return Range;
	}

	public int getFirepower() {
		return Firepower;
	}

	public int getCargo() {
		return Cargo;
	}
	
	public Cards get(Cards[] cards, int index)
	{
	    return cards[index];
	}

}
