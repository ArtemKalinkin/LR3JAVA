import java.util.ArrayList;
import java.util.List;

public class Subject extends AbstractElement implements Modifiable<Subject>, Cloneable, Printable {
    private int numberOfCities;
    private List<City> listOfCities = new ArrayList<>();
    private static int totalSubjects = 0;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, int numberOfCities, int population, int square, List<City> listOfCity) {
        super(name, population, square);
        this.numberOfCities = numberOfCities;
        this.listOfCities = listOfCity;
    }

    public void setNumberOfCities(int numberOfCities) {
        this.numberOfCities = numberOfCities;
    }

    public int getNumberOfCities() {
        return numberOfCities;
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
            do {
                try {
                    name = inputName("субъекта");
                    break;
                } catch (StringWithSmallLetterException e) {
                    System.out.println("Название субъекта необходимо писать с заглавной буквы!");
                }
            } while (true);
            for (Subject otherSubject : subjectList)
                if ((this != otherSubject) && (this.equals(otherSubject))) {
                    System.out.println("Данный субъект уже есть в списке");
                    flag = true;
                }
        } while (flag);
        do {
            try {
                numberOfCities = inputNumber("городов");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Количество городов не может быть отрицательным!");
            }
        } while (true);
        do {
            try {
                square = inputSquare("субъекта");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Площадь субъекта не может быть отрицательной!");
            }
        } while (true);
        do {
            try {
                population = inputPopulation("субъекта");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Население субъекта не может быть отрицательным!");
            }
        } while (true);
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

    @Override
    public String toString() {
        StringBuilder string;
        string = new StringBuilder(String.format(" * %-18s * %-18d * %-16d * %-9d * ", name, numberOfCities,
                square, population));
        if (listOfCities.isEmpty())
            string.append(AuxiliaryClass.listIsEmpty);
        else
            string.append(String.format("%-28s *\n", listOfCities.get(0).getName()));
        for (int i = 1; i < listOfCities.size(); i++) {
            string.append(String.format("*       *                    *             " +
                    "       *                  *           * %-28s *\n", listOfCities.get(i).getName()));
        }
        string.append("****************************************************************" +
                "*************************************************\n");
        return string.toString();
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
            i = 1;
            for (City city : listOfCities) {
                System.out.printf("* %-5d", i);
                System.out.print(city);
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
        System.out.printf("* %-5d", 1);
        System.out.print(this);
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
                        do {
                            try {
                                name = inputName("субъекта");
                                break;
                            } catch (StringWithSmallLetterException e) {
                                System.out.println("Название субъекта необходимо писать с заглавной буквы!");
                            }
                        } while (true);
                        for (Subject otherSubject : subjectList)
                            if ((this != otherSubject) && (this.equals(otherSubject))) {
                                System.out.println("Данный субъект уже есть в списке");
                                flag = true;
                            }
                    } while (flag);
                }
                case 2 -> {
                    do {
                        try {
                            numberOfCities = inputNumber("городов");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Количество городов не может быть отрицательным!");
                        }
                    } while (true);
                }
                case 3 -> {
                    do {
                        try {
                            square = inputSquare("субъекта");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Площадь субъекта не может быть отрицательной!");
                        }
                    } while (true);
                }
                case 4 -> {
                    do {
                        try {
                            population = inputPopulation("субъекта");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Население субъекта не может быть отрицательным!");
                        }
                    } while (true);
                }
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
                    number = inputNumber("городов");
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
        System.out.printf("* %-5d", 1);
        System.out.print(listOfCities.get(number));
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

    @Override
    public Subject clone() {
        try {
            Subject clone = (Subject) super.clone();
            List<City> clonedCities = new ArrayList<>();
            for (City city : listOfCities)
                clonedCities.add(city.clone());
            clone.listOfCities = clonedCities;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String getFormattedInfo() {
        return "Субъект: " + name + "; " + numberOfCities + "; " + square + "; " + population + ".";
    }
}
