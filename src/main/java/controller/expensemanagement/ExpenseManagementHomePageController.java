package controller.expensemanagement;

import commandline.CommandLineInputProperties;
import commandline.RoomsicleCLI;
import controller.ClassInitializer;

public class ExpenseManagementHomePageController {
    static  RoomsicleCLI roomsicleCLI = new RoomsicleCLI();

    static int userSelection;
    public void showHomePage() {
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("expense.decorator.message"));
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("expense.management.welcome.message"));
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("expense.decorator.message"));
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("expense.management.select.option.message"));
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("expense.management.select.choice.message"));
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("expense.management.select.user.choice.message"));
        userSelection = roomsicleCLI.getNumberResponse();
        if (userSelection==1){
            ExpenseAdditionController expenseAdditionController =new ExpenseAdditionController();
            expenseAdditionController.userExpenseAddition();
        }
        else if (userSelection==2){
            ExpenseSettleUpController expenseSettleUpController=new ExpenseSettleUpController();
            expenseSettleUpController.userExpenseSettleUp();
        }
        else{
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.invalid.input.message"));
        }
        ClassInitializer.initializer().getNavigator().navigator();
    }
}
