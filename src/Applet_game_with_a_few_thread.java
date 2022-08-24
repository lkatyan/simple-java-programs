import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

/*
    Задание: Составить программу вывода в верхней части экрана дисплея движущегося
        слева направо парусника с постоянной скоростью. Ее значение всякий раз задается
        генератором случайных чисел. В нижней части экрана дисплея расположена пушка.
        При нажатии кнопки происходит выстрел торпедой с постоянной скоростью.
        При попадании торпеды в парусник смоделировать взрыв парусника и его исчезновение.
        При промахе парусник достигает правой границы экрана дисплея и начинает движение
        сначала с новой постоянной скоростью.
    Реализация с помощью нескольких потоков отрисовки.
*/

public class Applet_game_with_a_few_thread extends Applet implements Runnable {
    Thread T;
    ShapeParus shape_parus=null;
    ShapeTorpeda shape_torpeda=null;
    int speed=2; // скорость отрисовки
    JButton b=new JButton();
    boolean isButtonPressed=false;

    public void run() {
        while (true) {
            repaint();
            try {
                T.sleep(50); // (int) (Math.random()*(600+1)) - 200; // скорость движения
            } catch (Exception exception){}
        }
    }

    public void init() {
        T=new Thread(this);
        T.start();
        shape_parus=new ShapeParus();
        shape_torpeda=new ShapeTorpeda();
    }

    public void paint(Graphics g) {
        shape_parus.T2.run();
        shape_torpeda.T3.run();
        b.setBounds(170, 230, 50, 50);
        add(b);
        g.setColor(Color.BLACK);
        if (isButtonPressed & ((shape_parus.x_parus<=shape_torpeda.x_torpeda & shape_parus.x_parus+50>=shape_torpeda.x_torpeda) |
                (shape_parus.x_parus<=shape_torpeda.x_torpeda+10 & shape_parus.x_parus+50>=shape_torpeda.x_torpeda+10)) &
                ((shape_parus.y_parus<= shape_torpeda.y_torpeda & shape_parus.y_parus+50>=shape_torpeda.y_torpeda) |
                (shape_parus.y_parus<=shape_torpeda.y_torpeda+10 & shape_parus.y_parus+50>=shape_torpeda.y_torpeda+10)))
        {
            g.setColor(Color.RED); // draw vzruv
            int x_vzruv=shape_parus.x_parus-30, y_vzruv=shape_parus.y_parus-80;
            int x[]={x_vzruv+10, x_vzruv+35, x_vzruv+50, x_vzruv+65, x_vzruv+90, x_vzruv+70, x_vzruv+80, x_vzruv+50, x_vzruv+20, x_vzruv+30, x_vzruv+10 };
            int y[]={ y_vzruv+100, y_vzruv+95, y_vzruv+70, y_vzruv+95, y_vzruv+100, y_vzruv+125, y_vzruv+150, y_vzruv+135, y_vzruv+150, y_vzruv+125, y_vzruv+100 };
            g.fillPolygon(x,y,11);
            //g.fillRect(shape_parus.x_parus-25, shape_parus.y_parus-25, 100,100);
            shape_parus.x_parus=30; shape_torpeda.y_torpeda=210; isButtonPressed=false;
        }
        g.setColor(Color.BLACK); // draw parus
        g.fillArc(shape_parus.x_parus-15, shape_parus.y_parus, 80, 50, 180,180);
        g.drawLine(shape_parus.x_parus + 50, shape_parus.y_parus, shape_parus.x_parus + 25, shape_parus.y_parus - 25);
        g.drawLine(shape_parus.x_parus+25, shape_parus.y_parus, shape_parus.x_parus+50, shape_parus.y_parus);
        g.drawLine(shape_parus.x_parus+25, shape_parus.y_parus-25, shape_parus.x_parus + 25, shape_parus.y_parus + 25);
        g.setColor(Color.PINK); // draw pushka and torpeda
        g.fillRect(170, 230, 50, 50);
        g.fillRect(190, 205, 10,25);

        if (isButtonPressed)
            g.fillOval(shape_torpeda.x_torpeda, shape_torpeda.y_torpeda, 10, 10);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape_torpeda.y_torpeda=210;
                isButtonPressed=true;
            }
        });
    }

    class ShapeParus implements  Runnable {
        Thread T2;
        int x_parus, y_parus;
        public ShapeParus() {
            T2 = new Thread(this);
            x_parus=30;
            y_parus=30;
        }
        public void run() {
            x_parus += 5;
            if (x_parus>300) x_parus=30;
            try {
                T2.sleep(speed);
            } catch (Exception exception) {
            }
        }
    }
    class ShapeTorpeda implements  Runnable {
        Thread T3;
        int x_torpeda, y_torpeda;
        public ShapeTorpeda() {
            T3 = new Thread(this);
            x_torpeda=190;
            y_torpeda=210;
        }
        public void run() {
            y_torpeda -= 10;
            if (y_torpeda<20) {
                y_torpeda=210;
                isButtonPressed=false;
            }
            try {
                T3.sleep(speed);
            } catch (Exception exception) {
            }
        }
    }
}