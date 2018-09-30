import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JPanel;


public class changedButton extends JPanel implements MouseListener{
    
    //Color of the tile, black or white
    public Color currentColor = Color.BLACK;
    //Dimension (position) of the tile within the board
    private Dimension gridLocation;
    
    private boolean isClicked = false;
    private static boolean selectedAPiece = false;
    public int doubleClick = 0;
    private int[] originalClick = {-1, -1};
    private Board gameBoard;
    
    /*
    Each piece should only need to "know" the position of the other
    pieces through an interface to it's parent (Board).  This is essentially
    adding 64 pieces of information that need caretaking to EACH of 64 buttons.
    
    At a basic level, you should only need a single variable
    */
    private Piece pieceOnButton = null;
    static Piece originalPiece = null;
    Piece resetPiece = null;
    
    /*
    This stuff will be initiallized in the board constructor
    
    
    private String[][] names = new String[][]{ //names of every piece
                                             {"wRook", "wKnight", "wBishop", "wQueen", "wKing", "wBishop", "wKnight", "wRook"},
                                             {"wPawn", "wPawn", "wPawn", "wPawn", "wPawn", "wPawn", "wPawn", "wPawn"},
                                             {null, null, null, null, null, null, null, null},
                                             {null, null, null, null, null, null, null, null},
                                             {null, null, null, null, null, null, null, null},
                                             {null, null, null, null, null, null, null, null},
                                             {"bPawn", "bPawn", "bPawn", "bPawn", "bPawn", "bPawn", "bPawn", "bPawn"},
                                             {"bRook", "bKnight", "bBishop", "bBQueen", "bKing", "bBishop", "bKnight", "bRook"}
                                             };
    */
    
    //Button constructor, takes a default size of the button and its position
    public changedButton(int buttonSize, Dimension position, Board parentBoard)
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
        if((gridLocation.getHeight() + gridLocation.getWidth()) % 2 == 1) 
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
    public void mouseClicked(MouseEvent arg0) {
        /*
        So lets game plan
        When a user clicks on a button, the decision tree should go something like this
        -if its not a piece, then we completely ignore them until they click on a valid
        piece
        -If it is a piece, we indicate to them what piece they have just clicked (by flipping the color.  Then we need to ask the piece itself what moves are valid
        for a second click.  So in order to tell when its a first click and a second click lets include a boolean flag "selectedAPiece"
        -If they click on a second button that is within the set of allowable moves, we can then make the move, and update the graphics
        -If they click on a second tile but it isn't an allowable move, we just ignore them but keep the flag expecting a second valid click.
        */
        pieceOnButton = this.getPiece();
        
        if(pieceOnButton != null) //clicked on piece
        {
            System.out.println("You clicked on (" + gridLocation.getWidth() + ", " + gridLocation.getHeight() + ") and it has a " + this.getPiece().getName() + " on it.");
            
            //The following if block signifies that they have clicked on this piece
            //but have NOT previously selected a piece to move.  This means we should
            //expect a second click for a destination.
            if(!gameBoard.pieceSelected)
            {
                currentColor = Color.GREEN;
                //mark this piece as the one we want to be able to move
                originalPiece = pieceOnButton;
                
                //Set the boolean flag to indicate that we have a piece selected
                gameBoard.pieceSelected = true;
            
                //They HAVE a piece selected, and this is their second click.
                //Further, because pieceOnButton != null, there is a piece in this space
                //That means this is the block of code for a capture.
            }
            else
            { 
                //set the background to natural black/white
                currentColor = Color.BLACK;
                if((gridLocation.getHeight() + gridLocation.getWidth()) % 2 == 1) 
                    currentColor = Color.WHITE;
                //Get the button the attacking piece came from
                Button prevButton = gameBoard.getButton(originalPiece.getLocation());
                System.out.println("There is a " + prevButton.getPiece().getName() + " moving from " + "(" + prevButton.getPiece().getLocation()[0] + ", " + prevButton.getPiece().getLocation()[1] + ") to capture the " + this.getPiece().getName() + " at (" + gridLocation.getHeight() + ", " + gridLocation.getWidth() + ")");
                gameBoard.pieceSelected = !selectedAPiece;
            }
            
            originalPiece = pieceOnButton;
        }
        
        if(pieceOnButton == null) //clicked on empty tile
        {   //currently has to double click
            
            //This button is a blank space
            //They have already selected a piece
            if(gameBoard.pieceSelected == true){ //check if valid, if valid, move
            System.out.println("You seem to have already selected a piece");
                if(originalPiece.validMove((int)gridLocation.getHeight(), (int)gridLocation.getWidth()) == true)
                {
                    System.out.println("You have selected a new position that seems valid");
                    Button prevButton = gameBoard.getButton(originalPiece.getLocation());
                    originalPiece.move((int)gridLocation.getHeight(), (int)gridLocation.getWidth());
                    this.setPiece(originalPiece);
                    prevButton.setPiece(null);
                    originalPiece = null;
                    
                    prevButton.repaint();
                    prevButton.currentColor = Color.BLACK;
                    if((prevButton.getLocation().getX() + prevButton.getLocation().getY()) % 2 == 1) 
                        prevButton.currentColor = Color.WHITE;
                }
            }else //wait for valid move
            { 
                
            }
            selectedAPiece = !selectedAPiece;
        
            //Not sure how you want to do the valid move implementation.  You have been hesitant with arrayLists in the past, but it is probably the easiest way to do this.
        }
        
        this.repaint();
        //gameBoard.print();
        //System.out.println("Click at " + gridLocation.getWidth() + ", " + gridLocation.getHeight());
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
}
