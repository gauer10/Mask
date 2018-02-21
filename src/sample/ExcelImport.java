package sample;

import com.sun.security.auth.module.NTSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Iterator;
import java.util.logging.XMLFormatter;

public class ExcelImport
{
    public void getExcel() throws IOException
    {


        FileFilter filter = new FileFilter()
        {
            @Override
            public boolean accept(File f)
            {
                if (f.isDirectory())
                    return true;
                else if (f.getName().endsWith(".xlsx"))
                    return true;
                else
                    return false;
            }

            @Override
            public String getDescription() {
                return "Plik Excel (.xlsx)";
            }
        };
        try
        {
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            frame.setDefaultCloseOperation(3);

            String username = new NTSystem().getName();
            JFileChooser chooser = new JFileChooser(String.valueOf(JFileChooser.APPROVE_OPTION));
            chooser.setCurrentDirectory(new File("C:/Users/" + username + "/Desktop/"));
            chooser.setFileFilter(filter);
            chooser.setSelectedFile(new File(""));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.showOpenDialog(frame);

            File file = new File(String.valueOf(chooser.getSelectedFile()));
            FileInputStream Excel = new FileInputStream(file);


            this.Excel = file;
            this.username = username;

            if (!(file == null))
            {
                setClassVariable();
            }

        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Nie wybrano pliku");
        }
        catch (NotOfficeXmlFileException e)
        {
            JOptionPane.showMessageDialog(null, "Błąd odczytu pliku");
        }


    }
    public int findRowDistribution() throws IOException
    {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(Excel));

          XSSFSheet sheet = workbook.getSheetAt(0);

          int rowNum = 0;
          for (Row row : sheet) {
              for (Cell cell : row) {
                  if (cell.getCellTypeEnum() == CellType.STRING) {
                      if (cell.getRichStringCellValue().getString().trim().equals("Dystrybutor"))
                          return row.getRowNum();
                  }
              }
          }
          new FileInputStream(Excel).close();
        return 0;

    }

    public int findCellDistribution() throws IOException {
        String aaa = "Dystrybutor";

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(Excel));
        XSSFSheet sheet = workbook.getSheetAt(0);

        int cellNum = 0;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellTypeEnum() == CellType.STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals(aaa))
                        return cell.getColumnIndex();
                }
            }
        }
        new FileInputStream(Excel).close();
        return cellNum;
    }
    public int findRowPPE() throws IOException {
        String PPE = "Kod PPE";

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(Excel));
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rowNum = 0;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellTypeEnum() == CellType.STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals(PPE))
                        return row.getRowNum();
                }
            }
        }
        new FileInputStream(Excel).close();
        return rowNum;
    }
    public int findCellPPE() throws IOException {
        String aaa = "Kod PPE";


        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(Excel));
        XSSFSheet sheet = workbook.getSheetAt(0);

        int cellNum = 0;
        for (Row row : sheet)
        {
            for (Cell cell : row)
            {
                if (cell.getCellTypeEnum() == CellType.STRING)
                {
                    if (cell.getRichStringCellValue().getString().trim().equals(aaa))
                        return cell.getColumnIndex();

                }
            }
        }
        new FileInputStream(Excel).close();
        return cellNum;
    }
    public void setClassVariable() throws IOException
    {
        this.rowDistribution = findRowDistribution();
        this.cellDistribution = findCellDistribution();
        this.rowPPE = findRowPPE();
        this.cellPPE = findCellPPE();
        checkPPE();
    }
    public void checkPPE() throws IOException, NullPointerException
    {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(Excel));
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(rowDistribution);
        Cell cell = row.getCell(cellDistribution);
        int rowlenght = sheet.getLastRowNum();
        String maskchoice[] = new String[rowlenght];
        String PPE[] = new String[rowlenght];
        String checkResult[] = new String[rowlenght];
        this.checkResult = checkResult;

            for (int i = 1; i <= rowlenght; i++) {
                row = sheet.getRow(rowDistribution + i);
                cell = row.getCell(cellDistribution);
                maskchoice[i - 1] = cell.getStringCellValue();

                row = sheet.getRow(rowPPE + i);
                cell = row.getCell(cellPPE);
                PPE[i - 1] = cell.getStringCellValue();
            }
            for (int i = 0; i < maskchoice.length; i++)
            {
                switch (maskchoice[i]) {
                    case "Innogy":
                        boolean innogyCheck = checkValue.checkCode(PPE[i], 33, 7, "PL00000");
                        if (innogyCheck) {
                            checkResult[i] = maskchoice[i] + " " + PPE[i] + " Kod prawidłowy\n";
                        } else {
                            checkResult[i] = maskchoice[i] + " " + PPE[i] + " Kod błędny\n";
                        }
                        break;
                    case "Enea":
                        boolean eneaCheck = checkValue.checkCode(PPE[i], 32, 13, "PLENED");
                        if (eneaCheck) {
                            checkResult[i] = maskchoice[i] + " " + PPE[i] + " Kod prawidłowy\n";
                        } else {
                            checkResult[i] = maskchoice[i] + " " + PPE[i] + " Kod błędny\n";
                        }
                        break;
                    default:
                    {
                        checkResult[i] = maskchoice[i] + " " + PPE[i] + " Nie rozpoznano OSD\n";
                    }
                }

            }
        new FileInputStream(Excel).close();

            setResult();
    }
    public void setResult() throws IOException
    {
        File directory = new File("C:/Users/" + username + "/Desktop/excel/Nowy folder/");
        directory.mkdirs();
        String Filename = String.valueOf(directory + "/Raport poprawności kodów.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(Filename));

        for (int i = 0; i < checkResult.length; i ++)
        {
            String content = checkResult[i];
            bw.write(content);
            bw.newLine();
        }
        bw.close();

        Desktop desktop = Desktop.getDesktop();
        desktop.open(directory);
    }

    CheckValue checkValue= new CheckValue();

    File Excel;

    String username;

    public int rowDistribution;
    public int cellDistribution;
    public int rowPPE;
    public int cellPPE;
    String checkResult[];



}




