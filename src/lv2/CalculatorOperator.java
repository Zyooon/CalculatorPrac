package lv2;

import java.util.*;

public class CalculatorOperator {


    static Set<Character> operatorSet = new HashSet<>(Arrays.asList('*', '+', '-', '/'));

    //연산 클래스
    public void operator(){

        CalculatorData calData = new CalculatorData();
        ResultData resultData = new ResultData();
        Scanner sc = new Scanner(System.in);

        while(true){
            
            //숫자 입력
            calData = insertData(calData, sc);

            //계산하는 메서드
            calData.setResultNum(dataOperate(calData));

            System.out.println("연산 결과 : " + calData.getResultNum());

            //결과 저장
            resultData.getResultStack().push(calData);

            System.out.println(resultData);

            //종료 or 계속
            if(isGoStop(sc)){
                sc.close();
                return;
            }
            //가장 먼저 저장된 데이터 지우기
            resultData = deleteData(resultData, sc);
        }

    }

    //숫자, 연산자 입력받는 메서드
    public CalculatorData insertData(CalculatorData calData, Scanner sc){
        int tempNum;
        char tempChar;

        //첫번째 숫자 입력
        while(true){
            System.out.println("첫번째 숫자를 입력하세요");
            System.out.print("숫자 입력 : ");
            try{
                tempNum = sc.nextInt();
                calData.setFirstNum(tempNum);
                break;
            } catch (Exception e) {
                System.out.println("제대로 입력해 주세요");
                sc.nextLine();
            }
        }

        //연산자 입력
        while(true){
            System.out.println("연산자를 입력하세요");
            System.out.print("연산자 입력 : ");
            try{
                String str = sc.next(); //nextLine > next 로 변경
                tempChar = str.charAt(str.length()-1);
                if(operatorSet.contains(tempChar)) {
                    calData.setOperator(tempChar);
                    break;
                }

            } catch (Exception e) {
                System.out.println("제대로 입력해 주세요1");
                sc.nextLine();
                continue;
            }
            System.out.println("제대로 입력해 주세요2");
        }

        //두번째 숫자 입력
        while(true){
            System.out.println("두번째 숫자를 입력하세요");
            System.out.print("숫자 입력 : ");
            try{
                tempNum = sc.nextInt();
                if(tempChar == '/' && tempNum == 0) {
                    System.out.println("0으로 나눌 수 없습니다.");
                    continue;
                }
                calData.setSecondNum(tempNum);
                break;
            } catch (Exception e) {
                System.out.println("제대로 입력해 주세요");
                sc.nextLine();
            }
        }
        return calData;
    }

    //계산하는 메서드
    public int dataOperate(CalculatorData calData){

        int resultNum = 0;
        int firstNum = calData.getFirstNum();
        int secondNum = calData.getSecondNum();
        char operator = calData.getOperator();

        switch (operator){
            case '+' : resultNum = firstNum + secondNum; break;
            case '-' : resultNum = firstNum - secondNum; break;
            case '*' : resultNum = firstNum * secondNum; break;
            case '/' : resultNum = firstNum / secondNum; break;
        }

        return resultNum;
    }

     public boolean isGoStop(Scanner sc){
        System.out.println("\r더 하시려면 아무키나 눌러주세요. (exit 입력 시 종료)");
        try{
            String exit = sc.next();
            if(exit.equals("exit")){
                return true;
            }
        } catch (Exception e) {
            System.out.println("계산을 다시 시작 하겠습니다.");
        }
        return false;
     }

     public ResultData deleteData(ResultData rd, Scanner sc){
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
