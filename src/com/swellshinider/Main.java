package com.swellshinider;

import com.swellshinider.util.ProgramInfos;
import com.swellshinider.util.SceneName;
import com.swellshinider.util.Scenes;
import com.swellshinider.util.TitleScenes;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

    public static Main instance;

    private Stage primaryStage;

    @Override
    public void start(Stage _primaryStage) {
        instance = this;
        primaryStage = _primaryStage;
        primaryStage.getIcons().add(new ImageView(new Image(getClass().getResource("resources/icon.png").toExternalForm())).getImage());
        primaryStage.setResizable(false);

        changeToScene(SceneName.MAIN);
    }

    public static void main(String[] args) { launch(args); }

    public void changeToScene(SceneName sceneName){
        switch (sceneName) {
            case MAIN:
                primaryStage.setTitle(ProgramInfos.NAME);
                primaryStage.setScene(Scenes.MAIN_SCENE);
                break;
            case PRIME_FACTORIZATION:
                primaryStage.setTitle(TitleScenes.PRIME_FACTORIZATION);
                primaryStage.setScene(Scenes.PRIME_FACTORIZER_SCENE);
                break;
            case MATRIX_DETERMINANT:
                primaryStage.setTitle(TitleScenes.MATRIX_DETERMINANT);
                primaryStage.setScene(Scenes.MATRIX_DETERMINANT_SCENE);
                break;
            case BASE_CONVERTER:
                primaryStage.setTitle(TitleScenes.BASE_CONVERTER);
                primaryStage.setScene(Scenes.BASE_CONVERTER_SCENE);
                break;
            case EULER_FUNCTION:
                primaryStage.setTitle(TitleScenes.EULER_FUNCTION);
                primaryStage.setScene(Scenes.TOTIENTE_EULER_SCENE);
                break;
            case PRIME_NUMBER_CHECKER:
                primaryStage.setTitle(TitleScenes.PRIME_NUMBER_CHECKER);
                primaryStage.setScene(Scenes.PRIME_CHECKER_SCENE);
                break;
            case FACTORIAL_CALCULATOR:
                primaryStage.setTitle(TitleScenes.FACTORIAL_CALCULATOR);
                primaryStage.setScene(Scenes.FACTORIAL_CALCULATOR_SCENE);
                break;
            case MMC_MDC_CALCULATOR:
                primaryStage.setTitle(TitleScenes.MMC_MDC_CALCULATOR);
                primaryStage.setScene(Scenes.MMC_AND_MDC_CALCULATOR_SCENE);
                break;
            case MATRIX_CALCULUS:
                primaryStage.setTitle(TitleScenes.MATRIX_CALCULUS);
                primaryStage.setScene(Scenes.MATRIX_CALCULATOR_SCENE);
                break;
            case EQUATION_CALCULATOR:
                primaryStage.setTitle(TitleScenes.EQUATION_CALCULATOR);
                primaryStage.setScene(Scenes.EQUATION_CALCULATOR_SCENE);
                break;

            case EXIT:
            default:
                System.exit(0);
        }

        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
