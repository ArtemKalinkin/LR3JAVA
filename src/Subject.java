import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String name;
    private int numberOfCities;
    private int population;
    private int square;
    private List<City> listOfCities = new ArrayList<>();

    private static int totalSubjects = 0;

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

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Subject subject))
            return false;
        return this.name.equals(subject.getName());
    }

    public void input(List<Subject> subjectList) {
        boolean flag;
        System.out.println("\nВВОД СУБЪЕКТА");
        do {
            flag = false;
            name = AuxiliaryClass.inputNameOfSomething("субъекта");
            for (Subject otherSubject : subjectList)
                if ((this != otherSubject) && (this.equals(otherSubject))) {
                    System.out.println("Данный субъект уже есть в списке");
                    flag = true;
                }
        } while (flag);
        numberOfCities = AuxiliaryClass.inputNumberOfSomething("городов");
        square = AuxiliaryClass.inputSquareOfSomething("субъекта");
        population = AuxiliaryClass.inputPopulationOfSomething("субъекта");
        do {
            City city = new City();
            city.input(listOfCities);
            listOfCities.add(city);
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить ввод городов (y/n):"));
        incrementTotalSubjects();
    }

    public static void tableHeader() {
        System.out.print("*********************************************************" +
                "********************************************************\n");
        System.out.print("* Номер *       Субъект      * Количество городов * Площадь субъекта " +
                "* Население *        Список городов        *\n");
        System.out.print("*********************************************************" +
                "********************************************************\n");
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-18s * %-18d * ", number + 1, name, numberOfCities);
        if (listOfCities.isEmpty())
            System.out.printf("%-16d * %-9d * %-28s *\n", square, population, AuxiliaryClass.listIsEmpty);
        else
            System.out.printf("%-16d * %-9d * %-28s *\n", square, population, listOfCities.get(0).getName());
        for (int i = 1; i < listOfCities.size(); i++) {
            System.out.printf("*       *                    *             " +
                    "       *                  *           * %-28s *\n", listOfCities.get(i).getName());
        }
        System.out.print("****************************************************************" +
                "*************************************************\n");
    }

    public int chooseCity() {
        int number = 0;
        int i;
        int size = listOfCities.size();
        if (size != 0) {
            City.tableHeader();
            i = 0;
            for (City city : listOfCities) {
                city.output(i);
                i++;
            }
            do {
                System.out.print("Введите номер города: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > size))
                    System.out.println("Города под данным номером нет в списке");
            } while ((number < 1) || (number > size));
            Main.scanner.nextLine();
        }
        return number - 1;
    }

    public void changeFields(List<Subject> subjectList) {
        boolean flag;
        int number;
        System.out.println("\nИЗМЕНЕНИЯ ПОЛЕЙ");
        tableHeader();
        output(0);
        do {
            System.out.println("1.Название субъекта");
            System.out.println("2.Количество городов");
            System.out.println("3.Площадь субъекта");
            System.out.println("4.Население субъекта");
            System.out.println("5.Список городов");
            do {
                System.out.print("Введите номер поля, который желаете изменить: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > 5))
                    System.out.println("Поля с данным номером нет");
            } while ((number < 1) || (number > 5));
            Main.scanner.nextLine();
            switch (number) {
                case 1 -> {
                    do {
                        flag = false;
                        name = AuxiliaryClass.inputNameOfSomething("субъекта");
                        for (Subject otherSubject : subjectList)
                            if ((this != otherSubject) && (this.equals(otherSubject))) {
                                System.out.println("Данный субъект уже есть в списке");
                                flag = true;
                            }
                    } while (flag);
                }
                case 2 -> numberOfCities = AuxiliaryClass.inputNumberOfSomething("городов");
                case 3 -> square = AuxiliaryClass.inputSquareOfSomething("субъекта");
                case 4 -> population = AuxiliaryClass.inputPopulationOfSomething("субъекта");
                case 5 ->
                        System.out.println("Для изменения списка городов перейдите по соответствующей команде в меню");
                default -> {
                }
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить изменение полей в данном субъекте?"));

    }

    public void addNewCity() {
        int number;
        if (listOfCities.size() < numberOfCities) {
            City city = new City();
            city.input(listOfCities);
            listOfCities.add(city);
        } else {
            System.out.println("Достигнуто количество городов соответствующее введенному числу - "
                    + numberOfCities);
            System.out.println("Для добавления новых городов в данный список вам необходимо изменить ");
            System.out.println("число количества городов в данном субъекте");
            if (AuxiliaryClass.answerYesOrNo("Желаете это сделать?")) {
                do {
                    number = AuxiliaryClass.inputNumberOfSomething("городов");
                    if (number <= listOfCities.size())
                        System.out.println("Данное число меньше или соответствует уже имеющемуся");
                } while (number <= listOfCities.size());
                numberOfCities = number;
                addNewCity();
            }
        }
    }

    public void removeCityFromList() {
        int number;
        number = chooseCity();
        City.tableHeader();
        listOfCities.get(number).output(0);
        if (AuxiliaryClass.answerYesOrNo("Вы действительно желаете удалить данный город из списка?")) {
            listOfCities.remove(number);
            City.decrementTotalCities();
        }
    }

    public static void incrementTotalSubjects() {
        totalSubjects++;
    }

    public static void decrementTotalSubjects() {
        totalSubjects--;
    }

    public static void printTotalSubjects() {
        System.out.println("Всего вы внесли в список " + totalSubjects + " субъектов");
    }
}
