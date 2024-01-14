import java.util.HashMap;

public class Graph {
    
    HashMap<String, Double> distances = new HashMap<>();
    HashMap<String, Double> times = new HashMap<>();
    Edge[] distanceEdges = new Edge[14];
    Edge[] timeEdges = new Edge[14];

    public Graph(){
;
        distanceEdges[0] = new Edge("home", "Quality_Shirts  ", 5);
        distanceEdges[1] = new Edge("Quality_Shirts  ",  "home", 5);
        distanceEdges[2] = new Edge("home",  "Quality_pants   ", 4);
        distanceEdges[3] = new Edge("Quality_pants   ", "home", 4);
        distanceEdges[4] = new Edge("Quality_Shirts  ", "Pants&Shirts    ", 3);
        distanceEdges[5] = new Edge("Pants&Shirts    ", "Quality_Shirts  ", 3);
        distanceEdges[6] = new Edge("Quality_pants   ", "Quality_Shirts  ", 6);
        distanceEdges[7] = new Edge("Quality_Shirts  ", "Quality_pants   ", 6);
        distanceEdges[8] = new Edge("Quality_pants   ", "Underwear_Store ", 5);
        distanceEdges[9] = new Edge("Underwear_Store ", "Quality_pants   ", 5);
        distanceEdges[10] = new Edge("Pants&Shirts    ", "Shoe_Store      ", 2);
        distanceEdges[11] = new Edge("Shoe_Store      ", "Shoe_Store      ", 2);
        distanceEdges[12] = new Edge("Underwear_Store ", "Everything_Store", 3);
        distanceEdges[13] = new Edge("Everything_Store", "Underwear_Store ", 3);

        timeEdges[0] = new Edge("home", "Quality_Shirts  ", 10);
        timeEdges[1] = new Edge("Quality_Shirts  ",  "home", 10);
        timeEdges[2] = new Edge("home",  "Quality_pants   ", 3);
        timeEdges[3] = new Edge("Quality_pants   ", "home", 3);
        timeEdges[4] = new Edge("Quality_Shirts  ", "Pants&Shirts    ", 4);
        timeEdges[5] = new Edge("Pants&Shirts    ", "Quality_Shirts  ", 4);
        timeEdges[6] = new Edge("Quality_pants   ", "Quality_Shirts  ", 2);
        timeEdges[7] = new Edge("Quality_Shirts  ", "Quality_pants   ", 2);
        timeEdges[8] = new Edge("Quality_pants   ", "Underwear_Store ", 1);
        timeEdges[9] = new Edge("Underwear_Store ", "Quality_pants   ", 1);
        timeEdges[10] = new Edge("Pants&Shirts    ", "Shoe_Store      ", 9);
        timeEdges[11] = new Edge("Shoe_Store      ", "Pants&Shirts    ", 9);
        timeEdges[12] = new Edge("Underwear_Store ", "Everything_Store", 6);
        timeEdges[13] = new Edge("Everything_Store", "Underwear_Store ", 6);
        

    }

    private void bellmanFord(String src) {
        
        distances.put("home", (double) Integer.MAX_VALUE);
        distances.put("Quality_Shirts  ", (double)Integer.MAX_VALUE);
        distances.put("Quality_pants   ", (double)Integer.MAX_VALUE);
        distances.put("Pants&Shirts    ", (double)Integer.MAX_VALUE);
        distances.put("Shoe_Store      ", (double)Integer.MAX_VALUE);
        distances.put("Underwear_Store ", (double)Integer.MAX_VALUE);
        distances.put("Everything_Store", (double)Integer. MAX_VALUE);
        times.put("home", (double) Integer.MAX_VALUE);
        times.put("Quality_Shirts  ", (double)Integer.MAX_VALUE);
        times.put("Quality_pants   ", (double)Integer.MAX_VALUE);
        times.put("Pants&Shirts    ", (double)Integer.MAX_VALUE);
        times.put("Shoe_Store      ", (double)Integer.MAX_VALUE);
        times.put("Underwear_Store ", (double)Integer.MAX_VALUE);
        times.put("Everything_Store", (double)Integer. MAX_VALUE);

        distances.put(src,0.0);
        times.put(src, 0.0);
        
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 14; j++) {
                String u = distanceEdges[j].source;
                String v = distanceEdges[j].destination;
                double weight = distanceEdges[j].weight;
                //System.out.println(weight);
               // System.out.println(u);
                

                
                if (distances.get(u) != Integer.MAX_VALUE && distances.get(u) + weight < distances.get(v)){
                    
                    
                    
                    distances.put(v, distances.get(u) + weight);
                }

                
            }
        }


        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 14; j++) {
                String u = timeEdges[j].source;
                String v = timeEdges[j].destination;
                double weight = timeEdges[j].weight;
                
                
                
                
                if (times.get(u) != Integer.MAX_VALUE && times.get(u) + weight < times.get(v)){
                    
                    
                    
                    times.put(v, times.get(u) + weight);
                }

                
            }
        }

        
        
        
    }
    

    public void setData(Store start, Store[] allStores){
        bellmanFord(start.toString());

        for (int i=0;i<6;i++){
            Store currStore = allStores[i];
            currStore.setDistance(distances.get(currStore.toString()));
            currStore.setTime(times.get(currStore.toString()));

        }
        
    }

    public void setData(Store[] allStores){
        bellmanFord("home");
        
        for (int i=0;i<6;i++){
            Store currStore = allStores[i];
            
            double currDistance = distances.get(currStore.toString());
            
            currStore.setDistance(currDistance);
            currStore.setTime(times.get(currStore.toString()));

        }
        
    }

    

    

    public double distanceFromHome(){
        return distances.get("home");
    }

    public double timeToHome(){
        return times.get("home");
    }

    

    
}
