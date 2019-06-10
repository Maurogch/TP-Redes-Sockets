package edu.utn;

import edu.utn.socket.MyClientSocket;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MyClientSocket client;
        Scanner sc = new Scanner (System.in);
        String ip;
        int port;

        try {
            System.out.print("Input IP to connect: ");
            ip = sc.nextLine();
            System.out.print("Input port number: ");
            port = sc.nextInt();

            if(port < 1 && port > 65535){
                throw new IllegalArgumentException();
            }

            client = new MyClientSocket(InetAddress.getByName(ip),port);

            System.out.println("Connected to Server: " + client.getSocket().getInetAddress());
            client.start();
        } catch (UnknownHostException e){
            System.out.println("Can't resolve host name");
        }catch (InputMismatchException e){
            System.out.println("Port syntax wrong, must be an integer");
        }catch (IllegalArgumentException e){
            System.out.println("Invalid port number, out of range");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
