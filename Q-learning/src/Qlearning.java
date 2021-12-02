import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Qlearning {
    private final double l_rate = 0.1; // learning rate
    private final double dc_factor = 0.95;// discount_factor
    private final int reward = 50;
    private final int penalty = -10;
    private final int states;
    private final int width;
    private final int height;
    private Maze map;
    private int[][][] R;
    private double[][][] Q;

    public Qlearning(Maze map) {
	int states = map.getStates();
	width = map.getMazeWidth();
	height = map.getMazeHeight();
	R = new int[states][height][width];
	Q = new double[states][height][width];
	this.states = states;
	this.map = map;

	mapR();
    }

    /**
     * Set every tile on every map to -1
     */
    private void initR() {
	for (int k = 0; k < states; k++) {
	    for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
		    R[k][i][j] = -1;
		}
	    }
	}

    }

    private void initQ() {
	for (int k = 0; k < states; k++) {
	    for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
		    Q[k][i][j] = (double) R[k][i][j];
		}
	    }
	}
    }

    /**
     * Calculate R for every tile
     */
    private void mapR() {
	initR();
	int[][] temp = map.getMap();
	int k = -1;
	for (int i = 0; i < temp.length; i++) {
	    for (int j = 0; j < temp[0].length; j++) {
		k++;

		if (temp[i][j] != 2) {
		    int left = j - 1;
		    if (left >= 0) {
			if (temp[i][left] == 0 || temp[i][left] == 1) {
			    R[k][i][left] = 0;
			} else if (temp[i][left] == 2) {
			    R[k][i][left] = reward;
			} else {
			    R[k][i][left] = penalty;
			}
		    }

		    int right = j + 1;
		    if (right < temp[0].length) {
			if (temp[i][right] == 0 || temp[i][right] == 1) {
			    R[k][i][right] = 0;
			} else if (temp[i][right] == 2) {
			    R[k][i][right] = reward;
			} else {
			    R[k][i][right] = penalty;
			}
		    }
		    int down = i + 1;
		    if (down < temp.length) {
			if (temp[down][j] == 0 || temp[down][j] == 1) {
			    R[k][down][j] = 0;
			} else if (temp[down][j] == 2) {
			    R[k][down][j] = reward;
			} else {
			    R[k][down][j] = penalty;
			}
		    }
		    int up = i - 1;
		    if (up >= 0) {
			if (temp[up][j] == 0 || temp[up][j] == 1) {
			    R[k][up][j] = 0;
			} else if (temp[up][j] == 2) {
			    R[k][up][j] = reward;
			} else {
			    R[k][up][j] = penalty;
			}
		    }

		}
	    }
	}
    }

    public void train(int epoch) {
	initQ();
	Random random = new Random();
	for (int i = 0; i < epoch; i++) {
	    int current_state = random.nextInt(states);
	    while (!map.isGoalfound(current_state)) {
		var validActions = validActions(current_state);
		
		// look randomly for valid state
		var keysAsArray = new ArrayList<>(validActions.keySet());
		var rndRow = keysAsArray.get(random.nextInt(keysAsArray.size()));

		var rndColumn = validActions.get(rndRow);
		double current_Q = Q[current_state][rndRow][rndColumn];
		var s = getStateFromLoc(rndRow, rndColumn);
		double maxQ = bestQValue(getStateFromLoc(rndRow, rndColumn));

		int actionReward = R[current_state][rndRow][rndColumn];

		double new_Q = current_Q + l_rate * (actionReward + dc_factor * maxQ - current_Q);
		Q[current_state][rndRow][rndColumn] = new_Q; // update Q

		current_state = getStateFromLoc(rndRow, rndColumn); // move to next state

	    }
	    System.out.println("Epoch#" + i);
	}
    }

    private int getStateFromLoc(int row, int column) {
	return column + row * width;
    }

    /**
     * 
     * @return valid actions from a given state
     */
    private HashMap<Integer, Integer> validActions(int state) {
	HashMap<Integer, Integer> valid = new HashMap<Integer, Integer>();
	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		if (R[state][i][j] != -1) {
		    valid.put(i, j);
		}
	    }
	}
	return valid;
    }

    /**
     * @return best expected value from the valid actions
     */
    private double bestQValue(int state) {
	var validActions = validActions(state);
	double currentMax = -10;

	// check every valid action
	for (var action : validActions.keySet()) {
	    double value = Q[state][action][validActions.get(action)];
	    currentMax = Util.max(value, currentMax);
	}
	return currentMax;

    }

    public void printR() {
	Util.printR(R);
    }

    public void printQ() {
	Util.printQ(Q);
    }

    public void printP() {
	String direction = "";
	for (int i = 0; i < states; i++) {
	    var last = getLocFromState(i);
	    var current = policies(i);
	    var s = Util.getDirectionFromPoints(last, current);
	    System.out.println("From state " + i + " go " + s);
	}
    }

    public String directAgent(int row, int column) {
	int current_state = getStateFromLoc(row, column);
	var current = getLocFromState(current_state);
	var destination = policies(current_state);

	String direction = Util.getDirectionFromPoints(current, destination);
	return direction;
    }

    private HashMap<Integer, Integer> getLocFromState(int state) {
	int row = state / height;
	int column = state % width;
	var temp = new HashMap<Integer, Integer>();
	temp.put(row, column);
	return temp;
    }

    public HashMap<Integer, Integer> policies(int state) {
	var validActions = validActions(state);
	double max = Double.MIN_VALUE;
	var target = new HashMap<Integer, Integer>();
	target = getLocFromState(state);
	for (var action : validActions.keySet()) {
	    double decision = Q[state][action][validActions.get(action)];

	    if (decision > max) {
		max = decision;
		target.clear();
		target.put(action, validActions.get(action));
	    }
	}
	return target;
    }

}
