package ru.ncedu.java.tasks;

import java.util.Iterator;

/**
 * Created by ultra on 12.08.2017.
 */
public class Test {
    public static void main (String[] args)
    {
        StringFilterImpl test = new StringFilterImpl();
        test.add("constitute");
        test.add("distribute");
        test.add("derivative");
        test.add("from");
        test.add("-123.2");
        test.add("introduced");

        /*Iterator<String> i1= test.getStringsByNumberFormat("-###.#");
        for (;i1.hasNext();)
            System.out.println(i1.next());
        System.out.println();

        Iterator<String> i2= test.getStringsByNumberFormat("#");
        for (;i2.hasNext();)
            System.out.println(i2.next());
        System.out.println();

        Iterator<String> i3= test.getStringsByNumberFormat("####(####)");
        for (;i3.hasNext();)
            System.out.println(i3.next());
        System.out.println();*/

        Iterator<String> i4= test.getStringsByPattern("t*i*e");
        for (;i4.hasNext();)
            System.out.println(i4.next());
        System.out.println();

        /*Iterator<String> i5= test.getStringsStartingWith("re");
        for (;i5.hasNext();)
            System.out.println(i5.next());
        System.out.println();

        Iterator<String> i6= test.getStringsContaining("t");
        for (;i6.hasNext();)
            System.out.println(i6.next());
        System.out.println();*/
    }
}
