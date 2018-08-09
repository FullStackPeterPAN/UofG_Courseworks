public class CarZeroToY extends Car {

	public CarZeroToY(int x, int y, String shape, int speed, Grid grid) {
		super(x, y, shape, speed, grid);
	}

	// move the car
	public void movement() {
		move = false; // start move
		count++; // every time count + 1

		// when count equals speed and the next cell is empty, move
		if (count == speed) {
			checkZYEmpty();
			if (empty == true && emptySame == false) {

				// move
				y = y - 1;
			}

			// start a new count
			count = 0;
		}
		move = true; // end move
	}

	public void checkZYEmpty() {

		// get the car from north to south on grid
		for (int i = 0; i < g.getCarNumZY(); i++) {
			if (x < 2 * g.getColumns() && x > (2 - s) + g.getColumns() * 2 / s && y < g.getRows() && y > 0) {

				// set true if empty
				if (g.grid[y - 1][x] == " ") {
					empty = true;
				} else {
					empty = false;
				}
			}

			// the last one must be true
			if (y == 0) {
				empty = true;
			}
		}
	}

	// method to check next cell if has the same direction car
	public void checkSameDirection() {
		if (x < 2 * g.getColumns() && x > (2 - s) + g.getColumns() * 2 / s && y < g.getRows() && y > 0) {

			// set true if empty
			if (g.grid[y - 1][x] == "o") {
				emptySame = true;
			} else {
				emptySame = false;
			}
		}
	}

	public void run() {

		// record start time for visible cars
		if (y == g.getRows() - 1 && x < g.getColumns() && x > g.getColumns() / s) {
			start = System.currentTimeMillis();
		}

		// reset when fall
		if (y < 0) {
			// record end time and calculate for visible cars
			if (x < g.getColumns() && x > g.getColumns() / s && start != 0) {
				end = System.currentTimeMillis();
				StaticReport.recordZY(end - start);
			}
			speed = (int) (Math.random() * 2) + 1;
			y = g.getRows();
			x = ((int) (Math.random() * (g.getColumns() + 5))) * 2 + 1; // a larger number to give a random car
																		// number
			// make sure the first location won't crush
			while (x < (g.getColumns() * 2) && g.getGrid()[g.getRows() - 1][x] != " ") {
				x = ((int) (Math.random() * (g.getColumns() + 5))) * 2 + 1; // a larger number to give a random car
																			// number
			}
		}
	}

}