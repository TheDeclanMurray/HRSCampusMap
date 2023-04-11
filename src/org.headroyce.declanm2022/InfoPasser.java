package org.headroyce.declanm2022;

import javafx.scene.control.Button;

public class InfoPasser extends Button {

    private String to;
    private String from;

    public InfoPasser(){
        to = "";
        from = "";
    }

    public void setFrom(String from) {
        this.from = from;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
}
