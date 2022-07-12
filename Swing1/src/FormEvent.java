import java.util.EventObject;

public class FormEvent extends EventObject {

    private String name;

    private String occupation;

    private int ageCategoryId;

    private String employmentCategory;

    private String taxId;

    private boolean usCitizen;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name, String occupation, int ageCategoryId,
                     String employmentCategory, String taxId, boolean usCitizen) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategoryId = ageCategoryId;
        this.employmentCategory = employmentCategory;
        this.taxId = taxId;
        this.usCitizen = usCitizen;
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

    public int getAgeCategoryId() {
        return ageCategoryId;
    }

    public void setAgeCategoryId(int ageCategoryId) {
        this.ageCategoryId = ageCategoryId;
    }

    public String getEmploymentCategory() {
        return employmentCategory;
    }

    public void setEmploymentCategory(String employmentCategory) {
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

}
