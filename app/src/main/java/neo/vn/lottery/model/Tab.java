package neo.vn.lottery.model;

public class Tab {
    String name;
    String code;
    Boolean isChecked;

    public Tab(String name, Boolean isChecked, String code) {
        this.name = name;
        this.code = code;
        this.isChecked = isChecked;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
