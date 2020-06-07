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


        //todo
        // 1) Сохранять клиентов в мапу
        // 2) на клиенте поднимать такой же мини-receive сервер для получения сообщений
        // 3) когда один клиент отсылает - сервер рассылает всем
        // 4) приватные сообщения - регистрация нового пользователя
        // 5) @nick текст - для личного сообщения
        //



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

        while (true) {
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
            outP = new DatagramPacket(msgIn.getBytes(), msgIn.length(), clientAddress, Server.USER_PORT);
            datagramSocket.send(outP);
            System.out.println(msgIn);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}