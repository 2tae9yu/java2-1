# 이태규 학번 202330124

## 6월 14일 강의
> 내용 정리

**자바의 입출력 스트림**
- 자바의 입출력 스트림
    - 입출력 장치와 자바 응용 프로그램 연결
        - 입력 스트림: 입력 장치로부터 자바 프로그램으로 데이터를 전달하는 객체
        - 출력 스트림: 자바 프로그램에서 출력 장치로 데이터를 보내는 객체
    - 특징
        - 입출력 스트림 기본 단위: 바이트
        - 단방향 스트림, 선입선출 구조

**자바의 입출력 스트림 종류**
- 문자 스트림
    - 문자만 입출력하는 스트림
    - 문자가 아닌 바이너리 데이터는 스트림에서 처리하지 못함
    - 문자가 아닌 데이터를 문자 스트림으로 출력하면 깨진 기호가 출력
    - 바이너리 파일을 문자 스트림으로 읽으면 읽을 수 없는 바이트가 생겨서 오류 발생. 예) 텍스트 파일을 읽는 입력 스트림

- 바이트 스트림
    - 입출력 데이터를 단순 바이트의 흐름으로 처리
    - 문자 데이터든 바이너리 데이터든 상관없이 처리 가능. 예) 바이너리 파일을 읽는 입력 스트림

**스트림 연결**
- 여러 개의 스트림을 연결하여 사용할 수 있음
    - 예) 키보드에서 문자를 입력받기 위해 System.in과 InputStreamReader를 연결한 코드
        ```java
        InputStreamReader rd = new InputStreamReader(System.in);
        ```
        ```java
        while(true) {
            int c = rd.read(); // 입력 스트림으로부터 키 입력. c는 입력된 키 문자 값
            if(c == -1) // 입력 스트림의 끝을 만나는 경우
                break; // 입력 종료
        }
        ```

**문자 스트림으로 텍스트 파일 읽기**
- 텍스트 파일을 읽기 위해 문자 스트림 FileReader클래스 이용
    1. 파일 입력 스트림 생성(파일 열기)
        - 스트림을 생성하고 파일을 열어 스트림과 연결
            ```java
            FileReader fn = new FileReader("c:\\test.txt");
            ```

    2. 파일 읽기
        - read()로 문자 하나씩 파일에서 읽음
            ```java
            int c;
            while((c = fin.read()) != -1) { // 문자를 c에 읽음. 파일 끝까지 반복
                System.out.println((char)c); // 문자 c 화면에 출력
            }
            ```

    3. 스트림 닫기
        - 스트림이 더 이상 필요 없으면 닫아야 함. 닫힌 스트림에서는 읽을 수 없음
        - close()로 스트림 닫기
            ```java
            fin.close();
            ```

**파일 입출력과 예외 처리**
- 파일 입출력 동안 예외 발생 가능
    - 스트림 생성 동안: FileNotFoundException 발생 가능
        - 파일의 경로명이 틀리거나, 디스크의 고장 등으로 파일을 열 수 없음
            ```java
            FileReader fin = new FileReader("c:\\test.txt"); // FileNotFoundException 발생 가능
            ```

    - 파일 읽기, 쓰기, 닫기를 하는 동안: IOException 발생 가능
        - 디스크 오동작, 파일이 중간에 깨진 경우, 디스크 공간이 모자라서 파일 입출력 불가
            ```java
            int c = fin.read(); // IOException 발생 가능
            ```

- try-catch 블럭 반드시 필요
    - 자바 컴파일러의 강제 사항
        ```java
        try {
            FileReader fin = new FileReader("c:\\test.txt");
            ~
            int c = fin.read()
            ~
            fin.close();
        }

        catch(FileNotFoundException e) {
            System.out.println("파일을 열 수 없음");
        }

        catch(IOException e) {
            System.out.println("입출력 오류");
        }
        ```

**문자 스트림으로 텍스트 파일 쓰기**
- 텍스트 파일에 쓰기 위해 문자 스트림 FileWriter 클래스 이용
    1. 파일 출력 스트림 생성(파일 열기)
        - 스트림을 생성하고 파일을 열어 스트림과 연결
            ```java
            FileWriter fout = new FileWriter("c:\\Temp\\test.txt");
            ```

    2. 파일 쓰기
        - write()로 문자 하나씩 파일에 기록
            ```java
            fout.write('A'); // 문자 'A'를 파일에 기록
            ```

        - 블록 단위로 쓰기 가능
            ```java
            char [] buf = new char[1024];
            fout.write(buf, 0, buf.length); // buf[0]부터 버퍼 크기만큼 쓰기
            ```

    3. 스트림 닫기
        - close()로 스트림 닫기
            ```java
            fout.close(); // 스트림 닫기. 더 이상 스트림에 기록할 수 없다.
            ```

**바이트 스트림으로 바이너리 파일 쓰기**
- 바이너리 값을 파일에 저장하기
    - 프로그램 내의 변수, 배열, 버퍼에 든 바이너리 값을 파일에 그대로 기록
        - FileOuterStream 클래스 이용

    1. 파일 출력 스트림 생성(파일 열기)
        - 스트림을 생성하고 파일을 열어 스트림과 연결
            ```java
            FileOutputStream fout = new FileOutputStream("c:\\Temp\\test.out");
            ```

    2. 파일 쓰기
        - write()로 문자 하나씩 파일에 기록
            ```java
            byte b[] = {7, 51, 3, 4, -1, 24};
            for(int i=0; i<b.length; i++)
                fout.write(b[i]); // 배열 b를 바이너리 그대로 기록
            ```

**바이트 스트림으로 바이너리 파일 읽기**
- 바이너리 파일에서 읽기 위해 FileInputStream 클래스 이용
    1. 파일 입력 스트림 생성(파일 열기)
        - 스트림을 생성하고 파일을 열어 스트림과 연결
            ```java
            FileInputStream fin = new FileInputStream("c:\\Temp\\test.out");
            ```

    2. 파일 읽기
        - read()로 문자 하나씩 파일에서 읽기
            ```java
            int n = 0, c;
            while((c = fin.read()) != -1) {
                b[n] = (byte)c; // 읽은 바이트를 배열에 저장
                n++;
            }
            ```

        - 블럭 단위로 읽기 가능
            ```java
            fin.read(b); // 배열 b의 바이트 크기만큼 바이너리 그대로 읽기
            ```

    3. 스트림 닫기
        - close()로 스트림 닫기

**File 클래스**
- File 클래스
    - 파일의 경로명 및 속성을 다루는 클래스
        - java.io.File
        - 파일과 디렉터리 경로명의 추상적 표현
    - 파일 이름 변경, 삭제, 디렉터리 생성, 크기 등 파일 관리
    - File 객체에는 파일 읽기 / 쓰기 기능 없음
        - 파일 입출력은 파일 입출력 스트림 이용

- File 객체 생성
    - 생성자에 파일 경로명을 주어 File 객체 생성
        ```java
        File f = new File("c:\\Temp\\test.txt");
        ```

    - 디렉터리와 파일명을 나누어 생성자 호출
        ```java
        File f = new File("c:\\Temp", "test.txt");
        ```

**File 클래스 활용**
- 파일 크기
    ```java
    long size = f.length();
    ```

- 파일 경로명
    ```java
    File f = new File("c:\\windows\\system.ini");
    String filename = f.getName();
    String path = f.getPath();
    String parent = f.getParent();
    ```

- 파일 타입
    ```java
    if(f.isFile())
        System.out.println(f.getPath() + "는 파일입니다.");
    else if(f.isDirectory())
        System.out.println(f.getPath() + "는 디렉터리입니다.");
    ```

- 디렉터리 파일 리스트 얻기
    ```java
    File f = new File("c:\\Temp");
    File[] subfiles = f.listFiles();

    for(int i=0; i<filenames.length; i++) {
        System.out.print(subfiles[i].getName());
        System.out.println("\t파일 크기: " + subfiles[i].length());
    }
    ```

**TCP / IP 소개**
- TCP / IP 프로토콜
    - 두 시스템 간에 데이터가 손상없이 안전하게 전송되도록 하는 통신 프로토콜
    - TCP에서 동작하는 응용프로그램 사례
        - e-mail, FTP, 웹(HTTP) 등

- TCP / IP 특징
    - 연결형 통신
        - 한 번 연결 후 계속 데이터 전송 가능
    - 보낸 순서대로 받아 응용프로그램에게 전달

**IP 주소**
- IP 주소
    - 네트워크 상에서 유일하게 식별될 수 있는 컴퓨터 주소
        - 숫자로 구성된 주소
        - 4개의 숫자가 '.'으로 연결
            - 예) 192.156.11.15

    - 숫자로 된 주소는 기억하기 어려우므로 www.naver.com과 같은 문자열로 구성된 도메인 이름으로 바꿔 사용
        - DNS(Domain Name System)
            - 문자열로 구성된 도메인 이름을 숫자로 구성된 IP 주소로 자동 변환
    
    - 현재는 32비트의 IP 버전 4(IPv4)가 사용되고 있음
        - IP 주소 고갈로 인해 128비트의 IP 버전 6(IPv6)이 점점 사용되는 추세ㅔ
    
    - 자신의 IP 주소를 간단히 localhost라는 이름으로 사용 가능

