package commandline;

public interface IRoomsicleCLI {

    void printMessage(String message);

    String getStringResponse();

    int getNumberResponse();

    long getLongNumberResponse();
}
