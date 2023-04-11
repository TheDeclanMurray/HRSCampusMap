package org.headroyce.declanm2022;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph  {

    private int from;
    private int to;

    //the weight between every pair of nodes
    private int[][] distanceMatrix;

    //the matrix used for Dijkstra's Algorithm
    private Node[][] DijkstraMatrix;

    //Converting to and from Names and integers
    private HashMap<String,Integer> Locations;
    private HashMap<Integer,String> inverseLocations;

    private int numLocations;
    private LList<String> minPath;

    public Graph(ArrayList<String> fromTo, HRSData hrsData){
        //get data from hrsData
        distanceMatrix = hrsData.getDistanceMatrix();
        Locations = hrsData.getLocations();
        inverseLocations = hrsData.getInverseLocations();
        numLocations = hrsData.getNumLocations();

        //determine start of path and end of path
        from = Locations.get(fromTo.get(0));
        to = Locations.get(fromTo.get(1));

        //Dijkstra's Algorithm
        DijkstraMatrix = new Node[numLocations][numLocations];
        minPath = Dijkstra();
    }

    //find the shortest path from one point to another
    private LList<String> Dijkstra(){

        HashMap<Integer,Boolean> Visited = new HashMap<>();  //keep track of visited nodes
        LList<String> minPath = new LList<>(); //min weight path
        int DijkstraDepth = 0; //keeps track of current depth
        int currLoc = from; //keeps track of current node
        int distance = 0;  //keeps track of current nodes cumulative weight
        Node holder;  //holds data: cumulative weigh, and previous node

        //collum of Dijkstra's Algorithm
        for(int c = 0; c < numLocations; c++){

            //row of Dijkstra's Algorithm

            //-------start of row building
            for(int i = 0; i < numLocations; i++){
                if(distanceMatrix[currLoc][i] == -1){
                    //if no direct path from current location to i then distance is max
                    holder = new Node(Integer.MAX_VALUE,currLoc);
                }else{
                    holder = new Node(distance+distanceMatrix[currLoc][i],currLoc);
                }

                if(i != currLoc){
                    if(DijkstraDepth > 0){
                        //not first row
                        if(DijkstraMatrix[DijkstraDepth-1][i].distance > holder.distance){
                            //if curr distance is less than old distance, use curr distance
                            DijkstraMatrix[DijkstraDepth][i] = holder;
                        }else{
                            //if old distance is less than/equal to curr distance, keep old distance
                            DijkstraMatrix[DijkstraDepth][i] = DijkstraMatrix[DijkstraDepth-1][i];
                        }
                    }else{
                        //first row
                        DijkstraMatrix[DijkstraDepth][i] = holder;
                    }
                }else{
                    holder.distance = distance;
                    if (DijkstraDepth != 0) {
                        //from location remains the same
                        holder.fromLoc = DijkstraMatrix[DijkstraDepth - 1][i].fromLoc;
                    }
                    DijkstraMatrix[DijkstraDepth][i] = holder;
                }

            }
            //------- end of row building

            Visited.put(currLoc,true);

            //if finished
            if(currLoc == to){
                minPath.add(inverseLocations.get(to));
                int loc = to;
                while(loc != from){
                    loc = DijkstraMatrix[DijkstraDepth][loc].fromLoc;
                    minPath.insert(inverseLocations.get(loc),-1);
                }
                //minPath.add(inverseLocations.get(to));
                System.out.println("minPath = "+minPath.toString());
                System.out.println("Distance = "+distance);
                break;
            }

            //next curr location
            boolean first = true;
            for(int i = 0; i < numLocations; i++){
                if(Visited.containsKey(i)){
                    //already visited
                }else if(first){
                    first = false;
                    currLoc = i;
                    distance = DijkstraMatrix[DijkstraDepth][currLoc].distance;
                }else if(DijkstraMatrix[DijkstraDepth][currLoc].distance > DijkstraMatrix[DijkstraDepth][i].distance){
                    currLoc = i;
                    distance = DijkstraMatrix[DijkstraDepth][currLoc].distance;
                }
            }

            DijkstraDepth +=1;
        }

        return minPath;
    }

    //node class used to hold two pieces of data in a single object
    private class Node{

        public int distance;
        public int fromLoc;

        public Node(int shortPath, int fromThisNode){
            distance = shortPath;
            fromLoc = fromThisNode;
        }

        public String toString(){
            String str = "[ ";
            if(distance != Integer.MAX_VALUE){
                str += distance;

            }else{
                str += "*";
            }
            str += "(";
            str += fromLoc;
            str += ") ] ";
            return str;
        }
    }

    public LList<String> getMinPath(){
        return minPath;
    }
}