package chapter03;

public class Ex3_14_2 {
    public static void main(String[] args) {
		int [] intArray = {1,2,3,4};
		int i=0, sum=0;

		try {
			for(i=0; i<10; i++) { // 배열 개수가 4인데 10번 루프
				sum += intArray[i]; // i=4 일때 예외 발생
                
				System.out.println("인덱스 " + i + "까지의 합 = " + sum);
			}
		}

		catch(ArrayIndexOutOfBoundsException e) {
			System.out.print("intArray["+ i + "]"+ "를 접근할 수 없습니다.");
			System.out.println(" 인덱스가 범위를 벗어났습니다.");
		}
	}
}