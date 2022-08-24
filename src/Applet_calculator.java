import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.List;
import java.util.*;

/*
    Задание: Разработать приложение, реализующее калькулятор. Приложение должно
        иметь строку редактирования (TextField), набор кнопок 0..9, кнопки
        арифметических действий - суммирование, вычитание, деление, умножение, память.
*/

public class Applet_calculator extends Applet {
    List l;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn_plus;
    Button btn_minus;
    Button btn_mnozh;
    Button btn_deli;
    Button btn_ravno;
    Button btn_point;
    Button btn_reset;
    Button btn_memory;
    Button btn_save;
    String sTextLabel;

    JTextField tf;
    JTextField tf2;

    String res="";
    String resonvuvod="";

    HashMap<String, String> memory= new HashMap<>();

    public void init() {
        l=new List();

        setSize(500, 200);
        setBackground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 20));

        tf=new JTextField();
        tf2=new JTextField();
        btn_memory = new Button("M");
        btn_point = new Button(".");
        btn_reset = new Button("C");
        btn0 = new Button("0");
        btn1 = new Button("1");
        btn2 = new Button("2");
        btn3 = new Button("3");
        btn4 = new Button("4");
        btn5 = new Button("5");
        btn6 = new Button("6");
        btn7 = new Button("7");
        btn8 = new Button("8");
        btn9 = new Button("9");
        btn_plus = new Button("+");
        btn_minus = new Button("-");
        btn_mnozh = new Button("*");
        btn_deli = new Button("/");
        btn_ravno = new Button("=");
        btn_save = new Button("S");

        setLayout(new GridLayout(5, 5));

        add(tf);
        add(tf2);
        add(btn_memory);
        add(btn_reset);
        add(btn_point);
        add(btn_plus);
        add(btn_minus);
        add(btn_mnozh);
        add(btn_deli);
        add(btn_ravno);
        add(btn0);
        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btn7);
        add(btn8);
        add(btn9);
        add(btn_save);

        //setLayout(null);
        add(l);
        l.setBounds(350,120,200,216);
    }

    public boolean action(Event evt, Object obj) {
        Button btn;
        if (evt.target instanceof Button) {
            btn = (Button)evt.target;
            sTextLabel = btn.getLabel();
            switch (sTextLabel) {
                case ("="):
                    ScriptEngineManager mgr = new ScriptEngineManager();
                    ScriptEngine engine = mgr.getEngineByName("JavaScript");
                    String foo = res;
                    try {
                        //System.out.println(engine.eval(foo));
                        resonvuvod=engine.eval(foo).toString();
                    } catch (ScriptException e) {
                        e.printStackTrace();
                    }
                    break;
                case ("C"):
                    //if(resonvuvod!="") memory.put(res, resonvuvod);
                    res="";
                    resonvuvod="";
                    break;
                case ("M"):
                    for (Map.Entry entry: memory.entrySet()) {
                        //l.add(entry.toString());
                        res=entry.getKey().toString();
                    }
                    break;
                case ("S"):
                    memory.clear();
                    memory.put(res, resonvuvod);
                    break;
                default:
                    res+=sTextLabel;
                    l.removeAll();
                    break;

            }
            showStatus("Button (\"" + sTextLabel + "\") pressed");
            tf.setText(res);
            tf2.setText(resonvuvod);
            return true;
        }
        return false;
    }

    public void paint(Graphics g) {
        Dimension dimAppWndDimension = size();
        g.setColor(Color.black);
        g.drawRect(0, 0,
                dimAppWndDimension.width  - 1,
                dimAppWndDimension.height - 1);
    }
}