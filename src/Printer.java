

import java.util.List;

public class Printer {
	public static void print(SNode l){
		
			if(l.isSubtreeList){
				if(l.right == null && l.left == null){
					System.out.println(l.value);
					return;
				}
				printListNotation(l);
				System.out.println("\n");
			}else{
				printDotNotation(l);
				System.out.println("\n");
			}
			
	}
	
	// print the list in list notation
	public static void printListNotation(SNode s){
		if(!s.value.equals(Token.Dot)){
			System.out.print(s.value + " ");
		}else{
			System.out.print(Token.OpenParenthesis);
			while(!s.value.equals(Token.NIL)){
				printListNotation(s.left);
				s = s.right;
			}
			System.out.print(Token.ClosingParenthesis);
		}
		
	}
	
	//Print the Dot notation
	public static void printDotNotation(SNode s){
		    if(s.left == null && s.right == null){
		    	System.out.print(s.value);
		    	return;
		    }
			System.out.print(Token.OpenParenthesis);
			printDotNotation(s.left);
			System.out.print(s.value);
			printDotNotation(s.right);
			System.out.print(Token.ClosingParenthesis);
		
	}
}
