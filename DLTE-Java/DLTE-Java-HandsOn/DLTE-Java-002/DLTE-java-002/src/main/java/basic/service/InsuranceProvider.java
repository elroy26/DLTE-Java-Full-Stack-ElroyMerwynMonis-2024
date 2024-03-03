package basic.service;
import java.util.Scanner;

public class InsuranceProvider {
    public static void main(String[] args) {
        // Initialization
        String hdfc = "car insurance, health insurance, cyber insurance, bike insurance";
        String paytm = "life insurance, education insurance, home insurance, bike insurance";
        String canara = "travel insurance, car insurance, education insurance, motor insurance";
        String sbi = "travel insurance, education insurance, cyber insurance, food insurance";

        // Input features
        String[] features = new String[3];
        Scanner input = new Scanner(System.in);
        System.out.println("Enter any 3 features to know the best suitable insurance provider:");
        for (int i = 0; i < 3; i++) {
            features[i] = input.nextLine();
        }

        // Process
        boolean found = false;
        if (containsAny(hdfc, features)) {
            System.out.println("HDFC is suitable for the provided features.");
            found = true;
        }
        if (containsAny(paytm, features)) {
            System.out.println("Paytm is suitable for the provided features.");
            found = true;
        }
        if (containsAny(canara, features)) {
            System.out.println("Canara is suitable for the provided features.");
            found = true;
        }
        if (containsAny(sbi, features)) {
            System.out.println("SBI is suitable for the provided features.");
            found = true;
        }
        if (!found) {
            System.out.println("No suitable insurance provider found for the provided features.");
        }
    }

    // Process
    public static boolean containsAny(String haystack, String[] needles) {
        for (String needle : needles) {
            if (haystack.contains(needle)) {
                return true;
            }
        }
        return false;
    }
}
