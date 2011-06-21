package zzDataBase;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalNotEnoughPrivelagesEx;
import zzEx.ZZInternalDuplicateEntryEx;
import zzEx.ZZIoBadFormatEx;
import zzProduct.Product;

public abstract class LoadSaver {

	public static final String productstag = "Products.txt";
	public static final String TEMPLATE_TAG= "templates.txt";

	public static void load(StaticContent sc, File zippedFile)
			throws IOException, ZZInternalDuplicateEntryEx, ZZIoBadFormatEx,
			ZZIllegalNotEnoughPrivelagesEx, ZZIllegalEx {
		sc.setfireListeners(false);
		ZipFile zf = new ZipFile(zippedFile);
		ZipEntry products = zf.getEntry(productstag);
		BufferedReader brr = new BufferedReader(new InputStreamReader(zf
				.getInputStream(products)));
		String prod;
		while ((prod = brr.readLine()) != null && (prod.trim().length() != 0)) {
			sc.getDataBaseMutable().addProduct(prod);
		}
		Enumeration e = zf.entries();
		ZipEntry templates = zf.getEntry(TEMPLATE_TAG);
		BufferedReader tempBrr = new BufferedReader(new InputStreamReader(zf
				.getInputStream(templates)));
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
		while (e.hasMoreElements()) {
			StringWriter swr = new StringWriter();
			ZipEntry ze = (ZipEntry) e.nextElement();
			if (ze.getName().equals(productstag)){
				continue;
			}
			if (ze.getName().equals(TEMPLATE_TAG)){
				continue;
			}
			BufferedReader read = new BufferedReader(new InputStreamReader(zf
					.getInputStream(ze)));
			int c;
			while ((c = read.read()) != -1) {
				swr.write(c);
			}
			swr.flush();
			//System.out.println(swr.toString());
			new Day(swr.toString());
		}
		
		sc.setfireListeners(true);
		sc.fireChange(StaticContent.databaseChanged);
		sc.fireChange(StaticContent.templatesChanged);
		//TODO fire porducts added i inne listenerki
	}

	public static void save(StaticContent sc, File zippedFile)
			throws IOException {
		zippedFile.delete();
		FileOutputStream fout = new FileOutputStream(zippedFile);
		ZipOutputStream zos = new ZipOutputStream(fout);
		BufferedOutputStream out = new BufferedOutputStream(zos);
		OutputStreamWriter wrr = new OutputStreamWriter(out);
		zos.putNextEntry(new ZipEntry(productstag));
		for (Product p : sc.getDataBase().getDatabase().values()) {
			wrr.write(p.toString() + "\n");
		}
		wrr.flush();
		zos.putNextEntry(new ZipEntry(TEMPLATE_TAG));
		for (Vector<String> vec : sc.getTemplates()){
			for(String s: vec){
				wrr.write(s+ " ,");
			}
			wrr.write("\n");
		}
		wrr.flush();
		for (Day d : sc.getDays()) {
			zos.putNextEntry(new ZipEntry(d.getDate().toString().replace('.', '-')
					+ "_" + d.getName() + ".txt"));
			wrr.write(d.toString());
			wrr.flush();
		}
		wrr.close();
	}
}
