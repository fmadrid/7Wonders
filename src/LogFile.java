
import java.io.BufferedWriter;
import java.io.FileWriter;

public class LogFile {
	
   private int            sensitivity;
   public  BufferedWriter output;

   public LogFile(String name) {
	   
	   sensitivity = 0;
      
	   try {output = new BufferedWriter(new FileWriter(name));}
	   catch(Exception e){}

   }
   
   public void close() {
   
      try{output.close();} 
      catch(Exception e){}
      
   }
   
   public void setPriority(int level) { sensitivity = level;}
   
   public int getSensitivity() {return sensitivity;}
   
   public void write(int level, String message) {
   
	   if(level <= sensitivity) {
    	 
		   try{output.write(message);} 
		   catch(Exception e){}
    
	   }
	   
   }
 
}