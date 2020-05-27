import java.util.ArrayList;

public class Dealer extends Player {
    private int dealerTotal;
    private ArrayList <Card> dealerHand;
    Deck deck;

    public Dealer(){
        dealerTotal = 0;
        dealerHand = new ArrayList <Card> ();
        deck = new Deck();
    }
    
    public void dealToPlayer (Player p){
        Card c = deck.removeCard();
        p.addCard(c);
        p.addTotal(c.getVal());
    }

   public void dealToDealer(){
        Card c = deck.removeCard();
        dealerHand.add(c);
        dealerTotal+=c.getVal();
    }

    //uses deck's printDeck method to print the entire 52 cards
   public void showDeck(){

    deck.printDeck();

   }
}