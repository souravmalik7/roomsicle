package commandline;

import java.util.Scanner;

public class RoomsicleCLI implements IRoomsicleCLI {

    private Scanner scanner;

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getStringResponse() {
        String response;
        scanner = new Scanner(System.in);
        response = scanner.nextLine();
        return response;
    }

    @Override
    public int getNumberResponse() {
        int response;
        scanner = new Scanner(System.in);
        response = scanner.nextInt();
        scanner.nextLine();
        return response;
    }

    @Override
    public long getLongNumberResponse() {
        long response;
        scanner = new Scanner(System.in);
        response = scanner.nextLong();
        scanner.nextLine();
        return response;
    }

}
