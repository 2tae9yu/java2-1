package chapter04;
import java.util.Scanner;

public class Ex4_2 {
    static class Rectangle {
        int width;
        int height;

        public int getArea() {
            return width * height;
        }
    }

    public static void main(String[] args) {
        Rectangle rect = new Rectangle(); // 객체 생성

        Scanner scanner = new Scanner(System.in);
        System.out.print(">> ");
        rect.width = scanner.nextInt();
        System.out.print(">> ");
        rect.height = scanner.nextInt();
        System.out.println("사각형의 면적은 " + rect.getArea());

        scanner.close();
    }
}