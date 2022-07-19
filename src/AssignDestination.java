import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class AssignDestination {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner addresses = new Scanner(new FileReader(args[0]));
        Scanner drivers = new Scanner(new FileReader(args[1]));
        //For debugging in local environment.
//        Scanner addresses = new Scanner(new FileReader("Addresses.txt"));
//        Scanner drivers = new Scanner(new FileReader("Drivers.txt"));

        List<String> addressesList;
        List<String> driversList;

        addressesList = FillList(addresses);
        driversList = FillList(drivers);

        TopSecretAlgorithm(addressesList, driversList);

        addresses.close();
        drivers.close();
    }

    private static List<String> FillList(Scanner values) {
        List<String> list = new ArrayList<>();

        while (values.hasNextLine()) {
            list.add(values.nextLine());
        }

        return list;
    }

    private static void TopSecretAlgorithm(List<String> address, List<String> driver) {
        List<String> assignedDriver = new ArrayList<>();
        List<Driver> listDriversSS = new ArrayList<>();
        double CONSTANT_VOWELS = 1.5;
        double CONSTANT_CONSONANTS = 1.0;
        double CONSTANT_COMMON_FACTOR = 1.5;

        double baseSuitabilityScore;

        for (int i = 0; i < address.size(); i++) {
            for (int j = 0; j < driver.size(); j++) {
                Driver realDriver = new Driver();

                //If the number is even.
                if (address.get(i).length() % 2 == 0) {
                    baseSuitabilityScore = CountVowels(driver.get(j)) * CONSTANT_VOWELS;
                }

                //If the number is not even, therefore odd.
                else {
                    baseSuitabilityScore = CountConsonants(driver.get(j)) * CONSTANT_CONSONANTS;
                }

                if (CheckCommonFactor(address.get(i), driver.get(j))) {
                    baseSuitabilityScore = (baseSuitabilityScore * CONSTANT_COMMON_FACTOR);
                }

                realDriver.setBaseSuitabilityScore(baseSuitabilityScore);
                realDriver.setName(driver.get(j));

                listDriversSS.add(realDriver);

                //Sort from most significant to less.
                Collections.sort(listDriversSS, new Comparator<Driver>() {
                    @Override
                    public int compare(Driver d1, Driver d2) {
                        return Double.compare(d2.getBaseSuitabilityScore(), d1.getBaseSuitabilityScore());
                    }
                });
            }

            System.out.println("Address: " + address.get(i));
            System.out.println("SS: " + listDriversSS.get(0).getBaseSuitabilityScore());
            System.out.println("Driver: " + listDriversSS.get(0).getName());

            driver.remove(listDriversSS.get(0).getName());
            listDriversSS.remove(0);
        }
    }

    private static int CountVowels(String driver) {
        int count = 0;

        for (int i=0 ; i<driver.length(); i++){
            char ch = driver.charAt(i);
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }

        return  count;
    }

    private static int CountConsonants(String driver) {
        int count = 0;

        for (int i=0 ; i<driver.length(); i++){
            char ch = driver.charAt(i);
            if (ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u'  && ch != ' ') {
                count++;
            }
        }

        return  count;
    }

    private static Boolean CheckCommonFactor(String address, String driver) {
        boolean commonFactor;
        int addressLength = address.length();
        int driversLength = driver.length();
        List<Integer> factorsAddress = new ArrayList<>();
        List<Integer> factorsDrivers = new ArrayList<>();

        //No division by 1. That's why we start at 2-
        for (int i = 2; i < addressLength; i++) {
            if (addressLength % i == 0) {
                factorsAddress.add(i);
            }
        }

        for (int i = 2; i < driversLength; i++) {
            if (driversLength % i == 0) {
                factorsDrivers.add(i);
            }
        }

        commonFactor = !Collections.disjoint(factorsAddress, factorsDrivers);

        return commonFactor;
    }
}
