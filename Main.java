import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

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

        List<ClothingItem> selectedItems = new ArrayList<>();

        // Store initialization (Add your stores and items here)
        Store storeA = new Store("Store A", 2, 5, 4.5);
        storeA.items.add(new ClothingItem("Shirt", 20, 4.5));
        storeA.items.add(new ClothingItem("Pants", 30, 4.0));

        // Add more stores and items...

        // User initialization
        double wallet = initialMoney;
        double timer = initialTime;
        List<ClothingItem> closet = new ArrayList<>();

        // Game loop
        while (timer > 0) {
            // Display available stores, let the user choose a store, and navigate
            System.out.println("Available Stores: ");
            System.out.println("1. " + storeA.name);
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
        System.out.println("Game Over! Your Score: " + score);

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

}
