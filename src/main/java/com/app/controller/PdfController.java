package com.app.controller;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.services.PdfService;
import com.app.utils.AppSettings;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class PdfController {

	
	@Autowired
	private PdfService pdfService;
	
	@GetMapping("/export")
	public void descargarPDF(@RequestParam(name = "nombre" , required = false, defaultValue = "") String nombre,
							@RequestParam(name = "anio" , required = false, defaultValue = "") String anio,
							@RequestParam(name = "total" , required = false, defaultValue = "0.00") double total,
							HttpServletResponse response) {
		try {
			
			Path file = Paths.get(pdfService.generatePlacesPdf(nombre,anio,total).getAbsolutePath());
			if(Files.exists(file)) {
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename"+ file.getFileName());
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
