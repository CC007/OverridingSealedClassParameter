import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Child1 child1 = new Child1();
        Child2 child2 = new Child2();
        Uncle uncle = new Uncle();

        MyInterface myInterface = new MyClass();
        myInterface.myMethod(child1);
        myInterface.myMethod(child2);
        System.out.println();

        MyClass myClass = new MyClass();
        myClass.myMethod(child1);
        myClass.myMethod(child2);
        System.out.println();

        MyInterface myInterface2 = new MyChildClass();
        myInterface2.myMethod(child1);
        myInterface2.myMethod(child2);
        System.out.println();

        MyClass myClass2 = new MyChildClass();
        myClass2.myMethod(child1);
        myClass2.myMethod(child2);
        System.out.println();

        MyChildInterface myChildInterface = new MyChildClass();
        myChildInterface.myMethod(child1);
        myChildInterface.myMethod(child2);
        myChildInterface.myMethod(uncle);
        System.out.println();

        MyChildClass myChildClass = new MyChildClass();
        myChildClass.myMethod(child1);
        myChildClass.myMethod(child2);
        myChildClass.myMethod(uncle);
    }
}

sealed interface GrandParent {}

record Uncle() implements GrandParent {}
sealed interface Parent extends GrandParent {}

record Child1() implements Parent {}
record Child2() implements Parent {}

interface MyInterface {
    void myMethod(Parent parent);
}

class MyClass implements MyInterface {

    @Override
    public void myMethod(Parent parent) {
        System.out.println("Handle parent");
        switch (parent) {
            case Child1 child1 -> myMethod(child1);
            case Child2 child2 -> myMethod(child2);
        }
    }

    public void myMethod(Child1 child1) {
        System.out.println("Child1");
    }

    public void myMethod(Child2 child2) {
        System.out.println("Child2");
    }
}

interface MyChildInterface {
    void myMethod(GrandParent grandParent);
}

class MyChildClass extends MyClass implements MyChildInterface {

    @Override
    public void myMethod(GrandParent grandParent) {
        System.out.println("Handle grandparent");
        switch (grandParent) {
            case Uncle uncle -> myMethod(uncle);
            case Child1 child1 -> myMethod(child1);
            case Child2 child2 -> myMethod(child2);
        }
    }

    public void myMethod(Uncle uncle) {
        System.out.println("Uncle");
    }
}
