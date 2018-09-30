/*seems like there isn't much distinction between wPawn and bPawn
especially now that there is a variable within Piece that indicates
which side they are on.
*/
public class Pawn extends Piece
{
    public Pawn(String newTeam, int[] location, Board theBoard)
    {
        //we can instantiate variables in the parent abstract class, Piece
        team = newTeam;
        pieceType = "Pawn";
        currentLocation = location;
        board = theBoard;
    }
    
    public boolean validMove(int i, int j)
    {
        if(team.equals("White"))
        {
           if(currentLocation[1] == 6)
           {
               if(i == currentLocation[0] && (j == currentLocation[1] - 1 || j == currentLocation[1] - 2))
                    return true;
               else if((Math.abs(i - currentLocation[0]) == 1 && Math.abs(j - currentLocation[1]) == 1) &&  board.getButtons(i, j).getPiece() != null)
               {
                   return true;
               }
               else
               {
                    System.out.println("Wrong"); 
                    return false;
               }
           }
           else
           {
               if(i == currentLocation[0] && j == currentLocation[1] - 1)
                    return true;
               else if((Math.abs(i - currentLocation[0]) == 1 && Math.abs(j - currentLocation[1]) == 1) &&  board.getButtons(i, j).getPiece() != null)
               {
                   return true;
               }
               else
               {
                    System.out.println("Wrong"); 
                    return false;
               }
           }
        }
        else if(team.equals("Black"))
        {
           if(currentLocation[1] == 1)
           {
               if(i == currentLocation[0] && (j == currentLocation[1] + 1 || j == currentLocation[1] + 2))
                    return true;
               else if((Math.abs(i - currentLocation[0]) == 1 && Math.abs(j - currentLocation[1]) == 1) &&  board.getButtons(i, j).getPiece() != null)
               {
                   return true;
               }
               else
               {
                   System.out.println("Wrong");   
                   return false;
               }
           }   
           else
           {
               if(i == currentLocation[0] && j == currentLocation[1] + 1)
                    return true;
               else if((Math.abs(i - currentLocation[0]) == 1 && Math.abs(j - currentLocation[1]) == 1) &&  board.getButtons(i, j).getPiece() != null)
               {
                   return true;
               }
               else
                {
                    System.out.println("Wrong");   
                    return false;
                }
           }
        }
        else
        {
            return false;
        }
    }
    
    public void move(int i, int j)
    {
        currentLocation[0] = i;
        currentLocation[1] = j;
    }
}
