package lv2;

import java.util.*;

public class Calculator {

    //필드
    private Stack<CalculatorData> resultStack;

    public Calculator() {
        resultStack = new Stack<>();
    }

    public Stack<CalculatorData> getResultStack() {
        return resultStack;
    }

    @Override
    public String toString() {
        return "전체 결과{" + resultStack + '}';
    }

    //계산하는 메서드 (오버플로우 방지 throws)
    public int calculate(CalculatorData calData) throws ArithmeticException{

        int resultNum = 0;
        int firstNum = calData.getFirstNum();
        int secondNum = calData.getSecondNum();
        char operator = calData.getOperator();

        switch (operator){
            case '+' : resultNum = Math.addExact(firstNum,secondNum); break;
            case '-' : resultNum = Math.subtractExact(firstNum,secondNum); break;
            case '*' : resultNum = Math.multiplyExact(firstNum,secondNum); break;
            case '/' : resultNum = firstNum / secondNum; break;
        }
        return resultNum;
    }

    // 종료 여부
     public int isGoStop(Scanner sc){
        System.out.println("\n더 하시려면 아무키나 눌러주세요. (exit 입력 시 종료, del 입력시 데이터 삭제)");
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
     public Calculator removeResult(Calculator cal, Scanner sc){
        if(cal.getResultStack().isEmpty()) return cal;
        int scTemp;
        while (true){
             System.out.println("\n데이터를 지우시겠습니까?");
             System.out.println("최근 데이터 삭제 : 1");
             System.out.println("모든 데이터 삭제 : 2");
             System.out.println("삭제 안함 : 0");
             try {
                 scTemp = sc.nextInt();

                 if(scTemp == 1){
                     cal.getResultStack().pop();
                     return cal;
                 } else if (scTemp == 2) {
                     cal.getResultStack().clear();
                     return cal;
                 } else if(scTemp == 0){
                     return cal;
                 }
                 System.out.println("다시 눌러 주세요.");
             } catch (Exception e) {
                 System.out.println("다시 눌러 주세요.");
             }
         }

     }

    // 숫자 및 연산자 입력받는 메서드
    public CalculatorData inputData(CalculatorData calData, Scanner sc){
        Set<Character> operatorSet = new HashSet<>(Arrays.asList('*', '+', '-', '/'));
        int tempNum;
        char tempChar;

        //첫번째 숫자 입력
        while(true){
            System.out.println("\n첫번째 숫자를 입력하세요");
            System.out.print("숫자 입력 : ");
            try{
                tempNum = sc.nextInt();
                sc.nextLine();
                calData.setFirstNum(tempNum);
                break;
            }catch (InputMismatchException e){
                System.out.println("문자나 기호, 너무 긴 숫자는 입력할 수 없어요.");
                sc.nextLine();
            }
        }

        //연산자 입력
        while(true){
            System.out.println("\n연산자를 입력하세요.  (+,-,*,/)");
            System.out.print("연산자 입력 : ");
            try{
                String str = sc.nextLine();
                tempChar = str.charAt(str.length()-1);
                if(operatorSet.contains(tempChar)) {
                    calData.setOperator(tempChar);
                    break;
                }else {
                    System.out.println("+,-,*,/ 만 입력해주세요");
                }
            } catch (Exception e) {
                System.out.println("+,-,*,/ 만 입력해주세요");
                sc.nextLine();
            }
        }

        //두번째 숫자 입력
        while(true){
            System.out.println("\n두번째 숫자를 입력하세요");
            System.out.print("숫자 입력 : ");
            try{
                tempNum = sc.nextInt(); //메서드가 넘어가기 때문에 nextLine() 필요 없음
                if(tempChar == '/' && tempNum == 0) {
                    System.out.println("0으로 나눌 수 없습니다.");
                    continue;
                }
                calData.setSecondNum(tempNum);
                break;
            } catch (InputMismatchException e){
                System.out.println("문자나 기호, 너무 긴 숫자는 입력할 수 없어요.");
                sc.nextLine();

            }
        }
        return calData;
    }

}
