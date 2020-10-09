package com.swellshinider.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Parents {

    public static Parent mainParent;
    public static Parent primeFactorizationParent;
    public static Parent matrixDeterminantParent;
    public static Parent baseConverterParent;
    public static Parent totienteEulerParent;
    public static Parent primeCheckerParent;
    public static Parent factorialCalculatorParent;
    public static Parent mmcAndMdcCalculatorParent;
    public static Parent matrixCalculatorParent;
    public static Parent equationCalculatorParent;

    static {
        try {
            mainParent = FXMLLoader.load(Parents.class.getResource("fxml/main.fxml"));
            primeFactorizationParent = FXMLLoader.load(Parents.class.getResource("fxml/primeFactorizer.fxml"));
            matrixDeterminantParent = FXMLLoader.load(Parents.class.getResource("fxml/matrixDeterminant.fxml"));
            baseConverterParent = FXMLLoader.load(Parents.class.getResource("fxml/baseConverter.fxml"));
            totienteEulerParent = FXMLLoader.load(Parents.class.getResource("fxml/totienteEuler.fxml"));
            primeCheckerParent = FXMLLoader.load(Parents.class.getResource("fxml/primeChecker.fxml"));
            factorialCalculatorParent = FXMLLoader.load(Parents.class.getResource("fxml/factorialCalculator.fxml"));
            mmcAndMdcCalculatorParent = FXMLLoader.load(Parents.class.getResource("fxml/MmcAndMdcCalculator.fxml"));
            matrixCalculatorParent = FXMLLoader.load(Parents.class.getResource("fxml/matrixCalculator.fxml"));
            equationCalculatorParent = FXMLLoader.load(Parents.class.getResource("fxml/Equation.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
