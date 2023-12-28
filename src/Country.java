import java.util.ArrayList;
import java.util.List;

public class Country extends AbstractElement implements Modifiable<Country>, Cloneable, Printable {
    private int numberOfSubjects;
    private long netProfitFromCompanies;
    private long income;
    private long expenses;
    private long budgetDeficitOrSurplus;
    private List<Subject> listOfSubjects = new ArrayList<>();

    private static int totalCountries = 0;

    public enum CountryField {
        NAME,
        POPULATION,
        SQUARE,
        NUMBER_OF_SUBJECTS,
        NET_PROFIT_FROM_COMPANIES,
        INCOME,
        EXPENSES,
        BUDGET_DEFICIT_OR_SURPLUS,
    }

    public Country() {
    }


    public Country(String name, int numberOfSubjects, long netProfitFromCompanies, int population,
                   int square, long income, long expenses, List<Subject> listOfSubjects) {
        super(name, population, square);
        this.numberOfSubjects = numberOfSubjects;
        this.netProfitFromCompanies = netProfitFromCompanies;
        this.income = income;
        this.expenses = expenses;
        this.budgetDeficitOrSurplus = income - expenses;
        this.listOfSubjects = listOfSubjects;
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
        if (!(object instanceof Country country))
            return false;
        return this.name.equals(country.getName());
    }


    public long inputIncome() {
        long income;
        System.out.print("Введите доход страны: ");
        while (!Main.scanner.hasNextLong()) {
            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите доход страны: ");
            Main.scanner.next();
        }
        income = Main.scanner.nextLong();
        if (income < 0)
            throw new IllegalArgumentException("Ошибка! Значение не может быть отрицательным!");

        Main.scanner.nextLine();
        return income;
    }

