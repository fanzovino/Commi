import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.telefonica.ws.amdocs.commissioning.files.generic.AmdocsInputRecord;


public class JavaRegex {

	public JavaRegex() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		 Pattern p = Pattern.compile("DA*\\.W*");
//		 Matcher m = p.matcher("DAAAAA.WWWW");
//		 System.out.print( m.toString() +"\n\n\n\n");
//		System.out.print(m.matches() + " >>>> " + p.pattern());
		AmdocsInputRecord amDocsInputRec = new AmdocsInputRecord() ;
		String fieldType = "BOOLEAN";
		int pos = 1;
		
		
		if(fieldType.equalsIgnoreCase("BOOLEAN")){
			fieldType = "Bool";
		}else{
			//remplazo la primera letra a UPPER para poder pedirlo al instanciar el metodo
			fieldType =  fieldType.substring(0, 1).toUpperCase() + fieldType.substring(1, fieldType.length()).toLowerCase();
		}
//		amDocsInputRec.getFieldBool(int i)
		  
		try {
			Class c = Class.forName(AmdocsInputRecord.class.getCanonicalName());
			Method mthd = c.getMethod("getField"+fieldType,Integer.TYPE);
			Object output=(Object)mthd.invoke(amDocsInputRec,pos);
			
			System.out.print(mthd.getName());
			System.out.print(mthd.toGenericString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	     		
		
		

	}

}
