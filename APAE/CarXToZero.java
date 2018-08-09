public class CarXToZero extends Car {

	public CarXToZero(int x, int y, String shape, int speed, Grid grid) {
		super(x, y, shape, speed, grid);
	}

	// move the car
	public void movement() {
		move = false; // start move
		count++; // every time count + 1

		// when count equals speed and the next cell is empty, move
		if (count == speed) {
			checkXZEmpty();
			if (empty == true) {

				// move
				x = x - 2;
			}

			// start a new count
			count = 0;
		}
		move = true; // end move
	}

	// check the next cell if it is empty for XZ cars
	public void checkXZEmpty() {
		for (int i = 0; i < g.getCarNumXZ(); i++) {
			if (y < g.getRows() && y > g.getRows() / s - 1 && x < (g.getColumns() * 2) && x > 1) { // avoid null

				// set true if empty
				if (g.grid[y][x - 2] == " ") {
					empty = true;
				} else {
					empty = false;
				}
			}

			// the last one must be true
			if (x == 1) {
				empty = true;
			}
		}
	}

	public void run() {

		// record start time for visible cars
		if (x == 39 && y > (g.getRows() / s) - 1 && y < g.getRows()) {
			start = System.currentTimeMillis();
		}

		// reset when fall
		if (x < 1) {

			// record end time and calculate for visible cars
			if (y > (g.getRows() / s) - 1 && y < g.getRows() && start != 0) {
				end = System.currentTimeMillis();
				StaticReport.recordXZ(end - start);
			}
			speed = (int) (Math.random() * 2) + 1;
			x = g.getColumns() * 2 - 1;
			y = (int) (Math.random() * (g.getRows() + 5)); // a larger number to give a random car number

			// make sure the first location won't crush
			while (y < g.getRows() && g.getGrid()[y][g.getColumns() - 1] != " ") {
				y = (int) (Math.random() * (g.getRows() + 5)); // a larger number to give a random car number
			}
		}
	}

}
