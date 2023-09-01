//import java.util.ArrayList;
//import java.util.List;
import java.text.ParseException;

/**
 * @author Stefan Maksimovic
 * Matrikelnummer: 01146675
 */


public class ArtikelverwaltungClient {

	public static void main(String[] args) {
		
		try {
		if(args.length==0) {
			throw new IllegalArgumentException("Error: Parameter ungueltig.");
		}
		if(args.length==1) {
			throw new IllegalArgumentException("Error: Parameter ungueltig.");
		}
		String fileName = args[0];
		Artikelverwaltung av = new Artikelverwaltung(fileName);
		
		if(args[1].equals("list")) {
			if(args.length == 2) {
			System.out.print(av.print());
			}
			if(args.length == 3){
			System.out.print(av.print(Integer.parseInt(args[2])));
			}
		}
		if(args[1].equals("add")) {
			if(args.length==2) {
				throw new IllegalArgumentException("Error: Parameter ungueltig.");
			}
			else if(args[2].equals("buch")){
				if(args.length == 9) {
					av.addBuch(Integer.parseInt(args[3]), args[4],args[5],Integer.parseInt(args[6]),
							Double.parseDouble(args[7]), Integer.parseInt(args[8]));
				}else {
					throw new IllegalArgumentException("Error: Parameter ungueltig.");
				}
		  } else if(args[2].equals("dvd")){
			  if(args.length == 10) {
				    av.addDvd(Integer.parseInt(args[3]), args[4],args[5], Integer.parseInt(args[6]),
				    		Double.parseDouble(args[7]), Integer.parseInt(args[8]), Integer.parseInt(args[9]));
			  }else {
				  throw new IllegalArgumentException("Error: Parameter ungueltig.");
			  }
				}
		}
		if(args[1].equals("delete")) {
			if(args.length==2) {
				throw new IllegalArgumentException("Error: Parameter ungueltig.");
			}
			else {
				av.deleteArtikel(Integer.parseInt(args[2]));
			}
		}
		if(args[1].equals("count")) {
			System.out.println(av.count());
		}
		if(args[1].equals("meanprice")) {
			System.out.println(av.avgPrice());
		}
		if(args[1].equals("oldest")) {
			System.out.print(av.oldest());
		}
		
		}catch(NumberFormatException e) {
			System.out.println("Error: Parameter ungueltig.");
			return;
		}catch(ParseException e) {
			System.out.println("Error: Parameter ungueltig.");
			return;
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		/*Artikelverwaltung av = new Artikelverwaltung("file.ser");
		av.addBuch(1, "HarryPotter", 2016, "verlagSth", 56.0, 34);
		System.out.println(av.print());
		System.out.println(av.avgPrice());*/
		
	/*	Artikel buch = new Buch(1,"Harry Potter",2016,"verlagSth",56.0,34);
		System.out.println(buch.getGrundpreis());
		System.out.println(buch.preis());
		System.out.println(buch.toString());
		//Artikel buch1 = new Buch(1,"Harry Potter",2016,"verlagSth",56.0,34);
		Artikel buch2 = new Buch(1,"Harry Potter",2016,"verlagSth",56.0,34);
		List<Artikel> buchList= new ArrayList<Artikel>();
		buchList.add(buch);
		if(buchList.contains(buch2)) {
			System.out.println("Contains!");
		}
		
		*/
	}
}
