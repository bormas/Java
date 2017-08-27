/**
 * Created by ultra on 14.07.2017.
 */
public class Checker {
    public static void main(String[] args) {
        ControlFlowStatements3Impl test = new ControlFlowStatements3Impl();

        long[][] arr = new long[][]
                {
                        { 5, 3, 1, 5},
                        { 0, 0, 5, 5},
                        { 2, 2, 2, 2},
                        { 6, 6, 4, 7},
                        { 0, 0, 3, 6}
                };

        System.out.println(test.getFunctionValue(-6));
        System.out.println(test.getFunctionValue(1));
        System.out.println(test.getFunctionValue(5));
        System.out.println();

        for (int i = 0; i < 13; i++)
            System.out.println(test.decodeSeason(i));
        System.out.println();

        long[][] arr2 = test.initArray();
        for (long[] i : arr2) {
            for (long j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();


        System.out.println(test.getMaxProductIndex(arr));
        System.out.println();

        System.out.println(test.calculateLineSegment(20, 4));
        System.out.println(test.calculateLineSegment(20, 3));
        System.out.println();
    }
}
