# 이태규 학번 202330124

## 4월 19일 강의
> 내용 정리

추상 클래스: 상속에서 슈퍼 클래스로 사용되는 클래스
추상 메소드: 선언은 되어 있으나 코드가 구현되어 있지 않은, 껍데기만 있는 메소드. 추상 메소드를 작성하려먼 abstract 키워드와 함께 원형만 선언하고 코드는 작성하지 않는다.

추상 클래스가 되는 경우는 2가지로서, 모드 abstract 키워드로 선언해야 한다.

- 추상 메소드를 포함하는 클래스
```java
abstract class shape { // 추상 클래스 선언
    public shape() { }
    public void paint() { draw(); }
    abstract public void draw(); // 추상 메소드 선언
}
```
- 추상 메소드가 없지만 abstract로 선언한 클래스
```java
abstract class MyComponent { // 추상 클래스 선언
    String name;
    public void load(String name) {
        this.name = name;
    }
}
```

추상 클래스를 단순히 상속받는 서브 클래스는 추상 클래스가 된다.(추상 클래스의 추상 메소드를 그대로 상속받기 때문)
그러므로 서브 클래스에 abstract를 붙여 추상 클래스임을 명시해야 컴파일 오류가 발생하지 않는다.

자바의 인터페이스
- interface 키워드를 사용하여 클래스를 선언하듯이 선언
```java
interface PhoneInterface { // 인터페이스 선언
    public static final int TIMEOUT = 10000; // 상수 필드, public static final 생략 가능
    public abstract void sendCall(); // 추상 메소드, public abstract 생략 가능
    public abstract void receiveCall(); // 추상 메소드, public abstract 생략 가능
    public default void printLogo() { // default 메소드, public 생략 가능
        System.out.println("** Phone **");
    };
}
```
- 인터페이스는 객체를 생성할 수 없다
- 인터페이스 타입의 레퍼런스 변수는 선언 가능하다
- 인터페이스끼리 상속된다
- 인터페이스를 상속받아 클래스를 작성하면 인터페이스의 모든 추상 메소드를 구현하여야 한다

자바의 패키지
- 서로 관련 있는 클래스나 인터페이스의 컴파일된 클래스(.class) 파일들을 한 곳에 묶어 놓은 것

- 모듈: 클래스 파일들을 묶어 놓은 패키지들을 담는 컨테이너로, 모듈 파일(.jmod)로 저장한다
- **현실적으로 모듈로 나누어 자바 프로그램을 작성할 필요 없음**

- import 문
```java
import java.util.Scanner;
public class ImportExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.next());
    }
}
```

Object 생성과 특징
- Object는 java.lang 패키지에 속한 클래스이며, 모든 클래스에 강제로 상속된다
- Object만이 아무 클래스도 상속받지 않는 유일한 클래스로 계층 구조 상 **최상위 클래스**이다.
```java
Object obj = new Object();
```
- toString(): 객체를 문자열로 변환하는 Object의 메소드

Wrapper 클래스
- int, char, double 등 8개의 기본 타입을 객체로 다루기 위해 JDK에 만들어진 8개 클래스를 통칭하여 부르는 말

String 클래스
- java.lang 패키지에 포함된 클래스, 문자열을 나타낸다. 스트링 리터럴은 자바 컴파일러에 의해 모두 String 객체로 처리된다

Math 클래스
- java.lang 패키지에 포함되어 있으며 기본적인 산술 연산을 제공한다, 모든 멤버 메소드는 static 타입

## 4월 12일 강의
> 내용 정리

non-static 멤버와 static 멤버의 차이점
- static 멤버(클래스 멤버): 객체를 생성하지 않고 사용할 수 있음, main() 메소드가 실행되기 전에 이미 생성됨
- non-static 멤버(인스턴스 멤버): 객체가 생길 때 객체마다 생성됨, 객체가 사라지면 non-static 멤버도 같이 사라짐

final 클래스
- final이 클래스 이름 앞에 사용되면 클래스를 상속받을 수 없음을 지정

final 메소드
- final로 메소드를 선언하면 오버라이딩 할 수 없는 메소드임을 선언

final 필드
- final로 필드를 선언하면 필드는 상수가 된다

자바의 상속 선언
- 자바에서는 부모 클래스를 슈퍼 클래스(super class), 상속받는 자식 클래스를 서브 클래스(sub class)라고 부르며, 상속을 선언할 때 확장한다는 뜻을 가진 extends 키워드를 사용

자바 상속의 특징
- 자바에서는 클래스의 다중 상속을 지원하지 않는다
- 자바에서는 상속의 횟수에 제한을 두지 않는다
- 자바에서 계층 구조의 최상위에 java.lang.Object 클래스가 있다

