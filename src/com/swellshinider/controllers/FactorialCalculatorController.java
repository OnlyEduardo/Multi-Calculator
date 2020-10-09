package com.swellshinider.controllers;

import com.swellshinider.util.Patronizer;
import com.swellshinider.util.ProgramInfos;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

public class FactorialCalculatorController extends Patronizer implements Initializable {

    public MenuItem versionText;
    public TextArea resultText;
    public TextField numberToCalc;
    public Text nOfZeros;
    public Text nOfCharacters;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        versionText.setText(ProgramInfos.VERSION);
        resultText.setWrapText(true);
    }

    public void calcFactorial(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        try {
            int value = Integer.parseInt(numberToCalc.getText());

            if(value > 12000 || value < 0){
                resultText.setText("Valor fora do limite estabelecido");
                nOfZeros.setText("");
                nOfCharacters.setText("");
                return;
            }

            BigInteger factorial = getFactorial(new BigInteger(Long.toString(value)));

            resultText.setText(factorial.toString());
            nOfZeros.setText("O número de zeros no final de "+ value +"! é " + getZeros(value));
            nOfCharacters.setText("O número de digitos de "+ value +"! é " + factorial.bitLength());

        } catch (Exception ignored){
            resultText.setText("Número inválido!");
            nOfZeros.setText("");
            nOfCharacters.setText("");
        } catch (StackOverflowError ignored2){
            resultText.setText("Não é possivel calcular esse fatorial pois ultrapassa o limite de RAM do seu computador");
            nOfZeros.setText("");
            nOfCharacters.setText("");
        }
    }

    private long getZeros(long value){
        long result = 0;

        for(int i = 2; i <= value; i++)
            result += countFactorsOf5(i);

        return result;
    }

    private int countFactorsOf5(int i){
        int count = 0;

        while(i % 5 == 0) {
            count++;
            i /= 5;
        }

        return count;
    }

    public BigInteger getFactorial(BigInteger n) {
        BigInteger result;

        if (n.equals(BigInteger.ZERO))
            result = BigInteger.ONE;
        else
            result = n.multiply(getFactorial(n.subtract(BigInteger.ONE)));

        return result;
    }
}
