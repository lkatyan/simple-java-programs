import java.io.*;

/*
    Задание:

    Считать массив объектов типа String:
    To;be:or*not;to:be*that;is:the*question
    I;am:the*student;of:economical*department
    My;name:is*John
    Hello;world

    Использовать методы indexOf() и substring() для извлечения
    из исходного массива строк последовательности подстрок,
    которые разделены символами ";", ":", "*".

    Исходная информация должна храниться в первом файле,
    программа её считывает и осуществляет с ней преобразования,
    преобразованная информация записывается во второй файл,
    в третий файл программа должна записать исходную и
    преобразованную информацию.
*/

public class Strings_in_file {
    public static void main(String[] args) {
        // Меню
        while (true) {
            try {
                String choice;
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("1. Work");
                System.out.println("2. Exit");
                choice=in.readLine();

                if (choice.compareTo("1")==0) {
                    String filename1="C:\\Users\\Catherine\\Downloads\\firstfile.txt",
                            filename2="C:\\Users\\Catherine\\Downloads\\secondfile.txt",
                            filename3="C:\\Users\\Catherine\\Downloads\\thirdfile.txt";

                    // Подсчет количества строк в первом файле для выделения памяти под исходные данные
                    File file = new File(filename1);
                    LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
                    lineNumberReader.skip(Long.MAX_VALUE);
                    int lines = lineNumberReader.getLineNumber();
                    lineNumberReader.close();
                    String[] datas = new String[lines];

                    // Чтение исходных данных из первого файла в массив строк
                    BufferedReader fileInput = new BufferedReader(new FileReader(filename1));
                    int k=0;
                    String data;
                    while ((data = fileInput.readLine()) != null) {
                        datas[k] = data;
                        k++;
                    }
                    fileInput.close();

                    // Замена разделителей на пробелы
                    char[] separators = {';', ':', '*'};
                    for (int j = 0; j < lines; j++) {
                        for (int i = 0; i < 3; i++) {
                            datas[j] = datas[j].replace(separators[i], ' ');
                        }
                    }

                    // Подсчет количества слов для выделения памяти под преобразованные данные
                    int count = 0, indexx=0;
                    for (int j = 0; j < lines; j++) {
                        while (datas[j].indexOf(' ', indexx) != -1) {
                            count++;
                            indexx = datas[j].indexOf(' ', indexx) + 1;
                        }
                        indexx = 0;
                    }
                    String[] stringsends = new String[count + lines];

                    // Добавление подстрок исходных строк в новый массив строк
                    int index = -1; k = 0;
                    for (int j = 0; j < lines; j++) {
                        while (datas[j].indexOf(' ', indexx) != -1) {
                            indexx = datas[j].indexOf(' ', indexx) + 1;
                            stringsends[k] = datas[j].substring(++index, indexx);
                            index=indexx-1; k++;
                        }
                        stringsends[k] = datas[j].substring(++index);
                        index=-1; k++; indexx=0;
                    }

                    // Вывод полученного массива строк во второй файл
                    FileWriter fileOutput= new FileWriter(filename2);
                    for (int i = 0; i < stringsends.length; i++) {
                        fileOutput.append(stringsends[i]);
                        fileOutput.append('\n');
                    }
                    fileOutput.close();

                    // Запись исходных и преобразованных данных в третий файл
                    fileInput = new BufferedReader(new FileReader(filename1));
                    fileOutput= new FileWriter(filename3);
                    while ((data = fileInput.readLine()) != null) {
                        fileOutput.append(data);
                        fileOutput.append('\n');
                    }
                    for (int i = 0; i < stringsends.length; i++) {
                        fileOutput.append(stringsends[i]);
                        fileOutput.append('\n');
                    }
                    fileOutput.close();
                    fileInput.close();

                    System.out.println("\nWork done successfully!");
                }

                else if (choice.compareTo("2")==0) {
                    return;
                }
            }

            catch(FileNotFoundException e) {
                System.out.println("filenotfound");
                    return;
            }
            catch(IOException e) {
                System.out.println("Error1");
                return;
            }
            catch(Exception e) {
                System.out.println("Error2");
                return;
            }
        }
    }
}
