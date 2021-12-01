public class Maze {

    private int mazeWidth;
    private int mazeHeight;
    private int states = 0;
    private int current_row, current_column, last_state = 0;
    private int[][] map;

    public Maze(int width, int height) {
	mazeWidth = width;
	mazeHeight = height;
	map = new int[height][width];
	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		map[i][j] = 0;
	    }
	}
	map[0][0] = 1;
    }

    public int getStates() {
	return mazeWidth * mazeHeight;
    }

    public void setStart(int row, int column) {
	clear_current();
	if (map[row][column] == 3) {
	    throw new RuntimeException("Can't start on a trap");
	}
	map[row][column] = 1;
	current_row = row;
	current_column = column;

    }

    private void clear_current() {
	map[current_row][current_column] = 0;
    }

    private void save_current() {
	last_state = map[current_row][current_column];
    }

    public void moveRight() {
	clear_current();
	current_column += 1;
	save_current();
	map[current_row][current_column] = 1;

    }

    public void moveLeft() {
	clear_current();
	current_column -= 1;
	save_current();
	map[current_row][current_column] = 1;

    }

    public void moveDown() {
	clear_current();
	current_row += 1;
	save_current();
	map[current_row][current_column] = 1;

    }

    public void moveUp() {
	clear_current();
	current_row -= 1;
	save_current();
	map[current_row][current_column] = 1;

    }

    public boolean validMove(String move) {
	if (move.toLowerCase() == "right") {
	    if (current_column + 1 < mazeWidth) {
		return true;
	    }
	} else if (move.toLowerCase() == "left") {
	    if (current_column > 0) {
		return true;
	    }
	} else if (move.toLowerCase() == "down") {
	    if (current_row + 1 < mazeHeight) {
		return true;
	    }

	} else if (move.toLowerCase() == "up") {
	    if (current_row > 0) {
		return true;
	    }

	} else {
	    throw new RuntimeException("Given string is not a defined move");
	}
	return false;

    }

    public void setTrap(int row, int column) {
	map[row][column] = 3;
    }

    public void setGoal(int row, int column) {
	map[row][column] = 2;
    }

    public int getCurrent_column() {
	return current_column;
    }

    public int getCurrent_row() {
	return current_row;
    }

    public int getLastState() {
	return last_state;
    }

    public boolean isFinalState() {
	if (last_state == 2) {
	    return true;
	}
	return false;
    }

    public boolean isGoalfound(int state) {
	int i = state / mazeWidth; // gets row from 2d to 1d
	int j = state - i * mazeWidth;// gets column from 2d to 1d
	return map[i][j] == 2;
    }

    public int getMazeHeight() {
	return mazeHeight;
    }

    public int getMazeWidth() {
	return mazeWidth;
    }

    public int[][] getMap() {
	return map;
    }

    public String toString() {
	StringBuilder builder = new StringBuilder();
	for (int i = 0; i < map.length; i++) {
	    for (int j = 0; j < map[0].length; j++) {
		builder.append(map[i][j] + " ");
	    }
	    builder.append("\n");
	}

	return builder.toString();
    }
}
