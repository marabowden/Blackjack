import java.util.Scanner;
public class Main{
    
    public static void main(String[] args) {
    //initializing dealer and player
        Dealer dealer = new Dealer();
        Player player = new Player();
     
   //dealing 2 cards to dealer and player

        dealer.dealToPlayer(player);
        dealer.dealToDealer();
        dealer.dealToPlayer(player);
        dealer.dealToDealer();

        //using inheritance to use showHand from the player class on dealer
        player.showHand();
        dealer.showHand();

        //displaying player and dealer total points
       System.out.println("Player Points:"+player.getPlayerTotal());
       System.out.println("Dealer Points:"+dealer.getPlayerTotal());
       
       
       Scanner scan = new Scanner (System.in);
       String gameInp="yes";
     while(gameInp!="no"){
       System.out.println("Do you want to hit or stay?");
       String playerInp = scan.next();
        playerInp=playerInp.toLowerCase();
//Player input hit or stay loop
       while(playerInp!="stay"&&player.getPlayerTotal()<21){
        dealer.dealToPlayer(player);
        player.showHand();
        System.out.println("Player Points:"+player.getPlayerTotal());

        //checking if the player wants to keep playing or not
        System.out.println("Do you want to hit or stay? (hit/stay)");
        playerInp = scan.next();
        playerInp=playerInp.toLowerCase();
       }
//Conditions to check if player busted or just stayed
       if(player.getPlayerTotal()==21){
           System.out.println("You win!");
           dealer.showDeck();
       }
       else if(player.getPlayerTotal()>21)
       {
        System.out.println("You lose!");
        dealer.showDeck();
       }
      //Dealer turn
       else if(player.getPlayerTotal()<21 && playerInp=="stay"){
    //dealing to dealer as long as dealer's total points are under 16
        while(dealer.getPlayerTotal()<16){
               System.out.println("Dealing to dealer");
                dealer.dealToDealer();
            }
            if(dealer.getPlayerTotal()==21){
                System.out.println("Dealer wins!");
                dealer.showDeck();
            }
            else if(dealer.getPlayerTotal()>21)
            {
                System.out.println("Dealer busts");
                dealer.showDeck();
            }
         //If player stays and dealer stays its a tie!
            else if(dealer.getPlayerTotal()==player.getPlayerTotal()){
                System.out.println("Its a Tie!");
                dealer.showDeck();
            }
       
       }

       System.out.println("Would you like to play another game of Black Jack? (yes/no)");
       gameInp=(scan.next()).toLowerCase();
       
    }

//closing Scanner for stopping recsource leak
scan.close();
   }

}