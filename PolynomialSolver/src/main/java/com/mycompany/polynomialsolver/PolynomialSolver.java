package com.mycompany.polynomialsolver;

import java.util.Scanner;
import javax.management.RuntimeErrorException;

interface IPolynomialSolver {

    void setPolynomial(char poly, int[][] terms);
    String print(char poly);
    void clearPolynomial(char poly);
    float evaluatePolynomial(char poly, float value);
    int[][] add(char poly1, char poly2);
    int[][] subtract(char poly1, char poly2);
    int[][] multiply(char poly1, char poly2);
}

public class PolynomialSolver implements IPolynomialSolver {
    static DoubleLinkedList A = new DoubleLinkedList();
    static DoubleLinkedList B = new DoubleLinkedList();
    static DoubleLinkedList C = new DoubleLinkedList();
    static DoubleLinkedList R = new DoubleLinkedList();

    @Override
    public void setPolynomial(char poly, int[][] terms) throws RuntimeException {
        if (poly == 'A' && terms[0].length != 0) {
            for (int i = 0; i < terms[0].length; ++i)
                A.addLast(terms[0][i], terms[1][i]);
        } else if (poly == 'B' && terms.length != 0) {
            for (int i = 0; i < terms[0].length; ++i)
                B.addLast(terms[0][i], terms[1][i]);
        } else if (poly == 'C' && terms.length != 0) {
            for (int i = 0; i < terms[0].length; ++i)
                C.addLast(terms[0][i], terms[1][i]);
        } else if (poly == 'R' && terms.length != 0) {
            for (int i = 0; i < terms[0].length; ++i)
                R.addLast(terms[0][i], terms[1][i]);
        } else
            throw new RuntimeException();
    }

    @Override
    public String print(char poly) throws RuntimeErrorException {
        String out = "";
        String x = "x";
        switch (poly) {
        case 'A':
            if (A.size() != 0) {
                for (int i = 0; i < A.size(); i++) {
                    if (A.get(i) != 0) {
                        if (A.getExponent(i) != 0) {
                            if (i != 0 && A.get(i) >= 1 && out != "")
                                out += "+";
                            if (A.getExponent(i) > 1 || A.getExponent(i) < 0) {
                                x = "x^";
                                if (A.get(i) == 1)
                                    out += x + String.valueOf(A.getExponent(i));
                                else
                                    out += String.valueOf(A.get(i)) + x + String.valueOf(A.getExponent(i));

                            } else if (A.getExponent(i) == 1) {
                                x = "x";
                                if (A.get(i) == 1)
                                    out += x;
                                else
                                    out += String.valueOf(A.get(i)) + x;
                            }

                        } else {
                            if (A.get(i) > 0 && out != "")
                                out += "+" + String.valueOf(A.get(i));
                            else
                                out += String.valueOf(A.get(i));
                        }
                    }
                }
            } else
                throw new RuntimeException();
            break;
        case 'B':
            if (B.size() != 0) {
                for (int i = 0; i < B.size(); i++) {
                    if (B.get(i) != 0) {
                        if (B.getExponent(i) != 0) {
                            if (i != 0 && B.get(i) >= 1 && out != "")
                                out += "+";
                            if (B.getExponent(i) > 1 || B.getExponent(i) < 0) {
                                x = "x^";
                                if (B.get(i) == 1)
                                    out += x + String.valueOf(B.getExponent(i));
                                else
                                    out += String.valueOf(B.get(i)) + x + String.valueOf(B.getExponent(i));
                            } else if (B.getExponent(i) == 1) {
                                x = "x";
                                if (B.get(i) == 1)
                                    out += x;
                                else
                                    out += String.valueOf(B.get(i)) + x;
                            }

                        } else {
                            if (B.get(i) > 0 && out != "")
                                out += "+" + String.valueOf(B.get(i));
                            else
                                out += String.valueOf(B.get(i));
                        }
                    }
                }
            } else
                throw new RuntimeException();
            break;
        case 'C':
            if (C.size() != 0) {
                for (int i = 0; i < C.size(); i++) {
                    if (C.get(i) != 0) {
                        if (C.getExponent(i) != 0) {
                            if (i != 0 && C.get(i) >= 1 && out != "")
                                out += "+";
                            if (C.getExponent(i) > 1 || C.getExponent(i) < 0) {
                                x = "x^";
                                if (C.get(i) == 1)
                                    out += x + String.valueOf(C.getExponent(i));
                                else
                                    out += String.valueOf(C.get(i)) + x + String.valueOf(C.getExponent(i));
                            } else if (C.getExponent(i) == 1) {
                                x = "x";
                                if (C.get(i) == 1)
                                    out += x;
                                else
                                    out += String.valueOf(C.get(i)) + x;
                            }

                        } else {
                            if (C.get(i) > 0 && out != "")
                                out += "+" + String.valueOf(C.get(i));
                            else
                                out += String.valueOf(C.get(i));
                        }
                    }
                }
            } else
                throw new RuntimeException();
            break;
        case 'R':
            if (R.size() != 0) {
                for (int i = 0; i < R.size(); i++) {
                    if (R.get(i) != 0) {
                        if (R.getExponent(i) != 0) {
                            if (i != 0 && R.get(i) >= 1 && out != "")
                                out += "+";
                            if (R.getExponent(i) > 1 || R.getExponent(i) < 0) {
                                x = "x^";
                                if (R.get(i) == 1)
                                    out += x + String.valueOf(R.getExponent(i));
                                else
                                    out += String.valueOf(R.get(i)) + x + String.valueOf(R.getExponent(i));
                            } else if (R.getExponent(i) == 1) {
                                x = "x";
                                if (R.get(i) == 1)
                                    out += x;
                                else
                                    out += String.valueOf(R.get(i)) + x;
                            }
                        } else {
                            if (R.get(i) > 0 && out != "")
                                out += "+" + String.valueOf(R.get(i));
                            else
                                out += String.valueOf(R.get(i));
                        }
                    }
                }
            } else
                throw new RuntimeException();
            break;
        default:
            throw new RuntimeException();
        }
        if (out.equals(""))
            out = "0";

        return out;
    }

