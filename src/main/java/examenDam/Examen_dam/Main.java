package examenDam.Examen_dam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

public class Main {

	public static void main(String[] args) {

		// Sirve para leer archivo eventos.txt

		ArrayList<Eventos> listaEventos = new ArrayList<>();
		try (BufferedReader bufferReader = new BufferedReader(new FileReader("src/main/resources/eventos.txt"))) {
			String linea;
			while ((linea = bufferReader.readLine()) != null) {
				String[] partes = linea.split(",", 4);
				if (partes.length == 4) {
					String nombre = partes[0];
					LocalDateTime fecha = LocalDateTime.parse(partes[1]);
					String ubicacion = partes[2];
					String descripcion = partes[3];
					listaEventos.add(new Eventos(nombre, fecha, ubicacion, descripcion));
				}
			}

		} catch (IOException e) {
			System.out.println("Error leyendo el archivo" + e.getMessage());
		}

		// Creacion de eventos
		Eventos evento1 = new Eventos(null, null, null, null);
		evento1.setNombre("Real Madrid Vs Real Sociedad");
		evento1.setFecha(LocalDateTime.of(2025, 4, 21, 21, 00));
		evento1.setUbicacion("Santiago Bernabeu");
		evento1.setDescripcion("Partido de futbol q no sirve para nada");

		Eventos evento2 = new Eventos(null, null, null, null);
		evento2.setNombre("Concierto Bad Bunny");
		evento2.setFecha(LocalDateTime.of(2025, 6, 14, 21, 00));
		evento2.setUbicacion("Wanda Metropolitano");
		evento2.setDescripcion("Muy caro, es mejor escucharlo fuera");

		Eventos evento3 = new Eventos(null, null, null, null);
		evento3.setNombre("Campeonato Mundial de petanca");
		evento3.setFecha(LocalDateTime.of(2025, 12, 29, 20, 30));
		evento3.setUbicacion("Caceres");
		evento3.setDescripcion("Apasionante e increible deporte");

		listaEventos.add(evento1);
		listaEventos.add(evento2);
		listaEventos.add(evento3);

		// System.out.println(listaEventos.toString());

		// Guardar todos los datos en salida_eventos.txt y crearlo
		try (BufferedWriter bufferWriter = new BufferedWriter(
				new FileWriter("src/main/resources/salida_eventos.txt"))) {
			for (Eventos e : listaEventos) {
				bufferWriter
						.write(e.getNombre() + "," + e.getFecha() + "," + e.getUbicacion() + "," + e.getDescripcion());
				bufferWriter.newLine();
			}
			System.out.println("Archivo 'salida_eventos.txt' generado correctamente.");

		} catch (IOException e) {
			System.out.println("Error escribiendo el archivo: " + e.getMessage());
		}

		// Excel

		// Sirve para crear libro
		XSSFWorkbook libro = new XSSFWorkbook();

		// Sirve para crear hoja
		XSSFSheet hoja = libro.createSheet("eventos");

		// Sirve para crear filas y celdas

		XSSFRow fila0 = hoja.createRow(0);
		fila0.createCell(0).setCellValue("Nombre");
		fila0.createCell(1).setCellValue("Fecha");
		fila0.createCell(2).setCellValue("Ubicación");
		fila0.createCell(3).setCellValue("Descripción");

		int numFilas = 1;
		for (Eventos e : listaEventos) {
			XSSFRow fila = hoja.createRow(numFilas++);
			fila.createCell(0).setCellValue(e.getNombre());
			fila.createCell(1).setCellValue(e.getFecha().toString());
			fila.createCell(2).setCellValue(e.getUbicacion());
			fila.createCell(3).setCellValue(e.getDescripcion());
		}

		try (FileOutputStream out = new FileOutputStream("src/main/resources/eventos.xlsx")) {
			libro.write(out);
			libro.close();
			System.out.println("Excel 'eventos.xlsx' generado correctamente.");

		} catch (IOException e) {
			System.out.println("Error escribiendo el Excel: " + e.getMessage());
		}

		try {
			String ruta = "src/main/resources/resumen_eventos.pdf";
			PdfWriter PDFwriter = new PdfWriter(ruta);
			PdfDocument pdf = new PdfDocument(PDFwriter);
			Document document = new Document(pdf);

			// Título del archivo
			Paragraph titulo = new Paragraph("Resumen de Eventos").setFontSize(30).setBold()
					.setFontColor(ColorConstants.MAGENTA).setTextAlignment(TextAlignment.CENTER).setMarginBottom(20);
			document.add(titulo);

			// Eventos
			for (Eventos e : listaEventos) {
				String eventoTexto = "Nombre: " + e.getNombre() + "\n" + "Fecha: " + e.getFecha().toString() + "\n"
						+ "Ubicación: " + e.getUbicacion() + "\n" + "Descripción: " + e.getDescripcion() + "\n\n";

				Paragraph parrafoEvento = new Paragraph(eventoTexto).setFontSize(15).setMarginBottom(10);
				document.add(parrafoEvento);
			}

			document.close();
			System.out.println("PDF 'resumen_eventos.pdf' generado correctamente.");

		} catch (IOException e) {
			System.out.println("Error generando el PDF: " + e.getMessage());
		}

	}

}
