package lv1;

import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static int firstNum;
    static int secondNum;
    static char operator;
    static Set<Character> operatorSet = new HashSet<>(Arrays.asList('*', '+', '-', '/'));
    static int resultNum;
    static String exit;

    public static void main(String[] args) {

        System.out.println("지금부터 계산을 시자악 하겠습니다~!");

        while(true){

            //첫번째 숫자 입력
            while(true){
                System.out.println("첫번째 숫자를 입력하세요");
                System.out.print("숫자 입력 : ");
                try{
                    firstNum = sc.nextInt();
                    break;
                } catch (InputMismatchException e){
                    System.out.println("문자열이거나 너무 긴 숫자는 입력할 수 없어요.");
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("제대로 입력해 주세요.");
                    sc.nextLine();
                }
            }
            
            //연산자 입력
            while(true){
                System.out.println("연산자를 입력하세요. (+,-,*,/)");
                System.out.print("연산자 입력 : ");
                try{
                    String str = sc.next(); //nextLine > next 로 변경
                    operator = str.charAt(str.length()-1);
                    if(operatorSet.contains(operator)) break;

                } catch (Exception e) {
                    System.out.println("+,-,*,/ 만 입력해주세요");
                    sc.nextLine();
                }
                System.out.println("+,-,*,/ 만 입력해주세요");
            }

            //두번째 숫자 입력
            while(true){
                System.out.println("두번째 숫자를 입력하세요");
                System.out.print("숫자 입력 : ");
                try{
                    secondNum = sc.nextInt();
                    if(operator == '/' && secondNum == 0) {
                        System.out.println("0으로 나눌 수 없습니다.");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e){
                    System.out.println("문자열이거나 너무 긴 숫자는 입력할 수 없어요.");
                    sc.nextLine();

                } catch (Exception e) {
                    System.out.println("제대로 입력해 주세요3");
                    sc.nextLine();
                }
            }

            //계산하기 -> 오버플로우 수정
//            switch (operator){
//                case '+' : resultNum = firstNum + secondNum; break;
//                case '-' : resultNum = firstNum - secondNum; break;
//                case '*' : resultNum = firstNum * secondNum; break;
//                case '/' : resultNum = firstNum / secondNum; break;
//            }

            //int 오버플로우 방지
            try{
                switch (operator){
                    case '+' : resultNum = Math.addExact(firstNum,secondNum); break;
                    case '-' : resultNum = Math.subtractExact(firstNum,secondNum); break;
                    case '*' : resultNum = Math.multiplyExact(firstNum,secondNum); break;
                    case '/' : resultNum = firstNum / secondNum; break;
                }

                System.out.println("연산 결과 : " + resultNum);
            } catch (ArithmeticException e) {
                System.out.println("입력값이 범위를 초과하였습니다.");
            }


            System.out.println("\r더 하시려면 아무키나 눌러주세요. (exit 입력 시 종료)");

            try{
                exit = sc.next();
                if(exit.equals("exit")){
                    System.out.println("종료되었습니다.");
                    break;
                }
            } catch (Exception e) {
                sc.nextLine();
            }
            //계산 끝
        }

        sc.close();

    }
}