    @Override
    public void clearPolynomial(char poly) throws RuntimeErrorException {
        switch (poly) {
        case 'A':
            A.clear();
            break;
        case 'B':
            B.clear();
            break;
        case 'C':
            C.clear();
            break;
        case 'R':
            R.clear();
            break;
        default:
            throw new RuntimeException();
        }
    }

    @Override
    public float evaluatePolynomial(char poly, float value) throws RuntimeErrorException {
        float result = 0;
        switch (poly) {
        case 'A':
            for (int i = 0; i < A.size(); i++)
                result += (float) A.get(i) * Math.pow(value, (float) A.getExponent(i));
            break;
        case 'B':
            for (int i = 0; i < B.size(); i++)
                result += (float) B.get(i) * Math.pow(value, (float) B.getExponent(i));
            break;
        case 'C':
            for (int i = 0; i < C.size(); i++)
                result += (float) C.get(i) * Math.pow(value, (float) C.getExponent(i));
            break;
        default:
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public int[][] add(char poly1, char poly2) throws RuntimeErrorException {

        int[][] sum = {};
        int len1 = 0;
        int len2 = 0;
        if ((poly1 == 'A' && poly2 == 'B') || (poly2 == 'A' && poly1 == 'B')) {
            if (A.size() == 0 || B.size() == 0)
                throw new RuntimeException();
            if (A.size() >= B.size()) {
                len1 = A.size() - B.size();
                len2 = A.size();
                sum = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sum[0][i] = A.get(i);
                    sum[1][i] = A.getExponent(i);
                }
                for (int i = len1; i < len2; i++) {
                    sum[0][i] = A.get(i) + B.get(i - len1);
                    sum[1][i] = A.getExponent(i);
                }
            } else {
                len1 = B.size() - A.size();
                len2 = B.size();
                sum = new int[2][len2];

                for (int i = 0; i < len1; i++) {
                    sum[0][i] = B.get(i);
                    sum[1][i] = B.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sum[0][i] = A.get(i) + B.get(i);
                    sum[1][i] = B.getExponent(i);
                }
            }
        } else if ((poly1 == 'A' && poly2 == 'C') || (poly2 == 'A' && poly1 == 'C')) {
            if (A.size() == 0 || C.size() == 0)
                throw new RuntimeException();
            if (A.size() >= C.size()) {
                len1 = A.size() - C.size();
                len2 = A.size();
                sum = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sum[0][i] = A.get(i);
                    sum[1][i] = A.getExponent(i);
                }
                for (int i = len1; i < len2; i++) {
                    sum[0][i] = A.get(i) + C.get(i);
                    sum[1][i] = A.getExponent(i);
                }
            } else {
                len1 = C.size() - A.size();
                len2 = C.size();
                sum = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sum[0][i] = C.get(i);
                    sum[1][i] = C.getExponent(i);
                }
                for (int i = len1; i < len2; i++) {
                    sum[0][i] = A.get(i) + C.get(i);
                    sum[1][i] = C.getExponent(i);
                }
            }
        } else if ((poly1 == 'C' && poly2 == 'B') || (poly2 == 'C' && poly1 == 'B')) {
            if (C.size() == 0 || B.size() == 0)
                throw new RuntimeException();
            if (C.size() >= B.size()) {
                len1 = C.size() - B.size();
                len2 = C.size();
                sum = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sum[0][i] = C.get(i);
                    sum[1][i] = C.getExponent(i);
                }
                for (int i = len1; i < len2; i++) {
                    sum[0][i] = C.get(i) + B.get(i);
                    sum[1][i] = C.getExponent(i);
                }
            } else {
                len1 = B.size() - C.size();
                len2 = B.size();
                sum = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sum[0][i] = B.get(i);
                    sum[1][i] = B.getExponent(i);
                }
                for (int i = len1; i < len2; i++) {
                    sum[0][i] = C.get(i) + B.get(i);
                    sum[1][i] = B.getExponent(i);
                }
            }
        } else
            throw new RuntimeException();
        return sum;
    }

    @Override
    public int[][] subtract(char poly1, char poly2) throws RuntimeErrorException {
        int[][] sub = {};
        int len1 = 0;
        int len2 = 0;
        if (poly1 == 'A' && poly2 == 'B') {
            if (A.size() == 0 || B.size() == 0)
                throw new RuntimeException();
            if (A.size() >= B.size()) {
                len1 = A.size() - B.size();
                len2 = A.size();
                sub = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sub[0][i] = A.get(i);
                    sub[1][i] = A.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sub[0][i] = A.get(i) - B.get(i - len1);
                    sub[1][i] = A.getExponent(i);
                }
            } else {
                len1 = B.size() - A.size();
                len2 = B.size();
                sub = new int[2][len2];

                for (int i = 0; i < len1; i++) {
                    sub[0][i] = B.get(i) * -1;
                    sub[1][i] = B.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sub[0][i] = A.get(i) - B.get(i);
                    sub[1][i] = B.getExponent(i);
                }
            }
        } else if (poly2 == 'A' && poly1 == 'B') {
            if (A.size() == 0 || B.size() == 0)
                throw new RuntimeException();
            if (A.size() >= B.size()) {
                len1 = A.size() - B.size();
                len2 = A.size();
                sub = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sub[0][i] = A.get(i) * -1;
                    sub[1][i] = A.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sub[0][i] = A.get(i) * -1 + B.get(i - len1);
                    sub[1][i] = A.getExponent(i);
                }
            } else {
                len1 = B.size() - A.size();
                len2 = B.size();
                sub = new int[2][len2];

                for (int i = 0; i < len1; i++) {
                    sub[0][i] = B.get(i);
                    sub[1][i] = B.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sub[0][i] = A.get(i) * -1 + B.get(i);
                    sub[1][i] = B.getExponent(i);
                }
            }
        } else if (poly1 == 'A' && poly2 == 'C') {
            if (A.size() == 0 || C.size() == 0)
                throw new RuntimeException();
            if (A.size() >= C.size()) {
                len1 = A.size() - C.size();
                len2 = A.size();
                sub = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sub[0][i] = A.get(i);
                    sub[1][i] = A.getExponent(i);
                }
                for (int i = len1; i < len2; i++) {
                    sub[0][i] = A.get(i) - C.get(i);
                    sub[1][i] = A.getExponent(i);
                }
            } else {
                len1 = C.size() - A.size();
                len2 = C.size();
                sub = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sub[0][i] = C.get(i) * -1;
                    sub[1][i] = C.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sub[0][i] = A.get(i) - C.get(i);
                    sub[1][i] = C.getExponent(i);
                }
            }
        } else if (poly2 == 'A' && poly1 == 'C') {
            if (A.size() == 0 || C.size() == 0)
                throw new RuntimeException();
            if (A.size() >= C.size()) {
                len1 = A.size() - C.size();
                len2 = A.size();
                sub = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sub[0][i] = A.get(i) * -1;
                    sub[1][i] = A.getExponent(i);
                }
                for (int i = len1; i < len2; i++) {
                    sub[0][i] = A.get(i) * -1 + C.get(i);
                    sub[1][i] = A.getExponent(i);
                }
            } else {
                len1 = C.size() - A.size();
                len2 = C.size();
                sub = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sub[0][i] = C.get(i);
                    sub[1][i] = C.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sub[0][i] = A.get(i) * -1 + C.get(i);
                    sub[1][i] = C.getExponent(i);
                }
            }
        } else if (poly1 == 'C' && poly2 == 'B') {
            if (C.size() == 0 || B.size() == 0)
                throw new RuntimeException();
            if (C.size() >= B.size()) {
                len1 = C.size() - B.size();
                len2 = C.size();
                sub = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sub[0][i] = C.get(i);
                    sub[1][i] = C.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sub[0][i] = C.get(i) - B.get(i);
                    sub[1][i] = C.getExponent(i);
                }
            } else {
                len1 = B.size() - C.size();
                len2 = B.size();
                sub = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sub[0][i] = B.get(i) * -1;
                    sub[1][i] = B.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sub[0][i] = C.get(i) - B.get(i);
                    sub[1][i] = B.getExponent(i);
                }
            }
        } else if (poly2 == 'C' && poly1 == 'B') {
            if (C.size() == 0 || B.size() == 0)
                throw new RuntimeException();
            if (C.size() >= B.size()) {
                len1 = C.size() - B.size();
                len2 = C.size();
                sub = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sub[0][i] = C.get(i) * -1;
                    sub[1][i] = C.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sub[0][i] = C.get(i) * -1 + B.get(i);
                    sub[1][i] = C.getExponent(i);
                }
            } else {
                len1 = B.size() - C.size();
                len2 = B.size();
                sub = new int[2][len2];
                for (int i = 0; i < len1; i++) {
                    sub[0][i] = B.get(i);
                    sub[1][i] = B.getExponent(i);
                }

                for (int i = len1; i < len2; i++) {
                    sub[0][i] = C.get(i) * -1 + B.get(i);
                    sub[1][i] = B.getExponent(i);
                }
            }
        } else
            throw new RuntimeException();

        return sub;
    }

    @Override
    public int[][] multiply(char poly1, char poly2) throws RuntimeErrorException {
        int[][] product = {};
        int len = 0;
        int len1 = 0;
        int len2 = 0;
        int[][] arr = {};
        int counter = 0;
        if ((poly1 == 'A' && poly2 == 'B') || (poly2 == 'A' && poly1 == 'B')) {
            if (A.size() == 0 || B.size() == 0)
                throw new RuntimeException();

            len1 = A.size();
            len2 = B.size();
            arr = new int[2][len1 * len2];
            for (int i = 0; i < len1; i++)
                for (int j = 0; j < len2; j++) {
                    arr[0][len] = A.get(i) * B.get(j);
                    arr[1][len] = A.getExponent(i) + B.getExponent(j);
                    len++;
                }

            for (int i = 0; i < len; i++)
                for (int j = i + 1; j < len; j++) {
                    if ((arr[1][i] != -1) && (arr[1][i] == arr[1][j])) {
                        arr[0][i] += arr[0][j];
                        arr[1][j] = -1;
                    }
                    if (arr[1][i] != -1)
                        counter++;
                }
            int k = 0;
            product = new int[2][counter];
            for (int i = 0; i < len; i++) {
                if (arr[1][i] != -1) {
                    product[0][k] = arr[0][i];
                    product[1][k] = arr[1][i];
                    k++;
                }
            }
        } else if ((poly1 == 'A' && poly2 == 'C') || (poly2 == 'A' && poly1 == 'C')) {
            if (A.size() == 0 || C.size() == 0)
                throw new RuntimeException();

            len1 = A.size();
            len2 = C.size();
            arr = new int[2][len1 * len2];
            for (int i = 0; i < len1; i++)
                for (int j = 0; j < len2; j++) {
                    arr[0][len] = A.get(i) * C.get(j);
                    arr[1][len] = A.getExponent(i) + C.getExponent(j);
                    len++;
                }

            for (int i = 0; i < len; i++)
                for (int j = i + 1; j < len; j++) {
                    if ((arr[1][i] != -1) && (arr[1][i] == arr[1][j])) {
                        arr[0][i] += arr[0][j];
                        arr[1][j] = -1;
                    }
                    if (arr[1][i] != -1)
                        counter++;
                }
            int k = 0;
            product = new int[2][counter];
            for (int i = 0; i < len; i++) {
                if (arr[1][i] != -1) {
                    product[0][k] = arr[0][i];
                    product[1][k] = arr[1][i];
                    k++;
                }
            }
        } else if ((poly1 == 'C' && poly2 == 'B') || (poly2 == 'C' && poly1 == 'B')) {
            if (C.size() == 0 || B.size() == 0)
                throw new RuntimeException();

            len1 = C.size();
            len2 = B.size();
            arr = new int[2][len1 * len2];
            for (int i = 0; i < len1; i++)
                for (int j = 0; j < len2; j++) {
                    arr[0][len] = C.get(i) * B.get(j);
                    arr[1][len] = C.getExponent(i) + B.getExponent(j);
                    len++;
                }

            for (int i = 0; i < len; i++)
                for (int j = i + 1; j < len; j++) {
                    if ((arr[1][i] != -1) && (arr[1][i] == arr[1][j])) {
                        arr[0][i] += arr[0][j];
                        arr[1][j] = -1;
                    }
                    if (arr[1][i] != -1)
                        counter++;
                }
            int k = 0;
            product = new int[2][counter];
            for (int i = 0; i < len; i++) {
                if (arr[1][i] != -1) {
                    product[0][k] = arr[0][i];
                    product[1][k] = arr[1][i];
                    k++;
                }
            }
        } else
            throw new RuntimeException();
        return product;
    }
    public static void main(String[] args) {
        try {
            menu();
        } catch (RuntimeException e) {
            System.out.println("Error");
            System.exit(0);
        }
    }
    public static void menu() throws RuntimeErrorException {
        Scanner input = new Scanner(System.in);
        char polynomial;
        char polynomial2;
        String o_p;
        IPolynomialSolver solver = new PolynomialSolver();
        while (input.hasNext()) {
            switch (input.next()) {
            case "set":
                polynomial = input.next().charAt(0);
                solver.clearPolynomial(polynomial);
                String read = input.next().replaceAll("\\[|\\]", "");
                String[] str = read.split(",");
                int[][] specs = new int[2][str.length];
                if (str.length == 1 && str[0].isEmpty())
                    throw new RuntimeException();
                else {
                    for (int i = 0; i < str.length; i++) {
                        specs[0][i] = (Integer.parseInt(str[i]));
                        specs[1][i] = (str.length) - i - 1;
                    }
                }
                solver.setPolynomial(polynomial, specs);
                break;
            case "print":
                polynomial = input.next().charAt(0);
                o_p = solver.print(polynomial);
                System.out.println(o_p);
                break;
            case "clear":
                polynomial = input.next().charAt(0);
                solver.clearPolynomial(polynomial);
                System.out.println("[]");
                break;
            case "eval":
                polynomial = input.next().charAt(0);
                float point = input.nextFloat();
                float evaluation = solver.evaluatePolynomial(polynomial, point);
                System.out.println((int) evaluation);
                break;
            case "add":
                solver.clearPolynomial('R');
                polynomial = input.next().charAt(0);
                polynomial2 = input.next().charAt(0);
                int[][] arr1 = solver.add(polynomial, polynomial2);
                solver.setPolynomial('R', arr1);
                System.out.println(solver.print('R'));
                break;
            case "sub":
                solver.clearPolynomial('R');
                polynomial = input.next().charAt(0);
                polynomial2 = input.next().charAt(0);
                int[][] arr2 = solver.subtract(polynomial, polynomial2);
                solver.setPolynomial('R', arr2);
                System.out.println(solver.print('R'));
                break;
            case "mult":
                solver.clearPolynomial('R');
                polynomial = input.next().charAt(0);
                polynomial2 = input.next().charAt(0);
                int[][] arr3 = solver.multiply(polynomial, polynomial2);
                solver.setPolynomial('R', arr3);
                System.out.println(solver.print('R'));
                break;
            default:
                throw new RuntimeException();
            }
        }
    }
}
/*-----------------------------------------------------Linked List-----------------------------------------*/
class Node {
    private int exponent;
    private int coefficients;
    private Node next;
    private Node prev;
    public void setExp(int new_exp) {this.exponent = new_exp;}
    public int getExp () {return this.exponent;}
    public void setCoeff(int new_coeff) {this.coefficients = new_coeff;}
    public int getCoeff () {return this.coefficients;}
    public void setNext(Node new_next) {this.next = new_next;}
    public Node getNext () {return this.next;}
    public void setPrev(Node new_prev) {this.prev = new_prev;}
    public Node getPrev () {return this.prev;}
}

