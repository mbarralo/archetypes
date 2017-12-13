#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ${package}.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by mbarralo on 22-08-2016.
 */
public class MemberDao {

    //*******************************
    public static ObservableList<Member> searchMembers() {
        String selectStmt = "SELECT * FROM users";
        try {
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Member> empList = getEmployeeList(rsEmps);
            return empList;
        } catch (Exception e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw new RuntimeException(e);
        }
    }

    private static ObservableList<Member> getEmployeeList(ResultSet rs) {

        ObservableList<Member> empList = FXCollections.observableArrayList();

        try {
            while (rs.next()) {
                Member emp = new Member();

                emp.setUsername(rs.getString("username"));
                emp.setPassword(rs.getString("password"));
                emp.setName(rs.getString("name"));
                emp.setGender(rs.getString("gender"));
                emp.setBirthDate(LocalDate.parse(rs.getString("birth"), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                //Add employee to the ObservableList
                empList.add(emp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //return empList (ObservableList of Employees)
        return empList;
    }


    public static void insertMember(String username, String password, String gender, String birthdate) {
        String updateStmt =
                "INSERT INTO users${symbol_escape}n" +
                        "(username, password, gender, birth)${symbol_escape}n" +
                        "VALUES${symbol_escape}n" +
                        "('" + username + "', '" + password + "','" + gender + "','" + birthdate + "');";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (Exception e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw new RuntimeException(e);
        }
    }

    public static void deleteMember(String username) {
        String updateStmt =
                "DELETE FROM users${symbol_escape}n" +
                        "WHERE USERNAME = '" + username + "';";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (Exception e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw new RuntimeException(e);
        }
    }
}
