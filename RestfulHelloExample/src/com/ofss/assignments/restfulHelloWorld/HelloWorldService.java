package com.ofss.assignments.restfulHelloWorld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Junaid Girkar
 *
 */
@Path("/hello")
public class HelloWorldService {
	// This method is called if HTML and XML is not requested
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey Plain";
	}

	// This method is called if XML is requested
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	}

	// This method is called if HTML is requested
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body><h1>" + "Hello Jersey HTML" + "</h1></body>"
				+ "</html> ";
	}

//	@Path("/hi")
//	@GET
//	@Produces(MediaType.TEXT_HTML)
//	public String sayPlainTextHello2() throws CsvValidationException, IOException {
//
//		String customer_id = "5000100006";
//		String accountId = "Q000092876521";
//		String return_value = csv_read.readCsv("C:\\Users\\junai\\eclipse-workspace\\CSV-CRUD\\CustomerData.csv",
//				customer_id, accountId);
//
//		return "<html> " + "<title>" + "Hello " + "</title>" + "<body><h1>" + "Hello " + return_value + "</h1></body>"
//				+ "</html> ";
//
//	}

	@Path("/file")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public InputStream hello() throws FileNotFoundException {
		File file = new File("index.html");
		return new FileInputStream(file);
	}

	@Path("/{name}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String hello(@PathParam("name") String name) {
		return "<html> " + "<title>" + "Hello " + name + "</title>" + "<body><h1>" + "Hello " + name + "</h1></body>"
				+ "</html> ";
	}
}