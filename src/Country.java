import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    private int numberOfSubjects;
    private long netProfitFromCompanies;
    private int population;
    private int square;
    private long income;
    private long expenses;
    private long budgetDeficitOrSurplus;
    private List<Subject> listOfSubjects = new ArrayList<>();

    private static int totalCountries = 0;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, int numberOfSubjects, long netProfitFromCompanies, int population,
                   int square, long income, long expenses, List<Subject> listOfSubjects) {
        this.name = name;
        this.numberOfSubjects = numberOfSubjects;
        this.netProfitFromCompanies = netProfitFromCompanies;
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


    public void setNetProfitFromCompanies(long netProfitFromCompanies) {
        this.netProfitFromCompanies = netProfitFromCompanies;
    }

    public long getNetProfitFromCompanies() {
        return netProfitFromCompanies;
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


    public void setBudgetDeficitOrSurplus(long budgetDeficitOrSurplus) {
        this.budgetDeficitOrSurplus = budgetDeficitOrSurplus;
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

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Country))
            return false;
        Country country = (Country) object;
        return this.name.equals(country.getName());
    }


    public long inputIncome() {
        long income;
        do {
            System.out.print("Введите доход страны: ");
            while (!Main.scanner.hasNextLong()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите доход страны: ");
                Main.scanner.next();
            }
            income = Main.scanner.nextLong();
            if (income < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (income < 0);

        Main.scanner.nextLine();
        return income;
    }

    public long inputExpenses() {
        long expenses;
        do {
            System.out.print("Введите расходы страны: ");
            while (!Main.scanner.hasNextLong()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите расходы страны: ");
                Main.scanner.next();
            }
            expenses = Main.scanner.nextLong();
            if (expenses < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (expenses < 0);

        Main.scanner.nextLine();
        return expenses;
    }

    public AuxiliaryClass calculateProfitFromCompanies() {
        long profit = 0;
        List<City> cities;
        List<Company> companies;
        for (Subject subject : listOfSubjects) {
            cities = subject.getListOfCities();
            for (City city : cities) {
                companies = city.getListOfCompany();
                for (Company company : companies) {
                    profit += company.getNetProfit();
                }
            }
        }
        return new AuxiliaryClass(profit);
    }

    public void setAddressOfCompanies() {
        for (Subject subject : listOfSubjects)
            for (City city : subject.getListOfCities())
                for (Company company : city.getListOfCompany())
                    company.setAddress(name + ", " + subject.getName() + ", " + city.getName());
    }

    public void input(List<Country> countryList) {
        boolean flag;
        System.out.println("\nВВОД СТРАНЫ");
        do {
            flag = false;
            name = AuxiliaryClass.inputNameOfSomething("страны");
            for (Country otherCountry : countryList)
                if ((this != otherCountry) && (this.equals(otherCountry))) {
                    System.out.println("Данная страна уже есть в списке");
                    flag = true;
                }
        } while (flag);
        numberOfSubjects = AuxiliaryClass.inputNumberOfSomething("субъектов");
        square = AuxiliaryClass.inputSquareOfSomething("страны");
        population = AuxiliaryClass.inputPopulationOfSomething("страны");
        income = inputIncome();
        expenses = inputExpenses();
        budgetDeficitOrSurplus = income - expenses;
        do {
            Subject subject = new Subject();
            subject.input(listOfSubjects);
            listOfSubjects.add(subject);
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить ввод субъектов (y/n):"));
        netProfitFromCompanies = calculateProfitFromCompanies().getValue();
        setAddressOfCompanies();
    }

    public static void tableHeader() {
        System.out.print("***************************************" +
                "*********************************************************************************************" +
                "**************************************************************\n");
        System.out.print("* Номер *       Страна       * Количество субъектов * Площадь страны * Население *" +
                "  Прибыль компаний   *     Доходы     *     Расходы     * Профицит/дефицит бюджета *" +
                "      Список субъектов     *\n");
        System.out.print("***************************************" +
                "*********************************************************************************************" +
                "**************************************************************\n");
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-18s * %-20d * %-14d * ", number + 1, name, numberOfSubjects, square);
        System.out.printf("%-9d * %-19d * %-14d * ", population, netProfitFromCompanies, income);
        System.out.printf("%-15d * %-24d ", expenses, budgetDeficitOrSurplus);
        if (listOfSubjects.isEmpty())
            System.out.printf("* %-25s *\n", AuxiliaryClass.listIsEmpty);
        else
            System.out.printf("* %-25s *\n", listOfSubjects.get(0).getName());
        for (int i = 1; i < listOfSubjects.size(); i++) {
            System.out.printf("*       *                    *                      *                *           *" +
                            "                     *                *                 *                          * %-25s *\n",
                    listOfSubjects.get(i).getName());
        }
        System.out.print("****************************************************************************************" +
                "**********************************************************************************************************\n");
    }

    public int chooseSubject() {
        int number = 0;
        int i;
        int size = listOfSubjects.size();
        if (size != 0) {
            Subject.tableHeader();
            i = 0;
            for (Subject subject : listOfSubjects) {
                subject.output(i);
                i++;
            }
            do {
                System.out.print("Введите номер субъекта: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > size))
                    System.out.println("Субъекта под данным номером нет в списке");
            } while ((number < 1) || (number > size));
            Main.scanner.nextLine();
        }
        return number - 1;
    }

    public int compare(Country secondCountry) {
        int percentageOfSquare;
        int percentageOfPopulation;
        int percentageOfProfits;
        int percentageOfIncome;
        int percentageOfExpenses;
        int flag = 0;
        int numberOne = 0, numberTwo = 0;
        int value = 0;
        if ((this.square == 0) || (secondCountry.square == 0))
            flag = 1;
        if ((this.population == 0) || (secondCountry.population == 0))
            flag = 2;
        if ((this.netProfitFromCompanies == 0) || (secondCountry.netProfitFromCompanies == 0))
            flag = 3;
        if ((this.income == 0) || (secondCountry.income == 0))
            flag = 4;
        if ((this.expenses == 0) || (secondCountry.expenses == 0))
            flag = 5;
        switch (flag) {
            case 1:
                System.out.println("Площадь одной из стран записана некорректно и равна 0");
                break;
            case 2:
                System.out.println("Население одной из стран записано некорректно и равно 0");
                break;
            case 3:
                System.out.println("Прибыль от компаний одной из стран записана некорректно и равна 0");
                break;
            case 4:
                System.out.println("Доход одной из стран записан некорректно и равен 0");
                break;
            case 5:
                System.out.println("Расходы одной из стран записаны некорректно и равны 0");
                break;
            default:
                break;
        }
        if (flag == 0) {
            System.out.println("\nПроцентное соотношение характеристик стран");
            System.out.printf("1.%s     2.%s\n", this.name, secondCountry.name);
            // Площадь
            if (this.square > secondCountry.square) {
                percentageOfSquare = AuxiliaryClass.calculateByPercentFirstNumberIsGreaterThanSecond(this.square,
                        secondCountry.square);
                System.out.printf("Площадь страны - %s больше площади страны - %s на %d %%\n",
                        this.name, secondCountry.name, percentageOfSquare);
                numberOne++;
            } else if (this.square < secondCountry.square) {
                percentageOfSquare = AuxiliaryClass.calculateByPercentFirstNumberIsGreaterThanSecond(secondCountry.square,
                        this.square);
                System.out.printf("Площадь страны - %s больше площади страны - %s на %d %%\n",
                        secondCountry.name, this.name, percentageOfSquare);
                numberTwo++;
            } else
                System.out.print("Площади стран равны\n");
            // Население
            if (this.population > secondCountry.population) {
                percentageOfPopulation = AuxiliaryClass.calculateByPercentFirstNumberIsGreaterThanSecond(this.population,
                        secondCountry.population);
                System.out.printf("Население страны - %s больше населения страны - %s на %d %%\n",
                        this.name, secondCountry.name, percentageOfPopulation);
                numberOne++;
            } else if (this.population < secondCountry.population) {
                percentageOfPopulation = AuxiliaryClass.calculateByPercentFirstNumberIsGreaterThanSecond(
                        secondCountry.population, this.population);
                System.out.printf("Население страны - %s больше населения страны - %s на %d %%\n",
                        secondCountry.name, this.name, percentageOfPopulation);
                numberTwo++;
            } else
                System.out.printf("Население стран равно");
            //Прибыль компании
            if (this.netProfitFromCompanies > secondCountry.netProfitFromCompanies) {
                percentageOfProfits = AuxiliaryClass.calculateByPercentFirstNumberIsGreaterThanSecond(
                        this.netProfitFromCompanies, secondCountry.netProfitFromCompanies);
                System.out.printf("Прибыль компаний страны - %s больше прибыли компаний страны - %s на %d %%\n",
                        this.name, secondCountry.name, percentageOfProfits);
                numberOne++;
            } else if (this.netProfitFromCompanies < secondCountry.netProfitFromCompanies) {
                percentageOfProfits = AuxiliaryClass.calculateByPercentFirstNumberIsGreaterThanSecond(
                        secondCountry.netProfitFromCompanies, this.netProfitFromCompanies);
                System.out.printf("Прибыль компаний страны - %s больше прибыли компаний страны - %s на %d %%\n",
                        secondCountry.name, this.name, percentageOfProfits);
                numberTwo++;
            } else System.out.print("Прибыли компаний стран равны\n");
            // Доходы
            if (this.income > secondCountry.income) {
                percentageOfIncome = AuxiliaryClass.calculateByPercentFirstNumberIsGreaterThanSecond(this.income,
                        secondCountry.income);
                System.out.printf("Доходы страны - %s больше доходов страны - %s на %d %%\n",
                        this.name, secondCountry.name, percentageOfIncome);
                numberOne++;
            } else if (this.income < secondCountry.income) {
                percentageOfIncome = AuxiliaryClass.calculateByPercentFirstNumberIsGreaterThanSecond(secondCountry.income,
                        this.income);
                System.out.printf("Доходы страны - %s больше доходов страны - %s на %d %%\n",
                        secondCountry.name, this.name, percentageOfIncome);
                numberTwo++;
            } else
                System.out.print("Доходы стран равны\n");
            // Расходы
            if (this.expenses > secondCountry.expenses) {
                percentageOfExpenses = AuxiliaryClass.calculateByPercentFirstNumberIsGreaterThanSecond(
                        this.expenses, secondCountry.expenses);
                System.out.printf("Расходы страны - %s больше расходов страны - %s на %d %%\n",
                        this.name, secondCountry.name, percentageOfExpenses);
            } else if (this.expenses < secondCountry.expenses) {
                percentageOfExpenses = AuxiliaryClass.calculateByPercentFirstNumberIsGreaterThanSecond(
                        secondCountry.expenses, this.expenses);
                System.out.printf("Расходы страны - %s больше расходов страны - %s на %d %%\n",
                        secondCountry.name, this.name, percentageOfExpenses);
            } else
                System.out.print("Расходы стран равны\n");
            if (numberOne > numberTwo)
                value = 1;
            else
                value = 2;
        }
        return value;
    }

    public void changeFields(List<Country> countryList) {
        boolean flag;
        int number;
        System.out.println("\nИЗМЕНЕНИЯ ПОЛЕЙ");
        tableHeader();
        output(0);
        do {
            System.out.println("1.Название страны");
            System.out.println("2.Количество субъектов");
            System.out.println("3.Площадь страны");
            System.out.println("4.Население страны");
            System.out.println("5.Прибыль компаний");
            System.out.println("6.Доходы");
            System.out.println("7.Расходы");
            System.out.println("8.Профицит/дефицит бюджета");
            System.out.println("9.Список субъектов");
            do {
                System.out.print("Введите номер поля, который желаете изменить: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > 9))
                    System.out.println("Поля с данным номером нет");
            } while ((number < 1) || (number > 9));
            Main.scanner.nextLine();
            switch (number) {
                case 1:
                    do {
                        flag = false;
                        name = AuxiliaryClass.inputNameOfSomething("страны");
                        for (Country otherCountry : countryList)
                            if ((this != otherCountry) && (this.equals(otherCountry))) {
                                System.out.println("Данная страна уже есть в списке");
                                flag = true;
                            }
                    } while (flag);
                    setAddressOfCompanies();
                    break;
                case 2:
                    numberOfSubjects = AuxiliaryClass.inputNumberOfSomething("субъектов");
                    break;
                case 3:
                    square = AuxiliaryClass.inputSquareOfSomething("страны");
                    break;
                case 4:
                    population = AuxiliaryClass.inputPopulationOfSomething("страны");
                    break;
                case 5:
                    System.out.println("Данное поле рассчитывается автоматически и его нельзя изменить");
                    System.out.println("Для его изменения вам необходимо совершить корректировку ");
                    System.out.println("в компаниях относящихся к данной стране в поле \"Прибыль\"");
                    break;
                case 6:
                    do {
                        System.out.print("Введите доход страны: ");
                        while (!Main.scanner.hasNextInt()) {
                            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите доход страны: ");
                            Main.scanner.next();
                        }
                        income = Main.scanner.nextInt();
                        if (income < 0)
                            System.out.println("Данное поле не может быть отрицательным!");
                    } while (income < 0);
                    break;
                case 7:
                    do {
                        System.out.print("Введите расходы страны: ");
                        while (!Main.scanner.hasNextInt()) {
                            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите расходы страны: ");
                            Main.scanner.next();
                        }
                        expenses = Main.scanner.nextInt();
                        if (expenses < 0)
                            System.out.println("Данное поле не может быть отрицательным!");
                    } while (expenses < 0);
                    break;
                case 8:
                    System.out.println("Данное поле рассчитывается автоматически и его нельзя изменить");
                    System.out.println("Для его изменения вам необходимо совершить корректировку ");
                    System.out.println("в поле \"Доходы\" или \"Расходы\"");
                    break;
                case 9:
                    System.out.println("Для изменения списка субъектов перейдите по соответствующей команде в меню");
                    break;
                default:
                    break;
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить изменение полей в данной стране?"));
    }

    public void addNewSubject() {
        int number;
        if (listOfSubjects.size() < numberOfSubjects) {
            Subject subject = new Subject();
            subject.input(listOfSubjects);
            listOfSubjects.add(subject);
        } else {
            System.out.println("Достигнуто количество субъектов соответствующее введенному числу - "
                    + numberOfSubjects);
            System.out.println("Для добавления новых субъектов в данный список вам необходимо изменить ");
            System.out.println("число количества субъектов в данной стране");
            if (AuxiliaryClass.answerYesOrNo("Желаете это сделать?")) {
                do {
                    number = AuxiliaryClass.inputNumberOfSomething("субъектов");
                    if (number <= listOfSubjects.size())
                        System.out.println("Данное число меньше или соответствует уже имеющемуся");
                } while (number <= listOfSubjects.size());
                numberOfSubjects = number;
                addNewSubject();
            }
        }
    }

    public void removeSubjectFromList() {
        int number;
        number = chooseSubject();
        Subject.tableHeader();
        listOfSubjects.get(number).output(0);
        if (AuxiliaryClass.answerYesOrNo("Вы действительно желаете удалить данный субъект из списка?"))
            listOfSubjects.remove(number);
    }

    public static void incrementTotalCountries() {
        totalCountries++;
    }

    public static void printTotalCountries() {
        System.out.println("Вы внесли в список " + totalCountries + " из 195 существующих стран");
    }

}
