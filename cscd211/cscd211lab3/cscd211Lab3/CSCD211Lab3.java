package cscd211Lab3;


import java.util.Scanner;

import cscd211Game.Classes.Card;
import cscd211Game.Classes.Deck;
import cscd211Game.Players.Dealer;
import cscd211Game.Players.Player;
import cscd211Methods.Lab3Methods;

/**
 * This is Lab3 which contains the main method for testing your code. This java
 * file can't be modified in any way. <br>
 * The flow of the program is as follows:<br>
 * 
 * <br>
 * 1) The program creates an array of 104 standard playing card representing 2 standard deck of card<br>
 * 
 * 2) The 104 cards are passed to the Deck class making the 2 decks of cards<br>
 * 
 * 3) The user is prompted for the number players other than the dealer<br>
 * 
 * 4) The user is prompted for the maximum number of cards the player can hold in their hand<br>
 * 
 * 5) The user is prompted for the names of the players and an array of Player objects are created<br>
 * 
 * 6) The user is prompted to enter the number of cards that will be dealt each hand. The number must be greater than 0 and less than or equal to max number of cards
 * in the player's hand.<br>
 * 
 * 7) The dealer is created and passed the deck(s) of cards and the array of players. The dealer is not a player but just facilitator for game play.<br>
 * 
 * 8) The user is prompted for how many times to shuffle the deck and then the deck(s) are shuffled that many times<br>
 * 
 * 9) The cards are dealt, the hands are displayed an a winner is chosen. <br>For this lab the game is a modified game of war wear the sums of the value of the cards
 * determines who the winner is.  The winner is simply the player with the greatest summed card value.  If there are two or more players with the same summed value then it is
 * a tie and there is no winner<br>
 * 
 * 10) The game continues until the deck is empty or nearly empty<br>
 * 
 * 11) The decks are reset and the user is offered to play again -- This is an all or nothing. A single user is asked to play again and if s/he chooses yes, the game fully resets
 * and a new set of players is created.<br>
 * 
 * <br>NOTE: There are methods that you must write but they are never called.  This is for expansion purposes and you must still write them.
 */
public class CSCD211Lab3
{
	/**
     * This constructor is NOT REQUIRED. It is only here because it appears
     * automatically when the JavaDoc is generated. If it were not here it would
     * still appear in the JavaDoc.
     */
    public CSCD211Lab3()
    {
        /* empty constructor */
    }// end DVC

    /**
     * The main method. The information about this method is describe above.
     * 
     * @param args
     *            Any command line parameters that are passed in. If there are
     *            any they will be ignored by the program
     * @throws Exception
     *             To shut the compiler up for the potential FileNotFoundException that
     *             Scanner object likes to whine about.
     */
   public static void main(final String [] args) throws Exception
   {
      Card [] cards = null;
      Dealer theHouse = null;
      Deck theDecks = null;
      Player [] players = null;
      Scanner kb = new Scanner(System.in);
      int shuffleTimes, maxPlayerCards, totalPlayers, cardsToDeal;
      
       
      cards = Lab3Methods.createCards(104, 2, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
      theDecks = new Deck(cards);
      
      do
      {
         totalPlayers = Lab3Methods.readTotalPlayers(kb);
         maxPlayerCards = Lab3Methods.readMaxPlayerCards(kb);
         players = Lab3Methods.createPlayers(totalPlayers, maxPlayerCards, kb);
         cardsToDeal = Lab3Methods.readCardsToDeal(kb, maxPlayerCards);
         
         theHouse = new Dealer(players, theDecks, cardsToDeal);            
     
         shuffleTimes = Lab3Methods.readTimesToShuffle(kb);
         theDecks.shuffle(shuffleTimes);
         do
         {
            theHouse.deal();
            theHouse.displayHand();  
            theHouse.determineWinner();
            
            System.out.println("Press Enter to Continue");
            kb.nextLine();
                  
         }while(theHouse.cardsRemaining());
         
         theHouse.reset();
                    
      } while (Lab3Methods.goAgain(kb));
   
   }// end main

}// end class