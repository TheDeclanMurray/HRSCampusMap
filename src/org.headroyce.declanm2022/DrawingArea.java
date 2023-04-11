package org.headroyce.declanm2022;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * The main drawing canvas for our application
 *
 * @author Brian Sea
 */
public class DrawingArea extends StackPane {

    // The main drawing canvas
    private Canvas mainCanvas;

    //lists of tools
    private Plan Floor3;
    private Plan Floor4;
    private Plan Floor5;

    // The plan to draw
    private Plan activePlan;

    private LineTool floor3path;
    private LineTool floor4path;
    private LineTool floor5path;


    private DrawingWorkspace mainWorkspace;

    public DrawingArea(DrawingWorkspace mw){

        mainWorkspace = mw;
        mainCanvas = new Canvas();

        // Force the canvas to resize to the screen's size
        mainCanvas.widthProperty().bind(this.widthProperty());
        mainCanvas.heightProperty().bind(this.heightProperty());

        this.getChildren().add(mainCanvas);

        floor3path = new LineTool(mainCanvas);
        floor4path = new LineTool(mainCanvas);
        floor5path = new LineTool(mainCanvas);

        InitiateFloor3();
        InitiateFloor4();
        InitiateFloor5();
    }

    /**
     * Render the viewable canvas
     */
    public void renderWorld(){
        if( activePlan == null ) return;

        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        gc.clearRect(0,0, mainCanvas.getWidth(), mainCanvas.getHeight());

        for(Tool t: activePlan){
            t.render();
        }

        if(activePlan == Floor3){
            floor3path.setColor(Color.BLUE);
            floor3path.render();
            floor3path.renderWidgets();
        }
        if (activePlan == Floor4){
            floor4path.setColor(Color.BLUE);
            floor4path.render();
            floor4path.renderWidgets();
        }
        if(activePlan == Floor5){
            floor5path.setColor(Color.BLUE);
            floor5path.render();
            floor5path.renderWidgets();
        }

    }

    public void setActivePlan( Plan p ){
        activePlan = p;
        renderWorld();
    }

