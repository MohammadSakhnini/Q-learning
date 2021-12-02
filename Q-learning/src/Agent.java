public class Agent {
    private int current_row, current_column, last_state = 0;
    private int[][] map;

    public void move(String direction) {
	switch (direction.toLowerCase()) {
	case "right": {
	    moveRight();
	    break;
	}
	case "left": {
	    moveLeft();
	    break;
	}
	case "up": {
	    moveUp();
	    break;
	}
	case "down": {
	    moveDown();
	    break;
	}
	case "stop": {
	    System.out.println("Goal reached!");
	}
	default:
	    break;
	}
    }

    private void moveRight() {
	clear_current();
	current_column += 1;
	save_current();
	map[current_row][current_column] = 1;

    }

    private void moveLeft() {
	clear_current();
	current_column -= 1;
	save_current();
	map[current_row][current_column] = 1;

    }

    private void moveDown() {
	clear_current();
	current_row += 1;
	save_current();
	map[current_row][current_column] = 1;

    }

    private void moveUp() {
	clear_current();
	current_row -= 1;
	save_current();
	map[current_row][current_column] = 1;

    }

    public int getCurrent_column() {
	return current_column;
    }

    public int getCurrent_row() {
	return current_row;
    }

    public void setCurrent_column(int current_column) {
	this.current_column = current_column;
    }

    public void setCurrent_row(int current_row) {
	this.current_row = current_row;
    }

    public void setMap(int[][] map) {
	this.map = map;
    }

    private void clear_current() {
	map[current_row][current_column] = 0;
    }

    private void save_current() {
	last_state = map[current_row][current_column];
    }

}
