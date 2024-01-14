import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    static Scanner inp = new Scanner(System.in);
    static HandleStuff stuff = new HandleStuff();
    static Store[] allStores = stuff.getStores();

    static double timer;
    static double wallet;

    public static void main(String[] args) throws Exception {
        // User Initialization
        ImageLoader image = new ImageLoader();
        image.openImage();

        System.out.println("\nWelcome to ayad and mehir's gaym.");
        System.out.println("this is a shopping game, you will be asked to pick an initial amount of time and money to start with");
        System.out.println("as well as a recommended amount for each, (you should go over the recommended amount for a better experience");

        System.out.println("\nEnter initial money(recommended 150): ");
        double initialMoney = inp.nextDouble();
        wallet = initialMoney;

        System.out.println("Enter initial time(recommended 200): ");
        double initialTime = inp.nextDouble();
        timer = initialTime;
        initializeTimer(initialTime);
        
        System.out.println("\nIn your balance you have, "+ wallet + " dollars and you have " + timer +" minutes to spend it on buying a new wardrobe");
        System.out.println("Please spend your money and time wisely to pick out the best wardrobe possible");
        
        Integer start = 0;
        while(start != 1){
            System.out.println("\npress 1 to start and good luck player");
            start = inp.nextInt();
        }
        
        while(start == 1){
            visitStores();
            System.out.println("would you like to 1)buy anything else      2)go home");
            start = inp.nextInt();
        }
        System.out.println("congrats for finishing the game, your score is" + calculateScore()); 

        //reset the text file closet.txt
        try (FileWriter writer = new FileWriter("my_closet.txt", false)) {
            // Writing nothing to truncate the file
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void initializeTimer(double initialTime) {
        timer = initialTime;

        // Create a thread to decrement the timer every minute
        Thread timerThread = new Thread(() -> {
            while (true) {
                try {
                    // Sleep for one minute
                    Thread.sleep(60000);
                    timer -= 1;  // Decrease the timer by 1 minute
                    System.out.println("Time left: " + timer + " minutes");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timerThread.start();
    }


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
        double score = (avgRating / 2) + (wallet / 2);
        return score;
    }
    
    private static void writeClosetToFile(String name, double cost, double stars) {
        try (FileWriter writer = new FileWriter("my_closet.txt", true)) {
            writer.write(name + " " + cost + " " + stars + "\n");
            System.out.println("\nSelected items written to my_closet.txt\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void visitStores(){
        System.out.println("these are all the stores along with the costs to get there:\n");
        System.out.println("number    store_name    price     time");
        int choice = 2;
        int pick = 0;
        String[] itemss = {};
        double[] pricess = {};
        double[] ratingss = {};

        while(choice == 2){

        // list all available stores
         for(int i=0; i<6; i++){
            System.out.println(i+1 + ") " +allStores[i].toString() + " "+allStores[i].getRating() + " "+allStores[i].getDistance() + " "+allStores[i].getTime());
        }

        // let the user pick his store
        System.out.println("\npick the number of the store you want(1-6)\n");

        pick = inp.nextInt();
        itemss = allStores[pick-1].getItemNames();
        pricess = allStores[pick-1].getPrices();
        ratingss = allStores[pick-1].clothesRatings();

        System.out.println("Here is a preview of the items at this store");
        for(int i=0 ; i < allStores[pick-1].getItemNames().length; i ++){
            System.out.println(i+1 + ") " +itemss[i] + " " +pricess[i]+ "Dollars " + ratingss[i] + "Stars");
        }

        // let the user pick if he wants to go there
        System.out.println("1)go to store        2)pick another store");  
        choice = inp.nextInt();
        }
        stuff.updateStore(allStores[pick-1].toString());

        wallet = wallet - allStores[pick-1].getDistance();
        timer = timer - allStores[pick-1].getTime();

        checkout(itemss, pricess, ratingss);
    }

    private static void checkout(String[] items, double[] prices, double[] ratings){
        int buy2 = 1;

        while(buy2 == 1){

            System.out.println("\nbudget: "+wallet+"\ttime: " +timer);
            System.out.println("\nWelcome to the store, here are the available items");
            for(int i = 0; i < items.length; i ++){
                System.out.println(i+1 + ") " + items[i] + " " + prices[i] + "Dollars " + ratings[i] + "Stars");
            }

        System.out.println("\n pick the number of the item you want to buy");
        int buy = inp.nextInt();
        
        wallet = wallet - prices[buy-1];
        System.out.println("\nYou have bought " + items[buy-1] + ", and currently have " + wallet + " Dollars in your wallet");

        System.out.println("Do you want to buy anything else from this store? \n1)yes\t\t2)no");
        buy2 = inp.nextInt(); 

        writeClosetToFile(items[buy-1], prices[buy-1], ratings[buy-1]);
        }                  
    }
}