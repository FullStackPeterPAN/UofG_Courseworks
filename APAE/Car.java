public abstract class Car implements Runnable {

	int x = 1;
	int y = 0;
	String shape = "";
	int speed = 0;
	Grid g;
	int s = SeperateMain.s; // easier to use

	int count = 0; // count the time when the car should move
	boolean empty = false; // check the next cell if empty
	boolean emptySame = false; // check the cell if has same direction car
	boolean move = false; // make sure each time only move once

	// long to record start/end time
	long start;
	long end;

	public Car(int x, int y, String shape, int speed, Grid grid) {
		this.x = x;
		this.y = y;
		this.shape = shape;
		this.speed = speed;
		this.g = grid;
	}

	// move the car
	public void movement() {

	}

	// the same get methods for every car
	// the beauty of abstract class is you just need to put them here
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getShape() {
		return shape;
	}

	public int getSpeed() {
		return speed;
	}

	public void checkSameDirection() {

	}

	public void run() {

	}

}