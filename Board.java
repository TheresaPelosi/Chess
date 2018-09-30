import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Board extends JFrame{
    
    //2D array that stores the Button components
    Button[][] buttons;
    //Default size of the button
    //Set to this value because of size of .gif images
    int buttonSize = 41;
    
    public boolean pieceSelected = false;
    public int[] activeTile;
    public String whosTurn = "White";
    
    
    //Constructor for board
    //width and height are for the number of tiles in each dimension
    public Board(int width, int height){
        
        //Creates an intermediate panel that our small tiles attach to
        //and this panel, in turn, attaches to the JFrame (Board)
        JPanel topPanel = new JPanel(new GridLayout(width, height), true);
        topPanel.setPreferredSize(new Dimension(width*buttonSize, height*buttonSize));
        
        buttons = new Button[width][height];
        
        //Instantiate the buttons and add them to our topPanel
        setupBoard(topPanel);
        
        //Add the topPanel to the JFrame
        this.add(topPanel);
        
        //this is a crucial command that starts at the bottom of the
        //heirarchy and resizes elements to fit based on the size of 
        //their components.
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    //Just a double-for loop that makes buttons and adds them to the Board.
    public void setupBoard(JPanel currentPanel){
        for(int i = 0; i < buttons.length; i++){
            for(int j = 0; j < buttons[i].length; j++){
                int[] loc = {j, i};
                Button newButton = new Button(buttonSize, loc, this);
                buttons[j][i] = newButton;
                currentPanel.add(newButton);
            }
        }
        
        /*
        Here I am going to create a row of pawns for each side at the 
        start of the game.  You need to do the work for the other types
        of pieces, but it should look something like this
        */
        for(int i = 0; i < 8; i++) //row of pawns
        {
            int[] loc = {i, 1};
            Pawn newPawn = new Pawn("Black", loc, this);
            buttons[i][1].setPiece(newPawn);
        }
        for(int i = 0; i < 8; i+= 7) //two rooks
        {
            int[] loc = {i, 0};
            Rook newRook = new Rook("Black", loc, this);
            buttons[i][0].setPiece(newRook);
        }
        for(int i = 2; i < 8; i += 3)
        {
            int[] loc = {i, 0};
            Bishop newBishop = new Bishop("Black", loc, this);
            buttons[i][0].setPiece(newBishop);
        }
        for(int i = 1; i < 8; i+= 5)
        {
            int[] loc = {i, 0};
            Knight newKnight = new Knight("Black", loc, this);
            buttons[i][0].setPiece(newKnight);
        }
            int[] loc = {3, 0};
            King newKing = new King("Black", loc, this);
            buttons[3][0].setPiece(newKing);
            
            loc[0] = 4;
            loc[1] = 0;
            Queen newQueen = new Queen("Black", loc, this);
            buttons[4][0].setPiece(newQueen);
        
        for(int i = 0; i < 8; i++)
        {
            int[] loca = {i, 6};
            Pawn newPawn = new Pawn("White", loca, this);
            buttons[i][6].setPiece(newPawn);
        }
        for(int i = 0; i < 8; i+= 7) //two rooks
        {
            int[] loca = {i, 7};
            Rook newRook = new Rook("White", loca, this);
            buttons[i][7].setPiece(newRook);
        }
        for(int i = 1; i < 8; i+= 5)
        {
            int[] loca = {i, 7};
            Knight newKnight = new Knight("White", loca, this);
            buttons[i][7].setPiece(newKnight);
        }
        for(int i = 2; i < 8; i += 3)
        {
            int[] loca = {i, 7};
            Bishop newBishop = new Bishop("White", loca, this);
            buttons[i][7].setPiece(newBishop);
        }
        
        int[] loca = {3, 7};
            King newKing1 = new King("White", loca, this);
            buttons[3][7].setPiece(newKing1);
            
            int[] locat = {4, 7};
            Queen newQueen1 = new Queen("White", locat, this);
            buttons[4][7].setPiece(newQueen1);
    }
    
    public void print()
    {
        /**for(int i = 0; i < 8; i++)
        {
            String row = "";
            for(int j = 0; j < 8; j++)
            {
                if(buttons[i][j].getPiece() == null) row+= " ";
                else if(buttons[i][j].getPiece().equals("Pawn"))
                    row+= "P";
                else if(buttons[i][j].getPiece().equals("Rook"))
                    row+= "R";
                else if(buttons[i][j].getPiece().equals("Knight"))
                    row+= "K";
                else if(buttons[i][j].getPiece().equals("Queen"))
                    row+= "Q";
                else if(buttons[i][j].getPiece().equals("King"))
                    row+= "*";
                else if(buttons[i][j].getPiece().equals("Bishop"))
                    row+= "B";
               
            }
            System.out.println(row);
        }**/
       }
       public Button getButton(int[] position)
       {
           return buttons[position[0]][position[1]];
       }
       
       public Button getButtons(int x, int y)
       {
           return buttons[x][y];
       }
       
       public void takeTurn()
       {
           if(whosTurn.equals("White")) whosTurn = "Black";
           else if(whosTurn.equals("Black")) whosTurn = "White";
       }
}