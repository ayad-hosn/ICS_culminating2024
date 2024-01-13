import java.util.HashMap;

public class Graph {
    
    HashMap<String, Integer> distances = new HashMap<>();
    HashMap<String, Integer> times = new HashMap<>();
    Edge[] distanceEdges;
    Edge[] timeEdges;

    public Graph(){
        System.out.println("hello");

        distances.put("stroe1", Integer.MAX_VALUE);
        distances.put("stroe1", Integer.MAX_VALUE);
        distances.put("stroe1", Integer.MAX_VALUE);
        distances.put("stroe1", Integer.MAX_VALUE);
        distances.put("stroe1", Integer.MAX_VALUE);
        distances.put("stroe1", Integer.MAX_VALUE);
        times.put("stroe1", Integer.MAX_VALUE);
        times.put("stroe1", Integer.MAX_VALUE);
        times.put("stroe1", Integer.MAX_VALUE);
        times.put("stroe1", Integer.MAX_VALUE);
        times.put("stroe1", Integer.MAX_VALUE);
        times.put("stroe1", Integer.MAX_VALUE);
        
    
    }

    private void bellmanFord(String src) {
        
        
        distances.put(src,0);

        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                String u = distanceEdges[j].source;
                String v = distanceEdges[j].destination;
                int weight = distanceEdges[j].weight;
                if (distances.get(u) != Integer.MAX_VALUE && distances.get(u) + weight < distances.get(v))
                    distances.put(v, distances.get(u) + weight);
            }
        }

        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                String u = timeEdges[j].source;
                String v = timeEdges[j].destination;
                int weight = timeEdges[j].weight;
                if (times.get(u) != Integer.MAX_VALUE && times.get(u) + weight < times.get(v))
                    times.put(v, times.get(u) + weight);
            }
        }
        

        
    }


    

    public void setData(Store start, Store end){
        start.setDistance(getShortestDistance());
        start.setTime(getShortestTime());
    }

    public double getShortestDistance(){
        return 1.0;
    }

    public double getShortestTime(){
        return 1.0;
    }
}
