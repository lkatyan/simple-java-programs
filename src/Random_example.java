/*
    Задание: Создать массив строк, инициализировать названиями месяцев,
        создать массив, содержащий 12 случайных десятичных значений между 0.0 и 100.0,
        вычислить и вывести среднее значение 12 значений.
*/

public class Random_example {
    public static void main(String[] args) {
        String[] months=new String[12];
        months[0]="January";
        months[1]="February";
        months[2]="March";
        months[3]="April";
        months[4]="May";
        months[5]="June";
        months[6]="July";
        months[7]="August";
        months[8]="September";
        months[9]="October";
        months[10]="November";
        months[11]="December";

        int[] n=new int[12];
        double srednee=0;
        for (int i=0;i<12;i++) {
            n[i]=(int)(Math.random()*100);
            srednee+=n[i];
            System.out.println(months[i]+" "+n[i]);
        }
        srednee=srednee/12;
        System.out.println("Среднее: "+srednee);
    }
}
