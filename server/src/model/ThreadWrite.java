package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ThreadWrite extends Thread {
    private List<ThreadSocket> connections = new ArrayList<>();

    @Override
    public void run(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            String input;
            while ((input = br.readLine()) != null)
            {
                sb.append(input).append(" "); //String builder should be used in threads

                if(!sb.toString().contains(";")){
                    System.out.println("Missing ; in message");
                }else{
                    String[] arr = sb.toString().split(";", 2); //split id and message
                    String id = arr[0];
                    String message = "Host: " + arr[1];
                    int idNumber = Integer.parseInt(id);

                    if(idNumber + 1 > connections.size())
                        System.out.println("Invalid id number");
                    else
                        connections.get(idNumber).getOut().println(message);
                }

                sb.setLength(0); //clear stringBuilder
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.run();
        } catch (NumberFormatException e){
            System.out.println("Incorrect id format");
            this.run();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error in message composition");
            this.run();
        }
    }

    public void addConnection(ThreadSocket threadSocket){
        connections.add(threadSocket);
    }

    public int getConnectionsSize(){
        return connections.size();
    }
}
