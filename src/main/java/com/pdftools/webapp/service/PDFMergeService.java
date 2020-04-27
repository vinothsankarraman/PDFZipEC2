package com.pdftools.webapp.service;

import java.io.File;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

@Service
public class PDFMergeService {
 
	public boolean pdfmergeservice(File file1, File file2, String outputfile) {
		
		System.out.println(file1);
		System.out.println(file2);		
				
		try {
			// Loading the PDF documents	
			PDDocument doc1 = PDDocument.load(file1);			
			PDDocument doc2 = PDDocument.load(file2);

			// Instantiating PDFMergerUtility class
			PDFMergerUtility PDFmerger = new PDFMergerUtility();

			// Setting the destination file
			PDFmerger.setDestinationFileName(outputfile);

			// adding the source files into Merger
			PDFmerger.addSource(file1);
			PDFmerger.addSource(file2);

			// Merging the two documents
			PDFmerger.mergeDocuments(null);
			System.out.println("Documents merged");

			// Closing the documents
			doc1.close();
			doc2.close();
				
			return true;
		}
		catch(Exception e){
			
			return false;
		}
	}

}
