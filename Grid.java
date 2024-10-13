public class Grid 
{
    //Global Variables
    private int sizeX, sizeY;
    private Cell[][] grid;

    @SuppressWarnings("unused") private static Cell player = new Cell(1);

    //Constructors
    public Grid(int x, int y)
    {
        //Declare variables
        this.sizeX = x; this.sizeY = y;
        this.grid = new Cell[sizeY][sizeX];

        //Start
        resetLevel();
    }

    //Methods
    public void resetLevel()
    {
        for(int i = 0; i < sizeY; i++)
        {
            for(int j = 0; j < sizeX; j++)
            {
                grid[i][j] = new Cell(0);
            }
        }
    }

    public void printLevel()
    {
        System.out.print("   ");
        for(int j = 0; j < sizeX; j++)
        {
            System.out.print(" " + j + " ");
        }
        System.out.println();
        for(int i = 0; i < sizeY; i++)
        {
            System.out.print(" " + i + " ");
            for(int j = 0; j < sizeX; j++)
            {
                switch(grid[i][j].getType())
                {
                    default: System.out.print("ERR"); break;
                    case 0:  System.out.print("[ ]"); break;
                    case 1:  System.out.print("[X]"); break;
                    case 2:  System.out.print("[i]"); break;
                    case 3:  System.out.print("[F]");

                }
            }
            System.out.println();
        }
    }

    public void move(String move)
    {
        //Find player
        int x = findPlayer() / 10;
        int y = findPlayer() % 10;

        //Please see bottom **
        frame();

        int newX = x;
        int newY = y;

        String check = "";

        //Check move
        if(move.equals("w")) {newX = x;     newY = y - 1;}
        if(move.equals("a")) {newX = x - 1; newY = y;}
        if(move.equals("s")) {newX = x;     newY = y + 1;}
        if(move.equals("d")) {newX = x + 1; newY = y;}
        if(move.equals(" ")) {newX = x;     newY = y;}

        check = check(newX, newY, move);

        int type = getType(x, y);
        
        //Finish
        if(check.equals("dead")) {setType(x, y, type); setType(newX, newY, 2);}
        if(check.equals("alive")) setType(x, y, 0);
        if(check.equals("player")) setType(x, y, 1);
        if(check.equals("flag")) {setType(x, y, 0); setType(newX, newY, 2);}
    }

    public void frame()
    {
        //Preparations
        int[][] temp = new int[getSizeY()][getSizeX()];
        for(int i = 0; i < getSizeY(); i++)
        {
            for(int j = 0; j < getSizeX(); j++)
            {
                temp[i][j] = grid[i][j].getType();
            }
        }
        
        //For each cell
        for(int i = 0; i < temp.length; i++)
        {
            for(int j = 0; j < temp[i].length; j++)
            {
                int count = 0;
                //Check all sides
                if(j > 0 && temp[i][j-1] == 1) count++; //Check left
                if(j < temp[i].length - 1 && temp[i][j+1] == 1) count++; //Check right
                if(i > 0 && temp[i-1][j] == 1) count++; //Check up
                if(i < temp.length - 1 && temp[i+1][j] == 1) count++; //Check down

                //Check all corners
                if(i > 0 && j > 0 && temp[i-1][j-1] == 1) count++; //Check top left
                if(i > 0 && j < temp[i].length - 1 && temp[i-1][j+1] == 1) count ++; //Check top right
                if(i < temp.length - 1 && j > 0 && temp[i+1][j-1] == 1) count++; //Check bottom left
                if(i < temp.length - 1 && j < temp[i].length - 1 && temp[i+1][j+1] == 1) count++; //Check bottom right

                //Set state
                if(temp[i][j] == 0 || temp[i][j] == 2) //Dead
                {
                    if(count == 3) //Revive
                    {
                        setType(j, i, 1);
                    }
                    else //Set/stay dead
                    {
                        setType(j, i, 0);
                    }
                }
                else if(temp[i][j] == 1) //Live cell
                {
                    if(count < 2) //Underpopulation
                    {
                        setType(j, i, 0);
                    }
                    else if(count > 3) //Overpopulation
                    {
                        setType(j, i, 0);
                    }
                }
            }
        }
    }
    
    public String check(int x, int y, String move)
    {
        //Check for border
        if(move.equals("w") && y == -1) return "border";
        if(move.equals("a") && x == -1) return "border";
        if(move.equals("s") && y == grid.length) return "border";
        if(move.equals("d") && x == grid[y].length) return "border"; 

        //Check for type
        if(grid[y][x].getType() == 0) return "dead";
        else if(grid[y][x].getType() == 1) return "alive";
        else if(grid[y][x].getType() == 2) return "player";
        else if(grid[y][x].getType() == 3) return "flag";
        else return "ERR";
    }

    public int findPlayer()
    {
        for(int i = 0; i < sizeY; i++)
        {
            for(int j = 0; j < sizeX; j++)
            {
                if(grid[i][j].getType() == 2)
                {
                    return j * 10 + i;
                }
            }
        }
        return -1;
    }
    public boolean findFlag()
    {
        for(int i = 0; i < sizeY; i++)
        {
            for(int j = 0; j < sizeX; j++)
            {
                if(grid[i][j].getType() == 3)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    //Getters/Setters
    public void setType(int x, int y, int type)
    {
        grid[y][x].setType(type);
    }
    public int getType(int x, int y)
    {
        return grid[y][x].getType();
    }

    public int getSizeX()
    {
        return this.sizeX;
    }
    public int getSizeY()
    {
        return this.sizeY;
    }
}

//** Frame is placed there in case the cell i want replaced where the player was becomes alive
