import java.util.List;
import java.util.ArrayList;

class Store {
    String name;
    double distance;
    double time;
    double rating;
    List<ClothingItem> items;

    public Store(String name, double distance, double time, double rating) {
        this.name = name;
        this.distance = distance;
        this.time = time;
        this.rating = rating;
        this.items = new ArrayList<>();
    }
}