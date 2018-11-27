import Custom.MyArrayList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> arr = new MyArrayList<>();
        Integer[] integers = {1, 20, 43, 35, 45, 345, 234, 5, 322, 64, 73, 554, 3, 765};
        arr.addAll(Arrays.asList(integers));
        MyArrayList<Integer> arr2 = new MyArrayList<>();

        System.out.println("arr: ");
        arr.display();
        System.out.println("arr2: ");
        arr2.display();

        MyComparator<Integer> comp = new MyComparator<>();

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

        System.out.println("---------");

        MyArrayList<Integer> arr3 = new MyArrayList<>();
        Integer[] integers2 = {1, 20, 43, 35, 45, 345, 234, 5, 322, 64, 73, 554, 3, 765};
        Collections.addAll(arr3, integers2);
        System.out.println("arr3:");
        arr3.display();

        MyArrayList<Integer> arr4 = new MyArrayList<>();
        Integer[] integers3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Collections.addAll(arr4, integers3);
        System.out.println("arr4:");
        arr4.display();

        Collections.copy(arr4, arr3);
        System.out.println("arr4 after copy operation:");
        arr4.display();

        arr4.sort(comp); // equivalent: Collections.sort(arr4, comp);
        System.out.println("arr4 after sorting:");
        arr4.display();

//        System.out.println(Math.ceil((double)14 / 10));
    }
}

class MyComparator<T extends Comparable<T>> implements Comparator<T> {

    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
