package cloud.ptl.persistence;

import java.io.Serializable;

/**
 * Class containing information about some address
 */
public class Address implements Serializable {
    private String streetName;
    private Integer homeNumber;
    private Integer roomNumber;
    private String postalCode;
    private String postOffice;

    public Address(String streetName, Integer homeNumber, Integer roomNumber, String postalCode, String postOffice){
        this.streetName = streetName;
        this.homeNumber = homeNumber;
        this.roomNumber = roomNumber;
        this.postalCode = postalCode;
        this.postOffice = postOffice;
    }

    @Override
    /**
     * Convertion from Address to String representation
     */
    public String toString() {
        return String.format(   "Street Name: %s\n" +
                                "Home Number: %s\n" +
                                "Room Number: %s\n" +
                                "Postal Code: %s\n" +
                                "Post Office: %s\n",
                streetName,
                homeNumber.toString(),
                roomNumber.toString(),
                postalCode,
                postOffice);
    }

    public String getStreetName() {
        return streetName;
    }

    public Integer getHomeNumber() {
        return homeNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setHomeNumber(Integer homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
