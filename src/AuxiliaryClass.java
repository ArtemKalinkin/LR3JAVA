import java.util.Scanner;

public class AuxiliaryClass {
    public static boolean answerYesOrNo(String s) {
        Scanner scanner = new Scanner(System.in);
        String answer;
        do {
            System.out.println("Желаете продолжить ввод " + s);
            answer = scanner.nextLine();
            if ((answer.equals("No")) || (answer.equals("n")) || (answer.equals("N")) || (answer.equals("no"))) {
                scanner.close();
                return false;
            } else if ((answer.equals("Нет")) || (answer.equals("нет"))) {
                scanner.close();
                return false;
            } else if ((answer.equals("yes")) || (answer.equals("y")) || (answer.equals("Y")) || (answer.equals("Yes"))) {
                scanner.close();
                return true;
            } else if ((answer.equals("Да")) || (answer.equals("да"))) {
                scanner.close();
                return true;
            } else {
                System.out.println("Некорректный ответ!");
                System.out.println("Варианты положительных ответов: Да, да, Yes, yes, Y, y");
                System.out.println("Варианты отрицательных ответов: Нет, нет, No, no, N, n");
            }
        } while (true);
    }
}
