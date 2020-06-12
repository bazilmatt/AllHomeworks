package Homework10.ver2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Listener extends Thread {
    private int port;
    private DatagramSocket ds;

    public Listener(DatagramSocket dss) {
        this.ds = dss;

    }

    @Override
    public void run() {

        byte[] msg = new byte[256];

        try {
         //   DatagramSocket datagramSocket = new DatagramSocket(port);

            while (true) {
                String message;
                DatagramPacket inP = new DatagramPacket(msg, 0, InetAddress.getByName("localhost"), port);
                String msgIn = "";
                msg = new byte[256];
                inP = new DatagramPacket(msg, msg.length);
                ds.receive(inP);
                msgIn = new String(inP.getData(), 0, inP.getLength());
                System.out.println(msgIn);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}