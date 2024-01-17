import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class UserInterface {
    static Scanner inp = new Scanner(System.in);
    static HandleStores stuff = new HandleStores();
    static Store[] allStores = stuff.getStores();

    static double timer;
    static double wallet;

    static boolean gameStat = true;

    public static void main(String[] args) throws Exception {

        // User Initialization
        System.out.println("\nWelcome to ayad and mehir's gaym.");
        System.out.println("This is a shopping game. You will be asked to pick an initial amount of time and money to start with,");
        System.out.println("as well as a recommended amount for each (you should go over the recommended amount for a better experience).");

        // User enters initial money and time
        System.out.println("\nEnter initial money (recommended 150): ");
        double initialMoney = inp.nextDouble();
        wallet = initialMoney;

        System.out.println("Enter initial time (recommended 200): ");
        double initialTime = inp.nextDouble();
        timer = initialTime;
        initializeTimer(initialTime);

        // Display user's initial balance
        System.out.println("\nIn your balance you have, "+ wallet + " dollars and you have " + timer +" minutes to spend it on buying a new wardrobe.");
        System.out.println("Please spend your money and time wisely to pick out the best wardrobe possible.");
        
        // Prompt user to start the game
        Integer start = 0;
        while(start != 1){
            System.out.println("\nPress 1 to start and good luck player.");
            start = inp.nextInt();
        }

        // Main game loop
        while(start == 1){
            visitStores();
            System.out.println("Would you like to 1)buy anything else      2)go home");
            start = inp.nextInt();
        }
        gameStat = false;

        // Game over message with final score
        
        System.out.println("Congratulations for finishing the game! Your score is " + calculateScore());
        gameStat = false;
        // Reset the text file "my_closet.txt"
        try (FileWriter writer = new FileWriter("my_closet.txt", false)) {
            // Writing nothing to truncate the file
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
        
    }

    // Initialize a timer thread to decrement timer every minute
    private static void  initializeTimer(double initialTime) {
        timer = initialTime;
        
        Thread timerThread = new Thread(() -> {
            
            while (true) {
                try {
                    Thread.sleep(60000); // Sleep for one minute
                    timer -= 1;  // Decrease the timer by 1 minute
                    if (gameStat){
                        System.out.println("Time left: " + timer + " minutes\tMoney left: " + wallet + " Dollars");
                    }
                    
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        timerThread.start();
        
        
                
    }

    // Calculate and return the final score
    private static double calculateScore() {
        double totalRating = 0;
        int itemCount = 0; // Track the number of items

        try (BufferedReader reader = new BufferedReader(new FileReader("my_closet.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    double itemRating = Double.parseDouble(parts[2]);
                    totalRating += itemRating;
                    itemCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double avgRating = (itemCount > 0) ? totalRating / itemCount : 0; // Avoid division by zero
        double score = (avgRating / 3) + (wallet / 3) + (timer / 3);
        return score;
    }

    // Write selected items to "my_closet.txt" file
    private static void writeClosetToFile(String name, double cost, double stars) {
        try (FileWriter writer = new FileWriter("my_closet.txt", true)) {
            writer.write(name + " " + cost + " " + stars + "\n");
            System.out.println("\nSelected items written to my_closet.txt\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void displayStores(){
        System.out.println("These are all the stores along with the costs to get there:\n");
        System.out.println("Number\tStore_Name\t\tRating\tPrice\tTime");
        for(int i=0; i<6; i++){
            System.out.println(i+1 + ")\t" +allStores[i].toString() + "\t"+allStores[i].getRating() + "\t"+allStores[i].getDistance() + "\t"+allStores[i].getTime());
        }
    }

    // Display available stores and prompt user to choose
    private static void visitStores(){
       
        int choice = 2;
        int pick = 0;
        String[] items = {};
        double[] prices = {};
        double[] ratings = {};

        while(choice == 2){
            pick = 0;
            while(pick == 0){

                // List all available stores
                displayStores();

                // Let the user pick his store
                System.out.println("\nPick the number of the store you want\npress 0 if you want to sort the stores\n");
                
                
                pick = inp.nextInt();

                //keep index in bounds                
                while (pick>6 || pick<0){
                    System.out.println("You selected a number out of bounds");
                    displayStores();
                    
                    System.out.println("\nPick the number of the store you want\npress 0 if you want to sort the stores\n");
                    pick = inp.nextInt();
                }

                //sorting method        
                if(pick == 0){
                    System.out.println("1)by price\t2)by time\t3)by rating\t4)dont sort");
                    int sort = inp.nextInt();

                    //keep index in bounds
                    while (!(sort<4&&sort>0)){
                        System.out.println("You entered a number out of bounds");
                        System.out.println("1)by price\t2)by time\t3)by rating\t4)dont sort");
                        sort = inp.nextInt();
                    }

                    //sort options
                    if(sort == 1){
                        stuff.sortByDistance();
                    }else if(sort == 2){
                        stuff.sortByTime();
                    }else if(sort == 3){
                        stuff.sortByRating();
                    }
                }
            }            

           // Retrieve items, prices, and ratings for the selected store
            items = allStores[pick-1].getItemNames();
            prices = allStores[pick-1].getPrices();
            ratings = allStores[pick-1].clothesRatings();

            // Display a preview of the items at the selected store
            System.out.println("number\titem\t\tprice\t\trating");
            for(int i = 0; i < items.length; i++){
                System.out.println(i+1 + ")\t" + items[i] + "\t" + prices[i] + " Dollars\t" + ratings[i] + " Stars");
            }

            // Let the user decide if they want to go to the store or pick another store
            System.out.println("1)Go to the store    2)Pick another store"); 
            choice = inp.nextInt();
            while (choice>2||choice<1){
                System.out.println("You selected a number out of bounds");
                System.out.println("\n1)Go to the store    2)Pick another store"); 
                choice = inp.nextInt();
            } 
            
        }

        // Update the selected store and deduct money and time
        
        wallet -= allStores[pick-1].getDistance();
        timer -= allStores[pick-1].getTime();
        stuff.updateStore(allStores[pick-1].toString());
        
        // Proceed to checkout
        checkout(items, prices, ratings);
    }

    // Allow the user to checkout by buying items from the selected store
    private static void checkout(String[] items, double[] prices, double[] ratings){
        int buy2 = 1;

        while(buy2 == 1){
            System.out.println("\nBudget: "+ wallet + "\tTime: " + timer);
            System.out.println("\nWelcome to the store! Here are the available items:\n");

            System.out.println("number\titem\t\tprice\t\trating");
            for(int i = 0; i < items.length; i++){
                System.out.println(i+1 + ")\t" + items[i] + "\t" + prices[i] + " Dollars\t" + ratings[i] + " Stars");
            }

            // Let the user pick the number of the item they want to buy
            System.out.println("\nPick the number of the item you want to buy");
            int buy = inp.nextInt();
        
            // Deduct the cost of the item from the wallet
            wallet -= prices[buy-1];
            System.out.println("\nYou have bought " + items[buy-1] + ", and currently have " + wallet + " Dollars in your wallet");

            // Ask if the user wants to buy anything else from this store
            System.out.println("Do you want to buy anything else from this store? \n1) Yes    2) No");
            buy2 = inp.nextInt(); 

            // Write the purchased item to the closet file
            writeClosetToFile(items[buy-1], prices[buy-1], ratings[buy-1]);
        }                  
    }

}