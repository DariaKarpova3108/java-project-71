package hexlet.code;

/**
 * Represents a status value with its old and new values.
 */

public class StatusValue {
    private final String status;
    private Object oldValue;
    private Object newValue;

    /**
     * Constructs a new StatusValue with the given status, old value, and new value.
     *
     * @param status   the status of the value
     * @param oldValue the old value
     * @param newValue the new value
     */

    public StatusValue(String status, Object oldValue, Object newValue) {
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /**
     * Returns the status of the value.
     *
     * @return the status of the value
     */

    public String getStatus() {
        return status;
    }

    /**
     * Returns the old value.
     *
     * @return the old value
     */

    public Object getOldValue() {
        return oldValue;
    }

    /**
     * Returns the new value.
     *
     * @return the new value
     */

    public Object getNewValue() {
        return newValue;
    }

    /**
     * Returns the update old value.
     *
     * @return the update old value
     */
    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * Returns the update new value.
     *
     * @return the update new value
     */

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }
}
