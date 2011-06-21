import java.awt.Component;


import zzProduct.Product;

public interface ProductDialog {
	public enum Result{
		OK, //Produkt jest ok
		USER_ABORTED, //U�yszkodnik si� sp�ka� ;-)
		NON_FATAL_ERRORS, // B��dy bazy danych - np. �yskodnik nie ma praw do edycji
		FATAL_ERRORS // Du�y b��d - czyli niewy�apany wyj�tek klasy innej ni� ZZIllegalEx
	}
	
	/**
	 * 
	 * @param c W�a�ciciel JDialoga
	 * @return Warto�� enuma. 
	 */
	Result display (Component c);
	
	/**
	 * 
	 * @param c W�a�ciciel JDialoga
	 * @param p Produkt edytowany
	 * @return Warto�� enuma.
	 */
	Result display (Component c, Product p);
	
	/**
	 * Zwraca produkt
	 * @return
	 */
	Product getProduct();
	
	/**
	 * Problemy kt�re na kt�e mo�esz si� natkn��:
	 * Generalnie na samych podwalinach progamu jest du�a wada koncepcyjna. 
	 * Klasa produkt ma trzy klasy funkcji:
	 * getXXX - zwraca warto�� pola
	 * setXXX - ustawia warto�� pola
	 * changeXXX - zmienia warto�c pola 
	 * je�li pole jest nie-ustawione a wywo�ujesz na m\nim change - 
	 * rzucany jest wyj�tek. Je�li pole jest ustwaione a wywo�jesz na nim set
	 * To rzucany jest wyj�tek.
	 * NIE ma funkcji booleanIsSetXXX niestety. - mo�esz dopisa�. ale chyba nie warto
	 * Czyli musisz to uwzgl�dnic w dialogu. 
	 * Mozesz te� pogrzeba� w klasie Simple pordukt. Pisa�em j� dawno i nei jestem z niej
	 * dumny. Tylko niech przejdzie wszystkie testy z zzTests z product w nazwie. 
	 * Uwa�aj bp sotatnio si� naciolem - nie wszystkie funkcje changeXXX dzia�aj�.
	 * Tzn nie wsz�dzie jest linijka np. date = changedDate.  Tzn. wydaje mi si� �e ju� 
	 * wszystkie, ale wydaje mi si� ;-).
	 */
	
	/**
	 * Uwaga og�lna:
	 * W kalsie zzUser.User powsta�a rodzina funckji boolean canUserPerformXXX
	 * i u�ywaj ich (ew. dopisuj kolejne funckje) zamiats por�wnywac priorytety. 
	 */
	
	/**
	 * Nie musisz implementowa� tego interfejsu - idea jest wazna. 
	 * Rzucaj co tam chcesz.
	 * Mo�esz podejrze� moja klas� co to robi, ale ona raczej nie ma mo�liwo�ci edycji. 
	 * Jak chcesz mozesz dopisa� do mojej kalsy (ProductGeneratingDialog?), albo 
	 * po niej dziedziczy�. 
	 * Proponuje zr�b to na takiej zasadzie:
	 * class Dialog{
	 * private Flavour flav;
	 * private interface Flavour{
	 * //masz wszystkie funkcje do ustawiania
	 * }
	 * class CreateFlavout{
	 * 	Kiedy two�ysz od zera produkt
	 * }
	 * class ModifyFlavour{
	 * 	Kiedy modyfikujesz
	 *    Pami�taj �e nie wszystkie pola musz� byc ustawione (cho� raczej b�d�)
	 *   }
	 * 
	 * 
	 * 
	 */
}
