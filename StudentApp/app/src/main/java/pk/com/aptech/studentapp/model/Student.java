package pk.com.aptech.studentapp.model;

import android.net.Uri;

import java.util.Date;

public class Student {

    public Student() {
    }

    public Student(String firstname, String lastname, String fathername, String mothername, String cnic, String dateofbirth, String placeofbirth, String contact1, String contact2, String guardiancontact1, String guardiancontact2, String address, String email, String photouri) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.fathername = fathername;
        this.mothername = mothername;
        this.cnic = cnic;
        this.dateofbirth = dateofbirth;
        this.placeofbirth = placeofbirth;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.guardiancontact1 = guardiancontact1;
        this.guardiancontact2 = guardiancontact2;
        this.address = address;
        this.email = email;
        this.photouri = photouri;
    }

    private String firstname;
    private String lastname;
    private String fathername;
    private String mothername;
    private String cnic;
    private String dateofbirth;
    private String placeofbirth;
    private String contact1;
    private String contact2;
    private String guardiancontact1;
    private String guardiancontact2;
    private String address;
    private String email;
    private String photouri;



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getPlaceofbirth() {
        return placeofbirth;
    }

    public void setPlaceofbirth(String placeofbirth) {
        this.placeofbirth = placeofbirth;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getGuardiancontact1() {
        return guardiancontact1;
    }

    public void setGuardiancontact1(String guardiancontact1) {
        this.guardiancontact1 = guardiancontact1;
    }

    public String getGuardiancontact2() {
        return guardiancontact2;
    }

    public void setGuardiancontact2(String guardiancontact2) {
        this.guardiancontact2 = guardiancontact2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotouri() {
        return photouri;
    }

    public void setPhotouri(String photouri) {
        this.photouri = photouri;
    }
}
