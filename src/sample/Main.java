package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class Main extends Application
{

    private static Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("MainStage.fxml"));
        primaryStage.setTitle("Maska kodów");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        Scene scene = new Scene(root, width / 5, height / 3);
        int widthFrame = (int) scene.getWidth();
        int heightFrame = (int) scene.getHeight();
        primaryStage.setX((width - widthFrame) / 2);
        primaryStage.setY((height - heightFrame)/ 2);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(false);
        primaryStage.show();


    }
    public void secondStage() throws IOException
    {
        int width =  Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
        Pane secondStage = loader.load();

        Stage addSecondStage = new Stage();
        addSecondStage.setTitle("Mask Writer");
        addSecondStage.initModality(Modality.WINDOW_MODAL);
        addSecondStage.initOwner(primaryStage);
        Scene scene = new Scene(secondStage);
        addSecondStage.setWidth(500);
        addSecondStage.setHeight(250);
        int widthFrame = (int) addSecondStage.getWidth();
        int heightFrame = (int)addSecondStage.getHeight();
        addSecondStage.setX((width - widthFrame) / 2);
        addSecondStage.setY((height - heightFrame) / 2);
        addSecondStage.initStyle(StageStyle.UNIFIED);

        addSecondStage.setScene(scene);
        addSecondStage.setResizable(true);
        addSecondStage.showAndWait();
    }


    public static void main(String[] args) throws IOException { launch(args);}




}
