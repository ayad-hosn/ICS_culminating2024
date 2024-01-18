/* The Road class represents a road connecting two stores
 * Every road has a source, destination, and weight 
 */
public class Road {
    // Variables to store the source node, destination node, and weight of the edge
    String source, destination;
    double weight;

    /* Constructor to initialize an Edge object with 
     * the provided source, destination, and weight 
    */
    Road(String source, String destination, double weight) {
        this.source = source; // Set the source node of the edge
        this.destination = destination; // Set the destination node of the edge
        this.weight = weight; // Set the weight (cost, distance, time, etc.) of the edge
    }
}
