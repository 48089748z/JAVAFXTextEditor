package sample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.*;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.io.*;

public class Controller
{
    public TextArea texto;
    public MenuItem Eenganxar;
    public MenuItem Ecopiar;
    public CheckMenuItem arial;
    public CheckMenuItem arialBlack;
    public Button btcopy;
    public Button btpaste;
    public Button btcut;
    public double fontSize;
    public void initialize()
    {
        fontSize = texto.getFont().getSize();
        btcopy.setGraphic(new ImageView("copy.png"));
        btpaste.setGraphic(new ImageView("paste.png"));
        btcut.setGraphic(new ImageView("cut.png"));
    }
    public void sortir(ActionEvent actionEvent) {Platform.exit();} //Tanca l'aplicació
    public void copiar(ActionEvent actionEvent) { texto.copy();} //Copia text seleccionat
    public void tallar(ActionEvent actionEvent) {texto.cut();} //Talla text seleccionat
    public void enganxar(ActionEvent actionEvent) {texto.paste();} //Enganxa text seleccionat
    public void desfer(ActionEvent actionEvent) {texto.undo();} //Desfa la ultima acció
    public void setRed(ActionEvent actionEvent) {texto.setStyle("-fx-text-fill: red;");} //Fica el text de color vermell
    public void setGreen(ActionEvent actionEvent) {texto.setStyle("-fx-text-fill: green;");} //Fica el text de color verd
    public void setBlue(ActionEvent actionEvent) {texto.setStyle("-fx-text-fill: blue;");} //Fica el text de color blau
    public void fontArialBlack(ActionEvent actionEvent)  //Fica el text a DejaVu Sans Bold
    {
        texto.setFont(new Font("DejaVu Sans Bold", fontSize));
        CheckMenuItem item = (CheckMenuItem) actionEvent.getSource();
        arialBlack.setSelected(false);
        arial.setSelected(false);
        item.setSelected(true);
        texto.setFont(new Font(item.getText(), texto.getFont().getSize()));
    }
    public void fontArial(ActionEvent actionEvent) //Fica el text a DejaVu Sans
    {
        texto.setFont(new Font("DejaVu Sans", fontSize));
        CheckMenuItem item = (CheckMenuItem) actionEvent.getSource();
        arialBlack.setSelected(false);
        arial.setSelected(false);
        item.setSelected(true);
        texto.setFont(new Font(item.getText(), texto.getFont().getSize()));
        texto.setFont(new Font("DejaVu Sans Bold", fontSize));
    }
    public void zoomInTamany(ActionEvent actionEvent) //Augmenta el tamany del text
    {
        fontSize=fontSize+10;
        texto.setFont(Font.font(fontSize));
    }
    public void zoomOutTamany(ActionEvent actionEvent) //Disminueix el tamany del text
    {
        if (fontSize>10)
        {
            fontSize=fontSize-10;
            texto.setFont(Font.font(fontSize));
        }
    }
    public void deshabilitar(Event Event) //Deshabilita les opcions enganxar i copiar quan no hi ha text seleccionat
    {
        if (texto.getSelectedText().equals(""))
        {
            Eenganxar.setDisable(true);
            Ecopiar.setDisable(true);
        }
        else
        {
            Eenganxar.setDisable(false);
            Ecopiar.setDisable(false);
        }
    }
    public void help(ActionEvent actionEvent)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help Window");
        alert.setHeaderText("There is no help available");
        alert.setContentText("This is Microsoft Word 2.0!");
        alert.showAndWait();
    }
    public void open(ActionEvent actionEvent) //OPEN A FILE
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Resource File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new ExtensionFilter("All Files", "*.*"));
        Stage mainStage = new Stage();
        File selectedFile = chooser.showOpenDialog(mainStage);
        chooser.setTitle(selectedFile.getName());
        try
        {
            BufferedReader bfr = new BufferedReader(new FileReader(selectedFile));
            while (bfr.ready())
            {
                texto.setText(texto.getText().toString() + bfr.readLine() + "\n");
            }
        }
        catch (FileNotFoundException one) {}
        catch (IOException two){}
    }
    public void save(ActionEvent actionEvent) //SAVE A FILE
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Resource File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new ExtensionFilter("All Files", "*.*"));
        Stage mainStage = new Stage();
        File selectedFile = chooser.showSaveDialog(mainStage);
        chooser.setTitle(selectedFile.getName());
        try
        {
            selectedFile.createNewFile();
            BufferedWriter bfw = new BufferedWriter(new FileWriter(selectedFile));
            bfw.write(texto.getText().toString());
        }
        catch (FileNotFoundException one) {}
        catch (IOException two){}
    }
}


