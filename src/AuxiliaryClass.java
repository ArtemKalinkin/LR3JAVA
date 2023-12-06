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
            if ((answer.equals("No")) || (answer.equals("n")) || (answer.equals("N")) || (answer.equals("no"))) {
                return false;
            } else if ((answer.equals("Нет")) || (answer.equals("нет"))) {
                return false;
            } else if ((answer.equals("yes")) || (answer.equals("y")) || (answer.equals("Y")) || (answer.equals("Yes"))) {
                return true;
            } else if ((answer.equals("Да")) || (answer.equals("да"))) {
                return true;
            } else {
                System.out.println("Некорректный ответ!");
                System.out.println("Варианты положительных ответов: Да, да, Yes, yes, Y, y");
                System.out.println("Варианты отрицательных ответов: Нет, нет, No, no, N, n");
            }
        } while (true);
    }

    public static String inputNameOfSomething(String s) {
        String name;
        do {
            System.out.print("Введите название " + s + ": ");
            while (!Main.scanner.hasNextLine()) {
                System.out.print("\nОшибка ввода!\nВведите название " + s + ": ");
                Main.scanner.next();
            }
            name = Main.scanner.nextLine();
            if (name.isBlank())
                System.out.println("Данное поле не может быть пустым");
        } while (name.isBlank());
        name = name.trim();
        return name;
    }

    public static int inputNumberOfSomething(String s) {
        int number;
        do {
            System.out.print("Введите количество " + s + ": ");
            while (!Main.scanner.hasNextInt()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите количество " + s + ": ");
                Main.scanner.next();
            }
            number = Main.scanner.nextInt();
            if (number < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (number < 0);

        Main.scanner.nextLine();
        return number;
    }

    public static int inputSquareOfSomething(String s) {
        int square;
        do {
            System.out.print("Введите площадь " + s + ": ");
            while (!Main.scanner.hasNextInt()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите площадь " + s + ": ");
                Main.scanner.next();
            }
            square = Main.scanner.nextInt();
            if (square < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (square < 0);

        Main.scanner.nextLine();
        return square;
    }

    public static int inputPopulationOfSomething(String s) {
        int population;
        do {
            System.out.print("Введите население " + s + ": ");
            while (!Main.scanner.hasNextInt()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите население " + s + ": ");
                Main.scanner.next();
            }
            population = Main.scanner.nextInt();
            if (population < 0)
                System.out.println("Данное поле не может быть отрицательным!");
        } while (population < 0);

        Main.scanner.nextLine();
        return population;
    }


    public static int chooseContinent(List<Continent> listOfContinents) {
        int number = 0;
        int i;
        int size = listOfContinents.size();
        if (!listOfContinents.isEmpty()) {
            Continent.tableHeader();
            i = 0;
            for (Continent continent : listOfContinents) {
                continent.output(i);
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
            listOfContinents.get(number).output(0);
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
        int i = 0;
        int numberOfContinent;
        int numberOfCountry;
        int numberOfSubject;
        int numberOfCity;
        List<Country> countries;
        List<Subject> subjects;
        List<City> cities;
        List<Company> companies;
        switch (number) {
            case 1:
                if (continentList.isEmpty())
                    System.out.println("\n\n" + listIsEmpty);
                Continent.tableHeader();
                for (Continent continent : continentList) {
                    continent.output(i);
                    i++;
                }
                break;
            case 2:
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                if (numberOfContinent == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                countries = continentList.get(numberOfContinent).getListOfCountries();
                Country.tableHeader();
                for (Country country : countries) {
                    country.output(i);
                    i++;
                }
                break;
            case 3:
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
                    subject.output(i);
                    i++;
                }
                break;
            case 4:
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
                    city.output(i);
                    i++;
                }
                break;
            case 5:
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
                    company.output(i);
                    i++;
                }
                break;
            default:
                break;

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
                country.output(i);
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
                country.output(i);
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
            case 1:
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                continentList.get(numberOfContinent).changeFields(continentList);
                break;
            case 2:
                numberOfContinent = AuxiliaryClass.chooseContinent(continentList);
                countries = continentList.get(numberOfContinent).getListOfCountries();
                numberOfCountry = continentList.get(numberOfContinent).chooseCountry();
                if (numberOfCountry == -1) {
                    System.out.println(AuxiliaryClass.listIsEmpty);
                    break;
                }
                countries.get(numberOfCountry).changeFields(countries);
                countries.get(numberOfCountry).setAddressOfCompanies();
                break;
            case 3:
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
                break;
            case 4:
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
                break;
            case 5:
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
                break;
            default:
                break;
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
            case 1:
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
                break;
            case 2:
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
                break;
            case 3:
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
                break;
            case 4:
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
                break;
            case 5:
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
                break;
            default:
                break;
        }
    }

    public static void printInfoOfTotalElements() {
        Continent.printTotalContinents();
        Country.printTotalCountries();
        Subject.printTotalSubjects();
        City.printTotalCities();
        Company.printTotalCompanies();
    }
}


