
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;



public class Interpreter {
	    
		public static void main(String[] args){
			// Initialize the BufferedReader, and set the reader for scanner
			String filename = args[2];
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), Charset.forName("US-ASCII")));
				MyScanner.setReader(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<SNode> l = Parcer.ParseStart();
			for(SNode node:l){
				// type check the expression
				Checker.check(node);
				Printer.print(node);
			}
		}
				
}
