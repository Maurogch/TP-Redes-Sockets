package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadSocket extends Thread{
    private Socket client;
    private String serverIp;
    private String serverPort;

    public ThreadSocket(Socket client, String serverIp, Integer serverPort){
        this.client = client;
        this.serverIp = serverIp;
        this.serverPort = serverPort.toString();
    }

    @Override
    public void run(){
        try {
            String data;
            Scanner sc = new Scanner (System.in);
            String clientAddress = client.getInetAddress().getHostAddress();

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println("Conected to " + serverIp + " port " + serverPort);
            out.println("Close connection with 'x'");
            out.flush();

            System.out.println("New connection from " + clientAddress);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            while ( (data = in.readLine()) != null ) { //read host inputs
                if ((data.equals("x"))){  // if x is inputted close connection
                    try {
                        out.println("Cerrando la conexión por pedido del cliente...");
                        out.close();
                        in.close();
                        client.close();
                        System.out.println("Connection with " + clientAddress + " closed");
                        break;
                    } catch (IOException e) {
                        System.out.println("Error al cerrar la conexión");
                    }
                }
                System.out.println("Message from " + clientAddress + ": " + data); //print to console data inputted
                System.out.print("Escriba respuesta: ");
                out.println("(Esperando respuesta del servidor)");
                out.println(sc.nextLine());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
