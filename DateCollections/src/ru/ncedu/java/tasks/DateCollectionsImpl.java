package ru.ncedu.java.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by ultra on 05.08.2017.
 */
public class DateCollectionsImpl implements DateCollections {
    int dateStyle;
    private Map<String, Element> mainMap;
    List<Element> list;

    private Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String c1, String c2) {
            try {
                Calendar calendar1 = toCalendar(c1);
                Calendar calendar2 = toCalendar(c2);
                int result;
                if ((result = compareCalendar(calendar1, calendar2, Calendar.YEAR)) != 0) {
                    return result;
                }
                if ((result = compareCalendar(calendar1, calendar2, Calendar.MONTH)) != 0) {
                    return result;
                }
                return compareCalendar(calendar1, calendar2, Calendar.DATE);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }

        private int compareCalendar(Calendar calendar1, Calendar calendar2, int param) {
            return Integer.compare(calendar1.get(param), calendar2.get(param));
        }
    };

    public DateCollectionsImpl() {
        this.dateStyle = DateFormat.MEDIUM;
    }

    @Override
    public void setDateStyle(int dateStyle) {
        this.dateStyle = dateStyle;
    }

    @Override
    public Calendar toCalendar(String dateString) throws ParseException {
        DateFormat df = DateFormat.getDateInstance(dateStyle);
        Calendar c = df.getCalendar();
        c.setTime(df.parse(dateString));
        return c;
    }

    @Override
    public String toString(Calendar date) {
        DateFormat df = DateFormat.getDateInstance(dateStyle);
        return df.format(date.getTime());
    }

    @Override
    public void initMainMap(int elementsNumber, Calendar firstDate) {
        Random random = new Random();
        this.mainMap = new HashMap<String, Element>();
        for (int i = 0; i < elementsNumber; i++) {
            Calendar calendar = (Calendar) firstDate.clone();
            calendar.add(Calendar.DATE, 110 * i);
            this.mainMap.put(toString(calendar), new Element(calendar, random.nextInt(2000)));
        }
    }

    @Override
    public void setMainMap(Map<String, Element> map) {
        this.mainMap = map;
    }

    @Override
    public Map<String, Element> getMainMap() {
        return this.mainMap;
    }

    @Override
    public SortedMap<String, Element> getSortedSubMap() {
        SortedMap<String, Element> sortedMap = new TreeMap<String, Element>(comparator);
        for (String key : mainMap.keySet())
        {
            DateFormat df = DateFormat.getDateInstance(dateStyle);
            String currentTime = df.format(Calendar.getInstance().getTime());
            if (comparator.compare(key, currentTime) == 1) {
                sortedMap.put(key, mainMap.get(key));
            }
        }
        return sortedMap;
    }

    @Override
    public List<Element> getMainList() {
        list = new ArrayList<Element>();
        Iterator<Map.Entry<String, Element>> it = mainMap.entrySet().iterator();
        while (it.hasNext()) {
            list.add(it.next().getValue());
        }
        for (int i = 0; i < list.size() - 1; i++)
        {
            for (int j = 0; j < list.size() - i - 1; j++)
            {
                if(list.get(j).getBirthDate().compareTo(list.get(j + 1).getBirthDate()) == 1)
                {
                    Element tempVar = list.get(j+1);
                    list.set(j+1, list.get(j));
                    list.set(j, tempVar);
                }
            }
        }
        return list;
    }

    @Override
    public void sortList(List<Element> list) {
        for (int i = 0; i < list.size() - 1; i++)
        {
            for (int j = 0; j < list.size() - i - 1; j++)
            {
                if(list.get(j).getDeathDate().compareTo(list.get(j + 1).getDeathDate()) == 1)
                {
                    Element tempVar = list.get(j+1);
                    list.set(j+1, list.get(j));
                    list.set(j, tempVar);
                }
            }
        }
    }

    @Override
    public void removeFromList(List<Element> list) {
        for (Iterator<Element> iterator = list.iterator(); iterator.hasNext();) {
            Element element = iterator.next();
            int month = element.getBirthDate().get(Calendar.MONTH);
            if (month == Calendar.DECEMBER || month == Calendar.JANUARY || month == Calendar.FEBRUARY)
                iterator.remove();
        }
    }
}
