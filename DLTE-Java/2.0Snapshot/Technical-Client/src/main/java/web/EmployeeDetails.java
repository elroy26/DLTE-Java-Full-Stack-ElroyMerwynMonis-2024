
package web;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employeeDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employeeDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employeeFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employeeID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="employeeLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employeeMiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employeePermanentAddress" type="{http://web/}employeeAddress" minOccurs="0"/>
 *         &lt;element name="employeeTemporaryAddress" type="{http://web/}employeeAddress" minOccurs="0"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeeDetails", propOrder = {
    "email",
    "employeeFirstName",
    "employeeID",
    "employeeLastName",
    "employeeMiddleName",
    "employeePermanentAddress",
    "employeeTemporaryAddress",
    "phoneNumber"
})
public class EmployeeDetails {

    protected String email;
    protected String employeeFirstName;
    protected Integer employeeID;
    protected String employeeLastName;
    protected String employeeMiddleName;
    protected EmployeeAddress employeePermanentAddress;
    protected EmployeeAddress employeeTemporaryAddress;
    protected Long phoneNumber;

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the employeeFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    /**
     * Sets the value of the employeeFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeFirstName(String value) {
        this.employeeFirstName = value;
    }

    /**
     * Gets the value of the employeeID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the value of the employeeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEmployeeID(Integer value) {
        this.employeeID = value;
    }

    /**
     * Gets the value of the employeeLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeLastName() {
        return employeeLastName;
    }

    /**
     * Sets the value of the employeeLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeLastName(String value) {
        this.employeeLastName = value;
    }

    /**
     * Gets the value of the employeeMiddleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    /**
     * Sets the value of the employeeMiddleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeMiddleName(String value) {
        this.employeeMiddleName = value;
    }

    /**
     * Gets the value of the employeePermanentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    /**
     * Sets the value of the employeePermanentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setEmployeePermanentAddress(EmployeeAddress value) {
        this.employeePermanentAddress = value;
    }

    /**
     * Gets the value of the employeeTemporaryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    /**
     * Sets the value of the employeeTemporaryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setEmployeeTemporaryAddress(EmployeeAddress value) {
        this.employeeTemporaryAddress = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPhoneNumber(Long value) {
        this.phoneNumber = value;
    }

}
