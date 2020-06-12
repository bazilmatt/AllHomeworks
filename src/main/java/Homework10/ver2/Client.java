package Homework10.ver2;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static Integer USER_PORT;
    public static Integer SERVER_PORT = 7000;

    public static void main(String[] args) {


        byte[] data;
        String line;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите !ник");


        try {
            InetAddress addr = InetAddress.getByName("localhost");
            DatagramSocket ds = new DatagramSocket();



            System.out.println("порт отправки сообщений" + ds.getLocalPort());


            DatagramPacket outPack;
            Listener listenerThread = new Listener(ds);
            listenerThread.start();

            while (!(line = scanner.nextLine()).isEmpty()) {
                String message = line;

                outPack = new DatagramPacket(message.getBytes(), message.length(), addr, SERVER_PORT);
                ds.send(outPack);
            }

            ds.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
