package edu.utn.socket;
import edu.utn.model.SocketReader;
import edu.utn.model.SocketWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class MyClientSocket extends Thread{
    private Socket socket;

    public MyClientSocket(InetAddress serverAddress, int serverPort){
        try {
            this.socket = new Socket();
            //constructing like this to set a connection time out more strict
            this.socket.connect(new InetSocketAddress(serverAddress, serverPort), 2000);

            //States
            System.out.println("The socket is connected: " + socket.isConnected());
            System.out.println("The socket is bounded: " + socket.isBound());
        }catch (IllegalArgumentException e){
            System.out.println("Invalid port number, out of range");
            System.exit(0);
        }catch (ConnectException e) {
            System.out.println("Connection refused for that port (port is closed)");
            System.exit(0);
        } catch (SocketTimeoutException e){
            System.out.println("Connection time out, no connection possible for that IP");
            System.exit(0);
        } catch (SocketException e){
            System.out.println("Network is unreachable or error in IP syntax");
            System.exit(0);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            String hostAddress = socket.getInetAddress().getHostAddress();
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);

            SocketReader reader = new SocketReader(in, hostAddress);
            SocketWriter writer = new SocketWriter(out);

            reader.start();
            writer.start();

            while (true){
                //if any thread has an IO error close connection and finish the program
                sleep(500);
                if(writer.isError() || reader.isError() || writer.isClosed() || reader.isClosed()){
                    System.out.println("Connection with server lost");
                    socket.close();
                    System.exit(0);
                }
            }
        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
