import java2.add.*;
import java3.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Add foo = new Add();
        System.out.println(foo.add(10, 20));

        Calc2 bar = new Calc2();
        System.out.println(bar.add2(30, 20));
    }
}