class DoubleLinkedList {
    Node Head = null;
    Node Last = null;
    public int length = 0;

    public void addFirst(int coeff, int exp) {
        Node new_node = new Node();
        new_node.setCoeff(coeff);
        new_node.setExp(exp);
        if (length == 0) {
            Head = new_node;
            Last = new_node;
        } else {
            new_node.setNext(Head);
            Head.setPrev(new_node);
            Head = new_node;
            length++;
        }
    }

    public void addLast(int coeff, int exp) {
        Node new_node = new Node();
        new_node.setCoeff(coeff);
        new_node.setExp(exp);
        if (length == 0) {
            Head = new_node;
            Last = new_node;
        } else {
            new_node.setPrev(Last);
            Last.setNext(new_node);
            Last = new_node;
        }
        length++;
    }

    public int get(int index) {

        Node new_node = Head;
        if (length != 0 && index >= 0 && index < length) {
            for (int i = 0; i < index; ++i)
                new_node = new_node.getNext();
        } else
            throw new RuntimeException();
        return new_node.getCoeff();
    }

    public int getExponent(int index) {
        Node new_node = Head;
        if (length != 0 && index >= 0 && index < length) {
            for (int i = 0; i < index; ++i)
                new_node = new_node.getNext();
            return new_node.getExp();
        } else
            throw new RuntimeException();
    }

    public void clear() {
        Node new_node = new Node();
        new_node.setNext(null);;
        new_node.setPrev(null);;
        if (length == 0)
            return;
        else {
            Head = new_node;
            Last = new_node;
        }
    }

    public boolean isEmpty() {
        if (length == 0)
            return true;
        else
            return false;
    }

    public void removeFirst() {
        if (length == 0)
            return;
        else if (length == 1)
            clear();
        else {
            Head = Head.getNext();
            Head.setPrev(null);;
        }
        length--;
    }

    public int size() {
        int count = 0;
        Node new_node = Head;
        while (new_node.getNext() != null) {
            count++;
            new_node = new_node.getNext();
        }
        return ++count;
    }
}
