import java.net.*;
import java.io.*;

/*
    Задание: Разработать приложение на основе UDP-соединения.
    Возможности сервера: по полученным от клиента исходным
        параметрам рассчитать значение функции, передать
        клиенту рассчетное значение функции, а также сохранить
        исходные параметры и значение функции в файл.
*/

public class UDP_server {
    public final static int DEFAULT_PORT=8001;
    public void runServer() throws IOException {
        DatagramSocket serverSocket = null;
        try {

            byte[] buf = new byte[1024];
            double[] arr =  {0, 0, 0};
            double res=0;
            serverSocket = new DatagramSocket(DEFAULT_PORT);
            System.out.println("UPDServer: Started on " + serverSocket.getLocalAddress() + ":" + serverSocket.getLocalPort());

            DatagramPacket inputPacket = new DatagramPacket(buf, buf.length);
            for (int i = 0; i < 3; i++) {
                buf=new byte[1024];
                inputPacket = new DatagramPacket(buf, buf.length);
                serverSocket.receive(inputPacket);
                String receivedData = new String(inputPacket.getData());
                arr[i]=Double.parseDouble(receivedData);
            }
            //res=Math.sqrt(10*(Math.sqrt(arr[0])+Math.pow(arr[0],arr[1]*arr[2]))*(Math.sin(arr[2])*Math.sin(arr[2])-Math.abs(arr[0]+arr[1])))*Math.exp(arr[2]);
            res=arr[0]+arr[1]+arr[2];

            try(FileWriter writer = new FileWriter("C:\\Users\\Catherine\\Downloads\\ffile.txt", false))
            {
                for (int i=0; i<3; i++){
                    writer.write(String.valueOf(arr[i]));
                    writer.write('\n');
                }
                writer.write(String.valueOf(res));
                writer.flush();
            }
            catch(IOException ex) {
                System.out.println(ex.getMessage());
            }
            String result = String.valueOf(res);
            int count = result.length();
            String strres=String.valueOf(count);
            buf=strres.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, inputPacket.getAddress(), inputPacket.getPort());
            serverSocket.send(sendPacket);
            char charres=' ';
            for (int i=0; i<result.length(); i++) {
                charres = result.charAt(i);
                buf[0] = (byte) charres;
                sendPacket = new DatagramPacket(buf, buf.length, inputPacket.getAddress(), inputPacket.getPort());
                serverSocket.send(sendPacket);
            }
        }
        finally {
            if (serverSocket!=null) {
                serverSocket.close();
            }
        }
    }
    public static void main(String[] args) {
        try {
            UDP_server udpSvr = new UDP_server();
            udpSvr.runServer();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
