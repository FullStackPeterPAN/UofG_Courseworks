import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StaticReport {
	static int countXZ = 0;
	static long minXZ = 0;
	static long maxXZ = 0;
	static long meanXZ = 0;
	static long allXZ = 0;
	static int countZX = 0;
	static long minZX = 0;
	static long maxZX = 0;
	static long meanZX = 0;
	static long allZX = 0;
	static int countYZ = 0;
	static long minYZ = 0;
	static long maxYZ = 0;
	static long meanYZ = 0;
	static long allYZ = 0;
	static int countZY = 0;
	static long minZY = 0;
	static long maxZY = 0;
	static long meanZY = 0;
	static long allZY = 0;

	public static void recordXZ(long time) {
		if (time < minXZ) {
			minXZ = time; //get minimum time
		} else if (time > maxXZ) {
			maxXZ = time; // get maximum time
		}
		
		// record the first time for minimum
		if (minXZ == 0) {
			minXZ = time;
		}
		
		//record all and count for mean at last
		allXZ = allXZ + time;
		countXZ++;
	}
	
	public static void recordZX(long time) {
		if (time < minZX) {
			minZX = time; //get minimum time
		} else if (time > maxZX) {
			maxZX = time; // get maximum time
		}
		
		// record the first time for minimum
		if (minZX == 0) {
			minZX = time;
		}
		
		//record all and count for mean at last
		allZX = allZX + time;
		countZX++;
	}
	
	public static void recordYZ(long time) {
		if (time < minYZ) {
			minYZ = time; //get minimum time
		} else if (time > maxYZ) {
			maxYZ = time; // get maximum time
		}
		
		// record the first time for minimum
		if (minYZ == 0) {
			minYZ = time;
		}
		
		//record all and count for mean at last
		allYZ = allYZ + time;
		countYZ++;
	}
	
	public static void recordZY(long time) {
		if (time < minZY) {
			minZY = time; //get minimum time
		} else if (time > maxZY) {
			maxZY = time; // get maximum time
		}
		
		// record the first time for minimum
		if (minZY == 0) {
			minZY = time;
		}
		
		//record all and count for mean at last
		allZY = allZY + time;
		countZY++;
	}

	public static void generateReport() {
		String title = "REPORT: ";
		String gZX = "	Generator ZX: \r\n		Max: " + maxZX + "\r\n		Min: " + minZX + "\r\n		Ave: " + allZX / countZX;
		String gXZ = "	Generator XZ: \r\n		Max: " + maxXZ + "\r\n		Min: " + minXZ + "\r\n		Ave: " + allXZ / countXZ;
		String gYZ = "	Generator YZ: \r\n		Max: " + maxYZ + "\r\n		Min: " + minYZ + "\r\n		Ave: " + allYZ / countYZ;
		String gZY = "	Generator ZY: \r\n		Max: " + maxZY + "\r\n		Min: " + minZY + "\r\n		Ave: " + allZY / countZY;
		FileWriter fw;
		try {
			fw = new FileWriter("report.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(title + "\r\n");
			bw.append(gZX + "\r\n");
			bw.append(gXZ + "\r\n");
			bw.append(gYZ + "\r\n");
			bw.append(gZY);
			bw.close();
			fw.close();
		} catch (IOException e) {
		}
	}
}