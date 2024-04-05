package chapter03;
import java.util.Scanner;

public class Ex3_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count=0, n=0; // count는 입력되는 수의 개수
		double sum=0; // sum은 합
		
		System.out.println("정수를 입력하고 마지막에 0을 입력하세요.");
        
		while((n = scanner.nextInt()) != 0) { // 0이 입력되면 while문 벗어남
			sum = sum + n;
			count++;
		}

		System.out.print("수의 개수는 " + count + "개이며 ");
		System.out.println("평균은 " + sum/count + "입니다.");

		scanner.close();
    }   
}