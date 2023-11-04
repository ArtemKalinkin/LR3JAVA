import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Country {
    private String name;
    private int numberOfSubjects;
    private long netProfitFromEnterprises;
    private int population;
    private int square;
    private long income;
    private long expenses;
    private long budgetDeficitOrSurplus;
    private List<Subject> listOfSubjects = new ArrayList<>();

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, int numberOfSubjects, long netProfitFromEnterprises, int population,
                   int square, long income, long expenses, List<Subject> listOfSubjects) {
        this.name = name;
        this.numberOfSubjects = numberOfSubjects;
        this.netProfitFromEnterprises = netProfitFromEnterprises;
        this.population = population;
        this.square = square;
        this.income = income;
        this.expenses = expenses;
        this.budgetDeficitOrSurplus = income - expenses;
        this.listOfSubjects = listOfSubjects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumberOfSubjects(int numberOfSubjects) {
        this.numberOfSubjects = numberOfSubjects;
    }

    public int getNumberOfSubjects() {
        return numberOfSubjects;
    }


    public long getNetProfitFromEnterprises() {
        return netProfitFromEnterprises;
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

    public void setIncome(long income) {
        this.income = income;
        this.budgetDeficitOrSurplus = this.income - this.expenses;
    }

    public long getIncome() {
        return income;
    }

    public void setExpenses(long expenses) {
        this.expenses = expenses;
        this.budgetDeficitOrSurplus = this.income - this.expenses;
    }

    public long getExpenses() {
        return expenses;
    }


    public long getBudgetDeficitOrSurplus() {
        return budgetDeficitOrSurplus;
    }

    public void setListOfSubjects(List<Subject> listOfSubjects) {
        this.listOfSubjects = listOfSubjects;
    }

    public List<Subject> getListOfSubjects() {
        return listOfSubjects;
    }

    public long calculateProfitFromEnterprises() {
        long profit = 0;
        List<City> cities;
        List<Enterprise> enterprises;
        for (Subject subject : listOfSubjects) {
            cities = subject.getListOfCities();
            for (City city : cities) {
                enterprises = city.getListOfEnterprises();
                for (Enterprise enterprise : enterprises) {
                    profit += enterprise.getNetProfit();
                }
            }
        }
        return profit;
    }

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ВВОД СТРАНЫ\n");

        do {
            System.out.print("Введите название страны:");
            while (!scanner.hasNextLine()) {
                System.out.println("Ошибка ввода!\nВведите название страны: ");
                scanner.next();
            }
            name = scanner.nextLine();
            if (name.isBlank())
                System.out.println("Данное поле не может быть пустым");
        } while (name.isBlank());

        do {
            System.out.println("Введите население страны: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите название страны: ");
                scanner.next();
            }
            population = scanner.nextInt();
            if (population < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (population < 0);

        do {
            System.out.println("Введите площадь страны: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите площадь страны: ");
                scanner.next();
            }
            square = scanner.nextInt();
            if (square < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (square < 0);

        do {
            System.out.println("Введите доход страны: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите доход страны: ");
                scanner.next();
            }
            income = scanner.nextInt();
            if (income < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (income < 0);


        do {
            System.out.println("Введите расходы страны: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите расходы страны: ");
                scanner.next();
            }
            expenses = scanner.nextInt();
            if (expenses < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (expenses < 0);

        budgetDeficitOrSurplus = income - expenses;

        do {
            System.out.println("Введите количество субъектов: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите количество субъектов: ");
                scanner.next();
            }
            numberOfSubjects = scanner.nextInt();
            if (numberOfSubjects < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (numberOfSubjects < 0);

        scanner.close();

        do {
            Subject subject = new Subject();
            subject.input();
            listOfSubjects.add(subject);
        } while (AuxiliaryClass.answerYesOrNo("субъектов (y/n):"));
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-18s * %-20d * %-14d * ", number + 1, name, numberOfSubjects, square);
        System.out.printf("%-9d * %-19d * %-14d * ", population, netProfitFromEnterprises, income);
        System.out.printf("%-15d * %-24d ", expenses, budgetDeficitOrSurplus);
        System.out.printf("%-25s *\n", listOfSubjects.getFirst().getName());
        for (int i = 1; i < listOfSubjects.size(); i++) {
            System.out.printf("*       *                    *                      *                *           *" +
                            "                     *                *                 *                          * %-25s *\n",
                    listOfSubjects.get(i).getName());
        }
        System.out.print("****************************************************************************************" +
                "**********************************************************************************************************\n");
    }
}