**내 컴퓨터의 IP 주소 확인하기**
- 내 컴퓨터의 윈도우에서 명령창을 열어 ipconfig 명령 수행

**포트**
- 포트
    - 통신하는 프로그램 간에 가상의 연결단 포트 생성
        - IP 주소는 네트워크 상의 컴퓨터 또는 시스템을 식별하는 주소
        - 포트 번호를 이용하여 통신할 응용프로그램 식별

    - 모든 응용프로그램은 하나 이상의 포트 생성 사능
        - 포트를 이용하여 상대방 응용프로그램과 데이터 교환

    - 잘 알려진 포트(well-known ports)
        - 시스템이 사용하는 포트 번호
        - 잘 알려진 응용프로그램에서 사용하는 포트 번호
            - 0부터 1023 사이의 포트 번호
            - ex) SSH 22, HTTP 80, FTP 21
        - 잘 알려진 포트 번호는 개발자가 사용하지 않는 것이 좋음
            - 충돌 가능성 있음

**소켓 프로그래밍**
- 소켓(socket)
    - TCP / IP 네트워크를 이용하여 쉽게 통신 프로그램을 작성하도록 지원하는 기반 기술

    - 소켓
        - 두 응용프로그램 간의 양방향 통신 링크의 한쪽 끝 단
        - 소켓끼리 데이터를 주고 받음
        - 소켓은 특정 IP 포트 번호와 결합

    - 자바로 소켓 통신할 수 있는 라이브러리 지원
    - 소켓 종류: 서버 소켓과 클라이언트 소켓

**Socket 클래스, 클라이언트 소켓**
- Socket 클래스
    - 클라이언트 소켓에 사용되는 클래스
    - java.net 패키지에 포함
    - 생성자

**클라이언트에서 소켓으로 서버에 접속하는 코드**
- 클라이언트 소켓 생성 및 서버 접속
    - Socket의 생성자에서 128.12.1.1의 주소의 9999포트에 접속
        ```java
        Socket clientSocket = new Socket("128.12.1.1", 9999);
        ```

- 소켓으로부터 데이터를 전송할 입출력 스트림 생성
    ```java
    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    ```

- 서버로 데이터 전송
    - flush()를 호출하면 스트림 속에 데이터 모두 전송
        ```java
        out.write("hello" + "\n");
        out.flush();
        ```

- 서버로부터 데이터 수신
    ```java
    String line = in.readline();
    // 서버로부터 한 행의 문자열 수신
    ```

- 네트워크 접속 종료
    ```java
    clientSocket.close();
    ```

**ServerSocket 클래스, 서버 소캣**
- ServerSocket 클래스
    - 서버 소캣에 사용되는 클래스, java.net 패키지에 포함

**서버에 클라이언트가 연결되는 과정**
- 서버는 서버 소켓으로 들어오는 연결 요청을 기다림(listen)

- 클라이언트가 서버에게 연결 요청

- 서버가 연결 요청 수락(accept)
    - 새로운 클라이언트 소켓을 만들어 클라이언트와 통신하게 함
    - 그리고 다시 다른 클라이언트의 연결을 기다림

**서버가 클라이언트와 통신하는 과정**
- 서버 소켓 생성
    ```java
    ServerSocket serverSocket = new ServerSocket(9999);
    ```
    - 서버는 9999 포트에서 접속 기다리는 포트로 9999 선택

- 클라이언트로부터 접속 기다림
    ```java
    Socket socket = serverSocket.accept();
    ```
    - accept() 메소드는 접속 요청이 오면 접속 후 새 Socket 객체 반환
    - 접속 후 새로 만들어진 Socket 객체를 통해 클라이언트와 통신

- 네트워크 입출력 스트림 생성
    ```java
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    ```
    - Socket 객체의 getInputStream()과 getOutputStream() 메소드를 이용하여 입출력 데이터 스트림 생성

**서버 - 클라이언트 채팅 프로그램 만들기**
- 간단한 채팅 프로그램
    - 서버와 클라이언트가 1:1로 채팅
    - 클라이언트와 서버가 서로 한번씩 번갈아 가면서 문자열 전손
        - 문자열 끝에 "\n"을 덧붙여 보내고 라인 단위로 수신
    - 클라이언트가 bye를 보내면 프로그램 종료

<hr/>

## 6월 7일 강의
> 내용 정리

**스윙 컴포넌트 그리기, paintComponent()**
- 스윙의 페인팅 기본
    - 모든 컴포넌트는 자신의 모양을 스스로 그린다
    - 컨테이너는 자신을 그린 후 그 위에 자식 컴포넌트들에게 그리기 지시
    - 모든 스윙 컴포넌트는 자신의 모양을 그리는 paintComponent() 메소드 보유

- public void paintComponent(Graphics g)
    - 스윙 컴포넌트가 자신의 모양을 그리는 메소드
    - JComponent의 메소드: 모든 스윙 컴포넌트가 이 메소드를 오버라이딩함
    - 언제 호출되는가?
        - 컴포넌트가 그려져야 하는 시점마다 호출
        - 크기가 변경되거나 위치가 변경되거나, 컴포넌트가 가려졌던 것이 사라지는 등
    - 매게변수인 Graphics 객체
        - 그래픽 컨텍스트: 컴포넌트 그리기에 필요한 도구를 제공하는 객체
        - 자바 플랫폼에 의해 공급
        - 색 지정, 도형 그리기, 클리핑, 이미지 그리기 등의 메소드 제공

**paintComponent()의 오버라이딩과 JPanel**
- paintComponent(Graphics g)의 오버라이딩
    - 개발자가 JComponent를 상속받아 새로운 컴포넌트 설계
    - 기존 컴포넌트의 모양에 변화를 주고자 할 때
        ```java
        class MComponent extends JXXX {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ~ 필요한 그리기 코드 작성
            }
        }
        ```

- JPanel
    - 비어 있는 컨테이너
    - 개발자가 다양한 GUI를 창출할 수 있는 캔버스로 적립
    - JPanel을 상속받아 개발자 임의의 모양을 가지는 패널로 많이 사용

**그래픽 기반 GUI 프로그래밍**
- 그래픽 기반 GUI 프로그래밍
    - 스윙 컴포넌트에 의존하지 않고 선, 원 이미지 등을 이용하여 직접 화면을 구성하는 방법
    - 그래픽 기반 GUI 프로그래밍의 학습이 필요한 이유
        - 컴포넌트의 한계를 극복하고 차트, 게임 등 자유로운 컨텐츠 표현
        - 그래픽은 컴포넌트에 비해 화면 출력 속도가 빠름
        - 스윙 컴포넌트들로 모두 그래픽으로 작성되어 있어, 그래픽에 대한 학습은 자바 GUI의 바탕 기술을 이해하는데 도움
        - 그래픽을 이용하여 개발자 자신만의 컴포넌트 개발

    
- 자바의 그래픽(Graphics) 좌표 시스템

**Graphics와 문자열 출력**
- Graphics의 기능
    - 색상 선택하기
    - 문자열 그리기
    - 도형 그리기
    - 도형 칠하기
    - 이미지 그리기
    - 클리핑

- 문자열 출력을 위한 Graphics 메소드
    ```java
    void drawString(String str, int x, int y)
    // str 문자열을 (x, y) 영역에 그림, 현재 Graphics에 설정된 색과 폰트로 문자열 출력
    ```

**그래픽의 색과 폰트**
- 색: Color 클래스
    - 자바의 색: r(Red), g(Green), b(Blue) 성분으로 구성, 각 성분은 0 ~ 255(8비트) 범위의 정수
        ```java
        Color(int r, int g, int b) // r, g, b 값으로 sRGB 색 생성
        Color(int rgb) // rgb는 32비트의 정수이지만 하위 24비트만 유효. 즉, 0x00rrggbb로 표현, 각 바이트가 r, g, b의 색 성분
        ```
    - 예) 빨간색: new Color(255, 0, 0), 초록색: new Color(0x0000ff00), 노란색: Color.YELLOW

- 폰트: Font 클래스
    ```java
    Font(String fontFace, int style, int size)
    // fontFace: "고딕체", "Ariel" 등과 같은 폰트 이름
    // style: Font.BOLD, Font.ITALIC, Font.PLAIN 중 한 값으로 문자의 스타일
    // size: 픽셀 단위의 문자 크기
    ```

- Graphics에 색과 폰트 설정
    ```java
    void setColor(Color color) // 그래픽 색을 color로 설정, 그리기 시에 색으로 이용
    void setFont(Font font) // 그래픽 폰트를 font로 설정, 문자열 출력 시 폰트로 이용
    ```

