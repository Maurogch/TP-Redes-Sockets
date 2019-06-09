package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadSocket extends Thread{
    private int idSocket;
    private Socket client;
    private String serverIp;
    private String serverPort;
    private String clientAddress;
    private PrintWriter out;

    public ThreadSocket(int idSocket, Socket client, String serverIp, Integer serverPort){
        this.idSocket = idSocket;
        this.client = client;
        this.serverIp = serverIp;
        this.serverPort = serverPort.toString();
        try {
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error geting outputstream to connection");
            e.printStackTrace();
        }
        clientAddress = client.getInetAddress().getHostAddress();
    }

    @Override
    public void run(){
        try {
            String data;

            out.println("Connected to " + serverIp + " port " + serverPort);
            out.println("Close connection with 'x'");
            out.flush();

            System.out.println("New connection from " + clientAddress + " ID: " + idSocket);

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while ( (data = in.readLine()) != null ) { //read host inputs
                if ((data.equals("x"))){  // if x is inputted close connection
                    out.println("Closing connection by request of client...");
                    out.close();
                    in.close();
                    client.close();
                    System.out.println("Connection with " + clientAddress + " ID: " + idSocket + " closed");
                    break;
                }
                //print to console data inputted
                System.out.println("Message from " + clientAddress + " ID: " + idSocket + ": " + data);
            }
            if(!client.isClosed()) { //if socket is not closed and it gets here (escaped the while) connection was lost
                System.out.println("Connection lost with " + clientAddress + " ID: " + idSocket);
                client.close();
            }
        } catch (IOException e) {
            System.out.println("Connection lost with " + clientAddress + " ID: " + idSocket);
            try {
                client.close();
            } catch (IOException ex) {
                System.out.println("Error closing connection with " + clientAddress + " ID: " + idSocket);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getIdSocket() {
        return idSocket;
    }

    public Socket getClient() {
        return client;
    }

    public PrintWriter getOut() {
        return out;
    }
}
