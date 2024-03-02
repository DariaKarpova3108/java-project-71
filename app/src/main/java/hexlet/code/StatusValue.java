package hexlet.code;

public class StatusValue {
    private final String status;
    private final Object oldValue;
    private final Object newValue;

    public StatusValue(String status, Object oldValue, Object newValue) {
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
