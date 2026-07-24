package com.crimetrack;

public class Criminal {
    private int id;
    private String criminalNo, name, aliasName, address, gender, aadhaarNo, imagePath;

    public Criminal(int id, String criminalNo, String name, String aliasName, String address, String gender, String aadhaarNo, String imagePath) {
        this.id = id;
        this.criminalNo = criminalNo;
        this.name = name;
        this.aliasName = aliasName;
        this.address = address;
        this.gender = gender;
        this.aadhaarNo = aadhaarNo;
        this.imagePath = imagePath;
    }

    public int getId() { return id; }
    public String getCriminalNo() { return criminalNo; }
    public String getName() { return name; }
    public String getAliasName() { return aliasName; }
    public String getAddress() { return address; }
    public String getGender() { return gender; }
    public String getAadhaarNo() { return aadhaarNo; }
    public String getImagePath() { return imagePath; }
}
