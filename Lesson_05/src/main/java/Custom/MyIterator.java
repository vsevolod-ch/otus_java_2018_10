package Custom;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class MyIterator<T> implements Iterator<T> {

    private List<T> list;
    private int index = 0;

    public MyIterator(List<T> list) {
        this.list = list;
    }

    public boolean hasNext() {
        return this.index < this.list.size();
    }

    public T next() {
        return this.list.get(this.index++);
    }
}
