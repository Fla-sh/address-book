package cloud.ptl.persistence;

/**
 * Contains String names for each field in Contact and Address class. It is used in JSON -> POJO, POJO -> JSON converting process
 */
public enum ContactFields {
    NAME("String", "Name"),
    SURNAME("String", "Surname"),
    HASH("String", "Hash"),
    STREET_NAME("String", "Street_Name"),
    HOME_NUMBER("Integer", "Home_Number"),
    FLAT_NUMBER("Integer", "Flat_Number"),
    POSTAL_CODE("String", "Postal_Code"),
    POST_OFFICE("String", "Post_Office"),
    PHONE_NUMBER("String", "Phone_Number");

//    private final String type;
    private final String fieldName;
    ContactFields(String type, String fieldName) {
//        this.type = type;
        this.fieldName = fieldName;
    }

// --Commented out by Inspection START (23.10.2019, 23:34):
//    public String getType() {
//        return type;
//    }
// --Commented out by Inspection STOP (23.10.2019, 23:34)

    public String getFieldName() {
        return fieldName;
    }
}
