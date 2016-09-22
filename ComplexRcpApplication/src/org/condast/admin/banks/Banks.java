package org.condast.admin.banks;

import java.util.Collection;
import java.util.TreeSet;

import org.condast.admin.utils.StringStyler;

public class Banks {

	public enum BankNames{
		RABOBANK,
		ING_BANK,
		ASN_BANK,
		TRIODOS_BANK,
		FRIESLAND_BANK;

		@Override
		public String toString() {
			return StringStyler.prettyString( super.toString() );
		}	
	}
	
	private Collection<BankNames> selectedBanks;
	
	private static final Banks banks = new Banks();
	
	private Banks() {
		this.selectedBanks = new TreeSet<BankNames>();
		this.initBanks();
	}
	
	public static Banks getInstance(){
		return banks;
	}
	
	public void initBanks(){
		this.selectedBanks.add( Banks.BankNames.RABOBANK );
		this.selectedBanks.add( Banks.BankNames.ASN_BANK );
		this.selectedBanks.add( Banks.BankNames.TRIODOS_BANK );
		this.selectedBanks.add( Banks.BankNames.ING_BANK );
	}
	
	public void clear(){
		this.selectedBanks.clear();
	}
	
	public boolean addBank( BankNames bank ){
		return this.selectedBanks.add( bank );
	}

	public boolean removeBank( BankNames bank ){
		return this.selectedBanks.remove( bank );
	}

	public boolean isSelected( BankNames bank ){
		return this.selectedBanks.contains( bank );
	}
	
	public String[] getSelectedBankNames(){
		String[] names = new String[ this.selectedBanks.size() ];
		int i=0;
		for( BankNames bank: this.selectedBanks ){
		  names[i++] = bank.toString();
		}
		return names;
	}

}
