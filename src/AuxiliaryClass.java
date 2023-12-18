import java.util.ArrayList;
import java.util.List;

public class AuxiliaryClass {

    private long value;

    public void setValue(int value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public AuxiliaryClass(long value) {
        this.value = value;
    }

    public static String listIsEmpty = "Список пуст";

    public static boolean answerYesOrNo(String s) {
        String answer;
        do {
            System.out.println(s);
            answer = Main.scanner.nextLine();
            switch (answer) {
                case "No", "n", "N", "no", "Нет", "нет" -> {
                    return false;
                }
                case "yes", "y", "Y", "Yes", "Да", "да" -> {
                    return true;
                }
                default -> {
                    System.out.println("Некорректный ответ!");
                    System.out.println("Варианты положительных ответов: Да, да, Yes, yes, Y, y");
                    System.out.println("Варианты отрицательных ответов: Нет, нет, No, no, N, n");
                }
            }
        } while (true);
    }

    public static int chooseContinent(List<Continent> listOfContinents) {
        int number = 0;
        int i;
        int size = listOfContinents.size();
        if (!listOfContinents.isEmpty()) {
            Continent.tableHeader();
            i = 1;
            for (Continent continent : listOfContinents) {
                System.out.printf("* %-5d", i);
                System.out.print(continent);
                i++;
            }
            do {
                System.out.print("Введите номер континента: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > size))
                    System.out.println("Континента под данным номером нет в списке");
            } while ((number < 1) || (number > size));
            Main.scanner.nextLine();
        }
        return number - 1;

    }

    public static void addNewContinent(List<Continent> continentList) {
        if (continentList.size() < 6) {
            Continent continent = new Continent();
            continent.input(continentList);
            continentList.add(continent);
        }
    }

    public static void removeContinentFromList(List<Continent> listOfContinents) {
        int number;
        if (!listOfContinents.isEmpty()) {
            number = chooseContinent(listOfContinents);
            Continent.tableHeader();
            System.out.printf("* %-5d", 1);
            System.out.print(listOfContinents.get(number));
            if (AuxiliaryClass.answerYesOrNo("Вы действительно желаете удалить данный континент из списка?")) {
                listOfContinents.remove(number);
                Continent.decrementTotalContinents();
            }
        } else
            System.out.println(AuxiliaryClass.listIsEmpty);
    }


    public static int menuOutput() {
        int number;
        System.out.println("\n\nВывести на экран:");
        System.out.println("1.Все континенты");
        System.out.println("2.Все страны определенного континента");
        System.out.println("3.Все субъекты определенной страны");
        System.out.println("4.Все города определенного субъекта");
        System.out.println("5.Все компании определенного города");
        do {
            System.out.print("\nВведите номер действия: ");
            while (!Main.scanner.hasNextInt()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите номер действия: ");
                Main.scanner.next();
            }
            number = Main.scanner.nextInt();
        } while ((number > 5) || (number < 1));
        Main.scanner.nextLine();
        return number;
    }

    public static void outputAll(List<Continent> continentList, int number) {
        int i = 1;
        int numberOfContinent;
        int numberOfCountry;
        int numberOfSubject;
        int numberOfCity;
        List<Country> countries;
        List<Subject> subjects;
        List<City> cities;
        List<Company> companies;
        switch (number) {
            case 1 -> {
                if (continentList.isEmpty())
                    System.out.println("\n\n" + listIsEmpty);
                Continent.tableHeader();
                for (Continent continent : continentList) {
                    System.out.printf("* %-5d", i);
                    System.out.print(continent);
                    i++;
                }
            }
            case 2 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                if (numberOfContinent == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                countries = continentList.get(numberOfContinent).getListOfCountries();
                Country.tableHeader();
                for (Country country : countries) {
                    System.out.printf("* %-5d", i);
                    System.out.print(country);
                    i++;
                }
            }
            case 3 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                if (numberOfContinent == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (numberOfCountry == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                subjects = countries.get(numberOfCountry).getListOfSubjects();
                Subject.tableHeader();
                for (Subject subject : subjects) {
                    System.out.printf("* %-5d", i);
                    System.out.print(subject);
                    i++;
                }
            }
            case 4 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                if (numberOfContinent == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (numberOfCountry == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                subjects = countries.get(numberOfCountry).getListOfSubjects();
                numberOfSubject = countries.get(numberOfCountry).chooseSubject();
                if (numberOfSubject == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                cities = subjects.get(numberOfSubject).getListOfCities();
                City.tableHeader();
                for (City city : cities) {
                    System.out.printf("* %-5d", i);
                    System.out.print(city);
                    i++;
                }
            }
            case 5 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                if (numberOfContinent == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (numberOfCountry == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                subjects = countries.get(numberOfCountry).getListOfSubjects();
                numberOfSubject = countries.get(numberOfCountry).chooseSubject();
                if (numberOfSubject == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                cities = subjects.get(numberOfSubject).getListOfCities();
                numberOfCity = subjects.get(numberOfSubject).chooseCity();
                if (numberOfCity == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                companies = cities.get(numberOfCity).getListOfCompany();
                Company.tableHeader();
                for (Company company : companies) {
                    System.out.printf("* %-5d", i);
                    if (company instanceof Branch branch) {
                        System.out.print(branch);
                    } else
                        System.out.print(company);
                    i++;
                }
            }
            default -> {
            }
        }
    }

    public static int calculateByPercentFirstNumberIsGreaterThanSecond(long numberOne, long numberTwo) {
        int result;
        result = (int) ((numberOne - numberTwo) * 100 / numberTwo);
        return result;
    }

    public static void compareTwoCountries(List<Continent> continentList) {
        int numberOfContinentOne;
        int numberOfContinentTwo;
        int numberOne;
        int numberTwo;
        System.out.println("\n\n\n\nСРАВНЕНИЕ ДВУХ СТРАН\n");
        System.out.println("\nВыберите континент первой страны");
        numberOfContinentOne = AuxiliaryClass.chooseContinent(continentList);
        List<Country> countryListOne = continentList.get(numberOfContinentOne).getListOfCountries();
        System.out.println("\nВыберите континент второй страны");
        numberOfContinentTwo = AuxiliaryClass.chooseContinent(continentList);
        List<Country> countryListTwo = continentList.get(numberOfContinentTwo).getListOfCountries();
        if ((countryListOne.isEmpty()) || (countryListTwo.isEmpty())) {
            System.out.println("На данный момент список стран пуст");
        } else {
            Country.tableHeader();
            int i = 0;
            for (Country country : countryListOne) {
                System.out.printf("* %-5d", i);
                System.out.print(country);
                i++;
            }
            int size = countryListOne.size();
            do {
                System.out.print("Введите номер первой страны: ");
                numberOne = Main.scanner.nextInt();
                if ((numberOne < 1) || (numberOne > size))
                    System.out.println("Страны под данным номером нет в списке");
            } while ((numberOne < 1) || (numberOne > size));
            Main.scanner.nextLine();

            Country.tableHeader();
            i = 0;
            for (Country country : countryListTwo) {
                System.out.printf("* %-5d", i);
                System.out.print(country);
                i++;
            }
            size = countryListTwo.size();
            do {
                System.out.print("Введите номер второй страны: ");
                numberTwo = Main.scanner.nextInt();
                if ((numberTwo < 1) || (numberTwo > size))
                    System.out.println("Страны под данным номером нет в списке");
                if ((numberOne == numberTwo) & (numberOfContinentOne == numberOfContinentTwo))
                    System.out.println("Вы уже выбрали данную страну для сравнения");
            } while ((numberTwo < 1) || (numberTwo > size) || ((numberTwo == numberOne) &
                    (numberOfContinentOne == numberOfContinentTwo)));
            Main.scanner.nextLine();

            numberOne--;
            numberTwo--;

            if (countryListOne.get(numberOne).compare(countryListTwo.get(numberTwo)) == 1)
                System.out.printf("\nПо общим показателям страна - %s лучше страны - %s\n",
                        countryListOne.get(numberOne).getName(), countryListTwo.get(numberTwo).getName());
            else
                System.out.printf("\nПо общим показателям страна - %s лучше страны - %s\n",
                        countryListTwo.get(numberTwo).getName(), countryListOne.get(numberOne).getName());
        }
    }

    public static void changeFieldsOfSomething(List<Continent> continentList) {
        int number;
        int numberOfContinent;
        int numberOfCountry;
        int numberOfSubject;
        int numberOfCity;
        int numberOfCompany;
        List<Country> countries;
        List<Subject> subjects;
        List<City> cities;
        List<Company> companies;
        System.out.println("\n\nИзменить:");
        System.out.println("1.Поля континента");
        System.out.println("2.Поля страны определенного континента");
        System.out.println("3.Поля субъекты определенной страны");
        System.out.println("4.Поля города определенного субъекта");
        System.out.println("5.Поля компании определенного города");
        do {
            System.out.print("\nВведите номер действия: ");
            while (!Main.scanner.hasNextInt()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите номер действия: ");
                Main.scanner.next();
            }
            number = Main.scanner.nextInt();
        } while ((number > 5) || (number < 1));
        Main.scanner.nextLine();
        switch (number) {
            case 1 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                continentList.get(numberOfContinent).changeFields(continentList);
            }
            case 2 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (numberOfCountry == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                countries.get(numberOfCountry).changeFields(countries);
                countries.get(numberOfCountry).setAddressOfCompanies();
            }
            case 3 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (numberOfCountry == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                subjects = countries.get(numberOfCountry).getListOfSubjects();
                numberOfSubject = countries.get(numberOfCountry).chooseSubject();
                if (numberOfSubject == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                subjects.get(numberOfSubject).changeFields(subjects);
                countries.get(numberOfCountry).setAddressOfCompanies();
            }
            case 4 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (numberOfCountry == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                subjects = countries.get(numberOfCountry).getListOfSubjects();
                numberOfSubject = countries.get(numberOfCountry).chooseSubject();
                if (numberOfSubject == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                cities = subjects.get(numberOfSubject).getListOfCities();
                numberOfCity = subjects.get(numberOfSubject).chooseCity();
                if (numberOfCity == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                cities.get(numberOfCity).changeFields(cities);
                countries.get(numberOfCountry).setAddressOfCompanies();
            }
            case 5 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (numberOfCountry == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                subjects = countries.get(numberOfCountry).getListOfSubjects();
                numberOfSubject = countries.get(numberOfCountry).chooseSubject();
                if (numberOfSubject == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                cities = subjects.get(numberOfSubject).getListOfCities();
                numberOfCity = subjects.get(numberOfSubject).chooseCity();
                if (numberOfCity == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                companies = cities.get(numberOfCity).getListOfCompany();
                numberOfCompany = cities.get(numberOfCity).chooseCompany();
                if (numberOfCompany == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                companies.get(numberOfCompany).changeFields(companies);
                countries.get(numberOfCountry).setNetProfitFromCompanies(
                        countries.get(numberOfCountry).calculateProfitFromCompanies().getValue());
            }
            default -> {
            }
        }
    }

    public static void addOrRemoveSomething(List<Continent> continentList, boolean mode) {
        int number;
        int numberOfContinent;
        int numberOfCountry;
        int numberOfSubject;
        int numberOfCity;
        List<Country> countries;
        List<Subject> subjects;
        List<City> cities;
        if (mode)
            System.out.println("\n\nДобавить:");
        else
            System.out.println("\n\nУдалить:");
        System.out.println("1.Континент");
        System.out.println("2.Страну");
        System.out.println("3.Субъект");
        System.out.println("4.Город");
        System.out.println("5.Компания");
        do {
            System.out.print("\nВведите номер действия: ");
            while (!Main.scanner.hasNextInt()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите номер действия: ");
                Main.scanner.next();
            }
            number = Main.scanner.nextInt();
        } while ((number > 5) || (number < 1));
        Main.scanner.nextLine();
        switch (number) {
            case 1 -> {
                if (mode)
                    do {
                        addNewContinent(continentList);
                        if (continentList.size() == 6)
                            System.out.println("Вы ввели все континенты");
                    } while ((continentList.size() < 6) & (answerYesOrNo("Желаете добавить еще один континент?")));
                else
                    do {
                        removeContinentFromList(continentList);
                        if (continentList.isEmpty())
                            System.out.println("Вы удалили все континенты");
                    } while ((!continentList.isEmpty()) & (answerYesOrNo("Желаете удалить еще один континент?")));
            }
            case 2 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                if (mode)
                    do {
                        continentList.get(numberOfContinent).addNewCountry();
                    } while (answerYesOrNo("Желаете добавить еще одну страну?"));
                else
                    do {
                        continentList.get(numberOfContinent).removeCountryFromList();
                    } while ((!continentList.get(numberOfContinent).getListOfCountries().isEmpty())
                            & (answerYesOrNo("Желаете удалить еще одну страну?")));
            }
            case 3 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (mode) {
                    do {
                        countries.get(numberOfCountry).addNewSubject();
                    } while (answerYesOrNo("Желаете добавить еще один субъект?"));
                    countries.get(numberOfCountry).setAddressOfCompanies();
                    countries.get(numberOfCountry).setNetProfitFromCompanies(
                            countries.get(numberOfCountry).calculateProfitFromCompanies().getValue());
                } else {
                    if (numberOfCountry == -1) {
                        System.out.println(AuxiliaryClass.listIsEmpty);
                        break;
                    }
                    do {
                        countries.get(numberOfCountry).removeSubjectFromList();
                    } while ((!countries.get(numberOfCountry).getListOfSubjects().isEmpty())
                            & (answerYesOrNo("Желаете удалить еще один субъект?")));
                }
            }
            case 4 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (numberOfCountry == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                subjects = countries.get(numberOfCountry).getListOfSubjects();
                numberOfSubject = countries.get(numberOfCountry).chooseSubject();
                if (mode) {
                    do {
                        subjects.get(numberOfSubject).addNewCity();
                    } while (answerYesOrNo("Желаете добавить еще один город?"));
                    countries.get(numberOfCountry).setAddressOfCompanies();
                    countries.get(numberOfCountry).setNetProfitFromCompanies(
                            countries.get(numberOfCountry).calculateProfitFromCompanies().getValue());
                } else {
                    if (numberOfSubject == -1) {
                        System.out.println(AuxiliaryClass.listIsEmpty);
                        break;
                    }
                    do {
                        subjects.get(numberOfSubject).removeCityFromList();
                    } while ((!subjects.get(numberOfSubject).getListOfCities().isEmpty())
                            & (answerYesOrNo("Желаете удалить еще один город?")));
                }
            }
            case 5 -> {
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (numberOfCountry == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                subjects = countries.get(numberOfCountry).getListOfSubjects();
                numberOfSubject = countries.get(numberOfCountry).chooseSubject();
                if (numberOfSubject == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                cities = subjects.get(numberOfSubject).getListOfCities();
                numberOfCity = subjects.get(numberOfSubject).chooseCity();
                if (mode) {
                    do {
                        cities.get(numberOfCity).addNewCompany();
                    } while (answerYesOrNo("Желаете добавить еще одну компанию?"));
                    countries.get(numberOfCountry).setAddressOfCompanies();
                    countries.get(numberOfCountry).setNetProfitFromCompanies(
                            countries.get(numberOfCountry).calculateProfitFromCompanies().getValue());
                } else {
                    if (numberOfCity == -1) {
                        System.out.println(AuxiliaryClass.listIsEmpty);
                        break;
                    }
                    do {
                        cities.get(numberOfCity).removeCompanyFromList();
                    } while ((!cities.get(numberOfCity).getListOfCompany().isEmpty())
                            & (answerYesOrNo("Желаете удалить еще одну компанию?")));
                }
                countries.get(numberOfCountry).setNetProfitFromCompanies(
                        countries.get(numberOfCountry).calculateProfitFromCompanies().getValue());
            }
            default -> {
            }
        }
    }

    public static void printInfoOfTotalElements() {
        Continent.printTotalContinents();
        Country.printTotalCountries();
        Subject.printTotalSubjects();
        City.printTotalCities();
        Company.printTotalCompanies();
    }

    public static void workWithStaticArrays() {
        Company[] companiesOne;
        Company[][] companiesTwo = new Company[4][4];
        List<Company> companies = new ArrayList<>();
        int n = 0;
        System.out.println("Введите барнаульские компании");
        do {
            Company company = new Company();
            company.input(companies, false);
            company.setAddress("Россия, Алтайский край, Барнаул");
            companies.add(company);
            n++;
        } while ((n < 16) && (answerYesOrNo("Желаете продолжить ввод компаний? (y/n):")));
        companiesOne = companies.toArray(new Company[n]);
        if (n == 16) {
            int k = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    companiesTwo[i][j] = companies.get(k);
                    k++;
                }
            }
            System.out.println("Неотсортированный список:");
            Company.tableHeader();
            k = 1;
            for (int i = 0; i < companiesTwo.length; i++) {
                for (int j = 0; j < companiesTwo[i].length; j++) {
                    System.out.printf("* %-5d", k);
                    System.out.print(companiesTwo[i][j]);
                    k++;
                }
            }
        } else {
            System.out.println("Неотсортированный список:");
            Company.tableHeader();
            int k = 0;
            for (Company company : companiesOne) {
                System.out.printf("* %-5d", k);
                System.out.print(company);
                k++;
            }
        }
        quickSort(companiesOne, 0, companiesOne.length - 1);
        System.out.println("Список в порядке возрастания прибыли компаний:");
        int k = 0;
        Company.tableHeader();
        for (Company company : companiesOne) {
            System.out.printf("* %-5d", k);
            System.out.print(company);
            k++;
        }
    }

    public static void quickSort(Company[] arr, int low, int high) {
        if (low < high) {
            int pi = splitIntoParts(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int splitIntoParts(Company[] companies, int low, int high) {
        int middle = low + (high - low) / 2;
        long middleElement = companies[middle].getNetProfit();

        Company temp = companies[middle];
        ;
        companies[middle] = companies[high];
        companies[high] = temp;

        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (companies[j].getNetProfit() < middleElement) {
                i++;

                temp = companies[i];
                companies[i] = companies[j];
                companies[j] = temp;
            }
        }

        temp = companies[i + 1];
        companies[i + 1] = companies[high];
        companies[high] = temp;

        return i + 1;
    }

    public static void searchSomethingWithSameName(List<Continent> continentList) {
        Container<Printable> container = new Container<>();
        String currentName;
        currentName = AbstractElement.inputName("");
        for (Continent continent : continentList) {
            if (continent.getName().equalsIgnoreCase(currentName))
                container.addElement(continent);
            for (Country country : continent.getListOfCountries()) {
                if (country.getName().equalsIgnoreCase(currentName))
                    container.addElement(country);
                for (Subject subject : country.getListOfSubjects()) {
                    if (subject.getName().equalsIgnoreCase(currentName)) {
                        container.addElement(subject);
                    }
                    for (City city : subject.getListOfCities()) {
                        if (city.getName().equalsIgnoreCase(currentName))
                            container.addElement(city);
                        for (Company company : city.getListOfCompany()) {
                            if (company.getName().equalsIgnoreCase(currentName)) {
                                if (company instanceof Branch branch)
                                    container.addElement(branch);
                                else
                                    container.addElement(company);
                            }
                        }
                    }
                }
            }
        }
        if (container.getElements().isEmpty()) {
            System.out.println("Ничего не найдено");
            System.out.println(listIsEmpty);
        } else {
            System.out.println("Совпадения");
            container.displayElements();
        }
    }


}


