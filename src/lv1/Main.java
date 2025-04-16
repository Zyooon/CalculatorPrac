package lv1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static int firstNum;
    static int secondNum;
    static char operator;
    static boolean isOnOff = true;
    static Set<Character> operatorSet = new HashSet<>(Arrays.asList('*', '+', '-', '/'));
    static int resultNum;
    static String exit;

    public static void main(String[] args) {

        System.out.println("지금부터 계산을 시자악 하겠습니다~!");

        while(isOnOff){

            //첫번째 숫자 입력
            while(true){
                System.out.println("첫번째 숫자를 입력하세요");
                System.out.print("숫자 입력 : ");
                try{
                    firstNum = sc.nextInt();
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
                    operator = sc.next().charAt(0); //nextLine > next 로 변경
                    if(operatorSet.contains(operator)) break;

                } catch (Exception e) {
                    System.out.println("제대로 입력해 주세요1");
                    sc.nextLine();
                }
                System.out.println("제대로 입력해 주세요2");
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
                } catch (Exception e) {
                    System.out.println("제대로 입력해 주세요");
                    sc.nextLine();
                    continue;
                }
            }

            //계산하기
            switch (operator){
                case '+' : resultNum = firstNum + secondNum; break;
                case '-' : resultNum = firstNum - secondNum; break;
                case '*' : resultNum = firstNum * secondNum; break;
                case '/' : resultNum = firstNum / secondNum; break;
            }

            System.out.println("연산 결과 : " + resultNum);
            System.out.println("\r더 하시려면 아무키나 눌러주세요. (exit 입력 시 종료)");

            try{
                exit = sc.next();
                if(exit.equals("exit")){
                    break;
                }
            } catch (Exception e) {
                sc.nextLine();
            }
            //계산 끝
        }
        System.out.println("종료되었습니다.");
        sc.close();

    }
}
