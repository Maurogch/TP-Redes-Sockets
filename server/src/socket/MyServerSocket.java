package socket;

import model.ThreadSocket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
    private ServerSocket server;
    private String ipAddres;
    private int port;


    public MyServerSocket(String ipAddress, int port) throws Exception {
        if (ipAddress != null && !ipAddress.isEmpty())
            this.server = new ServerSocket(port, 50, InetAddress.getByName(ipAddress));
        else
            this.server = new ServerSocket(port, 50, InetAddress.getLocalHost());

        this.ipAddres = server.getInetAddress().getHostAddress();
        this.port = port;
    }

    public void listen() throws Exception {
        while (true) {
            Socket client = this.server.accept();
            new ThreadSocket(client, ipAddres, port).start(); //new thread for every new connection
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