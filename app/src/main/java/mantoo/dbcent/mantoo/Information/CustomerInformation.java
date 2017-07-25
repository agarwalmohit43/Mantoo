package mantoo.dbcent.mantoo.Information;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class CustomerInformation {

   private String customerName;
   private String customerAddress;
   private String customerContact;
   private double customerBalance;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerBalance() {
        String amnt="";
        amnt+=customerBalance;
        return amnt;
    }

    public void setCustomerBalance(double customerBalance) {
        this.customerBalance = customerBalance;
    }
}
