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
import resources.Card.Suit;


public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

	Solitaire game;
	Card selected=null;
	Stack<Card> start=null;
	JPanel foundation=new JPanel(new GridLayout());
	JPanel tableau=new JPanel(new GridLayout());
	JPanel deck =new JPanel(new GridLayout());
	JPanel discard=new JPanel(new GridLayout());
	ArrayList<JLayeredPane> columns =new ArrayList<>();
   public GUI(Solitaire game){
	   this.game=game;
        //Create and set up the window.
       setTitle("Solitaire");
       setSize(800,600);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // this supplies the background
       try {
		//System.out.println(getClass().toString());
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
	   deck.setBounds(700,000,150,150);
	   discard.setBounds(850,0,150,150);
	   tableau.setBounds(00,200,1000,800);
	   
	  /*Stack <Card> testStack=new Stack<Card>();

		testStack.add(new Card(3, Card.Suit.Diamonds));
		testStack.add(new Card(4, Card.Suit.Diamonds));
		testStack.add(new Card(5, Card.Suit.Clubs));
		testStack.add(new Card(8, Card.Suit.Diamonds));
		testStack.add(new Card(7, Card.Suit.Clubs));
	   //tableau.add(drawPile(testStack));
	   for(Card c:testStack){
		deck.add(c);
	   }
		*/
	   this.add(deck);
	   this.add(foundation);
	   this.add(tableau);
	   this.add(discard);
	   game.initialize();
	   //foundation.setBorder(BorderFactory.createLineBorder(Color.red));
	   //tableau.setBorder(BorderFactory.createLineBorder(Color.red));
	   deck.setBorder(BorderFactory.createLineBorder(Color.blue));// just because
	   
	   Card empty = new Card(100, Suit.Spades);// something to click on the discard pile
	   Card back= new Card(100,Suit.Spades);
	   game.discard.add(empty);
	   discard.setBorder(BorderFactory.createLineBorder(Color.green));
	   //JLayeredPane discard=drawFoundation(game.discard);  
	   back.hide();
	   empty.show();
		deck.add(back);
	   //deck.add(discard);
	  
	   //back.setBorder(BorderFactory.createLineBorder(Color.yellow));
	   deck.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// moves a card to the discard pile
			if(game.deck.isEmpty()){
				for(int i=0;i<game.discard.size()-1;i++){
					Card c=game.discard.get(i);
					game.deck.add(c);
					game.discard.remove(c);
				}
				game.discard.clear();
				System.out.println("deck refilled");
				update();
			}
			game.draw();

			
			selected=game.discard.peek();
			selected.setBorder(BorderFactory.createLineBorder(Color.red));
			start=game.discard;

			
			//System.out.println("drawed a card");
			update();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		//	selected=game.deck.peek();
		//	start=game.deck;
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			//selected=game.deck.peek();
			//start=game.deck;
			//update();
			
			//throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			// do nothing
			//throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			//throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
			//also do nothing
			//update();//becasue why not
		}
		
	});
	for(Stack<Card> part: game.foundations){
		Card c=new Card(100, Suit.Spades);
		part.add(c);
	
	foundation.addMouseListener(new MouseListener(){
		
		//precondition:
		//postcondition moves card to foundations if it is an ace or if it can move to foundation
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (selected!= null){
				if(selected.value==1){
					for(Stack<Card> f : game.foundations){
						if(f.get(f.size()-1).value==100&&f.get(0).value!=1){
							f.add(selected);
							for(Stack<Card>col :game.columns){
								if(col.contains(selected)){
									col.remove(selected);
								}
							}
							update();
							return;
						}
					}
				}
				//moves card
				for(Stack<Card> f: game.foundations){
					if(f.get(0).suit==selected.suit){

				
				
				if(game.isLegalMove(selected,f.get(0))){
					//start.pop();
					selected.setBorder(null);
					//game.foundations.get(0).add(selected);
					System.out.print("card moved to foundation");
					update();
				}
			}
				//update();
		}
			}
			
			update();
			
			
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
   }


	   
		
		for(Stack<Card> column : game.columns){
			for(Card c :column){
				if(column.indexOf(c)!=column.size()-1){
					c.hide();
				}
			}
		}
	   for(Card c: game.deck){
			c.addMouseListener(this);
			//System.out.println("Card"+c.suit+"of"+c.value+"got mouselistener");
	   }
	   for(Stack<Card> col: game.getColumns()){
		for(Card c :col){
			c.addMouseListener(this);
			//System.out.println("Card"+c.suit+"of"+c.value+"got mouselistener");
		}

	   }
    	this.setVisible(true);
		//System.out.print("Gui displayed");
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
		//card.setBounds(0,15*i,100, 145);
		card.setBounds(0,10*i,50, 75);//chromebook size
		//if((Card) cards[i].)
		//((Card) cards[i]).hide();
		if(i==cards.length-1){
			((Card) cards[i]).show();
		}

//		card.setBounds(0, cards.length*50-50*i,100, 145);
	}

	return pile;
	}
	public JLayeredPane drawFoundation(Stack<Card> stackIn) {// same as draw pile jsut cards not offset like columns

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
			card.setBounds(0,0,100, 145);//heres the difference
	//		card.setBounds(0, cards.length*50-50*i,100, 145);
		}
	
		return pile;
		}

