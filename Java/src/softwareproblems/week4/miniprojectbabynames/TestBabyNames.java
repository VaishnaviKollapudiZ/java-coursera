package softwareproblems.week4.miniprojectbabynames;

public class TestBabyNames {
    public static void main(String[] args){
        BabyNames babyNames = new BabyNames();
        babyNames.testTotalBirths();
        babyNames.testGetRank();
        babyNames.testGetName();
        babyNames.testWhatIsNameInYear();
        babyNames.testGetAverageRank();
        babyNames.testYearOfHighestRank();
        babyNames.testGetTotalBirthsRankedHigher();
    }
}
