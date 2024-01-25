package main.java.model;

import main.java.model.enums.Gender;

import java.time.LocalDate;
import java.util.Objects;

public record Account(
        String firstName, String lastName, String country,
        LocalDate birthday, Gender gender, Double balance) {
//    private String firstName;
//    private String lastName;
//    private String country;
//    private LocalDate birthday;
//    private Gender gender;
//    private Double balance;


//    @Override
//    public String toString() {
//        return "model.Account{" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", country='" + country + '\'' +
//                ", birthday=" + birthday +
//                ", gender='" + gender + '\'' +
//                ", balance=" + balance +
//                '}';
//    }
//
//    public Account(String firstName, String lastName, String country, LocalDate birthday, Gender gender, Double balance) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.country = country;
//        this.birthday = birthday;
//        this.gender = gender;
//        this.balance = balance;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Account account = (Account) o;
//        return Objects.equals(firstName, account.firstName) && Objects.equals(lastName, account.lastName) && Objects.equals(country, account.country) && Objects.equals(birthday, account.birthday) && Objects.equals(gender, account.gender) && Objects.equals(balance, account.balance);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(firstName, lastName, country, birthday, gender, balance);
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public LocalDate getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(LocalDate birthday) {
//        this.birthday = birthday;
//    }
//
//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }
//
//    public Double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(Double balance) {
//        this.balance = balance;
//    }
//
//    @Override
//    public int compareTo(Account o) {
//        if (this.getFirstName().compareTo(o.getFirstName()) == 0) {
//            return (int) (this.getBalance() - o.getBalance());
//        } else if(this.getFirstName().compareTo(o.getFirstName()) >= 1) {
//            return 1;
//        } else {
//            return -1;
//        }
//    }
}
