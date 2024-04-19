package chapter05;

class Point_2 {
    private int x, y;

    public Point_2() {
        this.x = this.y = 0;
    }

    public Point_2(int x, int y) {
        this.x = x; this.y = y;
    }

    public void showPoint() {
        System.out.println("(" + x + "," + y + ")");
    }
}

class ColorPoint_2 extends Point_2 {
    private String color;

    public ColorPoint_2(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    public void showColorPoint() {
        System.out.print(color);
        showPoint();
    }
}
public class Ex5_2 {
    public static void main(String[] args) {
        ColorPoint_2 cp = new ColorPoint_2(5, 6, "blue");
        cp.showColorPoint();
    }
}