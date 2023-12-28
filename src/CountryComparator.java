import java.util.Comparator;

public class CountryComparator implements Comparator<Country> {
    private Country.CountryField sortByField;

    public CountryComparator(Country.CountryField sortByField) {
        this.sortByField = sortByField;
    }

    @Override
    public int compare(Country countryOne, Country countryTwo) {
        return switch (sortByField) {
            case NAME -> countryOne.name.compareTo(countryTwo.name);
            case POPULATION -> Long.compare(countryOne.population, countryTwo.population);
            case SQUARE -> Integer.compare(countryOne.square, countryTwo.square);
            case NUMBER_OF_SUBJECTS -> Integer.compare(countryOne.getNumberOfSubjects(),
                    countryTwo.getNumberOfSubjects());
            case NET_PROFIT_FROM_COMPANIES -> Long.compare(countryOne.getNetProfitFromCompanies(),
                    countryTwo.getNetProfitFromCompanies());
            case INCOME -> Long.compare(countryOne.getIncome(), countryTwo.getIncome());
            case EXPENSES -> Long.compare(countryOne.getExpenses(), countryTwo.getExpenses());
            case BUDGET_DEFICIT_OR_SURPLUS -> Long.compare(countryOne.getBudgetDeficitOrSurplus(),
                    countryTwo.getBudgetDeficitOrSurplus());
        };
    }
}
