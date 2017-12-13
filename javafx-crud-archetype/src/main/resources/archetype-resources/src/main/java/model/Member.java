#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by mbarralo on 22-08-2016.
 */
public class Member {

    private StringProperty username;
    private StringProperty password;
    private StringProperty name;
    private StringProperty gender;
    private SimpleObjectProperty<LocalDate> birthDate;

    public Member() {
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        name = new SimpleStringProperty();
        gender = new SimpleStringProperty();
        birthDate = new SimpleObjectProperty<>();
    }


    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }


    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public SimpleObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }
}
