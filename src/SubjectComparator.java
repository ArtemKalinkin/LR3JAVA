import java.util.Comparator;

public class SubjectComparator implements Comparator<Subject> {

    private Subject.SubjectField sortByField;

    public SubjectComparator(Subject.SubjectField sortByField) {
        this.sortByField = sortByField;
    }

    @Override
    public int compare(Subject subjectOne, Subject subjectTwo) {
        return switch (sortByField) {
            case NAME -> subjectOne.name.compareTo(subjectTwo.name);
            case POPULATION -> Long.compare(subjectOne.population, subjectTwo.population);
            case SQUARE -> Integer.compare(subjectOne.square, subjectTwo.square);
            case NUMBER_OF_CITIES -> Integer.compare(subjectOne.getNumberOfCities(), subjectTwo.getNumberOfCities());
        };
    }
}
