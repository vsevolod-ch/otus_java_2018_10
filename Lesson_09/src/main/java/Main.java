import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.MBeanServer;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Process ID: " + ManagementFactory.getRuntimeMXBean().getName());
        long start = System.currentTimeMillis();

        switchOnMonitoring();
        int loop = 10000;
        int size = 5_000_000;
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName o = new ObjectName("Lesson_09:type=StressThread");
        StressThread stress = new StressThread(loop);
        mBeanServer.registerMBean(stress, o);
        stress.setSize(size);
//        Thread t1 = stress.start();
//        Thread t2 = stress.start();
//        Thread t3 = stress.start();
//        t1.join();
//        t2.join();
//        t3.join();
        stress.run();

        long end = System.currentTimeMillis();

        System.out.println("Time:" + (end - start) / 1000);
    }

    private static void switchOnMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

                    System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
