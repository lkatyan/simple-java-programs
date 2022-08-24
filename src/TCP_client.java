import java.io.*;
import java.net.*;

/*
    Задание: Разработать приложение-определитель матрицы.
	Возможности клиента: вводится исходная матрица произвольного порядка и
	    передается серверу, ответ сервера выводится в консоль.
*/

public class TCP_client {
    public static void main(String[] args) {
        int length;
        try {
            System.out.println("server connecting ");
            Socket clientSocket = new Socket("127.0.0.1", 2525);
            System.out.println("connection established ");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream cois = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println("Enter lenght: ");
            String clientMessage = stdin.readLine();
            coos.writeObject(clientMessage);
            length = Integer.parseInt(clientMessage);

            System.out.println("Enter matrix: ");
            for (int i = 0; i < length * length; i++) {
                clientMessage = stdin.readLine();
                coos.writeObject(clientMessage);
            }
            System.out.print(cois.readObject());

            coos.close();
            cois.close();
            clientSocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
