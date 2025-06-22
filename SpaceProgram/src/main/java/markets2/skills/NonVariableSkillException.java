package markets2.skills;

//
public final class NonVariableSkillException extends Exception {
    //
    public NonVariableSkillException() {
        super("Attempting to modify a non-variable skill.");
    }
}