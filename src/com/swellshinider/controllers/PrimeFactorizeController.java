package com.swellshinider.controllers;

import com.swellshinider.util.Patronizer;
import com.swellshinider.util.ProgramInfos;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimeFactorizeController extends Patronizer implements Initializable {

    public TextField inputNumber;
    public TextField factorizationResult;
    public MenuItem versionText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        versionText.setText(ProgramInfos.VERSION);
    }

    public void doFactorization(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        try{
            long value = Long.parseLong(inputNumber.getText());
            factorizationResult.setText(primeFactorization(value));
        } catch (Exception ignored) {
            factorizationResult.setText("Valor inserido inválido");
        }
    }

    public static String primeFactorization(long num){
        StringBuilder result = new StringBuilder();

        for(int i = 2; i <= num; i++){
            if(num % i == 0){
                result.append(i).append("*");
                num = num/i;
                i--;
            }
        }

        return(result.substring(0, result.length()-1));
    }
}
