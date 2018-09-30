
/**
 * Write a description of class Piece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Piece
{
	//Throw in some common variables for all pieces
	protected String team; //Which side the piece is on, "Black" or "White" perhaps?
	protected int[] currentLocation; //current location
	protected String pieceType; //String value of type ("pawn", "bishop", etc)
	protected boolean captured;
	protected Board board;
	
    public void move(int i, int j)
    {
        currentLocation[0] = i;
        currentLocation[1] = j;
    }
    
    public void capture()
    {
        currentLocation[0] = -1;
        currentLocation[1] = -1;
        captured = true;
    }
    
    public String getTeam()
    {
        return team;
    }
    
    public int[] getLocation()
    {
        return currentLocation;
    }
	
	public void setLocation(int[] newLocation)
	{
		currentLocation = newLocation;
	}
    
    public boolean validMove(int i, int j)
    {
        return true;
    }
    
    public String getName()
    {
        return pieceType;
    }
}
