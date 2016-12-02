package eb.vesna.addressbook.models;

public class ContactData {
    private final String firstname;
    private final String lastName;
    private final String mobilePhone;
    private final String email;
    private final String address;
    private String group;

    public ContactData(String firstname, String lastName, String mobilePhone, String email, String address, String group) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.address = address;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGroup() {
        return group;
    }
}
