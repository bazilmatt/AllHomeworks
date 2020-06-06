package Homework10.ver1;


import java.io.IOException;
import java.net.*;

public class UdpClient extends Thread {

    private DatagramSocket socket;
    private InetAddress address;

    private byte[]buf;

    public UdpClient() throws UnknownHostException, SocketException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    @Override
    public void run() {
        try {
            sendEcho("asdasd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String sendEcho(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        return received;
    }


    public void close() {
        socket.close();
    }
}