**도형 그리기와 칠하기**
- 도형 그리기
    - 선, 타원, 사각형, 둥근 모서리 사각형, 원호, 폐 다각형 그리기
    - 선의 굵기 조절할 수 없음
        ```java
        void drawLine(int x1, int y1, int x2, int y2)
            // (x1, y1)에서 (x2, y2)까지 선을 그린다
        void drawOval(int x, int y, int w, int h)
            // (x, y)에서 w x h 크기의 사각형에 내접하는 타원을 그린다
        void drawRect(int x, int y, int w, int h)
            // (x, y)에서 w x h 크기의 사각형을 그린다
        void drawRoundRect(int x, int y, int w, int h, int arcWidth, int arcHeight)
        * arcWidth: 모서리 원의 수형 반지름
        * arcHeight: 모서리 원의 수직 반지름
        // (x, y)에서 w x h 크기의 사각형을 그리되, 4개의 모서리는 arcWidth와 arcHeight를 이용하여 원호로 그린다.
        ```

- 도형 칠하기
    - 도형을 그리고 내부를 칠하는 기능
        - 도형의 외곽선과 내부를 따로 칠하는 기능 없음
    - 도형 칠하기를 위한 메소드
        - 그리기 메소드 명에서 draw 대신 fill로 이름 대치하면 됨. fillRect(), fillOval() 등

**Graphics의 원호와 폐다각형 그리기 메소드**
```java
void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle)
* startAngle: 원호의 시작 각도
* arcAngle: 원호 각도
    /* (x, y)에서 w x h 크기의 사각형에 내접하는 원호를 그린다. 3시 방향이 0도의 기점이다.
    startAngle 지점에서 arcAngle 각도 만큼 원호를 그린다. arcAngle이 양수이면 반시계 방향, 음수이면 시계 방향으로 그린다. */
void drawPolygon(int []x, int []y, int n)
    /* x, y 배열에 저장된 점들 중 n개를 연결하는 폐다각형을 그린다.
    (x[0], y[0]), (x[1], y[1]) ~ (x[n-1], y[n-1]), (x[0], y[0])의 점들을 순서대로 연결한다.
```

**스윙에서 이미지를 그리는 2가지 방법**
1. JLabel을 이용한 이미지 그리기
    ```java
    ImageIcon image = new ImageIcon("image/apple.jpg");
    JLabel label = new JLabel(image);
    panel.add(label);
    ```
    - 장점: 이미지 그리기 간편 용이
    - 단점: 이미지의 원본 크기대로 그리므로 이미지 크기 조절 불가

2. Graphics의 drawImage()로 이미지 출력
    - 장점: 이미지 일부분 등 이미지의 원본 크기와 다르게 그리기 가능
    - 단점: 컴포넌트로 관리할 수 없음, 이미지의 위치나 크기 등을 적절히 조절하는 코딩 필요

**repaint()**
- repaint()
    - 모든 컴포넌트가 가지고 있는 메소드
    - 자바 플랫폼에게 컴포넌트 그리기를 강제 지시하는 메소드
    - repaint()를 호출하면, 자바 플랫폼이 컴포넌트의 paintComponent() 호출
        ```java
        component.repaint()
        ```

- repaint()의 호출이 필요한 경우
    - 개발자가 컴포넌트를 다시 그리고자 하는 경우
        - 프로그램에서 컴포넌트의 모양과 위치를 변경하고 바로 화면에 반영시키고자 하는 경우
        - 컴포넌트가 다시 그려져야 그 때 변경된 위치에 변경된 모양으로 출력됨
        - repaint()는 자바 플랫폼에게 지금 당장 컴포넌트를 다시 그리도록 지시함

- 부모 컴포넌트부터 다시 그리는 것이 좋음
    - 컴포넌트 repaint()가 불려지면
        - 이 컴포넌트는 새로운 위치에 다시 그려지지만 이전의 위치에 있던 자신의 모양이 남아 있음
    - 부모 컴포넌트의 repaing()를 호출하면
        - 부모 컨테이너의 모든 내용을 지우고 자식을 다시 그리기 때문에 컴포넌트의 이전 모양이 지워지고 새로 변경된 크기나 위치에 그려짐
            ```java
            component.getParent().repaint();
            ```

**멀티태스킹(multi-tasking) 개념**
- 멀티태스킹
    - 여러 개의 작업(태스크)이 동시에 처리되는 것

**스레드와 운영체제**
- 스레드(thread)
    - 운영체제에 의해 관리되는 하나의 작업 혹은 태스크
    - 스레드와 태스크(혹은 작업)은 바꾸어 사용해도 무관

- 멀티스레딩(multi-threading)
    - 여러 스레드를 동시에 실행시키는 응용프로그램을 작성하는 기법

- 스레드 구성
    - 스레드 코드
        - 작업을 실행하기 위해 작성한 프로그램 코드
        - 개발자가 작성
    - 스레드 정보
        - 스레드 명, 스레드 ID, 스레드의 실행 소요 시간, 스레드의 우선 순위 등
        - 운영체제가 스레드에 대해 관리하는 정보

**멀티태스킹과 멀티스레딩**
- 멀티태스킹 구현 기술
    - 멀티프로세싱(multi-processing)
        - 하나의 응용프로그램이 여러 개의 프로세스를 생성하고, 각 프로세스가 하나의 작업을 처리하는 기법
        - 각 프로세스 독립된 메모리 영역을 보유하고 실행
        - 프로세스 사이의 문맥 교환에 따른 과도한 오버헤드와 시간 소모의 문제점
    - 멀티스레딩(multi-threading)
        - 하나의 응용프로그램이 여러 개의 스레드를 생성하고, 각 스레드가 하나의 작업을 처리하는 기법
        - 하나의 응용프로그램에 속한 스레드는 변수 메모리, 파일 오픈 테이블 등 자원으로 공유하므로, 문맥 교환에 따른 오버헤드가 매우 작음
        - 현재 대부분의 운영체제가 멀티스레딩을 기본으로 하고 있음

**자바 스레드(Thread)와 JVM**
- 자바 스레드
    - 자바 가상 기계(JVM)에 의해 스케줄되는 실행 단위의 코드 블럭
    - 스레드의 생명 주기는 JVM에 의해 관리됨: JVM은 스레드 단위로 스케줄링

- JVM과 자바의 멀티스레딩
    - 하나의 JVM은 하나의 자바 응용프로그램만 실행
        - 자바 응용프로그램이 시작될 때 JVM이 함께 실행됨
        - 자바 응용프로그램이 종료하면 JVM도 함계 종료함
    - 응용프로그램은 하나 이상의 스레드로 구성 가능

**자바 스레드 만들기**
- 스레드 만드는 2가지 방법
    1. java.lang.Thread 클래스를 상속받아 스레드 작성
    2. java.lang.Runnable 인터페이스를 구현하여 스레드 작성

**Thread 클래스를 상속받아 스레드 만들기(2)**
- Thread를 상속받아 run() 오버라이딩
    - Thread 클래스 상속. 새 클래스 작성
    - run() 메소드 작성
        - run() 메소드를 스레드 코드라고 부름
        - run() 메소드에서 스레드 실행 시작
```java
class TimerThread extends Thread {
    ~

    @Override
    public void run() { // run() 오버라이딩
        ~
    }
}
```

- 스레드 객체 생성
    - 생성된 객체는 필드와 메소드를 가진 객체일 뿐 스레드로 작동하지 않음
```java
TimerThread th = new TimerThread();
```

- 스레드 시작
    - start() 메소드 호출
        - 스레드로 작동 시작
        - 스레드 객체의 run()이 비로소 실행
        - JVM에 의해 스케줄되기 시작함
```java
th.start();
```

**Thread를 상속받아 1초 단위로 초 시간을 출력하는 TimerThread 스레드 작성 사례**
```java
class TimerThread extends Thread {
    int n = 0;

    @Override
    public void run() {
        while(true) { // 무한 루프를 실행한다.
            System.out.println(n);
            n++;

            try {
                sleep(1000); // 1초 동안 잠을 잔 후 깨어난다.
            }

            catch(InterruptedException e){return;}
        }
    }
}
```
```java
public class TestThread {
    public static void main(String[] args) {
        TimerThread th = new TimerThread();
        th.start();
    }
}
```

**Runnable 인터페이스로 스레드 만들기**
- Runnable 인터페이스 구현하는 새 클래스 작성
    - run() 메소드 구현
        - run() 메소드를 스레드 코드라고 부름
        - run() 메소드에서 스레드 실행 시작
```java
class TimeRunnable implements Runnable {
    ~

    @Override
    public void run() { // run() 메소드 구현
        ~
    }
}
```

- 스레드 객체 생성
```java
Thread th = new Thread(new TimerRunnable());
```

- 스레드 시작
    - start() 메소드 호출
        - 스레드로 작동 시작
        - 스레드 객체의 run()이 비로소 실행
        - JVM에 의해 스케줄되기 시작함
```java
th.start();
```

