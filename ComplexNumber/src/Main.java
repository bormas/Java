/**
 * Created by ultra on 26.07.2017.
 */
public class Main {
    public static void main(String[] args)
    {
        ComplexNumberImpl test = new ComplexNumberImpl();
        ComplexNumberImpl test2 = new ComplexNumberImpl();
        ComplexNumberImpl test3 = new ComplexNumberImpl();
        //test.set(-12.4, 5.4);
        //test2.set(10, -5.4);
        //test3.set(13, 6);
        //System.out.println(test.toString() + " " + test2.toString());

        test.set("4");
        test2.set("-4");
        test3.set("-7.3-12.344i");

        System.out.println(test.toString() + "\n" + test2.toString() + "\n" + test3.toString());

        //System.out.println(test.compareTo(test2));
        //System.out.println(test.compareTo(test3));

    }
}
