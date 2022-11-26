package com.employeepayroll;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class EmployeePayRollService {
    private List<EmployeePayRollData> employeePayRollDataList;
    public EmployeePayRollService(){}

    public EmployeePayRollService(List<EmployeePayRollData>employeePayRollDataList){
        this.employeePayRollDataList=employeePayRollDataList;
    }
    public static void main(String[] args) {
        ArrayList<EmployeePayRollData>employeePayRollDataList=new ArrayList<>();
        EmployeePayRollService employeePayRollService=new EmployeePayRollService(employeePayRollDataList);
        Scanner consoleInputReader=new Scanner(System.in);
        employeePayRollService.readEmployeePayRollData(consoleInputReader);// Reading empPayRoll Data
        employeePayRollService.writeEmployeePayRollData();
    }

    private void readEmployeePayRollData(Scanner consoleInputReader) {//Passing scanner from console
        System.out.println("Enter Employee ID: ");
        int id=consoleInputReader.nextInt();
        System.out.println("Enter Employee Name: ");
        String name=consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary=consoleInputReader.nextDouble();
        EmployeePayRollData data=new EmployeePayRollData(id,name,salary);
        employeePayRollDataList.add(data);// Creating object data and adding to list
    }
    private void writeEmployeePayRollData(){
        System.out.println("\nWriting Employee PayRoll reader to Console: "+employeePayRollDataList);// writing data into console
    }
}

