package files.stash.domain.entity;

/**
 * Enum representing various departments within an organization.
 * <p>
 * Each enum constant has a user-friendly display name used for display or UI purposes.
 */
public enum Department {

    /** Department responsible for software development, infrastructure, and IT support. */
    INFORMATION_TECHNOLOGY("Information Technology"),

    /** Department responsible for promoting and advertising the organization's products or services. */
    MARKETING("Marketing"),

    /** Department responsible for sales operations and client relationships. */
    SALES("Sales"),

    /** Department responsible for recruiting, employee relations, and HR operations. */
    HUMAN_RESOURCE("Human Resource"),

    /** Department responsible for managing financial planning, records, and analysis. */
    FINANCE("Finance"),

    /** Department responsible for day-to-day business operations and logistics. */
    OPERATION("Operation");

    private final String displayName;

    /**
     * Constructor to associate a display name with the enum constant.
     *
     * @param displayName the user-friendly name of the department.
     */
    Department(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the department.
     *
     * @return the user-friendly name of the department.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Converts a display name back to the corresponding Department enum constant.
     *
     * @param displayName the display name to convert
     * @return the corresponding Department enum constant, or null if not found
     */
    public static Department fromDisplayName(String displayName) {
        if (displayName == null) {
            return null;
        }
        
        for (Department dept : Department.values()) {
            if (dept.displayName.equals(displayName)) {
                return dept;
            }
        }
        return null;
    }

    /**
     * Returns the display name when the enum constant is printed.
     *
     * @return the display name.
     */
    @Override
    public String toString() {
        return displayName;
    }
}
