import java.util.HashMap;

class HandleStuff {
    String type;
    double price;
    double rating;
    Store[] stores;
    String currStop = "home";
    HashMap<Integer, Store> storeNames = new HashMap<>();
    
    public HandleStuff() {
        Store store1 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        Store store2 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        Store store3 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        Store store4 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        Store store5 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        Store store6 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        stores = new Store[]{store1, store2, store3, store4, store5, store6};

        storeNames.put(1, store1);
        storeNames.put(2, store2);
        storeNames.put(3, store3);
        storeNames.put(4, store4);
        storeNames.put(5, store5);
        storeNames.put(6, store6);

    }

    public void updateStore(String name){
        currStop = name;
    }

    
        

    public Store getStore(int storeNum){
        return storeNames.get(storeNum);
    }


    

    public void sortByDistance(){
        int storesNum = 6;
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