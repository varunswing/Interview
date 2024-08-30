import java.util.Stack;

public class Stacks {
    public static void main(String[] args) {
        Stack s = new Stack();
        s.push(22);
        s.push(33);
        s.push(44);
        s.push(55);
        s.push(66);

        s.forEach(n ->  { System.out.println(n); });  
        System.out.println("Is the stack empty or not? " + s.empty());//false
        System.out.println("Location of Dell: " + s.search(22));//5
        System.out.println("Location of Dell: " + s.search(66));// 1
        System.out.println("The stack size is: " + s.size());// 5
        System.out.println("The element at the top of the" + " stack is: " + s.peek());//66
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        System.out.println("Is the stack empty or not? " + s.empty());//true
    }
}