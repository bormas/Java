package ru.ncedu.java.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by ultra on 13.08.2017.
 */
public class BusinessCardImpl implements BusinessCard {
    private String employee;
    private String department;
    private int salary;
    private int age;
    private String gender;
    private String phone;

    private int getAge (String date) throws InputMismatchException {
        try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(sdf.parse(date));

        long diff = Calendar.getInstance().getTime().getTime() - birthDate.getTime().getTime();
        return  (int)((double)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)/365.25);
        } catch (ParseException ex) {
            System.out.println("Wrong date format");
            throw new InputMismatchException("Date format is wrong");
        }
    }

    @Override
    public BusinessCard getBusinessCard(Scanner scanner) throws NoSuchElementException, InputMismatchException {

        String[] arr = scanner.next().split(";");
        if (arr.length != 7)
            throw new NoSuchElementException("Not enough elements");
        //Employee
        String name = arr[0];
        String lastName = arr[1];
        this.employee = name + " " + lastName;

        //Department
        this.department = arr[2];

        //Age
        this.age = getAge(arr[3]);

        //Gender
        char gender;
        if (arr[4].length() == 1)
            gender = arr[4].toCharArray()[0];
        else
            throw new InputMismatchException("Gender format is wrong");

        if (gender == 'M')
            this.gender = "Male";
        else if (gender == 'F')
            this.gender = "Female";
        else
            throw new InputMismatchException("Gender should be M or F");

        //Salary
        int salary;
        try {
            salary = Integer.parseInt(arr[5]);
        } catch (NumberFormatException ex) {
            throw new InputMismatchException("Salary format is wrong");
        }

        if (salary < 100 || salary > 100000)
            throw new InputMismatchException("Salary out of bounds");
        this.salary = salary;

        //Phone
        char[] phone;
        if (arr[6].length() == 10)
            phone = arr[6].toCharArray();
        else
            throw new InputMismatchException("Phone must consist of 10 digits");

        for (int i = 0; i < phone.length; i++)
            if (phone[i] < '0' || phone[i] > '9')
                throw new InputMismatchException("Phone must not contain letters");
        this.phone = "+7 " + phone[0] + phone[1] + phone[2] + "-"
                           + phone[3] + phone[4] + phone[5] + "-"
                           + phone[6] + phone[7] + "-"
                           + phone[8] + phone[9];

        return this;
    }

    @Override
    public String getEmployee() {
        return employee;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getPhoneNumber() {
        return phone;
    }
}
