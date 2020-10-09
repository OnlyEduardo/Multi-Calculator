package com.swellshinider.controllers;

import com.swellshinider.access.Access;
import com.swellshinider.util.ProgramInfos;
import com.swellshinider.util.SceneName;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class MainController implements Initializable {

    public Text versionText;
    public Text appName;
    public TextField searchableText;
    public ListView<Access> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appName.setText(ProgramInfos.NAME);
        versionText.setText(ProgramInfos.VERSION);
        listView.setItems(allAccess().sorted());
    }

    private ObservableList<Access> allAccess(){

        return observableArrayList(
                new Access(
                        "Determinante de matrizes",
                        "Calcula a determinante da matriz baseado na matriz fornecida.",
                        SceneName.MATRIX_DETERMINANT
                ),
                new Access(
                        "Calculadora de MMC e MDC",
                        "Calcula o MMC e o MDC baseado nos valores fornecidos.",
                        SceneName.MMC_MDC_CALCULATOR
                ),
                new Access(
                        "Conversor de bases",
                        "Converte bases numéricas, Bin, Oct, Dec e Hex.",
                        SceneName.BASE_CONVERTER
                ),
                new Access(
                        "Calculadora de fatoriais",
                        "Calcula o fatorial, o número de zeros no final do fatorial e o número de digitos do mesmo.",
                        SceneName.FACTORIAL_CALCULATOR
                ),
                new Access(
                        "Verificador de números primos",
                        "Verifica se o número fornecido é primo.",
                        SceneName.PRIME_NUMBER_CHECKER
                ),
                new Access(
                        "Fatoração de primos",
                        "Fatora o valor fonecido em multiplicações de números primos.",
                        SceneName.PRIME_FACTORIZATION
                ),
                new Access(
                        "Função totiente de Euler",
                        "Aplica o valor fornecido na função totiente de euler e mostra o valor.",
                        SceneName.EULER_FUNCTION
                ),
                new Access(
                        "Calculadora de matrizes",
                        "Pega as duas matrizes fornecidas e faz o calculo requerido, resultando em uma matriz.",
                        SceneName.MATRIX_CALCULUS
                ),
                new Access(
                        "Calculadora de equações do segundo grau",
                        "Calcula a equação de segundo grau baseado nos coeficientes fornecidos.",
                        SceneName.EQUATION_CALCULATOR
                )
        );
    }

    public void searchAccess(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        String text = searchableText.getText().toLowerCase();

        if(text.equals("")){
            listView.setItems(allAccess().sorted());
            return;
        }

        ObservableList<Access> subentries = observableArrayList();

        for (Access access : listView.getItems()) {
            if(access.getName().startsWith(text) ||
            access.getName().endsWith(text) ||
            access.getName().equals(text))
                subentries.add(access);
            else if(access.getName().contains(text))
                subentries.add(access);
            else if(access.getDescription().contains(text))
                subentries.add(access);
        }

        if(subentries.size() == 0)
            listView.setItems(allAccess().sorted());
        else
            listView.setItems(subentries.sorted());
    }

    public void clicks() {
        Access selected = listView.getSelectionModel().getSelectedItem();
        selected.goTo();
    }
}
