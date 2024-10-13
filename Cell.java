public class Cell 
{
    //Global variables
    private int type;

    public Cell(int type)
    {
        this.type = type;
    }

    //Getters and Setters
    public int getType()
    {
        //Type 0: [ ], 1: [X], 2: [i], 3:[F]
        return this.type;
    }
    public void setType(int type)
    {
        this.type = type;
    }
}
