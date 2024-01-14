import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class Store {
    String name;
    double distance;
    double time;
    double storeRating;
    
    HashMap<String, double[]> clothes = new HashMap<>();
    String[] names;
    double[] prices;
    double[] clothesRatings;
    
    public Store(String name, double storeRating, String[] names, double[]prices, double[]clothesRatings) {
        this.name = name;
        
        this.storeRating = storeRating;
        this.clothesRatings = clothesRatings;
        this.names = names;
        this.prices = prices;
        buildHashMap();
    }


    public void buildHashMap(){
        int size = names.length;
        for (int i=0;i<size;i++){
            clothes.put(names[i], new double[]{clothesRatings[i],prices[i]});
        }

    }

    public double getTime(){
        return time;
    }


    public double getDistance(){
        return distance;
    }

    public String[] getItemNames(){
        return names;
    }

    public double[] getPrices(){
        return prices;
    }

    public double[] clothesRatings(){
        return clothesRatings;
    }

    public void setTime(double time){
        this.time = time;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }


    public double getRating(){
        return storeRating;
    }

    @Override
    public String toString(){
        return name;
    }


     
}