package dev.conner.entities;

public class Expense {

    private int id;
    private String issuerId;        //(or maybe employee id)
    private String description;
    private String type;            //change to enum
    private int amount;
    private long date;              //epoch time
    private String status;          //change to enum

    public Expense(int id, String issuerId, String description, String type, int amount, long date, String status) {
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

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
                ", status='" + status + '\'' +
                '}';
    }
}