//precondition:gui and game are both initialized
//postcondition: display is updated
	public void update() {
		//System.out.println("update called");
		for(int i=0;i<columns.size();i++){
			columns.remove(i);
			i--;
		}
		tableau.removeAll();
		int i=0;
		for (Stack <Card> column : game.columns){
			i++;
			JLayeredPane colum=drawPile(column);
			//colum.setBounds(400*i, 200, 400, 1200);
			colum.setBounds(200*i, 200, 200, 1200);
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
		for(int j=0;j<4;j++){
			Stack<Card> part =game.foundations.get(j);
			foundation.add(drawFoundation(part));
	
		}
		deck.removeAll();
		discard.removeAll();
		//Card back= new Card(100,Suit.Spades);
		JLayeredPane discardstack=drawFoundation(game.discard);  
		discard.add(discardstack);
		
		//back.hide();
		//game.deck.add(back);
		deck.add(drawFoundation(game.deck));
		for(Card c : game.deck){
			c.hide();
		}
		for(Card c: game.discard){
			c.show();
		}
		
		//System.out.print(game.discard.peek());
		//deck.add(discard);
		//deck.add(drawFoundation(game.discard));




		if (game.isGameWon()){
			JPanel finish=new JPanel(new FlowLayout());
			finish.setBounds(0,0,800,800);
			JLabel notice =new JLabel("Congrajulations you win");
			finish.add("winDisplay", notice);
		}



	}

	@Override
	//precondition:
//postcondition:
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("Mouse dragged");
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

		start=game.deck;
		//System.out.println("Card: " + arg0.getComponent());
		if(selected==null){
			selected=(Card) arg0.getComponent();
			//System.out.println("I selected "+selected);
			selected.setBorder(BorderFactory.createLineBorder(Color.red));
			for (Stack <Card> col : game.getColumns()){
				 	for (Card c : col){
				 		if(c.equals((Card)(arg0.getComponent()))){

				 			//System.out.print(c.toString()+"selected");
							start=col;
				 		}
				 	}
			}
		/* 
			for (Stack <Card> part : game.foundations){
				for (Card c : part){
					if(c.equals((Card)(arg0.getComponent()))){

						//System.out.print(c.toString()+"selected");
					   start=part;
					}
				}
	   }
	   */

		
/* 
		if(deck.contains(arg0.getX(),arg0.getY())){
				
			selected=game.deck.pop();
			System.out.print(selected.toString()+"selected");
		}	
		}
*/ 
				}else{
					//System.out.println("a card is already selected and the next one is also clicked start is "+selected+" and destination is "+arg0.getComponent());
					//Stack<Card> destination=null;
			   Card d=(Card)arg0.getComponent();
	 		   if(game.isLegalMove(selected,d)){
					//start.remove(selected);
					//destination.add(selected); happens in islegalmove function
					//System.out.println("card moved in columns");
					selected.setBorder(null);
					update();
				}else{
					selected.setBorder(null);
					selected=(Card) arg0.getComponent();
					selected.setBorder(BorderFactory.createLineBorder(Color.red));
					//System.out.println("new card selected");
				}
			
			   }
				
			   //System.out.println("mouse clicked");
			   //this.update();
			update();
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
	//does nothing because it would be redundant with mopuseclicked and cause errors
	public void mousePressed(MouseEvent arg0) {

	
		
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
		//System.out.println("A");
	}
}
