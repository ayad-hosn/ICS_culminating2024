/* 
 *  The HandleFiles class has methods that control 
 * the reading and writing of a file parts of this program
 */

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class HandleFiles {

    private double wallet = 0;
    private double timer = 0;

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
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double avgRating = (itemCount > 0) ? totalRating / itemCount : 0; // Avoid division by zero
        double score = (avgRating / 3) + (money / 3) + (time / 3);
        return Math.round(score * 100.0) / 100.0;
    }

    void emptyCloset(){
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

    void writeClosetToFile(String name, double cost, double stars) {
    try (FileWriter writer = new FileWriter("my_closet.txt", true)) {
        writer.write(name + " " + cost + " " + stars + "\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


    // Display available difficulty levels
    void displayDifficultyLevels() {
    try (BufferedReader reader = new BufferedReader(new FileReader("difficulty.txt"))) {
        String line;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            if (count>1){
                System.out.print((count-1)+". ");
            }
            System.out.println(line);
            count++;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    // Read wallet and timer values from the difficulty file based on the selected difficulty level
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

    double getWallet(){
        return wallet;
    }

    double getTimer(){
        return timer;
    }
}
