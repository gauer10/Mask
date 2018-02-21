package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import java.io.IOException;

public class Controller2
{
    @FXML
    private void showSecondStage() throws IOException
    {
        main.secondStage();

    }
    @FXML
    private void ExcelOpen() throws IOException
    {
        excelImport.getExcel();
    }
    @FXML
    private void Exit()
    {
        System.exit(0);
    }


    ExcelImport excelImport = new ExcelImport();

    Main main = new Main();
}

