package net.garyscorner.codejammoist;

//File:		DataStructure.java
//FileDesc:	Contains the DataStructure class for our array of problems
//Author:	Gary Bezet
//Date:		2016-06-14
//Desc:		Designed for google apac test "Practice Round APAC test 2016"  Written as practice
//Problem:	https://code.google.com/codejam/contest/6234486/dashboard#s=p2
//Results:	

public class DataStructure {

	public int casenum;  //case number
	
	public int totalCards;  //total number of cards
	
	public String[] cards;  //array with cards in it
	
	public int solution = 0;
	
	
	//solve the problem here
	public void solve() {
		
		int lastcard = 0; //how many to go back
		
		for(int i = 1; i < totalCards; i++) {
			if( cards[i].compareTo(cards[lastcard]) < 0 ) {
				solution++;
				
			} else {
				lastcard = i;
			}
		}
	}
	
}
