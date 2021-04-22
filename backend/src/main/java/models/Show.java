package models;

public class Show {

    private int id;
    private String title;
    private int genre;
    private double length;
    private String type;
    private int procoId;
    private int year;
    private long addedOn;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProcoId() {
        return procoId;
    }

    public void setProcoId(int procoId) {
        this.procoId = procoId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(long year) {
        this.addedOn = year;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
