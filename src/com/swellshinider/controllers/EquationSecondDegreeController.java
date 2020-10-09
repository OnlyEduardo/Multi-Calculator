package com.swellshinider.controllers;

import com.swellshinider.util.Patronizer;
import com.swellshinider.util.ProgramInfos;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class EquationSecondDegreeController extends Patronizer implements Initializable {

    public MenuItem versionText;
    public TextField coefA;
    public TextField coefB;
    public TextField coefC;
    public Text preview;
    public Text errorText;

    // Parte 1
    public Text part1res;
    public Separator sep1;
    public Text part1resDiv;

    // Parte 2
    public Text part2res;
    public Separator sep2;
    public Text part2resDiv;

    // X1
    public Text x1res;
    public Separator sep21;
    public Text x1resDiv;

    // X2
    public Text x2res;
    public Separator sep211;
    public Text x2resDiv;

    public Text solution;

    private double a;
    private double b;
    private double c;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        versionText.setText(ProgramInfos.VERSION);
        setViews(false);
    }

    public boolean updateViewOfFormula() {
        if(buildFormula()){

            String _a;
            String _b;
            String _c;

            if((int)(a) == a)
                _a = Integer.toString((int)(a));
            else
                _a = Double.toString(a).replace(".", ",");

            if((int)(b) == b)
                _b = Integer.toString((int)(b));
            else
                _b = Double.toString(b).replace(".", ",");

            if((int)(c) == c)
                _c = Integer.toString((int)(c));
            else
                _c = Double.toString(c).replace(".", ",");

            String formula = String.format("(%s)x² + (%s)x + (%s) = 0", _a, _b, _c);
            preview.setText(formula);

            return true;
        }
        setViews(false);
        return false;
    }

    public void calculate(ActionEvent actionEvent) {
        if(!actionEvent.getEventType().equals(ActionEvent.ACTION))
            return;

        if(!updateViewOfFormula())
            return;

        setViews(true);

        boolean isLaterals = false;
        String[] raizes = new String[2];

        double r = Math.pow(b, 2) - (4 * a * c);
        double root = Math.sqrt(r);

        showPart1And2(r);

        if(Double.isNaN(root)){
            root = Math.sqrt((Math.pow(b, 2) - (4 * a * c)) * -1);
            isLaterals = true;
        }

        if(isLaterals){ // complexo

            showFormattedRaiz(root, true);
            showFormattedXs();

            if((b * -1) % (2 * a) == 0 && root % (2 * a) == 0){

                double p1 = (b * -1) / (2 * a);
                double p2 = root / (2 * a);

                if(p2 != 1.0){ // Com divisor diferente de 1

                    StringBuilder r1 = new StringBuilder();
                    StringBuilder r2 = new StringBuilder();

                    if((int) p1 == p1){
                        r1.append((int) p1);
                        r2.append((int) p1);
                    } else {
                        r1.append(p1);
                        r2.append(p1);
                    }

                    r1.append(" + ");
                    r2.append(" - ");

                    if((int) p2 == p2){
                        r1.append((int) p2);
                        r2.append((int) p2);
                    } else {
                        r1.append(p2);
                        r2.append(p2);
                    }

                    r1.append("i");
                    r2.append("i");

                    raizes[0] = r1.toString().replace(".", ",");
                    raizes[1] = r2.toString().replace(".", ",");

                } else { // Divisor inexistente

                    if((int) p1 == p1){
                        raizes[0] = ((int) p1 + " + i").replace(".", ",");
                        raizes[1] = ((int) p1 + " - i").replace(".", ",");
                    } else {
                        raizes[0] = (p1 + " + i").replace(".", ",");
                        raizes[1] = (p1 + " - i").replace(".", ",");
                    }
                }
            } else {

                if((int)(2 * a) == (2 * a)){
                    raizes[0] = ((b * -1) +" + "+  root + "i / " + (int)(2 * a)).replace(".", ",");
                    raizes[1] = ((b * -1) +" - "+ root + "i / " + (int)(2 * a)).replace(".", ",");
                } else {
                    raizes[0] = ((b * -1) +" + "+ root + "i / " + (2 * a)).replace(".", ",");
                    raizes[1] = ((b * -1) +" - "+ root + "i / " + (2 * a)).replace(".", ",");
                }
            }

        } else { // Se não for complexo
            showFormattedRaiz(root, false);
            showFormattedXs();

            double x1 = ((b * -1) + root) / (2 * a);
            double x2 = ((b * -1) - root) / (2 * a);

            if((int) x1 == x1)
                raizes[0] = Integer.toString((int) x1).replace(".", ",");
            else
                raizes[0] = Double.toString(x1).replace(".", ",");

            if((int) x2 == x2)
                raizes[1] = root == 0.0 ? "" : Integer.toString((int)x2).replace(".", ",");
            else
                raizes[1] = root == 0.0 ? "" : Double.toString(x2).replace(".", ",");
        }

        String solve;

        if(2 * a != 0)
            solve = String.format("S = {%s; %s}", raizes[0], raizes[1]);
        else
            solve = "Com a = 0, essa equação não pode ser resolvida por bhaskara. Pois divisão a zero tende ao infinito";

        solution.setText(solve);
    }

    private void showPart1And2(double r) {

        StringBuilder m1 = new StringBuilder();
        StringBuilder m2 = new StringBuilder();

        StringBuilder partDiv = new StringBuilder();

        m1.append("X = -(");
        m2.append("X = -(");

        if((int)(b) == b){
            m1.append((int)(b));
            m1.append(") ± √((");
            m1.append((int)(b));
            m1.append(")² - 4(");
            m2.append((int)(b));
        } else {
            m1.append(b);
            m1.append(") ± √((");
            m1.append(b);
            m1.append(")² - 4(");
            m2.append(b);
        }

        m2.append(") ± √");

        if((int)(a) == a){
            partDiv.append("2(");
            partDiv.append((int)a);
            partDiv.append(")");
            m1.append((int)a);
        } else {
            partDiv.append("2(");
            partDiv.append(a);
            partDiv.append(")");
            m1.append(a);
        }

        m1.append(")(");

        if((int)(c) == c)
            m1.append((int)c);
        else
            m1.append(c);

        m1.append("))");

        if((int)(r) == r)
            m2.append((int)(r));
        else
            m2.append(r);

        part1res.setText(m1.toString().replace(".", ","));
        part1resDiv.setText(partDiv.toString().replace(".", ","));
        part2res.setText(m2.toString().replace(".", ","));
        part2resDiv.setText(partDiv.toString().replace(".", ","));
    }

    private void showFormattedRaiz(double root, boolean isLateral) {
        if((int)(b * -1) == (b * -1)){
            if(isLateral){
                if((int) root == root){
                    x1res.setText("x¹ = " + (int)(b * -1) + " + " + (int) root + "i");
                    x2res.setText("x² = " + (int)(b * -1) + " - " + (int) root + "i");
                } else {
                    x1res.setText("x¹ = " + (int)(b * -1) + " + " + root + "i");
                    x2res.setText("x² = " + (int)(b * -1) + " - " + root + "i");
                }
            } else {
                if((int) root == root){
                    x1res.setText("x¹ = " + (int)(b * -1) + " + " + (int) root);
                    x2res.setText("x² = " + (int)(b * -1) + " - " + (int) root);
                } else {
                    x1res.setText("x¹ = " + (int)(b * -1) + " + " + root);
                    x2res.setText("x² = " + (int)(b * -1) + " - " + root);
                }
            }

        } else {
            if(isLateral){
                if((int) root == root){
                    x1res.setText("x¹ = " + (b * -1) + " + " + (int) root + "i");
                    x2res.setText("x² = " + (b * -1) + " - " + (int) root + "i");
                } else {
                    x1res.setText("x¹ = " + (b * -1) + " + " + root + "i");
                    x2res.setText("x² = " + (b * -1) + " - " + root + "i");
                }
            } else {
                if((int) root == root){
                    x1res.setText("x¹ = " + (b * -1) + " + " + (int) root);
                    x2res.setText("x² = " + (b * -1) + " - " + (int) root);
                } else {
                    x1res.setText("x¹ = " + (b * -1) + " + " + root);
                    x2res.setText("x² = " + (b * -1) + " - " + root);
                }
            }
        }

        x1res.setText(x1res.getText().replace(".", ","));
        x2res.setText(x2res.getText().replace(".", ","));
    }

    private void showFormattedXs() {
        if((int)(2 * a) == 2 * a){
            x1resDiv.setText((int)(2 * a) + "");
            x2resDiv.setText((int)(2 * a) + "");
        } else {
            x1resDiv.setText(2 * a + "");
            x2resDiv.setText(2 * a + "");
        }

        x1resDiv.setText(x1resDiv.getText().replace(".", ","));
        x2resDiv.setText(x2resDiv.getText().replace(".", ","));
    }

    private boolean buildFormula() {

        try {
            a = Double.parseDouble(coefA.getText().replace(",", "."));
            b = Double.parseDouble(coefB.getText().replace(",", "."));
            c = Double.parseDouble(coefC.getText().replace(",", "."));

            errorText.setText("");

            return true;
        } catch (Exception ignored){
            errorText.setText("Algum dos valores são inválidos");
            a = b = c = 0;
            return false;
        }
    }

    private void setViews(boolean _b){
        part1res.setVisible(_b);
        sep1.setVisible(_b);
        part1resDiv.setVisible(_b);

        part2res.setVisible(_b);
        sep2.setVisible(_b);
        part2resDiv.setVisible(_b);

        x1res.setVisible(_b);
        sep21.setVisible(_b);
        x1resDiv.setVisible(_b);

        x2res.setVisible(_b);
        sep211.setVisible(_b);
        x2resDiv.setVisible(_b);

        solution.setVisible(_b);
    }
}
