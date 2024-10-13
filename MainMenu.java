import java.util.*;

public class MainMenu 
{
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        //Intro
        System.out.println("Welcome to Conway's Game of Obstables!");
        System.out.println("This game is based on Conway's Game of Life.\n");

        //Rules
        System.out.println("Here, there is a grid of 'cells' that are either alive and dead.");
        System.out.println("Every time you make a move, the grid changes its state depending on its previous state.\n");

        System.out.println("The cells adhere to the following rules:");
        System.out.println("If a living cell has fewer than two or greater than three live neighbors, it dies.");
        System.out.println("If a living cell has two or three live neighbors, it moves on to the next generation.");
        System.out.println("If a dead cell has exactly three neighbors, it becomes alive.\n");

        //Instructions
        System.out.println("The goal of this game is to reach the flag [F] as a player [i]");
        System.out.println("If you hit a living [X] cell on its next position, you die.");
        System.out.println("You move with wasd, you wait with space, you exit with e, and you input with enter.");
        System.out.println("You can exit the program any time.\n");

        System.out.println();
        
        int input = -2;
        while(input != - 1) 
        {
            try
            {
                System.out.println("Choose one from the following menu: ");
                System.out.println("1. Play Game\n2. Settings");

                input = scanner.nextInt();
                
                switch(input)
                {
                    case 1: playGame(); break;
                    case 2: settings(); break;
                    default: input = 0;
                }
            } 
            catch(Exception e)
            {
                System.out.println("Wrong input");
            }
        }
    }

    //Menu options
    public static void playGame()
    {
        System.out.println("What level would you like to play?");
        int input = scanner.nextInt();
        
        @SuppressWarnings("unused") Level level = new Level(input);

    }

    public static void settings()
    {
        
    }
}
