import java.util.Formatter;
import java.util.HashMap;

public class Util {

    public static <T extends Comparable<T>> T max(T a, T b) {
	return a.compareTo(b) > 0 ? a : b;
    }

    public static String getDirectionFromPoints(HashMap<Integer, Integer> last, HashMap<Integer, Integer> current) {
	String direction = "";
	for (int i : last.keySet()) {
	    for (int j : current.keySet()) {
		if (i == j) {
		    if (last.get(i) == current.get(j)) {
			direction = "Stop";
		    } else if (last.get(i) > current.get(j)) {
			direction = "Left";
		    } else if (last.get(i) < current.get(j)) {
			direction = "Right";
		    }
		} else {
		    if (i < j) {
			direction = "Down";
		    } else {
			direction = "Up";
		    }
		}

	    }
	}
	return direction;

    }

    public static void printR(int[][][] arr) {
	Formatter formatter = new Formatter();
	for (int i = 0; i < arr[0].length; i++) {
	    formatter.format("\n");
	    for (int j = 0; j < arr[0].length; j++) {
		formatter.format("R from tile[%d][%d]", i, j);
		formatter.format("%" + ((arr[0].length * arr[0].length) - arr[0].length) + "s", "");
	    }
	    formatter.format("\n");
	    for (int x = 0; x < arr[0].length; x++) {
		for (int k = i * arr[0].length; k < i * arr[0].length + arr[0].length; k++) {
		    for (int j = 0; j < arr[0].length; j++) {
			formatter.format("%3s", arr[k][x][j]);
			formatter.format("%4s", "");
		    }
		    formatter.format("|");
		}
		formatter.format("\n");

	    }

	}
	System.out.println(formatter.toString());
	formatter.close();
    }

    public static void printQ(double[][][] arr) {
	Formatter formatter = new Formatter();
	for (int i = 0; i < arr[0].length; i++) {
	    formatter.format("\n");
	    for (int j = 0; j < arr[0].length; j++) {
		formatter.format("Q from tile[%d][%d]", i, j);
		formatter.format("%" + ((arr[0].length * arr[0].length) + arr[0].length * arr[0].length) + "s", "");
	    }
	    formatter.format("\n");
	    for (int x = 0; x < arr[0].length; x++) {
		for (int k = i * arr[0].length; k < i * arr[0].length + arr[0].length; k++) {
		    for (int j = 0; j < arr[0].length; j++) {
			formatter.format("%6.2f", arr[k][x][j]);
			formatter.format("%4s", "");
		    }
		    formatter.format("|");
		}
		formatter.format("\n");

	    }

	}
	System.out.println(formatter.toString());
	formatter.close();
    }
}
