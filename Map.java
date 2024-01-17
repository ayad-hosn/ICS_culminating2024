import java.util.HashMap;
/* Graph class represents a graph with distances and times between nodes 
*/
public class Map {
    // Hashmaps to store distances and times between stores
    HashMap<String, Double> distances = new HashMap<>();
    HashMap<String, Double> times = new HashMap<>();
    // Arrays to store edges of the nodes for both distances and times
    Road[] distanceEdges = new Road[14];
    Road[] timeEdges = new Road[14];

    public Map(){

;
        distanceEdges[0] = new Road("home", "Quality_Shirts  ", 5);
        distanceEdges[1] = new Road("Quality_Shirts  ",  "home", 5);
        distanceEdges[2] = new Road("home",  "Quality_pants   ", 4);
        distanceEdges[3] = new Road("Quality_pants   ", "home", 4);
        distanceEdges[4] = new Road("Quality_Shirts  ", "Pants&Shirts    ", 3);
        distanceEdges[5] = new Road("Pants&Shirts    ", "Quality_Shirts  ", 3);
        distanceEdges[6] = new Road("Quality_pants   ", "Quality_Shirts  ", 6);
        distanceEdges[7] = new Road("Quality_Shirts  ", "Quality_pants   ", 6);
        distanceEdges[8] = new Road("Quality_pants   ", "Underwear_Store ", 5);
        distanceEdges[9] = new Road("Underwear_Store ", "Quality_pants   ", 5);
        distanceEdges[10] = new Road("Pants&Shirts    ", "Shoe_Store      ", 2);
        distanceEdges[11] = new Road("Shoe_Store      ", "Shoe_Store      ", 2);
        distanceEdges[12] = new Road("Underwear_Store ", "Everything_Store", 3);
        distanceEdges[13] = new Road("Everything_Store", "Underwear_Store ", 3);

        timeEdges[0] = new Road("home", "Quality_Shirts  ", 10);
        timeEdges[1] = new Road("Quality_Shirts  ",  "home", 10);
        timeEdges[2] = new Road("home",  "Quality_pants   ", 3);
        timeEdges[3] = new Road("Quality_pants   ", "home", 3);
        timeEdges[4] = new Road("Quality_Shirts  ", "Pants&Shirts    ", 4);
        timeEdges[5] = new Road("Pants&Shirts    ", "Quality_Shirts  ", 4);
        timeEdges[6] = new Road("Quality_pants   ", "Quality_Shirts  ", 2);
        timeEdges[7] = new Road("Quality_Shirts  ", "Quality_pants   ", 2);
        timeEdges[8] = new Road("Quality_pants   ", "Underwear_Store ", 1);
        timeEdges[9] = new Road("Underwear_Store ", "Quality_pants   ", 1);
        timeEdges[10] = new Road("Pants&Shirts    ", "Shoe_Store      ", 9);
        timeEdges[11] = new Road("Shoe_Store      ", "Pants&Shirts    ", 9);
        timeEdges[12] = new Road("Underwear_Store ", "Everything_Store", 6);
        timeEdges[13] = new Road("Everything_Store", "Underwear_Store ", 6);

    }

    /* Bellman-Ford algorithm to find shortest paths from a source node
     * @params the source of the graph
     * The source is the node that the user is currently at
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
     * @param start - represents the source of the graph or in other terms
     * the store or location the user is in current
     * @param allStores - array representing all the stores
     * used to update the distances from all the stores to the current store 
    */
    public void setData(Store start, Store[] allStores){
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

    /* Overloaded method to set the distance 
     * and time data for all stores from 'home' 
     * @param allStores the array containing all the instances of the stores
     * @return void method returns nothing
    */
    public void setData(Store[] allStores){
        bellmanFord("home");
        
        for (int i=0;i<6;i++){
            Store currStore = allStores[i];
            
            double currDistance = distances.get(currStore.toString());
            
            currStore.setDistance(currDistance);
            currStore.setTime(times.get(currStore.toString()));

        }
        
    }

    


    /* Method to get the distance 
     * from 'home' to the current store 
     * @param none
     * @return returns the distance from home to the current store 
    */

    public double distanceFromHome(){
        return distances.get("home");
    }

    /* Method to get the time 
     * from 'home' to the current store 
     * @param none
     * @return returns the time from home to the current store 
    */
    public double timeToHome(){
        return times.get("home");
    }

    

    
}