**main 스레드**
- main 스레드
    - JVM이 응용프로그램을 실행할 때 디폴트로 생성되는 스레드
        - main() 메소드 실행 시작
        - main() 메소드가 종료하면 main 스레드 종료

**스레드 종료와 타 스레드 강제 종료**
- 스스로 종료
    - run() 메소드 리턴
```java
class TimerThread extends Thread {
    int n = 0;

    @Override
    public void run() {
        while(true) {
            System.out.println(n); // 화면에 카운트 값 출력
            n++;

            try {
                sleep(1000);
            }

            catch(InterruptedException e) {
                return; // 예외를 받고 스스로 리턴하여 종료
            }
        }
    }
}
```

- 타 스레드에서 강제 종료
    - interrupt() 메소드 사용
```java
public static void main(String [] args) {
    TimerThread th = new TimerThread();
    th.start();

    th.interrupt(); // TimerThread 강제 종료
}
```

**스레드 동기화(Thread Synchronization)**
- 멀티스레드 프로그램 작성시 주의점
    - 다수의 스레드가 공유 데이터에 동시에 접근하는 경우
        - 공유 데이터의 값에 예상치 못한 결과 발생 가능

- 스레드 동기화
    - 동기화란?
        - 스레드 사이의 실행순서 제어, 공유데이터에 대한 접근을 원활하게 하는 기법
    - 멀티스레드의 공유 데이터의 동시 접근 문제 해결
        - 방법 1) 공유 데이터를 접근하는 모든 스레드의 한 줄 세우기
        - 방법 2) 한 스레드가 공유 데이터에 대한 작업을 끝낼 때까지 다른 스레드가 대기하도록 함

- 자바의 스레드 동기화 방법 - 2가지
    - synchroized 키워드로 동기화 블럭 지정
    - wait()-notify() 메소드로 스레드의 실행 순서 제어

**synchronized 블럭 지정**
- synchronized 키워드
    - 스레드가 독점적으로 실행해야 하는 부분(동기화 코드)을 표시하는 키워드
        - 임계 영역(critical section) 표기 키워드
    - synchronized 블럭 지정 방법
        - 메소드 전체 혹은 코드 블럭

- synchronized 블럭이 실행될 때
    - 먼저 실행한 스레드가 모니터 소유
        - 모니터랑 해당 객체를 독점적으로 사용할 수 있는 권한
    - 모니터를 소유한 스레드가 모니터를 내놓을 때까지 다른 스레드 대기

```java
// synchronized 메소드
synchronized void print(String text) { // 동기화 메소드
    ~
    for(int i = 0; i < text.length(); i++) // text의 각 문자 출력
        System.out.print(text.chatAt(i));
    ~
}
```
```java
// synchronized 코드 블럭
void execute(String text) {
    ~
    synchronized(this) { // 동기화 코드 블럭
        ~
        for(int i = 0; i < text.length(); i++)
            System.out.print(text.charAt(i));
            ~
    }
}
```

**wait()-notify()**
- wait()-notify()가 필요한 경우
    - 공유 데이터로 두 개 이상의 스레드가 데이터를 주고 받을 때
        - producer-consumer 문제

- 동기화 메소드
    - wait(): 다른 스레드가 notify()를 불러줄 때까지 기다린다.
    - notify(): wait()를 호출하여 대기중인 스레드를 깨운다.
        - wait(), notify()는 Object 메소드

<hr/>

## 5월 31일 강의
> 내용 정리

**자바의 GUI 프로그래밍 방법**
- 자바의 GUI 프로그래밍 방법 2종류
    1. 컴포넌트 기반 GUI 프로그래밍
        - 스윙 컴포넌트를 이용하여 쉽게 GUI를 구축
        - 자바에서 제공하는 컴포넌트의 한계를 벗어나지 못함
    
    2. 그래픽 기반 GUI 프로그래밍
        - 그래픽을 이용하여 GUI구축
        - 개발자가 직접 그래픽으로 화면을 구성하는 부담
        - 독특한 GUI를 구성할 수 있는 장점
        - GUI 처리의 실행 속도가 빨라, 게임 등에 주로 이용

**스윙 컴포넌트의 공통 메소드, JComponent의 메소드**
- JComponent
    - 스윙 컴포넌트는 모두 상속받는 슈퍼 클래스, 추상 클래스
    - 스윙 컴포넌트들이 상속받는 공통 메소드의 상수 구현
    - JComponent의 주요 메소드 사례

    ```java
    컴포넌트의 모양과 관련된 메소드
    void setForeGround(Color) // 전경색 설정
    void setBackground(Color) // 배경색 설정
    void setOpaque(boolean) // 불투명성 설정
    void setFont(Font) // 폰트 설정
    Font getFont() // 폰트 리턴
    ```

    ```java
    컴포넌트의 위치와 크기에 관련된 메소드
    int getWidth() // 폭 리턴
    int getHeight() // 높이 리턴
    int getX() // x 좌표 리턴
    int getY() // y 좌표 리턴
    Point getLocationOnScreen() // 스크린 좌표상에서의 컴포넌트 좌표
    void setLocation(int, int) // 위치 지정
    void setSize(int, int) // 크기 지정
    ```

    ```java
    컴포넌트의 상태와 관련된 메소드
    void setEnabled(boolean) // 컴포넌트 활성화 / 비활성화
    void setVisible(boolean) // 컴포넌트 보이기 / 숨기기
    boolean isVisible() // 컴포넌트의 보이는 상태 리턴
    ```

    ```java
    컨테이너를 위한 메소드
    Component add(Component) // 자식 컴포넌트 추가
    void remove(Component) // 자식 컴포넌트 제거
    void removeAll() // 모든 자식 컴포넌트 제거
    Component[] getComponents() // 자식 컴포넌트 배열 리턴
    Container getParent() // 부모 컨테이너 리턴
    Container getTopLeveLAncestor() // 최상위 부모 컨테이너 리턴
    ```

**JLabel로 문자열과 이미지 출력**
- JLabel의 용도
    - 문자열이나 이미지를 화면에 출력하기 위한 목적

- 레이블 생성
    ```java
    JLabel() // 빈 레이블
    JLabel(Icon image) // 이미지 레이블
    JLabel(String text) // 문자열 레이블
    JLabel(String text, Icon image, int hAlign) // 문자열과 이미지 모두 가진 레이블
    // hAlign: 수평 정렬 값으로 SwingConstants.LEFT, SwingConstant.RIGHT, SwingConstants.CENTER 중 하나
    ```

**레이블 생성 예**
- 문자열 레이블 생성
    ```java
    JLabel textLabel = new JLabel("사랑합니다");
    ```

- 이미지 레이블 생성
    - 이미지 파일로부터 이미지를 읽기 위해 ImageIcon 클래스 사용
    - 다룰 수 있는 이미지: png, git, jpg
        - sunset.jpg의 경로명이 "images/sunset.jpg"인 경우
            ```java
            ImageIcon image = new ImageIcon("images/sunset.jpg");
            JLabel imageLabel = new JLabel(image);
            ```

- 수평 정렬 값을 가진 레이블 컴포넌트 설정
    - 수평 정렬로, 문자열과 이미지를 모두 가진 레이블
        ```java
        ImageIcon image = new ImageIcon("images/sunset.jpg");
        JLabel label = new JLabel("사랑합니다", image, SwingConstants.CENTER);
        ```

**JButton으로 버튼 만들기**
- JButton의 용도
    - 버튼 모양의 컴포넌트. 사용자들로부터 명령을 입력 받기 위한 목적
    - 버튼은 클릭될 때 Action 이벤트 발생

- 버튼 생성
    ```java
    JButton() // 빈 버튼
    JButton(Icon image) // 이미지 버튼
    JButton(String text) // 문자열 버튼
    JButton(String text, Icon image) // 문자열과 이미지 모두 가진 버튼
    ```

    - "hello" 문자열을 가진 버튼 생성 예
        ```java
        JButton btn = new JButton("hello");
        ```

**이미지 버튼 만들기**
- 하나의 버튼에 3개의 이미지 등록
    - 마우스 조작에 따라 3개의 이미지 중 적잘한 이미지 자동 출력

- 3개의 버튼 이미지
    1. normalIcon
        - 버튼의 보통 상태(디폴트) 때 출력되는 이미지
        - 생성자에 이미지 아이콘 전달 혹은 JButton의 setIcon(normalIcon);

    2. rolloverIcon
        - 버튼에 마우스가 올라갈 때 출력되는 이미지
        - 이미지 설정 메소드: JButton의 setRolloverIcon(rolloverIcon);

    3. pressedIcon
        - 버튼을 누른 상태 때 출력되는 이미지
        - 이미지 설정 메소드: JButton의 setPressedIcon(pressedIcon);

**이미지 버튼에 이미지 설정**
- 이미지 로딩
    - 필요한 이미지 로딩: new ImageIcon(이미지 경로명);
    - 사례)
        ```java
        ImageIcon normalIcon = new ImageIcon("images/normalIcon.gif");
        ImageIcon rolloverIcon = new ImageIcon("images/rolloverIcon.gif");
        ImageIcon pressedIcon = new ImageIcon("images/pressedIcon.gif");
        ```

