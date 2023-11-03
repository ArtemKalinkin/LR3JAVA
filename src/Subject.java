public class Subject {
    private String name;
    private int numberOfCities;
    private int population;
    private int square;
    private City[] listOfCities;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, int numberOfCities, int population, int square, City[] listOfCity) {
        this.name = name;
        this.numberOfCities = numberOfCities;
        this.population = population;
        this.square = square;
        this.listOfCities = listOfCity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumberOfCities(int numberOfCities) {
        this.numberOfCities = numberOfCities;
    }

    public int getNumberOfCities() {
        return numberOfCities;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getSquare() {
        return square;
    }

    public void setListOfCity(City[] listOfCity) {
        this.listOfCities = listOfCity;
    }

    public City[] getListOfCity() {
        return listOfCities;
    }
}
