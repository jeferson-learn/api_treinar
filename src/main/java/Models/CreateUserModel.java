package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name","last_name","email","age","phone","address","state","city"})
public class CreateUserModel {

    private String name;
    private String lastName;
    private String email;
    private String age;
    private String phone;
    private String address;
    private String state;
    private String city;

    public CreateUserModel(){
        name = "Jeferson";
        lastName = "Eugenio";
        email = "jefynhu11@test.com";
        age = "27";
        phone = "987654321";
        address = "Assis Brasil";
        state = "Rio Grande do Sul";
        city = "Porto Alegre";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
