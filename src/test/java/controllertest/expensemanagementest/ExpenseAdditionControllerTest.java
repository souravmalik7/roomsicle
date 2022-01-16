package controllertest.expensemanagementest;

import commandline.CommandLineInputProperties;
import controller.ControllerProperties;
import controller.expensemanagement.ExpenseAdditionController;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class ExpenseAdditionControllerTest {
    ExpenseAdditionController expenseAdditionController=new ExpenseAdditionController();
    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }
    @Test
    public void ExpenseTest(){
        Assert.assertEquals("books",expenseAdditionController.ValidateExpense("books"));
    }

    @Test
    public void userExpenseAdditionTest(){
        Assert.assertEquals(100,expenseAdditionController.ValidataeExpenseAddition(100));
    }

    @Test
    public void userDescription(){
        Assert.assertEquals("Fruits",expenseAdditionController.viewDescription("Fruits"));
    }

}