package com.employeepayroll;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollService {
    public enum IOCommand {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    //private List<EmployeePayRollData> employeePayRollDataList;
    public List<EmployeePayRollData> employeePayRollDataList;

    public void setEmployeePayRollDataList(List<EmployeePayRollData> employeePayRollDataList) {
        this.employeePayRollDataList = employeePayRollDataList;
    }

    public EmployeePayRollService() {
    }

  public EmployeePayRollService(List<EmployeePayRollData> employeePayRollDataList) {
        this.employeePayRollDataList = employeePayRollDataList;
 }
    public static void main(String[] args) {
        ArrayList<EmployeePayRollData> employeePayRollDataList = new ArrayList<>();
        EmployeePayRollService employeePayRollService = new EmployeePayRollService(employeePayRollDataList);
        Scanner consoleInputReader = new Scanner(System.in);
        //employeePayRollService.readEmployeePayRollData(consoleInputReader);// Reading empPayRoll Data;
        employeePayRollService.writeEmployeePayrollData(IOCommand.CONSOLE_IO);
        employeePayRollService.writeEmployeePayrollData(IOCommand.FILE_IO);
        employeePayRollService.printData();

    }
    private void readEmployeePayRollData(Scanner consoleInputReader) {//Passing scanner from console
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee Name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        EmployeePayRollData data = new EmployeePayRollData(id, name, salary);
        employeePayRollDataList.add(data);// Creating object data and adding to list
    }

    //private void writeEmployeePayRollData(){
    //System.out.println("\nWriting Employee PayRoll reader to Console: "+employeePayRollDataList);// writing data into console
    public void writeEmployeePayrollData(IOCommand ioFormat) {
        if (ioFormat.equals(ioFormat.CONSOLE_IO)) {
            System.out.println("Writing Employee PayRoll Data to Console.");
            for (EmployeePayRollData data : employeePayRollDataList) {
                data.printData();
            }
        } else if (ioFormat.equals(ioFormat.FILE_IO)) {
            new EmployeePayRollFileIO().writeData(employeePayRollDataList);
            System.out.println("Write employee payroll in file");

        }
    }
    public static int countEntries(IOCommand ioFormat){
        if (ioFormat.equals(IOCommand.FILE_IO))
            return new EmployeePayRollFileIO().countEntries();
        return 0;
    }
    public void printData(){ //printing data
        new EmployeePayRollFileIO().printData();
    }
}

