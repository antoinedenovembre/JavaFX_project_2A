package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Index {
    @FXML
    private CheckBox cbox;
    @FXML
    private PasswordField password;
    @FXML
    private TextField txt;
    @FXML
    private TreeView<String> treeView;

    public void initialize() {
        cbox.setText("To be checked");
        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);
        root.getChildren().addAll(
                new TreeItem<>("usr"),
                new TreeItem<>("dev"),
                new TreeItem<>("bin")
        );
        treeView.setRoot(root);
    }

    @FXML
    private void OnCheck(ActionEvent actionEvent) {
        if (cbox.isSelected()) cbox.setText("Checked");
        else cbox.setText("To be checked");
    }

    @FXML
    public void checkPasswd(ActionEvent actionEvent) {
        txt.setText("Password is " + password.getCharacters().toString());
    }
}
