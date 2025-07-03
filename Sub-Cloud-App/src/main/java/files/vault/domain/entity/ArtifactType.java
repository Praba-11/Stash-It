package files.vault.domain.entity;

public enum ArtifactType {
    DOCUMENT("Document"),
    CERTIFICATE("Certificate"),
    LICENSE("License"),
    CONTRACT("Contract"),
    REPORT("Report"),
    POLICY("Policy"),
    PROCEDURE("Procedure"),
    FORM("Form"),
    TEMPLATE("Template"),
    OTHER("Other");

    private final String displayName;

    ArtifactType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
