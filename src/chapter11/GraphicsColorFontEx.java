package chapter11;

import javax.swing.*;
import java.awt.*;

public class GraphicsColorFontEx extends JFrame {
	public GraphicsColorFontEx() {
		setTitle("문자열, Color, Font 사용  예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new MyPanel()); // 새로운 패널을 생성하여 컨텐트팬으로 사용
		
		setSize(300, 300);
		setVisible(true);
	}

	// JPanel을 상속받아 새 패널 구현
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE); // 파란색 지정
			g.drawString("자바가 정말 재밋다.~~", 30,30); // (30,30)에 문자열 출력
			g.setColor(new Color(255, 0, 0)); // 빨간색 지정
			g.setFont(new Font("Arial", Font.ITALIC, 30));  
			g.drawString("How much?", 30, 70);
			g.setColor(new Color(0x00ff00ff)); // r=ff, g=00, b=ff, 즉 보라색
			
			for(int i=1; i<=4; i++) {
				g.setFont(new Font("Jokerman", Font.ITALIC, i*10));
				g.drawString("This much!!", 30, 60+i*40); 
			}
		}	
	}
	
	public static void main(String [] args) {
		new GraphicsColorFontEx();
	}
}