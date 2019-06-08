package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
    private ServerSocket server;

    public MyServerSocket(String ipAddress) throws Exception {
        if (ipAddress != null && !ipAddress.isEmpty())
            this.server = new ServerSocket(3000, 50, InetAddress.getByName(ipAddress));
        else
            this.server = new ServerSocket(3000, 50, InetAddress.getLocalHost());
    }

    public void listen() throws Exception {
        String data = null;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        out.println("Telnet " + server.getInetAddress().getHostAddress() + " port " + server.getLocalPort() );
        out.println("Close connection with 'x'");
        out.flush();


        System.out.println("\r\nNew connection from " + clientAddress);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        while ( (data = in.readLine()) != null ) { //read host inputs
            if ((data.equals("x"))){  // if x is inputted close connection
                try {
                    out.println("Connection closing by host request...");
                    out.close();
                    in.close();
                    server.close();
                    client.close();
                    System.out.println("Server is down");
                    break;
                } catch (IOException e) {
                    System.out.println("Error al cerrar la conexión");
                }
            }
            System.out.println("\r\nMessage from " + clientAddress + ": " + data); //print to console data inputted
        }
    }

    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return this.server.getLocalPort();
    }

    public void closeConnection(){
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}