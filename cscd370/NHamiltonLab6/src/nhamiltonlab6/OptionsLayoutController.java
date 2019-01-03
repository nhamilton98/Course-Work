package nhamiltonlab6;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class OptionsLayoutController extends Dialog<ButtonType> implements Initializable
{
    @FXML
    private CheckBox italics;
    @FXML
    private CheckBox bold;
    @FXML
    private RadioButton dateTime;
    @FXML
    private RadioButton showString;
    @FXML
    private TextField textInput;
    @FXML
    private TextField textSizeInput;
    
    private ButtonType mButtonOK;
    
    public OptionsLayoutController() throws IOException
    {
        super();
        
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("OptionsLayout.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        this.getDialogPane().setContent(root);
        
        mButtonOK = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType buttonCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(mButtonOK, buttonCancel);
        
        Button buttonOK = (Button) this.getDialogPane().lookupButton(mButtonOK);
        buttonOK.addEventFilter(ActionEvent.ACTION, actionEvent -> validateOK(actionEvent));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        italics.setSelected(OptionsData.italics);
        bold.setSelected(OptionsData.bold);
        dateTime.setSelected(OptionsData.dateTime);
        showString.setSelected(OptionsData.showString);
        if (dateTime.isSelected())
            textInput.setDisable(true);
        textInput.setText(OptionsData.userString);
        textSizeInput.setText(((Integer)OptionsData.size).toString());
    }
    
    @FXML
    private void onShowDateTime(ActionEvent event) { textInput.setDisable(true); }
    
    @FXML
    private void onShowString(ActionEvent event) { textInput.setDisable(false); }
    
    private void validateOK(ActionEvent event)
    {
        int textSize;
        try
        {
            textSize = Integer.parseInt(textSizeInput.getText());
            if (textSize < 8 || textSize > 40)
            {
                this.showSizeDialog();
                event.consume();
            }
            else
            {
                OptionsData.bold = this.bold.isSelected();
                OptionsData.italics = this.italics.isSelected();
                OptionsData.dateTime = this.dateTime.isSelected();
                OptionsData.showString = this.showString.isSelected();
                OptionsData.userString = this.textInput.getText();
                OptionsData.size = textSize;
            }
        } catch (NumberFormatException e)
        {
            Logger.getLogger(OptionsLayoutController.class.getName()).log(Level.SEVERE, "Text size is not an integer.", e.getMessage());
            this.showSizeDialog();
            event.consume();
        }
    }
    
    private void showSizeDialog()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Text Size");
        alert.setHeaderText("Text size must be between 8 and 40.");
        alert.showAndWait();
        
        textSizeInput.selectAll();
    }
}
