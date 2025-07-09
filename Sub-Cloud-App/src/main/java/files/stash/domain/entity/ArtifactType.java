package files.stash.domain.entity;

/**
 * Enum representing the various types of artifacts that can be uploaded or managed in the system.
 * <p>
 * Each enum constant has a user-friendly display name used for UI representation.
 */
public enum ArtifactType {

    /** Represents a general document. */
    DOCUMENT("Document"),

    /** Represents a certificate, such as an award or qualification. */
    CERTIFICATE("Certificate"),

    /** Represents a license document. */
    LICENSE("License"),

    /** Represents a contract or agreement. */
    CONTRACT("Contract"),

    /** Represents a report such as financial or technical. */
    REPORT("Report"),

    /** Represents a policy document. */
    POLICY("Policy"),

    /** Represents a procedural document outlining steps or operations. */
    PROCEDURE("Procedure"),

    /** Represents a form used for data collection or submissions. */
    FORM("Form"),

    /** Represents a template file used as a base format. */
    TEMPLATE("Template"),

    /** Represents any other type of artifact not covered above. */
    OTHER("Other");

    private final String displayName;

    /**
     * Constructor to associate a display name with the enum constant.
     *
     * @param displayName A user-friendly name for display purposes.
     */
    ArtifactType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name associated with the artifact type.
     *
     * @return the user-friendly display name of the artifact type.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the display name when the enum is printed or logged.
     *
     * @return the display name of the artifact type.
     */
    @Override
    public String toString() {
        return displayName;
    }
}
