/**
 * Created by ultra on 14.07.2017.
 */
public class ZeroTaskImpl implements ZeroTask {
    private int integerValue = 0;
    /**
     * Sets the value of a field of the class.
     */
    //If you define the field integerValue like commented above, then implementation of this method will be as follows:
    //	integerValue = value;
    public void setIntegerValue(int value)
    {
        integerValue = value;
    }
    /**
     * Returns the value of the field which was set by the setIntegerValue method.
     * The initial value of the field (before calling setIntegerValue method) must be 0.
     * Note: in Java, the value of int type may be casted _implicitly_ to double type.
     */
    //If you define the field integerValue like commented above, then implementation of this method will be as follows:
    //	return integerValue;
    public double getDoubleValue()
    {
        return integerValue;
    }
}
