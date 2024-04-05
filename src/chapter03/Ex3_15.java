package chapter03;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Ex3_15 {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("정수 5개 입력(정수가 아니면 더하지 않습니다)>>");

		int sum=0, n=0;

		for (int i=0; i<5; i++) {		
			try {
				n = scanner.nextInt();
				sum += n;
			}

			catch (InputMismatchException e) {
				String r = scanner.next(); // 변환하지 못해 scanner의 버퍼에 남아 있는 토큰을 읽어서 버린다.				
				System.out.println(r + "은 정수로 변환할 수 없습니다.");
				continue; // 다음 반복으로 계속 진행
			}
		}

		System.out.println("정수 합은 " + sum);

        scanner.close();
	}
}