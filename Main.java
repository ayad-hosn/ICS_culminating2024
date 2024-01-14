import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    Scanner inp = new Scanner(System.in);
    HandleStuff stuff = new HandleStuff();
    Store[] allStores = stuff.getStores();

    double timer;
    double wallet;

    public void main(String[] args) {
        // User Initialization
        System.out.println("Enter initial money: ");
        double initialMoney = inp.nextDouble();
        wallet = initialMoney;

        System.out.println("Enter initial time: ");
        double initialTime = inp.nextDouble();
        timer = initialTime;
        

        System.out.println("Welcome to kool 5ara.");
        System.out.println("In your balance you have, "+ wallet + " and you have " + timer +" minutes to spend it on buying a new wardrobe");
        System.out.println("Please spend your money and time wisely to pick out the best wardrobe possible");
        
        Integer start = 0;
        while(start != 1){
            System.out.println("press 1 to start and good luck player");
            start = inp.nextInt();
        }
        

        while(start == 1){
            visitStores();
                        

        } 

    }

    private static double calculateScore(List<ClothingItem> closet, double timeLeft, double moneyLeft) {
        // Implement the scoring formula here
        // [(2 * rating of shirt) + (2 * rating of pants) + ...] / 5 + timeLeft * 0.5 + moneyLeft
        // Return the calculated score
        return 0; // Placeholder, replace with the actual calculation
    }
    
    private static void writeClosetToFile(List<ClothingItem> selectedItems) {
        try (FileWriter writer = new FileWriter("my_closet.txt", true)) {
            for (ClothingItem item : selectedItems) {
                // Writing each item to the file
                writer.write(String.format("%s %.2f %.2f%n", item.type, item.price, item.rating));
            }
            System.out.println("Selected items written to my_closet.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void visitStores(){
        System.out.println("these are all the stores");
        int choice = 2;
        int pick = 0;
        String[] itemss;
        double[] pricess;
        double[] ratingss;
        while(choice == 2){
        // list all available stores
        for(int i=0; i<6; i++){
            System.out.println(i+1 + ") " +allStores[i].toString() + allStores[i].getRating() + allStores[i].getDistance() + allStores[i].getTime());
        }

        // let the user pick his store
        System.out.println("pick the number of the store you want(1-6)\n");

        pick = inp.nextInt();
        itemss = allStores[pick-1].getItemNames();
        pricess = allStores[pick-1].getPrices();
        ratingss = allStores[pick-1].clothesRatings();

        for(int i=0 ; i < allStores[pick-1].getItemNames().length; i ++){
            System.out.println(itemss[i] + pricess[i] + ratingss[i]);
        }

        // let the user pick if he wants to go there
        System.out.println("1)go to store        2)pick another store");  
        choice = inp.nextInt();
        }
        stuff.updateStore(allStores[pick-1].toString());

        wallet -= allStores[pick-1].getDistance();
        timer -= allStores[pick-1].getTime();
        checkout(itemss, pricess, ratingss);
    }

    private void checkout(String[] items, double[] prices, double[] ratings){
        for(int i = 0; i < items.length; i ++){
            System.out.println(items[i] + prices[i] + ratings[i]);
        }

        System.out.println("\n ")

                
    }


}