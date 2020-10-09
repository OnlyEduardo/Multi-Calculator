package com.swellshinider.controllers;

import com.swellshinider.util.Patronizer;
import com.swellshinider.util.ProgramInfos;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseConverterController extends Patronizer implements Initializable {

    public Pane panel;
    public MenuItem versionText;
    public TextField numberToConvert;
    public TextField resultText;

    public ChoiceBox<String> entryBox1;
    public ChoiceBox<String> entryBox2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        versionText.setText(ProgramInfos.VERSION);

        entryBox1 = new ChoiceBox<>();
        entryBox2 = new ChoiceBox<>();

        entryBox1.setPrefSize(69, 25);
        entryBox1.setLayoutX(290);
        entryBox1.setLayoutY(86);
        entryBox1.getItems().addAll("Bin", "Oct", "Dec", "Hex");
        entryBox1.setValue("Dec");

        entryBox2.setPrefSize(69, 25);
        entryBox2.setLayoutX(148);
        entryBox2.setLayoutY(123);
        entryBox2.getItems().addAll("Bin", "Oct", "Dec", "Hex");
        entryBox2.setValue("Dec");

        panel.getChildren().addAll(entryBox1, entryBox2);
    }

    public void convertToBase(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        try{

            String r = baseToBase(numberToConvert.getText(), entryBox1.getValue(), entryBox2.getValue());

            if(numberToConvert.getText().equals("")){
                resultText.setText("");
                return;
            }

            if(r == null){
                resultText.setText("Número não corresponde a base");
                return;
            }

            resultText.setText(r);
        } catch (Exception ignored){
            resultText.setText("Número não corresponde a base");
        }
    }

    // Conversores
    private String baseToBase(String value, String iBase, String toBase){

        if(iBase.equals(toBase))
            return value;

        switch (iBase){
            case "Bin":
                return binToSome(value, toBase);
            case "Oct":
                return octToSome(value, toBase);
            case "Dec":
                return decToSome(value, toBase);
            case "Hex":
                return hexToSome(value, toBase);
        }

        return null;
    }

    private String hexToSome(String value, String toBase) {

        try {
            switch (toBase){
                case "Bin":
                    return decToSome(String.valueOf(Integer.parseInt(value, 16)), "Bin");
                case "Oct":
                    return decToSome(String.valueOf(Integer.parseInt(value, 16)), "Oct");
                case "Dec":
                    return String.valueOf(Integer.parseInt(value, 16));
            }

        } catch (Exception ignored) { }

        return null;
    }

    private String decToSome(String value, String toBase) {

        try {
            switch (toBase){
                case "Bin":
                    return Integer.toBinaryString(Integer.parseInt(value));
                case "Oct":
                    return Integer.toOctalString(Integer.parseInt(value));
                case "Hex":
                    return Integer.toHexString(Integer.parseInt(value));
            }

        } catch (Exception ignored) { }

        return null;
    }

    private String octToSome(String value, String toBase) {

        try {
            switch (toBase){
                case "Bin":
                    return decToSome(String.valueOf(Integer.parseInt(value, 8)), "Bin");
                case "Dec":
                    return String.valueOf(Integer.parseInt(value, 8));
                case "Hex":
                    return decToSome(String.valueOf(Integer.parseInt(value, 8)), "Hex");
            }

        } catch (Exception ignored) { }

        return null;
    }

    private String binToSome(String value, String toBase){

        try {
            switch (toBase){
                case "Oct":
                    return decToSome(String.valueOf(Integer.parseInt(value, 2)), "Oct");
                case "Dec":
                    return String.valueOf(Integer.parseInt(value, 2));
                case "Hex":
                    return decToSome(String.valueOf(Integer.parseInt(value, 2)), "Hex");
            }

        } catch (Exception ignored) { }

        return null;
    }
}
