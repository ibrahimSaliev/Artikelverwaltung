import java.util.List;

/**
 * @author Stefan Maksimovic
 * Matrikelnummer: 01146675
 */


public interface ArtikelDAO {

	public List<Artikel> getArtikel();

	public Artikel getArtikel(int id);
	
	public void saveArtikel(Artikel artikel);

	public void deleteArtikel(int id);
}
