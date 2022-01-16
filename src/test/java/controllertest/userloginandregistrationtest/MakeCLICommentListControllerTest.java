package controllertest.userloginandregistrationtest;

import commandline.CommandLineInputProperties;
import controller.ControllerProperties;
import controller.clicommentlist.MakeCLICommentListController;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MakeCLICommentListControllerTest  {
    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void makeCLICommentListControllerTest() {
        MakeCLICommentListController makeCLICommentListController=new MakeCLICommentListController();
        String com1;
        List<String> expResult = new ArrayList<>();
        com1= CommandLineInputProperties.getCommandLineInputPropertyValue("login.fail.makecli.message");
        expResult.add(com1);
        List<String> actual = makeCLICommentListController.makeCLICommentListController("login.fail.message");
        Assert.assertEquals(expResult, actual);
    }
}