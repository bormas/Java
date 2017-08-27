/**
 * Created by ultra on 13.07.2017.
 */
public class ControlFlowStatements1Impl implements ControlFlowStatements1 {
    /**
     * For this double value x find the value of following function f
     *
     *        | 2*sin(x), если x>0,
     *  f(x)= |
     *        | 6-x, если x<=0.
     *
     * @return f.
     */
    public float getFunctionValue(float x)
    {
        if (x > 0)
            return (float) (2*Math.sin(x));
        else
            return 6 - x;
    }
    /**
     * The number is given within range of 1–7.
     * Return string that corresponds to the following number:<br/>
     * 1 — Monday, 2 — Tuesday, 3 - Wednesday, 4 - Thursday, 5 - Friday, 6 - Saturday, 7 - Sunday.
     * @param weekday
     * @return string representation of weekday
     */
    public String decodeWeekday(int weekday)
    {
        switch (weekday) {
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            case 7: return "Sunday";
            default: return "Wrong number";
        }
    }

    /**
     * Create a matrix 8x5 of integer values,
     * where every element is the following: array[i][j] = i*j.
     * @return array.
     */
    public int[][] initArray()
    {
        int [][] array = new int[8][5];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 5; j++)
                array[i][j] = i*j;
        return array;
    }

    /**
     * Find a minimum value in matrix of random size
     * @param array array which contains at least one element
     * @return min value of array.
     */
    public int getMinValue(int[][] array) {
        int min = Integer.MAX_VALUE;
        for (int[] i : array) {
            for (int j : i)
                min = Math.min(min, j);
        }
        return min;
    }

    /**
     * Initial bank deposit is $1000.
     * At the end of every year deposit grows by P percents
     * According to given in advance P estimate, how many years we will need to exceed 5000$ and find result deposit
     * @param P deposits percent
     * @return information about deposit (return as a class value {@link ControlFlowStatements1.BankDeposit})
     */
    public ControlFlowStatements1.BankDeposit calculateBankDeposit(double P)
    {
        BankDeposit bd = new BankDeposit();
        bd.amount = 1000;
        while (bd.amount < 5000) {
            bd.amount += bd.amount*P/100;
            bd.years++;
        }

        return bd;
    }
}
