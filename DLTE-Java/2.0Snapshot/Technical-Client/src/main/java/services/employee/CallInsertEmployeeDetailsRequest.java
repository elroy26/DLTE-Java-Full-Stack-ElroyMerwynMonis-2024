
package services.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeDetails" type="{http://employee.services}EmployeeDetails"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "employeeDetails"
})
@XmlRootElement(name = "callInsertEmployeeDetailsRequest")
public class CallInsertEmployeeDetailsRequest {

    @XmlElement(required = true)
    protected EmployeeDetails employeeDetails;

    /**
     * Gets the value of the employeeDetails property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeDetails }
     *     
     */
    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    /**
     * Sets the value of the employeeDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeDetails }
     *     
     */
    public void setEmployeeDetails(EmployeeDetails value) {
        this.employeeDetails = value;
    }

}
