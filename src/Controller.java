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

    //All of these are assigned for combo boxes
    @FXML
    private ComboBox<String> PlayerListInUpdateAndRead;
    @FXML
    private ComboBox<String> TeamListInUpdateAndRead;
    @FXML
    private ComboBox<String> chooseForTeamPlayerONE ;//when create teams
    @FXML
    private ComboBox<String> chooseForTeamPlayerTWO ;
    @FXML
    private ComboBox<String> chooseToUpdateTeamPlayesONE;//when updating teams
    @FXML
    private ComboBox<String> chooseToUpdateTeamPlayesTWO;
    //Tournament comboBoxes for all the teams and rounds
    @FXML
    private ComboBox<String> Team1R1;//Round 1
    @FXML
    private ComboBox<String> Team2R1;
    @FXML
    private ComboBox<String> Team3R1;
    @FXML
    private ComboBox<String> Team4R1;
    @FXML
    private ComboBox<String> Team5R1;
    @FXML
    private ComboBox<String> Team6R1;
    @FXML
    private ComboBox<String> Team7R1;
    @FXML
    private ComboBox<String> Team8R1;
    @FXML
    private ComboBox<String> Team1R2;//Round 2
    @FXML
    private ComboBox<String> Team2R2;
    @FXML
    private ComboBox<String> Team3R2;
    @FXML
    private ComboBox<String> Team4R2;
    @FXML
    private ComboBox<String> Team1R3;//Finaly
    @FXML
    private ComboBox<String> Team2R3;
    @FXML
    private ComboBox<String> winner;//WINNER

    @FXML
    private TextField createTeamName;
    @FXML
    private TextField nameInput, birthInput, emailInput, idInput, idTeamInput;//create for players
    @FXML
    private TextField readName;//read for players
    @FXML
    private TextField readbirth;//read for players
    @FXML
    private TextField readEmail;//read for players
    @FXML
    private TextField readRank;//read for players

    @FXML
    private TextField readPlayerONE;//read for teams
    @FXML
    private TextField readPlayerTWO;//read for teams
    @FXML
    private TextField readTeamName;//read for teams

    @FXML
    private Button saveButton;

    @FXML
    private void saveButtonAction(ActionEvent event) {


        //if(saveButton.getText().equals("Save")){
        //    saveButton.setText("SAVED!!!");
       // }else{
        //    saveButton.setText("Save");
       // }

        String laimetojai = winner.getValue();

        try {
            String sql =  "UPDATE Teams SET Score = Score+ 1 WHERE Teams.TeamName = '" + laimetojai + "'";

            System.out.println(sql);

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //LOAD action for Team players //Create
    @FXML
    public void loadActionTeamPlayers(ActionEvent event) {// this method is USED FOR CREATING A TEAM OUT OF 2 PLAYERS

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
            chooseForTeamPlayerONE.setItems(list);
            chooseForTeamPlayerTWO.setItems(list);
            //this in create/read tab


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //LOAD action for Teams //Update&Read
    @FXML
    public void loadActionUpdateForTeams(ActionEvent event) {

        List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection(); //load players to comboBox

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Teams");
            while (rs.next()) {
                members.add(
                        rs.getInt(1) +" "
                                +   rs.getString(4));
            }
            con.close();

            ObservableList<String> list = FXCollections.observableArrayList();

            String listString = "";

            for (String s : members) {
                listString += list.add(s);
            }
            TeamListInUpdateAndRead.setItems(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Load action for Players //Update&Read
    @FXML
    public void loadActionUpdateForPlayers(ActionEvent event) {

        List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection(); //load players to comboBox

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players");
            while (rs.next()) {
                members.add(
                        rs.getInt(1) +" "
                              +   rs.getString(2));
            }
            con.close();

            ObservableList<String> list = FXCollections.observableArrayList();

            String listString = "";

            for (String s : members) {
                listString += list.add(s);
            }
            PlayerListInUpdateAndRead.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //READ action for Team information //Update&Read
    @FXML
    public void readActionForTeams(ActionEvent event){

        String readID = idTeamInput.getText();

        try {

            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Teams WHERE TeamID = " + readID);
            while (rs.next()) {

                readPlayerONE.setText(rs.getString(2));
                readPlayerTWO.setText(rs.getString(3));
                readTeamName.setText(rs.getString(4));
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //READ action for Player information //Update&Read
    @FXML
    public void readActionForPlayers(ActionEvent event){

        String readID = idInput.getText();

        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players WHERE ID = " + readID);

            while (rs.next()) {

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
    //DELETE action for Players //Update&Read
    @FXML
    public void deleteActionForPlayers(ActionEvent actionEvent){

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
    //DELETE action for Teams //Update&Read
    @FXML
    public void deleteActionForTeams(ActionEvent actionEvent){

        String deleteID = idTeamInput.getText();

        try {
            String sql = "DELETE FROM `Teams` WHERE `TeamID` = " + deleteID+ ";";

            System.out.println(sql);

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //UPDATE action for players //Update&Read
    @FXML
    public void updateActionForPlayers(ActionEvent actionEvent){
      String id = idInput.getText();
      String name =  readName.getText();
      String birth = readbirth.getText();
      String email = readEmail.getText();

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
    //UPDATE action for Teams //Update&Read
    @FXML
    public void updateActionForTeams(ActionEvent actionEvent){
        String idTeam = idTeamInput.getText();
        String player1 = chooseToUpdateTeamPlayesONE.getValue();//readPlayerONE.getText();
        String player2 = chooseToUpdateTeamPlayesTWO.getValue();//readPlayerTWO.getText();
        String teamName = readTeamName.getText();

        try {
            String sql =  "UPDATE Teams SET Member1 = "+ "'" +player1+"'" + ", Member2 =  +" + "'"+ player2 + "',"+ "TeamName ="  + " '" + teamName+"'" + "WHERE Teams. TeamID =" + idTeam;


            System.out.println(sql);

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //CREATE action for Team //Create
    @FXML
    public void createActionForTeams(ActionEvent actionEvent){

        String teamname = createTeamName.getText() ;
        String player1Name =chooseForTeamPlayerONE.getValue(); //member1
        String player2Name =chooseForTeamPlayerTWO.getValue(); //member2

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
    //CREATE action for Players //Create
    @FXML
    public void createActionForPlayers(ActionEvent actionEvent) {

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
    //LOAD action for Teams //Tournament
    @FXML
    public void loadActionForTeamsTournaments(ActionEvent actionEvent){

        List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Teams");

            while (rs.next()) {
                members.add(
                        rs.getString(4));
            }
            con.close();

            ObservableList<String> list = FXCollections.observableArrayList();

            String listString = "";

            for (String s : members) {
                listString += list.add(s);
            }
            Team1R1.setItems(list);
            Team2R1.setItems(list);
            Team3R1.setItems(list);
            Team4R1.setItems(list);
            Team5R1.setItems(list);
            Team6R1.setItems(list);
            Team7R1.setItems(list);
            Team8R1.setItems(list);
            Team1R2.setItems(list);
            Team2R2.setItems(list);
            Team3R2.setItems(list);
            Team4R2.setItems(list);
            Team1R3.setItems(list);
            Team2R3.setItems(list);
            winner.setItems(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
