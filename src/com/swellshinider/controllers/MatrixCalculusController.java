package com.swellshinider.controllers;

import com.swellshinider.util.Patronizer;
import com.swellshinider.util.ProgramInfos;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MatrixCalculusController extends Patronizer implements Initializable {

    public Pane panel;
    public MenuItem versionText;
    public TextArea matrixA;
    public TextArea matrixB;
    public TextArea matrixC;
    public Text errorMessage;
    public ChoiceBox<String> calculusChoice;

    private int n;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        versionText.setText(ProgramInfos.VERSION);

        calculusChoice = new ChoiceBox<>();

        calculusChoice.setPrefSize(114, 25);
        calculusChoice.setLayoutX(51);
        calculusChoice.setLayoutY(360);
        calculusChoice.getItems().addAll("Multiplicar", "Somar", "Subtrair");
        calculusChoice.setValue("Multiplicar");

        panel.getChildren().add(calculusChoice);
    }

    private double[][] getMatrixFromTextArea(String stringedMatrix){
        String[] lines = stringedMatrix.split("\n");
        double[][] matrix = new double[lines.length][lines.length];

        try {

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                String[] valuesInActualLine = line.split(" ");

                // Verifica se matrix é NxN
                if (lines.length != valuesInActualLine.length)
                    return null;

                n = lines.length;

                // Prepara os divisores
                for (int j = 0; j < valuesInActualLine.length; j++){
                    float val;
                    String item = valuesInActualLine[j];
                    item = item.replace(",", ".");
                    String[] div = item.split("/");

                    if(div.length == 2)
                        val = Float.parseFloat(div[0]) / Float.parseFloat(div[1]);
                    else
                        val = Float.parseFloat(item);

                    matrix[i][j] = val;
                }
            }

        } catch (Exception ignored){
            return null;
        }

        return matrix;
    }

    private void checkValidMatrix(double[][] matrix1, double[][] matrix2, double[][] result) {
        if(matrix1 == null){
            errorMessage.setText("Matriz A parece ter algum erro");
        }
        else if(matrix2 == null){
            errorMessage.setText("Matriz B parece ter algum erro");
        }
        else if(result == null){
            errorMessage.setText("Matrizes inválidas, verifique se algum caractere inválido foi adicionado");
        } else {
            showMatrix(result);
        }
    }

    private void showMatrix(double[][] result){
        StringBuilder _final = new StringBuilder();

        for(double[] lines: result){
            String line = Arrays.toString(lines).replace(", ", " ");
            line = line.replace("[", "");
            line = line.replace("]", "");
            line = line.replace(".", ",");

            _final.append(line).append("\n");
        }

        matrixC.setText(new String(_final));
    }

    public void calculateAction(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        switch (calculusChoice.getValue()){
            case "Multiplicar":
                doMultiplication();
                break;
            case "Somar":
                doSum();
                break;
            case "Subtrair":
                doSub();
                break;
        }
    }

    private void doSub() {
        double[][] matrix1 = getMatrixFromTextArea(matrixA.getText());
        double[][] matrix2 = getMatrixFromTextArea(matrixB.getText());
        double[][] result = subMatrix(matrix1, matrix2);

        checkValidMatrix(matrix1, matrix2, result);
    }

    private void doSum() {
        double[][] matrix1 = getMatrixFromTextArea(matrixA.getText());
        double[][] matrix2 = getMatrixFromTextArea(matrixB.getText());
        double[][] result = sumMatrix(matrix1, matrix2);

        checkValidMatrix(matrix1, matrix2, result);
    }

    private void doMultiplication(){
        double[][] matrix1 = getMatrixFromTextArea(matrixA.getText());
        double[][] matrix2 = getMatrixFromTextArea(matrixB.getText());
        double[][] result = multiplicationMatrix(matrix1, matrix2);

        checkValidMatrix(matrix1, matrix2, result);
    }

    // Methods
    private double[][] multiplicationMatrix(double[][] A, double[][] B){

        if (A == null || B == null || A.length != B.length)
            return null;

        double[][] AxB = new double[n][n];
        DecimalFormat df = new DecimalFormat("###,##0.00");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sm = 0;

                for (int k = 0; k < n; k++){
                    sm += A[i][k] * B[k][j];
                }

                AxB[i][j] = Double.parseDouble(df.format(sm).replace(",", "."));
            }
        }

        return AxB;
    }

    private double[][] sumMatrix(double[][] A, double[][] B){

        if (A == null || B == null || A.length != B.length)
            return null;

        double[][] AxB = new double[n][n];
        DecimalFormat df = new DecimalFormat("###,##0.00");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sm = A[i][j] + B[i][j];
                AxB[i][j] = Double.parseDouble(df.format(sm).replace(",", "."));
            }
        }

        return AxB;
    }

    private double[][] subMatrix(double[][] A, double[][] B){

        if (A == null || B == null || A.length != B.length)
            return null;

        double[][] AxB = new double[n][n];
        DecimalFormat df = new DecimalFormat("###,##0.00");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sm = A[i][j] - B[i][j];
                AxB[i][j] = Double.parseDouble(df.format(sm).replace(",", "."));
            }
        }

        return AxB;
    }
}
