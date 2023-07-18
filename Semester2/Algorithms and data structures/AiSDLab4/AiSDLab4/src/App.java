
import java.util.Scanner;

public class App {

    public static Brackets bracketsThing = new Brackets();

    public static String expressionInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Wprowadż wyrażenie: ");
        String expression = input.nextLine();
        input.close();

        return expression;
    }

    public static String isBalanced(){
        if(bracketsThing.isBalancedExpression(expressionInput()))
            return "zbalansowane";
        return "niezbalansowane";
    }

    public static String isPalindrome(){
        Scanner input = new Scanner(System.in);
        System.out.println("Wprowadż wyrażenie: ");
        String expression = input.nextLine();
        input.close();

        ArrayStack<Character> firstStack = new ArrayStack<>();
        ArrayStack<Character> secondStack = new ArrayStack<>();
        
        char[] splittedExpression = new char[expression.length()];

        int index = 0;
        for(int i = 0; i<expression.length();i++){
                splittedExpression[index] = expression.charAt(i);
                index++;
        }

    

        if(splittedExpression.length%2 == 0){
            for(int i = 0;i<splittedExpression.length/2;i++)
                firstStack.push(splittedExpression[i]);
            
            for(int i = splittedExpression.length/2;i<splittedExpression.length;i++)
                secondStack.push(splittedExpression[i]);

            secondStack.reverse();

            while(!firstStack.isEmpty()){
                if(firstStack.pop()!=secondStack.pop())
                    return "Wyraz nie jest palindromem";
            }
        }

        if(splittedExpression.length%2 != 0){
            for(int i = 0;i<splittedExpression.length/2;i++)
                firstStack.push(splittedExpression[i]);
            for(int i = (splittedExpression.length/2)+1;i<splittedExpression.length;i++)
                secondStack.push(splittedExpression[i]);

                secondStack.reverse();

            while(!firstStack.isEmpty()){
                if(firstStack.pop()!=secondStack.pop())
                    return "Wyraz nie jest palindromem";
            }
            return "Wyraz jest palindromem";
        }

        return "Wyraz jest palindromem";

    }

    public static ArrayStack<Integer> generateStack(){

        ArrayStack<Integer> generatedStack = new ArrayStack<>(10);

        for(int i = 0; i<10;i++)
            generatedStack.push(i);

        return generatedStack;
    }

    public static ArrayStack<Integer> elementsTransitionWithoutUsingAnyStack(ArrayStack<Integer> stack){

        stack.reverse();

        ArrayStack<Integer> temporaryStack = new ArrayStack<>(stack.size());

        while(!stack.isEmpty()){
            temporaryStack.push(stack.pop());
        }
        return temporaryStack;

    }

    public static ArrayStack<Integer> elementsTransitionUsingStack(ArrayStack<Integer> stack){
        ArrayStack<Integer> temporaryStack = new ArrayStack<>();

        while(!stack.isEmpty())
            temporaryStack.push(stack.pop());

        while(!temporaryStack.isEmpty())
            stack.push(temporaryStack.pop());

        return stack;
    }


    public static void main(String[] args) throws Exception {

       //System.out.println("Podane wyrażenie jest " + isBalanced());
 

        //System.out.println(isPalindrome());

         

        ArrayStack<Integer> firstStack = generateStack();

        System.out.println("Liczby na stosie przed przerzuceniem: na drugi stos: ");
        while(!firstStack.isEmpty()){
            System.out.println(firstStack.pop());
        }
        
        firstStack = generateStack();


        ArrayStack<Integer> secondStack = elementsTransitionWithoutUsingAnyStack(firstStack);

        System.out.println("Liczby na stosie po przerzuceniu na drugi stos: ");
        while(!secondStack.isEmpty()){
            System.out.println(secondStack.pop());
        }


        firstStack = generateStack();
        
        secondStack = elementsTransitionUsingStack(firstStack);


        System.out.println("Liczby po wrzuceniu na stos drugą metodą użwywając dodatkowego stosu:");
        while(!secondStack.isEmpty())
            System.out.println(secondStack.pop());

       System.out.println(""); 

       

    
        


    }
}