생성자: 객체를 초기화 시켜주는 것

슈퍼클래스의 private 멤버
- 슈퍼 클래스의 멤버가 private으로 선언되면, 서브 클래스를 포함하여 다른 어떤 클래스에서도 접글할 수 없다

슈퍼클래스의 디폴트 멤버
- 슈퍼 클래스의 멤버가 디폴트로 선언되면, 패키지에 있는 모든 클래스가 접근 가능하다

슈퍼클래스의 public 멤버
- 슈퍼 클래스의 멤버가 public으로 선언되면, 같은 패키지에 있든 다른 패키지에 있든 모든 클래스에서 접근할 수 있다.

업캐스팅(upcasting): 서브 클래스의 객체에 대한 레퍼런스를 슈퍼 클래스 타입으로 변환하는 것

다운캐스팅(downcasting): 업캐스팅과 반대로 캐스팅하는 것

메소드 오버라이딩(method overriding): 슈퍼 클래스와 서브 클래스의 메소드 사이에 발생하는 관계, 슈퍼 클래스에 선언된 메소드와 같은 이름, 같은 리턴 타입, 같은 매개 변수 리스트를 갖는 메소드를 서브 클래스에서 재작성하는 것

## 4월 5일 강의
> 내용 정리

배열 선언 및 생성
1. 배열에 대한 레퍼런스 변수 선언
2. 배열 생성 - 배열의 저장 공간 할당

레퍼런스 치환과 배열 공유
```java
int intArray[] = new int[5];
int myArray[] = intArray; // 레퍼런스 치환. myArray는 intArray와 동일한 배열 참조
```

메소드에서 배열 리턴
- 메소드는 레퍼런스만 리턴하기 때문에, 리턴 타입을 선언할 때 [] 안에 배열의 크기를 지정하지 않는다. <br/>또한 리턴하는 배열의 타입이 리턴받는 레퍼런스 변수의 타입과 일치해야 한다.
```java
int [] intArray; // makeArray()의 리턴 타입과 동일한 타입 선언
intArray = makeArray(); // makeArray() 메소드가 리턴하는 배열 받음
```

자바의 예외 처리
- 정수를 0으로 나누는 경우
- 배열의 크기보다 큰 인덱스로 배열의 원소를 접근하는 경우
- 존재하지 않는 파일을 읽으려고 하는 경우
- 정수 입력을 기다리는 코드가 실행되고 있을 때, 사용자가 문자를 입력한 경우

예외 처리, try-catch-finally문
```java
try {
    에외가 발생할 가능성이 있는 실행문(try 블럭)
}

catch(처리할 예외 타입 선언) {
    예외 처리문(catch 블럭)
}

/* 생략 가능
finally {
    예외 발생 여부와 상관없이 무조건 실행되는 문장(finally 블럭)
}
*/
```

캡슐화
- 객체를 캡슐로 싸서 내부를 보호하고 볼 수 없게 하는 것(객체의 본질적인 특징)

상속
- 자식 클래스가 부모 클래스의 속성을 물려받고 기능을 추가하여 확장(extends)하는 개념

다형성
- 같은 이름의 메소드가 클래스 혹은 객체에 따라 다르게 동작하도록 구현하는 것
- 메소드 오버라이딩(overriding): 부모 클래스에 구현된 메소드를, 자식 클래스에서 동일한 이름으로 자신의 특징에 맞게 다시 구현하는 것
- 메소드 오버로딩(overloading): 클래스 내에서 이름이 같지만 서로 다르게 동작하는 메소드를 여러 개 만드는 것

## 3월 29일 강의
> 내용 정리

증감 연산자
- a++: a를 1 증가하고 증가 전의 값 반환
- ++a: a를 1 증가하고 증가된 값 반환
- a--: a를 1 감소하고 감소 전의 값 반환
- --a: a를 1 감소하고 감소된 값 반환

조건문
- if문, if-else문, switch문

반복문
- for문, while문, do-while문

## 3월 22일 강의
> 내용 정리

1. Ctrl + Shift + P
2. Java: Create Java Project
3. No builds tools
4. Directory 설정
5. .vscode, bin, lib, src 파일 복사
6. 기존 폴더에 붙여넣은 후 커밋

주석 단축키 Ctrl + ?

자바 기본 구조
```java
클래스 {
    변수
    상수
    함수(메소드) 등
}
```

```java
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
```

## 3월 15일 강의
> 내용 정리

1. F1
2. Git: Clone
3. GitHub에서 복사한 주소 붙여넣기
4. 변경 사항 - 변경 내용 스테이징
5. 메세지 작성 후 커밋

확장 프로그램 Git Graph를 통해 작업 확인 가능

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello!!! VSCode가 좋아요");
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("Test Hello");
    }
}
```