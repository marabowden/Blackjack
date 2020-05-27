

public class Card extends Deck {
    
    private String suit;
    private int value;

    public Card(String s,int v){
        suit = s;
        value = v;
    }

    public Card(){
        suit="";
        value=0;
    }

    public String draw (Card c){
        return "Your card is "+value+" of "+suit;
    }

    public void printCard(){
        System.out.print(value+suit);
    }

    public int getVal(){
        return value;
    }

    public String getSuit(){
        return suit;
    }
}