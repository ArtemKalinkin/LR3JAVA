import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Enterprise {
    private String name;
    private String address;
    private long turnoverPerYear;
    private long netProfit;
    private Date dateOfFoundation;
    private String industry;

    public Enterprise() {
    }

    public Enterprise(String name) {
        this.name = name;
    }

    public Enterprise(String name, String address, long turnoverPerYear, long netProfit, Date date, String industry) {
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

    public void input() {
        boolean flag = true;
        String stringDate;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Scanner scanner = new Scanner(System.in);
        System.out.println("ВВОД ПРЕДПРИЯТИЯ\n");

        do {
            System.out.print("\nВведите название предприятия: ");
            while (!scanner.hasNextLine()) {
                System.out.println("Ошибка ввода!\nВведите название предприятия: ");
                scanner.next();
            }
            name = scanner.nextLine();
            if (name.isBlank())
                System.out.println("Данное поле не может быть пустым");
        } while (name.isBlank());

        do {
            System.out.print("\nВведите оборот за год: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода!\nВведите оборот за год: ");
                scanner.next();
            }
            turnoverPerYear = scanner.nextInt();
            if (turnoverPerYear < 0)
                System.out.println("Оборот не может быть отрицательным");
        } while (turnoverPerYear < 0);

        System.out.print("\nВведите прибыль предприятия: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка ввода!\nВведите прибыль предприятия: ");
            scanner.next();
        }
        netProfit = scanner.nextLong();

        do {
            System.out.print("\nВведите дату основания предприятия в формате (дд.мм.гггг): ");
            try {
                while (!scanner.hasNextLine()) {
                    System.out.println("Ошибка ввода!\nВведите дату основания: ");
                    scanner.next();
                }
                stringDate = scanner.nextLine();
                dateOfFoundation = format.parse(stringDate);
            } catch (ParseException e) {
                System.out.println("Ошибка ввода!\nДата введена некорректно!");
                flag = false;
            }
        } while (!flag);

        do {
            System.out.print("\nВведите отрасль предприятия: ");
            while (!scanner.hasNextLine()) {
                System.out.println("Ошибка ввода!\nВведите отрасль предприятия: ");
                scanner.next();
            }
            industry = scanner.nextLine();
            if (industry.isBlank())
                System.out.println("Данное поле не может быть пустым");
        } while (industry.isBlank());

        scanner.close();
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-23s * %-47s * ", number + 1, name, address);
        System.out.printf("%-17d * %-13d * ", turnoverPerYear, netProfit);
        System.out.printf("%-32s * %-14s *\n", industry, dateOfFoundation);
        System.out.print("****************************************************************************************" +
                "*************************************************************************************\n");
    }
}
