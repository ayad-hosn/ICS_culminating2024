import java.util.HashMap;

class HandleStuff {
    String type;
    double price;
    double rating;
    Store[] stores;
    String currStop = "home";
    HashMap<String, Store> storeNames = new HashMap<>();
    public HandleStuff() {
        Store store1 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        Store store2 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        Store store3 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        Store store4 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        Store store5 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        Store store6 = new Store("hello", 1, new String[]{"a", "b"}, new double[]{1, 2},new double[]{1, 2});
        stores = new Store[]{store1, store2, store3, store4, store5, store6};

        storeNames.put("hello", store1);
        storeNames.put("hello", store2);
        storeNames.put("hello", store3);
        storeNames.put("hello", store4);
        storeNames.put("hello", store5);
        storeNames.put("hello", store6);

    }

    public void updateStore(String name){
        currStop = name;
    }
        

    public Store getStore(String name){
        return storeNames.get(name);
    }


    

    public void sortByDistance(){
        System.out.println("hello");
    }

    public void sortByTime(){
        System.out.println("hello");
    }
    
    public void sortByRating(){
        System.out.println("hello");
    }

    public Store[] getStores(){
        return stores;
    }




}