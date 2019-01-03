package nhamiltonfinal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;

public class SettingsLayoutController extends Dialog<Void> implements Initializable
{
    @FXML
    private ChoiceBox<Integer> segPicker;
    @FXML
    private ChoiceBox<Integer> rockPicker;
    
    private ButtonType mButtonOK;
    
    public SettingsLayoutController() throws IOException
    {
        super();
        
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SettingsLayout.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        this.getDialogPane().setContent(root);
        
        mButtonOK = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType buttonDefault = new ButtonType("Default", ButtonData.APPLY);
        ButtonType buttonCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(mButtonOK, buttonDefault, buttonCancel);
        
        Button buttonOK = (Button) this.getDialogPane().lookupButton(mButtonOK);
        buttonOK.addEventHandler(ActionEvent.ACTION, actionEvent -> onOK(actionEvent));
        Button def = (Button) this.getDialogPane().lookupButton(buttonDefault);
        def.addEventHandler(ActionEvent.ACTION, actionEvent -> onDefault(actionEvent));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        segPicker.setItems(FXCollections.observableArrayList(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        segPicker.setValue(CentipedeSettings.numCentipedeSegs);
        rockPicker.setItems(FXCollections.observableArrayList(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30));
        rockPicker.setValue(CentipedeSettings.numRocks);
    }
    
    private void onOK(ActionEvent event)
    {
        CentipedeSettings.numCentipedeSegs = segPicker.getValue();
        CentipedeSettings.numRocks = rockPicker.getValue();
        CentipedeSettings.storePreferences(getClass());
    }
    
    private void onDefault(ActionEvent event)
    {
        segPicker.setValue(10);
        rockPicker.setValue(15);
        CentipedeSettings.numCentipedeSegs = segPicker.getValue();
        CentipedeSettings.numRocks = rockPicker.getValue();
        CentipedeSettings.storePreferences(getClass());
        event.consume();
    }
}