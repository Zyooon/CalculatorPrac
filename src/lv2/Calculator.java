package lv2;

import java.util.*;

public class Calculator {

    private Stack<CalculatorData> resultStack;

    public Calculator() {
        resultStack = new Stack<>();
    }

    public Calculator(Stack<CalculatorData> resultStack) {
        this.resultStack = resultStack;
    }

    public Stack<CalculatorData> getResultStack() {
        return resultStack;
    }

    @Override
    public String toString() {
        return "전체 결과{" + resultStack + '}';
    }

    //계산하는 메서드
    public int calculate(CalculatorData calData) throws ArithmeticException{

        int resultNum = 0;
        int firstNum = calData.getFirstNum();
        int secondNum = calData.getSecondNum();
        char operator = calData.getOperator();

        //오버플로우 방지
        switch (operator){
            case '+' : resultNum = Math.addExact(firstNum,secondNum); break;
            case '-' : resultNum = Math.subtractExact(firstNum,secondNum); break;
            case '*' : resultNum = Math.multiplyExact(firstNum,secondNum); break;
            case '/' : resultNum = firstNum / secondNum; break;
        }
        return resultNum;
    }

    // 또 할지 말지
     public int isGoStop(Scanner sc){
        System.out.println("\r더 하시려면 아무키나 눌러주세요. (exit 입력 시 종료, del 입력시 데이터 삭제)");
        try{
            String str = sc.nextLine();
            if(str.equals("exit")){
                return 2;
            }else if(str.equals("del")){
                return 3;
            }
        } catch (Exception e) {
            System.out.println("계산을 다시 시작 하겠습니다.");
        }
        return 1;
     }
     // 데이터 삭제
     public Calculator removeResult(Calculator rd, Scanner sc){
        int scTemp;
        while (true){
             System.out.println("데이터를 지우시겠습니까?");
             System.out.println("최근 데이터 삭제 : 1");
             System.out.println("모든 데이터 삭제 : 2");
             System.out.println("삭제 안함 : 0");
             try {
                 scTemp = sc.nextInt();

                 if(scTemp == 1){
                     rd.getResultStack().pop();
                     return rd;
                 } else if (scTemp == 2) {
                     rd.getResultStack().clear();
                     return rd;
                 } else if(scTemp == 0){
                     return rd;
                 }
                 System.out.println("다시 눌러 주세요.");
             } catch (Exception e) {
                 System.out.println("다시 눌러 주세요.");
             }
         }

     }

}
