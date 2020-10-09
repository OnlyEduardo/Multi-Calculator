package com.swellshinider.controllers;

import com.swellshinider.util.Patronizer;
import com.swellshinider.util.ProgramInfos;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MatrixDeterminantController extends Patronizer implements Initializable {

    public MenuItem versionText;
    public TextField resultText;
    public TextArea matrixText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        versionText.setText(ProgramInfos.VERSION);
    }

    public void calcDet(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        String[] separatedPerLines = matrixText.getText().split("\n");
        boolean isAllRight = true;
        float[][] matrix = new float[separatedPerLines.length][separatedPerLines.length];

        try{
            for (int i = 0; i < separatedPerLines.length; i++) {
                String line = separatedPerLines[i];
                String[] valuesInActualLine = line.split(" ");

                // Verifica se matrix é NxN
                if (separatedPerLines.length != valuesInActualLine.length) {
                    resultText.setText("Matriz inválida");
                    isAllRight = false;
                    break;
                }

                // Prepara os divisores
                for (int j = 0; j < valuesInActualLine.length; j++){
                    String item = valuesInActualLine[j];
                    float number;

                    String[] div = item.split("/");

                    if(div.length == 2)
                        number = Float.parseFloat(div[0]) / Float.parseFloat(div[1]);
                    else
                        number = Float.parseFloat(item);

                    matrix[i][j] = number;
                }
            }

            if(isAllRight)
                resultText.setText(String.valueOf(determinant(matrix)));

        } catch (Exception ignored){
            resultText.setText("Matriz inválida");
        }
    }

    private float determinant(float[][] matrix){
        int result = 0;
        int saver;

        if(matrix.length==1){
            return(matrix[0][0]);
        }

        for(int i = 0; i < matrix.length; i++){
            float[][]smaller = new float[matrix.length-1][matrix.length-1];

            for(int a = 1; a < matrix.length; a++){
                for(int b = 0; b < matrix.length; b++){
                    if(b < i)
                        smaller[a - 1][b] = matrix[a][b];
                    else if(b > i)
                        smaller[a - 1][b - 1] = matrix[a][b];
                }
            }

            saver = i % 2 == 0 ? 1 : -1;
            result += saver * matrix[0][i] * (determinant(smaller));
        }

        return(result);
    }
}
