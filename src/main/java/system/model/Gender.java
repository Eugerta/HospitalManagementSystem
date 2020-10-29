package system.model;

public enum Gender {

	FEMALE("Female"), MALE("Male");
	
   private final String displayValue;
    
    private Gender(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
