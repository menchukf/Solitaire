
import javax.imageio.ImageIO;
import javax.swing.*;

//import Card.Suit;
//Author: Abby Bruskin
//Date: October 23, 2025
//This class creates the GUI for the Solitaire game
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;


public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

	Solitaire game;
   public GUI(Solitaire game){
	   this.game= game;
        //Create and set up the window.
       setTitle("Solitaire");
       setSize(900,700);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
	   
       // this supplies the background
       try {
		System.out.println(getClass().toString());
		Image blackImg = ImageIO.read(getClass().getResource("background.jpg"));
		setContentPane(new ImagePanel(blackImg));

       }catch(IOException e) {
    	   e.printStackTrace();
       }
       
    

	   //establishes the main game area panel
	    JPanel gameArea = new JPanel();
		gameArea.setOpaque(false);
		gameArea.setLayout(new BoxLayout(gameArea, BoxLayout.PAGE_AXIS));
		gameArea.setSize(new Dimension(750,500));
		gameArea.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
		this.add(gameArea);
		
	   //establishes the card piles panel inside gameArea
		JPanel cardPiles = new JPanel();
		cardPiles.setOpaque(false);
		cardPiles.setLayout(new BoxLayout(cardPiles, BoxLayout.PAGE_AXIS));
		cardPiles.setPreferredSize(new Dimension(750,248));
		cardPiles.setMinimumSize(new Dimension(750,248));
		cardPiles.setMaximumSize(new Dimension(750,248));
		cardPiles.setAlignmentY(1.0f);
		cardPiles.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.YELLOW));
		gameArea.add(cardPiles);

	   //establishes the hand panel inside gameArea
		JPanel hand = new JPanel();
		hand.setOpaque(false);
		hand.setLayout(new BoxLayout(hand, BoxLayout.PAGE_AXIS));
		hand.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
		hand.setMinimumSize(new Dimension(750,244));
		hand.setPreferredSize(new Dimension(750,244));
		hand.setMaximumSize(new Dimension(750,244));
		hand.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		gameArea.add(Box.createVerticalGlue());
		gameArea.add(hand);	

	   Stack<Card> testStack = new Stack<Card>();
	   testStack.push(new Card(8, Card.Suit.Clubs));
	   testStack.push(new Card(7, Card.Suit.Hearts));
	   testStack.push(new Card(6, Card.Suit.Clubs));
	   testStack.push(new Card(5, Card.Suit.Diamonds));
	 
	   
	JLayeredPane pile = drawPile(testStack);
	pile.setSize(75, 180);
	cardPiles.add(pile);
	   
	   //Display the window.
		this.setVisible(true);
    }


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public JLayeredPane drawPile(Stack<Card> stackIn) {

    Object cards[];

    cards = stackIn.toArray(); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. You’ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position
	JLayeredPane pile = new JLayeredPane();

	int offset = 0;
	for (int i = 0; i < cards.length; i++) {
		Card card = (Card) cards[i];
		System.out.println("Adding card: " + card.toString());
		card.setLocation(0, offset);
		card.setSize(85, 118); 
		pile.add(card, Integer.valueOf(i)); 
		offset += 20; 
	}
	return pile;
}

 

}

