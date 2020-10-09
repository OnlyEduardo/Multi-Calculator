package com.swellshinider.controllers;

import com.swellshinider.util.Patronizer;
import com.swellshinider.util.ProgramInfos;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TotienteCalculator extends Patronizer implements Initializable {

    public MenuItem versionText;
    public TextField numberToCalc;
    public TextField resultText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        versionText.setText(ProgramInfos.VERSION);
    }

    public void calcTotiente(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        try{
            int number = Integer.parseInt(numberToCalc.getText());
            resultText.setText(Integer.toString(totient(number)));
        } catch (Exception ignored){
            resultText.setText("Número inválido");
        }
    }

    public int totient(int num){
        int count = 0;

        for(int a = 1; a < num; a++)
            if(fastEuclideanAlgorithm(num, a) == 1)
                count++;

        return(count);
    }

    public int fastEuclideanAlgorithm(int a, int b){
        int temp;

        if(a < b){
            temp = a;
            a = b;
            b = temp;
        }

        if(a % b == 0)
            return(b);

        return(fastEuclideanAlgorithm(a % b, b));
    }
}
