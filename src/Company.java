import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Company {
    private String name;
    private String address;
    private long turnoverPerYear;
    private long netProfit;
    private Date dateOfFoundation;
    private String industry;

    private static int totalCompanies = 0;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public Company(String name, String address, long turnoverPerYear, long netProfit, Date date, String industry) {
        this.name = name;
        this.address = address;
        this.turnoverPerYear = turnoverPerYear;
        this.netProfit = netProfit;
        this.dateOfFoundation = date;
        this.industry = industry;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setTurnoverPerYear(long turnoverPerYear) {
        this.turnoverPerYear = turnoverPerYear;
    }

    public long getTurnoverPerYear() {
        return turnoverPerYear;
    }

    public void setNetProfit(long netProfit) {
        this.netProfit = netProfit;
    }

    public long getNetProfit() {
        return netProfit;
    }

    public void setDateOfFoundation(Date dateOfFoundation) {
        this.dateOfFoundation = dateOfFoundation;
    }

    public Date getDateOfFoundation() {
        return dateOfFoundation;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Company company))
            return false;
        return this.name.equals(company.getName());
    }

    public long inputTurnover() {
        long turnover;
        System.out.print("Введите оборот за год: ");
        while (!Main.scanner.hasNextLong()) {
            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите оборот за год: ");
            Main.scanner.next();
        }
        turnover = Main.scanner.nextLong();
        if (turnover < 0)
            throw new IllegalArgumentException("Ошибка! Значение не может быть отрицательным!");

        Main.scanner.nextLine();
        return turnover;
    }

    public long inputNetProfit() {
        long profit;
        System.out.print("Введите прибыль компании: ");
        while (!Main.scanner.hasNextLong()) {
            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите прибыль компании: ");
            Main.scanner.next();
        }
        profit = Main.scanner.nextLong();

        Main.scanner.nextLine();
        return profit;
    }

    public String inputIndustry() {
        String industry;
        do {
            System.out.print("Введите отрасль компании: ");
            while (!Main.scanner.hasNextLine()) {
                System.out.print("Ошибка ввода!\nВведите отрасль компании: ");
                Main.scanner.next();
            }
            industry = Main.scanner.nextLine();
            if (industry.isBlank())
                System.out.println("Данное поле не может быть пустым");
        } while (industry.isBlank());

        industry = industry.trim();
        return industry;
    }

    public Date parseStringDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            throw new ParseException("Date parsing error", e.getErrorOffset());
        }
    }

    public void inputDateOfFoundation() {
        String stringDate;
        do {
            System.out.print("Введите дату основания компании в формате (дд.мм.гггг): ");
            try {
                while (!Main.scanner.hasNextLine()) {
                    System.out.print("Ошибка ввода!\nВведите дату основания: ");
                    Main.scanner.next();
                }
                stringDate = Main.scanner.nextLine();
                stringDate = stringDate.trim();
                dateOfFoundation = parseStringDate(stringDate);
                break;
            } catch (ParseException e) {
                System.out.println("Ошибка ввода!\nДата введена некорректно!");
            }
        } while (true);

    }


    public void input(List<Company> companyList) {
        boolean flag;
        System.out.println("\nВВОД ПРЕДПРИЯТИЯ");
        do {
            flag = false;
            do {
                try {
                    name = AuxiliaryClass.inputNameOfSomething("компании");
                    break;
                } catch (StringWithSmallLetterException e) {
                    System.out.println("Название компании необходимо писать с заглавной буквы!");
                }
            } while (true);
            for (Company otherCompany : companyList)
                if ((this != otherCompany) & (this.equals(otherCompany))) {
                    System.out.println("Компания с данным названием уже есть в списке");
                    flag = true;
                }
        } while (flag);
        do {
            try {
                turnoverPerYear = inputTurnover();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Оборот за год не может быть отрицательным");
            }
        } while (true);
        netProfit = inputNetProfit();
        industry = inputIndustry();
        inputDateOfFoundation();
        incrementTotalCompanies();
    }

    public static void tableHeader() {
        System.out.print("***************************************************************************************" +
                "**************************************************************************************\n");
        System.out.print("* Номер *        Компания         *                  Местоположение                 " +
                "*   Оборот за год   *    Прибыль    *              Отрасль             * Дата основания *\n");
        System.out.print("*******************************************************************************************" +
                "**********************************************************************************\n");
    }

    public void output(int number) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        System.out.printf("* %-5d * %-23s * %-47s * ", number + 1, name, address);
        System.out.printf("%-17d * %-13d * ", turnoverPerYear, netProfit);
        System.out.printf("%-32s * %-14s *\n", industry, format.format(dateOfFoundation));
        System.out.print("****************************************************************************************" +
                "*************************************************************************************\n");
    }

    public void changeFields(List<Company> companyList) {
        int number;
        boolean flag;
        System.out.println("\nИЗМЕНЕНИЯ ПОЛЕЙ");
        tableHeader();
        output(0);
        do {
            System.out.println("1.Название компании");
            System.out.println("2.Местоположение компании");
            System.out.println("3.Оборот за год");
            System.out.println("4.Прибиль компании");
            System.out.println("5.Отрасль компании");
            System.out.println("6.Дата основания компании");
            do {
                System.out.print("Введите номер поля, который желаете изменить: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > 6))
                    System.out.println("Поля с данным номером нет");
            } while ((number < 1) || (number > 6));
            Main.scanner.nextLine();
            switch (number) {
                case 1 -> {
                    do {
                        flag = false;
                        do {
                            try {
                                name = AuxiliaryClass.inputNameOfSomething("компании");
                                break;
                            } catch (StringWithSmallLetterException e) {
                                System.out.println("Название компании необходимо писать с заглавной буквы!");
                            }
                        } while (true);
                        for (Company otherCompany : companyList)
                            if ((this != otherCompany) && (this.equals(otherCompany))) {
                                System.out.println("Данный город уже есть в списке");
                                flag = true;
                            }
                    } while (flag);
                }
                case 2 -> {
                    System.out.println("Данное поле заполняется автоматически и его нельзя изменить");
                    System.out.println("Для его изменения вам необходимо совершить корректировку в стране, субъекте");
                    System.out.println("и городе, которые относятся к данному предприятию в полях \"Название\"");
                }
                case 3 -> {
                    do {
                        try {
                            turnoverPerYear = inputTurnover();
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Оборот за год не может быть отрицательным");
                        }
                    } while (true);
                }
                case 4 -> netProfit = inputNetProfit();
                case 5 -> industry = inputIndustry();
                case 6 -> inputDateOfFoundation();
                default -> {
                }
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить изменение полей в данном компании?"));
    }

    public static void incrementTotalCompanies() {
        totalCompanies++;
    }

    public static void decrementTotalCompanies() {
        totalCompanies--;
    }

    public static void printTotalCompanies() {
        System.out.println("Всего вы внесли в список " + totalCompanies + " компаний");
    }
}
