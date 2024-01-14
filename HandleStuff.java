import java.util.HashMap;

class HandleStuff {
    String type;
    double price;
    double rating;
    Store[] stores;
    String currStop = "home";
    HashMap<String, Store> storeNames = new HashMap<>();
    Graph graph = new Graph();
    public HandleStuff() {
        //name, storerating, names, prices, clothes rating
        Store store1 = new Store("High_Quality_Shirt", 4.2, new String[]{"black_shirt", "White_shirt", "Pink_shirt", "Green_shirt", "Orange_shirt"}, new double[]{30, 20, 30, 20, 15},new double[]{5, 5, 4, 4, 3});
        Store store2 = new Store("High_Quality_Pants", 4.1, new String[]{"short_pants","long_pants","black_jeans","blue_jeans","sweatpants"}, new double[]{30, 20, 30, 20, 15},new double[]{5, 4.5, 4, 4, 3});
        Store store3 = new Store("Pants_And_Shirts", 3.5, new String[]{"adidas_pants","puma_pants","nike_pants", "adidas_shirt", "puma_shirt", "nike_shirt"}, new double[]{25, 20, 20, 20, 17, 14},new double[]{4, 3.5, 2.5, 4, 3.5, 2.5});
        Store store4 = new Store("Shoe_Store", 3.8, new String[]{"running_shoes", "sneakers", "formal_shoes", "slippers", "sandals"}, new double[]{50, 40, 60, 25, 20}, new double[]{4.5, 4, 4.8, 3.5, 3});
        Store store5 = new Store("Underwear_Shirt_Store", 3.7, new String[]{"boxer_briefs", "briefs", "boxers", "v-neck_shirt", "crew_neck_shirt", "polo_shirt"}, new double[]{15, 18, 20, 25, 30, 35}, new double[]{4, 3.5, 3.8, 3.9, 4.2, 4.5});
        Store store6 = new Store("Everything_Store", 3.0, new String[]{"jeans", "chinos", "t-shirt", "polo_shirt", "sneakers", "loafers", "boxer_briefs", "briefs"}, new double[]{40, 30, 20, 25, 50, 60, 15, 18}, new double[]{4, 3.5, 3.8, 3.9, 4.5, 4.2, 4, 3.5});
        stores = new Store[]{store1, store2, store3, store4, store5, store6};

        storeNames.put("High_Quality_Shirt", store1);
        storeNames.put("High_Quality_Pants", store2);
        storeNames.put("Pants_And_Shirts", store3);
        storeNames.put("Shoe_Store", store4);
        storeNames.put("Underwear_Shirt_Store", store5);
        storeNames.put("Everything_Store", store6);
        setHome();

    }

    public void updateStore(String name){
        currStop = name;
        graph.setData(storeNames.get(currStop), stores);
    }

    public void setHome(){
        graph.setData(stores);
        System.out.println("hello");
    }


    public double homeDistance(){
        return graph.homeDistance();
    }

    public double homeTime(){
        return graph.homeTime();
    }

    
        

    public Store getStore(String storeName){
        return storeNames.get(storeName);
    }


    

    public void sortByDistance(){
        int storesNum = 7;
        for (int i = 0; i < storesNum - 1; i++){
            for (int j = 0; j < storesNum - i - 1; j++){
                
                
                if (stores[j].getDistance() > stores[j + 1].getDistance()) {
                        // Swap temp and arr[i]
                        Store temp = stores[j];
                        stores[j] = stores[j + 1];
                        stores[j + 1] = temp;
                }
            }
        }
    }   

    public void sortByTime(){
        int storesNum = 6;
        for (int i = 0; i < storesNum - 1; i++){
            for (int j = 0; j < storesNum - i - 1; j++){
                
                
                if (stores[j].getTime() > stores[j + 1].getTime()) {
                        // Swap temp and arr[i]
                        Store temp = stores[j];
                        stores[j] = stores[j + 1];
                        stores[j + 1] = temp;
                }
            }
        }
    }
    
    public void sortByRating(){
        int storesNum = 6;
        for (int i = 0; i < storesNum - 1; i++){
            for (int j = 0; j < storesNum - i - 1; j++){
                
                
                if (stores[j].getRating() > stores[j + 1].getRating()) {
                        // Swap temp and arr[i]
                        Store temp = stores[j];
                        stores[j] = stores[j + 1];
                        stores[j + 1] = temp;
                }
            }
        }
    }

    public Store[] getStores(){
        return stores;
    }

    public String getCurrentStore(){
        return currStop;
    }




}