    private Plan InitiateFloor3(){
        Floor3 = new Plan("Floor3");

        //Old Building(I think)
        LineTool lt = new LineTool(mainCanvas);
        //Old Building
        Point p = new Point(190,220);
        lt = new LineTool(mainCanvas);
        lt.add(190,220);
        lt.add(190,300);
        lt.add(400,300);
        lt.add(400, 220);
        lt.add(190,220);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+170,p.y);
        lt.add(p.x+170,p.y+80);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x,p.y+37);
        lt.add(p.x+85,p.y+37);
        lt.add(p.x+170,p.y+37);
        lt.setLineWidth(1);
        Floor3.add(lt);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+85,p.y+37);
        lt.add(p.x+85,p.y);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+20,p.y+37);
        lt.add(p.x+20,p.y+63);
        lt.add(p.x,p.y+63);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+26,p.y+80);
        lt.add(p.x+26,p.y+43);
        lt.add(p.x+46,p.y+43);
        lt.add(p.x+46,p.y+80);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+65,p.y+80);
        lt.add(p.x+65,p.y+43);
        lt.add(p.x+85,p.y+43);
        lt.add(p.x+85,p.y+80);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x,p.y+28);
        lt.add(p.x+18,p.y+28);
        lt.add(p.x+18,p.y);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+18,p.y+14);
        lt.add(p.x+56,p.y+14);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+56,p.y);
        lt.add(p.x+56,p.y+37);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+69,p.y);
        lt.add(p.x+69,p.y+37);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+30,p.y);
        lt.add(p.x+30,p.y+37);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+43,p.y);
        lt.add(p.x+43,p.y+14);
        lt.setLineWidth(1);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+30,p.y+28);
        lt.add(p.x+48,p.y+28);
        lt.add(p.x+48,p.y+37);
        lt.setLineWidth(1);
        Floor3.add(lt);

        //Library
        p = new Point(190,350);
        lt = new LineTool(mainCanvas);
        lt.add(190,350);
        lt.add(190,420);
        lt.add(290,420);
        lt.add(290,350);
        lt.add(190,350);
        Floor3.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+25,p.y);
        lt.add(p.x+25,p.y+70);
        lt.setLineWidth(1);
        Floor3.add(lt);

        //TODO: Language Building
        lt = new LineTool(mainCanvas);
        lt.add(360,400);
        lt.add(380,520);
        lt.add(380+2*120/5,520-2*20/5);
        lt.add(360+2*120/5,400-2*20/5);
        lt.add(360,400);
        Floor3.add(lt);

        return Floor3;
    }

    private Plan InitiateFloor4(){
        Floor4 = new Plan("Floor4");

        //Gym
        Point p = new Point(190,20);
        LineTool lt = new LineTool(mainCanvas);
        lt.add(190,20);
        lt.add(190,130);
        lt.add(330,130);
        lt.add(330,20);
        lt.add(190,20);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+20,p.y+110);
        lt.add(p.x+20,p.y+86);
        lt.add(p.x+40,p.y+86);
        lt.add(p.x+40,p.y+110);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+50,p.y+110);
        lt.add(p.x+50,p.y+86);
        lt.add(p.x+140,p.y+86);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+140,p.y);
        lt.add(p.x+155,p.y);
        lt.add(p.x+155,p.y+66);
        lt.add(p.x+140,p.y+66);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+140,p.y+33);
        lt.add(p.x+155,p.y+33);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+20,p.y+98);
        lt.add(p.x+40,p.y+98);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+50,p.y+98);
        lt.add(p.x+140,p.y+98);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+105,p.y+86);
        lt.add(p.x+105,p.y+110);
        lt.setLineWidth(1);
        Floor4.add(lt);

        //New Building(I think)
        p = new Point(190,130);
        lt = new LineTool(mainCanvas);
        lt.add(190,130);
        lt.add(190,200);
        lt.add(420,200);
        lt.add(420,130);
        lt.add(190,130);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+20,p.y+70);
        lt.add(p.x+20,p.y+40);
        lt.add(p.x+230,p.y+40);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x,p.y+30);
        lt.add(p.x+40,p.y+30);
        lt.add(p.x+40,p.y);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+50,p.y);
        lt.add(p.x+50,p.y+30);
        lt.add(p.x+230,p.y+30);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+90,p.y);
        lt.add(p.x+90,p.y+30);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+137,p.y);
        lt.add(p.x+137,p.y+30);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+184,p.y);
        lt.add(p.x+184,p.y+30);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+89,p.y+40);
        lt.add(p.x+89,p.y+70);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+136,p.y+40);
        lt.add(p.x+136,p.y+70);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+183,p.y+40);
        lt.add(p.x+183,p.y+70);
        lt.setLineWidth(1);
        Floor4.add(lt);

        //Old Building(I think)
        p = new Point(190,220);
        lt = new LineTool(mainCanvas);
        lt.add(190,220);
        lt.add(190,300);
        lt.add(400,300);
        lt.add(400, 220);
        lt.add(190,220);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+30,p.y);
        lt.add(p.x+30,p.y+35);
        lt.add(p.x+210,p.y+35);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x,p.y+45);
        lt.add(p.x+180,p.y+45);
        lt.add(p.x+180,p.y+80);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x,p.y+28);
        lt.add(p.x+18,p.y+28);
        lt.add(p.x+18,p.y);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+18,p.y+14);
        lt.add(p.x+30,p.y+14);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+30,p.y);
        lt.add(p.x+30,p.y+35);
        lt.add(p.x+210,p.y+35);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+18,p.y+28);
        lt.add(p.x+18,p.y+45);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+195,p.y+80);
        lt.add(p.x+195,p.y+45);
        lt.add(p.x+210,p.y+45);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+195,p.y+45);
        lt.add(p.x+195,p.y+35);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+78,p.y);
        lt.add(p.x+78,p.y+35);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+90,p.y);
        lt.add(p.x+90,p.y+35);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+138,p.y);
        lt.add(p.x+138,p.y+35);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+174,p.y);
        lt.add(p.x+174,p.y+35);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+36,p.y+45);
        lt.add(p.x+36,p.y+80);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+72,p.y+45);
        lt.add(p.x+72,p.y+80);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+108,p.y+45);
        lt.add(p.x+108,p.y+80);
        lt.setLineWidth(1);
        Floor4.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+144,p.y+45);
        lt.add(p.x+144,p.y+80);
        lt.setLineWidth(1);
        Floor4.add(lt);

        //TODO: Language Building
        lt = new LineTool(mainCanvas);
        lt.add(360,400);
        lt.add(380,520);
        lt.add(380+2*120/5,520-2*20/5);
        lt.add(360+2*120/5,400-2*20/5);
        lt.add(360,400);
        Floor4.add(lt);


        return Floor4;
    }
    private Plan InitiateFloor5(){
        Floor5 = new Plan("Floor5");

        //New Building(I think)
        Point p = new Point(190,130);
        LineTool lt = new LineTool(mainCanvas);
        lt.add(190,130);
        lt.add(190,200);
        lt.add(420,200);
        lt.add(420,130);
        lt.add(190,130);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x,p.y+30);
        lt.add(p.x+6,p.y+30);
        lt.add(p.x+6,p.y+20);
        lt.add(p.x+14,p.y+20);
        lt.add(p.x+14,p.y+30);
        lt.add(p.x+20,p.y+30);
        lt.add(p.x+20,p.y);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+20,p.y+30);
        lt.add(p.x+230,p.y+30);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+20,p.y+70);
        lt.add(p.x+20,p.y+40);
        lt.add(p.x+230,p.y+40);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x,p.y+40);
        lt.add(p.x+20,p.y+40);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+89,p.y);
        lt.add(p.x+89,p.y+30);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+119,p.y);
        lt.add(p.x+119,p.y+30);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+188,p.y);
        lt.add(p.x+188,p.y+30);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+62,p.y+70);
        lt.add(p.x+62,p.y+40);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+104,p.y+70);
        lt.add(p.x+104,p.y+40);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+146,p.y+70);
        lt.add(p.x+146,p.y+40);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+188,p.y+70);
        lt.add(p.x+188,p.y+40);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+10,p.y);
        lt.add(p.x+10,p.y+20);
        lt.setLineWidth(1);
        Floor5.add(lt);



        //Old Building
        p = new Point(190,220);
        lt = new LineTool(mainCanvas);
        lt.add(190,220);
        lt.add(190,300);
        lt.add(400,300);
        lt.add(400, 220);
        lt.add(190,220);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x,p.y+45);
        lt.add(p.x+210,p.y+45);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+30,p.y);
        lt.add(p.x+30,p.y+35);
        lt.add(p.x+210,p.y+35);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x,p.y+28);
        lt.add(p.x+18,p.y+28);
        lt.add(p.x+18,p.y);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+18,p.y+14);
        lt.add(p.x+30,p.y+14);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+18,p.y+28);
        lt.add(p.x+18,p.y+45);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+66,p.y);
        lt.add(p.x+66,p.y+35);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+102,p.y);
        lt.add(p.x+102,p.y+35);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+138,p.y);
        lt.add(p.x+138,p.y+35);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+174,p.y);
        lt.add(p.x+174,p.y+35);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+36,p.y+45);
        lt.add(p.x+36,p.y+80);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+72,p.y+45);
        lt.add(p.x+72,p.y+80);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+87,p.y+45);
        lt.add(p.x+87,p.y+80);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+123,p.y+45);
        lt.add(p.x+123,p.y+80);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+159,p.y+45);
        lt.add(p.x+159,p.y+80);
        lt.setLineWidth(1);
        Floor5.add(lt);
        lt = new LineTool(mainCanvas);
        lt.add(p.x+174,p.y+45);
        lt.add(p.x+174,p.y+80);
        lt.setLineWidth(1);
        Floor5.add(lt);


        return Floor5;
    }

    public void minPath(LList<MappingPoint> mp){
        floor3path = new LineTool(mainCanvas);
        floor4path = new LineTool(mainCanvas);
        floor5path = new LineTool(mainCanvas);

        MappingPoint start = mp.get(0);

        if(start.Level == 3){
            setActivePlan(Floor3);
        }
        if(start.Level == 4){
            setActivePlan(Floor4);
        }
        if(start.Level == 5){
            setActivePlan(Floor5);
        }


        for(MappingPoint curr: mp){
            if(curr.Level == 3){
                Point p = new Point(curr.x,curr.y);
                floor3path.add(p);
            }
            if(curr.Level == 4){
                Point p = new Point(curr.x,curr.y);
                floor4path.add(p);
            }
            if(curr.Level == 5){
                Point p = new Point(curr.x,curr.y);
                floor5path.add(p);
            }
        }


        renderWorld();
    }

    public Plan getFloor3(){
        return Floor3;
    }
    public Plan getFloor4(){
        return Floor4;
    }
    public Plan getFloor5(){
        return Floor5;
    }
}