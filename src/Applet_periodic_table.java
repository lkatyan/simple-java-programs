import javax.swing.*;
import java.awt.*;

/*
    Задание: разработать апплет с отрисовкой таблицы Менделеева.
*/

public class Applet_periodic_table extends JApplet {

    public void paint(Graphics g) {

        Color c0=new Color(243, 235, 176);
        g.setColor(c0);
        g.fillRect(10,10,660,470);

        Color c1=new Color(143, 224, 224);
        g.setColor(c1);
        g.fillRect(70,120,60,40);

        g.setColor(Color.PINK);
        g.fillRect(70,80,60,40);

        g.setColor(Color.YELLOW);
        g.fillRect(130,120,60,40);

        g.setColor(Color.GREEN);
        g.fillRect(190,120,60,40);

        g.setColor(Color.GRAY);
        g.fillRect(70,160,60,40);

        g.setColor(Color.BLACK);
        int x=70;
        for (int j=0; j<7; j++) {
            x+=60;
            g.drawLine(x,40,x,480);
        }
        g.drawLine(610,200,610,480);

        //line down
        g.drawLine(10,10,10,480);
        g.drawLine(40,10,40,480);
        g.drawLine(70,10,70,480);
        g.drawLine(550,80,550,480);

        //line right
        g.drawLine(10,10,670,10);
        g.drawLine(70,40,670,40);
        g.drawLine(10,80,670,80);
        g.drawLine(10,120,550,120);
        g.drawLine(10,160,550,160);
        g.drawLine(10,200,670,200);
        g.drawLine(40,240,670,240);
        g.drawLine(10,280,670,280);
        g.drawLine(40,320,670,320);
        g.drawLine(10,360,670,360);
        g.drawLine(40,400,670,400);
        g.drawLine(10,440,670,440);
        g.drawLine(10,480,670,480);

        g.setFont(new Font("Dialog",Font.BOLD,12));
        g.drawString("Г Р У П П Ы Э Л Е М Е Н Т О В", 100,30);

        x=85;
        g.drawString("I", 145,65);
        g.drawString("II", 205,65);
        g.drawString("III", 265,65);
        g.drawString("IV", 325,65);
        g.drawString("V", 385,65);
        g.drawString("VI", 445,65);
        g.drawString("VII", 505,65);

        g.drawString("I", 20,105);
        g.drawString("II", 20,145);
        g.drawString("III", 20,185);
        g.drawString("IV", 20,245);
        g.drawString("V", 20,325);
        g.drawString("VI", 20,405);
        g.drawString("VII", 20,465);

        g.drawString("1", 50,105);
        g.drawString("2", 50,145);
        g.drawString("3", 50,185);
        g.drawString("4", 50,225);
        g.drawString("5", 50,265);
        g.drawString("6", 50,305);
        g.drawString("7", 50,345);
        g.drawString("8", 50,385);
        g.drawString("9", 50,425);
        g.drawString("10", 50,465);

        x=75;
        g.drawString("(H)", x+25,95);
        g.drawString("Li       3", x,135);
        g.drawString("Na       11", x,175);
        g.drawString("K       19", x,215);
        g.drawString("29       Cu", x,255);
        g.drawString("Rb       37", x,295);
        g.drawString("47       Ag", x,335);
        g.drawString("Cs       55", x,375);
        g.drawString("79       Au", x,415);
        g.drawString("Fr       87", x,455);

        x=140;
        g.drawString("Be    4", x,135);
        g.drawString("Mg    12", x,175);
        g.drawString("Ca    20", x,215);
        g.drawString("30    Zn", x,255);
        g.drawString("Sr    38", x,295);
        g.drawString("48    Cd", x,335);
        g.drawString("Ba    56", x,375);
        g.drawString("80    Hg", x,415);
        g.drawString("Ra    88", x,455);

        x=200;
        g.drawString("B    5", x,135);
        g.drawString("Al    5", x,175);
        g.drawString("Sc    5", x,215);
        g.drawString("Ga    5", x,255);
        g.drawString("Y    5", x,295);
        g.drawString("In    5", x,335);
        g.drawString("La    5", x,375);
        g.drawString("Tl    5", x,415);
        g.drawString("Ac    5", x,455);

        x=260;
        g.drawString("C    5", x,135);
        g.drawString("Si    5", x,175);
        g.drawString("Ti    5", x,215);
        g.drawString("Ge    5", x,255);
        g.drawString("Zr    5", x,295);
        g.drawString("Sn    5", x,335);
        g.drawString("Hf    5", x,375);
        g.drawString("Pb    5", x,415);
        g.drawString("Rf    5", x,455);

        x=320;
        g.drawString("N    5", x,135);
        g.drawString("P    5", x,175);
        g.drawString("V    5", x,215);
        g.drawString("As    5", x,255);
        g.drawString("Nb    5", x,295);
        g.drawString("Sb    5", x,335);
        g.drawString("Ta    5", x,375);
        g.drawString("Bi    5", x,415);
        g.drawString("Db    5", x,455);

        x=380;
        g.drawString("O    5", x,135);
        g.drawString("S    5", x,175);
        g.drawString("Cr    5", x,215);
        g.drawString("Se    5", x,255);
        g.drawString("Mo    5", x,295);
        g.drawString("Te    5", x,335);
        g.drawString("W    5", x,375);
        g.drawString("Po    5", x,415);
        g.drawString("Sg    5", x,455);

        x=440;
        g.drawString("H    5", x,95);
        g.drawString("F    5", x,135);
        g.drawString("Cl    5", x,175);
        g.drawString("Mn    5", x,215);
        g.drawString("Br    5", x,255);
        g.drawString("Tc    5", x,295);
        g.drawString("I    5", x,335);
        g.drawString("Re    5", x,375);
        g.drawString("At    5", x,415);
        g.drawString("Bh    5", x,455);

        x=500;
        g.drawString("He    5", x,95);
        g.drawString("Ne    5", x,135);
        g.drawString("Ar    5", x,175);
        g.drawString("Fe    5", x,215);
        g.drawString("Kr    5", x,255);
        g.drawString("Ru    5", x,295);
        g.drawString("Xe    5", x,335);
        g.drawString("Os    5", x,375);
        g.drawString("Rn    5", x,415);
        g.drawString("Hs    5", x,455);

        x=560;
        g.drawString("Co    5", x,215);
        g.drawString("Rh    5", x,295);
        g.drawString("Ir    5", x,375);
        g.drawString("Mt    5", x,455);

        x=620;
        g.drawString("Ni    5", x,215);
        g.drawString("Pd    5", x,295);
        g.drawString("Pt    5", x,375);

        g.setFont(new Font("Dialog",Font.BOLD,10));

        x=80;
        g.drawString("Литий 6,9", x,155);
        g.drawString("Литий 6,9", x,195);
        g.drawString("Литий 6,9", x,235);
        g.drawString("Литий 6,9", x,275);
        g.drawString("Литий 6,9", x,315);
        g.drawString("Литий 6,9", x,355);
        g.drawString("Литий 6,9", x,395);
        g.drawString("Литий 6,9", x,435);
        g.drawString("Литий 6,9", x,475);

        x=140;
        g.drawString("Литий 6,9", x,155);
        g.drawString("Литий 6,9", x,195);
        g.drawString("Литий 6,9", x,235);
        g.drawString("Литий 6,9", x,275);
        g.drawString("Литий 6,9", x,315);
        g.drawString("Литий 6,9", x,355);
        g.drawString("Литий 6,9", x,395);
        g.drawString("Литий 6,9", x,435);
        g.drawString("Литий 6,9", x,475);

        x=200;
        g.drawString("Литий 6,9", x,155);
        g.drawString("Литий 6,9", x,195);
        g.drawString("Литий 6,9", x,235);
        g.drawString("Литий 6,9", x,275);
        g.drawString("Литий 6,9", x,315);
        g.drawString("Литий 6,9", x,355);
        g.drawString("Литий 6,9", x,395);
        g.drawString("Литий 6,9", x,435);
        g.drawString("Литий 6,9", x,475);

        x=260;
        g.drawString("Литий 6,9", x,155);
        g.drawString("Литий 6,9", x,195);
        g.drawString("Литий 6,9", x,235);
        g.drawString("Литий 6,9", x,275);
        g.drawString("Литий 6,9", x,315);
        g.drawString("Литий 6,9", x,355);
        g.drawString("Литий 6,9", x,395);
        g.drawString("Литий 6,9", x,435);
        g.drawString("Литий 6,9", x,475);

        x=320;
        g.drawString("Литий 6,9", x,155);
        g.drawString("Литий 6,9", x,195);
        g.drawString("Литий 6,9", x,235);
        g.drawString("Литий 6,9", x,275);
        g.drawString("Литий 6,9", x,315);
        g.drawString("Литий 6,9", x,355);
        g.drawString("Литий 6,9", x,395);
        g.drawString("Литий 6,9", x,435);
        g.drawString("Литий 6,9", x,475);

        x=380;
        g.drawString("Литий 6,9", x,155);
        g.drawString("Литий 6,9", x,195);
        g.drawString("Литий 6,9", x,235);
        g.drawString("Литий 6,9", x,275);
        g.drawString("Литий 6,9", x,315);
        g.drawString("Литий 6,9", x,355);
        g.drawString("Литий 6,9", x,395);
        g.drawString("Литий 6,9", x,435);
        g.drawString("Литий 6,9", x,475);

        x=440;
        g.drawString("Литий 6,9", x,115);
        g.drawString("Литий 6,9", x,155);
        g.drawString("Литий 6,9", x,195);
        g.drawString("Литий 6,9", x,235);
        g.drawString("Литий 6,9", x,275);
        g.drawString("Литий 6,9", x,315);
        g.drawString("Литий 6,9", x,355);
        g.drawString("Литий 6,9", x,395);
        g.drawString("Литий 6,9", x,435);
        g.drawString("Литий 6,9", x,475);

        x=500;
        g.drawString("Литий 6,9", x,115);
        g.drawString("Литий 6,9", x,155);
        g.drawString("Литий 6,9", x,195);
        g.drawString("Литий 6,9", x,235);
        g.drawString("Литий 6,9", x,275);
        g.drawString("Литий 6,9", x,315);
        g.drawString("Литий 6,9", x,355);
        g.drawString("Литий 6,9", x,395);
        g.drawString("Литий 6,9", x,435);
        g.drawString("Литий 6,9", x,475);

        x=560;
        g.drawString("Литий 6,9", x,235);
        g.drawString("Литий 6,9", x,315);
        g.drawString("Литий 6,9", x,395);
        g.drawString("Литий 6,9", x,475);

        x=620;
        g.drawString("Литий 6,9", x,235);
        g.drawString("Литий 6,9", x,315);
        g.drawString("Литий 6,9", x,395);

        g.drawLine(670,10,670,480);

    }
}
