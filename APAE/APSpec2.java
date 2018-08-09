public class APSpec2 {

	public static void main(String[] args) {
		SeperateMain sep = new SeperateMain(2); // half lanes available
		Grid grid = new Grid(10, 20);

		// create the first cars to start
		CarZeroToX[] car1 = new CarZeroToX[grid.getCarNumZX()];
		CarYToZero[] car2 = new CarYToZero[grid.getCarNumYZ()];
		CarZeroToY[] car3 = new CarZeroToY[grid.getCarNumZY()];
		CarXToZero[] car4 = new CarXToZero[grid.getCarNumXZ()];
		Car[] car = new Car[grid.getCarNum()]; // one car to rule them all

		// random location of cars
		int[] car1Y = new int[grid.getCarNumZX()];
		int[] car2X = new int[grid.getCarNumYZ()];
		int[] car3X = new int[grid.getCarNumZY()];
		int[] car4Y = new int[grid.getCarNumXZ()];

		// check two crosses if occupied
		boolean Cross1 = false;
		boolean Cross2 = false;

		// add car location and speed
		for (int i = 0; i < grid.getCarNumZX(); i++) {

			// a random location
			car1Y[i] = (int) (Math.random() * grid.getRows());

			// abandon the car if occupied
			for (int j = 0; j < i; j++) {
				if (car1Y[i] == car1Y[j] && i != j) {
					car1Y[i] = 666;
					break; // no need to go on
				}
			}

			// check if cross occupied
			if (car1Y[i] == 0) {
				Cross1 = true;
			}
			car1[i] = new CarZeroToX(1, car1Y[i], "-", (int) (Math.random() * 2) + 1, grid);
		}

		for (int i = 0; i < grid.getCarNumYZ(); i++) {

			// random location
			car2X[i] = ((int) (Math.random() * grid.getColumns())) * 2 + 1;
			while (Cross1 == true && car2X[i] == 1) {
				car2X[i] = 666;
			}

			// abandon if occupied
			for (int j = 0; j < i; j++) {
				if (car2X[i] == car2X[j]) {
					car2X[i] = 666;
					break; // no need to go on
				} else {

				}
			}
			car2[i] = new CarYToZero(car2X[i], 0, "o", (int) (Math.random() * 3) + 1, grid);
		}

		for (int i = 0; i < grid.getCarNumZY(); i++) {

			// random location
			car3X[i] = ((int) (Math.random() * grid.getColumns())) * 2 + 1;

			// abandon if occupied
			for (int j = 0; j < i; j++) {
				if (car3X[i] == car3X[j]) {
					car3X[i] = 666;
					break; // no need to go on
				} else {

				}
			}

			// check if cross occupied
			if (car3X[i] == grid.getColumns() - 1) {
				Cross2 = true;
			}
			car3[i] = new CarZeroToY(car3X[i], grid.getRows() - 1, "o", (int) (Math.random() * 3) + 1, grid);
		}

		for (int i = 0; i < grid.getCarNumXZ(); i++) {

			// random location
			car4Y[i] = (int) (Math.random() * grid.getRows());

			// check cross
			while (Cross2 == true && car4Y[i] == grid.getRows() - 1) {
				car4Y[i] = 666;
			}

			// abandon if occupied
			for (int j = 0; j < i; j++) {
				if (car4Y[i] == car4Y[j] && i != j) {
					car4Y[i] = 666;
					break; // no need to go on
				} else {

				}
			}
			car4[i] = new CarXToZero(grid.getColumns() * 2 - 1, car4Y[i], "-", (int) (Math.random() * 2) + 1, grid);
		}

		// put every car in one array
		for (int i = 0; i < grid.getCarNumZX(); i++) {
			car[i] = car1[i];
		}
		for (int i = 0; i < grid.getCarNumXZ(); i++) {
			car[i + grid.getCarNumZX()] = car4[i];
		}
		for (int i = 0; i < grid.getCarNumYZ(); i++) {
			car[i + grid.getCarNumZX() + grid.getCarNumXZ()] = car2[i];
		}
		for (int i = 0; i < grid.getCarNumZY(); i++) {
			car[i + grid.getCarNum() - grid.getCarNumZY()] = car3[i];
		}
		// relate the first cars in main method to cars created in grid class
		grid.relateCar(car);

		// start grid
		Thread g = new Thread(grid);
		g.start();
	}

}