import model.ThreadWrite;
import socket.MyServerSocket;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        MyServerSocket app = null;
        /**Options: - 127.0.0.1
         * - 0.0.0.0
         * - null
         * if null uses ip assigned by DHCP
         */
        String ipaddres = null;
        int port = 50000;

        try {
            app = new MyServerSocket(ipaddres, port);
            System.out.println("\r\nRunning Server: " +
                    "Host=" + app.getSocketAddress().getHostAddress() +
                    " Port=" + app.getPort());
            System.out.println("Responses made with id of connection separated by ;");
            System.out.println("Example: 0;message here");

            while (true) {
                app.listen();
            }
        } catch (Exception e) {
            e.printStackTrace();
            app.closeConnection();
        }
    }
}
