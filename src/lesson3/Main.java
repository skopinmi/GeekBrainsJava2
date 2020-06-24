package lesson3;

import java.util.Comparator;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        TreeSet<Employee> ts = new TreeSet(new MyComp());
        ts.add(new Employee(20));
        ts.add(new Employee(30));
        ts.add(new Employee(10));
        ts.add(new Employee(40));
        System.out.println(ts);
    }
}

class MyComp implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        if(o1.getSalary() > o2.getSalary()) {
            return 1;
        } else {
            return -1;
        }
    }
}

//class MyComp implements Comparator<Employee> {
//    @Override
//    public int compare(Employee o1, Employee o2) {
//        return o1.getSalary() - o2.getSalary();
//    }
//}





class Employee {
    private int salary;
    public Employee(int salary) {
        this.salary = salary;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return String.valueOf(salary);
    }

    @Override
    public int hashCode() {
        return salary;
    }
}
