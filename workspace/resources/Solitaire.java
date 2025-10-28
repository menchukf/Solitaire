package resources;
import java.util.ArrayList;
import java.util.Stack;
import resources.Card.Suit;
	//the part of your program that's in charge of game rules goes here.
public class Solitaire {
	ArrayList<Stack <Card>> columns=new ArrayList<Stack <Card>>();
	Stack<Card> deck=new Stack<Card>();
	Stack<Card> discard=new Stack<>();
	ArrayList<Stack <Card>> foundations=new ArrayList<Stack<Card>>();
//precondition: all paramaters are non-null
//postcondition: returns false if move is illegal, returns true and makes move if move is legal
public boolean isLegalMove (Card selected,Card d){//d for destination
	boolean valid=false;
	Stack<Card> start=null;
	Stack<Card> destination=null;
	for(Stack<Card> col: columns){
	 	for(Card c :col){
	 		if (c.equals(selected)){
	 			start=col;
				//System.out.println("start found");
	 		}
			if (c.equals(d)){
				destination=col;
				//System.out.println("end found");
			}
	 	}

	}
				
		for (Stack <Card> part : this.foundations){
			for (Card c : part){
				if(c.equals(selected)){

						//System.out.print(c.toString()+"selected");
				   start=part;
				}
				if(c.equals(d)){
						//System.out.print(c.toString()+"selected");
				   destination=part;
				}
			}
	   }
			for(Card c:deck){
				if (c.equals(selected)){
					start=deck;
				}
				if(selected.equals(d)){
					//selected.setBorder(null);
					return false;
					
					//selected.setBorder(BorderFactory.createLineBorder(Color.red));
				}
			}
	//start.pop();
	//System.out.println("legalmove called");
	if(destination==null ){
		System.out.println("not found");
		return false;
	}
	if(start==null){
		start=discard;
	}
	//System.out.println("destination:"+destination);
	
	//System.out.println("start:"+start);
    Card last=destination.peek();
	//System.out.println("last= "+last.value+"=d="+d.value);
	/*if(last.value==100){
		valid=true;
		start.remove(selected);
		destination.add(selected);
	}*/
	if(columns.contains(destination)){
		//System.out.println("move in columns");
	if (selected.value==13 && last.value==100){
		valid=true;
		start.remove(selected);
		destination.add(selected);
		//System.out.println("kingmoved");

	}	
	if((last.suit.isRed && !selected.suit.isRed) || (!last.suit.isRed && selected.suit.isRed)){
		//System.out.println("column check1 passed");
		//System.out.println("last: "+last.value+" selected: "+selected.value);
		if(last.value-1==selected.value){
			//System.out.println("column check2 passed");
			valid=true;
		//destination.add(0, last);
		//destination.add(0, selected);
		//System.out.println(selected + " moved to column");
		//System.out.println("move is valid");
		//System.out.println("selected removed: "+selected.value+"from "+start);
		start.remove(selected);
		
		destination.add(selected);
		}
	}else{
		//destination.add(0,last);
		//start.add(0,selected);
		//System.out.print("card returned to start");
		valid=false;

	}

	}else if(foundations.contains(destination)){
		System.out.println("reached foundations");
		if (selected.value==1 && last.value==100){
			valid=true;
			start.remove(selected);
			destination.add(selected);
		}
		if(last.suit==selected.suit && last.value+1==selected.value ){
			//destination.add(0,last);
			destination.add(selected);
			start.remove(selected);
			valid=true;
		}
		

	}else if(destination==discard){
		valid=false;
	}
	//if(start==discard && valid==true){
	//	start.remove(selected);
	//}
	return valid;
}

//starts the game, displaying teh deck and the columns
//precondition:
//postcondition:game is initialized and ready to display
public void initialize(){

	for(int i=0;i<4;i++){
		Suit suit=Suit.Diamonds;
		if (i==0){
			suit=Suit.Clubs;
		}
		if(i==1){
			suit=Suit.Spades;
		}
		if (i==2){
			suit=Suit.Diamonds;
		}
		if(i==3){
			suit=Suit.Hearts;
		}
	
		for (int j=1;j<=13;j++){
			deck.add(new Card(j, suit));
		}
	}
	//System.out.println(deck);
	for(int i=0;i<deck.size();i++){//shuffles the deck
		Card c = deck.pop();
		int index = (int) (Math.random()*deck.size());
		deck.add(index, c);
		
	}
	



	for(int i=0;i<9;i++){//deals the columns
		Stack<Card> column =new Stack<Card>();
		column.add(new Card(100, Suit.Spades));
		for(int j=0;j<i;j++){
			Card c=deck.pop();
			column.add(c);
		}
		//column.add(new Card(100,Suit.Spades));
		columns.add(column);
	}
	for(int i=0;i<5;i++){
		Stack<Card> part =new Stack<Card>();
		foundations.add(part);

	}
	
	//System.out.print("game initialized");
	
}

public Stack<Card> getDeck(){
	return deck;
}
public ArrayList<Stack <Card>> getColumns(){
	return columns;
}
public boolean isGameWon(){
	boolean won=true;
	for(Stack <Card> f : foundations){
		if(f!= null && !f.isEmpty()){
			if(f.peek()==null){
				won=false;
				return won;
			}
			if(f.lastIndexOf(f.peek())==13){
				won=true;
			}else{
				won=false;
			}	
		} else {
			won=false;

		}
	}

	return won;
}

public void draw(){
	Card c=deck.pop();
	discard.add(c);
	if(deck.contains(c)){
		deck.remove(c);
	}
	System.out.println("draw function called");
}

public boolean testWin(){
	//for(Stack<Card> f :foundations){
		for(int i=0;i<4;i++){
			Suit suit=Suit.Diamonds;
			if (i==0){
				suit=Suit.Clubs;
			}
			if(i==1){
				suit=Suit.Spades;
			}
			if (i==2){
				suit=Suit.Diamonds;
			}
			if(i==3){
				suit=Suit.Hearts;
			}
		
			for (int j=1;j<=13;j++){
				foundations.get(i).add(new Card(j, suit));
			}
		}
	//}
	return(this.isGameWon());
}


}