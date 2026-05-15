package CMSC230;

public class MyStack<Type> {

    private int stackPointer;
    private Object[] stackArray;

    @SuppressWarnings("unchecked")
    public Type pop(){
        Type object = null;
        if (stackPointer > 0){
            object = (Type) stackArray[stackPointer-1];
            stackPointer--; 
            stackArray[stackPointer] = null;
        } 
        return object;
    }

    public void push(Type typeObject){
        if (typeObject == null){
            throw new IllegalArgumentException(
                "typeObject cant be null"
            );
        }
        if(stackPointer != stackArray.length){
            stackArray[stackPointer] = typeObject;
            stackPointer++;
        }
    }

    public void empty(){
        Type object = pop();
        while(object != null){
            object = pop();
        }
    }


    public MyStack(int stackSize){
        if (stackSize < 0){
            throw new IllegalArgumentException(
                "stackSize must be positive"
            );
        }
        stackArray = new Object[stackSize];
    }

}
