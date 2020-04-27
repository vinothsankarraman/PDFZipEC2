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
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pdftools.webapp.service.PDFtoWordService;

@Controller
public class PDFtoWordController {
	
	@Autowired
	private PDFtoWordService service;

	// For Display home page
	@GetMapping(value = "/")
	public String homepage(ModelMap model) {
		// return new ModelAndView("home","","");
		System.out.println("Get mapping  ...");
		return "home";
		// return new ModelAndView(new RedirectView("home.jsp"));

	}

	@GetMapping(value = "pdf2word")
	public String pdftowordpage(ModelMap model) {
		// return new ModelAndView("home","","");
		System.out.println("Get mapping pdfto word ...");
		return "PdftoWord";
		// return new ModelAndView(new RedirectView("home.jsp"));

	}

	@GetMapping(value = "merge")
	public String mergepage(ModelMap model) {
		// return new ModelAndView("home","","");
		System.out.println("Get mapping Merge ...");
		return "Merge";
		// return new ModelAndView(new RedirectView("home.jsp"));

	}

	// Logic with Upload Functionality
	@PostMapping(value = "/pdftowordupload")
	public void pdftowordupload(@RequestParam MultipartFile file, HttpServletResponse response) throws Exception {

		String finalname = null;
		InputStream in = null;
		

		String finalnamewithoutextn = null;

		System.out.println("Get mapping pdftoword upload   ...");

		// Setting the Upload Directory which is also a Backup Directory
		String UploadDir = "/home/ec2-user/pdftoword";

		byte[] bytes = file.getBytes();

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println(fileName);

		// To get only the PDF file name
		String list[] = fileName.split("/");

		for (String name : list) {
			if (name.endsWith(".pdf")) {
				finalname = name;
			}
		}
		System.out.println(finalname);

		// Remove the Extension and have only the file name
		finalnamewithoutextn = finalname.replaceFirst("[.][^.]+$", "");

		// Write the File to the upload directory path
		Path path = Paths.get(UploadDir + File.separator + finalname);
		System.out.println("got the path: " + path);
		Files.write(path, bytes);

		String uploadedfile = path.toString();
		String savedfile = (UploadDir + File.separator + finalnamewithoutextn + ".docx");
		System.out.println(savedfile);

		String downloadfile = finalnamewithoutextn + ".docx";

		
		File downloadfilepath = new File(savedfile);
		File uploadedfilepath = new File(uploadedfile);
		// File downloadfilepath1 = new File(newsavefile);

		System.out.println("File Uploaded..");
		System.out.println("Downloaded file path" + downloadfilepath);
		// System.out.println(downloadfilepath1);

		boolean output = service.pdftoWordconvert(uploadedfile, savedfile);

		if (output) {

			System.out.println("file converted using spire");
			response.setContentType("application/octet-stream");
			String disHeader = "Attachment; Filename=" + downloadfile;
			System.out.println(disHeader);
			response.setHeader("Content-Disposition", disHeader);

			ServletOutputStream outs = response.getOutputStream();

			try {
				in = new BufferedInputStream(new FileInputStream(downloadfilepath));

				System.out.println(in);

				int ch;
				while ((ch = in.read()) != -1) {
					outs.print((char) ch);
				}
			} finally {
				if (in != null) {
					in.close(); // very important
				}

			}

			outs.flush();
			outs.close();
			// in.close();
			downloadfilepath.delete();
			uploadedfilepath.delete();

		}

	}

}