- 버튼에 이미지 등록
    - JButton의 메소드를 호출하여 이미지 등록
    - 사례)
        ```java
        JButton button = new JButton("테스트버튼", normalIcon); // normalIcon 달기
        button.setRolloverIcon(rolloverIcon); // rolloverIcon 달기
        button.setPressedIcon(pressedIcon); // pressedIcon 달기
        ```

    - 실행 중에 normal 이미지(디폴트 이미지) 교체 사례
        ```java
        ImageIcon newIcon = new ImageIcon("images/newIcon.gif");
        button.setIcon(newIcon); // 디폴트 이미지 변경
        ```

**체크박스에 Item 이벤트 처리**
- Item 이벤트
    - 체크 박스의 선택 상태에 변화가 생길 때 발생하는 이벤트
        - 사용자가 마우스나 키보드로 체크박스를 선택 / 해제할 때
        - 프로그램에서 체크박스를 선택 / 해제하여 체크 상태에 변화가 생길 때
            ```java
            JCheckBox c = new JCheckBox("사과");
            c.setSelected(true); // 선택 상태로 변경
            ```
    - 이벤트가 발생하면 ItemEvent 객체 생성
    - ItemListener 리스너를 이용하여 이벤트 처리

- ItemListener 리스너의 추상 메소드
    ```java
    void itemStateChanged(ItemEvent e) // 체크박스의 선택 상태가 변하는 경우 호출
    ```

- ItemEvent의 주요 메소드
    ```java
    int getStateChange() // 체크박스가 선택된 경우 ItemEvent.SELECTED를, 해제된 경우 ItemEvent.DESELECTED를 리턴한다.
    Object getItem() // 이벤트를 발생시킨 아이템 객체를 리턴한다. 체크박스의 경우 JCheckBox 컴포넌트의 레퍼런스를 리턴한다.
    ```

**JRadioButton으로 라디오버튼 만들기**
- JRadioButton의 용도
    - 버튼 그룹을 형성하고, 그룹에 속한 버튼 중 하나만 선택되는 라디오버튼
    - 체크박스와의 차이점
        - 체크 박스는 각각 선택 / 해제가 가능하지만, 라디오버튼은 그룹에 속한 버튼 중 하나만 선택

- 라디오버튼 생성
    ```java
    JRadioButton() // 빈 라디오버튼
    JRadioButton(Icon image) // 이미지 라디오버튼
    JRadioButton(Icon image, boolean selected) // 이미지 라디오버튼
    JRadioButton(String text) // 문자열 라디오버튼
    JRadioButton(String text, boolean selected) // 문자열 라디오버튼
    JRadioButton(String text, Icon image) // 문자열과 이미지를 가진 라디오버튼
    JRadioButton(String text, Icon image, boolean selected) // 문자열과 이미지를 가진 라디오버튼
    // selected: true면 선택 상태로 초기화
    ```

**라디오버튼 생성 및 Item 이벤트 처리**
- 버튼 그룹과 라디오버튼 생성 과정
    1. 버튼 그룹 객체 생성
    2. 라디오버튼 생성
    3. 라디오버튼을 버튼 그룹에 삽입
    4. 라디오버튼을 컨테이너에 삽입
        ```java
        ButtonGroup group = new ButtonGroup();

        JRadioButton apple = new JRadioButton("사과");
        JRadioButton pear = new JRadioButton("배");
        JRadioButton cherry = new JRadioButton("체리");

        group.add(apple);
        group.add(pear);
        group.add(cherry);

        container.add(apple);
        container.add(pear);
        container.add(cherry);
        ```

- 라디오버튼에 Item 이벤트 처리: ItemListener 리스너 이용
    - 라디오버튼이 선택 / 해제되어 상태가 달리지면, Item 이벤트 발생
        - 사용자가 마우스나 키보드로 선택 상태를 변경할 때
        - 프로그램에서 JRadioButton의 setSelected()를 호출하여 선택 상태를 변경할 때

**JTextField로 한 줄 입력 창 만들기**
- JTextField
    - 한 줄의 문자열을 입력 받는 창(텍스트필드)
        - 텍스트 입력 도중 <Enter>키가 입력되면 Action 이벤트 발생
        - 입력 가능한 문자 개수와 입력 창의 크기는 서로 다름

- 텍스트필드 생성
    ```java
    JTextField() // 빈 텍스트필드
    JTextField(int cols) // 입력 창의 열의 개수가 cols개인 텍스트필드
    JTextField(String text) // text 문자열로 초기화된 텍스트필드
    JTextField(String text, int cols) // 입력 창의 열이 개수는 cols개이고 text 문자열로 초기화된 텍스트 필드
    ```
    - "컴퓨터공학과"로 초깃값을 가지는 텍스트필드 생성 예
        ```java
        JTextField tf2 = new JTextField("컴퓨터공학과");
        ```

**TextArea로 여러 줄의 입력 창 만들기**
- JTextArea
    - 여러 줄의 문자열을 입력받을 수 있는 창(텍스트영역)
        - 스크롤바를 지원하지 않는다.
        - JScrollPane 객체에 삽입하여 스크롤바 지원받음

- 생성자
    ```java
    JTextArea() // 빈 텍스트영역
    JTextArea(int rows, int cols) // 입력 창이 rows x cols개의 문자 크기인 텍스트영역
    JTextArea(String text) // text 문자열로 초기화된 텍스트영역
    JTextArea(Stirng text, int rows, int cols) // 입력 창이 rows x cols개의 문자 크기이며 text 문자열로 초기화된 텍스트영역
    ```

**JList<E>**
- JList<E>
    - 하나 이상의 아이템을 보여주고 아이템을 선택하도록 하는 리스트
    - Java 7부터 제네릭 리스트로 바뀜
        - <E>에 지정된 타입의 객체만 저장하는 리스트
    - JScrollPane에 JList<E>를 삽입하여 스크롤 가능

- 리스트 생성
    ```java
    JList<E>() // 빈 리스트
    JList<E>(Vector listData) // 벡터로부터 아이템을 공급받는 리스트
    JList<E>(Object[] listData) // 배열로부터 아이템을 공급받는 리스트
    ```

    - 예) 9개의 과일 이름 문자열이 든 리스트 만들기
        ```java
        String[] fruits = {"apple", "banana", "kiwi", "mango", "pear", "peach", "berry", "strawberry", "blackberry"};

        JList<String> strList = new JList<String>(fruits);
        ```

**JComboBox**
- JComboBox<E>
    - 텍스트필드와 버튼, 그리고 드롭다운 리스트로 구성되는 콤보박스
    - 드롭다운 리스트에서 선택한 것이 텍스트필드에 나타남

- 콤보박스 생성
    ```java
    JComboBox<E>() // 빈 콤보박스
    JComboBox<E>(Vector listData) // 벡터로부터 아이템을 공급받는 콤보박스
    JComboBox<E>(Object[] listData) // 배열로부터 아이템을 공급받는 콤보박스
    ```

    - 예) 텍스트를 아이템으로 가진 콤보박스 생성
        ```java
        String[] fruits = {"apple", "banana", "kiwi", "mango", "pear", "peach", "berry", "strawberry", "blackberry"};

        JComboBox<String> combo = new JComboBox<String>(fruits);
        ```

**메뉴 구성**
- 메뉴 만들기에 필요한 스윙 컴포넌트
    - 메뉴아이템 - JMenuItem
    - 메뉴 - JMenu
        - 여러 개의 메뉴아이템을 가짐
    - 메뉴바 - JMenuBar
        - 여러 개의 메뉴를 붙이는 바이며, 프레임에 부탁됨
    - 분리선
        - 메뉴아이템 사이의 분리선으로 separator라고 부름
        - JMenu의 addSeparator()를 호출하여 삽입함

**메뉴 만드는 과정**
1. JMenuBar 컴포넌트 생성
2. JMenu 컴포넌트를 생성하여 JMenuBar에 붙인다.
3. JMenuItem 컴포넌트를 생성하여 JMenu에 붙인다, 여러 개의 메뉴와 메뉴 아이템을 생성한다.
4. JMenuBar 컴포넌트를 JFrame에 붙인다.
    ```java
    JMenuBar mb = new JMenuBar();

    JMenu screenMenu = new JMenu("Screen");
    mb.add(screenMenu);

    screenMenu.add(new JMenuItem("Load"));
    screenMenu.add(new JMenuItem("Hide"));
    screenMenu.add(new JMenuItem("ReShow"));
    screenMenu.addSeparator();
    screenMenu.add(new JMenuItem("EXIT"));

    frame.setJMenuBar(mb);
    ```

