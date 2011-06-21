package field;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zzEx.ZZIllegalWrongPatternEx;

public class field {
  static final String QuotationPatternString = "((\" & ^(\\\\ \")){1} .* (\" & ^(\\\\ \")){1}?";
  final Pattern QuotationPattern 
    = Pattern.compile(QuotationPatternString);
  
  static final String longQuotationMarksString = "\\\\ \"";
  final Pattern longQuotationMarksPattern = Pattern.compile(longQuotationMarksString);
  
  static final String comma = "\\.";
  
  static final String slash = "\\\\\\\\";
  protected field() {
    super();
  }
  
  protected Object parseString(String s, Parseable p) throws ZZIllegalWrongPatternEx{
    s = s.trim();
    Matcher m = QuotationPattern.matcher(s);
    if(m.groupCount()!=1) throw new ZZIllegalWrongPatternEx();
    String interior = m.group();
    String exterior = m.replaceAll("");
    exterior = exterior.trim(); 
    if(exterior.equals(",")) throw new ZZIllegalWrongPatternEx();
    Matcher m2 = longQuotationMarksPattern.matcher(interior);
    m2.replaceAll("\"");
    
    return null;
  }
  

}
