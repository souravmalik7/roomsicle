package controller.clicommentlist;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static controller.usersurvey.UserSurveyConstants.ZERO;

public class MakeCLICommentListController implements IMakeCLICommentListController {
    public List<String> makeCLICommentListController(String... comments){
        List<String> CliList = new ArrayList<>();
        for (String comment : comments) {
            Collections.addAll(CliList,comment);
        }
        IRoomsicleCLI iroomsicleCLI= ClassInitializer.initializer().getRoomsicleCLI();
        int limit;
        for (limit=ZERO;limit<CliList.size();limit++){
            iroomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue(CliList.get(limit)));
        }
        return CliList;
    }
}
