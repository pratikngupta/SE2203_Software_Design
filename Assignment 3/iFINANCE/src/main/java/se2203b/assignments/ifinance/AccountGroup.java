package se2203b.assignments.ifinance;

import javafx.beans.property.*;

public class AccountGroup {
    //this is model class for AccountGroupAdapter class

    //stmt.execute("CREATE TABLE AccountGroups (groupID int, groupName varchar(255), groupElement int, groupParent int)");

    private IntegerProperty  groupID;
    private StringProperty groupName;
    private IntegerProperty groupElement;
    private IntegerProperty groupParent;

    public AccountGroup(int groupID, String groupName, int groupElement, int groupParent) {
        this.groupID = new SimpleIntegerProperty(groupID);
        this.groupName = new SimpleStringProperty(groupName);
        this.groupElement = new SimpleIntegerProperty(groupElement);
        this.groupParent = new SimpleIntegerProperty(groupParent);
    }

    public int getGroupID() {
        return groupID.get();
    }

    public IntegerProperty groupIDProperty() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID.set(groupID);
    }

    public String getGroupName() {
        return groupName.get();
    }

    public StringProperty groupNameProperty() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
    }

    public int getGroupElement() {
        return groupElement.get();
    }

    public IntegerProperty groupElementProperty() {
        return groupElement;
    }

    public void setGroupElement(int groupElement) {
        this.groupElement.set(groupElement);
    }

    public int getGroupParent() {
        return groupParent.get();
    }

    public IntegerProperty groupParentProperty() {
        return groupParent;
    }

    public void setGroupParent(int groupParent) {
        this.groupParent.set(groupParent);
    }
}
