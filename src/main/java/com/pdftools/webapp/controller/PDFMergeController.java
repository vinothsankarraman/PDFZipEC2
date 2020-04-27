package com.pdftools.webapp.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pdftools.webapp.service.PDFMergeService;

@Controller
public class PDFMergeController {
	
	@Autowired
	private PDFMergeService service;

	// Logic for PDF Merge using PDFBox
	@PostMapping(value = "/pdfmergenew")
	public void pdfmerge(@RequestParam MultipartFile file1, @RequestParam MultipartFile file2,
			HttpServletResponse response) throws Exception {

		System.out.println("Get mapping pdfmergenew upload   ...");
		String firstfinalname = null;
		String secondfinalname = null;
		InputStream in = null;

		String outputfilename = "mergedfile.pdf";
		
		// Setting the Upload Directory which is also a Backup Directory
				String UploadDir = "/home/ec2-user/mergeupload";

		// Get the Output file path
		// String outputfile = model.getSavefile();
		String outputfiledir = UploadDir + File.separator + outputfilename;
		System.out.println(outputfiledir);

		
		byte[] bytes1 = file1.getBytes();
		String fileName1 = StringUtils.cleanPath(file1.getOriginalFilename());
		System.out.println(fileName1);

		byte[] bytes2 = file2.getBytes();
		String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
		System.out.println(fileName2);

		// To get only the PDF file name
		String firstfile[] = fileName1.split("/");
		String secondfile[] = fileName2.split("/");

		for (String name : firstfile) {
			if (name.endsWith(".pdf")) {
				firstfinalname = name;
			}
		}
		System.out.println(firstfinalname);

		for (String name : secondfile) {
			if (name.endsWith(".pdf")) {
				secondfinalname = name;
			}
		}
		System.out.println(secondfinalname);

		// Write the File to the upload directory path
		Path pathfirst = Paths.get(UploadDir + File.separator + firstfinalname);
		System.out.println("got the first path: " + pathfirst);
		Files.write(pathfirst, bytes1);

		Path pathsecond = Paths.get(UploadDir + File.separator + secondfinalname);
		System.out.println("got the second path: " + pathsecond);
		Files.write(pathsecond, bytes2);

		// String uploadedfile = pathfirst.toString();
		String file1path = (UploadDir + File.separator + firstfinalname);
		String file2path = (UploadDir + File.separator + secondfinalname);
		System.out.println(file1path);
		System.out.println(file2path);
		File downloadfilepath1 = new File(file1path);
		File downloadfilepath2 = new File(file2path);
		File downloadfilepathoutput = new File(outputfiledir);

		boolean result = service.pdfmergeservice(downloadfilepath1, downloadfilepath2, outputfiledir);

		if (result) {

			response.setContentType("application/octet-stream");
			String disHeader = "Attachment; Filename=" + outputfilename;
			System.out.println(disHeader);
			response.setHeader("Content-Disposition", disHeader);

			ServletOutputStream outs = response.getOutputStream();

			try {
				in = new BufferedInputStream(new FileInputStream(downloadfilepathoutput));

				System.out.println(in);

				int ch;
				while ((ch = in.read()) != -1) {
					outs.print((char) ch);
				}
			} finally {
				if (in != null)
					in.close(); // very important
			}

			outs.flush();
			outs.close();
			// in.close();
			downloadfilepath1.delete();
			downloadfilepath2.delete();
			downloadfilepathoutput.delete();

		}

	}

}
