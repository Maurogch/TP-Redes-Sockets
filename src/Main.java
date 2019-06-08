import socket.MyServerSocket;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        MyServerSocket app = null;

        try {
            app = new MyServerSocket(null);
            System.out.println("\r\nRunning Server: " +
                    "Host=" + app.getSocketAddress().getHostAddress() +
                    " Port=" + app.getPort());

            app.listen();
        } catch (Exception e) {
            e.printStackTrace();
            app.closeConnection();
        }
    }
}
