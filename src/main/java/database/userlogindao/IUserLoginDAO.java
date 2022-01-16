package database.userlogindao;

import java.util.Map;

public interface IUserLoginDAO {
     Map<String, String> getUserLoginAndPassword(String query);
}
