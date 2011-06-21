package zzDataBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalNotEnoughPrivelagesEx;
import zzEx.ZZInternalDuplicateEntryEx;
import zzEx.ZZIoBadFormatEx;

public class DirLoadSaver extends LoadSaver {
   
   public static void load(StaticContent sc, File dir)
   throws IOException, ZZInternalDuplicateEntryEx, ZZIoBadFormatEx,
   ZZIllegalNotEnoughPrivelagesEx, ZZIllegalEx {
      sc.setfireListeners(false);
      String prod;
      File products = new File(dir, DirLoadSaver.productstag);
      BufferedReader brr = new BufferedReader(new FileReader(products));
      while ((prod = brr.readLine()) != null && (prod.trim().length() != 0)) {
         sc.getDataBaseMutable().addProduct(prod);
      }
      
      File templates = new File(dir, DirLoadSaver.TEMPLATE_TAG );
      
      BufferedReader tempBrr = new BufferedReader(new FileReader(templates));
      String template;     
      while ((template = tempBrr.readLine()) != null && (template.trim().length() != 0)) {
         System.out.println("templates" + template);
         StringTokenizer tok = new StringTokenizer(template,",");
         Vector<String> vec= new Vector<String>(10); 
         while(tok.hasMoreElements()){
            vec.add(tok.nextToken().trim());
            System.out.println(vec.lastElement());
         }
         sc.getTemplates().add(vec);
      }
      
      File[] list = dir.listFiles();
      for(File f: list){
         StringWriter swr = new StringWriter();
         if(f.equals(templates) || f.equals(products)) continue;
         BufferedReader read = new BufferedReader(new FileReader(f));
         int c;
         while ((c = read.read()) != -1) {
            swr.write(c);
         }
         swr.flush();
         new Day(swr.toString());
      }     
   }
}
