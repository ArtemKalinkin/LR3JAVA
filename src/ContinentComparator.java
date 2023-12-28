import java.util.Comparator;

public class ContinentComparator implements Comparator<Continent> {

    private Continent.ContinentField sortByField;

    public ContinentComparator(Continent.ContinentField sortByField) {
        this.sortByField = sortByField;
    }

    @Override
    public int compare(Continent continentOne, Continent continentTwo) {
        return switch (sortByField) {
            case NAME -> continentOne.getName().compareTo(continentTwo.getName());
            case POPULATION -> Long.compare(continentOne.getPopulation(), continentTwo.getPopulation());
            case SQUARE -> Integer.compare(continentOne.getSquare(), continentTwo.getSquare());
            case NUMBER_OF_COUNTRIES ->
                    Integer.compare(continentOne.getNumberOfCountries(), continentTwo.getNumberOfCountries());
        };
    }
}
