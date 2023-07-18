public class Brackets {

    ArrayStack<Character> bracketsStack;

    char[] openingBrackets = {'(', '{', '<', '['};
    char[] closingBrackets = {')', '}', '>', ']'};

    public Brackets(){}
    
    boolean isOpeningBracket(char ch){
        for (char c : openingBrackets) {
            if(ch == c) return true;
        }    
        return false;
    }

    boolean isClosingBracket(char ch){
        for (char c : closingBrackets) {
            if(ch == c) return true;
        }    
        return false;
    }

     boolean pairCompare(char c, ArrayStack<Character> bracketsStack){
        


        if(c == ')'){
            if(bracketsStack.top() == '('){
                return true;
            }
        }


        if(c == ']'){
            if(bracketsStack.top() == '['){
                return true;
            }
        }

        if(c == '}'){
            if(bracketsStack.top() == '{'){
                return true;
            }
        }

        if(c == '>'){
            if(bracketsStack.top() == '<'){
                return true;
            }
        }
       
        return false;
     }





    boolean isBalancedExpression(String expression){
        int stackSize = expression.length();

        ArrayStack<Character> bracketsStack = new ArrayStack<>(stackSize);


        char[] dividedExpression = new char[stackSize];

        for(int i = 0; i<stackSize;i++) 
            dividedExpression[i] = expression.charAt(i);


         for (char c : dividedExpression) {
            if(this.isOpeningBracket(c)){
                bracketsStack.push(c);
            }
            if(this.isClosingBracket(c)){
                if(pairCompare(c, bracketsStack)){
                    bracketsStack.pop();
                }else{
                    return false;
                }
            }
        }

        


       if(bracketsStack.size()!=0){
          return false;
        }




        return true;
    }

    
}
