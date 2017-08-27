/**
 * Created by ultra on 14.07.2017.
 */
public class ControlFlowStatements3Impl implements ControlFlowStatements3 {
    /**
     * For given x find the value of following function:
     *        | -x, если x<=0,
     *  f(x)= | x^2, если 0<x<2,
     *        | 4, если x>=2.
     * @return f.
     */
    public double getFunctionValue(double x)
    {
        if (x <= 0)
            return -x;
        else if (x >= 2)
            return 4;
        else
            return x*x;
    }

    /**
     * monthNumber — given number in range 1–12 (1 — January, 2 — February and etc.).
     * Return the corresponding year time ("Winter", "Spring", "Summer", "Autumn")
     * or "Error" string in case when monthNumber doesn't lie in range 1-12
     * @param monthNumber
     * @return string representation of monthNumber
     */
    public String decodeSeason(int monthNumber)
    {
        switch (monthNumber) {
            case 12:
            case 1:
            case 2: return "Winter";
            case 3:
            case 4:
            case 5: return "Spring";
            case 6:
            case 7:
            case 8: return "Summer";
            case 9:
            case 10:
            case 11: return "Autumn";

            default: return "Error";
        }
    }
    /**
     * Create a matrix 8x5 of integer values,
     * where every element is the following: array[i][j] = |i-j|^5.
     * @return array.
     */
    public long[][] initArray()
    {
        long[][] array = new long[8][5];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 5; j++)
                array[i][j] = (long) Math.pow(Math.abs(i - j), 5);
        return array;
    }
    /**
     * Define index of line in two-dimensional array with biggest value of multiplied elements
     * @param array
     * @return index of array
     */
    public int getMaxProductIndex(long[][] array)
    {
        int maxProductIndex = 0, id = 0;
        long maxLine = 0, line;
        for (long[] i : array) {
            line = 1;
            for (long j : i) {
                line *= j;
            }
            if (line > maxLine) {
                maxLine = line;
                maxProductIndex = id;
            }
            id++;
        }
        return  maxProductIndex;
    }

    /**
     * A and B (A>=B) are given. On the line with length A there're located many sectors with B length
     * Sectors with B length do not interfere with each other.
     * Without operators '*' and '/' find out the rest free length of A sector
     * @param A Length of bigger sector
     * @param B Length of sectors that are located on A sector
     * @return Length of free sector on A
     */

    public float calculateLineSegment(float A, float B)
    {
        while (A >= B)
            A = Math.abs(A-B);
        return A;
    }
}
