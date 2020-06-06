package Homework10.ver2;


public class Server2 {


//
//        Listener listenerThread = new Listener(SERVER_PORT);
////        listenerThread.start();
////
//        try {
//            byte[] data = "hello".getBytes();
//            InetAddress addr = InetAddress.getByName("127.0.0.1");
//          //  DatagramPacket outpack = new DatagramPacket(data, data.length, addr, 4998);
//          //  DatagramPacket incomingPack = new DatagramPacket(data, data.length);
//
//            DatagramSocket ds = new DatagramSocket();
//            Scanner scanner = new Scanner(System.in);
//            while (!(scanner.nextLine().getBytes().equals(null))) {
//                data = scanner.nextLine().getBytes();
//                outpack.setData(data);
//                ds.send(outpack);
//                ds.receive(incomingPack);
//                String text = incomingPack.getData().toString();
//                System.out.println(text);
//            }
////                bufferedWriter.write(message);
////                bufferedWriter.newLine();
////                bufferedWriter.flush();
////            }
//            //ds.send(pack);
//           // ds.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try (Socket socket = new Socket("127.0.0.1", Client.CLIENT_PORT);
//             BufferedWriter bufferedWriter =
//                     new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
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
