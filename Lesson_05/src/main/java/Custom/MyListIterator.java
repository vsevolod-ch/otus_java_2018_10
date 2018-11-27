package Custom;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyListIterator<T> implements ListIterator<T> {

    private int index = -1;
    private List<T> list;

    public MyListIterator(List<T> list) {
        this(list, -1);
    }

    public MyListIterator(List<T> list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.list.size();
    }

    @Override
    public T next() {
        if (this.index == this.list.size() - 1)
            throw new NoSuchElementException();
        return this.list.get(++this.index);
    }

    @Override
    public boolean hasPrevious() {
        return this.index > 0;
    }

    @Override
    public T previous() {
        if (this.index <= 0)
            throw new NoSuchElementException();
        return this.list.get(--this.index);
    }

    @Override
    public int nextIndex() {
        return ++this.index;
    }

    @Override
    public int previousIndex() {
        if (this.index == -1)
            return -1;
        return --this.index;
    }

    @Override
    public void remove() {
        if (this.index < 0)
            throw new IllegalStateException();
        this.list.remove(this.index--);
    }

    @Override
    public void set(T t) {
        if (this.index < 0 || this.index >= this.list.size())
            throw new IllegalStateException();
        this.list.set(this.index, t);
    }

    @Override
    public void add(T t) {
        if (this.index < 0)
            throw new IllegalStateException();
        this.list.add(this.index, t);
    }
}
