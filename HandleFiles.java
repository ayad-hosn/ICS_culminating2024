import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class HandleFiles {


public double calculateScore(double money, double time) {

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
    return score;
    }

    public void emptyCloset(){
    try (FileWriter writer = new FileWriter("my_closet.txt", false)) {
        FileWriter newWriter = new FileWriter("my_closet.txt", true);
        newWriter.write("Item_Name Price Rating\n");
        writer.close();
        newWriter.close();
        // Writing nothing to truncate the file
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.exit(0);
    }

    public void writeClosetToFile(String name, double cost, double stars) {
    try (FileWriter writer = new FileWriter("my_closet.txt", true)) {
        writer.write(name + " " + cost + " " + stars + "\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


    // Display available difficulty levels
    public void displayDifficultyLevels() {
    try (BufferedReader reader = new BufferedReader(new FileReader("difficulty.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    // Read wallet and timer values from the difficulty file based on the selected difficulty level
    public boolean readDifficultyFromFile(String selectedDifficulty, double wallet, double timer) {
    try (BufferedReader reader = new BufferedReader(new FileReader("difficulty.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String difficulty = parts[0].trim().toLowerCase();
                if (difficulty.equals(selectedDifficulty)) {
                    String[] values = parts[1].trim().split(" ");
                    if (values.length == 2) {
                        wallet = Double.parseDouble(values[0]);
                        timer = Double.parseDouble(values[1]);
                        return true;
                    }
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
    }
}