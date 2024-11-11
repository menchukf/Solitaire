package resources;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import resources.Card.Suit;
	//the part of your program that's in charge of game rules goes here.
public class Solitaire {
	ArrayList<Stack <Card>> columns;
	Stack<Card> deck;
	Stack<Card> discard;
	ArrayList<Stack <Card>> foundations;
//precondition:
//postcondition:
public boolean isLegalMove (Card selected, int x, int y){


	return true;
}

//starts the game, displaying teh deck and the columns
//precondition:
//postcondition:
public void initialize(){

	for(int i=0;i<=4;i++){
		Suit suit=Suit.Spades;
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
	System.out.print("deck generated");
	for(int i=0;i<7;i++){
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
	

}

public Stack<Card> getDeck(){
	return deck;
}
public ArrayList<Stack <Card>> getColumns(){
	return columns;
}


public Card draw(){
	Card c=deck.pop();
	discard.add(c);
	return c;
}


public boolean isLegalMove(Card c ){
	boolean valid = false;
	return valid;
}



}