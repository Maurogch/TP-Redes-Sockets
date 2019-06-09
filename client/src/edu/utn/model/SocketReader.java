package edu.utn.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SocketReader extends Thread {
    private InputStreamReader inputStreamReader;
    private String hostAddress;
    private boolean error;
    private boolean closed;

    public SocketReader(InputStreamReader inputStreamReader, String hostAddress){
        this.inputStreamReader = inputStreamReader;
        this.hostAddress = hostAddress;
        this.error = false;
        this.closed = false;
    }

    @Override
    public void run(){
        try {
            String data = null;
            BufferedReader in = new BufferedReader(inputStreamReader);
            while ( (data = in.readLine()) != null ) { //read host inputs
                System.out.println("Message from " + hostAddress + ": " + data);
                if(data.equals("Closing connection by server request...")){
                    closed = true;
                }
            }
        } catch (IOException e) {
            error = true;
        }
    }

    public boolean isError() {
        return error;
    }

    public boolean isClosed() {
        return closed;
    }
}
