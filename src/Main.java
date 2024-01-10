import java.util.Scanner;

public class Main {
    public static String calc(String input) throws Exception {
        int numberOne;
        int numberTwo;
        String arithmeticExpression;
        String result;
        boolean isRoman;
        String[] aValues = input.split("[+\\-*/]");
        if (aValues.length != 2) throw new Exception("Должно быть два операнда");
        arithmeticExpression = detectOperation(input);

        if (arithmeticExpression == null) throw new Exception("Неподдерживаемая математическая операция");
        if (Roman.isRoman(aValues[0]) && Roman.isRoman(aValues[1])) {
            numberOne = Roman.convertToArabian(aValues[0]);
            numberTwo = Roman.convertToArabian(aValues[1]);
            isRoman = true;
        } else if (!Roman.isRoman(aValues[0]) && !Roman.isRoman(aValues[1])) {
            numberOne = Integer.parseInt(aValues[0]);
            numberTwo = Integer.parseInt(aValues[1]);
            isRoman = false;
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }

        if (numberOne > 10 || numberTwo > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arabian = calc(numberOne, numberTwo, arithmeticExpression);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String inputValue) {
        if (inputValue.contains("+")) {
            return "+";
        } else if (inputValue.contains("-")) {
            return "-";
        } else if (inputValue.contains("*")) {
            return "*";
        } else if (inputValue.contains("/")) {
            return "/";
        } else {
            return null;
        }
    }

    static int calc(int valueOne, int valueTwo, String arithmeticExpression) {

        if (arithmeticExpression.equals("+")) {
            return valueOne + valueTwo;
        } else if (arithmeticExpression.equals("-")) {
            return valueOne - valueTwo;
        } else if (arithmeticExpression.equals("*")) {
            return valueOne * valueTwo;
        } else {
            return valueOne / valueTwo;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Введите два числа (арабских или римских): ");
        String inputValue = new Scanner(System.in).nextLine();
        System.out.println(calc(inputValue));
    }
}
