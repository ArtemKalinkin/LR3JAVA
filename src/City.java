public class City {
    private String name;
    private int population;
    private int numberOfEnterprises;
    private Enterprise[] listOfEnterprises;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(String name, int population, int numberOfEnterprises, Enterprise[] listOfEnterprises) {
        this.name = name;
        this.population = population;
        this.numberOfEnterprises = numberOfEnterprises;
        this.listOfEnterprises = listOfEnterprises;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setNumberOfEnterprises(int numberOfEnterprises) {
        this.numberOfEnterprises = numberOfEnterprises;
    }

    public int getNumberOfEnterprises() {
        return numberOfEnterprises;
    }

    public void setListOfEnterprises(Enterprise[] listOfEnterprises) {
        this.listOfEnterprises = listOfEnterprises;
    }

    public Enterprise[] getListOfEnterprises() {
        return listOfEnterprises;
    }
}
