/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab008;

import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class StackArrayDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
 Scanner input = new Scanner(System.in);
		
		// Other variables
		int choice; // user choice
		int value;  // value to insert, delete, or search for
		String exp;
            //    String regex = "(?<=[-+*/()])|(?=[-+*/()]) ";
		// Make a new Linked List called myList
               StackArray myStack = new StackArray(5);
		
		// Do/while loop showing menu, getting user choice, and performing actions
		do {
			// Show menu and get user choice
			showMenu();
			choice = input.nextInt();
			
			// PUSH new value into stack
			if (choice == 1) {
				if (!myStack.isFull()) {
					System.out.print(">    What value do you want to push: ");
					value = input.nextInt();

					// Invoke push method with "value" as the parameter
					myStack.push(value);
					System.out.println(">    " + value + " was successfully pushed into the stack.");
					System.out.println();
				}
				else {
					System.out.println(">    ERROR: cannot push (stack is full).");
					System.out.println();
				}
			}
			
			// POP value from stack
			else if (choice == 2) {				
				// First, check to see if stack is empty.
				// IF it is, then we clearly cannot POP
				if (myStack.isEmpty()) {
					System.out.println(">    Error: cannot pop stack (stack is empty).");
				}
				// ELSE, POP the stack. The pop() method returns an int. (the value at top of stack)
				// You can do whatever you want with this reference. Here, we simply print the Data value,
				// indicating that it has been popped from the stack.
				else {
					
					System.out.println(">    " + myStack.pop()+ " has been popped from the stack.");
				}
				System.out.println();
			}
			
			// PEEK (look at) the top value of the stack...but do not actually pop it off
			else if (choice == 3) {
				// First, check to see if stack is empty.
				// IF it is, then we clearly cannot PEEK
				if (myStack.isEmpty()) {
					System.out.println(">    Error: cannot peek at stack (stack is empty).");
				}
				// ELSE, we invoke the peek() method, which returns an int value, representing
				// the value at the top of the stack. If you prefer, you could have the peek() method
				// return a reference to the actual top node. (if this was a stack of objects). 
				// This gives you more flexibility to print a variety of data members, modify the node, etc.
				else {
					System.out.println(">    " + myStack.peek() + " is the value at the top of the stack.");
				}
				System.out.println();
			}
			
			// Search for an item in the stack
			else if (choice == 4) {
				System.out.print(">    What value do you want to search for: ");
				value = input.nextInt();
				if (myStack.search(value))
					System.out.println(">    " + value + " was found in the stack.");
				else
					System.out.println(">    " + value + " was not found in the stack.");
				System.out.println();
			}
			
			// Print all values in stack
			else if (choice == 5) {
				if (myStack.isEmpty()) {
					System.out.println(">    Error: cannot print nodes (the stack is empty)");
					System.out.println();
				}
				else {
					System.out.println(">    Printing All Nodes:");
					System.out.print(">    ");
					myStack.PrintStack();
					System.out.println();
					System.out.println();
				}
			}
                        else if (choice == 6) {
				{
                                    input.nextLine();    // line
                                        System.out.println("Enter the postfix expession you wish to evaluate (type on one line and use spaces between all term):");
                                        exp = input.nextLine();
					System.out.println(">    evaluate expression");
                                        int postfix = postFix(exp);
	                                System.out.println("you entered : "+ exp);
				        System.out.println("this evaluate to: "+ postfix);
				}
			}
                        else if (choice == 7) {
				 input.nextLine();
                                 System.out.println("Enter the infix expession you wish to evaluate (type on one line and use spaces between all term): ");
                                 System.out.println(" \tExample: 7 * 16 + 5 + 16 * 3 + 16 * 2");
                                 System.out.print(" \tPlease enter the infix expession: ");
                                 String infix = input.nextLine();
                                 String convertPost = Infix2Postfix(infix);
                                 System.out.println(">\tYou entered the Infix expession: " + infix);
                                 System.out.println(">\tWhich converts to the following postfix expession: postfix expession" + convertPost);
                                 System.out.println();
			}

			// Quit
			else if (choice == 8) {
				System.out.println(">    Goodbye!");
				System.out.println();
			}
			
			// Wrong choice
			else {
				System.out.println(">    Wrong selection. Try again.");
				System.out.println();
			}
			
		} while (choice != 8);
	}
 
 //----------------------------------------------------------------------------------------------------------------
	public static void showMenu() {
		System.out.println("|-----------------------------------------------|");
		System.out.println("|---------     Stack - Array (Menu)    ---------|");
		System.out.println("|-----------------------------------------------|");
		System.out.println("|   1. Push an item into the stack              |");
		System.out.println("|   2. Pop (and print) an item from the stack   |");
		System.out.println("|   3. Peek (look at) the top item in the stack |");
		System.out.println("|   4. Search for an item in the stack          |");
		System.out.println("|   5. Print all nodes in the stack             |");
                System.out.println("|   6. Evaluate Postfix expression              |");
                System.out.println("|   7. convert infix to postfix ");
		System.out.println("|   8. Quit                                     |");
		System.out.println("|-----------------------------------------------|");
		System.out.println();
		System.out.print("> Please enter your choice: ");
	} 
        
        public static int postFix(String exp){
            StackArray postfx = new StackArray(15);
            
            
            for (String token : exp.split(" ")) {
                if (!(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"))) { // is not a digit 
                    if (!postfx.isFull()) {
                    postfx.push(Integer.valueOf(token)); //convert to int 
                    } else {
                    System.out.println("Stack is full!");
                }
                }else{
                    
                int val1 = postfx.pop();
                int val2 = postfx.pop();
                
                   switch(token){ //calculation
                       case "+": postfx.push(val1+val2);break;
                       case "*": postfx.push(val1*val2);break;
                       case "-": postfx.push(val1-val2);break;
                       case "/": postfx.push(val1/val2);break;
                }
        
            }
        }
            return postfx.peek(); //return the value on top
   
            }
        public static String Infix2Postfix(String exp) {
        StackArrayString infix = new StackArrayString(20); // to store values 
        String postfix = " ";
        for (String token : exp.split(" ")) {
            if (!infix.isFull()) {
                if (token.equals("(")) {
                    infix.push(token);
                } else if (token.equals(")")) {
                    while (infix.peek() != "(") {
                        postfix += infix.pop() + " ";
                    }
                    infix.pop();
                } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                    if (infix.isEmpty()) {
                        infix.push(token);
                    } else {
                        while ((!infix.isEmpty()) && getPrecedence(token) <= getPrecedence(infix.peek())) {
                            postfix += infix.pop() + " ";
                        }
                        infix.push(token);
                    }
                } else {
                    postfix += token + " ";
                }
            } else {
                System.out.println("Stack is full!");
            }
        }
        while (!infix.isEmpty()) {
            postfix += infix.pop() + " ";
        }
        return postfix; //the postfix 
    }
        
// to order the precedence of the operators
    public static int getPrecedence(String op) {
        if (op.equals("+") || op.equals("-")) {
            return 1;
        }
        if (op.equals("*") || op.equals("/")) {
            return 2;
        }
        if (op.equals("^")) {
            return 3;
        } else {
            return 0;
        }
}
}