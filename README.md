# 이태규 학번 202330124

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