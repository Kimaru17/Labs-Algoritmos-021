package util;

import domain.*;

import java.text.DecimalFormat;
import java.util.Random;

public class Utility {
    private static final Random random;
        private static SinglyLinkedList studentList;
        private static DoublyLinkedList courseList;
        private static DoublyLinkedList registerList;

        //constructor estatico, inicializador estatico
        static {
            // semilla para el random
            long seed = System.currentTimeMillis();
            random = new Random(seed);
            studentList = new SinglyLinkedList();
            courseList = new DoublyLinkedList();
            registerList = new DoublyLinkedList();
        }
        //student singly list getter and setter
        public static SinglyLinkedList getStudentList() {
            return studentList;
        }
        public static void setStudentList(SinglyLinkedList studentList) {
            Utility.studentList = studentList;
        }
        //course doubly list getter and setter
        public static DoublyLinkedList getCourseList() {return courseList;}
        public static void setCourseList(DoublyLinkedList courseList) {Utility.courseList = courseList;}
        //register doubly list getter and setter
        public static DoublyLinkedList getRegisterList() {return registerList;}
        public static void setRegisterList(DoublyLinkedList registerList) {Utility.registerList = registerList;}

    public static int random(int bound){
        //return (int)Math.floor(Math.random()*bound); //forma 1
        return 1+random.nextInt(bound);
    }

    public static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void fill(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = random(99);
        }
    }

    public static String format(long n) {
        return new DecimalFormat("###,###,###.##").format(n);
    }

    public static int min(int x, int y) {
        return x<y ? x : y;
    }

    public static int max(int x, int y) {
        return x>y ? x : y;
    }

    public static String show(int[] a) {
        String result="";
        for(int item : a){
            if(item == 0) break; //si es cero es xq no hay mas elementos
            result+=item + " ";
        }
        return result;
    }

    public static int compare(Object a, Object b) {
        switch(instanceOf(a, b)){
            case "Integer":
                Integer int1 = (Integer)a; Integer int2 = (Integer)b;
                return int1 < int2 ? -1 : int1 > int2 ? 1 : 0;

            case "String":
                String str1 = (String)a; String str2 = (String)b;
                return str1.compareTo(str2) < 0 ? -1 : str1.compareTo(str2) > 0 ? 1 : 0;

            case "Character":
                Character ch1 = (Character) a; Character ch2 = (Character) b;
                return ch1.compareTo(ch2) < 0 ? -1 : ch1.compareTo(ch2) > 0 ? 1 : 0;

            case "Student":
                Student st1 = (Student) a; Student st2 = (Student) b;
                return st1.getId().compareTo(st2.getId()) < 0 ? -1
                        :  st1.getId().compareTo(st2.getId()) > 0 ? 1 : 0;

            case "Course":
                Course c1 = (Course) a; Course c2 = (Course) b;
                return c1.getId().compareTo(c2.getId()) < 0 ? -1
                        :  c1.getId().compareTo(c2.getId()) > 0 ? 1 : 0;

            case "Register":
                Register reg1 = (Register) a; Register reg2 = (Register) b;
                return reg1.getId() < reg2.getId() ? -1 : reg1.getId() > reg2.getId() ? 1 : 0;
        }
        return 2; //Unknown
    }

    public static String instanceOf(Object a, Object b){
        if(a instanceof Integer && b instanceof Integer) return "Integer";
        if(a instanceof String && b instanceof String) return "String";
        if(a instanceof Character && b instanceof Character) return "Character";
        if(a instanceof Student && b instanceof Student) return "Student";
        if(a instanceof Course && b instanceof Course) return "Course";
        if(a instanceof Register && b instanceof Register) return "Register";
        return "Unknown";
    }
}
