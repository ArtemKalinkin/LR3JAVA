import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int number;
        int numberOfMenu;

        List<Continent> listOfContinents = new ArrayList<>();
        do {
            if (listOfContinents.isEmpty()) {
                System.out.println("ВВОД НОВОГО СПИСКА\n\n");
                do {
                    Continent continent = new Continent();
                    continent.input(listOfContinents);
                    listOfContinents.add(continent);
                } while ((listOfContinents.size() < 6) &&
                        (AuxiliaryClass.answerYesOrNo("Желаете продолжить ввод континентов (y/n):")));
                numberOfMenu = 0;
            } else {
                System.out.println("\n\n\n\n__МЕНЮ__");
                System.out.println("1.Вывести на экран");
                System.out.println("2.Сравнить две страны");
                System.out.println("3.Изменить поля");
                System.out.println("4.Удалить элемент из списка");
                System.out.println("5.Добавить новый элемент в список");
                System.out.println("6.Узнать количество введенных элементов");
                System.out.println("7.Завершить работу");

                do {
                    System.out.print("\nВведите номер действия: ");
                    while (!Main.scanner.hasNextInt()) {
                        System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите номер действия: ");
                        Main.scanner.next();
                    }
                    numberOfMenu = Main.scanner.nextInt();
                } while ((numberOfMenu < 1) || (numberOfMenu > 7));
                switch (numberOfMenu) {
                    case 1:
                        number = AuxiliaryClass.menuOutput();
                        AuxiliaryClass.outputAll(listOfContinents, number);
                        break;
                    case 2:
                        AuxiliaryClass.compareTwoCountries(listOfContinents);
                        break;
                    case 3:
                        AuxiliaryClass.changeFieldsOfSomething(listOfContinents);
                        break;
                    case 4:
                        AuxiliaryClass.addOrRemoveSomething(listOfContinents, false);
                        break;
                    case 5:
                        AuxiliaryClass.addOrRemoveSomething(listOfContinents, true);
                        break;
                    case 6:
                        AuxiliaryClass.printInfoOfTotalElements();
                        break;
                    case 7:
                        if (!AuxiliaryClass.answerYesOrNo("Вы действительно желаете завершить работу?"))
                            numberOfMenu = 0;
                        break;

                    default:
                        break;
                }
            }
        } while (numberOfMenu != 6);
        scanner.close();
    }


}