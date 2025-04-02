/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.models;

/**
 *
 * @author Mauricio
 */
public class CompetitorDTO {
 
    private String name;
    
    private String surname;
    
    private int age;
    
    private String telephone;
    
    private String cellphone;
    
    private String address;
    
    private String city;
    
    private String country;
    
    private String email;
    
    private String passw;

//    public CompetitorDTO(String name, String surname, int age, String telephone, String cellphone, String address, String city, String country, String email, String passw) {
//        this.name = name;
//        this.surname = surname;
//        this.age = age;
//        this.telephone = telephone;
//        this.cellphone = cellphone;
//        this.address = address;
//        this.city = city;
//        this.country = country;
//        this.email = email;
//        this.passw = passw;
//    }
    
    
    
    public CompetitorDTO(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }
    
    
}
