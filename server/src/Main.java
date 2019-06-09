import socket.MyServerSocket;

import java.net.BindException;

public class Main {

    public static void main(String[] args) {
        MyServerSocket app = null;
        /**Options: - 127.0.0.1
         * - 0.0.0.0
         * - null
         * if null uses ip assigned by DHCP
         */
        String ipAddress = null;
        int port = 50000;

        try {
            app = new MyServerSocket(ipAddress, port);
            System.out.println("\r\nRunning Server: " +
                    "Host=" + app.getSocketAddress().getHostAddress() +
                    " Port=" + app.getPort());
            System.out.println("Responses made with id of connection separated by ;");
            System.out.println("Example: 0;message here");
            System.out.println("Close connection with a client by using ID;close");
            System.out.println("Broadcast with id number -1, Example: -1;Broadcast message \n");

            while (true) {
                app.listen();
            }
        } catch (BindException e){
            System.out.println("Ip address for server is not valid");
        } catch (IllegalArgumentException e){
            System.out.println("Port value is out of range for server");
        } catch(Exception e) {
            e.printStackTrace();
            try {
                app.closeConnection();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }
}
