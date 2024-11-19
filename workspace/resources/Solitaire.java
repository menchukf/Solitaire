package resources;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import resources.Card.Suit;
	//the part of your program that's in charge of game rules goes here.
public class Solitaire {
	ArrayList<Stack <Card>> columns=new ArrayList<Stack <Card>>();
	Stack<Card> deck=new Stack<>();
	Stack<Card> discard=new Stack<>();
	ArrayList<Stack <Card>> foundations=new ArrayList<Stack<Card>>();
//precondition:
//postcondition:
public boolean isLegalMove (Card selected,Stack<Card> destination,Stack<Card> start){
	boolean valid=false;
	//start.pop();
   Card last=null;
	if(columns.contains(destination)){
	if (selected.value==13){
		valid=true;
		return true;
	}
	try {
		last=destination.pop();	
	} catch (Exception e) {
		// TODO: handle exception
		System.out.print("last = null");
		return false;
	}
	
	
	if(last.suit.isRed!=selected.suit.isRed){
		destination.add(last);
		destination.add(selected);
		valid=true;

	}else{
		destination.add(last);
		valid=false;

	}

	}else if(foundations.contains(destination)){
		if (selected.value==1){
			valid=true;
			return true;
		}
		last =destination.pop();
		if(last.suit==selected.suit && last.value+1==selected.value ){
			destination.add(last);
			destination.add(selected);
			valid=true;
		}else{
			destination.add(last);
			valid=false;
		}

	}
	if (valid==false){
		start.add(selected);
	}

	return valid;
}

//starts the game, displaying teh deck and the columns
//precondition:
//postcondition:
public void initialize(){

	for(int i=0;i<4;i++){
		Suit suit=Suit.Diamonds;
		if (i==0){
			suit=Suit.Clubs;
		}
		if(i==2){
			suit=Suit.Spades;
		}
		if (i==3){
			suit=Suit.Diamonds;
		}
		if(i==4){
			suit=Suit.Hearts;
		}
	
		for (int j=1;j<=13;j++){
			deck.add(new Card(j, suit));
		}
	}
	
	for(int i=0;i<deck.size();i++){
		Card c = deck.pop();
		int index = (int) (Math.random()*deck.size());
		deck.add(index, c);
		
	}
	



	for(int i=0;i<9;i++){
		Stack<Card> column =new Stack<Card>();
		
		for(int j=0;j<i;j++){
			Card c=deck.pop();
			column.add(c);
		}
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
		try {
			if(f.peek()==null){
				won=false;
				return won;
			}	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}if(f.lastIndexOf(f.peek())!=13){
			won=false;
		}
	}

	return won;
}

public Card draw(){
	Card c=deck.pop();
	discard.add(c);
	return c;
}




}