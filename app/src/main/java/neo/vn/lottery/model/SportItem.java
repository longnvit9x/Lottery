package neo.vn.lottery.model;

public class SportItem {
    public SportItem(Boolean isSection) {
        this.isSection = isSection;
    }

    private Boolean isSection;

    public Boolean getSection() {
        return isSection;
    }

    public void setSection(Boolean section) {
        isSection = section;
    }
}
