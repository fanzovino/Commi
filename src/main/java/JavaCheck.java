import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.core.IsInstanceOf;


public class JavaCheck {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String value = "1234.56";
		BigDecimal data[] = {
				 new BigDecimal("12312342") ,
				 new BigDecimal("123.2342E-2"), 
				 new BigDecimal("-4.332E4"),
				 new BigDecimal("1.100"),
				 new BigDecimal("1.1345354")
		};
//		Object val = 12312342 ;
	//	Object val = 123.2342E-2; 
		//Object val = 123.2342E-2 -4.332E4 1.100 => 1.1345354
	//	Object val = -4.332E4 ;
		
		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
	//	simbolos.setExponentSeparator("E");
		
	//	DecimalFormat f = new DecimalFormat(pattern, symbols)
		//TODO: Ver el pattern a seguir.
	//	DecimalFormat formateador = new DecimalFormat(Pattern.compile("#.#####").pattern(),simbolos);
		DecimalFormat formateador = new DecimalFormat("#.#*");
		formateador.setDecimalFormatSymbols(simbolos);
	//	formateador.setMaximumFractionDigits(20);
		formateador.setGroupingSize(0);
		
	//	 Pattern p = Pattern.compile("#*.#*");
	//	
	//	Matcher m = p.matcher("#####.######");
	//	 boolean b = m.matches();
	//	 System.out.print(b);
	//	 System.out.print("---------------------------------------------------\n");
		 
		 for(int i = 0; i < data.length; i++) {
			 System.out.println ("POS " + String.valueOf(i) + " [" + String.valueOf(data[i]) + "]\n");
	
			 BigDecimal d = new BigDecimal(String.valueOf(data[i]));
			 System.out.println ("    Formateado es [" + d.toPlainString() + "]\n");
	
	//	if(val instanceof Double){
	//		System.out.println ("El formato es : " + formateador.format((Double)val));
	//	}else{
	//		System.out.println ( "No es un Double ");
	//
		 }
		
	//	System.out.println ( formateador.format(val));	
	}

}
