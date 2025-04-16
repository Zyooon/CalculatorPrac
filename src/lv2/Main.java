package lv2;

public class Main {

    public static void main(String[] args) {
        CalculatorOperator calOperator = new CalculatorOperator();
        //메인 클래스
        //생성자 클래스 - 숫자 두개, 연산자, 결과 값, 결과 기록 stack
        //연산하는 클래스


        //scanner 에서 NoSuchElementException 에러 발생

        System.out.println("지금부터 계산을 시자악 하겠습니다~!");
        calOperator.operator();
        System.out.println("종료되었습니다.");

    }
}
