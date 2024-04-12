
package app.employee.webservice.endpoint;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import services.employee.EmployeeDetails;
import services.employee.ServiceStatus;


/**
 * <p>Java class for listFilteredEmployeeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listFilteredEmployeeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="employeeDetails" type="{http://employee.services}EmployeeDetails" maxOccurs="unbounded" form="qualified"/>
 *                   &lt;element name="serviceStatus" type="{http://employee.services}serviceStatus" form="qualified"/>
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
@XmlType(name = "listFilteredEmployeeResponse", propOrder = {
    "_return"
})
public class ListFilteredEmployeeResponse {

    @XmlElement(name = "return", namespace = "")
    protected ListFilteredEmployeeResponse.Return _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ListFilteredEmployeeResponse.Return }
     *     
     */
    public ListFilteredEmployeeResponse.Return getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListFilteredEmployeeResponse.Return }
     *     
     */
    public void setReturn(ListFilteredEmployeeResponse.Return value) {
        this._return = value;
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
     *         &lt;element name="employeeDetails" type="{http://employee.services}EmployeeDetails" maxOccurs="unbounded" form="qualified"/>
     *         &lt;element name="serviceStatus" type="{http://employee.services}serviceStatus" form="qualified"/>
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
        "employeeDetails",
        "serviceStatus"
    })
    public static class Return {

        @XmlElement(required = true)
        protected List<EmployeeDetails> employeeDetails;
        @XmlElement(required = true)
        protected ServiceStatus serviceStatus;

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

        /**
         * Gets the value of the serviceStatus property.
         * 
         * @return
         *     possible object is
         *     {@link ServiceStatus }
         *     
         */
        public ServiceStatus getServiceStatus() {
            return serviceStatus;
        }

        /**
         * Sets the value of the serviceStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link ServiceStatus }
         *     
         */
        public void setServiceStatus(ServiceStatus value) {
            this.serviceStatus = value;
        }

    }

}
