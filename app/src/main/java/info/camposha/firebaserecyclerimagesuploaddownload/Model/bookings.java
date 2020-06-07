package info.camposha.firebaserecyclerimagesuploaddownload.Model;

public class bookings {
    private String Fname;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String nationality;
    private String persons;
    private String person;


    public bookings() {
    }

    public bookings(String fname, String name, String email, String phone, String gender, String nationality, String persons,String person) {
        Fname = fname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.nationality = nationality;
        this.persons = persons;
        this.persons = person;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }
    public String getPerson() {
        return person;
    }
    public void setPerson(String person) {
        this.persons = person;
    }
}
