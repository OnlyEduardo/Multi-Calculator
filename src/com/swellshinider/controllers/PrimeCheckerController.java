package com.swellshinider.controllers;

import com.swellshinider.util.Patronizer;
import com.swellshinider.util.ProgramInfos;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimeCheckerController  extends Patronizer implements Initializable {

    public MenuItem versionText;
    public TextField numberToCalc;
    public Text resultText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        versionText.setText(ProgramInfos.VERSION);
    }

    public void checkPrime(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        try {
            int value = Integer.parseInt(numberToCalc.getText());

            if(value <= 1){
                resultText.setText("Número não é primo!");
                return;
            }

            if(isPrime(value))
                resultText.setText("Número é primo!");
            else
                resultText.setText("Número não é primo!");

        } catch (Exception ignored){
            resultText.setText("Número inválido!");
        }
    }

    public boolean isPrime(int num){
        if(num == 2)
            return true;

        for(int i = 2; i <= (int) Math.sqrt(num) + 1; i++){
            if(num % i == 0)
                return false;
        }

        return true;
    }
}
