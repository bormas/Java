/**
 * Created by ultra on 26.07.2017.
 */
public class EmployeeImpl implements Employee {
    private int salary;
    String firstName, lastName;
    Employee manager;

    public EmployeeImpl() {salary = 1000; manager = null; firstName = ""; lastName = "";}
    /**
     * @return Salary of an employee at this moment
     */
    public int getSalary()
    {
        return salary;
    }

    /**
     * Increasing salary
     * @param value increasing parameter
     */
    public void increaseSalary(int value)
    {
        salary += value;
    }

    /**
     * @return Name of an employee
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * Set an employers name
     * @param firstName new name
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return last name of an employee
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the last name
     * @param lastName Новая фамилия
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return Name + " " + last name
     */
    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    /**
     * Устанавливает Менеджера сотрудника.
     * @param manager Сотрудник, являющийся менеджером данного сотрудника.
     * НЕ следует предполагать, что менеджер является экземпляром класса EmployeeImpl.
     */
    public void setManager(Employee manager)
    {
        this.manager = manager;
    }

    /**
     * @return Имя и фамилия Менеджера, разделенные символом " " (пробел).
     * Если Менеджер не задан, возвращает строку "No manager".
     */
    public String getManagerName()
    {
        if (manager == null)
            return "No manager";
        else
            return manager.getFullName();
    }

    /**
     * Возвращает Менеджера верхнего уровня, т.е. вершину иерархии сотрудников,
     *   в которую входит данный сотрудник.
     * Если над данным сотрудником нет ни одного менеджера, возвращает данного сотрудника.
     * Замечание: поскольку менеджер, установленный методом {@link #setManager(Employee)},
     *   может быть экзепляром другого класса, при поиске топ-менеджера нельзя обращаться
     *   к полям класса EmployeeImpl. Более того, поскольку в интерфейсе Employee не объявлено
     *   метода getManager(), поиск топ-менеджера невозможно организовать в виде цикла.
     *   Вместо этого нужно использовать рекурсию (и это "более объектно-ориентированно").
     */
    public Employee getTopManager()
    {
        if (manager != null)
            return manager.getTopManager();
        else
            return this;
    }
}