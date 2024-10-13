import java.util.*;

public class Level 
{
    //Global Variables
    private Grid grid;

    //Constructors
    public Level(int level)
    {
        switch(level)
        {
            case 1: levelOne(); break;
            case 2: levelTwo(); break;
            //case 3: levelThree(); break;
            default: break;
        }

        //Start game
        System.out.println("Move with wasd. Escape with e. Wait with space.\nThen input with enter.");
        Scanner scanner = new Scanner(System.in);
        String move = scanner.nextLine();

        while(!move.equals("e"))
        {
            //Update grid state
            //grid.frame();

            //Check move and move
            if(move.equals("w")) grid.move(move);
            else if(move.equals("a")) grid.move(move);
            else if(move.equals("s")) grid.move(move);
            else if(move.equals("d")) grid.move(move);
            else if(move.equals(" ")) grid.move(move);
            else System.out.println("INVALID INPUT"); 

            //Finish
            grid.printLevel();

            if(grid.findFlag() == false)
            {
                grid.printLevel();
                System.out.println("YOU WIN");
                move = "e";
            }
            else if(grid.findPlayer() >= 0)
            {
                move = scanner.nextLine();
            }
            else if(grid.findPlayer() == -1)
            {
                grid.printLevel();
                System.out.println("YOU DIED");
                move = "e";
            }
        }
    }

    //Levels
    public void levelOne() //Rotation and moving into an obstacle about to move
    {
        //Start setup
        grid = new Grid(3, 7);

        //Player
        grid.setType(1, 6, 2);

        //Obstacles
        grid.setType(1, 2, 1);
        grid.setType(1, 3, 1);
        grid.setType(1, 4, 1);

        //Flags
        grid.setType(1, 0, 3);

        //Finish setup
        grid.printLevel();
    }
    
    public void levelTwo() //Waiting
    {
        //Start setup
        grid = new Grid(9, 9);

        //Player
        grid.setType(4, 4, 2);

        //Obstacles
        grid.setType(3, 3, 1);
        grid.setType(5, 3, 1);
        grid.setType(3, 5, 1);
        grid.setType(5, 5, 1);

        grid.setType(4, 3, 1);
        grid.setType(4, 5, 1);
        grid.setType(3, 4, 1);
        grid.setType(5, 4, 1);

        //Flags
        grid.setType(0, 0, 3);

        // Finish setup
        grid.printLevel();
    }
}
