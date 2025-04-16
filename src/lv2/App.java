package lv2;

import java.util.*;

public class App {
    static Set<Character> operatorSet = new HashSet<>(Arrays.asList('*', '+', '-', '/'));

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        CalculatorData calData = new CalculatorData();
        Scanner sc = new Scanner(System.in);
        //메인 클래스
        //생성자 클래스 - 숫자 두개, 연산자, 결과 값, 결과 기록 stack
        //연산하는 클래스

        System.out.println("지금부터 계산을 시자악 하겠습니다~!");
        //계산기 클래스

        while(true){

            //숫자 입력
            calData = insertData(calData, sc);
            sc.nextLine();

            //계산 - 입력값 범위 초과시 에러
            try{
                calData.setResultNum(calculator.dataOperate(calData));
                System.out.println("연산 결과 : " + calData.getResultNum());
            }catch (ArithmeticException e) {
                System.out.println("입력값이 범위를 초과하였습니다.");
                System.out.println("다시 입력해 주세요.");
                continue;
            }

            //결과 저장
            calculator.getResultStack().push(calData);

            //결과들 출력
            System.out.println(calculator);

            int num = calculator.isGoStop(sc);
            //계속 or 종료 or 삭제
            if(num == 2){
                System.out.println("종료되었습니다.");
                break;
            }else if(num == 3){
                //가장 먼저 저장된 데이터 지우기ㅍ
                calculator = calculator.deleteData(calculator, sc);
            }

            

        }
        sc.close();
    }



    public static CalculatorData insertData(CalculatorData calData, Scanner sc){
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
}

