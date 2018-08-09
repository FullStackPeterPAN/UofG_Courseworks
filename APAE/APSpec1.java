public class APSpec1 {

	public static void main(String[] args) {
		SeperateMain sep = new SeperateMain(1); // all lanes available
		Grid grid = new Grid(10, 20);

		// create the first cars to start
		// car 3 and 4 is not going to be used, but have to be created to keep it right
		CarZeroToX[] car1 = new CarZeroToX[grid.getCarNumZX()];
		CarYToZero[] car2 = new CarYToZero[grid.getCarNumYZ()];
		CarZeroToY[] car3 = new CarZeroToY[grid.getCarNumZY()];
		CarXToZero[] car4 = new CarXToZero[grid.getCarNumXZ()];
		Car[] car = new Car[grid.getCarNum()]; // one car to rule them all

		// random location of cars
		int[] car1Y = new int[grid.getCarNumZX()];
		int[] car2X = new int[grid.getCarNumYZ()];

		// check the cross if it is occupied
		boolean Cross1 = false;

		// add car location and speed
		for (int i = 0; i < grid.getCarNumZX(); i++) {

			// get a random location
			car1Y[i] = (int) (Math.random() * grid.getRows());

			// abandon it if it is occupied
			for (int j = 0; j < i; j++) {
				if (car1Y[i] == car1Y[j]) {
					car1Y[i] = 666;
					break; // no need to go on
				} else {

				}
			}

			// check the cross
			if (car1Y[i] == 0) {
				Cross1 = true;
			}

			// start first cars
			car1[i] = new CarZeroToX(1, car1Y[i], "-", (int) (Math.random() * 2) + 1, grid);
		}

		for (int i = 0; i < grid.getCarNumYZ(); i++) {

			// a random location
			car2X[i] = ((int) (Math.random() * grid.getColumns())) * 3 + 1;

			// check if it is cross
			while (Cross1 == true && car2X[i] == 1) {
				car2X[i] = 666;
			}

			// abandon occupied cells
			for (int j = 0; j < i; j++) {
				if (car2X[i] == car2X[j]) {
					car2X[i] = 666;
					break; // no need to go on
				} else {

				}
			}
			car2[i] = new CarYToZero(car2X[i], 0, "o", (int) (Math.random() * 2) + 1, grid);
		}

		// put every car in one array
		for (int i = 0; i < grid.getCarNumZX(); i++) {
			car[i] = car1[i];
		}
		for (int i = 0; i < grid.getCarNumYZ(); i++) {
			car[i + grid.getCarNumZX()] = car2[i];
		}

		// relate the first cars in main method to cars created in grid class
		grid.relateCar(car);

		// start grid
		Thread g = new Thread(grid);
		g.start();
	}

}
