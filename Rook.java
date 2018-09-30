
/**
 * Write a description of class wRook here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rook extends Piece
{
    public Rook(String newTeam, int[] location, Board theBoard)
    {
        //we can instantiate variables in the parent abstract class, Piece
        team = newTeam;
        pieceType = "Rook";
        currentLocation = location;
        board = theBoard;
    }
    
    public boolean validMove(int i, int j)
    {
       if(j == currentLocation[1] || i == currentLocation[0]) //straight
        {
            int y = currentLocation[1];
            int x = currentLocation[0];
            if(i == currentLocation[0] && j > currentLocation[1]) //vertical down
            {
                System.out.println("Block 1");
                    boolean isValid = true;
                    for(int k = y + 1; k < j; k++)
                    {
                        if(board.getButtons(i, k).getPiece() != null)
                        {
                            isValid = false;
                            break;
                        }
                    }
                    return isValid;
            }
            else if(j == currentLocation[1] && i > currentLocation[0]) //horizontal right
            {
                System.out.println("Block 2");
                boolean isValid = true;
                for(int k = x + 1; k < i; k++)
                {
                    System.out.println("i:" + i + " j:" + j + " k:" + k);
                    if(board.getButtons(k, j).getPiece() != null)
                    {
                        isValid = false;
                        break;
                    }
                }
                 return isValid;
            }
            else if(i == currentLocation[0] && j < currentLocation[1]) //horizontal left
            {
                System.out.println("Block 3");
                boolean isValid = true;
                for(int k = y - 1; k > j; k--)
                {
                    if(board.getButtons(i, k).getPiece() != null)
                    {
                        isValid = false;
                        break;
                    }
                }
                 return isValid;
            }
            else if(j == currentLocation[1] && i < currentLocation[0]) //vertical up
            {
                System.out.println("Block 4");
                boolean isValid = true;
                for(int k = x - 1; k > i; k--)
                {
                    if(board.getButtons(x, j).getPiece() != null)
                    {
                        isValid = false;
                        break;
                    }
                }
                 return isValid;
            }
            else
                return false;
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
