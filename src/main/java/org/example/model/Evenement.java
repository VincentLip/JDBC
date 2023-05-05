package org.example.model;

public class Evenement {

    private int id;
    private String name;
    private String hour;
    private String date;
    private Lieu lieu;
    private float price;
    private int nbTicketSold;

    public Evenement(String name, String hour, String date, Lieu lieu, float price, int nbTicketSold) {
        this.name = name;
        this.hour = hour;
        this.date = date;
        this.lieu = lieu;
        this.price = price;
        this.nbTicketSold = nbTicketSold;
    }

    public Evenement(int id,String name, String hour, String date, Lieu lieu, float price, int nbTicketSold) {
        this(name, hour, date, lieu, price, nbTicketSold);
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNbTicketSold() {
        return nbTicketSold;
    }

    public void setNbTicketSold(int nbTicketSold) {
        this.nbTicketSold = nbTicketSold;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hour='" + hour + '\'' +
                ", date='" + date + '\'' +
                ", lieu=" + lieu +
                ", price=" + price +
                ", nbTicketSold=" + nbTicketSold +
                '}';
    }
}
