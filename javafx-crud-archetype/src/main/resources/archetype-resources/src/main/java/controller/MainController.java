#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ${package}.model.Member;
import ${package}.model.MemberDao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by mbarralo on 19-08-2016.
 */
public class MainController implements Initializable {

    @FXML
    Label usernameLabel;

    @FXML
    Label genderLabel;

    @FXML
    Label birthLabel;

    @FXML
    TableView memberTable;

    @FXML
    TableColumn<Member, String> memberUsernameColumn;

    @FXML
    TableColumn<Member, String> memberGenderColumn;

    @FXML
    TableColumn<Member, LocalDate> memberBirthDateColumn;

    @FXML
    Button deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        memberUsernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        memberGenderColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        memberBirthDateColumn.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());

        refreshMembers();

        // bind visibility of delete button to actual selection of row
        deleteButton.visibleProperty().bind(memberTable.getSelectionModel().selectedItemProperty().isNotNull());

        memberTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> changedRow(newValue));
        memberTable.setStyle("-fx-focus-color: transparent");
    }

    @FXML
    private void startAddMember(ActionEvent event) throws IOException {
        System.out.println("starter Member");

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/member.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.setTitle("Modal to Create Member");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        NewMemberController controller = loader.getController();
        controller.injectMainController(this);
    }

    private void changedRow(Object newValue) {
        if (newValue != null && newValue instanceof Member) {
            Member member = (Member) newValue;
            genderLabel.setText(member.getGender());
            usernameLabel.setText(member.getUsername());
            birthLabel.setText(member.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
        }
    }

    public void refreshMembers() {
        memberTable.setItems(MemberDao.searchMembers());
    }

    @FXML
    private void deleteMember(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Object selectedItem = memberTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null && selectedItem instanceof Member) {
                Member member = (Member) memberTable.getSelectionModel().getSelectedItem();
                System.out.println("deleting " + member.getUsername());
                MemberDao.deleteMember(member.getUsername());
                refreshMembers();
            } else {
                System.out.println("No item is selected!");
            }
        } else {
            System.out.println("canceled");
        }

    }

    @FXML
    public void quitApplication(ActionEvent event) {
        System.out.println("cleaning up and closing..");
        System.exit(0);
    }

    @FXML
    private void showAboutDialog(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Members App Help");
        alert.setHeaderText("Members Application Demo");
        alert.setContentText("This is for demo purposes.${symbol_escape}nIt contains all sorts of stuff!");

        alert.showAndWait();
    }
}
