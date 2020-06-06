package Homework10.ver2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Listener extends Thread {
    private int port;

    public Listener(int port) {
        this.port = port;

    }

    @Override
    public void run() {

        byte[] msg = new byte[256];

        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            DatagramPacket inP = new DatagramPacket(msg, 0, InetAddress.getByName("localhost"), port);
            String message;
            datagramSocket.receive(inP);
            message = inP.getData().toString();
            while (true) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}