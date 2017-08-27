/**
 * Created by ultra on 14.07.2017.
 */
public class Checker {
    public static void main(String[] args)
    {
        ArrayVectorImpl test = new ArrayVectorImpl();

        double[] arr = new double[] {1.0, 3.0, 5.0, 7.0};
        test.set(arr);
        double[] arr2 = new double[] {1.0, 3.0, 5.0, 7.0, 8.0};
        test.set(arr2);
        double[] arr3 = new double[] {1.0, 3.0, 7.0};
        test.set(arr3);
        test.set(10, 5.0);
    }
}
