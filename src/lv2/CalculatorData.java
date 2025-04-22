package lv2;

public class CalculatorData {

    //필드
    private int firstNum;
    private int secondNum;
    private char operator;
    private int resultNum;

    //기본 생성자
    public CalculatorData() {
    }

    //getter, setter
    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public int getResultNum() {
        return resultNum;
    }

    public void setResultNum(int resultNum) {
        this.resultNum = resultNum;
    }


    //toString
    @Override
    public String toString() {
        return "연산 결과{ " + firstNum+ " " +operator+ " " + secondNum + " = " + resultNum + " }";

    }
}
