package console.app;

import org.example.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.lang.System.exit;
import static org.example.EmployeeMain.enterDetails;
import static org.example.EmployeeMain.outputDetails;
import static org.example.EmployeeMain.filterData;

public class EmployeeProfile {
    static Scanner scanner=new Scanner(System.in);
    static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    static Logger logger= LoggerFactory.getLogger(EmployeeProfile.class);
    public static void main(String[] args) {
        try {
            while (true) {
                logger.info(resourceBundle.getString("app.greet"));
                System.out.println(resourceBundle.getString("app.greet"));
                System.out.println(resourceBundle.getString("app.menu"));
                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    logger.warn(resourceBundle.getString("app.menu.exception"));
                    System.out.println(resourceBundle.getString("app.menu.exception"));
                    scanner.nextLine();
                    continue;
                }

                switch (choice) {
                    case 1:
                        String option;
                    do {
                        enterDetails();
                        logger.info(resourceBundle.getString("app.request.again"));
                        System.out.println(resourceBundle.getString("app.request.again"));

                        do {
                            option = scanner.next();
                            if (!option.matches("(?i)(Yes|No)")) {
                                logger.error(resourceBundle.getString("app.request.error"));
                                System.out.println(resourceBundle.getString("app.request.error"));
                            }
                        } while (!option.matches("(?i)(Yes|No)"));
                    } while (option.equalsIgnoreCase("Yes"));
                    break;

                    case 2:
                        outputDetails();
                        break;
                    case 3:
                        do {
                            System.out.println(resourceBundle.getString("app.filter.pincode"));
                            String pincodeInput;
                            do {
                                pincodeInput = scanner.next();
                                if (!pincodeInput.matches("\\d{6}")) {
                                    logger.error(resourceBundle.getString("app.pincode.error"));
                                    System.out.println(resourceBundle.getString("app.pincode.error"));
                                }
                            } while (!pincodeInput.matches("\\d{6}"));

                            int pincode = Integer.parseInt(pincodeInput);

                            List<Object> filteredData = filterData(pincode);
                            System.out.println(resourceBundle.getString("app.filter"));
                            for (Object entry : filteredData) {
                                System.out.println(entry);
                            }
                            logger.info(resourceBundle.getString("app.request.pincode"));
                            System.out.println(resourceBundle.getString("app.request.pincode"));
                            do {
                                option = scanner.next();
                                if (!option.matches("(?i)(Yes|No)")) {
                                    logger.error("app.request.error");
                                    System.out.println(resourceBundle.getString("app.request.error"));
                                }
                            } while (!option.matches("(?i)(Yes|No)"));
                        } while (option.equalsIgnoreCase("Yes"));
                        break;

                    case 4:
                        logger.info(resourceBundle.getString("app.exit"));
                        System.out.println(resourceBundle.getString("app.exit"));
                        exit(0);
                    default:
                        logger.warn(resourceBundle.getString("app.default"));
                        System.out.println(resourceBundle.getString("app.default"));
                }
            }
        } catch (Exception e) {
            logger.error(resourceBundle.getString("app.exception"));
            System.out.println(resourceBundle.getString("app.exception") + e.getMessage());
            e.printStackTrace();
        }
    }
}
