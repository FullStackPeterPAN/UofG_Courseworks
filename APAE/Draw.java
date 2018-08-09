
public class Draw implements Runnable {
	String[][] grid;
	int rows, columns;

	public Draw(String[][] grid, int rows, int columns) {
		this.grid = grid;
		this.rows = rows;
		this.columns = columns;
	}

	public void drawThread() {
		try {
			// print out the grid
			System.out.println();
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < 2 * columns; j++) {
					System.out.print(grid[i][j]);
				}
				// new line
				System.out.print("|");
				System.out.println();
			}

			// the last line
			for (int i = 0; i < (2 * columns + 1); i++) {
				System.out.print("-");
			}
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		// draw every 20 milliseconds
		try {
			drawThread();
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
