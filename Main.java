import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Initialization
        System.out.println("Enter initial money: ");
        double initialMoney = scanner.nextDouble();

        System.out.println("Enter initial time: ");
        double initialTime = scanner.nextDouble();

        double costOfSelectedItems = 0; // Initialize to calculate in the loop
        double timeSpent = 0; // Initialize to calculate in the loop

        // User initialization
        double wallet = initialMoney;
        double timer = initialTime;
        

        System.out.println("Welcome to kool 5ara.");
        System.out.println("In your balance you have, "+ wallet + " and you have " + timer +" minutes to spend it on buying a new wardrobe");
        System.out.println("Please spend your money and time wisely to pick out the best wardrobe possible");
        System.out.println("press 1 to start and good luck player");

        Integer start = scanner.nextInt();

        //an array with all the stores
        
        stuff.sortByRating();
        Store[] allStores = stuff.getStores();
        stuff.updateStore("storanme");
        for (int i=0;i<6;i++){
            System.out.println(allStores[i].toString());
            System.out.println(allStores[i].getRating());
            System.out.println(allStores[i].toString());
            
            //
            Store storeIwant = stuff.getStore("store name");
            String[] clothes = storeIwant.getItemNames();
            String[] prices = storeIwant.getPrices();

           
        }



        // Game loop
        while (start == 1 & timer > 0) {
            
            
            
            // Display more stores...

            System.out.println("Choose a store: ");
            int choice = scanner.nextInt();

            // Implement navigation and shopping logic here...
                
            // Update user's wallet and timer
            wallet -= costOfSelectedItems;
            timer -= timeSpent;

            // Update user's closet
            writeClosetToFile(selectedItems);


            // Continue the game loop or end the game when the user decides
        }

        // Calculate and display the user's score
        double score = calculateScore(closet, timer, wallet);
        System.out.println("Bye Bye! Your Score: " + score);

        // Log user's closet and score to a text file (implement file I/O)
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


    private static String listStores(string[] args){
        HandleStuff stuff = new HandleStuff();
        Scanner scanner = new Scanner(System.in);


        Store[] allStores = stuff.getStores();
        System.out.println("these are all the stores");
        
        for(int i=0; i<6; i++){
            System.out.println(i + ") " +allStores[i].toString() + allStores[i].getRating() + allStores[i].getDistance());
        }

        System.out.println("pick the number of the store you want\n");
        Integer pick = scanner.nextInt();
        System.out.println(allStores[pick]);
        
        
    }

    

}

}


