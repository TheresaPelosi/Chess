
/**
 * Theresa Pelosi
 * Period 9
 * 
 * Play chess
 */
public class Chess
{
    abstract class Board
    {
        int Tile[][] = new int[8][8];
        boolean isPiece[][] = new boolean[8][8];
        
        protected void setBoard()
        {
            for(int i = 0; i < 8; i++)
            {
                if(i < 2 || i > 5)
                {
                    for(int j = 0; j < 8; j++)
                    {
                        isPiece[i][j] = false;
                    }
                }
                else
                {
                    for(int j = 0; j < 8; j++)
                    {
                        isPiece[i][j] = true;
                    }
                }
            }
        }
        
        protected void team()
        {
            int player = 1;
                
            if(true == true) //a move is taken
            {
                player = 3 - player;
            }
        }
    }
    
    abstract class Tile extends Board
    {
        protected void move(int i, int j) //this piece is moved to tile[i][j] and ispiece at position is set to true
        { 
          if(validMove(i, j) == true) //implement movement based on clicking 
          {
              isPiece[i][j] = true;
              position(i, j);
          }
        }
        
        protected boolean isPiece(int i, int j)
        {
            return isPiece[i][j];
        }
        
        protected int[] position(int i, int j)
        {
            int[] position = new int[2];
            position[0] = i;  //x coordinate
            position[1] = j;  //y coordinate
            
            return position;
        }
        
        protected boolean isAlive() //in piece
        {
            return false;
        }
        
        protected boolean validMove(int i, int j) //over-ride based on piece
        { //check for if on right team
            return false;
        }
        
        protected void team()
        {
            
        }
    }
    
    public void main()
    {
        
    }
}
