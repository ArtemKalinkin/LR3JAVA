import java.util.ArrayList;
import java.util.List;

public class Continent {
    private String name;
    private int numberOfCountries;
    private int square;
    private List<Country> listOfCountries = new ArrayList<>();

    private static int totalContinents = 0;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    public Continent(String name, int numberOfCountries, int square, List<Country> listOfCountries) {
        this.name = name;
        this.numberOfCountries = numberOfCountries;
        this.square = square;
        this.listOfCountries = listOfCountries;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumberOfCountries(int numberOfCountries) {
        this.numberOfCountries = numberOfCountries;
    }

    public int getNumberOfCountries() {
        return numberOfCountries;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getSquare() {
        return square;
    }

    public void setListOfCountries(List<Country> listOfCountries) {
        this.listOfCountries = listOfCountries;
    }

    public List<Country> getListOfCountries() {
        return listOfCountries;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Continent))
            return false;
        Continent continent = (Continent) object;
        return this.name.equals(continent.getName());
    }


    public void input(List<Continent> continentList) {
        boolean flag;
        System.out.println("\nВВОД КОНТИНЕНТА");
        do {
            flag = false;
            name = AuxiliaryClass.inputNameOfSomething("континента");
            for (Continent otherContinent : continentList)
                if ((this != otherContinent) && (this.equals(otherContinent))) {
                    System.out.println("Данный континент уже есть в списке");
                    flag = true;
                }
        } while (flag);
        square = AuxiliaryClass.inputSquareOfSomething("континента");
        numberOfCountries = AuxiliaryClass.inputNumberOfSomething("стран");
        do {
            Country country = new Country();
            country.input(listOfCountries);
            listOfCountries.add(country);
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить ввод стран (y/n):"));

    }

    public static void tableHeader() {
        System.out.print("******************************************************" +
                "*************************************************\n");
        System.out.print("* Номер *     Континент      * Количество стран *" +
                " Площадь континента *          Список стран          *\n");
        System.out.print("******************************************************" +
                "*************************************************\n");
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
        System.out.print("****************************************" +
                "***************************************************************\n");
    }

    public int chooseCountry() {
        int number = 0;
        int i;
        int size = listOfCountries.size();
        if (!listOfCountries.isEmpty()) {
            Country.tableHeader();
            i = 0;
            for (Country country : listOfCountries) {
                country.output(i);
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
        output(0);
        do {
            System.out.println("1.Название континента");
            System.out.println("2.Количество стран");
            System.out.println("3.Площадь континента");
            System.out.println("4.Список стран");
            do {
                System.out.print("Введите номер поля, который желаете изменить: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > 4))
                    System.out.println("Поля с данным номером нет");
            } while ((number < 1) || (number > 4));
            Main.scanner.nextLine();
            switch (number) {
                case 1:
                    do {
                        flag = false;
                        name = AuxiliaryClass.inputNameOfSomething("страны");
                        for (Continent otherContinent : continentList)
                            if ((this != otherContinent) && (this.equals(otherContinent))) {
                                System.out.println("Данная страна уже есть в списке");
                                flag = true;
                            }
                    } while (flag);
                    break;
                case 2:
                    numberOfCountries = AuxiliaryClass.inputNumberOfSomething("стран");
                    break;
                case 3:
                    square = AuxiliaryClass.inputSquareOfSomething("континента");
                    break;
                case 4:
                    System.out.println("Для изменения списка стран перейдите по соответствующей команде в меню");
                    break;
                default:
                    break;
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
                    number = AuxiliaryClass.inputNumberOfSomething("стран");
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
        listOfCountries.get(number).output(0);
        if (AuxiliaryClass.answerYesOrNo("Вы действительно желаете удалить данную страну из списка?"))
            listOfCountries.remove(number);
    }

    public static void incrementTotalContinents() {
        totalContinents++;
    }

    public static void printTotalContinents() {
        System.out.println("Вы внесли в список " + totalContinents + " из 6 существующих континентов");
    }
}
