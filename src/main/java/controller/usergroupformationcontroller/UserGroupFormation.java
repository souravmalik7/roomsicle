package controller.usergroupformationcontroller;

import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import controller.clicommentlist.IMakeCLICommentListController;
import database.usergroupformationdao.IUserGroupFormationDAO;
import models.usergroupformationmodel.IUserGroupFormationModel;

public class UserGroupFormation implements IUserGroupFormation{
    public void GroupFormation(){
        String firstChoice;
        String secondChoice;
        String thirdChoice;
        IMakeCLICommentListController makeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        IRoomsicleCLI roomsicleCLI=ClassInitializer.initializer().getRoomsicleCLI();
        IUserGroupFormationModel userGroupFormationModel=ClassInitializer.initializer().getUserGroupFormationModel();
        IUserGroupFormationDAO userGroupFormationDAO=ClassInitializer.initializer().getUserGroupFormationDAO();

        makeCLICommentListController.makeCLICommentListController("choose.your.roommate.message");
        makeCLICommentListController.makeCLICommentListController("user.first.choice.message");
        firstChoice=roomsicleCLI.getStringResponse();
        userGroupFormationModel.setFirstEmailId(firstChoice);

        makeCLICommentListController.makeCLICommentListController("user.second.choice.message");
        secondChoice=roomsicleCLI.getStringResponse();
        userGroupFormationModel.setSecondEmailId(secondChoice);

        makeCLICommentListController.makeCLICommentListController("user.third.choice.message");
        thirdChoice=roomsicleCLI.getStringResponse();
        userGroupFormationModel.setThirdEmailId(thirdChoice);

        userGroupFormationDAO.UserGroupFormationDAO(userGroupFormationModel);
        makeCLICommentListController.makeCLICommentListController("group.created.successfully.message");
        ClassInitializer.initializer().getNavigator().navigator();
    }
}
