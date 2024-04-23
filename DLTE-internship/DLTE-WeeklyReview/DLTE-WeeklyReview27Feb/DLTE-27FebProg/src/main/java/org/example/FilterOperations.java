package org.example;

import java.util.ArrayList;
import java.util.List;

public class FilterOperations implements FilterOperationsInterface {
    @Override
    public List<Object> filterByPermanentPincode(List<Object> employeeDataList,Integer pincode) {
        List<Object> filteredData = new ArrayList<>();
        for (Object entry : employeeDataList) {
            if (entry instanceof List) {
                List<?> dataEntry = (List<?>) entry;
                if (dataEntry.size() >= 2 && dataEntry.get(1) instanceof EmployeePermanentAddress) {
                    EmployeePermanentAddress permanentAddress = (EmployeePermanentAddress) dataEntry.get(1);
                    if (permanentAddress.getPincodePermanent().equals(pincode)) {
                        filteredData.add(entry);
                    }
                }
            }
        }
        return filteredData;
    }
    }


