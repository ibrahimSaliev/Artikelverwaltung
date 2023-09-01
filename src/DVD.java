/**
 * @author Stefan Maksimovic
 * Matrikelnummer: 01146675
 */

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DVD extends Artikel {
	
	private static final long serialVersionUID = 1L;
	private int runtime;
	private int altersfreigabe;
	private double rabatt=0.00;

	public DVD(int id, String titel, String verlag,int datum, double grundpreis,int runtime, int freigabe) {
		super(id, titel, verlag, datum, grundpreis);
		setRuntime(runtime);
		setAltersfreigabe(freigabe);
	}

	public int getRuntime() {
		return this.runtime;
	}
	
	public void setRuntime(int runtime) {
		if(runtime<=0) {
			throw new IllegalArgumentException("Error: Parameter ungueltig.");
		}else {
			this.runtime=runtime;
		}
	}
	
	public int getAltersfreigabe() {
		return this.altersfreigabe;
	}
	
	public void setAltersfreigabe(int freigabe) {
		if(freigabe==0 || freigabe==6 || freigabe==12 || freigabe==16 || freigabe==18) {
			this.altersfreigabe=freigabe;
		}else {
			throw new IllegalArgumentException("Error: Altersfreigabe ungueltig.");
		}
	}

	@Override
	public double rabatt() {
		switch(altersfreigabe) {
		
		case 0:
			this.rabatt += 0.2;
			break;
		case 6:
			this.rabatt += 0.15;
			break;
		case 12:
			this.rabatt += 0.1;
			break;
		case 16:
			this.rabatt += 0.05;
			break;
		case 18:
			this.rabatt= 0.00;
		
		}
		return rabatt;
	}
	
    public String toString(){
		
		
		
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("##.00", dfs);
		return "Typ:        DVD\n" +
			   "Id:         " + this.getId() +"\n" +
			   "Titel:      " + this.getTitel() + "\n" + 
			   "Jahr:       " + this.getErscheinungsdatum() + "\n" + 
			   "Verlag:     "+ this.getVerlag()+ "\n"+
			   "Grundpreis: " + df.format(this.getGrundpreis()) + "\n" +
			   "Preis:      " + df.format(this.preis()) + "\n" + 
			   "Dauer       " + this.getRuntime() + "\n" +
			   "Freigabe    " + this.getAltersfreigabe()+ "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + altersfreigabe;
		result = prime * result + runtime;
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
		DVD other = (DVD) obj;
		if (altersfreigabe != other.altersfreigabe)
			return false;
		if (runtime != other.runtime)
			return false;
		return true;
	}
	

}
