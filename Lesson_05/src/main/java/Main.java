import Custom.MyArrayList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> arr = new MyArrayList<Integer>();
        Integer[] integers = {1, 20, 43, 35, 45, 345, 234, 5, 322, 64, 73, 554, 3, 765};
        arr.addAll(Arrays.asList(integers));
        MyArrayList<Integer> arr2 = new MyArrayList<Integer>();

        System.out.println("arr: ");
        arr.display();
        System.out.println("arr2: ");
        arr2.display();

        MyComparator<Integer> comp = new MyComparator<Integer>();

        MyArrayList.copy(arr2, arr);
        System.out.println("arr: ");
        arr.display();
        System.out.println("arr2: ");
        arr2.display();

        MyArrayList.sort(arr, comp);
        System.out.println("arr: ");
        arr.display();
        System.out.println("arr2: ");
        arr2.display();
    }
}

class MyComparator<T extends Comparable<T>> implements Comparator<T> {

    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
