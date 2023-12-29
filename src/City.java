import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class City extends AbstractElement implements Modifiable<City>, Cloneable, Printable {
    private int numberOfCompanies;
    private List<Company> listOfCompany = new ArrayList<>();

    private static int totalCities = 0;

    public enum CityField {
        NAME,
        POPULATION,
        SQUARE,
        NUMBER_OF_COMPANIES
    }

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(String name, int population, int square, int numberOfCompanies, List<Company> listOfCompany) {
        super(name, population, square);
        this.numberOfCompanies = numberOfCompanies;
        this.listOfCompany = listOfCompany;
    }


    public void setNumberOfCompanies(int numberOfCompanies) {
        this.numberOfCompanies = numberOfCompanies;
    }

    public int getNumberOfCompanies() {
        return numberOfCompanies;
    }

    public void setListOfCompany(List<Company> listOfCompany) {
        this.listOfCompany = listOfCompany;
    }

    public List<Company> getListOfCompany() {
        return listOfCompany;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof City city))
            return false;
        return this.name.equals(city.getName());
    }


    public void input(List<City> cityList) {
        boolean flag;
        System.out.println("\nВВОД ГОРОДА");
        do {
            flag = false;
            do {
                try {
                    name = inputName("города");
                    break;
                } catch (StringWithSmallLetterException e) {
                    System.out.println("Название города необходимо писать с заглавной буквы!");
                }
            } while (true);
            for (City otherCity : cityList)
                if ((this != otherCity) && (this.equals(otherCity))) {
                    System.out.println("Данный город уже есть в списке");
                    flag = true;
                }
        } while (flag);
        do {
            try {
                population = inputPopulation("города");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Население города не может быть отрицательным!");
            }
        } while (true);
        do {
            try {
                numberOfCompanies = inputNumber("компаний");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Количество компаний не может быть отрицательным!");
            }
        } while (true);
        do {
            try {
                square = inputSquare("города");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Площадь города не может быть отрицательной!");
            }
        } while (true);
        do {
            int number;
            System.out.println("\n\nВвод компании - 1");
            System.out.println("Ввод филиала компании - 2");
            System.out.print("Введите номер действия: ");
            do {
                while (!Main.scanner.hasNextInt()) {
                    System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите номер действия: ");
                    Main.scanner.next();
                }
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > 2))
                    System.out.println("Ошибка! Значение не может быть отрицательным!");
                Main.scanner.nextLine();
            } while ((number < 1) || (number > 2));
            if (number == 1) {
                Company company = new Company();
                company.input(listOfCompany, false);
                listOfCompany.add(company);
            } else {
                Branch branch = new Branch();
                branch.input(listOfCompany, true);
                listOfCompany.add(branch);
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить ввод компаний (y/n):"));
        incrementTotalCities();
    }

    @Override
    public String toString() {
        StringBuilder string;
        string = new StringBuilder(String.format(" * %-18s * %-22d * %-14d * %-9d * ", name, numberOfCompanies,
                square, population));
        if (listOfCompany.isEmpty())
            string.append(String.format("%-36s *\n", AuxiliaryClass.listIsEmpty));
        else
            string.append(String.format("%-36s *\n", listOfCompany.get(0).getName()));
        for (int i = 1; i < listOfCompany.size(); i++) {
            if (listOfCompany.get(i) instanceof Branch branch)
                string.append(String.format("*       *                    *                        *                *" +
                                "           * %-36s *\n",
                        listOfCompany.get(i).getName() + " - филиал"));
            else
                string.append(String.format("*       *                    *                        *                *" +
                                "           * %-36s *\n",
                        listOfCompany.get(i).getName()));
        }
        string.append("********************************************************************" +
                "*******************************************************\n");
        return string.toString();
    }

    public static void tableHeader() {
        System.out.print("*******************************************************************" +
                "********************************************************\n");
        System.out.print("* Номер *       Город        *  Количество компаний   * Площадь города " +
                "* Население *      Список компаний и филиалов      *\n");
        System.out.print("*******************************************************************" +
                "********************************************************\n");
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-18s * %-22d * %-14d * %-9d * ", number + 1, name, numberOfCompanies,
                square, population);
        if (listOfCompany.isEmpty())
            System.out.printf("%-36s *\n", AuxiliaryClass.listIsEmpty);
        else
            System.out.printf("%-36s *\n", listOfCompany.get(0).getName());
        for (int i = 1; i < listOfCompany.size(); i++) {
            System.out.printf("*       *                    *                        *           * %-36s *\n",
                    listOfCompany.get(i).getName());
        }
        System.out.print("*******************************************************************" +
                "********************************************************\n");
    }

    public int chooseCompany() {
        int number = 0;
        int i;
        int size = listOfCompany.size();
        if (size != 0) {
            Company.tableHeader();
            i = 1;
            for (Company company : listOfCompany) {
                System.out.printf("* %-5d", i);
                if (company instanceof Branch branch) {
                    System.out.print(branch);
                } else
                    System.out.print(company);
                i++;
            }
            do {
                System.out.print("Введите номер компании: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > size))
                    System.out.println("Компании под данным номером нет в списке");
            } while ((number < 1) || (number > size));
            Main.scanner.nextLine();
        }
        return number - 1;
    }

    public void changeFields(List<City> cityList) {
        boolean flag;
        int number;
        System.out.println("\nИЗМЕНЕНИЯ ПОЛЕЙ");
        tableHeader();
        System.out.printf("* %-5d", 1);
        System.out.print(this);
        do {
            System.out.println("1.Название города");
            System.out.println("2.Количество компаний");
            System.out.println("3.Площадь города");
            System.out.println("4.Население города");
            System.out.println("5.Список компаний");
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
                                name = inputName("города");
                                break;
                            } catch (StringWithSmallLetterException e) {
                                System.out.println("Название города необходимо писать с заглавной буквы!");
                            }
                        } while (true);
                        for (City otherCity : cityList)
                            if ((this != otherCity) && (this.equals(otherCity))) {
                                System.out.println("Данный город уже есть в списке");
                                flag = true;
                            }
                    } while (flag);
                }
                case 2 -> {
                    do {
                        try {
                            population = inputPopulation("города");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Население города не может быть отрицательным!");
                        }
                    } while (true);
                }
                case 3 -> {
                    do {
                        try {
                            square = inputSquare("города");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Площадь города не может быть отрицательной!");
                        }
                    } while (true);
                }
                case 4 -> {
                    do {
                        try {
                            numberOfCompanies = inputNumber("компаний");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Количество компаний не может быть отрицательным!");
                        }
                    } while (true);
                }
                case 5 ->
                        System.out.println("Для изменения списка компаний перейдите по соответствующей команде в меню");
                default -> {
                }
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить изменение полей в данном городе?"));
    }

    public void addNewCompany() {
        int number;
        if (listOfCompany.size() < numberOfCompanies) {
            Company company = new Company();
            company.input(listOfCompany, false);
            listOfCompany.add(company);
        } else {
            System.out.println("Достигнуто количество компаний соответствующее введенному числу - "
                    + numberOfCompanies);
            System.out.println("Для добавления новых компаний в данный список вам необходимо изменить ");
            System.out.println("число количества компаний в данном субъекте");
            if (AuxiliaryClass.answerYesOrNo("Желаете это сделать?")) {
                do {
                    number = inputNumber("компаний");
                    if (number <= listOfCompany.size())
                        System.out.println("Данное число меньше или соответствует уже имеющемуся");
                } while (number <= listOfCompany.size());
                numberOfCompanies = number;
                addNewCompany();
            }
        }
    }

    public void removeCompanyFromList() {
        int number;
        number = chooseCompany();
        Company.tableHeader();
        System.out.printf("* %-5d", 1);
        if (listOfCompany.get(number) instanceof Branch branch) {
            System.out.print(branch);
        } else
            System.out.print(listOfCompany.get(number));
        if (AuxiliaryClass.answerYesOrNo("Вы действительно желаете удалить данную компанию из списка?")) {
            listOfCompany.remove(number);
            Company.decrementTotalCompanies();
        }
    }

    public static void incrementTotalCities() {
        totalCities++;
    }

    public static void decrementTotalCities() {
        totalCities--;
    }

    public static void printTotalCities() {
        System.out.println("Всего вы внесли в список " + totalCities + " городов");
    }

    @Override
    public City clone() {
        try {
            City clone = (City) super.clone();
            List<Company> clonedCompanies = new ArrayList<>();
            for (Company company : listOfCompany)
                if (company instanceof Branch branch)
                    clonedCompanies.add(branch.clone());
                else
                    clonedCompanies.add(company.clone());
            clone.listOfCompany = clonedCompanies;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String getFormattedInfo() {
        return "Город: " + name + "; " + numberOfCompanies + "; " + square + "; " + population + ".";
    }

    public static int chooseField(String s) {
        int number;
        System.out.println("\n\nВыбор поля для " + s + " городов: ");
        System.out.println("1.Название");
        System.out.println("2.Население");
        System.out.println("3.Площадь");
        System.out.println("4.Количество компаний");
        do {
            System.out.print("Введите номер поля: ");
            while (!Main.scanner.hasNextInt()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите номер поля: ");
                Main.scanner.next();
            }
            number = Main.scanner.nextInt();
            if ((number < 1) || (number > 4))
                System.out.println("Поля под данным номером нет!");
        } while ((number < 1) || (number > 4));
        return number;
    }

    public static CityField getSortField(int number) {
        switch (number) {
            case 1 -> {
                return CityField.NAME;
            }
            case 2 -> {
                return CityField.POPULATION;
            }
            case 3 -> {
                return CityField.SQUARE;
            }
            case 4 -> {
                return CityField.NUMBER_OF_COMPANIES;
            }
        }
        return null;
    }

    public void sortCompanies() {
        System.out.println("\n\nСписок до сортировки\n\n");
        Company.tableHeader();
        int i = 1;
        for (Company company : listOfCompany) {
            System.out.printf("* %-5d", i);
            System.out.print(company);
            i++;
        }
        int number, modeNumber;
        number = Company.chooseField("сортировки списка");
        if ((number == 1) || (number == 6)) {
            System.out.println("1.В алфавитном порядке");
            System.out.println("2.В обратном алфавитному порядке");
        } else {
            System.out.println("1.По возрастанию");
            System.out.println("2.По убыванию");
        }
        do {
            System.out.print("Введите номер: ");
            while (!Main.scanner.hasNextInt()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите номер: ");
                Main.scanner.next();
            }
            modeNumber = Main.scanner.nextInt();
            if ((modeNumber < 1) || (modeNumber > 2))
                System.out.println("Действия под данным номером нет!");
        } while ((modeNumber < 1) || (modeNumber > 2));
        Main.scanner.nextLine();
        CompanyComparator comparator = new CompanyComparator(Company.getSortField(number));
        listOfCompany.sort(comparator);
        if (modeNumber == 2)
            Collections.reverse(listOfCompany);
        System.out.println("\n\nСписок после сортировки\n\n");
        Company.tableHeader();
        i = 1;
        for (Company company : listOfCompany) {
            System.out.printf("* %-5d", i);
            System.out.print(company);
            i++;
        }
    }
}

