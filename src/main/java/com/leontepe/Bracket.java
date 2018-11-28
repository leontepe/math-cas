
package com.leontepe;

public class Bracket extends ExpressionElement {

    public static final Bracket LEFT_BRACKET = new Bracket('(');
    public static final Bracket RIGHT_BRACKET = new Bracket(')');

    private char character;

    private Bracket(char character) {
        this.character = character;
    }

    public static boolean isLeftBracket(char c) {
        return c == LEFT_BRACKET.getCharacter();
    }

    public static boolean isRightBracket(char c) {
        return c == RIGHT_BRACKET.getCharacter();
    }

    public static boolean isBracket(char c) {
        return isLeftBracket(c) || isRightBracket(c);
    }

    public char getCharacter() {
        return this.character;
    }
}