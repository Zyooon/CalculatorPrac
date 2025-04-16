package lv2;

public class CalculatorData {

    private int firstNum;
    private int secondNum;
    private char operator;
    private int resultNum;

    public CalculatorData() {
    }

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


    @Override
    public String toString() {
        return "calculatorData{" +
                "firstNum=" + firstNum +
                ", secondNum=" + secondNum +
                ", operator=" + operator +
                ", resultNum=" + resultNum +
                '}';
    }
}
