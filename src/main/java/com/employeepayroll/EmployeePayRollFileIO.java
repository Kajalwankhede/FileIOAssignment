package com.employeepayroll;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class EmployeePayRollFileIO {
    public static String payrollFile = "EmployeePayRoll.txt";

    public void writeData(List<EmployeePayRollData> employeePayRollDataList) {

        StringBuffer empBuffer = new StringBuffer();
        employeePayRollDataList.forEach(employee -> {
            String employeeDataStr = employee.toString().concat("\n");
            empBuffer.append(employeeDataStr);
        });

        try {
            Files.write(Paths.get(payrollFile), empBuffer.toString().getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public int countEntries() {

        int entries = 0;

        try {
            entries = (int) Files.lines(new File(payrollFile).toPath()).count();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return entries;
    }
    public void printData(){// show the data
        try{
            Files.lines(new File(payrollFile).toPath()).forEach(System.out::println);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<EmployeePayRollData>  readData() {
        List<EmployeePayRollData> employeePayRollDataList = new ArrayList<EmployeePayRollData>();

        try {
            Files.lines(new File(payrollFile).toPath())
                    .map(line->line.trim())
                    .forEach(line-> System.out.println(line));

        }catch(IOException exception) {
            exception.printStackTrace();
        }
        return employeePayRollDataList;
    }

}
