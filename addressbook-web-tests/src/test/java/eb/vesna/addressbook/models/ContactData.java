package eb.vesna.addressbook.models;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

@XStreamAlias("addressbook")
@Entity
@Table (name = "addressbook")
public class ContactData {
    //@XStreamOmitField
    @Id
    private int id;
    @Expose
    @Column (name = "firstname")
    private String firstname;
    @Expose
    @Column (name = "lastname")
    private String lastName;
    @Expose
    @Column (name = "address")
    @Type(type = "text")
    private String address;

    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Type(type = "text")
    @Column (name = "mobile")
    private String mobilePhone;
    @Expose
    @Type(type = "text")
    @Column (name = "work")
    private String workPhone;
    @Expose
    @Column (name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Transient
    private String allEmails;
    @Expose
    @Column (name = "email")
    @Type(type = "text")
    private String email;
    @Expose
    @Column (name = "email2")
    @Type(type = "text")
    private String email2;
    @Expose
    @Column (name = "email3")
    @Type(type = "text")
    private String email3;

    @Expose
    @Transient
    private String group;

    @Column (name = "photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAddress() {
        return address;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public String mergeEmails() {
        return Arrays.asList(getEmail(), getEmail2(), getEmail3())
                .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }
}