**메뉴아이템에 Action 이벤트 달기**
- 메뉴아이템을 클릭하면 Action 발생
    - 메뉴아이템은 사용자로부터의 지시나 명령을 받는데 사용
    - ActionListener 인터페이스로 리스너 작성
    - 각 메뉴아이템마다 이벤트 리스너 설정

    - 예) Load 메뉴아이템에 Action 리스너를 작성하는 경우
        ```java
        JMenuItem item = new JMenuItem("Load");
        item.addActionListener(new MenuActionListener()); // 메뉴아이템에 Action 리스너 설정
        screenMenu.add(item);

        class MenuActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                // 사용자가 Load 메뉴아이템을 선택하는 경우 처리할 작업 구현
                ~
            }
        }
        ```

**다이얼로그 종류**
1. 팝업 다이얼로그, JOptionPane
2. 확인 다이얼로그
3. 메시지 다이얼로그

<hr/>

## 5월 24일 강의
> 내용 정리

**이벤트 기반 프로그래밍**
- 이벤트 기반 프로그래밍(Event Driven Programming)
    - 이벤트의 발생에 의해 프로그램 흐름이 결정되는 방식
        - 이벤트가 발생하면 이벤트를 처리하는 루틴(이벤트 리스너) 실행
        - 실행될 코드는 이벤트의 발생에 의해 전적으로 결정

    - 반대되는 개념: 배치 실행(batch programming)
        - 프로그램이 개발자가 프로그램의 흐름을 결정하는 방식

    - 이벤트 종류
        - 사용자의 입력: 마우스 드래그, 마우스 클릭, 키보드 누름 등
        - 센서로부터의 입력, 네트워크로부터 데이터 송수신
        - 다른 응용 프로그램이나 다른 스레드로부터의 메시지
    
- 이벤트 기반 응용 프로그램의 구조
    - 각 이벤트마다 처리하는 리스너 코드 보유

- GUI 응용 프로그램은 이벤트 기반 프로그램으로 작성됨
    - GUI 라이브러리 종류
        - C++ 의 MFC, C# GUI, Visual Basic, X Window, Android 등
        - 자바의 AWT와 Swing

**자바 스윙 프로그램에서 이벤트 처리 과정**
- 이벤트가 처리되는 과정
    - 이벤트 발생
        - 예: 마우스의 움직임 혹은 키보드 입력

    - 이벤트 객체 생성
        - 현재 발생한 이벤트에 대한 정보를 가진 객체

    - 응용 프로그램에 작성된 이벤트 리스너 찾기

    - 이벤트 리스너 실행
        - 리스너에 이벤트 객체 전달
        - 리스너 코드 실행

**이벤트 객체**
- 이벤트 객체
    - 발생한 이벤트에 관한 정보를 가진 객체
    - 이벤트 리스너에 전달됨
        - 이벤트 리스너 코드가 발생한 이벤트에 대한 상황을 파악할 수 있게 함

- 이벤트 객체가 포함하는 정보
    - 이벤트 종류와 이벤트 소스
    - 이벤트가 발생한 화면 좌표 및 컴포넌트 내 좌표
    - 이벤트가 발생한 버튼이나 메뉴 아이템의 문자열
    - 클릭된 마우스 버튼 번호 및 마우스의 클릭 횟수
    - 키의 코드 값과 문자 값
    - 체크박스, 라디오버튼 등과 같은 컴포넌트에 이벤트가 발생하였다면 체크 상태

- 이벤트 소스를 알아 내는 메소드
    - Object getSource()
        - 발생한 이벤트의 소스 컴포넌트 리턴
        - Object 타입으로 리턴하므로 캐스팅하여 사용
        - 모든 이벤트 객체에 대해 적용

**리스터 인터페이스**
- 이벤트 리스너
    - 이벤트를 처리하는 자바 프로그램 코드, 클래스로 작성

- 자바는 다양한 리스너 인터페이스 제공
    - 예) ActionListener 인터페이스 - 버튼 클릭 이벤트를 처리하기 위한 인터페이스
        ```java
        interface ActionListener { // 아래 메소드를 개발자가 구현해야 함
            public void actionPerformed(ActionEvent e); // Action 이벤트 발생시 호출됨
        }
        ```

    - 예) MouseListener 인터페이스 - 마우스 조작에 따른 이벤트를 처리하기 위한 인터페이스
        ```java
        interface MouseListener { // 아래의 5개 메소드를 개발자가 구현해야 함
            public void mousePressed(MouseEvent e); // 마우스 버튼이 눌러지는 순간 호출
            public void mouseReleased(MouseEvent e); // 눌러진 마우스 버튼이 떼어지는 순간 호출
            public void mouseClicked(MouseEvent e); // 마우스가 클릭되는 순간 호출
            public void mouseEntered(MouseEvent e); // 마우스가 컴포넌트 위에 올라가는 순간 호출
            public void mouseExited(MouseEvent e); // 마우스가 컴포넌트 위에서 내려오는 순간 호출
        }
        ```

- 사용자의 이벤트 리스너 작성
    - 자바의 리스너 인터페이스(interface)를 상속받아 구현
    - 리스너 인터페이스의 모든 추상 메소드 구현

**이벤트 리스너 작성 과정 사례**
1. 이벤트와 이벤트 리스너 선택
    - 버튼 클릭을 처리하고자 하는 경우
        - 이벤트: Action 이벤트, 이벤트 리스너: ActionListener

2. 이벤트 리스너 클래스 작성: ActionListener 인터페이스 구현
    ```java
    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();

            if(b.getText().equals("Action"))
                b.setText("액션");

            else
                b.setText("Action");
        }
    }
    ```

3. 이벤트 리스너 등록
    - 이벤트를 받아 처리하고자 하는 컴포넌트에 이벤트 리스너 등록
    - component.addXXXListener(listener)
        - xxx: 이벤트 명, listener: 이벤트 리스너 객체
            ```java
            MyActionListener listener = new MyActionListener(); // 리스너 인스턴스 생성
            btn.addActionListener(listener); // 리스너 등록
            ```

**이벤트 리스너 작성 방법**
- 3가지 방법
    1. 독립 클래스로 작성
        - 이벤트 리스너를 완전한 클래스로 작성
        - 이벤트 리스너를 여러 곳에서 사용할 때 적합

    2. 내부 클래스(inner class)로 작성
        - 클래스 안에 멤버처럼 클래스 작성
        - 이벤트 리스너를 특정 클래스에서만 사용할 때 적합

    3. 익명 클래스(anonymous class)로 작성 
        - 클래스의 이름 없이 간단히 리스너 작성
        - 클래스 조차 만들 필요 없이 리스너 코드가 간단한 경우에 적합

**익명 클래스로 이벤트 리스너 작성**
- 익명 클래스(anonymous class): 이름 없는 클래스
    - (클래스 선언 + 인스턴스 생성)을 한번에 달성
        ```java
        new 익명 클래스의 슈퍼 클래스 이름(생성 인자들) {
            ~~~
            익명 클래스의 멤버 구현
            ~~~
        }
        ```
    
    - 간단한 리스너의 경우 익명 클래스 사용 추천
        - 메소드의 개수가 1, 2개인 리스너(ActionListener, ItemListener)에 대해 주로 사용

- ActionListener를 구현하는 익명의 이벤트 리스너 작성 예
    1. 이름을 가진 클래스를 작성하고 클래스 인스턴스를 생성하는 경우
        ```java
        // 이름을 가진 클래스 작성
        class MyActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                ~~~
            }
        }

        // 클래스 인스턴스 생성
        b.addActionListener(new MyActionListener());
        ```

    2. ActionListener를 상속받고 바로 메소드 작성, 동시에 new로 인스턴스를 생성하는 경우
        ```java
        // 클래스 작성과 동시에 인스턴스 생성
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ~~~
            }
        });
        ```

**어댑터 클래스**
- 이벤트 리스너 구현에 따른 부담
    - 리스너의 추상 메소드를 모두 구현해야 하는 부담
    - 예) 마우스 리스너에서 마우스가 눌러지는 경우(mousePressed())만 처리하고자 하는 경우에도 나머지 4개의 메소드를 모두 구현해야하는 부담

- 어댑터 클래스(Adapter)
    - 리스너의 모든 메소드를 단순 리턴하도록 만든 클래스(JDK에서 제공)
    - MouseAdapter 예
        ```java
        class MouseAdapter implements MouseListener, MouseMotionListener, MouseWheelListener {
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseDragged(MouseEvent e) {}
            public void mouseMoved(MouseEvent e) {}
            public void mouseWheelMoved(MouseWheelEvent e) {}
        }
        ```
    - 추상 메소드가 하나뿐인 리스너는 어댑터 없음
        - ActionAdapter, ItemAdapter 클래스는 존재하지 않음

**MouseListener 대신 MouseAdapter를 사용한 예**
```java
// MouseListener를 이용한 경우
JLabel la;
contentPane.addMouseListener(new MyMouseListener());
~~~

class MyMouseListener implements MouseListener {
    public void mousePressed(MouseEvnet e) {
        int x = e.getX();
        int y = e.getY();
        la.setLocation(x, y);
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
```

