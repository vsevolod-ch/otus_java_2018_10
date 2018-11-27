package Custom;

import java.util.Iterator;
import java.util.List;

class MyIterator<T> implements Iterator<T> {

    private List<T> list;
    private int index = 0;

    public MyIterator(List<T> list) {
        this.list = list;
    }

    public boolean hasNext() {
        return this.index < this.list.size() -1;
    }

    public T next() {
        return this.list.get(++this.index);
    }
}
