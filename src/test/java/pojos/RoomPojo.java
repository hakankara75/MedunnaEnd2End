package pojos;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomPojo {
    private int roomNumber;
    private String roomType;
    private Object status;
    private double price;
    private String description;
    private String createdBy;

    public RoomPojo() {
    }

    public RoomPojo(int roomNumber, String roomType, Object status, double price, String description, String createdBy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
        this.price = price;
        this.description = description;
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "RoomPojo{" +
                "roomNumber=" + roomNumber +
                ", roomType='" + roomType + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public Object getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
