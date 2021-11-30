import java.util.Iterator;

public class Qlearning {
    private final double l_rate = 0.1; // learning rate
    private final double dc_factor = 0.95;

    private final int reward = 50;
    private final int penalty = -10;

    private Maze map;
    private int[][] R;
    private double[][] Q;
    private int itr = 0;

    public Qlearning(int states, Maze map) {
	R = new int[states][states];
	Q = new double[states][states];
	this.map = map;

	// default reward matrix == -1
	for (int i = 0; i < R.length; i++) {
	    R[itr][i] = -1;
	}
    }

    private void move() {
	if (map.validMove("right")) {
	    map.moveRight();
	    int target = map.getCurrent_column();
	    if (target == 2) {
		R[itr][target] = reward;
	    }
	    if (target == 3) {
		R[itr][target] = penalty;
	    } else {
		R[itr][target] = 0;
	    }
	}
	if (map.validMove("left")) {
	    map.moveLeft();
	}
	if (map.validMove("up")) {
	    map.moveUp();
	}
	if (map.validMove("down")) {
	    map.moveDown();
	}
    }
}
