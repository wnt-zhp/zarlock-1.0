package zzQuery;

import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzProduct.Product;

public class HasMoreThanZero extends AbstractFilter {
	
	public static final String[] opis = 
		new String[]{
		"Wi�cej ni� zero",
		"Miej ni� zero"
	};

	private final class NozeroQuery extends AbstractQuery {
		boolean not;		
		
		@Override
		protected int cmp(Query q) {
			return 0;
		}

		public NozeroQuery(boolean not) {
			super("Jest na skladzie", "", "");
			// TODO Auto-generated constructor stub
			this.not = not;
		}

		@Override
		public boolean ask(Product p) {
			super.ask(p);
			try{
				if(not)	return  !(p.getCurrQ()>0); 
				else return p.getCurrQ()>0;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
	}

	public HasMoreThanZero() {
		super("Jest na sk�adzie", "Zwraca produkty kt�re maj� niezerow� ilo��");		
	}

	@Override
	public Query getFilter(boolean not) throws ZZInternalUnsupportedOperationExceptionEx {
		return new NozeroQuery(not);
	}

	@Override
	public boolean isPreSetted() {
		return true;
	}

	@Override
	public String[] getDescriptionOfNot() throws ZZInternalUnsupportedOperationExceptionEx {
		return opis;
	}
	
	

}
