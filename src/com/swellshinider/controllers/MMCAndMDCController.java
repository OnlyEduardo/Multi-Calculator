package com.swellshinider.controllers;

import com.swellshinider.util.Patronizer;
import com.swellshinider.util.ProgramInfos;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MMCAndMDCController extends Patronizer implements Initializable {

    public MenuItem versionText;
    public TextField mdcResult;
    public TextField mmcResult;
    public TextField numberToCalc1;
    public TextField numberToCalc2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        versionText.setText(ProgramInfos.VERSION);
    }

    public void calculateValues(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        try{

            int value1 = Integer.parseInt(numberToCalc1.getText());
            int value2 = Integer.parseInt(numberToCalc2.getText());

            mdcResult.setText(Integer.toString(MDC(value1, value2)));
            mmcResult.setText(Integer.toString(MMC(value1, value2)));
        } catch (Exception ignored){
            mdcResult.setText("ERR");
            mmcResult.setText("ERR");
        }
    }

    public int MDC(int a, int b){
        int temp;

        if(a == b)
            return a;

        if(a < b){
            temp = a;
            a = b;
            b = temp;
        }
        return(MDC(a - b, b));
    }

    public int MMC(int a, int b){
        return(a * b / (MDC(a, b)));
    }
}
