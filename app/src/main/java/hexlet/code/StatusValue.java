package hexlet.code;

/**
 * Checks for changes in the value by key and, if there is any change, records them as a new StatusValue object
 * with three properties (change status, old value, new value)
 * @return creates and returns a new object based on value changes with three properties
 * (change status, old value, new value)
 */

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
