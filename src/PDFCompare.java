import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;


public class PDFCompare {

	public static void main(String[] arg) {
		
		//pdfCompare(args0,args1,args2);
		
		/*PDFUtil pdfUtil = new PDFUtil();
		
		String file1="C:/Users/dtandel/Desktop/PowerGateTesting/AllProd.pdf";
		String file2="C:/Users/dtandel/Desktop/PowerGateTesting/ALLRC.pdf";

		
		// compares the pdf documents &amp; returns a boolean
		// true if both files have same content. false otherwise.
		// Default is CompareMode.TEXT_MODE
		pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);
		//if you need to store the result
		pdfUtil.highlightPdfDifference(true);
		pdfUtil.setImageDestinationPath("c:/Users/dtandel/Desktop/PowerGateTesting/imgpath/");
		
		for(int i=1;i<=58;i++)
		{
			try {
				pdfUtil.compare(file1, file2, i);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
			
		pdfVisualCompare(arg[0],arg[1],arg[2],arg[3]);
		try {
			AddImageToWord(arg[2]);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pdfTextCompare();
	}
	
	public static void pdfTextCompare()
	{
		PDFUtil pdfUtil = new PDFUtil();
		
		String file1="C:/Users/dtandel/Desktop/PowerGateTesting/AllProd.pdf";
		String file2="C:/Users/dtandel/Desktop/PowerGateTesting/ALLRC.pdf";
		
		// compares the pdf documents &amp; returns a boolean
		// true if both files have same content. false otherwise.
		// Default is CompareMode.TEXT_MODE
		pdfUtil.setCompareMode(CompareMode.TEXT_MODE);
		//if you need to store the result
		pdfUtil.highlightPdfDifference(true);
		pdfUtil.setImageDestinationPath("c:/Users/dtandel/Desktop/PowerGateTesting/imgpath/");
		
		for(int i=1;i<=58;i++)
		{
			try {
				if(!pdfUtil.compare(file1, file2, i))
				{
				System.out.println("Page Number Mismatch: " +i);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void pdfVisualCompare(String file1, String file2, String imgPath, String pages)
	{
		PDFUtil pdfUtil = new PDFUtil();
		
		//file1="C:/Users/dtandel/Desktop/PowerGateTesting/AllProd.pdf";
		//file2="C:/Users/dtandel/Desktop/PowerGateTesting/ALLRC.pdf";
		
		// compares the pdf documents &amp; returns a boolean
		// true if both files have same content. false otherwise.
		// Default is CompareMode.TEXT_MODE
		pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);
		//if you need to store the result
		pdfUtil.highlightPdfDifference(true);
		pdfUtil.setImageDestinationPath(imgPath);
		int pageNumbers = Integer.parseInt(pages);
		//pdfUtil.setImageDestinationPath("c:/Users/dtandel/Desktop/PowerGateTesting/imgpath/");
		
		for(int i=1;i<=pageNumbers;i++)
		{
			try {
				pdfUtil.compare(file1, file2, i);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void AddImageToWord(String imgPath) throws InvalidFormatException, FileNotFoundException, IOException
	{
		 XWPFDocument doc = new XWPFDocument();
	        XWPFParagraph p = doc.createParagraph();
	        XWPFRun xwpfRun = p.createRun();
	        
	        //File folder = new File("c:/Users/dtandel/Desktop/PowerGateTesting/imgpath/");
	        File folder = new File(imgPath);
	        File[] listOfFiles = folder.listFiles();

	            for (int i = 0; i < listOfFiles.length; i++) {
	              if (listOfFiles[i].isFile()) {
	                System.out.println("File " + listOfFiles[i].getName());
	                String imgFile = imgPath +listOfFiles[i].getName();
	                int format=XWPFDocument.PICTURE_TYPE_PNG;
		            xwpfRun.setText(listOfFiles[i].getName());
		            xwpfRun.addBreak();
		            xwpfRun.addPicture (new FileInputStream(imgFile), format, imgFile, Units.toEMU(500), Units.toEMU(500)); // 200x200 pixels
		            xwpfRun.addBreak();
		            xwpfRun.addBreak();
	              } else if (listOfFiles[i].isDirectory()) {
	                System.out.println("Directory " + listOfFiles[i].getName());
	              }
	            }
	            	       
	        FileOutputStream out = new FileOutputStream(imgPath + "PdfDiffConsolidated.docx");
	        doc.write(out);
	        out.close();
	        
	       /* XWPFDocument doc = new XWPFDocument();

	        XWPFParagraph title = doc.createParagraph();    
	        XWPFRun run = title.createRun();
	        run.setText("Fig.1 A Natural Scene");
	        run.setBold(true);
	        title.setAlignment(ParagraphAlignment.CENTER);

	        String imgFile = "c:/Users/dtandel/Desktop/PowerGateTesting/imgpath/AllProd_1_diff.png";
	        FileInputStream is = new FileInputStream(imgFile);
	        run.addBreak();
	        run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(400), Units.toEMU(400)); // 200x200 pixels
	        is.close();

	        FileOutputStream fos = new FileOutputStream("c:/Users/dtandel/Desktop/PowerGateTesting/imgpath/PdfDiffConsolidated.docx");
	        doc.write(fos);
	        fos.close();  */      
	}
	
	public static File[] getFileList(String strPath)
	{
		File folder = new File(strPath);
		File[] listOfFiles = folder.listFiles();
		
		return listOfFiles;
	}
	
			
}
