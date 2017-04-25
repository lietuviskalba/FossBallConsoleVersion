/**
 * Created by Kompas on 2017-04-06.1
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
    private ComboBox<String> comboboxReadUpdate ;
    @FXML
    private ComboBox<String> createPlayerOne ;
    @FXML
    private ComboBox<String> createPlayerTwo ;

    @FXML
    private TextField createTeamName;

    @FXML
    private Label label;
    @FXML
    private TextField nameInput, birthInput, emailInput, idInput, readOutput;//create
    @FXML
    private TextField readName;//read
    @FXML
    private TextField readbirth;//read
    @FXML
    private TextField readEmail;//read
    @FXML
    private TextField readRank;//read
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
    //create load action NOT FUCKING WORKING
    @FXML
    public void loadActionCreate(ActionEvent event) {// this method is USED FOR CREATING A TEAM OUT OF 2 PLAYERS

        List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection(); //load players to comboBox

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players");

            while (rs.next()) {
                members.add(
                    rs.getString(2));
            }
            con.close();

            ObservableList<String> list = FXCollections.observableArrayList();

            String listString = "";

            for (String s : members) {
                listString += list.add(s);
            }
            //comboboxReadUpdate.setItems(list);
            createPlayerOne.setItems(list);
            createPlayerTwo.setItems(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update load action
    @FXML
    public void loadActionUpdate(ActionEvent event) {

        List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection(); //load players to comboBox

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players");
            while (rs.next()) {
                members.add(
                        rs.getInt(1) +"ID# "
                              +   rs.getString(2));


            }
            con.close();

            ObservableList<String> list = FXCollections.observableArrayList();

            String listString = "";

            for (String s : members) {
                listString += list.add(s);
            }
            comboboxReadUpdate.setItems(list);
            //createPlayerOne.setItems(list);
            //createPlayerTwo.setItems(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void readAction(ActionEvent event){

        String readID = idInput.getText();
        String fieldName = "" ;
        String fieldBirth ="";
        String fieldEmail;
        String fieldRank;

        try {

            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players WHERE ID = " + readID);
            while (rs.next()) {

                //System.out.println(rs.getString(1));
                readName.setText(rs.getString(2));
                readbirth.setText(rs.getString(3));
                readEmail.setText(rs.getString(4));
                readRank.setText(rs.getString(5));
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
            String sql = "DELETE FROM `Players` WHERE `ID` = " + deleteID+ ";";

            System.out.println(sql);

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //update player info IN PROGRESS
    @FXML
    public void updatePlayerAction(ActionEvent actionEvent){
        //cia koda rasyk
      String id = idInput.getText();
      String name =  readName.getText();
      String birth = readbirth.getText();
      String email = readEmail.getText();
       String rank = readRank.getText();

        try {
            String sql =  "UPDATE Players SET name = "+ "'" +name+"'" + ", birth = + " + "'"+ birth + "',"+ "email ="  + " '" + email+"'" + "WHERE Players. ID =" + id;

            System.out.println(sql);

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //create team finished.
    @FXML
    public void createTeamAction(ActionEvent actionEvent){

        String teamname = createTeamName.getText() ;
        String player1Name =createPlayerOne.getValue(); //member1
        String player2Name =createPlayerTwo.getValue(); //member2

        System.out.println("Name ->" + teamname + "<-");


        try {
            String sql = "INSERT INTO Teams VALUES " +
                    "(NULL, " +"'"+ player1Name +"'"+ ", " + "'"     + player2Name+  "' "  +  " ," +   "'"     +teamname+  "'     "+"," +" "+ 0 + ");";

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
