import java.util.Comparator;

public class CityComparator implements Comparator<City> {

    private City.CityField sortByField;

    public CityComparator(City.CityField sortByField) {
        this.sortByField = sortByField;
    }

    @Override
    public int compare(City cityOne, City cityTwo) {
        return switch (sortByField) {
            case NAME -> cityOne.name.compareTo(cityTwo.name);
            case POPULATION -> Long.compare(cityOne.population, cityTwo.population);
            case SQUARE -> Integer.compare(cityOne.square, cityTwo.square);
            case NUMBER_OF_COMPANIES -> Integer.compare(cityOne.getNumberOfCompanies(), cityTwo.getNumberOfCompanies());
        };
    }
}
