#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import ${package}.model.MemberDao;
import ${package}.util.WindowUtils;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Created by mbarralo on 22-08-2016.
 */
public class NewMemberController implements Initializable {

    @FXML
    TextField textUsername;
    
    @FXML
    TextField textPassword;

    @FXML
    DatePicker birthDatePicker;

    @FXML
    ToggleGroup gender;

    private MainController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void addMember(ActionEvent event) {

        MemberDao.insertMember(textUsername.getText(), textPassword.getText(), ((RadioButton) gender.getSelectedToggle()).getText(), birthDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        Stage stage = WindowUtils.getStageFromEvent(event);
        stage.close();

        mainController.refreshMembers();
    }



    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
