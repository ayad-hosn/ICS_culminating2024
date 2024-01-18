/* Map class represents a graph with distances and times between the stores 
*/

import java.util.HashMap;

class Map {
    // Hashmaps to store distances and times between stores
    HashMap<String, Double> distances = new HashMap<>();
    HashMap<String, Double> times = new HashMap<>();
    // Arrays to store edges of the nodes for both distances and times
    Road[] distanceEdges = new Road[14];
    Road[] timeEdges = new Road[14];

    public Map(){


        this.distanceEdges[0] = new Road("home", "Quality_Shirts  ", 5);
        this.distanceEdges[1] = new Road("Quality_Shirts  ",  "home", 5);
        this.distanceEdges[2] = new Road("home",  "Quality_pants   ", 4);
        this.distanceEdges[3] = new Road("Quality_pants   ", "home", 4);
        this.distanceEdges[4] = new Road("Quality_Shirts  ", "Pants&Shirts    ", 3);
        this.distanceEdges[5] = new Road("Pants&Shirts    ", "Quality_Shirts  ", 3);
        this.distanceEdges[6] = new Road("Quality_pants   ", "Quality_Shirts  ", 6);
        this.distanceEdges[7] = new Road("Quality_Shirts  ", "Quality_pants   ", 6);
        this.distanceEdges[8] = new Road("Quality_pants   ", "Underwear_Store ", 5);
        this.distanceEdges[9] = new Road("Underwear_Store ", "Quality_pants   ", 5);
        this.distanceEdges[10] = new Road("Pants&Shirts    ", "Shoe_Store      ", 2);
        this.distanceEdges[11] = new Road("Shoe_Store      ", "Shoe_Store      ", 2);
        this.distanceEdges[12] = new Road("Underwear_Store ", "Everything_Store", 3);
        this.distanceEdges[13] = new Road("Everything_Store", "Underwear_Store ", 3);

        this.timeEdges[0] = new Road("home", "Quality_Shirts  ", 10);
        this.timeEdges[1] = new Road("Quality_Shirts  ",  "home", 10);
        this.timeEdges[2] = new Road("home",  "Quality_pants   ", 3);
        this.timeEdges[3] = new Road("Quality_pants   ", "home", 3);
        this.timeEdges[4] = new Road("Quality_Shirts  ", "Pants&Shirts    ", 4);
        this.timeEdges[5] = new Road("Pants&Shirts    ", "Quality_Shirts  ", 4);
        this.timeEdges[6] = new Road("Quality_pants   ", "Quality_Shirts  ", 2);
        this.timeEdges[7] = new Road("Quality_Shirts  ", "Quality_pants   ", 2);
        this.timeEdges[8] = new Road("Quality_pants   ", "Underwear_Store ", 1);
        this.timeEdges[9] = new Road("Underwear_Store ", "Quality_pants   ", 1);
        this.timeEdges[10] = new Road("Pants&Shirts    ", "Shoe_Store      ", 9);
        this.timeEdges[11] = new Road("Shoe_Store      ", "Pants&Shirts    ", 9);
        this.timeEdges[12] = new Road("Underwear_Store ", "Everything_Store", 6);
        this.timeEdges[13] = new Road("Everything_Store", "Underwear_Store ", 6);

    }

    /* Bellman-Ford algorithm to find shortest paths from a source node
     * @params src - the source node of the graph
     * The source is the store that the user is currently at
     */
    private void bellmanFord(String src) {
        // Initialize all distances to infinity (maximum value)
        distances.put("home", (double) Integer.MAX_VALUE);
        distances.put("Quality_Shirts  ", (double)Integer.MAX_VALUE);
        distances.put("Quality_pants   ", (double)Integer.MAX_VALUE);
        distances.put("Pants&Shirts    ", (double)Integer.MAX_VALUE);
        distances.put("Shoe_Store      ", (double)Integer.MAX_VALUE);
        distances.put("Underwear_Store ", (double)Integer.MAX_VALUE);
        distances.put("Everything_Store", (double)Integer. MAX_VALUE);
        // Initialize all distances to infinity (maximum value)
        times.put("home", (double) Integer.MAX_VALUE);
        times.put("Quality_Shirts  ", (double)Integer.MAX_VALUE);
        times.put("Quality_pants   ", (double)Integer.MAX_VALUE);
        times.put("Pants&Shirts    ", (double)Integer.MAX_VALUE);
        times.put("Shoe_Store      ", (double)Integer.MAX_VALUE);
        times.put("Underwear_Store ", (double)Integer.MAX_VALUE);
        times.put("Everything_Store", (double)Integer. MAX_VALUE);
        // Set distance and time from source to itself to 0
        distances.put(src,0.0);
        times.put(src, 0.0);

        // Relax edges repeatedly to find shortest paths
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 14; j++) {
                // Source node of the edge

                String u = distanceEdges[j].source;
                // destination node of the edge
                //an edge represents the distance of the road 
                //between one node to another node
                String v = distanceEdges[j].destination;
                double weight = distanceEdges[j].weight;
                //System.out.println(weight);
               // System.out.println(u);
                

                // If the distance to the source node is not infinity 
                //and the path through u to v is shorter than the current shortest 
                //calculated path to v
                if (distances.get(u) != Integer.MAX_VALUE && distances.get(u) + weight < distances.get(v)){
                    // Update the distance to the destination node

                    distances.put(v, distances.get(u) + weight);
                }

                
            }
        }


        // Relax edges repeatedly to find shortest paths
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 14; j++) {
                // Source node of the edge

                String u = timeEdges[j].source;
                // destination node of the edge
                //an edge represents the time it takes to take the 
                //road between one node to another node
                String v = timeEdges[j].destination;
                double weight = timeEdges[j].weight;
                
                


                // If the distance to the source node is not infinity 
                //and the path through u to v is shorter than the current shortest 
                //calculated path to v
                if (times.get(u) != Integer.MAX_VALUE && times.get(u) + weight < times.get(v)){
                    
                    
                    // Update the time to the destination node
                    times.put(v, times.get(u) + weight);
                }

                
            }
        }

        
        
        
    }
    
    /* Method to set the distance and time data for a 
     * specific starting store and all stores
     * Overloaded method setData()
     * @param start - represents the source of the graph or in other terms
     * the store or location the user is in current
     * @param allStores - array representing all the stores
     * used to update the distances from all the stores to the current store 
    */
    protected void setData(Store start, Store[] allStores){
        //call the bellman ford method using the name for the current store
        bellmanFord(start.toString());
        //update the distances and times from all the stores to the current stores
        //by looping through all stores
        for (int i=0;i<6;i++){
            Store currStore = allStores[i];
            currStore.setDistance(distances.get(currStore.toString()));
            currStore.setTime(times.get(currStore.toString()));
        }
        
    }

    /* Overloaded method setData
     *to set the distance 
     * and time data for all stores from 'home' 
     * @param allStores - the array containing all the instances of the stores
     * @return void method returns nothing
    */
    protected void setData(Store[] allStores){
        bellmanFord("home");
        
        for (int i=0;i<6;i++){
            Store currStore = allStores[i];
            
            double currDistance = distances.get(currStore.toString());
            
            currStore.setDistance(currDistance);
            currStore.setTime(times.get(currStore.toString()));

        }
        
    }

    


   
    

    
}
