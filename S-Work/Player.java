import java.util.ArrayList;
public class Player extends Card {
    private int playerTotal;
    private ArrayList <Card> playerHand;
   
    

   public Player(){
       playerTotal=0;
       playerHand = new ArrayList <Card> ();
   }

public void addCard(Card c) {
  playerHand.add(c);
}

public void addTotal(int x){
    playerTotal+=x;
}

public void showHand(){
    for (Card card : playerHand) {
        card.printCard();
    }
}

public int getPlayerTotal(){
    return playerTotal;
}

public void resetTot(){
    playerTotal=0;
}
}