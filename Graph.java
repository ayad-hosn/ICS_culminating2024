public class Graph {
    

    public Graph(){
        System.out.println("hello");
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
