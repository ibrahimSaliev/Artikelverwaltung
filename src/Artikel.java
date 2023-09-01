/**
 * @author Stefan Maksimovic
 * Matrikelnummer: 01146675
 */

import java.io.Serializable;
import java.util.*;

public abstract class Artikel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int Id;
	private String Titel;
	private int Erscheinungsdatum;
	private String Verlag;
	private double Grundpreis;
	
	public Artikel(int id,String titel,String verlag,int datum,double grundpreis) {
		setId(id);
		setTitel(titel);
		setErscheinungsdatum(datum);
		setVerlag(verlag);
		setGrundpreis(grundpreis);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitel() {
		return Titel;
	}

	public void setTitel(String titel) {
		Titel = titel;
	}

	public int getErscheinungsdatum() {
		return Erscheinungsdatum;
	}

	public void setErscheinungsdatum(int erscheinungsdatum) {
		Calendar rightNow = Calendar.getInstance();
		int b=rightNow.get(Calendar.YEAR);
		if(erscheinungsdatum<=b)
		   this.Erscheinungsdatum = erscheinungsdatum;
		else
			throw new IllegalArgumentException("Error: Erscheinungsjahr ungueltig.");
	}

	public String getVerlag() {
		return Verlag;
	}

	public void setVerlag(String verlag) {
		Verlag = verlag;
	}

	public double getGrundpreis() {
		return Grundpreis;
	}

	public void setGrundpreis(double grundpreis) {
		if(grundpreis>0) {
			this.Grundpreis=grundpreis;
		}else {
			throw new IllegalArgumentException("Error: Parameter ungueltig.");
		}
	}
	
	abstract public double rabatt();
	
	public double preis(){
		double preis;
		preis = this.Grundpreis-(this.Grundpreis*this.rabatt());
		return preis;
		//return Math.round(preis * 100.0) / 100.0;
		
	}
	
	public int alter() {
		Calendar now = Calendar.getInstance();
		int res=now.get(Calendar.YEAR)-getErscheinungsdatum();
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Erscheinungsdatum;
		long temp;
		temp = Double.doubleToLongBits(Grundpreis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Id;
		result = prime * result + ((Titel == null) ? 0 : Titel.hashCode());
		result = prime * result + ((Verlag == null) ? 0 : Verlag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artikel other = (Artikel) obj;
		if (Erscheinungsdatum != other.Erscheinungsdatum)
			return false;
		if (Double.doubleToLongBits(Grundpreis) != Double.doubleToLongBits(other.Grundpreis))
			return false;
		if (Id != other.Id)
			return false;
		if (Titel == null) {
			if (other.Titel != null)
				return false;
		} else if (!Titel.equals(other.Titel))
			return false;
		if (Verlag == null) {
			if (other.Verlag != null)
				return false;
		} else if (!Verlag.equals(other.Verlag))
			return false;
		return true;
	}
	
	

}
