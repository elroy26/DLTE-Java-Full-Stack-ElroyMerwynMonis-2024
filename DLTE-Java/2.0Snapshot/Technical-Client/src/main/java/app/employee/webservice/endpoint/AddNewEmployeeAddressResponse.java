
package app.employee.webservice.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import services.employee.ServiceStatus;


/**
 * <p>Java class for addNewEmployeeAddressResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addNewEmployeeAddressResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
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
@XmlType(name = "addNewEmployeeAddressResponse", propOrder = {
    "_return"
})
public class AddNewEmployeeAddressResponse {

    @XmlElement(name = "return", namespace = "")
    protected AddNewEmployeeAddressResponse.Return _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link AddNewEmployeeAddressResponse.Return }
     *     
     */
    public AddNewEmployeeAddressResponse.Return getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddNewEmployeeAddressResponse.Return }
     *     
     */
    public void setReturn(AddNewEmployeeAddressResponse.Return value) {
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
        "serviceStatus"
    })
    public static class Return {

        @XmlElement(required = true)
        protected ServiceStatus serviceStatus;

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
