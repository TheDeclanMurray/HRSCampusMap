module HRSCampusMap {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;

    opens org.headroyce.declanm2022 to javafx.fxml;
    exports org.headroyce.declanm2022;
}