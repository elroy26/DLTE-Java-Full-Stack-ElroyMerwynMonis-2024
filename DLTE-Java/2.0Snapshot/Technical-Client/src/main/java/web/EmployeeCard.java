
package web;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employeeCard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employeeCard">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeAddresses" type="{http://web/}employeeAddress" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="employeeDetails" type="{http://web/}employeeDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeeCard", propOrder = {
    "employeeAddresses",
    "employeeDetails"
})
public class EmployeeCard {

    @XmlElement(nillable = true)
    protected List<EmployeeAddress> employeeAddresses;
    @XmlElement(nillable = true)
    protected List<EmployeeDetails> employeeDetails;

    /**
     * Gets the value of the employeeAddresses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employeeAddresses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployeeAddresses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeAddress }
     * 
     * 
     */
    public List<EmployeeAddress> getEmployeeAddresses() {
        if (employeeAddresses == null) {
            employeeAddresses = new ArrayList<EmployeeAddress>();
        }
        return this.employeeAddresses;
    }

    /**
     * Gets the value of the employeeDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employeeDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployeeDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeDetails }
     * 
     * 
     */
    public List<EmployeeDetails> getEmployeeDetails() {
        if (employeeDetails == null) {
            employeeDetails = new ArrayList<EmployeeDetails>();
        }
        return this.employeeDetails;
    }

}
