package org.example.model;

import java.util.List;

public class Client {

    private int id;
    private String lastname;
    private String firstname;
    private String email;
    private List<Evenement> evenementList;

    public Client(String lastname, String firstname, String email, List<Evenement> evenementList) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.evenementList = evenementList;
    }
    public Client(int id,String lastname, String firstname, String email, List<Evenement> evenementList) {
        this(lastname, firstname, email, evenementList);
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Evenement> getEvenementList() {
        return evenementList;
    }

    public void setEvenementList(List<Evenement> evenementList) {
        this.evenementList = evenementList;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", evenementList=" + evenementList +
                '}';
    }
}
