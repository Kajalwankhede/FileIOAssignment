package com.employeepayroll;

import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import com.employeepayroll.EmployeePayRollService.IOCommand;

import static org.junit.Assert.assertEquals;

public class EmployeePayRollServiceTest {
    EmployeePayRollService employeePayRollService;

  @Test
    public void init() {
        EmployeePayRollData[] listOfPayRoll = {
                new EmployeePayRollData(1,"Jeff Bezos",100000.0),
                new EmployeePayRollData(2, "Bill Gates",200000.0),
                new EmployeePayRollData(3, "Mark Zuckerberg",300000.0)
        };

        employeePayRollService = new EmployeePayRollService();
        employeePayRollService.setEmployeePayRollDataList(Arrays.asList(listOfPayRoll));
        employeePayRollService.writeEmployeePayrollData(IOCommand.FILE_IO);
    }

    @Test
    public void givenThreeEmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        Assert.assertEquals(3, EmployeePayRollService.countEntries(IOCommand.FILE_IO));
    }
}
