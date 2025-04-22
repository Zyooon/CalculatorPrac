package lv3;

import java.text.DecimalFormat;

//T 타입을 Number 로 제한 -> 숫자만 입력받음
public class ArithmeticCalculatorData<T extends Number> {

    //필드
    private T firstNum;
    private T secondNum;
    private char operator;
    private T resultNum;

    //기본 생성자
    public ArithmeticCalculatorData() {
    }

    //getter, setter
    public T getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(T firstNum) {
        this.firstNum = firstNum;
    }

    public T getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(T secondNum) {
        this.secondNum = secondNum;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public T getResultNum() {
        return resultNum;
    }

    public void setResultNum(T resultNum) {
        this.resultNum = resultNum;
    }


    @Override
    public String toString() {
        return "연산 결과{ " + firstNum + " " + operator + " " + secondNum + " = " + new DecimalFormat("#.############").format(resultNum) + " }";

    }
}
