import java.util.Scanner;

public class Solution_equation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("x= ");
        double x = input.nextDouble();
        //double x=0.85;
        double a=4.5;
        double b=2.2;
        double c=-1.5;

        double y = (Math.sqrt(a/(1+b*x*x)))+b*(1/Math.tan(x))+Math.exp(c*x);
        System.out.println("y= "+y);
    }
}