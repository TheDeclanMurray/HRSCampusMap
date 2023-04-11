package org.headroyce.declanm2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class HRSData {

    private int numLocations;

    //for easy conversion from location name to spot in 2D array Lists
    private HashMap<String,Integer> Locations;

    //for easy conversion from spot in 2D array Lists to Location Name
    private HashMap<Integer,String> inverseLocations;

    //Keeps track of the floor leve, and the coordinate points of the
    private HashMap<String,MappingPoint> Coordinates;

    //the 2D array list that contains distances between Nodes
    private int[][] distanceMatrix;

    public HRSData(String input){
        numLocations = 0;
        Locations = new HashMap<>();
        inverseLocations = new HashMap<>();
        Coordinates = new HashMap<>();
        Reader(input);
        //printMatrix();
    }

    //Reads input file and creates the path weight matrix
    private void Reader(String inputFile){
        Boolean firstPath = false;
        try{
            File fileInput = new File(inputFile);
            Scanner reader = new Scanner(fileInput);

            while(reader.hasNextLine()) {
                //grab each line of the input file
                String data = reader.nextLine();
                if(data.length() != 0){
                    if(data.charAt(0) == 'L'){
                        //new location
                        addLocation(data);
                    }
                    if(data.charAt(0) == 'P'){
                        //new path between location(unidirectional)
                        if(!firstPath){
                            firstPath = true;
                            distanceMatrix = new int[numLocations][numLocations];
                            setupMatrix(distanceMatrix);
                        }
                        inputMatrix(data);
                    }
                }

            }

            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("An Error Occurred");
        }
    }

    public void addLocation(String location){
        //L:[Name]|[x]|[y]
        int break1 = 0;
        int break2 = 0;

        //separate the data in the line
        for(int i = 2; i < location.length(); i++){
            if(location.charAt(i) == '|'&& break1 ==0){
                break1 = i;
            }
            if(location.charAt(i) =='|'&& break1 != 0){
                //gets triggered twice, but should not matter
                break2 = i;
            }
        }
        String name = location.substring(3,break1);
        String strX = location.substring(break1+1,break2);
        String strY = location.substring(break2+1);
        double x = Integer.parseInt(strX);
        double y = Integer.parseInt(strY);
        int f = Integer.parseInt(location.substring(2,3));
        MappingPoint p = new MappingPoint(x,y,f);
        Coordinates.put(name,p);

        Locations.put(name,numLocations);
        inverseLocations.put(numLocations,name);
        numLocations += 1;
    }

    //start out with every node not connected to every other node
    //other than to itself, that is length of 0
    public void setupMatrix(int[][] M){
        for(int i = 0; i < numLocations; i++){
            for( int j = 0; j< numLocations;j++){
                if(i != j){
                    M[i][j] = -1;
                }
            }
        }
    }

    //input data into the path weigh matrrix
    public void inputMatrix(String path){
        //P:A|B|3   --> Path from A to B
        int break1 = 0;
        int break2 = 0;

        //separate the 3 data
        for(int i = 2; i < path.length(); i++){
            if(path.charAt(i) == '|'&& break1 ==0){
                break1 = i;
            }
            if(path.charAt(i) =='|'&& break1 != 0){
                //gets triggered twice, but should not matter
                break2 = i;
            }
        }
        String from = path.substring(2,break1);
        String to = path.substring(break1+1,break2);
        String weight = path.substring(break2+1);
        //System.out.println("To: "+to+", From: "+ from);
        distanceMatrix[Locations.get(from)][Locations.get(to)] = Integer.parseInt(weight);
        distanceMatrix[Locations.get(to)][Locations.get(from)] = Integer.parseInt(weight);
    }

    //Print the Matrix to Check its correct
    public void printMatrix(){
        System.out.println("-------Distance Matrix-------");
        int l = Locations.size();
        for(int i = 0; i < l; i++){
            for(int j = 0; j < l; j++){
             System.out.print("["+distanceMatrix[i][j]+"]");
            }
            System.out.println("");
        }
    }

    //A way to see if a given name corresponds to a Nodes name in the graph
    public boolean containsNode(String name){
        if(Locations.containsKey(name)){
            return true;
        }
        //TODO: capitals, spaces, numbers only
        //ex: {Room 510, room 510, Room510, room510, 510} are all the same
        return false;
    }

    public int[][] getDistanceMatrix(){
        return distanceMatrix;
    }
    public HashMap<String,Integer> getLocations(){
        return Locations;
    }
    public HashMap<Integer, String> getInverseLocations() {
        return inverseLocations;
    }
    public int getNumLocations(){
        return numLocations;
    }
    public MappingPoint getCoordinate(String name){
        return Coordinates.get(name);
    }
}