import java.util.Comparator;

public class CompanyComparator implements Comparator<Company> {

    private CompanyField sortByField;

    public CompanyComparator(CompanyField sortByField) {
        this.sortByField = sortByField;
    }


    @Override
    public int compare(Company companyOne, Company companyTwo) {
        return switch (sortByField) {

            case NAME -> companyOne.name.compareTo(companyTwo.name);
            case ADDRESS -> companyOne.address.compareTo(companyTwo.address);
            case TURNOVER -> Long.compare(companyOne.turnoverPerYear, companyTwo.turnoverPerYear);
            case NET_PROFIT -> Long.compare(companyOne.netProfit, companyTwo.netProfit);
            case DATE_OF_FOUNDATION -> companyOne.dateOfFoundation.compareTo(companyTwo.dateOfFoundation);
            case INDUSTRY -> companyOne.industry.compareTo(companyTwo.industry);
        };
    }
}
