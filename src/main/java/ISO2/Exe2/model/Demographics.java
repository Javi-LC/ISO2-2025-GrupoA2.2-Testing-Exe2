package ISO2.Exe2.model;

/**
 * Value object that encapsulates demographic information about a customer.
 * Simple data holder without business logic (logic is in FareService).
 */
public class Demographics {
    private final int age;
    private final boolean isStudent;
    private final boolean livesWithParents;

    public Demographics(int age, boolean isStudent, boolean livesWithParents) {
        this.age = age;
        this.isStudent = isStudent;
        this.livesWithParents = livesWithParents;
    }

    public int getAge() {
        return age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public boolean livesWithParents() {
        return livesWithParents;
    }
}
