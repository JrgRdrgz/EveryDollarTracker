package com.example.everydollartracker;

public class profile_info {

    private String email;
    private String password;
    private String DOF;
    private String FullName;
    private String UserName;
    private String Income;
    private String Expenses;
    public profile_info(){}

    public profile_info(String email, String password, String DOF, String fullName, String userName, String income, String expenses) {
        this.email = email;
        this.password = password;
        this.DOF = DOF;
        FullName = fullName;
        UserName = userName;
        Income = income;
        Expenses = expenses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDOF() {
        return DOF;
    }

    public void setDOF(String DOF) {
        this.DOF = DOF;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getIncome() {
        return Income;
    }

    public void setIncome(String income) {
        Income = income;
    }

    public String getExpenses() {
        return Expenses;
    }

    public void setExpenses(String expenses) {
        Expenses = expenses;
    }
}
