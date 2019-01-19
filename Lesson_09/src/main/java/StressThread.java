import javax.management.*;

public class StressThread implements Runnable, BenchmarkMBean {

    private int size = 1000;

    private int loop;

    public StressThread(int loop) {
        this.loop = loop;
    }

    public Thread start() {
        Thread t = new Thread(this);
        t.start();
        return t;
    }


    public void run() {
        for (int i = 0; i < this.loop; i++) {
            Object[] o = new Object[this.size];
            for (int j = 0; j < this.size; j++) {
                o[j] = new String(new char[this.size]);
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int s) {
        this.size = s;
    }
}
