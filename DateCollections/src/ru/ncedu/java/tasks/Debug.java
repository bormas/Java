package ru.ncedu.java.tasks;
import ru.ncedu.java.tasks.DateCollections.Element;
import java.util.*;
/**
 * Created by ultra on 05.08.2017.
 */
public class Debug {
    public static void main(String[] argv)
    {
        DateCollectionsImpl dateCollections = new DateCollectionsImpl();
        Map<String, Element> map = new TreeMap<String, Element>();
        dateCollections.initMainMap(30, Calendar.getInstance());

        List<Element> list = dateCollections.getMainList();
        int k = 1;
        System.out.println("getMainList");
        for (Iterator<Element> iterator = list.iterator(); iterator.hasNext();)
        {
            Element temp = iterator.next();
            System.out.println(k + ") " +temp.toString());
            k++;
        }


        dateCollections.setMainMap(dateCollections.getSortedSubMap());
        List<Element> list2 = dateCollections.getMainList();
        k = 1;
        System.out.println("getSortedSubMap");
        for (Iterator<Element> iterator = list2.iterator(); iterator.hasNext();)
        {
            System.out.println(k + ") " + iterator.next().toString());
            k++;

        }

        System.out.println(list2.size());
        dateCollections.sortList(list2);
        k = 1;
        System.out.println("sortList");
        for (Iterator<Element> iterator = list2.iterator(); iterator.hasNext();)
        {
            System.out.println(k + ") " + iterator.next().toString());
            k++;
        }
    }
}
