/**
 * Created by ultra on 14.07.2017.
 */

/**
 * Task authors
 *  @author Elena Protsenko
 *  @author Alexey Evdokimov
 */
public interface ControlFlowStatements3{
    /**
     * For given x find the value of following function:
     *        | -x, если x<=0,
     *  f(x)= | x^2, если 0<x<2,
     *        | 4, если x>=2.
     * @return f.
     */
    public double getFunctionValue(double x);

    /**
     * monthNumber — given number in range 1–12 (1 — January, 2 — February and etc.).
     * Return the corresponding year time ("Winter", "Spring", "Summer", "Autumn")
     * or "Error" string in case when monthNumber doesn't lie in range 1-12
     * @param monthNumber
     * @return string representation of monthNumber
     */
    public String decodeSeason(int monthNumber);

    /**
     * Create a matrix 8x5 of integer values,
     * where every element is the following: array[i][j] = |i-j|^5.
     * @return array.
     */
    long[][] initArray();

    /**
     * Define index of line in two-dimensional array with biggest value of multiplied elements
     * @param array
     * @return index of array
     */
    int getMaxProductIndex(long[][] array);

    /**
     * A and B (A>=B) are given. On the line with length A there're located many sectors with B length
     * Sectors with B length do not interfere with each other.
     * Without operators '*' and '/' find out the rest free length of A sector
     * @param A Length of bigger sector
     * @param B Length of sectors that are located on A sector
     * @return Length of free sector on A
     */
    public float calculateLineSegment(float A, float B);
}

