import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
	Agent agent = new Agent();
	Maze maze = new Maze(10,10);
	maze.addAgent(agent);
	maze.setStart(0, 0);
	maze.setTrap(1, 1);
	maze.setTrap(0, 1);
	maze.setTrap(2, 1);
	maze.setTrap(6, 5);
	maze.setTrap(0, 1);
	maze.setTrap(8, 9);
	maze.setTrap(9, 1);
	maze.setGoal(9, 0);
	System.out.println(maze);
	Qlearning model = new Qlearning(maze);
	model.train(500);
	String s = "";
	while (s != "Stop") {
	    s = model.directAgent(agent.getCurrent_row(), agent.getCurrent_column());
	    agent.move(s);
	    System.out.println(maze);
	    TimeUnit.MILLISECONDS.sleep(500);
	}

    }
}
