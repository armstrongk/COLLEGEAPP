package com.example.armstrong.college.FireBaseMessage;

/**
 * Created by armstrong on 7/3/2017.
 */
public class FireBase_Model {
    private String pid;
    private String name;
    private String price;
    private String createdat;
    private String edittedat;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    private  String count;

    public FireBase_Model(String pid, String edittedat, String createdat, String price, String name) {
        this.pid = pid;
        this.edittedat = edittedat;
        this.createdat = createdat;
        this.price = price;
        this.name = name;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getEdittedat() {
        return edittedat;
    }

    public void setEdittedat(String edittedat) {
        this.edittedat = edittedat;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FireBase_Model() {
    }
}
