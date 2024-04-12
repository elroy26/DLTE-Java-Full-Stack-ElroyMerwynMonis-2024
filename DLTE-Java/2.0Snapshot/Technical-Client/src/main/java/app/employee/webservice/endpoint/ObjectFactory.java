
package app.employee.webservice.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the app.employee.webservice.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListEmployeeResponse_QNAME = new QName("http://endpoint.webservice.employee.app/", "listEmployeeResponse");
    private final static QName _ListEmployee_QNAME = new QName("http://endpoint.webservice.employee.app/", "listEmployee");
    private final static QName _AddNewEmployeeAddressResponse_QNAME = new QName("http://endpoint.webservice.employee.app/", "addNewEmployeeAddressResponse");
    private final static QName _ListFilteredEmployeeResponse_QNAME = new QName("http://endpoint.webservice.employee.app/", "listFilteredEmployeeResponse");
    private final static QName _AddNewEmployee_QNAME = new QName("http://endpoint.webservice.employee.app/", "addNewEmployee");
    private final static QName _AddNewEmployeeAddress_QNAME = new QName("http://endpoint.webservice.employee.app/", "addNewEmployeeAddress");
    private final static QName _AddNewEmployeeResponse_QNAME = new QName("http://endpoint.webservice.employee.app/", "addNewEmployeeResponse");
    private final static QName _ListFilteredEmployee_QNAME = new QName("http://endpoint.webservice.employee.app/", "listFilteredEmployee");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: app.employee.webservice.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddNewEmployeeAddressResponse }
     * 
     */
    public AddNewEmployeeAddressResponse createAddNewEmployeeAddressResponse() {
        return new AddNewEmployeeAddressResponse();
    }

    /**
     * Create an instance of {@link ListFilteredEmployeeResponse }
     * 
     */
    public ListFilteredEmployeeResponse createListFilteredEmployeeResponse() {
        return new ListFilteredEmployeeResponse();
    }

    /**
     * Create an instance of {@link AddNewEmployee }
     * 
     */
    public AddNewEmployee createAddNewEmployee() {
        return new AddNewEmployee();
    }

    /**
     * Create an instance of {@link AddNewEmployeeResponse }
     * 
     */
    public AddNewEmployeeResponse createAddNewEmployeeResponse() {
        return new AddNewEmployeeResponse();
    }

    /**
     * Create an instance of {@link AddNewEmployeeAddress }
     * 
     */
    public AddNewEmployeeAddress createAddNewEmployeeAddress() {
        return new AddNewEmployeeAddress();
    }

    /**
     * Create an instance of {@link ListFilteredEmployee }
     * 
     */
    public ListFilteredEmployee createListFilteredEmployee() {
        return new ListFilteredEmployee();
    }

    /**
     * Create an instance of {@link ListEmployeeResponse }
     * 
     */
    public ListEmployeeResponse createListEmployeeResponse() {
        return new ListEmployeeResponse();
    }

    /**
     * Create an instance of {@link ListEmployee }
     * 
     */
    public ListEmployee createListEmployee() {
        return new ListEmployee();
    }

    /**
     * Create an instance of {@link AddNewEmployeeAddressResponse.Return }
     * 
     */
    public AddNewEmployeeAddressResponse.Return createAddNewEmployeeAddressResponseReturn() {
        return new AddNewEmployeeAddressResponse.Return();
    }

    /**
     * Create an instance of {@link ListFilteredEmployeeResponse.Return }
     * 
     */
    public ListFilteredEmployeeResponse.Return createListFilteredEmployeeResponseReturn() {
        return new ListFilteredEmployeeResponse.Return();
    }

    /**
     * Create an instance of {@link AddNewEmployee.Arg0 }
     * 
     */
    public AddNewEmployee.Arg0 createAddNewEmployeeArg0() {
        return new AddNewEmployee.Arg0();
    }

    /**
     * Create an instance of {@link AddNewEmployeeResponse.Return }
     * 
     */
    public AddNewEmployeeResponse.Return createAddNewEmployeeResponseReturn() {
        return new AddNewEmployeeResponse.Return();
    }

    /**
     * Create an instance of {@link AddNewEmployeeAddress.Arg0 }
     * 
     */
    public AddNewEmployeeAddress.Arg0 createAddNewEmployeeAddressArg0() {
        return new AddNewEmployeeAddress.Arg0();
    }

    /**
     * Create an instance of {@link ListFilteredEmployee.Arg0 }
     * 
     */
    public ListFilteredEmployee.Arg0 createListFilteredEmployeeArg0() {
        return new ListFilteredEmployee.Arg0();
    }

    /**
     * Create an instance of {@link ListEmployeeResponse.Return }
     * 
     */
    public ListEmployeeResponse.Return createListEmployeeResponseReturn() {
        return new ListEmployeeResponse.Return();
    }

    /**
     * Create an instance of {@link ListEmployee.Arg0 }
     * 
     */
    public ListEmployee.Arg0 createListEmployeeArg0() {
        return new ListEmployee.Arg0();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListEmployeeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.webservice.employee.app/", name = "listEmployeeResponse")
    public JAXBElement<ListEmployeeResponse> createListEmployeeResponse(ListEmployeeResponse value) {
        return new JAXBElement<ListEmployeeResponse>(_ListEmployeeResponse_QNAME, ListEmployeeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListEmployee }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.webservice.employee.app/", name = "listEmployee")
    public JAXBElement<ListEmployee> createListEmployee(ListEmployee value) {
        return new JAXBElement<ListEmployee>(_ListEmployee_QNAME, ListEmployee.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewEmployeeAddressResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.webservice.employee.app/", name = "addNewEmployeeAddressResponse")
    public JAXBElement<AddNewEmployeeAddressResponse> createAddNewEmployeeAddressResponse(AddNewEmployeeAddressResponse value) {
        return new JAXBElement<AddNewEmployeeAddressResponse>(_AddNewEmployeeAddressResponse_QNAME, AddNewEmployeeAddressResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListFilteredEmployeeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.webservice.employee.app/", name = "listFilteredEmployeeResponse")
    public JAXBElement<ListFilteredEmployeeResponse> createListFilteredEmployeeResponse(ListFilteredEmployeeResponse value) {
        return new JAXBElement<ListFilteredEmployeeResponse>(_ListFilteredEmployeeResponse_QNAME, ListFilteredEmployeeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewEmployee }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.webservice.employee.app/", name = "addNewEmployee")
    public JAXBElement<AddNewEmployee> createAddNewEmployee(AddNewEmployee value) {
        return new JAXBElement<AddNewEmployee>(_AddNewEmployee_QNAME, AddNewEmployee.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewEmployeeAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.webservice.employee.app/", name = "addNewEmployeeAddress")
    public JAXBElement<AddNewEmployeeAddress> createAddNewEmployeeAddress(AddNewEmployeeAddress value) {
        return new JAXBElement<AddNewEmployeeAddress>(_AddNewEmployeeAddress_QNAME, AddNewEmployeeAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewEmployeeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.webservice.employee.app/", name = "addNewEmployeeResponse")
    public JAXBElement<AddNewEmployeeResponse> createAddNewEmployeeResponse(AddNewEmployeeResponse value) {
        return new JAXBElement<AddNewEmployeeResponse>(_AddNewEmployeeResponse_QNAME, AddNewEmployeeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListFilteredEmployee }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.webservice.employee.app/", name = "listFilteredEmployee")
    public JAXBElement<ListFilteredEmployee> createListFilteredEmployee(ListFilteredEmployee value) {
        return new JAXBElement<ListFilteredEmployee>(_ListFilteredEmployee_QNAME, ListFilteredEmployee.class, null, value);
    }

}
