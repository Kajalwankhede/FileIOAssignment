package com.employeepayroll;
import org.junit.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import com.employeepayroll.EmployeePayRollService.IOCommand;

public class EmployeePayRollServiceTest {
    EmployeePayRollService employeePayRollService;

  @Test
    public void init() {
        EmployeePayRollData[] listOfPayRoll = {
                new EmployeePayRollData(1,"Jenny Bezos",100000.0),
                new EmployeePayRollData(2, "Sunny Gates",200000.0),
                new EmployeePayRollData(3, "Honey Zuckerberg",300000.0)
        };

        employeePayRollService = new EmployeePayRollService();
        employeePayRollService.setEmployeePayRollDataList(Arrays.asList(listOfPayRoll));
        employeePayRollService.writeEmployeePayrollData(IOCommand.FILE_IO);
      employeePayRollService.printData();
    }

    @Test
    public void givenThreeEmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        Assert.assertEquals(3, EmployeePayRollService.countEntries(IOCommand.FILE_IO));
    }
}
