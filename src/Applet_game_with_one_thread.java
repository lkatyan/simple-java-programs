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
    Реализация с помощью одного потока отрисовки.
*/

public class Applet_game_with_one_thread extends Applet implements Runnable {
    Thread T;
    int level=0, levelpushka=1;
    int speed=50; //(int) (Math.random()*(600+1)) - 200;
    int x_parus=30, y_parus=30;
    int x_torpeda=190, y_torpeda=210;
    int reset=1, dvigh=0;
    int x_vzruv, y_vzruv;
    JButton b=new JButton("Click Here");

    public void run() {
        while (true) {
            if (dvigh==1) {
                if (((x_parus<=x_torpeda & x_parus+50>=x_torpeda) |
                        (x_parus<=x_torpeda+10 & x_parus+50>=x_torpeda+10)) &
                        ((y_parus<= y_torpeda & y_parus+50>=y_torpeda) |
                        (y_parus<=y_torpeda+10 & y_parus+50>=y_torpeda+10)))
                {
                    reset=0;
                    x_vzruv=x_parus; y_vzruv=y_parus;
                    x_parus=30; y_parus=30;
                    level=0;
                    levelpushka=0;
                }
            }
            if (levelpushka!=0) {
                levelpushka--;
                y_torpeda-=10;
            }
            else {
                x_torpeda=190;
                y_torpeda=210;
            }
            level++;
            x_parus+=5;
            repaint();
            try {
                T.sleep(speed);
            } catch (Exception exception){}
            if (level==50) {
                x_parus=30;
                y_parus=30;
                level=0;
            }
        }
    }

    public void init() {
        //this.setBackground(Color.white);
        T=new Thread(this);
        T.start();
    }

    public void paint(Graphics g) {
        b.setBounds(170, 230, 50, 50);
        add(b);
        if (reset == 0) {
            g.setColor(Color.RED);
            x_vzruv-=30; y_vzruv-=80;
            int x[]={x_vzruv+10, x_vzruv+35, x_vzruv+50, x_vzruv+65, x_vzruv+90, x_vzruv+70, x_vzruv+80, x_vzruv+50, x_vzruv+20, x_vzruv+30, x_vzruv+10 };
            int y[]={ y_vzruv+100, y_vzruv+95, y_vzruv+70, y_vzruv+95, y_vzruv+100, y_vzruv+125, y_vzruv+150, y_vzruv+135, y_vzruv+150, y_vzruv+125, y_vzruv+100 };
            g.fillPolygon(x,y,11);
            //g.fillRect(x_vzruv - 25, y_vzruv - 25, 100, 100);
            g.fillOval(x_torpeda, y_torpeda, 10, 10);
            reset = 1;
            dvigh = 0;
        }
        g.setColor(Color.BLACK); // draw parus
        g.fillArc(x_parus-15, y_parus, 80, 50, 180,180);
        g.drawLine(x_parus + 50, y_parus, x_parus + 25, y_parus - 25);
        g.drawLine(x_parus+25, y_parus, x_parus+50, y_parus);
        g.drawLine(x_parus+25, y_parus-25, x_parus + 25, y_parus + 25);
        g.setColor(Color.PINK); // draw pushka and torpeda
        g.fillRect(170, 230, 50, 50);
        g.fillRect(190, 205, 10,25);
        g.fillOval(x_torpeda, y_torpeda, 10, 10);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dvigh = 1;
                levelpushka = 20;
            }
        });
    }
}