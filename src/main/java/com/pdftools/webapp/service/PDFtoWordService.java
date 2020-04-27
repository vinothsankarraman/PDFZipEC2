package com.pdftools.webapp.service;

import org.springframework.stereotype.Service;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

@Service
public class PDFtoWordService {
	
public boolean pdftoWordconvert(String uploadedfile, String savedfile) {
		
		System.out.println(uploadedfile);
		System.out.println(savedfile);		
				
		try {
			PdfDocument doc = new PdfDocument();
			
			doc.loadFromFile(uploadedfile);

			// Converts the PDF to Docx file format
			doc.saveToFile(savedfile, FileFormat.DOCX);
			doc.close();
				
			return true;
		}
		catch(Exception e){
			
			return false;
		}
	}
	
	public boolean validateinputs(String loadfile, String savefile) {
		
		if(loadfile.endsWith(".pdf") && savefile.endsWith(".docx"))
		{
			return true;
		}
		else 
		{
			return false;
			
		}
	}

}
