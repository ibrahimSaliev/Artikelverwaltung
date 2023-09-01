/**
 * @author Stefan Maksimovic
 * Matrikelnummer: 01146675
 */

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Buch extends Artikel{
	

	private static final long serialVersionUID = 1L;
	private int pages;
	private double rabatt=0.00;
	private int alter;

	public Buch(int id, String titel, String verlag,int datum, double grundpreis,int pages) {
		super(id, titel, verlag, datum, grundpreis);
		setPages(pages);
		this.alter=alter();
		
	}
	
	public int getPages() {
		return this.pages;
	}
	
	public void setPages(int pages) {
		if(pages<=0) {
			throw new IllegalArgumentException("Error: Parameter ungueltig.");
		}else {
			this.pages=pages;
		}
	}

	@Override
	public double rabatt() {
        while(this.alter>0 && this.rabatt<0.3){
			
			this.rabatt += 0.05;
			this.alter-=1;
		}
        if(this.pages>1000) {
        	this.rabatt+=0.03;
        }
		
		return rabatt;
	}
	
    public String toString(){
		
		
		
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("##.00", dfs);
		return "Typ:        Buch\n" +
			   "Id:         " + this.getId() +"\n" +
			   "Titel:      " + this.getTitel() + "\n" + 
			   "Jahr:       " + this.getErscheinungsdatum() + "\n" + 
			   "Verlag:     "+ this.getVerlag()+"\n"+
	           "Grundpreis: " + df.format(this.getGrundpreis()) + "\n" +
			   "Preis:      " + df.format(this.preis()) + "\n" + 
			   "Seiten:     " + this.getPages()+"\n";
	}
    
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + pages;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buch other = (Buch) obj;
		if (pages != other.pages)
			return false;
		return true;
	}
	

}
