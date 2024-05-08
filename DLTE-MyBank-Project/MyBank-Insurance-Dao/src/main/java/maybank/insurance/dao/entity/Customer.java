package maybank.insurance.dao.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class Customer implements UserDetails  {
    @NotNull(message = "{user.customerId.null}")
    @Digits(integer = 8, fraction = 0, message = "{user.customerId.null}")
    private Integer customerId;
    @NotNull(message = "{user.customerName.null}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{user.customerName.invalid}")
    private String customerName;
    @NotNull(message = "{user.customerAddress.null}")
    private String customerAddress;
    @NotNull(message = "{user.customerStatus.null}")
    @Pattern(regexp = "^(open|closed)$", message = "{user.customerStatus.invalid}")
    private String customerStatus;
    @NotNull(message = "{user.customerContact.null}")
    @Pattern(regexp = "\\d{10}", message = "{user.customerContact.invalid}")
    private Long customerContact;
    @NotNull(message = "{user.username.null}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "{user.username.invalid}")
    private String username;
    @NotNull(message = "{user.password.null}")
    @Size(min = 8, message = "{user.password.invalid}")
    private String password;

    public Customer(@NotNull(message = "{user.customerName.null}") @Pattern(regexp = "^[a-zA-Z ]+$", message = "{user.customerName.invalid}") String customerName, @NotNull(message = "{user.customerAddress.null}") String customerAddress, @NotNull(message = "{user.customerStatus.null}") @Pattern(regexp = "^(open|closed)$", message = "{user.customerStatus.invalid}") String customerStatus, @NotNull(message = "{user.customerContact.null}") @Pattern(regexp = "\\d{10}", message = "{user.customerContact.invalid}") Long customerContact, @NotNull(message = "{user.username.null}") @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "{user.username.invalid}") String username, @NotNull(message = "{user.password.null}") @Size(min = 8, message = "{user.password.invalid}") String password) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
        this.customerContact = customerContact;
        this.username = username;
        this.password = password;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerContact(Long customerContact) {
        this.customerContact = customerContact;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getMaxAttempt() {
        return maxAttempt;
    }


    private int attempts;

    public int getAttempts() {
        return attempts;
    }
    private int maxAttempt=3;

    public Customer() {
    }

    public Customer(Integer customerId, String customerName, String customerAddress, String customerStatus, Long customerContact, String username, String password,int attempts) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
        this.customerContact = customerContact;
        this.username = username;
        this.password = password;
        this.attempts = attempts;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Long getCustomerContact() {
        return customerContact;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                ", customerContact=" + customerContact +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

}
