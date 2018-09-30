
/**
 * Write a description of class wKnight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Piece
{
    public Knight(String newTeam, int[] location, Board theBoard)
    {
        //we can instantiate variables in the parent abstract class, Piece
        team = newTeam;
        pieceType = "Knight";
        currentLocation = location;
        board = theBoard;
    }
    
    public static void move(int l[])
    {
        
    }
    
    public void capture()
    {
        
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
        /* boolean valid_knight_move(xold, yold, xnew, ynew) {
        /*    dx = xnew - xold;
         * dy = ynew - yold;
         * tmp = dx * dx + dy * dy;
         * if (tmp == 5)
         *  return true;
         *  else
         *  return false;
        }*/
        int dx = i - currentLocation[0];
        int dy = j - currentLocation[1];
        int tmp = dx * dx + dy * dy;
        
        if(tmp == 5)
            return true;
        else
            return false;
    }
    
    public String getName()
    {
        return pieceType;
    }
}
