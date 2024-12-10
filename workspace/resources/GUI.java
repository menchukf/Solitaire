package resources;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import resources.Card.Suit;


public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

	Solitaire game;
	Card selected=null;
	Stack<Card> start=null;
	JPanel foundation=new JPanel(new GridLayout());
	JPanel tableau=new JPanel(new GridLayout());
	JPanel deck =new JPanel(new GridLayout());
	ArrayList<JLayeredPane> columns =new ArrayList<>();
   public GUI(Solitaire game){
	   this.game=game;
        //Create and set up the window.
       setTitle("Solitaire");
       setSize(1200,770);
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

	   
	   foundation.setBounds(00,000,600,150);
	   deck.setBounds(700,000,300,150);
	   tableau.setBounds(00,200,1000,800);
	/* 
	  Stack <Card> testStack=new Stack<Card>();

		testStack.add(new Card(3, Card.Suit.Diamonds));
		testStack.add(new Card(4, Card.Suit.Diamonds));
		testStack.add(new Card(5, Card.Suit.Clubs));
		testStack.add(new Card(8, Card.Suit.Diamonds));
		testStack.add(new Card(7, Card.Suit.Clubs));
	   tableau.add(drawPile(testSftack));
	   */
	   this.add(deck);
	   this.add(foundation);
	   this.add(tableau);
	   
	   //foundation.setBorder(BorderFactory.createLineBorder(Color.red));
	   //tableau.setBorder(BorderFactory.createLineBorder(Color.red));
	   //deck.setBorder(BorderFactory.createLineBorder(Color.red));


	   Card back = new Card(100, Suit.Spades);
	   deck.add(back);
	back.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			selected=game.deck.peek();
			deck.add(selected);
			start=game.deck;
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			selected=game.deck.peek();
			start=game.deck;
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			selected=game.deck.peek();
			start=game.deck;
			
			
			//throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			// do nothing
			//throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			//throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
			//also do nothing
		}
		
	});
	foundation.addMouseListener(new MouseListener(){
		@Override
		public void mouseClicked(MouseEvent e) {
			// moves card to foundations if it is an ace or if it can move to foundation
			
			if (selected!= null){
				if(selected.value==1){
					for(Stack<Card> f : game.foundations){
						if(f.isEmpty()){
							f.add(selected);
							return;
						}
					}
				}else if (selected.value==game.foundations.get(0).get(0).value+1 && selected.suit==game.foundations.get(0).get(0).suit) {
					(game.foundations.get(0)).add(selected);
				}
				//moves card
				if(game.isLegalMove(selected, start,game.foundations.get(0))){
					start.pop();
					game.foundations.get(0).add(selected);
					System.out.print("card moved to foundation");
				}
			}
			
			
			
			//throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			// do nothing
			//throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			//throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
			//also do nothing
		}
		
	});



	   game.initialize();

	   for(Card c: game.deck){
			c.addMouseListener(this);
	   }
	   for(Stack<Card> col: game.getColumns()){
		for(Card c :col){
			c.addMouseListener(this);
		}

	   }
    	this.setVisible(true);
		System.out.print("Gui displayed");
		revalidate();
	   repaint();
    }

//precondition:method is called on a stack with cards in it
//postcondition:returns a JLayeredPane containing all the cards in the stack
	public JLayeredPane drawPile(Stack<Card> stackIn) {

    Object cards[];

    cards = stackIn.toArray(); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. 
	//You’ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position

	JLayeredPane pile=new JLayeredPane();
	//pile.setPreferredSize(new Dimension(100,800));
	for (int i = cards.length-1; i >=0; i--) {
		JPanel card = (Card) cards[i];
		this.add(card);
		pile.add(card, cards.length-i);
		//pile.add(card);
		card.setBounds(0,50*i,100, 145);
//		card.setBounds(0, cards.length*50-50*i,100, 145);
	}

	return pile;
	}

