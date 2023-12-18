import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Company implements Modifiable<Company>, Cloneable, Printable {
    protected String name;
    protected String address;
    protected long turnoverPerYear;
    protected long netProfit;
    protected Date dateOfFoundation;
    protected String industry;

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

    public String inputName(String s) {
        String name;
        do {
            System.out.print("Введите название " + s + ": ");
            while (!Main.scanner.hasNextLine()) {
                System.out.print("\nОшибка ввода!\nВведите название " + s + ": ");
                Main.scanner.next();
            }
            name = Main.scanner.nextLine();
            if (!Character.isUpperCase(name.charAt(0)))
                throw new StringWithSmallLetterException("Ошибка! Строка начинается не с заглавной буквы!");
            if (name.isBlank())
                System.out.println("Данное поле не может быть пустым");
        } while (name.isBlank());
        name = name.trim();
        return name;
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

    public long inputNetProfit(String s) {
        long profit;
        System.out.print("Введите прибыль " + s + ": ");
        while (!Main.scanner.hasNextLong()) {
            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите прибыль " + s + ": ");
            Main.scanner.next();
        }
        profit = Main.scanner.nextLong();

        Main.scanner.nextLine();
        return profit;
    }

    public String inputIndustry(String s) {
        String industry;
        do {
            System.out.print("Введите отрасль " + s + ": ");
            while (!Main.scanner.hasNextLine()) {
                System.out.print("Ошибка ввода!\nВведите отрасль " + s + ": ");
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

    public void inputDateOfFoundation(String s) {
        String stringDate;
        do {
            System.out.print("Введите дату основания " + s + " в формате (дд.мм.гггг): ");
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


    public void input(List<Company> companyList, boolean mode) {
        String s;
        if (!mode) {
            s = "компании";
            System.out.println("\nВВОД КОМПАНИИ");
        } else {
            s = "филиала";
            System.out.println("\nВВОД ФИЛИАЛА КОМПАНИИ");
        }
        do {
            try {
                name = inputName(s);
                break;
            } catch (StringWithSmallLetterException e) {
                System.out.println("Название " + s + " необходимо писать с заглавной буквы!");
            }
        } while (true);
        do {
            try {
                turnoverPerYear = inputTurnover();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Оборот за год не может быть отрицательным");
            }
        } while (true);
        netProfit = inputNetProfit(s);
        industry = inputIndustry(s);
        inputDateOfFoundation(s);
        if (!mode)
            incrementTotalCompanies();
    }

    public static void tableHeader() {
        System.out.print("********************************************************************************************" +
                "**********************************************************************************************" +
                "************************\n");
        System.out.print("* Номер *                           Компания                           *                  " +
                "Местоположение                 *   Оборот за год   *    Прибыль    *              Отрасль       " +
                "      * Дата основания *\n");
        System.out.print("********************************************************************************************" +
                "**********************************************************************************************" +
                "************************\n");

    }

    @Override
    public String toString() {
        String string;
        SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy");
        string = String.format(" * %-60s * %-47s * %-17d * %-13d * %-32s * %-14s *\n", name, address,
                turnoverPerYear, netProfit, industry, form.format(dateOfFoundation));
        string += "********************************************************************************************" +
                "**********************************************************************************************" +
                "***********************\n";
        return string;
    }

    public void output(int number) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        System.out.printf("* %-5d * %-53s * %-47s * ", number + 1, name, address);
        System.out.printf("%-17d * %-13d * ", turnoverPerYear, netProfit);
        System.out.printf("%-32s * %-14s *\n", industry, format.format(dateOfFoundation));
        System.out.print("********************************************************************************************" +
                "**********************************************************************************************" +
                "********************\n");
    }

    public void changeFields(List<Company> companyList) {
        int number;
        boolean flag;
        System.out.println("\nИЗМЕНЕНИЯ ПОЛЕЙ");
        tableHeader();
        System.out.printf("* %-5d", 1);
        System.out.print(this);
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
                                name = inputName("компании");
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
                case 4 -> netProfit = inputNetProfit("компании");
                case 5 -> industry = inputIndustry("компании");
                case 6 -> inputDateOfFoundation("компании");
                default -> {
                }
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить изменение полей в данной компании?"));
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

    @Override
    public Company clone() {
        try {
            return (Company) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String getFormattedInfo() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return "Компания: " + name + "; " + address + "; " + turnoverPerYear + "; " + netProfit + "; " + industry +
                "; " + format.format(dateOfFoundation) + ".";
    }
}
