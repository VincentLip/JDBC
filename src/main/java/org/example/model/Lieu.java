package org.example.model;

public class Lieu {

    private int id;
    private String name;
    private String adress;
    private int capacity;

    public Lieu(String name, String adress, int capacity) {
        this.name = name;
        this.adress = adress;
        this.capacity = capacity;
    }

    public Lieu(int id,String name, String adress, int capacity) {
        this(name, adress, capacity);
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Lieu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
