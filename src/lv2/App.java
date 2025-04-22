package lv2;

import java.util.*;

public class App {

    //메인 클래스
    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);
        System.out.println("계산기 시작");

        while(true){
            //객체 생성
            CalculatorData calData = new CalculatorData();
            
            //숫자 입력
            calData = calculator.inputData(calData, sc);
            sc.nextLine();

            //계산 - 입력값 범위 초과시 에러
            try{
                calData.setResultNum(calculator.calculate(calData));
                System.out.println("\n연산 결과 : " + calData.getResultNum());
            }catch (ArithmeticException e) {
                System.out.println("입력값이 범위를 초과하였습니다.");
                System.out.println("다시 입력해 주세요.");
                continue;
            }

            //결과 저장 및 출력
            calculator.getResultStack().push(calData);
            System.out.println(calculator);

            //계속 or 종료 or 삭제
            int num = calculator.isGoStop(sc);
            if(num == 2){
                System.out.println("종료되었습니다.");
                break;
            }else if(num == 3){
                //가장 먼저 저장된 데이터 지우기
                calculator = calculator.removeResult(calculator, sc);
            }
        }
        sc.close();
    }




}

