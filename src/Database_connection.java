import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

/*
    Задание: Разработать подсистему учета и регистрации расхода стройматериалов.
             Использовать классы Checkbox, Choice, диалог фильтрации.
    Реализовано подключение к локальной базе данных mysql с помощью jdbc.
*/

public class Database_connection extends Frame {
    Choice choice = new Choice();
    Choice choice2 = new Choice();
    Choice choice3 = new Choice();
    Choice choice4 = new Choice();

    Button btn_add = new Button("add");
    Button btn_update = new Button("update");
    Button btn_clear = new Button("clear");
    Button btn_delete = new Button("delete");
    Button btn_redact = new Button ("redact");
    Button btn_sort_info = new Button("sort");
    Button btn_find_info = new Button("find");
    Button btn_save_on_file = new Button("save");

    List list = new List(10, true);
    List list1 = new List();

    int id_of_checkbox;

    public Database_connection() {
        setLayout(null);
        setSize(680, 450);
        setTitle("Frame");
        setBackground(Color.WHITE);

        choice.addItem("Доска");
        choice.addItem("Стекло");
        choice.addItem("Молоток");
        choice.setBounds(20,40,120,50);
        add(choice);

        choice2.addItem("1 шт.");
        choice2.addItem("2 шт.");
        choice2.addItem("3 шт.");
        choice2.setBounds(140,40,120,50);
        add(choice2);

        choice3.addItem("Новые");
        choice3.addItem("Поврежденные");
        choice3.addItem("Возврат покупателя");
        choice3.setBounds(260,40,120,50);
        add(choice3);

        choice4.addItem("2000-05-01");
        choice4.addItem("2000-10-01");
        choice4.addItem("2000-12-01");
        choice4.setBounds(380,40,120,50);
        add(choice4);

        add(btn_add);
        btn_add.setBounds(150, 350, 80, 25);
        add(btn_update);
        btn_update.setBounds(230, 350, 80, 25);
        add(btn_clear);
        btn_clear.setBounds(310, 350, 80, 25);
        add(btn_delete);
        btn_delete.setBounds(390, 350, 80, 25);
        add(btn_redact);
        btn_redact.setBounds(150,375,80,25);
        add(btn_sort_info);
        btn_sort_info.setBounds(230,375,80,25);
        add(btn_find_info);
        btn_find_info.setBounds(310,375,80,25);
        add(btn_save_on_file);
        btn_save_on_file.setBounds(390,375,80,25);

        add(list);
        list.setBackground(Color.WHITE);
        list.setBounds(20, 100, 300, 216);

        add(list1);
        list1.setBackground(Color.WHITE);
        list1.setBounds(350, 100, 300, 216);

        addWindowListener(new WindowClose());
    }

