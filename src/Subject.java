import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subject {
    private String name;
    private int numberOfCities;
    private int population;
    private int square;
    private List<City> listOfCities = new ArrayList<>();

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, int numberOfCities, int population, int square, List<City> listOfCity) {
        this.name = name;
        this.numberOfCities = numberOfCities;
        this.population = population;
        this.square = square;
        this.listOfCities = listOfCity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumberOfCities(int numberOfCities) {
        this.numberOfCities = numberOfCities;
    }

    public int getNumberOfCities() {
        return numberOfCities;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getSquare() {
        return square;
    }

    public void setListOfCities(List<City> listOfCity) {
        this.listOfCities = listOfCity;
    }

    public List<City> getListOfCities() {
        return listOfCities;
    }


    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ВВОД СУБЪЕКТА\n");
        do {
            System.out.print("Введите название субъекта:");
            while (!scanner.hasNextLine()) {
                System.out.println("Ошибка ввода!\nВведите название субъекта: ");
                scanner.next();
            }
            name = scanner.nextLine();
            if (name.isBlank())
                System.out.println("Данное поле не может быть пустым");
        } while (name.isBlank());

        do {
            System.out.println("Введите население субъекта: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите название субъекта: ");
                scanner.next();
            }
            population = scanner.nextInt();
            if (population < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (population < 0);

        do {
            System.out.println("Введите площадь субъекта: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите площадь субъекта: ");
                scanner.next();
            }
            square = scanner.nextInt();
            if (square < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (square < 0);

        do {
            System.out.println("Введите количество городов: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите количество городов: ");
                scanner.next();
            }
            numberOfCities = scanner.nextInt();
            if (numberOfCities < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (numberOfCities < 0);

        scanner.close();

        do {
            City city = new City();
            city.input();
            listOfCities.add(city);
        } while (AuxiliaryClass.answerYesOrNo("городов (y/n):"));
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-18s * %-18d * ", number + 1, name, numberOfCities);
        System.out.printf("%-16d * %-9d * %-28s *\n", square, population, listOfCities.getFirst());
        for (int i = 1; i < listOfCities.size(); i++) {
            System.out.printf("*       *                    *             " +
                    "       *                  *           * %-28s *\n", listOfCities.get(i).getName());
        }
        System.out.print("****************************************************************" +
                "*************************************************\n");
    }
}