//precondition:gui and game are both initialized
//postcondition: display is updated
	public void update() {
		for(int i=0;i<columns.size();i++){
			columns.remove(i);
			i--;
		}
		tableau.removeAll();
		int i=0;
		for (Stack <Card> column : game.columns){
			i++;
			JLayeredPane colum=drawPile(column);
			colum.setBounds(400*i, 200, 400, 1200);
			columns.add(colum);
			this.add(colum);
			tableau.add(colum);
		}
 			/*for(int j=0;j<9;j++){
				Stack<Card> col=game.columns.get(j); 
				JLayeredPane c=drawPile(col);
				c.setBorder(BorderFactory.createLineBorder(Color.green));
				tableau.add(c);
			   }
	*/
		
		//System.out.print("columns displayed");
		foundation.removeAll();
		for(int j=0;j<5;j++){
			Stack<Card> part =game.foundations.get(j);
			foundation.add(drawPile(part));
	
		}
		if (game.isGameWon()){
			JPanel finish=new JPanel(new FlowLayout());
			finish.setBounds(0,0,800,800);
			JLabel notice =new JLabel("Congrajulations you win");
			finish.add("windisplay", notice);
	



	}

	@Override
	//precondition:
//postcondition:
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Mouse dragged");
	}

	@Override
	//precondition:
//postcondition:
	public void mouseMoved(MouseEvent arg0) {
		// updates game
		this.update();
	}

	@Override
	//precondition:click
//postcondition:selescts card for movement
	public void mouseClicked(MouseEvent arg0) {

		Stack <Card> start=game.deck;
		System.out.println("Card: " + arg0.getComponent());
		if(selected==null){
			selected=(Card) arg0.getComponent();
			System.out.println("I selected "+selected);
			selected.setBorder(BorderFactory.createLineBorder(Color.red));
			for (Stack <Card> col : game.getColumns()){
				 	for (Card c : col){
				 		if(c.equals((Card)(arg0.getComponent()))){

				 			System.out.print(c.toString()+"selected");

				 		}
				 	}
				}

		
/* 
		if(deck.contains(arg0.getX(),arg0.getY())){
				
			selected=game.deck.pop();
			System.out.print(selected.toString()+"selected");
		}	
		}
*/ 
				}else{
					Stack<Card> destination=game.discard;
			   Card d=(Card)arg0.getComponent();
	 		   for(Stack<Card> col: game.getColumns()){
	 			for(Card c :col){
	 				if (c.equals(selected)){
	 					start=col;
	 				}
					 if (c.equals(d)){
						destination=col;
					}
	 			}
			}

				//System.out.println(start);
				//System.out.println(destination);
	 		   if(game.isLegalMove(selected,destination ,start)){
					start.pop();
					destination.add(selected);
					System.out.println("card moved in columns");
					selected.setBorder(null);
				}else{
					selected.setBorder(null);
					selected=(Card) arg0.getComponent();
					start=destination;
					selected.setBorder(BorderFactory.createLineBorder(Color.red));
					System.out.println("new card ("+ selected.toString()+") selected");
				}
				
			   }
				
			   System.out.println("mouse clicked");
			   this.update();
			
			}
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// no function for this to perform
		//unpause timer or remove method
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//pause timer if there is one
		// no action to perform if mouse exits
	}

	@Override
	//precondition:mouse is clicked on the screen 
	//postconditionif not on a card, nothing happens, if card is already selected, does nothing, but if neither thaen the card that the mouse is pressed on is selected
	public void mousePressed(MouseEvent arg0) {

		// code copied from mouseclicked to only select, not place card
		
		// /* 
		// if(selected==null){//only if a card isn't already selected
		// 	selected=(Card) arg0.getComponent();
		
		// 	for (Stack <Card> col : game.getColumns()){
		// 		 	for (Card c : col){
		// 		 		if(c.equals((Card)(arg0.getComponent()))){

		// 		 			System.out.print(c.toString()+"selected");
		// 					return;
		// 		 		}
		// 		 	}
		// 		}
		
		// if(deck.contains(arg0.getPoint())){
				
		// 	selected=game.deck.pop();
		// 	System.out.print(selected.toString()+"selected");
		// }
		// }
	 	// System.out.println("mouse pressed");

		/*		Object cards[];
		for(Stack<Card> c :game.columns){

		cards = c.toArray(); //we convert this stack to an array
		for(Object card : cards){
			Card ca=(Card) card;
			if(ca.contains(arg0.getPoint())){
				selected=ca;
			}
		}
		}
		//*/
	}

	@Override
	//precondition:mouse is clicked on the screen 
	//postconditionif not on a card or no card is selected, nothing happens, ohterwise card si attempted to move 
	
	public void mouseReleased(MouseEvent arg0) {
		// places but doesnt select a card
	/*	if (selected!= null){
		for (Stack<Card> column : game.columns){
			for(Card c : column){
				if(c.equals((Card)arg0.getComponent())){
					boolean moved=game.isLegalMove(selected,start,column);
					if(moved){
						System.out.println("Card moved");
					}else{
						System.out.println("illegal move");
					}
				}
			}
		}
		}
		*/
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
