/**
 * Tasks authors
 *  @author Elena Protsenko
 *  @author Alexey Evdokimov
 */
public interface ControlFlowStatements1{
    /**
     * For this double value x find the value of following function f
     *
     *        | 2*sin(x), если x>0,
     *  f(x)= |
     *        | 6-x, если x<=0.
     *
     * @return f.
     */
    float getFunctionValue(float x);
    /**
     * The number is given within range of 1–7.
     * Return string that corresponds to the following number:<br/>
     * 1 — Monday, 2 — Tuesday, 3 - Wednesday, 4 - Thursday, 5 - Friday, 6 - Saturday, 7 - Sunday.
     * @param weekday
     * @return string representation of weekday
     */
    String decodeWeekday(int weekday);

    /**
     * Create a matrix 8x5 of integer values,
     * where every element is the following: array[i][j] = i*j.
     * @return array.
     */
    int[][] initArray();
    /**
     * Find a minimum value in matrix of random size
     * @param array array which contains at least one element
     * @return min value of array.
     */
    int getMinValue(int[][] array);

    /**
     * Initial bank deposit is $1000.
     * At the end of every year deposit grows by P percents
     * According to given in advance P estimate, how many years we will need to exceed 5000$ and find result deposit
     * @param P deposits percent
     * @return information about deposit (return as a class value {@link ControlFlowStatements1.BankDeposit})
     */
    BankDeposit calculateBankDeposit(double P);
    /**
     * Auxiliary type for method {@link ControlFlowStatements1#calculateBankDeposit(double)}.<br/>
     */
    public static class BankDeposit{
        /**
         * How many years did the deposit stay in the bank
         */
        public int years = 0;
        /**
         * Deposit size {@link #years}.
         */
        public double amount;

        @Override
        public String toString() {
            return years+" years: $"+amount;
        }
    }
}