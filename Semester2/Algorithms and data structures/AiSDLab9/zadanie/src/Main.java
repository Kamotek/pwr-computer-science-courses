import java.util.Scanner;

import javax.security.auth.login.FailedLoginException;

public class Main {

    public static int priority(char c){
        if (c=='(')
            return 0;
        if (c=='+' || c=='-')
            return 1;
        if (c=='*' || c=='/')
            return 2;
        else
            return -1;
    }

    private static boolean isOperator(char c){
        if(c == '+') return true;
        if(c == '-') return true;
        if(c == '/') return true;
        if(c == '*') return true;
        if(c == '%') return true;
        return false;
    }

    private static boolean isOperator(String e){
        if(e.equals("+")) return true;
        if(e.equals("-")) return true;
        if(e.equals("*")) return true;
        if(e.equals("/")) return true;
        if(e.equals("%")) return true;
        return false;
    }

    public static String makeONP(String expression) throws FullStackException, EmptyStackException{

        ArrayStack<Character> stack = new ArrayStack<>(expression.length());
        StringBuilder subString = new StringBuilder();
        StringBuilder finalAssembly = new StringBuilder();

        for (int i=0; i<expression.length(); i++){
            char e = expression.charAt(i);
            if (e>='0' && e <= '9'){
                subString.append(e);
            }
            else{
                if (!subString.isEmpty()){
                    finalAssembly.append(subString);
                    finalAssembly.append(" ");
                    subString.delete(0, subString.length());
                }
                if (e=='(')
                    stack.push(e);
                if (e==')'){
                    while (stack.top()!='('){
                        finalAssembly.append(stack.pop());
                        finalAssembly.append(" ");
                    }
                    if (stack.top()=='(')
                        stack.pop();
                }
                if (isOperator(e)){
                    while (!stack.isEmpty() && isOperator(stack.top()) && priority(e)<=priority(stack.top())){
                        finalAssembly.append(stack.pop());
                        finalAssembly.append(" ");
                    }
                    stack.push(e);
                }
            }
        }
        if (!subString.isEmpty()){
            finalAssembly.append(subString);
            finalAssembly.append(" ");
        }
        while (!stack.isEmpty()){
            finalAssembly.append(stack.pop());
        }
        System.out.println(finalAssembly.toString());
        return finalAssembly.toString();
    }

    public static BST makeTree(String expression) throws FullStackException, EmptyStackException{
        ArrayStack<Node> stack = new ArrayStack<>(expression.length());
        String [] onpExpression = expression.split(" ");

        for (String e : onpExpression){
            if (isOperator(e)){
                Node n = new Node(e);
                n.setRight(stack.pop());
                n.setLeft(stack.pop());
                stack.push(n);
            }
            else{
                stack.push(new Node(e));
            }
        }
        System.out.println(stack.top().getValue());
        return new BST(stack.pop());
    }



    public static void main (String [] args) throws FullStackException, EmptyStackException, EmptyQueueException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj wyrażenie z nawiasami: ");
        String input = scan.nextLine();
        String onpExpression = makeONP(input);
        BST binaryTree = makeTree(onpExpression);
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println();
        binaryTree.outputInfiks();
        binaryTree.outputPostfiks();
        binaryTree.leafNumber();
        binaryTree.nodeNumber();
        binaryTree.height();

        binaryTree.przechodzenieWszerz();
    }
}