    static class WindowClose extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }

    public static final String USER_NAME = "root";
    public static final String PASSWORD ="";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static Statement statement;
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    static {
        try {
            statement = connection.createStatement();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Database_connection main = new Database_connection();
        main.setVisible(true);

        Class.forName("com.mysql.cj.jdbc.Driver");

        ResultSet resultSet = statement.executeQuery("SELECT * FROM test");
        while (resultSet.next()) {
            System.out.println(
                    resultSet.getString(1) + " " +
                    resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " +
                    resultSet.getString(4) + " " +
                    resultSet.getString(5)
            );
        }
    }

    public void updates() {
        try {
            list.removeAll();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM test");
            while (resultSet.next()) {
                list.add(
                        resultSet.getString(1) + " " +
                                resultSet.getString(2) + " " +
                                resultSet.getString(3) + " " +
                                resultSet.getString(4) + " " +
                                resultSet.getString(5)
                );
            }
            list1.removeAll();
            System.out.println("updates");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean action(Event evt, Object obj) {
        Button btn;
        String one = choice.getSelectedItem();
        String two = choice2.getSelectedItem();
        String three = choice3.getSelectedItem();
        String four = choice4.getSelectedItem();

        if (evt.target instanceof Button) {
            btn = (Button)evt.target;
            String sTextLabel = btn.getLabel();

            switch (sTextLabel) {
                case ("add"):
                    try {
                        statement.executeUpdate("INSERT INTO test (name1, name2, name3, name4) " +
                                "value ('"+one+"','"+two+"','"+three+"','"+four+"')");
                        updates();
                        System.out.println("adds");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ("update"):
                    updates();
                    System.out.println("updates");
                    break;
                case ("clear"):
                    try {
                        list.removeAll();
                        statement.executeUpdate("DROP TABLE test");
                        statement.executeUpdate("CREATE TABLE test (" +
                                "id int auto_increment primary key," +
                                "name1 varchar(50) not null," +
                                "name2 varchar (50) not null," +
                                "name3 varchar(50) not null," +
                                "name4 date not null)"
                        );
                        updates();
                        System.out.println("clears");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ("delete"):
                    try {
                        String str_for_delete = list.getSelectedItem();
                        String str_prom="";
                        int id=1;

                        for (char ch : str_for_delete.toCharArray()) {
                            if (ch==' ') {
                                        id=Integer.parseInt(str_prom);
                                        System.out.println(id);
                                        break;
                            } else {
                                str_prom=str_prom + "" + ch;
                            }
                        }
                        statement.executeUpdate("DELETE FROM test WHERE id='"+id+"'" );
                        updates();
                        System.out.println("deletes");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ("redact"):
                    try {
                        String str_for_delete = list.getSelectedItem();
                        String str_prom="";
                        int c=1, id=1;

                        for (char ch : str_for_delete.toCharArray()) {
                            if (ch==' ' & c==1) {
                                id=Integer.parseInt(str_prom);
                                System.out.println(id);
                                c++;
                            } else {
                                str_prom=str_prom+""+ch;
                            }
                        }
                        statement.executeUpdate("UPDATE test " +
                                " SET name1='"+one+"', " +
                                " name2='"+two+"', " +
                                " name3='"+three+"', " +
                                " name4='"+four+"' " +
                                " WHERE id='"+id+"'"
                        );
                        updates();
                        System.out.println("redacts");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ("sort"):
                    DemoDialog d=new DemoDialog(this,"Dialog window", true);
                    String name_on_sort="";
                    id_of_checkbox=d.get_id_of_checkbox();

                    if (id_of_checkbox==1) name_on_sort="id";
                    else if (id_of_checkbox==2) name_on_sort="name1";
                    else if (id_of_checkbox==3) name_on_sort="name2";
                    else if (id_of_checkbox==4) name_on_sort="name3";
                    else if (id_of_checkbox==5) name_on_sort="name4";

                    try {
                        list1.removeAll();
                        System.out.println(name_on_sort);
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM test " +
                                " ORDER BY "+name_on_sort+" ");
                        while (resultSet.next()) {
                            list1.add(
                                    resultSet.getString(1) + " " +
                                    resultSet.getString(2) + " " +
                                    resultSet.getString(3) + " " +
                                    resultSet.getString(4) + " " +
                                    resultSet.getString(5)
                            );
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("sorts");
                    break;
                case ("find"):
                    DemoDialog2 d2=new DemoDialog2(this,"Dialog window", true);
                    ArrayList<String> strings_for_find = d2.get_strings_for_find();

                    try {
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM test " +
                                "WHERE " +
                                //" id = '"+strings_for_find.get(0)+"' AND " +
                                "name1 = '"+strings_for_find.get(1)+"'" //AND " +
                                //"name2 = '"+strings_for_find.get(2)+"' AND " +
                                //"name3 = '"+strings_for_find.get(3)+"' AND " +
                                //"name4 = '"+strings_for_find.get(4)+"' AND "
                        );
                        while (resultSet.next()) {
                            list1.add(
                                    resultSet.getString(1) + " " +
                                            resultSet.getString(2) + " " +
                                            resultSet.getString(3) + " " +
                                            resultSet.getString(4) + " " +
                                            resultSet.getString(5)
                            );
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("finds");
                    break;
                case ("save"):
                    ArrayList<String> bd = new ArrayList<>();

                    try {
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM test");
                        while (resultSet.next()) {
                            bd.add(
                                    resultSet.getString(1) + " " +
                                    resultSet.getString(2) + " " +
                                    resultSet.getString(3) + " " +
                                    resultSet.getString(4) + " " +
                                    resultSet.getString(5)
                            );
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    BufferedWriter out;

                    try {
                        File file = new File("C://Users/Catherine/Downloads/bd.txt");
                        out = new BufferedWriter(new FileWriter(file, false));
                        for (String s : bd) {
                            out.write(s);
                            out.newLine();
                        }
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("saves");
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }
}

class DemoDialog extends Dialog implements ActionListener {
    public int id_checkbox;

    CheckboxGroup check_group=new CheckboxGroup();
    Checkbox ch1 = new Checkbox("Номер", check_group, false);
    Checkbox ch2 = new Checkbox("Наименование", check_group, false);
    Checkbox ch3 = new Checkbox("Количество", check_group, false);
    Checkbox ch4 = new Checkbox("Состояние", check_group, false);
    Checkbox ch5 = new Checkbox("Дата", check_group, false);

    Button btn=new Button("Ok");

    public DemoDialog(Frame ff, String title, boolean b) {
        super(ff,title,b);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(ch1); add(ch2); add(ch3); add(ch4); add(ch5);
        setSize(300, 200);
        add(btn);
        btn.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (check_group.getSelectedCheckbox()==ch1) id_checkbox=1;
        else if (check_group.getSelectedCheckbox()==ch2) id_checkbox=2;
        else if (check_group.getSelectedCheckbox()==ch3) id_checkbox=3;
        else if (check_group.getSelectedCheckbox()==ch4) id_checkbox=4;
        else if (check_group.getSelectedCheckbox()==ch5) id_checkbox=5;
        this.dispose();
    }

    public int get_id_of_checkbox() {
        return id_checkbox;
    }
}

class DemoDialog2 extends Dialog implements ActionListener {
    ArrayList<String> str_for_find = new ArrayList<>();

    TextField txt1 = new TextField("",25);
    TextField txt2 = new TextField("",25);
    TextField txt3 = new TextField("",25);
    TextField txt4 = new TextField("",25);
    TextField txt5 = new TextField("",25);

    Button btn=new Button("Ok");

    public DemoDialog2(Frame ff, String title, boolean b) {
        super(ff,title,b);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel("Номер: ")); add(txt1);
        add (new JLabel("Наименование: ")); add(txt2);
        add (new JLabel("Количество: ")); add(txt3);
        add (new JLabel("Состояние: ")); add(txt4);
        add (new JLabel("Дата(гг-мм-дд): ")); add(txt5);
        setSize(350, 220);
        add(btn);
        btn.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        str_for_find.add(txt1.getText());
        str_for_find.add(txt2.getText());
        str_for_find.add(txt3.getText());
        str_for_find.add(txt4.getText());
        str_for_find.add(txt5.getText());
        this.dispose();
    }

    public ArrayList<String> get_strings_for_find() {
        return str_for_find;
    }
}
