public class Main {
    public static void main(String[] args) {
	Maze maze = new Maze(4, 4);
	maze.setTrap(1, 0);
	maze.setGoal(2, 2);
	Qlearning model = new Qlearning(maze, 1000);


    }
}
