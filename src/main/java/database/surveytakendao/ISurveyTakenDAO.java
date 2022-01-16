package database.surveytakendao;

import java.util.Map;

public interface ISurveyTakenDAO {
    Map<String, String> getSurveyTaken(String query);
}
