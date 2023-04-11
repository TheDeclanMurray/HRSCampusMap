package org.headroyce.declanm2022;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class MainWorkspace extends BorderPane {
    private DrawingWorkspace dw;
    private PlansIndex plansIndex;
    private HRSData hrsData;

    //The 3 Floor Plans we have
    private Plan Floor3;
    private Plan Floor4;
    private Plan Floor5;

    public MainWorkspace(){
        //crease an HRSData, which gets all graph information
        hrsData = new HRSData("input.txt");

        dw = new DrawingWorkspace();
        plansIndex = new PlansIndex();
        plansIndex.prefWidthProperty().bind(this.widthProperty().divide(4)); //width = 800/4 = 200


        Floor3 = dw.getFloor3();
        Floor4 = dw.getFloor4();
        Floor5 = dw.getFloor5();

        plansIndex.addPlan(Floor5);
        plansIndex.addPlan(Floor4);
        plansIndex.addPlan(Floor3);

        this.setLeft(plansIndex);

        plansIndex.setOnPlanSelected(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //change active Floor Layout
                dw.setActivePlan((Plan)event.getSource());
            }
        });

        plansIndex.setOnSearch(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Calculate shortest path
                InfoPasser ip = (InfoPasser) actionEvent.getSource();
                Dijkstra(ip.getTo(),ip.getFrom());
            }
        });
        this.setCenter(dw);
    }

    //Find the shortest path between two nodes
    private void Dijkstra(String to,String from){

        //Testing to see if searched rooms exist in graph
        if(!hrsData.containsNode(to)&&!hrsData.containsNode(from)){
            plansIndex.ToInvalidRoom();
            plansIndex.FromInvalidRoom();
            return;
        }
        if(!hrsData.containsNode(to)){
            plansIndex.ToInvalidRoom();
            return;
        }
        if(!hrsData.containsNode(from)){
            plansIndex.FromInvalidRoom();
            return;
        }

        ArrayList<String> FromTo = new ArrayList<>();
        FromTo.add(from);
        FromTo.add(to);
        Graph graph = new Graph(FromTo,hrsData);
        //shortest path
        LList<String> minPath = graph.getMinPath();
        LList<MappingPoint> mappingPoints = new LList<>();
        for(String str: minPath) {
            mappingPoints.add(hrsData.getCoordinate(str));
        }
        dw.minPath(mappingPoints);
    }
}