```java
// MouseAdapter를 이용한 경우
JLabel la;
contentPane.addMouseListener(new MyMouseAdapter());
~~~

class MyMouseAdapter extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        la.setLocation(x, y);
    }
}
```

**Key 이벤트와 포커스**
- 키 입력 시, 다음 세 경우 각각 Key 이벤트 발생
    1. 키를 누르는 순간
    2. 누른 키를 떼는 순간
    3. 누른 키를 떼는 순간(Unicode 키의 경우에만)

- 키 이벤트를 받을 수 있는 조건
    1. 모든 컴포넌트
    2. 현재 포커스(focus)를 가진 컴포넌트가 키 이벤트 독점

- 포커스(focus)
    1. 컴포넌트나 응용 프로그램이 키 이벤트를 독점하는 권한
    2. 컴포넌트에 포커스 설정 방법: 다음 2라인 코드 필요
        ```java
        component.setFocusable(true); // component가 포커스를 받을 수 있도록 설정
        component.requestFocus(); // component에 포커스 강제 지정
        ```

**KeyListener**
- 응용 프로그램에서 KeyListener를 상속받아 키 리스너 구현

- KeyListener의 3개 메소드
    ```java
    // 키 리스너(KeyListener)
    void keyPressed(KeyEvent e) {
        // 이벤트 처리 루틴
    }

    void keyReleased(KeyEvent e) {
        // 이벤트 처리 루틴
    }

    void keyTyped(KeyEvent e) {
        // 이벤트 처리 루틴
    }
    // 실행되는 순서: keyPressed() - keyTyped() - keyReleased()
    ```

- 컴포넌트에 키 이벤트 리스너 달기
    ```java
    component.addKeyListener(myKeyListener);
    ```

**유니코드(Unicode) 키**
- 유니코드 키의 특징
    1. 국제 산업 표준
    2. 전 세계의 문자를 컴퓨터에서 일관되게 표현하기 위한 코드 체계
    3. 문자들에 대해서만 키 코드 값 정의
        - A ~ Z, a ~ z, 0 ~ 9, !, @, & 등
    4. 문자가 아닌 키 경우에는 표준화된 키 코드 값 없음
        - <Function> 키, <Home> 키, <Up> 키, <Delete> 키, <Control> 키, <Shift> 키, <Alt> 키 등은 플랫폼에 따라 키 코드 값이 다를 수 있음

- 유니코드 키가 입력되는 경우
    - keyPressed(), keyTyped(), keyReleased()가 순서대로 호출

- 유니코드 키가 아닌 경우
    - keyPressed(), keyReleased()만 호출됨

**가상 키와 입력된 키 판별**
- KeyEvent 객체
    - 입력된 키 정보를 가진 이벤트 객체
    - KeyEvent 객체의 메소드로 입력된 키 판별

- KeyEvent 객체의 메소드로 입력된 키 판별
    - char KeyEvent.getKeyChar()
        1. 키의 유니코드 문자 값 리턴
        2. Unicode 문자 키인 경우에만 의미 있음
        3. 입력된 키를 판별하기 위해 문자 값과 비교하면 됨
        ```java
        // q 키를 누르면 프로그램 종료
        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar() == 'q')
                System.exit(0);
        }
        ```

    - int KeyEvent.getKeyCode()
        1. 유니코드 키 포함
        2. 모든 키에 대한 정수형 키 코드 리턴
        3. 입력된 키를 판별하기 위해 가상키(Virtual Key)값과 비교하여야 함
        4. 가상 키 값은 KeyEvent 클래스에 상수로 선언
        ```java
        // F5 키를 누르면 프로그램 종료
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_F5)
                System.exit(0);
        }
        ```

**가상 키(Virtual Key)**
- 가상 키는 KeyEvent 클래스에 상수로 선언

**Mouse 이벤트와 MouseListener, MouseMotionListener**
- Mouse 이벤트: 사용자의 마우스 조작에 따라 발생하는 이벤트
    - mouseClicked(): 마우스가 눌러진 위치에서 그대로 떼어질 때 호출
    - mouseReleased(): 마우스가 눌러진 위치에서 그대로 떼어지든 아니든 항상 호출
    - mouseDragged(): 마우스가 드래그되는 동안 계속 여러번 호출

- 마우스가 눌러진 위치에서 떼어지는 경우 메소드 호출 순서
    ```java
    mousePressed(), mouseReleased(), mouseClicked()
    ```

- 마우스가 드래그될 때 호출되는 메소드 호출 순서
    ```java
    mousePressed(), mouseDragged(), mouseDragged(), ... , mouseDragged(), mouseReleased()
    ```

**마우스 리스너 달기와 MouseEvent 객체 활용**
- 마우스 리스너 달기
    - 마우스 리스너는 컴포넌트에 다음과 같이 등록
        ```java
        component.addMouseListener(myMouseListener);
        ```
    - 컴포넌트가 마우스 무브(mouseMove())나 마우스 드래깅(mouseDragged())을 함께 처리하고자 하면, MouseMotion 리스너 따로 등록
        ```java
        component.addMouseMotionListener(myMouseMotionListener);
        ```

- MouseEvent 객체 활용
    - 마우스 포인터 위치, 컴포넌트 내 상대 위치
        - int getX(), int getY()
            ```java
            public void mousePressed(MouseEvent e) {
                int x = e.getX(); // 마우스가 눌러진 x 좌표
                int y = e.getY(); // 마우스가 눌러진 y 좌표
            }
            ```
    - 마우스 클릭 횟수
        - int getClickCount()
            ```java
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    ... // 더블클릭 처리 루틴
                }
            }
            ```

<hr/>

## 5월 17일 강의
> 내용 정리

**컨테이너와 배치 관리자**
<br/>
배치 관리자 대표 유형 4가지

1. FlowLayout 배치관리자
    - 컴포넌트가 삽입되는 순서대로 왼쪽에서 오른쪽으로 배치
    - 배치할 공간이 없으면 아래로 내려와서 반복한다

2. BorderLayout 배치관리자
    - 컨테이너의 공간을 동(EAST), 서(WEST), 남(SOUTH), 북(NORTH), 중앙(CENTER)의 5개 영역으로 나눔
    - 5개 영역 중 응용프로그램에서 지정한 영역에 컴포넌트 배치

3. GridLayout 배치관리자
    - 컨테이너를 프로그램에서 설정한 동일한 크기의 2차원 격자로 나눔
    - 컴포넌트는 삽입 순서대로 좌에서 우로, 다시 위에서 아래로 배치

4. CardLayout 배치관리자
    - 컨테이너의 공간에 카드를 쌓아 놓은 듯이 컴포넌트를 포개어 배치

java.awt 패키지에 구현되어 있음

**컨테이너의 디폴트 배치관리자**
- 컨테이너 생성시 자동으로 생성되는 배치관리자

AWT와 스윙 컨테이너 | 디폴트 배치관리자
:---: | :---:
`Window`, `JWindow` | `BorderLayout`
`Frane`, `JFrame` | `BorderLayout`
`Dialog`, `JDialog` | `BorderLayout`
`Panel`, `JPanel` | `FlowLayout`
`Applet`, `JApplet` | `FlowLayout`

**컨테이너에 새로운 배치관리자 설정**
- setLayout(LayoutManager lm) 메소드 호출
    - lm을 새로운 배치관리자로 설정

**FlowLayout 배치관리자**
- 배치방법
    - 컴포넌트를 컨테이너 내에 왼쪽에서 오른쪽으로 배치
        - 다시 위에서 아래로 순서대로 배치

```java
container.setLayout(new FlowLayout());
container.add(new JButton("add"));
container.add(new JButton("sub"));
container.add(new JButton("mul"));
container.add(new JButton("div"));
container.add(new JButton("Calculate"));
```

**FlowLayout의 생성자**
- 생성자
    - FlowLayout()
    - FlowLayout(int align, int hGap, int vGap)
        - align: 컴포넌트를 정렬하는 방법 지정, 왼쪽 정렬(FlowLayout.LEFT), 오른쪽 정렬(FlowLayout.RIGHT), 중앙 정렬(FlowLayout.CENTER(디폴트))
        - hGap: 좌우 두 컴포넌트 사이의 수평 간격, 픽셀 단위, 디폴트는 5
        - vGap: 좌우 두 컴포넌트 사이의 수직 간격, 픽셀 단위, 디폴트는 5

**BorderLayout 배치관리자**
- 배치방법
    - 컨테이너 공간을 5 구역으로 분할, 배치
        - 동, 서, 남, 북, 중앙

    - 배치 방법
        - add(Component comp, int index)
            - comp를 index의 공간에 배치

**BorderLayout 생성자와 add() 메소드**
- 생성자
    - BorderLayout()
    - BorderLayout(int hGap, int vGap)
        - hGap: 좌우 두 컴포넌트 사이의 수평 간격, 픽셀 단위(디폴트: 0)
        - vGap: 상하 두 컴포넌트 사이의 수직 간격, 픽셀 단위(디폴트: 0)

