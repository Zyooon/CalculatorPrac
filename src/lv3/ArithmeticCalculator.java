package lv3;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class ArithmeticCalculator {

    private List<ArithmeticCalculatorData> resultList;

    public ArithmeticCalculator() {
        resultList = new ArrayList<>();
    }

    public List<ArithmeticCalculatorData> getResultList() {
        return resultList;
    }

    @Override
    public String toString() {
        return "전체 결과{" + resultList + '}';
    }

    //enum 생성하여 연산 관리
    public enum OperatorType {
        PLUS('+', (x, y) -> x + y),
        MINUS('-', (x, y) -> x - y),
        MULTIPLY('*', (x, y) -> x * y),
        DIVIDE('/', (x, y)-> x / y);

        private final char symbol;
        private final BiFunction<Double, Double, Double> operate;

        // BiFunction<파라미터1 타입, 파라미터2 타입, 리턴 타입> -> 파라미터가 2개, 리턴값 1개
        OperatorType(char symbol, BiFunction<Double, Double, Double> operate) {
            this.symbol = symbol;
            this.operate = operate;
        }

        // 연산 값 리턴
        public double operate(double x, double y) {
            return operate.apply(x, y);
        }

        // 심볼 값 리턴 (+,-,*,/)
        public char getSymbol() {
            return symbol;
        }
    }

    //계산하는 메서드
    public ArithmeticCalculatorData calculate(ArithmeticCalculatorData calData) {

        double resultNum;
        double firstNum = calData.getFirstNum().doubleValue();
        double secondNum = calData.getSecondNum().doubleValue();
        char operatorSymbol = calData.getOperator();

        switch (operatorSymbol) {
            case '+':
                resultNum = OperatorType.PLUS.operate(firstNum, secondNum);
                break;
            case '-':
                resultNum = OperatorType.MINUS.operate(firstNum, secondNum);
                break;
            case '*':
                resultNum = OperatorType.MULTIPLY.operate(firstNum, secondNum);
                break;
            case '/':
                resultNum = OperatorType.DIVIDE.operate(firstNum, secondNum);
                break;
            default:
                resultNum = 0;
        }

        // if-else 문으로 변경
/*        if(operatorSymbol == OperatorType.PLUS.getSymbol()){
            resultNum = OperatorType.PLUS.operate(firstNum, secondNum);
        }else if(operatorSymbol == OperatorType.MINUS.getSymbol()){
            resultNum = OperatorType.MINUS.operate(firstNum, secondNum);
        }else if(operatorSymbol == OperatorType.MULTIPLY.getSymbol()){
            resultNum = OperatorType.MULTIPLY.operate(firstNum, secondNum);
        }else if(operatorSymbol == OperatorType.DIVIDE.getSymbol()){
            resultNum = OperatorType.DIVIDE.operate(firstNum, secondNum);
        }*/

        if (isInt(resultNum)) calData.setResultNum((int)resultNum);
        else calData.setResultNum(resultNum);

        return calData;
    }

    // 종료 여부 결정
    public boolean isGoStop(Scanner sc) {
        System.out.println("\n.exit 입력 시 종료됩니다.");
        try {
            String str = sc.nextLine();
            if (str.equals("exit")) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("계산을 다시 시작 하겠습니다.");
        }
        return true;
    }

    // 작업 선택
    public boolean selectWork(Scanner sc, ArithmeticCalculator cal) {
        System.out.println("\n계산을 시작합니다. (0 : 다른 작업)");

        try {
            String str = sc.nextLine();
            if (!str.equals("0")) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("계산을 다시 시작 하겠습니다.");
        }

        while (true) {
            System.out.println("\n필요한 작업을 선택해주세요.");
            System.out.println("연산 기록 조회 : 1");
            System.out.println("특정 데이터 조회 : 2");
            System.out.println("데이터 삭제 : 3");
            System.out.println("계산기 시작 : 0");
            try {
                int num = sc.nextInt();
                switch (num) {
                    case 1:
                        System.out.println(cal.getResultList());
                        break;
                    case 2:
                        checkUpperResult(cal, sc);
                        break;
                    case 3:
                        cal = removeResult(cal, sc);
                        break;
                    case 0:
                        return true;
                    default:
                        System.out.println("똑바로 입력해 주세요.");
                }
            } catch (Exception e) {
                System.out.println("똑바로 입력해 주세요.");
            }
        }

    }

    // 데이터 삭제
    private ArithmeticCalculator removeResult(ArithmeticCalculator cal, Scanner sc) {
        if (cal.getResultList().isEmpty()) {
            System.out.println("연산 데이터가 존재하지 않습니다.");
            return cal;
        }
        int scTemp;
        while (true) {
            System.out.println("데이터를 지우시겠습니까?");
            System.out.println("최근 데이터 삭제 : 1");
            System.out.println("모든 데이터 삭제 : 2");
            System.out.println("삭제 안함 : 0");
            try {
                scTemp = sc.nextInt();
                sc.nextLine();

                if (scTemp == 1) {
                    cal.getResultList().remove(resultList.size() - 1);
                    System.out.println(cal.getResultList());
                    return cal;
                } else if (scTemp == 2) {
                    cal.getResultList().clear();
                    System.out.println(cal.getResultList());
                    return cal;
                }  else if (scTemp == 0) {
                    return cal;
                } else {
                    System.out.println("다시 눌러 주세요.");
                }

            } catch (Exception e) {
                System.out.println("다시 눌러 주세요.");
                sc.nextLine();
            }
        }

    }
    // stream 값보다 큰 결과 조회
    public void checkUpperResult(ArithmeticCalculator cal, Scanner sc){
        if (cal.getResultList().isEmpty()) {
            System.out.println("연산 데이터가 존재하지 않습니다.");
            return;
        }
        while(true){
            try {
                System.out.println("숫자를 입력해주세요. 입력값보다 높은 결과값이 조회됩니다.");

                double inputNum = sc.nextDouble();
                sc.nextLine();

                //필터활용하여 새로운 데이터 조회
                List<ArithmeticCalculatorData> list = cal.getResultList().stream()
                        .filter(result -> result.getResultNum().doubleValue() > inputNum)
                        .collect(Collectors.toList());

                //list 출력
                System.out.println("\n"+inputNum+"보다 높은 결과 : "+list);

                return;


            } catch (Exception e) {
                System.out.println("똑바로 입력해 주세요.");
                sc.nextLine();
            }
        }
    }

    //int or double 판독
    private boolean isInt(double num) {
        if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
            return false;
        } else return num % 1 == 0.0;
    }

    // 계산기 숫자 및 연산자 입력
    public ArithmeticCalculatorData inputData(ArithmeticCalculatorData calData, Scanner sc) {
        Set<Character> operatorSet = new HashSet<>(Arrays.asList('*', '+', '-', '/'));
        double tempNum;
        char tempChar;

        //첫번째 숫자 입력
        while (true) {
            System.out.println("\n첫번째 숫자를 입력하세요");
            System.out.print("숫자 입력 : ");
            try {
                tempNum = sc.nextDouble();
                sc.nextLine();
                if (isInt(tempNum)) {
                    calData.setFirstNum((int) tempNum);
                } else {
                    calData.setFirstNum(tempNum);
                }
                break;
            } catch (Exception e) {
                System.out.println("숫자를 제대로 입력해 주세요");
                sc.nextLine();
            }
        }

        //연산자 입력
        while (true) {
            System.out.println("\n연산자를 입력하세요.  (+,-,*,/)");
            System.out.print("연산자 입력 : ");
            try {
                String str = sc.nextLine();
                tempChar = str.charAt(str.length() - 1);
                if (operatorSet.contains(tempChar)) {
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
        while (true) {
            System.out.println("\n두번째 숫자를 입력하세요");
            System.out.print("숫자 입력 : ");
            try {
                tempNum = sc.nextDouble();
                if (tempChar == '/' && tempNum == 0.0) {
                    System.out.println("0으로 나눌 수 없습니다.");
                    continue;
                }
                //int double 비교하여 처리
                if (isInt(tempNum)) {
                    calData.setSecondNum((int) tempNum);
                } else {
                    calData.setSecondNum(tempNum);
                }

                break;
            } catch (Exception e) {
                System.out.println("제대로 입력해 주세요");
                sc.nextLine();
            }
        }

        return calData;
    }

}
