package org.headroyce.declanm2022;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The View Element which handles the display and interation with all the Plans
 */
public class PlansIndex extends VBox {

    private VBox plansArea;
    private InfoPasser sButton;
    private Label currLocation, destination;

    /**
     * Allow external entities to setup an event listener to know when a plan is selected
     */
    private EventHandler<ActionEvent> selectedPlanEventHandler;

    private EventHandler<ActionEvent> onSearch;


    public PlansIndex(){

        //Create search Current Location box
        VBox search = new VBox();
        HBox From = new HBox();
        currLocation = new Label("Location:");
        currLocation.prefWidthProperty().setValue(80);
        TextField SearchFrom = new TextField();
        From.getChildren().addAll(currLocation, SearchFrom);

        //Create Search Destination Box
        HBox To = new HBox();
        destination = new Label("Destination:");
        destination.prefWidthProperty().setValue(80);
        TextField SearchTo = new TextField();
        To.getChildren().addAll(destination, SearchTo);

        search.getChildren().addAll(From, To);

        //Floor Plans List
        plansArea = new VBox();
        plansArea.getStyleClass().add("planIndexList");


        // Titlebar
        HBox titleBar = new HBox();
        Label title = new Label("Floors");
        title.prefWidthProperty().setValue(170);

        //Search button
        sButton = new InfoPasser();
        sButton.setTooltip(new Tooltip("Search"));
        Image img = new Image(getClass().getResourceAsStream("/images/search.png"));
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(30);
        imageView.setFitHeight(28);
        sButton.setGraphic(imageView);
        titleBar.getChildren().addAll(title,sButton);
        sButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sButton.setTo(SearchTo.getText());
                sButton.setFrom(SearchFrom.getText());
                currLocation.getStyleClass().remove("invalidRoom");
                destination.getStyleClass().remove("invalidRoom");
                onSearch.handle(actionEvent);
            }
        });
        // Setup toolbar for our index
        title.getStyleClass().add("plansIndexHeader");

        //this.getChildren().addAll(title, search, plansArea);
        this.getChildren().addAll(titleBar, search, plansArea);

    }
    public void FromInvalidRoom(){
        currLocation.getStyleClass().add("invalidRoom");    //invalid room

    }
    public void ToInvalidRoom(){
        destination.getStyleClass().add("invalidRoom");    //invalid room
    }

    /**
     * Add a plan to the index
     * @param p the plan to add
     */
    public void addPlan( Plan p ){
        if( p == null ) return;

        // Wrap the plan in a view and bind the width to the index's width
        PlansIndexItem guiItem = new PlansIndexItem(p);
        guiItem.prefWidthProperty().bind(this.widthProperty());
        guiItem.setPrefHeight(50);

        // If the item is clicked on then fire the PlanSelection Event
        guiItem.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if( selectedPlanEventHandler != null ) {
                    PlansIndexItem pi = (PlansIndexItem) event.getSource();
                    ActionEvent e = new ActionEvent(pi.plan, Event.NULL_SOURCE_TARGET);
                    selectedPlanEventHandler.handle(e);
                }
            }
        });

        plansArea.getChildren().add(0, guiItem);

        // fire the selection event to display the new plan
        if( selectedPlanEventHandler != null ){
            ActionEvent evt = new ActionEvent(p, Event.NULL_SOURCE_TARGET);
            selectedPlanEventHandler.handle(evt);
        }
    }

    public void setOnPlanSelected( EventHandler<ActionEvent> handler ){
        this.selectedPlanEventHandler = handler;
    }

    public void setOnSearch(EventHandler<ActionEvent> handler){
        this.onSearch = handler;
    }

    /**
     * The view of each plan's list item
     *
     * Each list item can be compared to other list items
     */
    private class PlansIndexItem extends HBox implements Comparable<PlansIndexItem>{

        private CheckBox selected;
        private Label txt;

        private Plan plan;


        @Override
        /**
         * Compare list items to each other
         */
        public int compareTo(PlansIndexItem other) {

            // If the string version are the same, then we consider the plan
            // equal to each other (this allows two plans of the same title)
            int hashCompare = this.toString().compareTo(other.toString());
            if( hashCompare == 0 ){
                return 0;
            }

            // The String versions are the same, so compare titles
            return this.plan.getTitle().compareTo(other.plan.getTitle());
        }

        public PlansIndexItem(Plan plan){
            if( plan == null ) throw new IllegalArgumentException("Plan cannot be null");
            this.plan = plan;

            selected = new CheckBox();
            txt = new Label(this.plan.getTitle());

            this.setAlignment(Pos.CENTER);

            selected.prefHeightProperty().bind(this.heightProperty());
            HBox.setMargin(selected, new Insets(5,5,5,5));

            //HBox.setMargin(info, new Insets(5,5,5,5));

            this.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            //this.getChildren().addAll(title);
            this.getChildren().addAll(txt);

        }
    }
}
