import java.net.*;
import java.io.*;

/*
    Задание: Разработать приложение на основе UDP-соединения.
    Возможности клиента: передать серверу исходные параметры
        (вводятся с клавиатуры) для расчета значения функции,
        а также получить расчетное значение функции.
*/

public class UDP_client {
    public void runClient() throws  IOException {
        DatagramSocket clientSocket = null;
        try {
            byte[] buf = new byte[1024];
            double num;
            clientSocket = new DatagramSocket();
            System.out.println("UDPClient: Started");

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String sentence;
            System.out.println("Enter x; y; z: ");
            for (int i = 0; i < 3; i++) {
                sentence = stdin.readLine();
                buf = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("127.0.0.1"), 8001);
                clientSocket.send(sendPacket);
            }

            DatagramPacket receivingPacket = new DatagramPacket(buf, buf.length);

            clientSocket.receive(receivingPacket);

            String receivedData = new String(receivingPacket.getData());
            int count = Integer.parseInt(receivedData);
            for (int i=0; i<count; i++) {
                receivingPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(receivingPacket);
                receivedData = new String(receivingPacket.getData());
                System.out.print(receivedData);
            }

            System.out.println();
            System.out.println("UDPClient: Ended");
        }
        finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
        }
    }
    public static void main(String[] args) {
        try {
            UDP_client client = new UDP_client();
            client.runClient();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
