import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static final int size = 20000000;

    public static void main(String[] args) {
        printSize((byte)1);
        printSize(Byte.valueOf((byte)1));
        printSize((short)1);
        printSize(Short.valueOf((short)1));
        printSize(1);
        printSize(Integer.valueOf(1));
        printSize(1L);
        printSize(Long.valueOf(1L));
        printSize(1F);
        printSize(Float.valueOf(1F));
        printSize(1.0);
        printSize(Double.valueOf(1.0));
        printSize(new Object());

        printSize("");
        printSize(new String(new char[1]));
        ArrayList<String> strArrList = new ArrayList<String>();
        String[] strArr = new String[Main.size];
        printSize(strArr);
        printSize(strArrList);
        strArrList.addAll(Arrays.asList(strArr));
        printSize(strArrList);

    }

    public static void printSize(Object o) {
        System.out.println(
                String.format(
                        "Объект %s имеет размер %d.",
                        o.getClass().getSimpleName(),
                        ObjectSizeGetter.sizeOf(o)
                )
        );
    }

}