    public long inputExpenses() {
        long expenses;
        System.out.print("Введите расходы страны: ");
        while (!Main.scanner.hasNextLong()) {
            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите расходы страны: ");
            Main.scanner.next();
        }
        expenses = Main.scanner.nextLong();
        if (expenses < 0)
            throw new IllegalArgumentException("Ошибка! Значение не может быть отрицательным!");

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
            try {
                name = inputName("страны");
            } catch (StringWithSmallLetterException e) {
                System.out.println("Название страны необходимо писать с заглавной буквы!");
            }
            for (Country otherCountry : countryList)
                if ((this != otherCountry) && (this.equals(otherCountry))) {
                    System.out.println("Данная страна уже есть в списке");
                    flag = true;
                }
        } while (flag);
        do {
            try {
                numberOfSubjects = inputNumber("субъектов");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Количество субъектов не может быть отрицательным!");
            }
        } while (true);
        do {
            try {
                square = inputSquare("страны");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Площадь страны не может быть отрицательной!");
            }
        } while (true);
        do {
            try {
                population = inputPopulation("страны");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Население страны не может быть отрицательным!");
            }
        } while (true);
        do {
            try {
                income = inputIncome();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Доходы страны не могут быть отрицательными!");
            }
        } while (true);
        do {
            try {
                expenses = inputExpenses();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Расходы страны не могут быть отрицательными!");
            }
        } while (true);
        budgetDeficitOrSurplus = income - expenses;
        do {
            Subject subject = new Subject();
            subject.input(listOfSubjects);
            listOfSubjects.add(subject);
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить ввод субъектов (y/n):"));
        netProfitFromCompanies = calculateProfitFromCompanies().getValue();
        setAddressOfCompanies();
        incrementTotalCountries();
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

    @Override
    public String toString() {
        StringBuilder string;
        string = new StringBuilder(String.format(" * %-18s * %-20d * %-14d * ", name, numberOfSubjects, square));
        string.append(String.format("%-9d * %-19d * %-14d * ", population, netProfitFromCompanies, income));
        string.append(String.format("%-15d * %-24d ", expenses, budgetDeficitOrSurplus));
        if (listOfSubjects.isEmpty())
            string.append(String.format("* %-25s *\n", AuxiliaryClass.listIsEmpty));
        else
            string.append(String.format("* %-25s *\n", listOfSubjects.get(0).getName()));
        for (int i = 1; i < listOfSubjects.size(); i++) {
            string.append(String.format("*       *                    *                      *                *           *" +
                            "                     *                *                 *                          * %-25s *\n",
                    listOfSubjects.get(i).getName()));
        }
        string.append("****************************************************************************************" +
                "**********************************************************************************************************\n");
        return string.toString();
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
            i = 1;
            for (Subject subject : listOfSubjects) {
                System.out.printf("* %-5d", i);
                System.out.print(subject);
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
            case 1 -> System.out.println("Площадь одной из стран записана некорректно и равна 0");
            case 2 -> System.out.println("Население одной из стран записано некорректно и равно 0");
            case 3 -> System.out.println("Прибыль от компаний одной из стран записана некорректно и равна 0");
            case 4 -> System.out.println("Доход одной из стран записан некорректно и равен 0");
            case 5 -> System.out.println("Расходы одной из стран записаны некорректно и равны 0");
            default -> {
            }
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
                System.out.print("Население стран равно");
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
        System.out.printf("* %-5d", 1);
        System.out.print(this);
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
                case 1 -> {
                    do {
                        flag = false;
                        do {
                            try {
                                name = inputName("страны");
                                break;
                            } catch (StringWithSmallLetterException e) {
                                System.out.println("Название страны необходимо писать с заглавной буквы!");
                            }
                        } while (true);
                        for (Country otherCountry : countryList)
                            if ((this != otherCountry) && (this.equals(otherCountry))) {
                                System.out.println("Данная страна уже есть в списке");
                                flag = true;
                            }
                    } while (flag);
                    setAddressOfCompanies();
                }
                case 2 -> {
                    do {
                        try {
                            numberOfSubjects = inputNumber("субъектов");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Количество субъектов не может быть отрицательным!");
                        }
                    } while (true);
                }
                case 3 -> {
                    do {
                        try {
                            square = inputSquare("страны");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Площадь страны не может быть отрицательной!");
                        }
                    } while (true);
                }
                case 4 -> {
                    do {
                        try {
                            population = inputPopulation("страны");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Население страны не может быть отрицательным!");
                        }
                    } while (true);
                }
                case 5 -> {
                    System.out.println("Данное поле рассчитывается автоматически и его нельзя изменить");
                    System.out.println("Для его изменения вам необходимо совершить корректировку ");
                    System.out.println("в компаниях относящихся к данной стране в поле \"Прибыль\"");
                }
                case 6 -> {
                    do {
                        try {
                            income = inputIncome();
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Доходы страны не могут быть отрицательными!");
                        }
                    } while (true);
                }
                case 7 -> {
                    do {
                        try {
                            expenses = inputExpenses();
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Расходы страны не могут быть отрицательными!");
                        }
                    } while (true);
                }
                case 8 -> {
                    System.out.println("Данное поле рассчитывается автоматически и его нельзя изменить");
                    System.out.println("Для его изменения вам необходимо совершить корректировку ");
                    System.out.println("в поле \"Доходы\" или \"Расходы\"");
                }
                case 9 ->
                        System.out.println("Для изменения списка субъектов перейдите по соответствующей команде в меню");
                default -> {
                }
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
                    number = inputNumber("субъектов");
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
        System.out.printf("* %-5d", 1);
        System.out.print(listOfSubjects.get(number));
        if (AuxiliaryClass.answerYesOrNo("Вы действительно желаете удалить данный субъект из списка?")) {
            listOfSubjects.remove(number);
            Subject.decrementTotalSubjects();
        }
    }

    public static void incrementTotalCountries() {
        totalCountries++;
    }

    public static void decrementTotalCountries() {
        totalCountries--;
    }

    public static void printTotalCountries() {
        System.out.println("Вы внесли в список " + totalCountries + " из 195 существующих стран");
    }

    @Override
    public Country clone() {
        try {
            Country clone = (Country) super.clone();
            List<Subject> clonedSubjects = new ArrayList<>();
            for (Subject subject : listOfSubjects)
                clonedSubjects.add(subject.clone());
            clone.listOfSubjects = clonedSubjects;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String getFormattedInfo() {
        return "Страна: " + name + "; " + numberOfSubjects + "; " + square + "; " + population + "; "
                + netProfitFromCompanies + "; " + income + "; " + expenses + "; " + budgetDeficitOrSurplus + ".";
    }

    public static int chooseField(String s) {
        int number;
        System.out.println("\n\nВыбор поля для " + s + " стран: ");
        System.out.println("1.Название");
        System.out.println("2.Население");
        System.out.println("3.Площадь");
        System.out.println("4.Количество субъектов");
        System.out.println("5.Прибыль от компаний");
        System.out.println("6.Доход");
        System.out.println("7.Расходы");
        System.out.println("8.Профицит\\дефицит бюджета");
        do {
            System.out.print("Введите номер поля: ");
            while (!Main.scanner.hasNextInt()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите номер поля: ");
                Main.scanner.next();
            }
            number = Main.scanner.nextInt();
            if ((number < 1) || (number > 8))
                System.out.println("Поля под данным номером нет!");
        } while ((number < 1) || (number > 8));
        return number;
    }

    public static CountryField getSortField(int number) {
        switch (number) {
            case 1 -> {
                return CountryField.NAME;
            }
            case 2 -> {
                return CountryField.POPULATION;
            }
            case 3 -> {
                return CountryField.SQUARE;
            }
            case 4 -> {
                return CountryField.NUMBER_OF_SUBJECTS;
            }
            case 5 -> {
                return CountryField.NET_PROFIT_FROM_COMPANIES;
            }
            case 6 -> {
                return CountryField.INCOME;
            }
            case 7 -> {
                return CountryField.EXPENSES;
            }
            case 8 -> {
                return CountryField.BUDGET_DEFICIT_OR_SURPLUS;
            }
        }
        return null;
    }

    public void sortSubjects() {
        System.out.println("\n\nСписок до сортировки\n\n");
        Subject.tableHeader();
        int i = 1;
        for (Subject subject : listOfSubjects) {
            System.out.printf("* %-5d", i);
            System.out.print(subject);
            i++;
        }
        int number, modeNumber;
        number = Country.chooseField("сортировки списка");
        if (number == 1) {
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
        SubjectComparator comparator = new SubjectComparator(Subject.getSortField(number));
        listOfSubjects.sort(comparator);
        if (modeNumber == 2)
            listOfSubjects.reversed();
        System.out.println("\n\nСписок после сортировки\n\n");
        Subject.tableHeader();
        i = 1;
        for (Subject subject : listOfSubjects) {
            System.out.printf("* %-5d", i);
            System.out.print(subject);
            i++;
        }
    }
}
