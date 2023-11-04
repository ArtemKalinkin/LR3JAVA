import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class City {
    private String name;
    private int population;
    private int numberOfEnterprises;
    private List<Enterprise> listOfEnterprises = new ArrayList<>();

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(String name, int population, int numberOfEnterprises, List<Enterprise> listOfEnterprises) {
        this.name = name;
        this.population = population;
        this.numberOfEnterprises = numberOfEnterprises;
        this.listOfEnterprises = listOfEnterprises;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setNumberOfEnterprises(int numberOfEnterprises) {
        this.numberOfEnterprises = numberOfEnterprises;
    }

    public int getNumberOfEnterprises() {
        return numberOfEnterprises;
    }

    public void setListOfEnterprises(List<Enterprise> listOfEnterprises) {
        this.listOfEnterprises = listOfEnterprises;
    }

    public List<Enterprise> getListOfEnterprises() {
        return listOfEnterprises;
    }

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ВВОД ГОРОДА\n");

        do {
            System.out.println("Введите название города: ");
            while (!scanner.hasNextLine()) {
                System.out.println("Ошибка ввода!\nВведите название города: ");
                scanner.next();
            }
            name = scanner.nextLine();
            if (name.isBlank())
                System.out.println("Данное поле не может быть пустым");
        } while (name.isBlank());

        do {
            System.out.println("Введите население города: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите название города: ");
                scanner.next();
            }
            population = scanner.nextInt();
            if (population < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (population < 0);

        do {
            System.out.println("Введите количество предприятий: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите количество предприятий: ");
                scanner.next();
            }
            numberOfEnterprises = scanner.nextInt();
            if (numberOfEnterprises < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (numberOfEnterprises < 0);

        scanner.close();

        do {
            Enterprise enterprise = new Enterprise();
            enterprise.input();
            listOfEnterprises.add(enterprise);
        } while (AuxiliaryClass.answerYesOrNo("предприятий (y/n):"));
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-18s * %-22d * %-9d * ", number + 1, name, numberOfEnterprises, population);
        System.out.printf("%-36s *\n", listOfEnterprises.getFirst());
        for (int i = 1; i < listOfEnterprises.size(); i++) {
            System.out.printf("*       *                    *                        *           * %-36s *\n",
                    listOfEnterprises.get(i).getName());
        }
        System.out.print("*******************************************************" +
                "***************************************************\n");
    }
}
