import java.util.Scanner;
import java.util.InputMismatchException;
public class UserInterface {
    // Scanner for user input
    static Scanner scanner = new Scanner(System.in);

    // HandleStores instance to manage store-related operations
    static HandleStores storeHandler = new HandleStores();

    // HandleFiles instance to manage file writing/reading operations
    static HandleFiles fileHandler = new HandleFiles();

    

    // Variables to track time, wallet, and game status
    static double timer;
    static double wallet;
    

    public static void main(String[] args) throws Exception {
        // User Initialization
        System.out.println("\nWelcome to Ayad and Mehir's game.");

        System.out.println("\nFun Fact!\nThe shortest distances and times to travel between stores is calculated using the bellman ford algorithm. Edge list representation is used to store the graph of the roads");
        System.out.println("\nThis is a shopping game. You will be asked to pick a difficulty level, which will determine your initial amount of time and money to start with.\n");


        
        getDifficulty();
        // Read difficulty level and set initial wallet and timer
        

        // Initialize the timer
        initializeTimer(timer);

        // Display user's initial balance
        System.out.println("\nIn your balance you have, " + wallet + " dollars and you have " + timer + " minutes to spend it on buying a new wardrobe.");
        System.out.println("Please spend your money and time wisely to pick out the best wardrobe possible.");

        // Prompt user to start the game
        Integer start = 0;
        while (start != 1) {
            System.out.println("\nPress 1 to start and good luck player.");
            start = getInput();
        }

        // Main game loop
        while (start == 1) {
            visitStores();
            System.out.println("Would you like to 1)buy anything else      2)go home");
            start = getInput();
        }
        

        // Game over message with final score
        System.out.println("Congratulations for finishing the game! Your score is " + fileHandler.calculateScore(wallet,timer));
        

        // Reset the text file "my_closet.txt"
        fileHandler.emptyCloset();
        System.exit(0);
    }


    public static Store[] getStores(){
        // Array to store all stores
        
        Store[] allStores = storeHandler.getStores();
        return allStores;
    }


    private static void getDifficulty(){
        // User picks difficulty level

        fileHandler.displayDifficultyLevels();
        System.out.println("\nPick a difficulty level:");
        
        int pick = getInput();
        
        String[] difficulties = {"easy", "easy+", "medium", "hard", "hard+", "extra hard"};

        while (pick > 6 || pick < 1) {
            System.out.println("You selected a number out of bounds");
           
            fileHandler.displayDifficultyLevels();
            System.out.println("\nPick a difficulty level:");
            
            pick = getInput();
        }
        fileHandler.readDifficultyFromFile(difficulties[pick-1]);
        wallet = fileHandler.getWallet();
        timer = fileHandler.getTimer();
        

        
    }


    private static int getInput(){
        
        
        Integer input = null;

        while (input == null) {
            try {
                
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: You did not enter a valid option. Please try again.");
                scanner.next(); // discard the non-integer input
            }
        }

        return input;
    
    }

        
    

    // Initialize a timer thread to decrement timer every minute
    private static void initializeTimer(double initialTime) {
        

        Thread timerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(60000); // Sleep for one minute
                    timer -= 1; // Decrease the timer by 1 minute
                    
                    System.out.println("Time left: " + timer + " minutes\tMoney left: " + wallet + " Dollars");
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        timerThread.start();
    }


    public static void displayStores() {
        Store[] allStores = getStores();
        System.out.println("These are all the stores along with the costs to get there:\n");
        System.out.println("Number\tStore_Name\t\tRating\tPrice\tTime");
        for (int i = 0; i < 6; i++) {
            System.out.println(
                    i + 1 + ")\t" + allStores[i].toString() + "\t" + allStores[i].getRating() + "\t"
                            + allStores[i].getDistance() + "\t" + allStores[i].getTime());
        }
    }

