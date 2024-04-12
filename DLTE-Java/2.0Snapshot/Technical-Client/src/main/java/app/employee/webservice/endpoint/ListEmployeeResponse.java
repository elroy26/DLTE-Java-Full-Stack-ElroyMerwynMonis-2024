
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
 * <p>Java class for listEmployeeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listEmployeeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="serviceStatus" type="{http://employee.services}serviceStatus" form="qualified"/>
 *                   &lt;element name="employeeProfile" type="{http://employee.services}EmployeeDetails" maxOccurs="unbounded" form="qualified"/>
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
@XmlType(name = "listEmployeeResponse", propOrder = {
    "_return"
})
public class ListEmployeeResponse {

    @XmlElement(name = "return", namespace = "")
    protected ListEmployeeResponse.Return _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ListEmployeeResponse.Return }
     *     
     */
    public ListEmployeeResponse.Return getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListEmployeeResponse.Return }
     *     
     */
    public void setReturn(ListEmployeeResponse.Return value) {
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
     *         &lt;element name="serviceStatus" type="{http://employee.services}serviceStatus" form="qualified"/>
     *         &lt;element name="employeeProfile" type="{http://employee.services}EmployeeDetails" maxOccurs="unbounded" form="qualified"/>
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
        "serviceStatus",
        "employeeProfile"
    })
    public static class Return {

        @XmlElement(required = true)
        protected ServiceStatus serviceStatus;
        @XmlElement(required = true)
        protected List<EmployeeDetails> employeeProfile;

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

        /**
         * Gets the value of the employeeProfile property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the employeeProfile property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEmployeeProfile().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EmployeeDetails }
         * 
         * 
         */
        public List<EmployeeDetails> getEmployeeProfile() {
            if (employeeProfile == null) {
                employeeProfile = new ArrayList<EmployeeDetails>();
            }
            return this.employeeProfile;
        }

    }

}
