package resources;
import javax.imageio.ImageIO;
import javax.swing.*;

import resources.Card.Suit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;


public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

	Solitaire game;
	Card selected;
	JPanel foundation=new JPanel(new GridLayout());
	JPanel tableau=new JPanel(new GridLayout());
	JPanel deck =new JPanel(new GridLayout());
	ArrayList<JLayeredPane> columns =new ArrayList<>();
   public GUI(Solitaire game){
	   this.game=game;
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
       
       /*******
        *  The code below should allow you to play the solitare
        * game once it's fully created.
        */

	   
	   foundation.setBounds(00,000,600,200);
	   deck.setBounds(700,000,300,200);
	   tableau.setBounds(00,200,1000,800);
	   Stack <Card> testStack=new Stack<Card>();

		testStack.add(new Card(3, Card.Suit.Diamonds));
		testStack.add(new Card(4, Card.Suit.Diamonds));
		testStack.add(new Card(5, Card.Suit.Clubs));
		testStack.add(new Card(8, Card.Suit.Diamonds));
		testStack.add(new Card(7, Card.Suit.Clubs));
	   tableau.add(drawPile(testStack));
	   
	   this.add(deck);
	   this.add(foundation);
	   this.add(tableau);
	   
	   foundation.setBorder(BorderFactory.createLineBorder(Color.red));
	   tableau.setBorder(BorderFactory.createLineBorder(Color.red));
	   deck.setBorder(BorderFactory.createLineBorder(Color.red));
	   deck.add(new Card(100, Suit.Spades));

        this.setVisible(true);
		System.out.print("Gui displayed");
    }

//precondition:
//postcondition:
	public JLayeredPane drawPile(Stack<Card> stackIn) {

    Object cards[];

    cards = stackIn.toArray(); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. You’ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position
	
	JLayeredPane pile=new JLayeredPane();

	for (int i = 0; i < cards.length; i++) {
		JPanel card = (Card) cards[i];
		card.setBounds(0, cards.length*50-50*i,100, 145);
		pile.add(card, i);
	}
	return pile;
	}

//precondition:
//postcondition:
	public void update() {
		for(int i=0;i<columns.size();i++){
			columns.remove(i);
			i--;
		}
		int i=0;
		for (Stack <Card> column : game.columns){
			i++;
			JLayeredPane colum=drawPile(column);
			columns.add(colum);
			colum.setBounds(400*i, 200, 400, 1200);
			this.add(colum);
			tableau.add(colum);
			
/* 			for(int i=0;i<9;i++){
				Stack<Card> column=game.columns.get(i); 
				JLayeredPane c=drawPile(column);
				tableau.add(c);
			   }
	*/
		}
		for(int j=0;j<5;j++){
			Stack<Card> part =game.foundations.get(j);
			foundation.add(drawPile(part));
	
		}
		



	}

	@Override
	//precondition:
//postcondition:
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//precondition:
//postcondition:
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//precondition:click
//postcondition:selescts card for movement
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (selected==null){
		for (Card c : game.deck){
			if(c.contains(arg0.getX(),arg0.getY())){
				selected=c;
				System.out.print(c.toString());
			}
		}
	}else{
		for(JLayeredPane p: columns){
			if (p.contains(arg0.getPoint())){
				if(game.isLegalMove(selected, arg0.getX, ABORT))
			}
		}
	}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//unpause timer or remove method
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//pause timer if there is one
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object cards[];
		for(Stack<Card> c :game.columns){

		cards = c.toArray(); //we convert this stack to an array
		for(Object card : cards){
			Card ca=(Card) card;
			if(ca.contains(arg0.getPoint())){
				selected=ca;
			}
		}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for (Stack<Card> column : game.columns){

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
