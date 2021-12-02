import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
	Agent agent = new Agent();
	Maze maze = new Maze(7,7);
	maze.addAgent(agent);
	maze.setStart(0, 0);
	maze.setTrap(1, 1);
	maze.setGoal(5, 6);
	System.out.println(maze);
	Qlearning model = new Qlearning(maze);
	model.train(10000);
	String s = "";
	while (s != "Stop") {
	    s = model.directAgent(agent.getCurrent_row(), agent.getCurrent_column());
	    agent.move(s);
	    System.out.println(maze);
	    TimeUnit.MILLISECONDS.sleep(500);
	}

    }
}
