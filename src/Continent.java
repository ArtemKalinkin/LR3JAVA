import java.util.List;
import java.util.Scanner;

public class Continent {
    private String name;
    private int numberOfCountries;
    private int square;
    private List<Country> listOfCountries;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    public Continent(String name, int numberOfCountries, int square, List<Country> listOfCountries) {
        this.name = name;
        this.numberOfCountries = numberOfCountries;
        this.square = square;
        this.listOfCountries = listOfCountries;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumberOfCountries(int numberOfCountries) {
        this.numberOfCountries = numberOfCountries;
    }

    public int getNumberOfCountries() {
        return numberOfCountries;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getSquare() {
        return square;
    }

    public void setListOfCountries(List<Country> listOfCountries) {
        this.listOfCountries = listOfCountries;
    }

    public List<Country> getListOfCountries() {
        return listOfCountries;
    }

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ВВОД КОНТИНЕНТА\n");

        do {
            System.out.print("Введите название континента:");
            while (!scanner.hasNextLine()) {
                System.out.println("Ошибка ввода!\nВведите название континента: ");
                scanner.next();
            }
            name = scanner.nextLine();
            if (name.isBlank())
                System.out.println("Данное поле не может быть пустым");
        } while (name.isBlank());

        do {
            System.out.println("Введите количество стран: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите количество стран: ");
                scanner.next();
            }
            numberOfCountries = scanner.nextInt();
            if (numberOfCountries < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (numberOfCountries < 0);

        do {
            System.out.println("Введите площадь континента: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите площадь континента: ");
                scanner.next();
            }
            square = scanner.nextInt();
            if (square < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (square < 0);

        scanner.close();

        do {
            Country country = new Country();
            country.input();
            listOfCountries.add(country);
        } while (AuxiliaryClass.answerYesOrNo("стран (y/n):"));
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-18s * %-16d * %-18d * ", number + 1, name, numberOfCountries, square);
        System.out.printf("%-30s *\n", listOfCountries.getFirst().getName());
        for (int i = 1; i < listOfCountries.size(); i++) {
            System.out.printf("*       *                    *                  *                    * %-30s *\n",
                    listOfCountries.get(i).getName());
        }
        System.out.print("****************************************" +
                "***************************************************************\n");
    }
}
