
package app.employee.webservice.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import services.employee.EmployeeDetails;


/**
 * <p>Java class for addNewEmployee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addNewEmployee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="employeeDetails" type="{http://employee.services}EmployeeDetails" form="qualified"/>
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
@XmlType(name = "addNewEmployee", propOrder = {
    "arg0"
})
public class AddNewEmployee {

    @XmlElement(namespace = "")
    protected AddNewEmployee.Arg0 arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link AddNewEmployee.Arg0 }
     *     
     */
    public AddNewEmployee.Arg0 getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddNewEmployee.Arg0 }
     *     
     */
    public void setArg0(AddNewEmployee.Arg0 value) {
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
     *         &lt;element name="employeeDetails" type="{http://employee.services}EmployeeDetails" form="qualified"/>
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
    public static class Arg0 {

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

}
