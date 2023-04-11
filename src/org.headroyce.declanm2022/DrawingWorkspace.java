package org.headroyce.declanm2022;

import javafx.scene.Node;

import javafx.scene.layout.*;


/**
 * The entire workspace of the application.
 *
 * @author Brian Sea
 */
public class DrawingWorkspace extends Pane {

    private DrawingArea drawingArea;
    private Pane activeToolPalette;

    public DrawingWorkspace() {

        drawingArea = new DrawingArea(this);

        drawingArea.prefHeightProperty().bind(this.heightProperty());
        drawingArea.prefWidthProperty().bind(this.widthProperty());

        this.getChildren().addAll(drawingArea);
    }

    public void setActivePlan(Plan p) {
        drawingArea.setActivePlan(p);
        getChildren().remove(activeToolPalette);
        activeToolPalette = null;
    }


    public void minPath(LList<MappingPoint> mp){
        drawingArea.minPath(mp);
    }

    public Plan getFloor3() {
        return drawingArea.getFloor3();
    }
    public Plan getFloor4() {
        return drawingArea.getFloor4();
    }
    public Plan getFloor5() {
        return drawingArea.getFloor5();
    }

}