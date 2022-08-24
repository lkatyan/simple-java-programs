import java.util.*;

/*
    Задание: Ввести с клавиатуры список из n целых чисел.
    а) Найти сумму, количество и среднее значение среди нечетных чисел.
    б) Найти среди отрицательных нечетных чисел минимальное и максимальное
        значения и их номера и поменять их местами.
*/

public class List_example {
    public static void main(String []args){
        List<Integer> numbers = new ArrayList<Integer>();

        // Ввод с клавиатуры
//        Scanner in = new Scanner(System.in);
//        for (int i = 0; i <10 ; i++) {
//          int s = in.nextInt();
//          numbers.add(s);
//        }

        for (Integer i : Arrays.asList(0,1,2,3,4,5,6,7))
            numbers.add(i);

        // Задание "а)"
        int summ=0, kol_vo=0, sr_znach=0;
        for (Iterator<Integer> iter = numbers.iterator(); iter.hasNext(); ) {
            Integer number = iter.next();
            if (number % 2 != 0) {
                summ += number;
                kol_vo++;
            }
        }
        sr_znach=summ/kol_vo;
        System.out.println(summ + " " + kol_vo + " " + sr_znach);

        // Задание "б)"
        numbers.clear();
        for (Integer i : Arrays.asList(-5,-4,-9,-3,-2,-1,0,1,2,3,-4,5,-1,7))
            numbers.add(i);

        int min=1000, max=-1000;
        for (Iterator<Integer> iter = numbers.iterator(); iter.hasNext(); ) {
            Integer number = iter.next();
            if ( (number<0) & (number % 2 != 0) ) {
                if (number<min) min=number;
                if (number>max) max=number;
            }
        }
        Collections.swap(numbers, numbers.indexOf(max), numbers.indexOf(min));
        System.out.println(numbers);
    }
}