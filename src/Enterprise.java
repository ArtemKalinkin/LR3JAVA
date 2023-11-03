import java.util.Date;
import java.util.GregorianCalendar;

public class Enterprise {
    private String name;
    private String address;
    private long turnoverPerYear;
    private long netProfit;
    private GregorianCalendar date;
    private String industry;

    public Enterprise() {
    }

    public Enterprise(String name) {
        this.name = name;
    }

    public Enterprise(String name, String address, long turnoverPerYear, long netProfit, GregorianCalendar date, String industry) {
        this.name = name;
        this.address = address;
        this.turnoverPerYear = turnoverPerYear;
        this.netProfit = netProfit;
        this.date = date;
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

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

}
