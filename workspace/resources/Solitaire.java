package resources;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import javax.management.monitor.GaugeMonitor;

import resources.Card.Suit;
	//the part of your program that's in charge of game rules goes here.
public class Solitaire {
	ArrayList<Stack <Card>> columns=new ArrayList<Stack <Card>>();
	Stack<Card> deck=new Stack<>();
	Stack<Card> discard=new Stack<>();
	ArrayList<Stack <Card>> foundations=new ArrayList<Stack<Card>>();
//precondition: all paramaters are non-null
//postcondition: returns false if move is illegal, returns true and makes move if move is legal
public boolean isLegalMove (Card selected,Stack<Card> destination,Stack<Card> start){
	boolean valid=false;
	//start.pop();
	System.out.println("legalmove called");
    Card last=destination.peek();
	
	if(columns.contains(destination)){
	if (selected.value==13 && last.value==100){
		valid=true;
		destination.add(selected);
		return valid;
	}
	if (destination==start) {
		System.out.println("no card movement");
		return false;
	}else{
		//start.add(0,selected);
		//System.out.print("card returned to start");
		valid=false;
	}
	
	
	if((last.suit.isRed && !selected.suit.isRed) || (!last.suit.isRed && selected.suit.isRed)){
		if(last.value-1==selected.value){
		//destination.add(0, last);
		//destination.add(0, selected);
		//System.out.println(selected + " moved to column");
		start.remove(selected);
		destination.add(selected);
		valid=true;
		}
	}else{
		//destination.add(0,last);
		//start.add(0,selected);
		//System.out.print("card returned to start");
		valid=false;

	}

	}else if(foundations.contains(destination)){
		if (selected.value==1 && destination.isEmpty()){
			valid=true;
			start.remove(selected);
			return valid;
		}
		if (!destination.isEmpty()){
		//last =destination.firstElement();
		
		if(last.suit==selected.suit && last.value+1==selected.value ){
			destination.add(0,last);
			destination.add(0, selected);
			start.remove(selected);
			valid=true;
		}else{
			//destination.add(0,last);
			//start.add(0, selected);;
			valid=false;
			//System.out.print("card returned to start");
		}
		}else{
		valid=false;
		//start.add(0,selected);
		}

	}if(destination==discard){
		valid=false;
	}

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
	
	System.out.print("game initialized");
	
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