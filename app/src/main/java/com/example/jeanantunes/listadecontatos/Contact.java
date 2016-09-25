package com.example.jeanantunes.listadecontatos;

/**
 * Created by jean.antunes on 20/09/2016.
 */
public class Contact {
    private String name;
    private String lastName;
    private String pathImage;
    private String email;
    private String phone;
    private String cellPhone;
    private String birthday;

    public Contact(){
        this("", "", "", "", "", "", "");
    }

    public Contact(String name, String lastName , String pathImage, String email, String phone, String cellPhone, String birthday) {
        this.name = name;
        this.lastName = lastName;
        this.pathImage = pathImage;
        this.email = email;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }
    public void setName (String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }
    public void setCellPhone (String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getBirthday() {
        return birthday;
    }
    public void setBirthday (String birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return pathImage;
    }

    public void setImage(String pathImage) {
        this.pathImage = pathImage;
    }


}
