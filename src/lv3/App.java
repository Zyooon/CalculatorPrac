package lv3;

import java.text.DecimalFormat;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ArithmeticCalculator calculator = new ArithmeticCalculator();
        //스캐너 생성
        Scanner sc = new Scanner(System.in);

        //계산기 클래스
        while(true){
            ArithmeticCalculatorData calData = new ArithmeticCalculatorData<>();

            if(!calculator.selectWork(sc, calculator)) break;

            //숫자 입력
            calData = calculator.inputData(calData, sc);
            sc.nextLine();

            //계산
            calculator.calculate(calData);
            //double 출력 시 소수점999999999.. 로 나오는 결과 수정
            System.out.println("\n연산 결과 : " + new DecimalFormat("#.############").format(calData.getResultNum()));

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

