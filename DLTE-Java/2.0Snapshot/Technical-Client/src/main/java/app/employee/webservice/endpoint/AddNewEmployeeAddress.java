
package app.employee.webservice.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import services.employee.EmployeeAddress;


/**
 * <p>Java class for addNewEmployeeAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addNewEmployeeAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="employeePermanentAddress" type="{http://employee.services}EmployeeAddress" form="qualified"/>
 *                   &lt;element name="employeeTemporaryAddress" type="{http://employee.services}EmployeeAddress" form="qualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addNewEmployeeAddress", propOrder = {
    "arg0"
})
public class AddNewEmployeeAddress {

    @XmlElement(namespace = "")
    protected AddNewEmployeeAddress.Arg0 arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link AddNewEmployeeAddress.Arg0 }
     *     
     */
    public AddNewEmployeeAddress.Arg0 getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddNewEmployeeAddress.Arg0 }
     *     
     */
    public void setArg0(AddNewEmployeeAddress.Arg0 value) {
        this.arg0 = value;
    }


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
     *         &lt;element name="employeePermanentAddress" type="{http://employee.services}EmployeeAddress" form="qualified"/>
     *         &lt;element name="employeeTemporaryAddress" type="{http://employee.services}EmployeeAddress" form="qualified"/>
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
        "employeePermanentAddress",
        "employeeTemporaryAddress"
    })
    public static class Arg0 {

        @XmlElement(required = true)
        protected EmployeeAddress employeePermanentAddress;
        @XmlElement(required = true)
        protected EmployeeAddress employeeTemporaryAddress;

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

    }

}
