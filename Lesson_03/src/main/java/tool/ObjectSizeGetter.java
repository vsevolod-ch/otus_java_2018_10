package tool;

import java.lang.instrument.Instrumentation;

public class ObjectSizeGetter {

    private static Instrumentation inst;

    public static void premain(String[] args, Instrumentation inst) {
        ObjectSizeGetter.inst = inst;
    }

    public static long sizeOf(Object o) {
        return ObjectSizeGetter.inst.getObjectSize(o);
    }

}
