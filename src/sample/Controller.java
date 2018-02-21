package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.*;

public class Controller
{
    CheckValue checkValue = new CheckValue();

    ObservableList wybor = (ObservableList) FXCollections.observableArrayList("Wybierz OSD", "Innogy", "Enea", "Energa", "Tauron Enion", "Tauron EnergiaPro", "Tauron GZE",
            "PGE Białystok", "PGE Lublin", "PGE Łódź Miasto", "PGE Łódź Teren", "PGE Rzeszów", "PGE Skarżysko Kamienna", "PGE Warszawa", "PGE Zamość", "PSG Warszawa",
            "PSG Wrocław", "PSG Poznań", "PSG Zabrze", "PSG Tarnów");

    @FXML
    private void initialize() throws Exception
    {
        choicebox.setValue("Wybierz OSD");
        choicebox.setItems(wybor);
    }
    @FXML
    private void akcja(javafx.event.ActionEvent event) throws Exception
    {
        try
        {
            String maskChoice = choicebox.getValue().toString();

            switch (maskChoice)
            {
                case "Innogy":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 33, 7,"PL00000");
                    break;
                case "Enea":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 32, 13,"PLENED0000059");
                    break;
                case "Energa":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 18, 6,"PL0037");
                    break;
                case "Tauron Enion":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                    {
                        if (getTextFromTextField().startsWith("PLTAUD"))
                            checkCode(getTextFromTextField(), 18, 6,"PLTAUD");
                        else
                            checkCode(getTextFromTextField(),15, 5, "ENID_");
                    }
                    break;
                case "Tauron EnergiaPro":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                    {
                        if (getTextFromTextField().startsWith("PLTAUD"))
                            checkCode(getTextFromTextField(), 18, 6,"PLTAUD");
                        else
                            checkCode(getTextFromTextField(),15, 5, "PROD_");
                    }
                    break;
                case "Tauron GZE":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 32, 6,"PLGZEO");
                    break;
                case "PGE Białystok":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 21, 6,"PL_ZEB");
                    break;
                case "PGE Lublin":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                    {
                        if (getTextFromTextField().startsWith("PL_LUB"))
                            checkCode(getTextFromTextField(), 21, 6,"PL_LUB");
                        else
                            checkCode(getTextFromTextField(),21, 7, "PL_PGEL");
                    }
                    break;
                case "PGE Łódź Miasto":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 18, 6,"PLLZED");
                    break;
                case "PGE Łódź Teren":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 18, 6,"PLZELD");
                    break;
                case "PGE Rzeszów":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 18, 6,"480548");
                    break;
                case "PGE Skarżysko Kamienna":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 21, 7,"PL_ZEOD");
                    break;
                case "PGE Warszawa":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 21, 7,"PL_ZEWD");
                    break;
                case "PGE Zamość":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                    {
                        if (getTextFromTextField().startsWith("PLZKED"))
                            checkCode(getTextFromTextField(), 18, 6,"PLZKED");
                        else if (getTextFromTextField().startsWith("PL_ZK"))
                            checkCode(getTextFromTextField(), 20, 5, "PL_ZK");
                        else
                            checkCode(getTextFromTextField(), 21, 6, "PL_ZKE");
                    }
                    break;
                case "PSG Warszawa":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkValue.checkCode2(getTextFromTextField(), 10);
                    break;
                case "PSG Wrocław":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 10, 1,"5");
                    break;
                case "PSG Poznań":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 10, 1,"12");
                    break;
                case "PSG Zabrze":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 12, 5,"PL003");
                    break;
                case "PSG Tarnów":
                    if (getTextFromTextField().isEmpty())
                        isEmpty();
                    else
                        checkCode(getTextFromTextField(), 9, 2,"00");
                    break;
                default:
                    default1();
                    break;
            }
        }
        catch (RuntimeException e)
        {
            JOptionPane.showMessageDialog(null, "Błąd " + e.getMessage());
        }
    }
    private void isEmpty()
    {
        label1.setCache(true);
        label1.setTextFill(Color.RED);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        label1.setEffect(ds);
        label1.setText("Wpisz kod");
    }

    private void checkCode(String kod, int lenght, int endConstant, String constant)
    {
        String tekst = getTextFromTextField();
        try
        {
            boolean checkCode = new CheckValue().checkCode(kod, lenght, endConstant, constant);

                if (checkCode)
                {
                    label1.setCache(true);
                    label1.setTextFill(Color.GREEN);
                    DropShadow ds = new DropShadow();
                    ds.setOffsetY(3.0f);
                    ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
                    label1.setEffect(ds);
                    label1.setText("KOD PRAWIDŁOWY");
                }
                else
                {
                    label1.setCache(true);
                    label1.setTextFill(Color.RED);
                    DropShadow ds = new DropShadow();
                    ds.setOffsetY(3.0f);
                    ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
                    label1.setEffect(ds);
                    label1.setText("KOD BŁĘDNY");
                }
            pole1.setText(String.valueOf(tekst).toUpperCase());
        }
        catch (RuntimeException e)
        {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Nie wpisano wartości");
        }
    }
    private void tauronEnion()
    {
        String code [] = new String[]{"PLTAUD", "ENID_"};
        int length[] = new int []{18, 15};
        int substring[] = new int[]{code[0].length(), code[1].length()};
    }
    private void default1()
    {
        label1.setCache(true);
        label1.setTextFill(Color.RED);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        label1.setEffect(ds);
        label1.setText("Nie wybrano OSD");

    }
    @FXML
    public String getTextFromTextField()
    {
        String kod = pole1.getText().trim().toUpperCase();
        this.kod = kod;
        return kod;
    }
    @FXML
    public void setTextLenght()
    {
        int lenght = pole1.getText().trim().length();
        textLenght.setText(String.valueOf(lenght));
    }
    @FXML
    private void closeApp()
    {
        System.exit(0);
    }
    @FXML
    private void closeWindow()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();

        stage.close();
    }

    String kod;

    @FXML
    TextField pole1;

    @FXML
    Label label1;

    @FXML
    ChoiceBox choicebox;

    @FXML
    Label textLenght;

    @FXML
    Button closeButton;

}





