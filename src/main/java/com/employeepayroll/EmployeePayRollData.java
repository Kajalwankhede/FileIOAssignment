package com.employeepayroll;

public class EmployeePayRollData {
    public int id;
    public String name;
    public double salary;
    public EmployeePayRollData(int id,String name,double salary){
        this.id=id;
        this.name=name;
        this.salary=salary;
    }


public void printData(){
    System.out.println("Employee Id: "+id);
    System.out.println("Employee Name: "+name);
    System.out.println("Employee Salary: "+salary);
}
    @Override
    public String toString(){
        return "EmployeePayRollDetails {" +
                "ID: "+id+
                ",Name: "+name+
                ",Salary: "+salary+
                '}';
    }
}
