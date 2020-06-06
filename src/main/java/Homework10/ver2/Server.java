package Homework10.ver2;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    public static final Integer SERVER_PORT = 7777;
    public static final Integer USER_PORT = 5555;
    public static DatagramSocket datagramSocket;
    public static DatagramPacket inP, outP;
    public static byte[] buf;
    private static HashMap<String, User> users;


    //udp socket chat
    public static void main(String[] args) throws IOException {

        Listener listenerThread = new Listener(SERVER_PORT);
        listenerThread.start();
        InetAddress userAddress = InetAddress.getByName("localhost");
        try {
            datagramSocket = new DatagramSocket(SERVER_PORT);
        } catch (Exception e) {
            System.err.println("connection Failed");
            e.printStackTrace();
        }
        buf = "textmessage".getBytes();
        outP = new DatagramPacket(buf,0, userAddress, USER_PORT);
        inP = new DatagramPacket(buf,0,userAddress,SERVER_PORT);

        Scanner scanner = new Scanner(System.in);
        String data;

        while ((data = scanner.nextLine()).isEmpty()) {
            data = scanner.nextLine();
            sendMessage(userAddress, data);
            datagramSocket.receive(inP);
            String text = Arrays.toString(inP.getData());
            System.out.println(text);
            System.out.println("sd");
            receiveMessage();
        }
    }

    private static void sendMessage(InetAddress addr, String msg) {
        try {
            outP = new DatagramPacket(msg.getBytes(), msg.length(), addr, Server.USER_PORT);
            datagramSocket.send(outP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receiveMessage() {
        try {
            String msgIn = "";
            buf = new byte[256];
            inP = new DatagramPacket(buf, buf.length);
            datagramSocket.receive(inP);
            InetAddress clientAddress = inP.getAddress();
            int clientPort = inP.getPort();
            msgIn = new String(inP.getData(), 0, inP.getLength());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}