
/**
 * Write a description of class bQueen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Queen extends Piece
{
    public Queen(String newTeam, int[] location, Board theBoard)
    {
        //we can instantiate variables in the parent abstract class, Piece
        team = newTeam;
        pieceType = "Queen";
        currentLocation = location;
        board = theBoard;
    }

    public boolean validMove(int i, int j)
    {
        if(Math.abs(j - currentLocation[1]) == Math.abs(i - currentLocation[0])) //diaganol
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
        else if(j == currentLocation[1] || i == currentLocation[0]) //straight
        {
            int y = currentLocation[1];
            int x = currentLocation[0];
            if(i == currentLocation[0] && j > currentLocation[1]) //vertical down
            {
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
        
        
        
        
        /**if ((col == currentLocation[0]) && (row == currentLocation[1]))
          return false;
        // could a rook do it?
        if (col == currentLocation[0])
          return true;
        if (row == currentLocation[1])
          return true;
        // could a bishop do it?
        if (currentLocation[0] - currentLocation[1] == col - row)
          return true;
        if (currentLocation[0] + currentLocation[1] == col + row)
         return true;    
        return false;**/
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
