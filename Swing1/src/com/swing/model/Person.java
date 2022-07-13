package com.swing.model;

public class Person {

    public static int count = 0;

    private int id;

    private String name;

    private String occupation;

    private AgeCategory ageCategory;

    private EmploymentCategory employmentCategory;

    private String taxId;

    private boolean usCitizen;

    private Gender gender;

    public Person() {
    }

    public Person(String name, String occupation, AgeCategory ageCategory,
                  EmploymentCategory employmentCategory, String taxId,
                  boolean usCitizen, Gender gender) {
        this.id = count;
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentCategory = employmentCategory;
        this.taxId = taxId;
        this.usCitizen = usCitizen;
        this.gender = gender;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public EmploymentCategory getEmploymentCategory() {
        return employmentCategory;
    }

    public void setEmploymentCategory(EmploymentCategory employmentCategory) {
        this.employmentCategory = employmentCategory;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public boolean isUsCitizen() {
        return usCitizen;
    }

    public void setUsCitizen(boolean usCitizen) {
        this.usCitizen = usCitizen;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", occupation='").append(occupation).append('\'');
        sb.append(", ageCategory=").append(ageCategory);
        sb.append(", employmentCategory=").append(employmentCategory);
        sb.append(", taxId='").append(taxId).append('\'');
        sb.append(", usCitizen=").append(usCitizen);
        sb.append(", gender=").append(gender);
        sb.append('}');
        return sb.toString();
    }
}
