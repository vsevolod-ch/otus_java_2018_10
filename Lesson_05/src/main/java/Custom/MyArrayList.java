package Custom;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private static final int INIT = 10;

    private T[] array;

    private int size;

    public MyArrayList() {
        this(MyArrayList.INIT);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initSize) {
        this.array = (T[]) new Object[initSize];
        this.size = 0;
    }

    public MyArrayList(T[] t) {
        this.array = t;
        this.size = t.length;
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(List<T> t) {
        this.array = (T[]) t.toArray();
        this.size = t.size();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(Object o) {
        return this.indexOf(o) != -1;
    }

    public boolean add(T t) {
        if (this.size == this.array.length - 1)
            this.increase();
        this.array[this.size++] = t;
        return true;
    }

    public boolean remove(Object o) {
        int index = this.indexOf(o);
        if (index != -1)
            this.remove(index);
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object element : c)
            if (!this.contains(element))
                return false;
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        this.increase(c);
        for (T t: c)
            this.add(t);
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends T> c) {
        this.increase(c);
        System.arraycopy(this.array, index, this.array, index + c.size(), c.size());
        T[] insert = (T[]) c.toArray();
        System.arraycopy(insert, 0, this.array, index, c.size());
        this.size += c.size();
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0)
            return false;
        boolean status = false;
        int index;
        for (Object t: c) {
            index = this.indexOf(t);
            if (index == -1)
                continue;
            this.remove(index);
            status = true;
        }
        return status;
    }

    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0)
            return false;
        boolean status = false;
        T t;
        for (int i = 0; i < this.size; i++) {
            t = this.array[i];
            if (c.contains(t))
                continue;
            this.remove(i);
            status = true;
        }
        return status;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        this.array = (T[]) new Object[MyArrayList.INIT];
        this.size = 0;
    }

    public T get(int index) {
        if (index >= this.size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        return this.array[index];
    }

    public T set(int index, T element) {
        if (index >= this.size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        T t = this.array[index];
        this.array[index] = element;
        return t;
    }

    public void add(int index, T element) {
        if (index >= this.size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        if (this.size == this.array.length - 1)
            this.increase();
        System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        this.array[index] = element;
        ++this.size;
    }

    public T remove(int index) {
        if (index >= this.size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        T t = this.array[index];
        System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
        --this.size;
        return t;
    }

    public int indexOf(Object o) {
        if (this.isEmpty())
            return -1;
        for (int i = 0; i < this.size; i++) {
            if (o.equals(this.array[i]))
                return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (this.isEmpty())
            return -1;
        for (int i = this.size - 1; i >= 0; i--) {
            if (o.equals(this.array[i]))
                return i;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public void display() {
        T[] tArr = (T[]) new Object[this.size];
        System.arraycopy(this.array, 0, tArr, 0, this.size);
        System.out.println(Arrays.toString(tArr));
    }

    public ListIterator<T> listIterator() {
        return new MyListIterator<>(this);
    }

    public ListIterator<T> listIterator(int index) {
        return new MyListIterator<>(this, index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator() {
        return new MyIterator<T>(this);
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    /**
     * Shallow copy src to dest.
     *
     * @param dest
     * @param src
     * @param <T>
     */
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        if (src.size() == 0)
            return;
        dest.clear();
        dest.addAll(src);
    }

    /**
     * Selection sort.
     *
     * @param list
     * @param c
     * @param <T>
     */
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        if (list.size() == 0)
            return;
        int min, x, main;
        T mi, ma;
        for (main = 0; main < list.size(); main++) {
            min = main;
            for (x = main + 1; x < list.size(); x++) {
                if (c.compare(list.get(x), list.get(min)) < 0)
                    min = x;
            }
            mi = list.get(min);
            ma = list.get(main);
            list.set(main, mi);
            list.set(min, ma);
        }
    }

    /**
     * Selection sort.
     *
     * @param c
     */
    public void sort(Comparator<? super T> c) {
        MyArrayList.sort(this, c);
    }

    @SuppressWarnings("unchecked")
    private void increase(int increaseSize) {
        T[] arr = (T[]) new Object[this.array.length + increaseSize];
        System.arraycopy(this.array, 0, arr, 0, this.array.length);
        this.array = arr;
    }

    private void increase() {
        this.increase(MyArrayList.INIT);
    }

    private void increase (Collection <? extends T> c) {
        if (c.size() > this.array.length - this.size) {
            int increaseSize = MyArrayList.INIT;
            if (c.size() > MyArrayList.INIT)
                increaseSize = c.size() + MyArrayList.INIT;
            this.increase(increaseSize);
        }
    }
}
