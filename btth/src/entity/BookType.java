package entity;

public class BookType {
    private int typeId;
    private String typeName;
    private String typeDescription;
    private boolean typeStatus;


    public BookType() {
    }

    public BookType(int typeId, String typeName, String typeDescription, boolean typeStatus) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDescription = typeDescription;
        this.typeStatus = typeStatus;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public boolean isTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(boolean typeStatus) {
        this.typeStatus = typeStatus;
    }

    @Override
    public String toString() {
        return String.format("Mã loại sách: %d - Tên loại sách: %s - Mô tả: %s - Trạng thái:%s ",
                this.typeId, this.typeName, this.typeDescription, this.typeStatus);
    }

}
