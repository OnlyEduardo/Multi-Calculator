package com.swellshinider.util;

import com.swellshinider.Main;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Patronizer {

    public void closeApplication(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        Main.instance.changeToScene(SceneName.EXIT);
    }

    public void showAbout(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        JOptionPane.showMessageDialog(null, ProgramInfos.ABOUT, "Sobre", JOptionPane.PLAIN_MESSAGE);
    }

    public void goBack(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        Main.instance.changeToScene(SceneName.MAIN);
    }

    public void accessSite(ActionEvent actionEvent) throws URISyntaxException, IOException {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        java.awt.Desktop.getDesktop().browse(new java.net.URI( ProgramInfos.SITE));
    }
}
