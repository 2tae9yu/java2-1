package chapter09;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AnonymousClassListener extends JFrame {
    public AnonymousClassListener() {
        setTitle("Action 이벤트 리스너 작성");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton btn = new JButton("Action");
        c.add(btn);

        // 익명 클래스로 이벤트 리스너를 작성한다.
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton)e.getSource();

                if(b.getText().equals("Action"))
                    b.setText("액션");

                else
                    b.setText("Action");

                // AnonymousClassListener의 멤버나 JFrame의 멤버를 호출할 수 있음
                setTitle(b.getText());
            }
        });

        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AnonymousClassListener();
    }
}