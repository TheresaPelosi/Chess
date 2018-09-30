 import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JPanel;


public class Button extends JPanel implements MouseListener{
    
    //Color of the tile, black or white
    public Color currentColor = Color.BLACK;
    //Dimension (position) of the tile within the board
    private int[] gridLocation = {-1, -1};
    
    private boolean isClicked = false;
    private static boolean selectedAPiece = false;
    public int doubleClick = 0;
    private int[] originalClick = {-1, -1};
    private Board gameBoard;
    
    private Piece pieceOnButton = null;
    private static Piece originalPiece = null;
    private String[] playerName = {"White", "Black"};
    private int player = 0;

   
    public Button(int buttonSize, int[] position, Board parentBoard)
    {
        gameBoard = parentBoard;
        this.setPreferredSize(new Dimension(buttonSize, buttonSize));
        
        //Try not to think about this one too hard.
        //The button class, since it implements mouseListener, it carries
        //a suite of methods (see below) regarding clicks.  We can add it 
        //to any component that needs a mouseListener.  This class also 
        //extends JPanel, so it can be the recipient of a mouseListener
        this.addMouseListener(this);
        gridLocation = position;
        
        //Sets the color to black or white depending on location
        if((gridLocation[0] + gridLocation[1]) % 2 == 1) 
            currentColor = Color.WHITE;
    }
    
    
    //Any graphics things go here
    public void paintComponent(Graphics g)
    {
        g.setColor(currentColor);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getSize().width, this.getSize().height);
        if(pieceOnButton == null) return;  //why does that work
        /*
        Okay, so this will draw the correct image on this button only
        based upon the pieceOnButton variable
        */
        String type = pieceOnButton.getName();
        String team = pieceOnButton.getTeam();
        /*
        okay, so this parts a little clever
        because each piece is encoded with a literal string
        for team ("Black" or "White") and type ("Pawn", "Bishop")
        and as long as we are consistent with the file names, the following
        code will work no matter what type of piece needs drawing
        
        !!!!!!!!! I thought of this but didn't think it was legal. Oh that makes things so much easier.
        */
        BufferedImage icon = null;
        try{
            icon = ImageIO.read(new File("pieces/" + team + type + ".gif"));
            g.drawImage(icon, 0, 0, this.getSize().width, this.getSize().height, null);
        }catch (IOException e) {}
    }
    
    //Anything having to do with mouse clicking should go here.
    public void mouseClicked(MouseEvent arg0)
    {
        System.out.println("Mouse clicked at (" + gridLocation[0] + ", " + gridLocation[1] + ") and pieceSelected=" + gameBoard.pieceSelected);
        //NO PIECE HAS BEEN SELECTED, THIS IS A FRESH TURN
        if(pieceOnButton != null)
            System.out.println(!pieceOnButton.getTeam().equals(playerName[player]));
            if(!gameBoard.pieceSelected)
            {
                if(!pieceOnButton.getTeam().equals(gameBoard.whosTurn))
                System.out.println("NOT YOUR TURN!!!!");
                //THEY CLICK ON A PIECE FROM THEIR SIDE
                //if(this.getPiece() != null && pieceOnButton.getTeam().equals(this.playerName[player])) //clicked on piece
                else if(this.getPiece() != null && pieceOnButton.getTeam().equals(gameBoard.whosTurn)) //clicked on piece
                {
                    System.out.println("This square contains a " + this.getPiece().getName());
                    currentColor = Color.GREEN;
                    gameBoard.activeTile = this.getLoc();
                    gameBoard.pieceSelected = true;
                }
            }
            //THEY HAVE A PIECE SELECTED, AND THIS IS THE BUTTON (TILE) DESTINATION THEY WANT TO MOVE TO
            else{
                System.out.println("The user wants to move the active piece to location (" + gridLocation[0] + ", " + gridLocation[1] + ")");
                //THEY CLICKED ON THE SAME PLACE, DESELECTING IT
                if(gameBoard.activeTile[0] == this.getLoc()[0] && gameBoard.activeTile[1] == this.getLoc()[1])
                {
                    System.out.println("Same space, deselecting");
                    //MAKE THE OLD TILE SHINY AND CLEAN
                    this.currentColor = Color.BLACK;
                    if((gridLocation[0] + gridLocation[1]) % 2 == 1) 
                        this.currentColor = Color.WHITE;
                        
                    gameBoard.activeTile = null;
                    gameBoard.pieceSelected = false;
                }
                else if(pieceOnButton == null) //clicked on empty tile
                {   
                    System.out.print("The space appears to be empty, checking if it is a valid move... ");
                    Button oldTile = gameBoard.getButton(gameBoard.activeTile);
                    
                    //ASK THE ACTIVE TILE IF IT'S PIECE SAYS THAT THE CURRENT TILE IS A VALID MOVE
                    if(oldTile.getPiece().validMove(gridLocation[0], gridLocation[1]))  
                    {
                        System.out.println("It appears to be valid.");
                        //MOVE THE PIECE TO THE CURRENT TILE
                        //THIS UPDATES THE STATE OF PIECE
                        oldTile.getPiece().move(gridLocation[0], gridLocation[1]);
                        //THIS UPDATES THE STATE OF THE CURRENT TILE
                        this.setPiece(oldTile.getPiece());
                            
                        //MAKE THE OLD TILE SHINY AND CLEAN
                        oldTile.currentColor = Color.BLACK;
                        int[] prevLoc = gameBoard.activeTile;
                        if((prevLoc[0] + prevLoc[1]) % 2 == 1) 
                            oldTile.currentColor = Color.WHITE;
                        
                        oldTile.repaint();
                
                        //CLEAR THE PIECE FROM THE OLD TILE
                        oldTile.setPiece(null);
                        
                        //CLEAR THE SELECTED FLAGS
                        gameBoard.pieceSelected = false;
                        gameBoard.activeTile = null;
                        gameBoard.takeTurn();
                    }
                } //Wrong vv for black pieces
                else if(pieceOnButton != null && !pieceOnButton.getTeam().equals(gameBoard.whosTurn)) //Clicked on another piece, capture
                {
                    Button oldTile = gameBoard.getButton(gameBoard.activeTile);
                    if(oldTile.getPiece().validMove(gridLocation[0], gridLocation[1]))  
                    {
                        if(oldTile.getPiece().getTeam() != this.getName())
                            pieceOnButton.capture();
                        System.out.println("It appears to be valid.");
                        oldTile.getPiece().move(gridLocation[0], gridLocation[1]);
                        this.setPiece(oldTile.getPiece());
                        oldTile.currentColor = Color.BLACK;
                        int[] prevLoc = gameBoard.activeTile;
                        if((prevLoc[0] + prevLoc[1]) % 2 == 1) 
                            oldTile.currentColor = Color.WHITE;
                    
                        oldTile.repaint();
                        oldTile.setPiece(null);
                    
                        gameBoard.pieceSelected = false;
                        gameBoard.activeTile = null;
                        
                        
                            
                        gameBoard.takeTurn();
                    }
    
                    //INVALID CHOICE
                    else System.out.println("It appears to be invalid.  Try again.");
                }
            }
            
            
        this.repaint();
    }

    //Need to be able to get/set the piece
    
    public void setPiece(Piece newPiece)
    {
        pieceOnButton = newPiece;
    }
    public Piece getPiece()
    {
        return pieceOnButton;
    }
    
    //irrelevent
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
        
    }
    
    public int[] getLoc(){
        return gridLocation;
    }
}
