package backend.model.enumtype;

public enum UserRole {
    ADMIN,
    USER;

    public String getAuthority() {
        return "ROLE_" + this.toString();
    }
}
