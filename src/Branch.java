import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Branch extends Company {

    public String status;
    private int numberOfEmployees;

    public Branch() {
    }

    public Branch(String name, String address, long turnoverPerYear, long netProfit, Date date, String industry,
                  String status, int numberOfEmployees) {
        super(name, address, turnoverPerYear, netProfit, date, industry);
        this.status = status;
        this.numberOfEmployees = numberOfEmployees;
    }


    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int inputNumberOfEmployees() {
        int number;
        System.out.print("Введите количество сотрудников: ");
        while (!Main.scanner.hasNextInt()) {
            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите количество сотрудников: ");
            Main.scanner.next();
        }
        number = Main.scanner.nextInt();
        if (number < 0)
            throw new IllegalArgumentException("Ошибка! Значение не может быть отрицательным!");

        Main.scanner.nextLine();
        return number;
    }

    public String inputStatus() {
        String status;
        do {
            System.out.print("Введите статус филиала: ");
            while (!Main.scanner.hasNextLine()) {
                System.out.print("Ошибка ввода!\nВведите статус филиала: ");
                Main.scanner.next();
            }
            status = Main.scanner.nextLine();
            if (status.isBlank()) {
                System.out.println("Вы не ввели статус филиала. По умолчанию устанавливается статус - неизвестно");
                status = "неизвестно";
            }
        } while (status.isBlank());

        status = status.trim();
        return status;
    }

    @Override
    public void input(List<Company> companyList, boolean mode) {
        super.input(companyList, mode);
        status = inputStatus();
        do {
            try {
                numberOfEmployees = inputNumberOfEmployees();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Количество сотрудников не может быть отрицательным");
            }
        } while (true);
    }

    @Override
    public String toString() {
        String string;
        SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy");
        string = String.format(" * %-60s * %-47s * %-17d * %-13d * %-32s * %-14s *\n", name + " - " + status
                        + " филиал. Кол-во сотрудников: " + numberOfEmployees, address, turnoverPerYear,
                netProfit, industry, form.format(dateOfFoundation));
        string += "********************************************************************************************" +
                "**********************************************************************************************" +
                "***********************\n";
        return string;
    }

    @Override
    public void changeFields(List<Company> companyList) {
        int number;
        System.out.println("\nИЗМЕНЕНИЯ ПОЛЕЙ");
        tableHeader();
        System.out.printf("* %-5d", 1);
        System.out.print(this);
        do {
            System.out.println("1.Название филиала");
            System.out.println("2.Местоположение филиала");
            System.out.println("3.Оборот за год");
            System.out.println("4.Прибиль филиала");
            System.out.println("5.Отрасль филиала");
            System.out.println("6.Дата основания филиала");
            System.out.println("7.Статус филиала");
            System.out.println("8.Количество сотрудников");
            do {
                System.out.print("Введите номер поля, который желаете изменить: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > 8))
                    System.out.println("Поля с данным номером нет");
            } while ((number < 1) || (number > 8));
            Main.scanner.nextLine();
            switch (number) {
                case 1 -> {
                    do {
                        try {
                            name = inputName("компании");
                            break;
                        } catch (StringWithSmallLetterException e) {
                            System.out.println("Название филиала необходимо писать с заглавной буквы!");
                        }
                    } while (true);
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
                case 4 -> netProfit = inputNetProfit("филиала");
                case 5 -> industry = inputIndustry("филиала");
                case 6 -> inputDateOfFoundation("филиала");
                case 7 -> status = inputStatus();
                case 8 -> numberOfEmployees = inputNumberOfEmployees();
                default -> {
                }
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить изменение полей в данном филиале?"));
    }

    @Override
    public Branch clone() {
        return (Branch) super.clone();
    }

    @Override
    public String getFormattedInfo() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return "Филиал компании: " + name + " - " + status + " филиал. Кол-во сотрудников: " + numberOfEmployees +
                "; " + address + "; " + turnoverPerYear + "; " + netProfit + "; " + industry +
                format.format(dateOfFoundation) + ".";
    }
}