- add() 메소드
    - void add(Component comp, int index)
        - comp 컴포넌트를 index 위치에 삽입한다.
        - index: 컴포넌트의 위치
            1. 동: BorderLayout.EAST
            2. 서: BorderLyaout.WEST
            3. 남: BorderLayout.SOUTH
            4. 북: BorderLayout.NORTH
            5. 중앙: BorderLayout.CENTER

**GridLayout 배치관리자**
- 배치방법
    - 컨테이너 공간을 동일한 사각형 격자(그리드)로 분할하고 각 셀에 컴포넌트 하나씩 배치
        - 생성자에 행수와 열수 지정
        - 셀에 왼쪽에서 오른쪽으로, 다시 위에서 아래로 순서대로 배치

**GridLayout 생성자**
- 생성자
    - GridLayout()
    - GridLayout(int rows, int cols)
    - GridLayout(int rows, int cols, int hGap, int vGap)
        - rows: 격자의 행수(디폴트: 1)
        - cols: 격자의 열수(디폴트: 1)
        - hGap: 좌우 두 컴포넌트 사이의 수평 간격, 픽셀 단위(디폴트: 0)
        - vGap: 상하 두 컴포넌트 사이의 수직 간격, 픽셀 단위(디폴트: 0)
        - rows x cols: 만큼의 셀을 가진 격자로 컨테이너 공간을 분할, 배치

**배치관리자가 없는 컨테이너**
- 배치관리자가 없는 컨테이너가 필요한 경우
    - 응용프로그램에서 직접 컴포넌트의 크기와 위치를 결정하고자 하는 경우
        1. 컴포넌트의 크기나 위치를 개발자 임의로 결정하고자 하는 경우
        2. 게임 프로그램과 같이 시간이나 마우스/키보드의 입력에 따라 컴포넌트들의 위치와 크기가 수시로 변하는 경우
        3. 여러 컴포넌트들이 서로 겹쳐 출력하고자 하는 경우

- 컨테이너의 배치관리자 제거 방법
    - container.setLayout(null);
    ```java
    JPanel p = new JPanel();
    p.setLayout(null); // JPanel의 배치관리자 삭제
    ```

- 컨테이너의 배치관리자가 없어지면, 컴포넌트에 대한 어떤 배치도 없음
    - 추가된 컴포넌트의 크기가 0으로 설정, 위치는 예측할 수 없게 된
    ```java
    // 패널 p에는 배치관리자가 없으면 아래 두 버튼은 배치되지 않는다

    p.add(new JButton("click")); // 폭과 높이가 0인 상태로 화면에 보이지 않는다.
    p.add(new JButton("me!")); // 폭과 높이가 0인 상태로 화면에 보이지 않는다.
    ```

**컴포넌트의 절대 위치와 크기 설정**
- 배치관리자가 없는 컨테이너에 컴포넌트를 삽입할 때
    - 프로그램에서 컴포넌트의 절대 크기와 위치 설정
    - 컴포넌트들이 서로 겹치게 할 수 있음

- 컴포넌트의 크기와 위치 설정 메소드
    - void setSize(int width, int height)
    - void setLocation(int x, int y)
    - void setBounds(int x, int y, int width, int height)

<hr/>

## 5월 3일 강의
> 내용 정리

**컬렉션: 요소(element)라고 불리는 가변 개수의 객체들의 저장소**
- 객체들의 컨테이너라고도 불림
- 요소의 개수에 따라 크기 자동 조절
- 요소의 삽입, 삭제에 따른 요소의 위치 자동 이동

컬렉션은 제네릭(generic) 기법으로 구현

**제네릭: 클래스나 메소드를 형판에서 찍어내듯이 생산할 수 있도록 일반화된 형판을 만드는 기법**
- 특정 타입만 다루지 않고, 여러 종류의 타입으로 변신할 수 있도록 클래스나 메소드를 일반화 시키는 과정
- 클래스나 인터페이스 이름에 <E>, <K>, <V> 등 타입매개 변수 포함

**제네릭 컬렉션 사례: 벡터 Vector<E>**
- <E>에서 E에 구체적인 타입을 주어 구체적인 타입만 다루는 벡터로 활용
- 정수만 다루는 컬렉션 벡터 Vector<Integer>
- 문자열만 다루는 컬렉션 벡터 Vector<String>

**벡터 Vector<E>의 특징**
<E>에 사용할 요소의 특정 타입으로 구체화

배열을 가변 크기로 다룰 수 있게 하는 컨테이너
- 배열의 길이 제한 극복
- 요소의 개수가 넘치면 자동으로 길이 조절

요소 객체들을 삽입, 삭제, 검색하는 컨테이너
- 삽입, 삭제에 따라 자동으로 요소의 위치 조정

Vector에 삽입 가능한 것
- 객체, null
- 기본 타입의 값은 Wrapper 객체로 만들어 저장

Vector에 객체 삽입
- 벡터의 맨 뒤, 중간에 객체 삽입 가능

Vector에서 객체 삭제
- 임의의 위치에 있는 객체 삭제 가능

**ArrayList<E>**
가변 크기 배열을 구현한 클래스
- <E>에 요소로 사용할 특정 타입으로 구체화

벡터와 거의 동일
- 요소 삽입, 삭제, 검색 등 벡터 기능과 거의 동일
- 벡터와 달리 스레드 동기화 기능 없음

Iterator<E> 인터페이스
- 리스트 구조의 컬렉션에서 요소의 순차 검색을 위한 인터페이스

Interator 객체 얻어내기
- 컬렉션의 iterator() 메소드 호출
```java
Vector<Integer> v = new Vector<Integer>();
Iterator<Integer> it = v.iterator();
```
- 컬렉션 검색 코드
```java
while(i.hasNext()) { // 모든 요소 방문
    int n = it.next(); // 다음 요소 리턴
    ~
}
```

**HashMap<K, V>**
키(key)와 값(value)의 쌍으로 구성되는 요소를 다루는 컬렉션
- K: 키로 사용할 요소의 타입
- V: 값으로 사용할 요소의 타입
- 키와 값이 한 쌍으로 삽입
- '값'을 검색하기 위해서는 반드시 '키'이용

삽입 및 검색이 빠른 특징
- 요소 삽입: put() 메소드
- 요소 검색: get() 메소드

**자바의 GUI(Graphical User Interface)**
GUI 응용프로그램
- GUI
사용자가 편리하게 입출력 할 수 있도록 그래픽을 화면을 구성하고, 마우스나 키보드로 입력 받을 수 있도록 지원하는 사용자 인터페이스
- 자바 언어에서 GUI 응용프로그램 작성
AWT와 Swing 패키지에 강력한 GUI 컴포넌트 제공
쉬운 GUI 프로그래밍

AWT와 Swing 패키지
- AWT(Abstract Windowing Toolkit) 패키지
자바가 처음 나왔을 때부터 배포된 GUI 패키지, 최근에는 거의 사용하지 않음
AWT 컴포넌트는 중량 컴포넌트(heavy weight component)

- Swing 패키지
AWT 기술을 기반으로 작성된 자바 라이브러리
모든 AWT 기능 + 추가된 풍부하고 화려한 고급 컴포넌트
AWT 컴포넌트를 모두 스윙으로 재작성, AWT 컴포넌트 이름 앞에 J자를 덧붙임
순수 자바 언오로 구현
스윙 컴포넌트는 경량 컴포넌트(light weight component)
현재 자바의 GUI로 사용됨

**컨테이너와 컴포넌트**
컨테이너
- 다른 컴포넌트를 포함할 수 있는 GUI 컴포넌트
- 다른 컨테이너에 포함될 수 있음

컴포넌트
- 컨테이너에 포함되어야 화면에 출력할 수 있는 GUI 객체
- 다른 컴포넌트를 포함할 수 없는 순수 컴포넌트
- 모든 GUI 컴포넌트가 상속받는 클래스: java.awt.Component
- 스윙 컴포넌트가 상속받는 클래스: javax.swingJComponent

최상위 컴포넌트
- 다른 컨테이너에 포함되지 않고도 화면에 출력되어 독립적으로 전재 가능한 컨테이너
(스스로 화면에 자신을 출력하는 컨테이너: JFrame, JDialog, JApplet)

**스윙 GUI 프로그램 만들기**
- 스윙 GUI 프로그램을 만드는 과정
1. 스윙 프레임 만들기
2. main() 메소드 작성
3. 스윙 프레임에 스윙 컴포넌트 붙이기

- 스윙 패키지 사용을 위한 import문
```java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*
```

**스윙 프레임**
- 스윙 프레임: 모든 스윙 컴포넌트를 담는 최상위 컨테이너
JFrame을 상속받아 구현
컴포넌트들은 화면에 보이려면 스윙 프레임에 부착되어야 함

- 스윙 프레임(JFrame) 기본 구성
Frame(java.awt.Frame), 메뉴바(Menu Bar), 컨텐트팬(Content Pane)의 3공간으로 구성된다.

<hr/>

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

<hr/>

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

<hr/>

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

<hr/>

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