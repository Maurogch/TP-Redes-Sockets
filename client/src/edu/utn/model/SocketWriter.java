package edu.utn.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SocketWriter extends Thread {
    private PrintWriter out;
    private boolean error;
    private boolean closed;

    public SocketWriter(PrintWriter out){
        this.out = out;
        this.error = false;
        this.closed = false;
    }

    @Override
    public void run(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            String input;
            while ((input = br.readLine()) != null){
                sb.append(input);

                out.println(input);
                out.flush();

                if(sb.toString().equals("x")){
                    closed = true;
                }

                sb.setLength(0);
            }
        }catch (IOException e) {
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
