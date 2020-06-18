package Homework10.ver2;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    public static final Integer SERVER_PORT = 7000;
    public static DatagramSocket datagramSocket;
    public static DatagramPacket inP, outP;
    public static byte[] buf;
    private static HashMap<String, Integer> users = new HashMap<>();


    //udp socket chat
    public static void main(String[] args) throws IOException {

        boolean flag = true;

        InetAddress userAddress = InetAddress.getByName("localhost");
        try {
            datagramSocket = new DatagramSocket(SERVER_PORT);
        } catch (Exception e) {
            System.err.println("connection Failed");
            e.printStackTrace();
        }
        buf = "textmessage".getBytes();
        outP = new DatagramPacket(buf, 0, userAddress, 1234);
        inP = new DatagramPacket(buf, 0, userAddress, SERVER_PORT);

        while (flag) {
            receiveMessage();
        }
    }


    private static void sendMessage(Integer port, String msg) {
        try {
            outP = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName("localhost"), port);
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


            int userPort = inP.getPort();
            System.out.println("userport " + userPort);

            String nickName;
            msgIn = new String(inP.getData(), 0, inP.getLength());
            String textMessage = msgIn.substring(msgIn.indexOf(">") + 1);

            if (msgIn.startsWith("!")) {
                nickName = msgIn.substring(msgIn.indexOf("!") + 1);
                users.put(nickName, userPort);
            } else if (textMessage.startsWith("@")) {
                String personalUser = msgIn.substring(msgIn.indexOf("@") + 1, msgIn.indexOf(" "));
                String message = msgIn.substring(msgIn.indexOf(" ") + 1);
                try {
                    if (!users.containsKey(personalUser)) throw new Exception();
                    sendMessage(users.get(personalUser), msgIn.substring(0, msgIn.indexOf(">") + 1) + message);
                } catch (Exception e) {
                    System.err.println("Нет такого пользователя в мапе");
                    e.printStackTrace();
                }
            } else {
                String finalMsgIn = msgIn;
                users.forEach((key, value) -> sendMessage(value, finalMsgIn));
                //sendMessage(users.get("ggg"), "Ответ");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}