    // Display available stores and prompt user to choose
    private static void visitStores() {
        Store[] allStores = getStores();
        int choice = 2;
        int pick = 0;
        String[] items = {};
        double[] prices = {};
        double[] ratings = {};

        while (choice == 2) {
            pick = 0;
            while (pick == 0) {

                // List all available stores
                displayStores();

                // Let the user pick his store
                System.out.println("\nYour budget is: " + wallet + "\tYour timer is: " + timer);
                System.out.println(
                        "\nPick the number of the store you want\npress 0 if you want to sort the stores\n");

                pick = getInput();

                // keep index in bounds
                while (pick > 6 || pick < 0) {
                    System.out.println("You selected a number out of bounds");
                    displayStores();

                    System.out.println(
                            "\nPick the number of the store you want\npress 0 if you want to sort the stores\n");
                    pick = getInput();
                }

                // sorting method
                if (pick == 0) {
                    System.out.println("1)by distance(costs $1/unit of distance traveled)\t2)by time\t3)by rating\t4)don't sort");
                    int sort = getInput();

                    // keep index in bounds
                    while (!(sort < 4 && sort > 0)) {
                        System.out.println("You entered a number out of bounds");
                        System.out.println("1)by distance(costs $1/unit of distance traveled)\t2)by time\t3)by rating\t4)don't sort");
                        sort = getInput();
                    }

                    // sort options
                    if (sort == 1) {
                        storeHandler.sortByDistance();
                    } else if (sort == 2) {
                        storeHandler.sortByTime();
                    } else if (sort == 3) {
                        storeHandler.sortByRating();
                    }
                }
            }

            // Retrieve items, prices, and ratings for the selected store
            items = allStores[pick - 1].getItemNames();
            prices = allStores[pick - 1].getPrices();
            ratings = allStores[pick - 1].clothesRatings();

            // Display a preview of the items at the selected store
            
            displayItems(items, prices, ratings);
            // Let the user decide if they want to go to the store or pick another store
            System.out.println("1)Go to the store    2)Pick another store");
            choice = getInput();
            while (choice > 2 || choice < 1) {
                System.out.println("You selected a number out of bounds");
                System.out.println("\n1)Go to the store    2)Pick another store");
                choice = getInput();
            }

        }

       

        // Update the selected store and deduct money and time

        wallet -= allStores[pick - 1].getDistance();
        timer -= allStores[pick - 1].getTime();
        storeHandler.updateStore(allStores[pick - 1].toString());

        // Proceed to checkout
        checkout(items, prices, ratings);
    }

    private static void displayItems(String[] items, double[] prices, double[] ratings){
        System.out.println("Number\tItem\t\tPrice\t\tRating");
            for (int i = 0; i < items.length; i++) {
                System.out.println(
                        i + 1 + ")\t" + items[i] + "\t" + prices[i] + " Dollars\t" + ratings[i] + " Stars");
            }
    }

    // Allow the user to checkout by buying items from the selected store
    private static void checkout(String[] items, double[] prices, double[] ratings) {
        int buy2 = 1;

        while (buy2 == 1) {
            System.out.println("\nBudget: " + wallet + "\tTime: " + timer);
            System.out.println("\nWelcome to the store! Here are the available items:\n");

            displayItems(items, prices, ratings);

            // Let the user pick the number of the item they want to buy
            System.out.println("\nPick the number of the item you want to buy");
            int buy = getInput();

            while (buy>items.length||buy<1){
                System.out.println("Number out of bounds");
                
            
                displayItems(items, prices, ratings);
                buy = getInput();
            }
            // Deduct the cost of the item from the wallet
            wallet -= prices[buy - 1];
            System.out.println("\nYou have bought " + items[buy - 1]);
            System.out.println("Wallet: " + wallet + "\tTimer: " + timer);

            // Write the purchased item to the closet file
            fileHandler.writeClosetToFile(items[buy - 1], prices[buy - 1], ratings[buy - 1]);

            // Ask if the user wants to buy anything else from this store
            System.out.println("Do you want to buy anything else from this store? \n1) Yes    2) No");
            buy2 = getInput();
        }
    }

}
