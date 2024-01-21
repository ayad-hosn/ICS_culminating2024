import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class HandleFiles {

    // Variables to track wallet and timer values
    private double wallet = 0;
    private double timer = 0;

    /*
     * Calculate the player's score based on money, time, and average item rating
     * @param money The remaining money in the wallet
     * @param time The remaining time on the timer
     * @return The calculated score
     */
    double calculateScore(double money, double time) {
        double totalRating = 0;
        int itemCount = 0; // Track the number of items

        try (BufferedReader reader = new BufferedReader(new FileReader("my_closet.txt"))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (count != 0) {
                    String[] parts = line.split(" ");
                    if (parts.length == 3) {
                        double itemRating = Double.parseDouble(parts[2]);
                        totalRating += itemRating;
                        itemCount++;
                    }
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double avgRating = (itemCount > 0) ? totalRating / itemCount : 0; // Avoid division by zero
        double score = (avgRating / 3) + (money / 3) + (time / 3);
        return Math.round(score * 100.0) / 100.0;
    }

    /*
     * Empty the closet by truncating the file and writing the header
     */
    void emptyCloset() {
        try (FileWriter writer = new FileWriter("my_closet.txt", false)) {
            FileWriter newWriter = new FileWriter("my_closet.txt", true);
            newWriter.write("Item_Name Price Rating\n");
            writer.close();
            newWriter.close();
            // Writing nothing to truncate the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Write an item to the closet file
     * @param name The name of the item
     * @param cost The cost of the item
     * @param stars The rating of the item
     */
    void writeClosetToFile(String name, double cost, double stars) {
        try (FileWriter writer = new FileWriter("my_closet.txt", true)) {
            writer.write(name + " " + cost + " " + stars + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Display available difficulty levels from the difficulty file
     */
    void displayDifficultyLevels() {
        try (BufferedReader reader = new BufferedReader(new FileReader("difficulty.txt"))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (count > 1) {
                    System.out.print((count - 1) + ". ");
                }
                System.out.println(line);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Read wallet and timer values from the difficulty file based on the selected difficulty level
     * @param selectedDifficulty The difficulty level selected by the user
     */
    void readDifficultyFromFile(String selectedDifficulty) {
        try (BufferedReader reader = new BufferedReader(new FileReader("difficulty.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String difficulty = parts[0].trim().toLowerCase();
                    if (difficulty.equals(selectedDifficulty)) {
                        String[] values = parts[1].trim().split(" ");
                        if (values.length == 6) {
                            this.wallet = Double.parseDouble(values[0]);
                            this.timer = Double.parseDouble(values[5]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Get the current wallet value
     * @return The wallet valuegit sta
     */
    double getWallet() {
        return wallet;
    }

    /*
     * Get the current timer value
     * @return The timer value
     */
    double getTimer() {
        return timer;
    }
}
