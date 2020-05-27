import java.util.*;
public class Deck {
    private String [][] deck = new String [4][13]; 
    
    public Deck(){
        generateDeck();
    }

    public void printDeck(){ 
        for (int row=0; row<4; row++) {
            for (int col=0; col<13; col++){
              
                if(deck[row][col]!=null)
                System.out.print(" ("+deck[row][col]+") ");

                else
                System.out.print(" (*) ");
            }
            System.out.println();
        }
    }

    public Card removeCard(){
        int randX=(int)(Math.random() * 4);
        int randY=(int)(Math.random() * 13);
        Card card=new Card();

   if(deckLength()>12){
        if(deck[randX][randY]!=null)
        {
          switch((deck[randX][randY]).substring(1,2)){
          case "2":
          case "3":
          case "4":
          case "5":
          case "6":
          case "7":
          case "8":
          case "9":
          case "10":
          card = new Card ((deck[randX][randY]).substring(0, 1),(Integer.parseInt((deck[randX][randY]).substring(1))));
          break;
          case "K":
          case "Q":
          case "J":card = new Card ((deck[randX][randY]).substring(0, 1),10);
          break;
          case "A":card = new Card ((deck[randX][randY]).substring(0, 1),1);
          break;

          }          
        deck[randX][randY]=null;
        }
        else 
        removeCard();
   }

   else if(deckLength()>=12)
      { 
        generateDeck();
        removeCard();
      }
        return card;
    }

    public int deckLength(){
      int deckLength = 0;
        for (int row=0; row<4; row++) {
            for (int col=0; col<13; col++){
              
                if(deck[row][col]!=null)
                deckLength++;

            }
        }

        return deckLength;
    }

    public void generateDeck(){

        String [] suits = {"♣","♦","♠","♥"};
        String [] vals = {"2","3","4","5","6","7","8","9","10","K","J","Q","A"};

        for (int row=0; row<suits.length; row++) {
            for (int col=0; col<vals.length; col++){
                deck[row][col]=suits[row]+" "+vals[col];
            }
        }

    }

}