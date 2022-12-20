package com.example.appfirebase;

public class Student
{
    String uId; //unique key
    String mail;
    String pass;
    String name;
    String mobile;
    int age;

    public Student() {
    }

    public Student(String uId, String mail, String pass, String name, String mobile, int age) {
        this.uId = uId;
        this.mail = mail;
        this.pass = pass;
        this.name = name;
        this.mobile = mobile;
        this.age = age;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
       return "NAME = "+name+" MOBILE = "+mobile+" AGE = "+age+" MAIL = "+mail;
    }

}


//1) pojo class banayo -> Student
//pela none select kari ne constructoir banavnu
// pachi badha ne select kar ne banavanu ctr+a kari ne
//getter and setter for all attribute
// pure java class -model
