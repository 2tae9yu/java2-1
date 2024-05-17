package chapter08;

import javax.swing.*;
import java.awt.*;

public class GridLayoutEx extends JFrame {
    public GridLayoutEx() {
        super("GridLayout 에제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane(); // 컨텐트팬 알아내기

        contentPane.setLayout(new GridLayout(4, 3, 10, 10)); // 1x10의 GridLayout 배치관리자

        for(int i = 9; i > 0; i--) { // 10개의 버튼 부착
            String text = Integer.toString(i); // 정수 i를 문자열로 변환
            JButton button = new JButton(text); // 버튼 컴포넌트 생성
            contentPane.add(button); // 컨텐트팬에 버튼 부착
        }

        contentPane.add(new JButton("*")); // 컨텐트팬에 버튼 부착
        contentPane.add(new JButton("0")); // 컨텐트팬에 버튼 부착

        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GridLayoutEx();
    }
}