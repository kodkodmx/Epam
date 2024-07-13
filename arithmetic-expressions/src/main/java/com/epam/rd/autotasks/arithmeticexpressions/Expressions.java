package com.epam.rd.autotasks.arithmeticexpressions;

public class Expressions {

    public static Variable var(String name, int value) {
        return new Variable(name, value);
    }

    public static Expression val(int value) {
        return new Expression() {
            @Override
            public int evaluate() {
                return value;
            }

            @Override
            public String toExpressionString() {
                return value < 0 ? "(" + value + ")" : String.valueOf(value);
            }
        };
    }

    public static Expression sum(Expression... members) {
        return new Expression() {
            @Override
            public int evaluate() {
                int sum = 0;
                for (Expression member : members) {
                    sum += member.evaluate();
                }
                return sum;
            }

            @Override
            public String toExpressionString() {
                StringBuilder sb = new StringBuilder("(");
                for (int i = 0; i < members.length; i++) {
                    if (i > 0) {
                        sb.append(" + ");
                    }
                    sb.append(members[i].toExpressionString());
                }
                sb.append(")");
                return sb.toString();
            }
        };
    }

    public static Expression product(Expression... members) {
        return new Expression() {
            @Override
            public int evaluate() {
                int product = 1;
                for (Expression member : members) {
                    product *= member.evaluate();
                }
                return product;
            }

            @Override
            public String toExpressionString() {
                StringBuilder sb = new StringBuilder("(");
                for (int i = 0; i < members.length; i++) {
                    if (i > 0) {
                        sb.append(" * ");
                    }
                    sb.append(members[i].toExpressionString());
                }
                sb.append(")");
                return sb.toString();
            }
        };
    }

    public static Expression difference(Expression minuend, Expression subtrahend) {
        return new Expression() {
            @Override
            public int evaluate() {
                return minuend.evaluate() - subtrahend.evaluate();
            }

            @Override
            public String toExpressionString() {
                return "(" + minuend.toExpressionString() + " - " + subtrahend.toExpressionString() + ")";
            }
        };
    }

    public static Expression fraction(Expression dividend, Expression divisor) {
        return new Expression() {
            @Override
            public int evaluate() {
                return dividend.evaluate() / divisor.evaluate();
            }

            @Override
            public String toExpressionString() {
                return "(" + dividend.toExpressionString() + " / " + divisor.toExpressionString() + ")";
            }
        };
    }
}
