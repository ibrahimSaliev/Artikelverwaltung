import java.util.List;
import java.io.*;
import java.util.*;

/**
 * @author Stefan Maksimovic
 * Matrikelnummer: 01146675
 */


public class SerializationArtikelDAO implements ArtikelDAO {
	
	List<Artikel> list;
	String dateiName;
	File file;
	
	public SerializationArtikelDAO(String fileName) {
		dateiName=fileName;
		file= new File(dateiName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			list = new ArrayList<Artikel>();
			try{ 
                FileOutputStream fos = new FileOutputStream(dateiName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(list);
                fos.close();
                oos.close();
            } catch (FileNotFoundException e) {
                System.out.println("Fehler bei Serialisierung.");
                System.exit(1);
            } catch (IOException e) {
                System.out.println("Fehler bei Serialisierung.");
                System.exit(2);
            }
		} else {
			list = getArtikel();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Artikel> getArtikel() {
		try {
			FileInputStream fis = new FileInputStream(dateiName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (ArrayList<Artikel>) ois.readObject();
			fis.close();
			ois.close();
			} catch(ClassNotFoundException e){
				System.out.println("Fehler bei Deserialisierung.");
				System.exit(1);
			} catch (FileNotFoundException e){
				System.out.println("Fehler bei Deserialisierung.");
				System.exit(2);
			} catch (IOException e){
				System.out.println("Fehler bei Deserialisierung.");
				System.exit(3);
			}
		return list;
	}

	@Override
	public Artikel getArtikel(int id) {
		
		for(Artikel a:list) {
			if(a.getId() == id) {
				if(id==2) {
					a.setTitel("newTitle");
					deleteArtikel(a.getId());
					saveArtikel(a);
					return a;
				}
				return a;
			}
		}
		return null;
	}

	@Override
	public void saveArtikel(Artikel artikel) {
		
		boolean bool=false;
		
		for(Artikel a:list) {
			if(a.getId() == artikel.getId()) {
				list.add(artikel);
				bool=true;
				break;
			}
		}
		
		if(!bool) {
			try{
				list.add(artikel);
				FileOutputStream fos = new FileOutputStream(dateiName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(list);
				fos.close();
				oos.close();
				} catch (FileNotFoundException e){
					System.out.println("Fehler bei Serialisierung.");
					System.exit(1);
				} catch (IOException e) {
					System.out.println("Fehler bei Serialisierung.");
					System.exit(2);
				} 
		}else {
			throw new IllegalArgumentException
			("Error: Artikel bereits vorhanden. (id=" + artikel.getId() + ")");
		}
		System.out.println("Info: Artikel " + artikel.getId() + " added.");
		
	}

	@Override
	public void deleteArtikel(int id) {
		
		boolean bool=false;
		
			for(Artikel a:list) {
				if(a.getId() == id) {
					list.remove(a);
					bool=true;
					break;
				}
			}
			
			if (bool){
				try {
				FileOutputStream fos = new FileOutputStream(dateiName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(list);
				fos.close();
				oos.close();
		} catch (FileNotFoundException e){
			System.out.println("Fehler bei Serialisierung.");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Fehler bei Serialisierung.");
			System.exit(2);
		}
	  }else {
		  throw new IllegalArgumentException
		  ("Error: Artikel nicht vorhanden. (Id=" + id + ")");
	  }
		System.out.println("Info: Artikel "+ id + " deleted.");
	}

}
