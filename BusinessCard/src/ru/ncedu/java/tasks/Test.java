package ru.ncedu.java.tasks;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by ultra on 13.08.2017.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        BusinessCardImpl test = new BusinessCardImpl();
        Scanner scanner = new Scanner(System.in);
        test.getBusinessCard(scanner);
        System.out.println(test.getEmployee());
        System.out.println(test.getDepartment());
        System.out.println(test.getAge());
        System.out.println(test.getGender());
        System.out.println(test.getSalary());
        System.out.println(test.getPhoneNumber());

    }
}
