package TestFramework;

import TestFramework.Annotations.After;
import TestFramework.Annotations.Before;
import TestFramework.Annotations.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class TestController<T> {
    /**
     * Класс теста
     */
    private Class clazz;
    /**
     * Метод начальной обработки тестов
     */
    private Method init;
    /**
     * Метод финальной обработки тестов
     */
    private Method finish;
    /**
     * Массив методов тестов. Использован TreeMap,
     * в котором ключи - это целые числа, равные значению priority.
     * TreeMap автоматически сортирует значения по возрастанию ключа.
     * В качестве значений массива TreeMap выбран ArrayList, который содержит
     * методы с равным приоритетом в произвольном порядке.
     */
    private TreeMap<Integer, ArrayList<Method>> invokablesTests;

    public TestController() {
        this.invokablesTests = new TreeMap<>();
        this.init = null;
        this.finish = null;
    }

    public static void testPackageThroughClassloader(String pack) throws Exception {
        ArrayList<Class> classes = TestController.getClasses(pack);
        for (Class c : classes) {
            TestController.test(c);
        }
    }

    public static void testPackageThroughReflections(String pack) throws Exception {
        Reflections ref = new Reflections(pack, new SubTypesScanner(false));
        Set<Class<?>> classes = ref.getSubTypesOf(Object.class);
        for (Class c : classes) {
            TestController.test(c);
        }
    }

    public static void test(Class clazz) throws NoSuchMethodException {
        TestController tc = new TestController();
        tc.clazz = clazz;
        tc.test();
    }

    public static void test(String clazz) throws ClassNotFoundException, NoSuchMethodException {
        TestController tc = new TestController();
        tc.clazz = Class.forName(clazz);
        tc.test();
    }

    public static ArrayList<Class> getClasses(String pack) throws Exception {
        String path = File.separatorChar + pack.replace('.', File.separatorChar);
        ArrayList<Class> classes = new ArrayList<>();
        InputStream resource = TestController.class.getResourceAsStream(path);
        if (resource == null)
            throw new Exception("Package resource is null");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
        String line = null;
        while (reader.ready()) {
            line = reader.readLine();
            if (line.endsWith(".class")) {
                classes.add(Class.forName(pack + "." + line.substring(0, line.lastIndexOf('.'))));
            }
        }
        return classes;
    }

    @SuppressWarnings("unchecked")
    private void test() throws NoSuchMethodException {
        try {
            System.out.println(String.format("------ run tests %s -----", this.clazz.getName()));
            this.parseClass();
            Constructor<T> c = this.clazz.getConstructor();
            T test;
            for (Map.Entry<Integer, ArrayList<Method>> entry : this.invokablesTests.entrySet()) {
                for (Method m : entry.getValue()) {
                    test = c.newInstance();
                    if (this.init != null)
                        this.init.invoke(test);
                    m.invoke(test);
                    if (this.finish != null)
                        this.finish.invoke(test);
                }
            }
        } catch (Exception e) {
            System.out.println("Errors:");
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(String.format("------ tests %s failed -----", this.clazz.getName()));
            System.out.println();
            return;
        }
        System.out.println(String.format("------ tests %s passed -----", this.clazz.getName()));
        System.out.println();

    }

    private void parseClass() {
        Method[] methods = this.clazz.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Before.class)) {
                if (this.init != null)
                    throw new RuntimeException("Multiple @Before Exception.");
                this.init = m;
            }
            if (m.isAnnotationPresent(After.class)) {
                if (this.finish != null)
                    throw new RuntimeException("Multiple @After Exception.");
                this.finish = m;
            }
            if (m.isAnnotationPresent(Test.class)) {
                Test t = m.getAnnotation(Test.class);
                int priority = t.priority();
                ArrayList<Method> arl;
                if (!this.invokablesTests.containsKey(priority)) {
                    arl = new ArrayList<>();
                    arl.add(m);
                    this.invokablesTests.put(priority, arl);
                } else {
                    arl = this.invokablesTests.get(priority);
                    arl.add(m);
                }
            }
        }
    }

}
