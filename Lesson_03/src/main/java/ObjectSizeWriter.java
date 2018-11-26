import HelperObjects.A;
import HelperObjects.B;
import HelperObjects.C;
import HelperObjects.D;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;



public class ObjectSizeWriter {

    private static final int ARRAY_SIZE = 20000000;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.out.println("Details: ");
        System.out.println(VM.current().details());
        System.out.println("---");
        System.out.println("Primitive fields sizes:");
        String[] types = {
                "boolean",
                "byte",
                "short",
                "char",
                "short",
                "int",
                "long",
                "double",
                "float"};
        long size;
        for (String type: types) {
            size = VM.current().sizeOfField(type);
            printSize(type, size);
        }

        System.out.println("---");
        System.out.println("Objects sizes:");
        Class[] types2 = {
                Boolean.class,
                Byte.class,
                Short.class,
                Character.class,
                Integer.class,
                Long.class,
                Double.class,
                Float.class,
                String.class,
                A.class,
                B.class,
                C.class,
                D.class
        };
        String info;
        for (Class type2: types2) {
            size = ClassLayout.parseClass(type2).instanceSize();
            info = ClassLayout.parseClass(type2).toPrintable();
            printSize(type2.getName(), size, info);
        }

        System.out.println("---");
        System.out.println("String array size:");
        String[] arr0 = new String[0];
        size = ClassLayout.parseInstance(arr0).instanceSize();
        printSize(arr0.getClass().getName(), size);

        String[] arr3 = new String[ARRAY_SIZE/2];
        size = ClassLayout.parseInstance(arr3).instanceSize();
        printSize(arr3.getClass().getName(), size);

        String[] arr = new String[ARRAY_SIZE];
        size = ClassLayout.parseInstance(arr).instanceSize();
        printSize(arr.getClass().getName(), size);


//        ArrayList<String> arr2 = new ArrayList<String>();
//        arr2.add("a");
//        System.gc();
//        System.out.println(GraphLayout.parseInstance(arr2).toPrintable());
//        System.out.println(GraphLayout.parseInstance(arr2).totalSize());
//        for (int i = 0; i < 100; i++) {
//            arr2.add("a");
//        }
//        System.gc();
//        System.out.println(GraphLayout.parseInstance(arr2).toPrintable());
//        System.out.println(GraphLayout.parseInstance(arr2).totalSize());

    }

    private static void printSize(String type, long size) {
        System.out.println(String.format("%s: %sB", type, size));
    }

    private static void printSize(String type, long size, String info) {
        System.out.println(String.format("%s: %sB\n%s", type, size, info));
    }

}
