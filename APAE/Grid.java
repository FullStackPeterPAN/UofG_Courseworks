import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Grid implements Runnable {

	// variable rows and columns
	private int rows;
	private int columns;
	String[][] grid;
	int s = SeperateMain.s; // static number for different main class

	// lock and condition to avoid moving for same cells
	ReentrantLock lockCell = new ReentrantLock();
	Condition cellFull = lockCell.newCondition();
	
	// car numbers of each direction
	int carNumZX = 80;
	int carNumXZ = 80 * (s - 1);
	int carNumYZ = 80;
	int carNumZY = 80 * (s - 1);
	int carNum = carNumZX + carNumXZ + carNumYZ + carNumZY; // total car number, easier to use

	// car arrays
	CarZeroToX[] carZX = new CarZeroToX[carNumZX];
	CarXToZero[] carXZ = new CarXToZero[carNumXZ];
	CarYToZero[] carYZ = new CarYToZero[carNumYZ];
	CarZeroToY[] carZY = new CarZeroToY[carNumZY];
	Car[] car = new Car[carNum]; // one car to rule them all

	public Grid(int r, int c) {

		// set rows and columns
		this.rows = r;
		this.columns = c;
	}

	// get methods
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getCarNumZX() {
		return carNumZX;
	}

	public int getCarNumYZ() {
		return carNumYZ;
	}

	public int getCarNumZY() {
		return carNumZY;
	}

	public int getCarNumXZ() {
		return carNumYZ;
	}

	public int getCarNum() {
		return carNum;
	}

	public String[][] getGrid() {
		return grid;
	}

	// relate to cars in main class
	public void relateCar(Car[] car) {
		this.car = car;
	}

	// method to start new car thread
	public void threadCar(Thread[] t) {
		for (int i = 0; i < carNum; i++) {
			t[i] = new Thread(car[i]);
		}
	}

	public void drawGrid() {

		// use "|" to create grids
		for (int i = 0; i < rows; i++) {

			// draw each line
			for (int j = 0; j < (2 * columns); j = j + 2) {
				grid[i][j] = "|";
				grid[i][j + 1] = " ";
			}
		}
	}

	public void showGrid() {

		// new grid array
		grid = new String[rows][2 * columns + 1];
		drawGrid();

		// new car thread
		Thread[] t = new Thread[carNum];

		// put car in car thread
		threadCar(t);

		// get the car from west to east on grid
		for (int i = 0; i < carNumZX; i++) {
			if (car[i].getY() < rows / s && car[i].getX() < 2 * columns) {
				grid[car[i].getY()][car[i].getX()] = car[i].getShape();
			}
		}

		// get the car from east to west on grid
		for (int i = 0; i < carNumXZ; i++) {
			if (car[i + carNumZX].getY() < rows 
					&& car[i + carNumZX].getY() > (rows / s) - 1
					&& car[i + carNumZX].getX() < 2 * columns 
					&& car[i + carNumZX].getX() > 0) {
				grid[car[i + carNumZX].getY()][car[i + carNumZX].getX()] 
						= car[i + carNumZX].getShape();
			}
		}

		// check Y and move
		for (int i = 0; i < carNumZY + carNumYZ; i++) {
			lockCell.lock();
			try {

				car[i + carNumZX + carNumXZ].movement();
				t[i + carNumZX + carNumXZ].start();
				while(car[i + carNumZX + carNumXZ].move == false) {
					cellFull.await();
				}
				Thread.sleep(1);
			} catch (InterruptedException e) {

			} finally {
				lockCell.unlock();
			}
		}

		// get the car from north to south on grid
		for (int i = 0; i < carNumYZ; i++) {
			if (car[i + carNumZX + carNumXZ].getX() < (s - 1) + 2 * columns / s
					&& car[i + carNumZX + carNumXZ].getY() < rows) {
				if (car[i + carNumZX + carNumXZ].getX() % 2 == 1) { // no car can be on the lines
					grid[car[i + carNumZX + carNumXZ].getY()][car[i + carNumZX + carNumXZ].getX()] 
							= car[i + carNumZX + carNumXZ].getShape();
				}
			}
		}

		// get the car from south to north on grid
		for (int i = 0; i < carNumZY; i++) {
			if (car[i + carNum - carNumZY].getX() < 2 * columns
					&& car[i + carNum - carNumZY].getX() > (1 - s) + 2 * columns / s
					&& car[i + carNum - carNumZY].getY() < rows) {
				if (car[i + carNum - carNumZY].getX() % 2 == 1 
						&& car[i + carNum - carNumZY].getY() > 0) { // no car can be on the lines
					grid[car[i + carNum - carNumZY].getY()][car[i + carNum - carNumZY].getX()]
							= car[i + carNum - carNumZY].getShape();
				} else if (car[i + carNum - carNumZY].getX() % 2 == 1 
						&& car[i + carNum - carNumZY].getY() == 0) {
					grid[car[i + carNum - carNumZY].getY()][car[i + carNum - carNumZY].getX()]
							= car[i + carNum - carNumZY].getShape();
				}
			}
		}

		// check X and move
		for (int i = 0; i < carNumZX + carNumXZ; i++) {
			lockCell.lock();
			try {
				car[i].movement();
				t[i].start();
				while(car[i].move == false) {
					cellFull.await();
				}
				Thread.sleep(1);
			} catch (InterruptedException e) {

			} finally {
				lockCell.unlock();
			}
		}

		// check if same direction has car on next cell
		for (int i = 0; i < carNumZY + carNumYZ; i++) {
			car[i + carNumZX + carNumXZ].checkSameDirection();
		}
		
		// start draw thread
		Draw d = new Draw(grid, rows, columns);
		Thread draw = new Thread(d);
		draw.start();

	}
	
	public void run() {

		// the first line
		for (int i = 0; i < (2 * columns + 1); i++) {
			System.out.print("-");
		}

		// draw 2000 times
		for (int i = 0; i < 2000; i++) {
			showGrid();
		}

		if (s == 2) {
			// generate report after draw 2000 times
			StaticReport.generateReport();
		}
	}

}