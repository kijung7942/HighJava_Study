package kr.or.ddit.basic.enumtest;

/*
        enum(열거형) -> 서로 관련있는 실수들의 집합을 나타낸다.
                    -> 클래스처럼 보이게하는 상수
        - 작성방법
                -> 열거형은 클래스처럼 독립된 java 파일에 만들 수 있고,
                   하나의 java 파일에 클래스와 같이 만들 수 있고,
                   클래스 내부에 내부 클래스처럼 만들 수 있다.

        - 열거형의 속성 및 메서드
        1) name()                 -> 열거형 상수의 이름을 문자열로 반환한다.
        2) ordinal()              -> 열거형 상수가 정의된 순서값(index값)을 반환한다.(0부터 시작)
        3) valueOf("열거형상수명")  -> 인수값에 지정한 '열거형상수명'과 일치하는 열거형 상수를 반환.
        4) 열거형이름.상수명         -> valueOf()메서드와 같다.

        ----------------------------------------------------------------------------------------

        - 열거형 선언하기
        방법1)
            enum 열거형이름{상수명1,상수명2,상수명3,...}
        방법2)
            enum 열거형이름{
                    상수명1(값1, 값2, 값3, ...)
                    상수명2(값1, 값2, 값3, ...)
                    상수명3(값1, 값2, 값3, ...)
                    ...

                    //상수명 앞의 값들이 저장될 변수 선언
                    private 자료형이름 변수명1;
                    private 자료형이름 변수명2;
                    ...

                    //열거형의 생성자를 만든다.
                    //열거형의 생성자는 '열거형상수'에 '값들'을 세팅하는 역할을 수행한다.
                    //열거형 생성자는 묵시적으로 'private'이다.


                    // 생성자의 매개변수는 상수옆의 값들과 자료형이 맞아야 한다.
                    private 열거형이름(자료형이름 변수명1, ...){
                        위에 선언된 변수들을 초기화 한다
                    }

                    // 위에 선언한 변수들을 외부에서 불러올 수 있는 getter 메서드를 만든다.


 */

public class EnumTest {
    public enum Color {RED, GREEN, BLUE}

    public enum Count {ONE, TWO, THREE}

    public enum Season {
        //상수명(값들...) 형식의 선언
        봄("3월부터 5월까지"), 
        여름("6월부터 8월까지"), 
        가을("9월부터 11월까지"), 
        겨울("12월부터 2월까지");
        


        private String span;  // 값이 저장 될 변수

        // 생성자
        Season(String months){ // private Season(String months){ 와 같다.
            span = months;
        }

        //getter 생성
        public String getSpan(){
            return span;
        }

    }



        public static void main(String[] args) {
//        ConstTest test = new ConstTest();
//
//        System.out.println(test.ONE);
//
//        System.out.println(ConstTest.ONE);
//        System.out.println(ConstTest.PI);
//        System.out.println(Math.PI);
//
//        //의도는 다르지만 값이 같다고 나옴.
//        if(ConstTest.ONE == ConstTest.RED){
//            System.out.println("같다");
//        }else{
//            System.out.println("다르다");
//        }


//        if(Count.ONE == Color.RED){  // 다른 종류의 열거형끼리 비교가 불가함.
        if (Count.ONE.equals(Color.RED)) { // 억지로 비교해보는 방법
            System.out.println("같다");
        } else {
            System.out.println("다르다");

            Color mycol = Color.valueOf("GREEN"); // Color my col = Color.GREEN 과 같다.
            Count mycnt = Count.THREE;

            System.out.println("mycol = " + mycol + " / mycnt = " + mycnt);
            System.out.println("mycol = " + mycol.name() + " / mycnt = " + mycnt.name());
            System.out.println("mycol = " + mycol.ordinal() + " / mycnt = " + mycnt.ordinal());

            if (mycol == Color.GREEN) {  // 같은 종류의 열거형끼리만 비교가 가능하다
                System.out.println("같다");
            } else {
                System.out.println("다르다");
            }
            System.out.println("----------------------------------------------------------");
            Season ss = Season.valueOf("봄");
            System.out.println("name : " + ss.name());
            System.out.println("ordinal : " + ss.ordinal());
            System.out.println("span : " + ss.getSpan());

            //열거형이름.values()  -> 모든 상수들을 배열로 가져온다.
            for(Season time : Season.values()){
                System.out.println(time.name() + "==" + time + "-->" + time.getSpan());
            }

            System.out.println("-----------------------------------------------------------");
            for(Color c : Color.values()){
                System.out.println(c + " --> " + c.ordinal());
            }

            // 열거형을 switch 문에서 비교하기
            switch (mycol){
                case RED:
                    System.out.println("RED 입니다.");
                    return;
                case GREEN:
                    System.out.println("GREEN 입니다.");
                    return;
                case BLUE:
                    System.out.println("BLUE 입니다.");
                    return;
                //case Color.GREEN :  // switch 문에서 쓸 때는 열거형의 이름을 생략해야 함.
            }


        }
    }
}
