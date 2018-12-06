import Tests.*;

public class Main {
    public static void main(String[] args) {
        try {
            TestController.test(Test01.class);
            TestController.test(Test02.class);
            TestController.test(Test03.class);
            TestController.test(Test04WithRuntimeException.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
