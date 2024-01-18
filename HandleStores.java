/* 
 *  The HandleStores class extends the Map class and 
 * manages stores and their information
 */

import java.util.HashMap;

class HandleStores extends Map{
   
     
    //represents all the stores in an array
    Store[] stores;
    //represents the store that the user is currently in
    String currStop = "home";
    //this hashmap is used to quickly match the store name to the instance of the store

    HashMap<String, Store> storeNames = new HashMap<>();

    HandleStores() {
        //call the constructor of the map class
        super();
        //stores name, store rating, names, prices, clothes rating respectively are passed into the Store constructor
        Store store1 = new Store("Quality_Shirts  ", 4.2, new String[]{"black_shirt", "White_shirt", "Pink_shirt", "Green_shirt", "Orange_shirt"}, new double[]{30, 20, 30, 20, 15},new double[]{5, 5, 4, 4, 3});
        Store store2 = new Store("Quality_pants   ", 4.1, new String[]{"short_pants","long_pants","black_jeans","blue_jeans","sweatpants"}, new double[]{30, 20, 30, 20, 15},new double[]{5, 4.5, 4, 4, 3});
        Store store3 = new Store("Pants&Shirts    ", 3.5, new String[]{"adidas_pants","puma_pants","nike_pants", "adidas_shirt", "puma_shirt", "nike_shirt"}, new double[]{25, 20, 20, 20, 17, 14},new double[]{4, 3.5, 2.5, 4, 3.5, 2.5});
        Store store4 = new Store("Shoe_Store      ", 3.8, new String[]{"running_shoes", "sneakers", "formal_shoes", "slippers", "sandals "}, new double[]{50, 40, 60, 25, 20}, new double[]{4.5, 4, 4.8, 3.5, 3});
        Store store5 = new Store("Underwear_Store ", 3.7, new String[]{"boxer_briefs", "briefs  ", "boxers  ", "v-neck_shirt", "crew_neck_shirt", "polo_shirt"}, new double[]{15, 18, 20, 25, 30, 35}, new double[]{4, 3.5, 3.8, 3.9, 4.2, 4.5});
        Store store6 = new Store("Everything_Store", 3.0, new String[]{"jeans\t", "chinos\t", "t-shirt\t", "polo_shirt", "sneakers", "loafers\t", "boxer_briefs", "briefs\t"}, new double[]{40, 30, 20, 25, 50, 60, 15, 18}, new double[]{4, 3.5, 3.8, 3.9, 4.5, 4.2, 4, 3.5});
        //initialize the array with all the stores we just created
        this.stores = new Store[]{store1, store2, store3, store4, store5, store6};
        //assign each store name its instance in the //this hashmap
        this.storeNames.put("Quality_Shirts  ", store1);
        this.storeNames.put("Quality_pants   ", store2);
        this.storeNames.put("Pants&Shirts    ", store3);
        this.storeNames.put("Shoe_Store      ", store4);
        this.storeNames.put("Underwear_Store ", store5);
        this.storeNames.put("Everything_Store", store6);
        // resets the graph so that the distance and time attribute of each store is
        //the distance from home
        //this method is called when the instance of this class is created
        setData("home",stores); 
    }           
    
    /* Updates the current stop and 
    sets the data for the graph based on the store name 
    @param name - name of the store
    @return void method returns nothing
    */
    void updateStore(String name){
      
        currStop = name; // Update the current stop to the given store name
        setData(currStop, stores); // Set the graph data for the current stop

    }

    
        
    /* Retrieves a Store object based on the store name 
     * @params storeName - the name of store whose instance needs to be accessed
    */

    Store getStore(String storeName){
        //return the instance of the store using the hashmap
        return storeNames.get(storeName);
    }


    
    /* Sorts the stores array by distance using bubble sort 
    @param none
    @return void method returns nothing
    */
    void sortByDistance(){

        int storesNum = stores.length; // Use the length of the stores array for the number of stores
        for (int i = 0; i < storesNum - 1; i++){
            for (int j = 0; j < storesNum - i - 1; j++){
                // Compare distances and swap if necessary
                if (stores[j].getDistance() > stores[j + 1].getDistance()) {
                        Store temp = stores[j]; // Temporary store for swapping
                        stores[j] = stores[j + 1]; // Swap stores
                        stores[j + 1] = temp;
                }
            }
        }
    }   

    /* Sorts the stores array by time using bubble sort 
    @param none
    @return void method returns nothing
    */
    void sortByTime(){
        int storesNum = stores.length; // Use the length of the stores array for the number of stores
        for (int i = 0; i < storesNum - 1; i++){
            for (int j = 0; j < storesNum - i - 1; j++){
                // Compare times and swap if necessary
                if (stores[j].getTime() > stores[j + 1].getTime()) {
                        Store temp = stores[j]; // Temporary store for swapping
                        stores[j] = stores[j + 1]; // Swap stores
                        stores[j + 1] = temp;
                }
            }
        }
    }
    

    /* Sorts the stores array by rating using bubble sort
     * @param none
     * @return void method returns nothing
     */
    void sortByRating(){
        int storesNum = 6;
        for (int i = 0; i < storesNum - 1; i++){
            for (int j = 0; j < storesNum - i - 1; j++){
                
                
                if (stores[j].getRating() < stores[j + 1].getRating()) {
                        // Swap temp and arr[i]
                        Store temp = stores[j];
                        stores[j] = stores[j + 1];
                        stores[j + 1] = temp;
                }
            }
        }
    }

    /* 
     * Method to retrieve the array of Store objects
     * @param nothing
     * @return return the array of store sorted by distance, time or rating
     */
    Store[] getStores(){
        
        return stores;
    }

    /* Method to get the name of the current store
     * @param none
     * @return return the name of the current stop
     */
    String getCurrentStore(){
        return currStop;
    }




}