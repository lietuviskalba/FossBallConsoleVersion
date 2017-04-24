/**
 * Created by Kompas on 2017-04-06.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Controller { //implements Initializable {

    @FXML
    private ComboBox<String> combobox;
    @FXML
    private Label label;
    @FXML
    private TextField nameInput, birthInput, emailInput, idInput, readOutput;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;

    @FXML
    private void saveButtonAction(ActionEvent event) {
        if(saveButton.getText().equals("Save")){
            saveButton.setText("SAVED!!!");
        }else{
            saveButton.setText("Save");
        }
    }
    @FXML
    private void buttonAction(ActionEvent event) {
        //label.setText("Selected + " + combobox.getValue());
       // combobox.setItems(list);// updates list

    }
    @FXML
    public void loadAction(ActionEvent event) {

        List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection(); //load players to comboBox

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players");
            while (rs.next()) {
                members.add(
                        rs.getInt(1) +" "
                                + rs.getString(2));

            }
            con.close();

            ObservableList<String> list = FXCollections.observableArrayList();

            String listString = "";

            for (String s : members) {
                listString += list.add(s);
            }
            combobox.setItems(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void readAction(ActionEvent event){

        String readText = readOutput.getText();
        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM players");
            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + "  "
                                + rs.getString(2) + "  "
                                + rs.getString(3));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    //delete method DONE
    @FXML
    public void deleteAction(ActionEvent actionEvent){

        String deleteID = idInput.getText();

        try {
            String sql = "DELETE FROM `Players` WHERE `Players`.`ID` + " + deleteID+ ";";

            System.out.println(sql);

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //create player DONE
    @FXML
    public void createAction(ActionEvent actionEvent) {

        String name = nameInput.getText();
        String birth = birthInput.getText();
        String email = emailInput.getText();

        System.out.println("Name ->" + name + "<-");

        try {
            String sql = "INSERT INTO Players VALUES " +
                    "(NULL, " +"'"+ name +"'"+ ", " + birth+"," +   "'"     +email+  "'     "+"," +" "+ 0 + ");";

            System.out.println(sql);

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
