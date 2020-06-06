package Homework10.ver1;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer extends Thread{


    private DatagramSocket socket;
    private boolean flag;
    private byte[] buffer = new byte[256];

    public UdpServer() throws SocketException {
        socket = new DatagramSocket(5544);
    }

    public void run(){
        flag = true;

        while (flag){
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = datagramPacket.getAddress();
            int port = datagramPacket.getPort();
            datagramPacket = new DatagramPacket(buffer, buffer.length, address, port);
            String recived = new String(datagramPacket.getData(),0,datagramPacket.getLength());

            if (recived.equals("quit")){
                flag = false;
                continue;
            }
            try {
                socket.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

}
