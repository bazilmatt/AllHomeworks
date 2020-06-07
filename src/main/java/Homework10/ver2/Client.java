package Homework10.ver2;


import javax.xml.crypto.Data;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

public class Client extends Thread {
    public static Integer CLIENT_PORT = 5555;
    public static Integer SERVER_PORT = 7777;

    public static void main(String[] args) throws IOException {
        Listener listenerThread = new Listener(CLIENT_PORT);
        listenerThread.start();

        byte[] data = "hello".getBytes();
        String line = "";

        try {
            InetAddress addr = InetAddress.getByName("localhost");
            DatagramPacket incomingPack = new DatagramPacket(data, data.length);

            DatagramSocket ds = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);

            while (!(line = scanner.nextLine()).isEmpty()) {
                data = line.getBytes();
                DatagramPacket outcomingPack = new DatagramPacket(data, data.length, addr, SERVER_PORT);
                ds.send(outcomingPack);
            }
//            ds.receive(incomingPack);
            ds.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
//        try (Socket socket = new Socket("127.0.0.1", Server.SERVER_PORT);
//             BufferedWriter bufferedWriter = new BufferedWriter(
//                     new OutputStreamWriter(socket.getOutputStream()))) {
//            Scanner scanner = new Scanner(System.in);
//            String message;
//            while (!(message = scanner.nextLine()).isEmpty()) {
//                bufferedWriter.write(message);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
}
