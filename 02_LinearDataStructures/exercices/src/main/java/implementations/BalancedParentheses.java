package implementations;

import interfaces.Solvable;

import java.util.ArrayDeque;

public class BalancedParentheses implements Solvable {
    private String parentheses;
    private ArrayDeque<Character> stack;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
        this.stack = new ArrayDeque<>();
    }

    @Override
    public Boolean solve() {
        for (int i = 0, n = parentheses.length() ; i < n ; i++) {
            Character ch = stack.peek();
            if (isClosingParentheses(ch, parentheses.charAt(i))){
                stack.poll();
            }else {
                stack.push(parentheses.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    private boolean isClosingParentheses (Character opening, Character closing) {
        if (opening == null || closing == null) {
            return false;
        }
        switch (opening) {
            case '{' : return closing.equals('}');
            case '[' : return closing.equals(']');
            case '(' : return closing.equals(')');
            default:return false;
        }
    }
}
