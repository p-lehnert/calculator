package GUI;

public class Term {

    private String term;

    private int priority;

    private EquationPart type;

    public Term (final String term, final int priority, final EquationPart type) {
        this.term = term;
        this.priority = priority;
        this.type = type;
    }

    public String getTerm () {
        return term;
    }

    public void setTerm (final String newTerm) {
        term = newTerm;
    }

    public int getPriority () {
        return priority;
    }

    public void setPriority (final int newPriority) {
        priority = newPriority;
    }

    public EquationPart getType () {
        return type;
    }

    public void setType (final EquationPart newType) {
        type = newType;
    }
}
