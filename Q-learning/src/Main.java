public class Main {
    public static void main(String[] args) {
	Maze maze = new Maze(4, 4);
	maze.setTrap(1, 2);
	maze.setGoal(3, 3);
	System.out.println(maze);
	System.out.println(maze.validMove("down"));
	System.out.println(maze);

    }
}
