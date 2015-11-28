
public class Checker {
	
	// check each funtion recursively
	public static String check(SNode s){
		// check if it's primitive type or check the function recursively
		 if(!s.value.equals(Token.Dot)){
			 return "";
		 }
		 String fun = s.left.value;
		 if(fun.equals("CAR")){
			 if(!s.right.left.value.equals(Token.Dot)){
				 isList("CAR", s.right.left.value);
			 }else{
				 isList("CAR", s.right.left.left.value);
			 }
			return check(s.right.left);
		 }
		 if(fun.equals("CDR")){
			 if(!s.right.left.value.equals(Token.Dot)){
				 isList("CDR", s.right.left.value);
			 }else{
				 isList("CDR", s.right.left.left.value);
			 }
			return check(s.right.left);
		 }
		 if(fun.equals("CONS")){  //check both parameter and recursively check
			 //first
			 SNode parameter  = s.right;
			 if(!parameter.left.value.equals(Token.Dot)){
				 isNat("CONS", parameter.left.value);
			 }else{
				 isNat("CONS", parameter.left.left.value);
			 }
			 //second parameter
			 if(!parameter.right.left.value.equals(Token.Dot)){
				 isList("CONS",parameter.right.left.value);
			 }else{
				 isList("CONS", parameter.right.left.left.value);
			 }
			 check(parameter.left);
			 return check(parameter.right.left);
		 }
		 if(fun.equals("NULL")){
			 if(!s.right.left.value.equals(Token.Dot)){
				 isList("NULL",s.right.left.value);
			 }else{
				 isList("NULL", s.right.left.left.value);
			 }
			 return check(s.right.left);
		 }
		if(fun.equals("ATOM")){
			 if(!s.right.left.value.equals(Token.Dot)){
				 isT("ATOM", s.right.left.value);
			 }else{
				 isT("ATOM", s.right.left.left.value);
			 }
			 return check(s.right.left);
		}
		if(fun.equals("EQ")){
			//first
			 SNode parameter  = s.right;
			 if(!parameter.left.value.equals(Token.Dot)){
				 isNat("EQ", parameter.left.value);
			 }else{
				 isNat("EQ", parameter.left.left.value);
			 }
			 //second parameter
			 if(!parameter.right.left.value.equals(Token.Dot)){
				 isNat("EQ",parameter.right.left.value);
			 }else{
				 isNat("EQ", parameter.right.left.left.value);
			 }
			 check(parameter.left);
			 return check(parameter.right.left);
		 }
		if(fun.equals("INT")){
			if(!s.right.left.value.equals(Token.Dot)){
				 isT("INT",s.right.left.value);
			 }else{
				 isT("INT",s.right.left.left.value);
			 }
			 return check(s.right.left);
		 }
		if(fun.equals("PLUS")){
			//first
			 SNode parameter  = s.right;
			 if(!parameter.left.value.equals(Token.Dot)){
				 isNat("PLUS", parameter.left.value);
			 }else{
				 isNat("PLUS", parameter.left.left.value);
			 }
			 //second parameter
			 if(!parameter.right.left.value.equals(Token.Dot)){
				 isNat("PLUS",parameter.right.left.value);
			 }else{
				 isNat("PLUS", parameter.right.left.left.value);
			 }
			 check(parameter.left);
			 return check(parameter.right.left);
		 }
		if(fun.equals("LESS")){
			//first
			 SNode parameter  = s.right;
			 if(!parameter.left.value.equals(Token.Dot)){
				 isNat("LESS", parameter.left.value);
			 }else{
				 isNat("LESS", parameter.left.left.value);
			 }
			 //second parameter
			 if(!parameter.right.left.value.equals(Token.Dot)){
				 isNat("LESS",parameter.right.left.value);
			 }else{
				 isNat("LESS", parameter.right.left.left.value);
			 }
			 check(parameter.left);
			 return check(parameter.right.left);
		 }
		if(fun.equals("COND")){
			 //evaluate each parameter
			SNode parameter = s.right;
			String prev = "null";
			while(!parameter.value.equals(Token.NIL)){
				String cur = "";
				//first parameter
				if(!parameter.left.left.value.equals(Token.Dot)){
					isBool("COND",parameter.left.left.value);
				}else{
					isBool("COND",parameter.left.left.left.value);
				}
				//second parameter
				if(!parameter.left.right.left.value.equals(Token.Dot)){
					cur = isT("COND",parameter.left.right.left.value);
				}else{
					cur = isT("COND",parameter.left.right.left.left.value);
				}
				if(!prev.equals("null") && !cur.equals(prev)){
					System.out.println("Error: Parameter for COND" + " All occurrences of T should be the same type !");
					System.exit(0);
				}
				prev = cur;
				parameter = parameter.right;
			}
			// recursively evaluate the parameter list of COND
			SNode runner = s.right;
			while(runner != parameter){
				check(runner.left.left);
				check(runner.left.right.left);
				runner = runner.right;
			}
			return "";
		 }
		return "";
	}
	
	
	
	
	
	
	public static boolean isList(String name, String fun){
		if(fun.equals("NIL") || fun.equals("CDR") || fun.equals("CONS")){
			return true;
		}
		System.out.println("Error: Parameter for "+name + " is not valid! A list needed !");
		System.exit(0);
		return false;
	}
	
	//check if the parameter is a boolean value
	public static boolean isBool(String name, String fun){
		if(fun.equals("T") || fun.equals("F") || fun.equals("ATOM") || fun.equals("EQ") 
				|| fun.equals("NULL") || fun.equals("INT") || fun.equals("LESS")){
			return true;
		}
		System.out.println("Error: Parameter for "+name + " is not valid! A bool value needed !");
		System.exit(0);
		return false;
	}
	// check if the parameter is numeric number
	public static boolean isNat(String name, String fun){
		if(fun.matches("[+-]?[1-9][0-9]*") || fun.matches("0") || fun.equals("CAR")
				|| fun.equals("PLUS")){
			return true;
		}
		System.out.println("Error: Parameter for "+name + " is not valid! A numeric value needed !");
		System.exit(0);
		return false;
	}
	
	// check if the parameter is T
	public static String isT(String name, String fun){
		if(fun.matches("[+-]?[1-9][0-9]*") || fun.matches("0") || fun.equals("CAR")
				|| fun.equals("PLUS")){
			return "NAT";
		}
		if(fun.equals("T") || fun.equals("F") || fun.equals("ATOM") || fun.equals("EQ") 
				|| fun.equals("NULL") || fun.equals("INT") || fun.equals("LESS")){
			return "BOOL";
		}
		if(fun.equals("NIL") || fun.equals("CDR") || fun.equals("CONS")){
			return "LISt";
		}else{
			System.out.println("Error: Parameter for "+name + " is not valid! A T value needed !");
			System.exit(0);
		}
		return "";
	}
}
