public abstract class AbstractElement {
    protected String name;
    protected long population;
    protected int square;

    public AbstractElement() {
    }

    public AbstractElement(String name, int population, int square) {
        this.name = name;
        this.population = population;
        this.square = square;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public static String inputName(String s) {
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

    public static int inputNumber(String s) {
        int number;
        System.out.print("Введите количество " + s + ": ");
        while (!Main.scanner.hasNextInt()) {
            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите количество " + s + ": ");
            Main.scanner.next();
        }
        number = Main.scanner.nextInt();
        if (number < 0)
            throw new IllegalArgumentException("Ошибка! Значение не может быть отрицательным!");
        Main.scanner.nextLine();
        return number;
    }

    public static int inputSquare(String s) {
        int square;
        System.out.print("Введите площадь " + s + ": ");
        while (!Main.scanner.hasNextInt()) {
            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите площадь " + s + ": ");
            Main.scanner.next();
        }
        square = Main.scanner.nextInt();
        if (square < 0)
            throw new IllegalArgumentException("Ошибка! Значение не может быть отрицательным!");

        Main.scanner.nextLine();
        return square;
    }

    public static long inputPopulation(String s) {
        long population;
        System.out.print("Введите население " + s + ": ");
        while (!Main.scanner.hasNextLong()) {
            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите население " + s + ": ");
            Main.scanner.next();
        }
        population = Main.scanner.nextLong();
        if (population < 0)
            throw new IllegalArgumentException("Ошибка! Значение не может быть отрицательным!");

        Main.scanner.nextLine();
        return population;
    }
}
