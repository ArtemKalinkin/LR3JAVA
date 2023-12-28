import java.util.ArrayList;
import java.util.List;

public class Continent extends AbstractElement implements Modifiable<Continent>, Cloneable, Printable {
    private int numberOfCountries;

    public enum ContinentField {
        NAME,
        POPULATION,
        SQUARE,
        NUMBER_OF_COUNTRIES
    }

    private List<Country> listOfCountries = new ArrayList<>();

    private static int totalContinents = 0;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    public Continent(String name, int numberOfCountries, int square, List<Country> listOfCountries, int population) {
        super(name, population, square);
        this.numberOfCountries = numberOfCountries;
    }


    public void setNumberOfCountries(int numberOfCountries) {
        this.numberOfCountries = numberOfCountries;
    }

    public int getNumberOfCountries() {
        return numberOfCountries;
    }


    public void setListOfCountries(List<Country> listOfCountries) {
        this.listOfCountries = listOfCountries;
    }

    public List<Country> getListOfCountries() {
        return listOfCountries;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Continent continent))
            return false;
        return this.name.equals(continent.getName());
    }


    public void input(List<Continent> continentList) {
        boolean flag;
        System.out.println("\nВВОД КОНТИНЕНТА");
        do {
            flag = false;
            do {
                try {
                    name = inputName("континента");
                    break;
                } catch (StringWithSmallLetterException e) {
                    System.out.println("Название континента необходимо писать с заглавной буквы!");
                }
            } while (true);
            for (Continent otherContinent : continentList)
                if ((this != otherContinent) && (this.equals(otherContinent))) {
                    System.out.println("Данный континент уже есть в списке");
                    flag = true;
                }
        } while (flag);
        do {
            try {
                population = inputPopulation("континента");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Население страны не может быть отрицательным!");
            }
        } while (true);
        do {
            try {
                square = inputSquare("континента");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Площадь континента не может быть отрицательной!");
            }
        } while (true);
        do {
            try {
                numberOfCountries = inputNumber("стран");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Количество стран не может быть отрицательным!");
            }
        } while (true);
        do {
            Country country = new Country();
            country.input(listOfCountries);
            listOfCountries.add(country);
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить ввод стран (y/n):"));
        incrementTotalContinents();
    }

    public static void tableHeader() {
        System.out.print("******************************************************" +
                "**************************************************************\n");
        System.out.print("* Номер *     Континент      * Количество стран *" +
                " Площадь континента *  Население *          Список стран          *\n");
        System.out.print("*******************************************************" +
                "*************************************************************\n");
    }

    @Override
    public String toString() {
        StringBuilder string;
        string = new StringBuilder(String.format(" * %-18s * %-16d * %-18d * %-10d * ", name, numberOfCountries,
                square, population));
        if (listOfCountries.isEmpty())
            string.append(String.format("%-30s *\n", AuxiliaryClass.listIsEmpty));
        else
            string.append(String.format("%-30s *\n", listOfCountries.get(0).getName()));
        for (int i = 1; i < listOfCountries.size(); i++) {
            string.append(String.format("*       *                    *                  *                     * %-30s *\n",
                    listOfCountries.get(i).getName()));
        }
        string.append("************************************************************" +
                "********************************************************\n");
        return string.toString();
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-18s * %-16d * %-18d * ", number + 1, name, numberOfCountries, square);
        if (listOfCountries.isEmpty())
            System.out.printf("%-30s *\n", AuxiliaryClass.listIsEmpty);
        else
            System.out.printf("%-30s *\n", listOfCountries.get(0).getName());
        for (int i = 1; i < listOfCountries.size(); i++) {
            System.out.printf("*       *                    *                  *                    * %-30s *\n",
                    listOfCountries.get(i).getName());
        }
        System.out.print("*********************************************************" +
                "**********************************************************\n");
    }

    public int chooseCountry() {
        int number = 0;
        int i;
        int size = listOfCountries.size();
        if (!listOfCountries.isEmpty()) {
            Country.tableHeader();
            i = 1;
            for (Country country : listOfCountries) {
                System.out.printf("* %-5d", i);
                System.out.print(country);
                i++;
            }
            do {
                System.out.print("Введите номер страны: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > size))
                    System.out.println("Страны под данным номером нет в списке");
            } while ((number < 1) || (number > size));
            Main.scanner.nextLine();
        }
        return number - 1;
    }

    public void changeFields(List<Continent> continentList) {
        boolean flag;
        int number;
        System.out.println("\nИЗМЕНЕНИЯ ПОЛЕЙ");
        tableHeader();
        System.out.printf("* %-5d", 1);
        System.out.print(this);
        do {
            System.out.println("1.Название континента");
            System.out.println("2.Количество стран");
            System.out.println("3.Площадь континента");
            System.out.println("4.Население континента");
            System.out.println("5.Список стран");
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
                                name = inputName("страны");
                                break;
                            } catch (StringWithSmallLetterException e) {
                                System.out.println("Название континента необходимо писать с заглавной буквы!");
                            }
                        } while (true);
                        for (Continent otherContinent : continentList)
                            if ((this != otherContinent) && (this.equals(otherContinent))) {
                                System.out.println("Данная страна уже есть в списке");
                                flag = true;
                            }
                    } while (flag);
                }
                case 2 -> {
                    do {
                        try {
                            numberOfCountries = inputNumber("стран");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Количество стран не может быть отрицательным!");
                        }
                    } while (true);
                }
                case 3 -> {
                    do {
                        try {
                            square = inputSquare("континента");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Площадь континента не может быть отрицательной!");
                        }
                    } while (true);
                }
                case 4 -> {
                    do {
                        try {
                            population = inputPopulation("континента");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Население континента не может быть отрицательным!");
                        }
                    } while (true);
                }
                case 5 -> System.out.println("Для изменения списка стран перейдите по соответствующей команде в меню");
                default -> {
                }
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить изменение полей в данном континенте?"));
    }

    public void addNewCountry() {
        int number;
        if (listOfCountries.size() < numberOfCountries) {
            Country country = new Country();
            country.input(listOfCountries);
            listOfCountries.add(country);
            country.setAddressOfCompanies();
        } else {
            System.out.println("Достигнуто количество стран соответствующее введенному числу - "
                    + numberOfCountries);
            System.out.println("Для добавления новых стран в данный список вам необходимо изменить ");
            System.out.println("число количества стран на данном континенте");
            if (AuxiliaryClass.answerYesOrNo("Желаете это сделать?")) {
                do {
                    number = inputNumber("стран");
                    if (number <= listOfCountries.size())
                        System.out.println("Данное число меньше или соответствует уже имеющемуся");
                } while (number <= listOfCountries.size());
                numberOfCountries = number;
                addNewCountry();
            }
        }
    }

    public void removeCountryFromList() {
        int number;
        number = chooseCountry();
        Country.tableHeader();
        System.out.printf("* %-5d", 1);
        System.out.print(listOfCountries.get(number));
        if (AuxiliaryClass.answerYesOrNo("Вы действительно желаете удалить данную страну из списка?")) {
            listOfCountries.remove(number);
            Country.decrementTotalCountries();
        }
    }

    public static void incrementTotalContinents() {
        totalContinents++;
    }

    public static void decrementTotalContinents() {
        totalContinents--;
    }

    public static void printTotalContinents() {
        System.out.println("Вы внесли в список " + totalContinents + " из 6 существующих континентов");
    }

    @Override
    public Continent clone() {
        try {
            Continent clone = (Continent) super.clone();
            List<Country> clonedCountries = new ArrayList<>();
            for (Country country : listOfCountries)
                clonedCountries.add(country.clone());
            clone.listOfCountries = clonedCountries;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String getFormattedInfo() {
        return "Континент: " + name + "; " + numberOfCountries + "; " + square + ".";
    }

    public static int chooseField(String s) {
        int number;
        System.out.println("\n\nВыбор поля для " + s + " континентов: ");
        System.out.println("1.Название");
        System.out.println("2.Население");
        System.out.println("3.Площадь");
        System.out.println("4.Количество стран");
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

    public static ContinentField getSortField(int number) {
        switch (number) {
            case 1 -> {
                return ContinentField.NAME;
            }
            case 2 -> {
                return ContinentField.POPULATION;
            }
            case 3 -> {
                return ContinentField.SQUARE;
            }
            case 4 -> {
                return ContinentField.NUMBER_OF_COUNTRIES;
            }
        }
        return null;
    }

    public void sortCountries() {
        System.out.println("\n\nСписок до сортировки\n\n");
        Country.tableHeader();
        int i = 1;
        for (Country country : listOfCountries) {
            System.out.printf("* %-5d", i);
            System.out.print(country);
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
        CountryComparator comparator = new CountryComparator(Country.getSortField(number));
        listOfCountries.sort(comparator);
        if (modeNumber == 2)
            listOfCountries.reversed();
        System.out.println("\n\nСписок после сортировки\n\n");
        i = 1;
        for (Country country : listOfCountries) {
            System.out.printf("* %-5d", i);
            System.out.print(country);
            i++;
        }
    }


}
