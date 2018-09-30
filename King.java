
/**
 * Write a description of class bKing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class King extends Piece
{
    public King(String newTeam, int[] location, Board theBoard)
    {
        //we can instantiate variables in the parent abstract class, Piece
        team = newTeam;
        pieceType = "King";
        currentLocation = location;
        board = theBoard;
    }
    
    public static void move(int l[])
    {
        
    }
    
    public void capture()
    {
        if(captured == true && team.equals("White"))
            System.out.println("\n\n\n\n\n\n\n\n Black team wins!\n\n\n\n\n\n\n\n\n\n");
        else if(captured == true && team.equals("Black"))
            System.out.println("\n\n\n\n\n\n\n\n White team wins!\n\n\n\n\n\n\n\n\n\n");
    }
    
    public String getTeam()
    {
        return team;
    }
    
    public void captured()
    {
        
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
        int dx = i - currentLocation[0];
        int dy = j - currentLocation[1]; //switch        
        
        if(((dx * dx + dy * dy) == 1 || (dx * dx + dy * dy) == 2) && i >= 0)
        {
            return true;
        }
        else
            return false;
    }
    
    public String getName()
    {
        return pieceType;
    }
}
