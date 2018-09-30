
/**
 * Write a description of class wBishop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bishop extends Piece
{
    public Bishop(String newTeam, int[] location, Board theBoard)
    {
        //we can instantiate variables in the parent abstract class, Piece
		team = newTeam;
		pieceType = "Bishop";
		currentLocation = location;
		board = theBoard;
    }
    

    public boolean validMove(int i, int j)
    {
        int y = currentLocation[1];
        int x = currentLocation[0];
        if(j - y < 0 && i - x < 0) //diaganol up left
        {
            boolean isValid = true;
            for(int k = y - 1; k > j; k--)
            {
                if(board.getButtons(x-1, k).getPiece() != null)
                {
                    isValid = false;
                    System.out.println("False");
                    break;
                }
                x--;
            }
            return isValid;
        }
        else if(j - y < 0 && i - x > 0) //diaganol down left
        {
            boolean isValid = true;
            for(int k = y - 1; k > j; k--)
            {
                if(board.getButtons(x+1, k).getPiece() != null)
                {
                    isValid = false;
                    System.out.println("False");
                    break;
                }
                x++;
            }
            return isValid;
        }
        else if(j - y > 0 && i - x > 0) //diaganol down right
        {
            boolean isValid = true;
            for(int k = y + 1; k < j; k++)
            {
                if(board.getButtons(x+1, k).getPiece() != null)
                {
                    isValid = false;
                    System.out.println("False");
                    break;
                }
                x++;
            }
            return isValid;
        }
        else if(j - y > 0 && i - x < 0) //diaganol up right
        {
            boolean isValid = true;
            for(int k = y + 1; k < j; k++)
            {
                if(board.getButtons(x-1, k).getPiece() != null)
                {
                    isValid = false;
                    System.out.println("False");
                    break;
                }
                x--;
            }
            return isValid;
        }
        else
            return false;
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
    
    public boolean validMove(int[] orig, int[] doub)
    {
        return true;
    }
    
    public String getName()
    {
        return pieceType;
    }
}
