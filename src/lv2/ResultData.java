package lv2;

import java.util.Stack;

public class ResultData {
    private Stack<CalculatorData> resultStack;

    public ResultData() {
        resultStack = new Stack<>();
    }

    public ResultData(Stack<CalculatorData> resultStack) {
        this.resultStack = resultStack;
    }

    public Stack<CalculatorData> getResultStack() {
        return resultStack;
    }

    @Override
    public String toString() {
        return "resultData{" +
                "resultStack=" + resultStack +
                '}';
    }
}
