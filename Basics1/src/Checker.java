/**
 * Created by ultra on 13.07.2017.
 */
public class Checker {
    public static void main(String[] args) {
        ControlFlowStatements1Impl test = new ControlFlowStatements1Impl();

        int[][] arr = new int[][]
                {
                        { 0, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 5, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 6, 0, 0, 0, -7, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
                };

        System.out.println(test.getFunctionValue(30));
        System.out.println(test.getFunctionValue(-3));

        for (int i = 0; i < 7; i++)
            System.out.println(test.decodeWeekday(i));

        int[][] arr2 = test.initArray();
        for (int[] i : arr2) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }


        System.out.println(test.getMinValue(arr));

        System.out.println(test.calculateBankDeposit(20).toString());
    }
}
