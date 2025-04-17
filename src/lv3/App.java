package lv3;

import java.util.Scanner;

public class App {

    static ArithmeticCalculator calculator = new ArithmeticCalculator();

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        //메인 클래스
        //생성자 클래스 - 숫자 두개, 연산자, 결과 값, 결과 기록 stack
        //연산하는 클래스

        //계산기 클래스
        while(true){
            ArithmeticCalculatorData calData = new ArithmeticCalculatorData<>();

            if(!calculator.selectWork(sc, calculator)) break;

            //숫자 입력
            calData = calculator.inputData(calData, sc);
            sc.nextLine();

            //계산
            calculator.calculate(calData);
            System.out.println("\n연산 결과 : " + calData.getResultNum());

            //결과 저장
            calculator.getResultList().add(calData);

            //결과들 출력
            System.out.println(calculator);

            //종료 물어보기
            if(!calculator.isGoStop(sc)) break;

        }
        sc.close();
    }

}

