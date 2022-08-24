import java.io.*;
import java.net.*;

/*
    Задание: Разработать приложение-определитель матрицы.
	Возможности сервера: вычисляет определитель полученной от клиента
	    матрицы и возвращает результат клиенту.
*/

public class TCP_server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientAccepted = null;
        ObjectInputStream sois= null;
        ObjectOutputStream soos = null;
        int lenght, opredelitel;

        try {
            System.out.println("server starting ");
            serverSocket = new ServerSocket(2525);
            clientAccepted = serverSocket.accept();
            System.out.println("connection established ");

            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            String clientMessageRecieved = (String) sois.readObject();
            lenght= Integer.parseInt(clientMessageRecieved);

            int[][] matrix = new int [lenght][lenght];

            for(int i = 0; i <matrix.length ; i++)
                matrix[i]= new int [lenght];

            for(int i = 0;i<lenght;i++)
                for(int j = 0; j < lenght ; j++)
                {
                    clientMessageRecieved = (String)sois.readObject();
                    matrix[i][j] = Integer.parseInt(clientMessageRecieved);
                }

            TCP_server n=new TCP_server();
            opredelitel=n.det(matrix);
            soos.writeObject(opredelitel);
        }
        catch (Exception e) {
        }
        finally {
            try {
                sois.close();
                soos.close();
                clientAccepted.close();
                serverSocket.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    int det(int[][] mat) {
        if (mat.length == 1)
            return mat[0][0];
        if (mat.length == 2)
            return mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1];
        int sum = 0, sign = 1;
        int newN = mat.length - 1;
        int[][] temp = new int[newN][newN];
        for (int t = 0; t < newN; t++) {
            int q = 0;
            for (int i = 0; i < newN; i++) {
                for (int j = 0; j < newN; j++) {
                    temp[i][j] = mat[1 + i][q + j];
                }
                if (q == i)
                    q = 1;
            }
            sum += sign * mat[0][t] * det(temp);
            sign *= -1;
        }
        return sum;
    }
}