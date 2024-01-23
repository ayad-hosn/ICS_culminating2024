/**
 * The Store class represents a store with various attributes and methods.
 * Each store has a name, distance from the user's current location, time to 
 * reach from the user's current location, and a store rating.
 * 
 * @author Mehir Narayana
*/
class Store {
    //name of store
    private String name;
    //distance to the store the user is at currently from this store
    private double distance;
    //time to the store the user is at currently from this store
    private double time;
    //the rating of the store
    private double storeRating;
    
    //array containing all clothes names in order
    private String[] names;
    //array containing all prices in the same order as names
    private double[] prices;
    //array containing all clothes ratings in the same order names
    private double[] clothesRatings;
    //2d array storing prices and clothes ratings
    private double pricesRatings[][];
    Store(String name, double storeRating, String[] names, double[]prices, double[]clothesRatings) {
        this.name = name;
        
        this.storeRating = storeRating;
        this.clothesRatings = clothesRatings;
        this.names = names;
        this.prices = prices;
        pricesRatings = new double[][]{this.prices, this.clothesRatings};

    }


    
    /* Getter method for the time distance between stores
     * @return the time it takes to get from this store to the
     * store is the user is at 
    */
    
    double getTime(){
        return time;
    }

    /* Getter method for the distance between stores
     * @return the distance to get from this store to the
     * store is the user is at 
    */
    double getDistance(){
        return distance;
    }

     /* Getter method for the clothes names
     * @return the array containing the names of the clothes sold at 
     * this store
    */
    String[] getItemNames(){
        return names;
    }

    /* Getter method for the clothes prices
     * @return the array containing the prices of the clothes sold at 
     * this store
    */
    double[] getPrices(){
        return pricesRatings[0];
    }

    /* Getter method for the clothes ratings
     * @return the array containing the ratings of the clothes sold at 
     * this store
    */
    double[] clothesRatings(){
        return pricesRatings[1];
    }

    /* Setter method for the store time
     * @param time - the time it takes to get from this store
     * to the store is the user is currently at
    */
    void setTime(double time){
        this.time = time;
    }

    /* Setter method for the clothes names
     * @param time - the distance it takes to get from this store
     * to the store is the user is currently at
    */
    void setDistance(double distance){
        this.distance = distance;
    }

    /* Getter method for the store ratings
     * @return the rating of the store 
     * 
    */
    double getRating(){
        return storeRating;
    }

    /* Override .toString method to return name of the store
     * @return the name of the store
     * 
    */
    @Override
    public String toString(){
        return name;
    }


     
}
