import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssignDestination {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner addresses = new Scanner(new FileReader(args[0]));
//        Scanner drivers = new Scanner(new FileReader(args[1]));
        Scanner addresses = new Scanner(new FileReader("Addresses.txt"));
        Scanner drivers = new Scanner(new FileReader("Drivers.txt"));
        List<String> addressesList = new ArrayList<>();
        List<?> driversList = new ArrayList<String>();
        String outString;

        StringBuilder sb = new StringBuilder();

        while(addresses.hasNextLine()) {
            addressesList.add(addresses.nextLine());
        }

        addresses.close();
        drivers.close();
        outString = addressesList.toString();

        System.out.println(outString);
    }
}
