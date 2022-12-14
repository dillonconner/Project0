package dev.conner.entities;

public class Expense {

    public enum Status{PENDING,APPROVED,DENIED}
    //enum Type{idk maybe use this}

    private int id;
    private int issuerId;        //(or maybe employee id)
    private String description;
    private String type;            //change to enum or not ???
    private int amount;
    private long date;              //epoch time
    private Status status;          //change to enum :statusDONE everywhere

    public Expense(){

    }

    public Expense(int id, int issuerId, String description, String type, int amount, long date, Status status) {
        this.id = id;
        this.issuerId = issuerId;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(int issuerId) {
        this.issuerId = issuerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", issuerId='" + issuerId + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
