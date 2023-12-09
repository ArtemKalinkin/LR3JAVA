import java.util.ArrayList;
import java.util.List;


public class City {
    private String name;
    private int population;
    private int numberOfCompany;
    private List<Company> listOfCompany = new ArrayList<>();

    private static int totalCities = 0;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(String name, int population, int numberOfCompany, List<Company> listOfCompany) {
        this.name = name;
        this.population = population;
        this.numberOfCompany = numberOfCompany;
        this.listOfCompany = listOfCompany;
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

    public void setNumberOfCompany(int numberOfCompany) {
        this.numberOfCompany = numberOfCompany;
    }

    public int getNumberOfCompany() {
        return numberOfCompany;
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
                    name = AuxiliaryClass.inputNameOfSomething("города");
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
                population = AuxiliaryClass.inputPopulationOfSomething("города");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Население города не может быть отрицательным!");
            }
        } while (true);
        do {
            try {
                numberOfCompany = AuxiliaryClass.inputNumberOfSomething("компаний");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Количество компаний не может быть отрицательным!");
            }
        } while (true);
        do {
            Company company = new Company();
            company.input(listOfCompany);
            listOfCompany.add(company);
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить ввод компаний (y/n):"));
        incrementTotalCities();
    }

    public static void tableHeader() {
        System.out.print("**************************************************" +
                "********************************************************\n");
        System.out.print("* Номер *       Город        *  Количество компаний   " +
                "* Население *           Список компаний            *\n");
        System.out.print("**************************************************" +
                "********************************************************\n");
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-18s * %-22d * %-9d * ", number + 1, name, numberOfCompany, population);
        if (listOfCompany.isEmpty())
            System.out.printf("%-36s *\n", AuxiliaryClass.listIsEmpty);
        else
            System.out.printf("%-36s *\n", listOfCompany.get(0).getName());
        for (int i = 1; i < listOfCompany.size(); i++) {
            System.out.printf("*       *                    *                        *           * %-36s *\n",
                    listOfCompany.get(i).getName());
        }
        System.out.print("*******************************************************" +
                "***************************************************\n");
    }

    public int chooseCompany() {
        int number = 0;
        int i;
        int size = listOfCompany.size();
        if (size != 0) {
            Company.tableHeader();
            i = 0;
            for (Company company : listOfCompany) {
                company.output(i);
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
        output(0);
        do {
            System.out.println("1.Название города");
            System.out.println("2.Количество компаний");
            System.out.println("3.Население города");
            System.out.println("4.Список компаний");
            do {
                System.out.print("Введите номер поля, который желаете изменить: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > 4))
                    System.out.println("Поля с данным номером нет");
            } while ((number < 1) || (number > 4));
            Main.scanner.nextLine();
            switch (number) {
                case 1 -> {
                    do {
                        flag = false;
                        do {
                            try {
                                name = AuxiliaryClass.inputNameOfSomething("города");
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
                            population = AuxiliaryClass.inputPopulationOfSomething("города");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Население города не может быть отрицательным!");
                        }
                    } while (true);
                }
                case 3 -> {
                    do {
                        try {
                            numberOfCompany = AuxiliaryClass.inputNumberOfSomething("компаний");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Количество компаний не может быть отрицательным!");
                        }
                    } while (true);
                }
                case 4 ->
                        System.out.println("Для изменения списка компаний перейдите по соответствующей команде в меню");
                default -> {
                }
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить изменение полей в данном городе?"));
    }

    public void addNewCompany() {
        int number;
        if (listOfCompany.size() < numberOfCompany) {
            Company company = new Company();
            company.input(listOfCompany);
            listOfCompany.add(company);
        } else {
            System.out.println("Достигнуто количество компаний соответствующее введенному числу - "
                    + numberOfCompany);
            System.out.println("Для добавления новых компаний в данный список вам необходимо изменить ");
            System.out.println("число количества компаний в данном субъекте");
            if (AuxiliaryClass.answerYesOrNo("Желаете это сделать?")) {
                do {
                    number = AuxiliaryClass.inputNumberOfSomething("компаний");
                    if (number <= listOfCompany.size())
                        System.out.println("Данное число меньше или соответствует уже имеющемуся");
                } while (number <= listOfCompany.size());
                numberOfCompany = number;
                addNewCompany();
            }
        }
    }

    public void removeCompanyFromList() {
        int number;
        number = chooseCompany();
        Company.tableHeader();
        listOfCompany.get(number).output(0);
        if (AuxiliaryClass.answerYesOrNo("Вы действительно желаете удалить данное компанию из списка?")) {
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

}

