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

    /*
     * Main method to run the game
     * @param args Command line arguments
     * @throws Exception Any exception that might occur during the game
     */
    public static void main(String[] args) throws Exception {
        // User Initialization
        System.out.println("\nWelcome to Ayad and Mehir's game.");

        System.out.println("\nFun Fact!\nThe shortest distances and times to travel between stores is calculated using the bellman ford algorithm. Edge list representation is used to store the graph of the roads!");
        System.out.println("\nThis is a shopping game. You will be asked to pick a difficulty level, which will determine your initial amount of time and money to start with.\n");

        getDifficulty();

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

    /*
     * Method to get an array of Store objects
     * @return Array of Store objects representing all stores
     */
    public static Store[] getStores() {
        // Array to store all stores
        Store[] allStores = storeHandler.getStores();
        return allStores;
    }

    /*
     * Method for the user to pick the difficulty level
     */
    private static void getDifficulty() {
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

    /*
     * Method to get user input with error handling
     * @return User input as an integer
     */
    private static int getInput() {
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

    /*
     * Method to initialize a timer thread to decrement timer every minute
     * @param initialTime Initial time value for the timer
     */
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

    /*
     * Method to display available stores
     * get the stores from store.java and display them using 
     * the while loop to go through all the items in that array.
     */
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

    /*
     * Method to visit stores and make purchases
     */
    private static void visitStores() {
        Store[] allStores = getStores();
        int choice = 2;
        int pick = 0;
        String[] items = {};
        double[] prices = {};
        double[] ratings = {};

        // while the user wants to check stores
        while (choice == 2) {
            pick = 0;

            // while user is looking at the displayed stores (incase they want to sort again)
            while (pick == 0) {

                displayStores();
                System.out.println("\nYour budget is: " + wallet + "\tYour timer is: " + timer);
                System.out.println(
                        "\nPick the number of the store you want\npress 0 if you want to sort the stores\n");

                //get user pick for the store / sort.
                pick = getInput();

                // make sure index is in bounds
                while (pick > 6 || pick < 0) {
                    System.out.println("You selected a number out of bounds");
                    displayStores();

                    System.out.println(
                            "\nPick the number of the store you want\npress 0 if you want to sort the stores\n");
                    pick = getInput();
                }

                // if user wants to sort
                if (pick == 0) {
                    System.out.println("1)by distance(costs $1/unit of distance traveled)\t2)by time\t3)by rating\t4)don't sort");
                    int sort = getInput();

                    // make sureindex out of bounds 
                    while (!(sort < 4 && sort > 0)) {
                        System.out.println("You entered a number out of bounds");
                        System.out.println("1)by distance(costs $1/unit of distance traveled)\t2)by time\t3)by rating\t4)don't sort");
                        sort = getInput();
                    }

                    if (sort == 1) {
                        storeHandler.sortByDistance();
                    } else if (sort == 2) {
                        storeHandler.sortByTime();
                    } else if (sort == 3) {
                        storeHandler.sortByRating();
                    }
                }
            }

            // get all the items from each store using methods in Handlestores and display them
            items = allStores[pick - 1].getItemNames();
            prices = allStores[pick - 1].getPrices();
            ratings = allStores[pick - 1].clothesRatings();
            displayItems(items, prices, ratings);

            // offer user to travel to store or check other stores out
            System.out.println("1)Go to the store    2)Pick another store");
            choice = getInput();
            while (choice > 2 || choice < 1) {
                System.out.println("You selected a number out of bounds");
                System.out.println("\n1)Go to the store    2)Pick another store");
                choice = getInput();
            }
        }

        // when user finally travels, deduct travel costs from wallet and travel time from timer
        wallet -= allStores[pick - 1].getDistance();
        timer -= allStores[pick - 1].getTime();

        // update the user location so that the time to travel to each store + costs change based on his current location
        storeHandler.updateStore(allStores[pick - 1].toString());

        // let the user buy from the store he is in
        checkout(items, prices, ratings);
    }

    /*
     * Method to display all the items in a store
     * @param items Array of item names
     * @param prices Array of item prices
     * @param ratings Array of item ratings
     */
    private static void displayItems(String[] items, double[] prices, double[] ratings) {
        System.out.println("Number\tItem\t\tPrice\t\tRating");
        for (int i = 0; i < items.length; i++) {
            System.out.println(
                    i + 1 + ")\t" + items[i] + "\t" + prices[i] + " Dollars\t" + ratings[i] + " Stars");
        }
    }

    /*
     * Method to allow the user to checkout by buying items from the selected store
     * @param items Array of item names
     * @param prices Array of item prices
     * @param ratings Array of item ratings
     */
    private static void checkout(String[] items, double[] prices, double[] ratings) {

        int stay = 1;

        // while the user wants to stay in the store
        while (stay == 1) {

            System.out.println("\nBudget: " + wallet + "\tTime: " + timer);
            System.out.println("\nWelcome to the store! Here are the available items:\n");

            displayItems(items, prices, ratings);

            System.out.println("\nPick the number of the item you want to buy");
            int buy = getInput();

            // if index out of bounds, display items again and get input again
            while (buy > items.length || buy < 1) {
                System.out.println("Number out of bounds");

                displayItems(items, prices, ratings);
                buy = getInput();
            }

            // deduct costs from wallet
            wallet -= prices[buy - 1];
            System.out.println("\nYou have bought " + items[buy - 1]);
            System.out.println("Wallet: " + wallet + "\tTimer: " + timer);

            //put the stuff the user bought in an inventory
            fileHandler.writeClosetToFile(items[buy - 1], prices[buy - 1], ratings[buy - 1]);

            System.out.println("Do you want to buy anything else from this store? \n1) Yes    2) No");
            stay = getInput();
        }
    }
}
