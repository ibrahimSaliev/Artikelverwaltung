/**
 * @author Stefan Maksimovic
 * Matrikelnummer: 01146675
 */
import java.util.*;
import java.text.DecimalFormat;
import java.text.ParseException;
//import java.util.ArrayList;

public class Artikelverwaltung {
	
	ArtikelDAO artikelDAO;
	DecimalFormat dm;
	
	public Artikelverwaltung(String fileName) {
		artikelDAO = new SerializationArtikelDAO(fileName);
		dm = new DecimalFormat("#.00");
	}
	
	public ArtikelDAO getArtikelDAO() {
		return this.artikelDAO;
	}
	
	public void setArtikelDAO(SerializationArtikelDAO dao) {
		this.artikelDAO=dao;
	}
	
	public String print() {
		String result = "";
		List<Artikel> list = artikelDAO.getArtikel();
		for(Artikel a : list) {
			result +=a.toString();
			//result += "\n";
		}
		return result.replace(',', '.');
	}
	
	public String print(int id) {
		if(artikelDAO.getArtikel(id) == null) {
			throw new IllegalArgumentException("Error: Artikel nicht vorhanden. (id=" + id + ")");
		}else {
			String result = artikelDAO.getArtikel(id).toString();
			return result.replace(',', '.');
		}
		
	}
	
	public void deleteArtikel(int id) {
		if(artikelDAO.getArtikel(id) == null) {
			throw new IllegalArgumentException("Error: Artikel nicht vorhanden. (id=" + id + ")");
		}else {
			artikelDAO.deleteArtikel(id);
		}
	}
	
	public int count() {
		return artikelDAO.getArtikel().size();
	}
	
	public String avgPrice() {
		int counter=0;
		double price=0.00;
		List<Artikel> list = artikelDAO.getArtikel();
		
		for(Artikel a : list) {
			price += a.preis();
			counter++;
		}
		if(counter==0) {
			return "0.00";
		}
		return dm.format(((double)price)/((double)counter)).replace(',', '.');
	}
	
	public String oldest() {
		List<Artikel> list = artikelDAO.getArtikel();
		if(list.isEmpty()) {
			return "";
		}
		int oldest = list.get(0).alter();
		for(Artikel a : list) {
			if(oldest < a.alter()) {
				oldest = a.alter();
			}
		}
		
		String result = "";
		for(Artikel a : list) {
			if(oldest == a.alter()) {
				result += "Id: "+ a.getId() + '\n';
			}
		}
		return result;
	}
	
	public void addBuch(int id,String titel,String verlag,int datum,double grundpreis,int pages) 
			throws ParseException {
		artikelDAO.saveArtikel(new Buch(id, titel, verlag, datum, grundpreis, pages));
		
	}
	
	public void addDvd(int id,String titel,String verlag,int datum,double grundpreis,int runtime,int freigabe)
	        throws ParseException {
		artikelDAO.saveArtikel(new DVD(id, titel, verlag, datum, grundpreis, runtime, freigabe));
		
	}
	

}
