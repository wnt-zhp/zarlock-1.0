import java.awt.Component;


import zzProduct.Product;

public interface ProductDialog {
	public enum Result{
		OK, //Produkt jest ok
		USER_ABORTED, //U¿yszkodnik siê spêka³ ;-)
		NON_FATAL_ERRORS, // B³êdy bazy danych - np. ¿yskodnik nie ma praw do edycji
		FATAL_ERRORS // Du¿y b³¹d - czyli niewy³apany wyj¹tek klasy innej ni¿ ZZIllegalEx
	}
	
	/**
	 * 
	 * @param c W³aœciciel JDialoga
	 * @return Wartoœæ enuma. 
	 */
	Result display (Component c);
	
	/**
	 * 
	 * @param c W³aœciciel JDialoga
	 * @param p Produkt edytowany
	 * @return Wartoœæ enuma.
	 */
	Result display (Component c, Product p);
	
	/**
	 * Zwraca produkt
	 * @return
	 */
	Product getProduct();
	
	/**
	 * Problemy które na któe mo¿esz siê natkn¹æ:
	 * Generalnie na samych podwalinach progamu jest du¿a wada koncepcyjna. 
	 * Klasa produkt ma trzy klasy funkcji:
	 * getXXX - zwraca wartoœæ pola
	 * setXXX - ustawia wartoœæ pola
	 * changeXXX - zmienia wartoœc pola 
	 * jeœli pole jest nie-ustawione a wywo³ujesz na m\nim change - 
	 * rzucany jest wyj¹tek. Jeœli pole jest ustwaione a wywo³jesz na nim set
	 * To rzucany jest wyj¹tek.
	 * NIE ma funkcji booleanIsSetXXX niestety. - mo¿esz dopisaæ. ale chyba nie warto
	 * Czyli musisz to uwzglêdnic w dialogu. 
	 * Mozesz te¿ pogrzebaæ w klasie Simple pordukt. Pisa³em j¹ dawno i nei jestem z niej
	 * dumny. Tylko niech przejdzie wszystkie testy z zzTests z product w nazwie. 
	 * Uwa¿aj bp sotatnio siê naciolem - nie wszystkie funkcje changeXXX dzia³aj¹.
	 * Tzn nie wszêdzie jest linijka np. date = changedDate.  Tzn. wydaje mi siê ¿e ju¿ 
	 * wszystkie, ale wydaje mi siê ;-).
	 */
	
	/**
	 * Uwaga ogólna:
	 * W kalsie zzUser.User powsta³a rodzina funckji boolean canUserPerformXXX
	 * i u¿ywaj ich (ew. dopisuj kolejne funckje) zamiats porównywac priorytety. 
	 */
	
	/**
	 * Nie musisz implementowaæ tego interfejsu - idea jest wazna. 
	 * Rzucaj co tam chcesz.
	 * Mo¿esz podejrzeæ moja klasê co to robi, ale ona raczej nie ma mo¿liwoœci edycji. 
	 * Jak chcesz mozesz dopisaæ do mojej kalsy (ProductGeneratingDialog?), albo 
	 * po niej dziedziczyæ. 
	 * Proponuje zrób to na takiej zasadzie:
	 * class Dialog{
	 * private Flavour flav;
	 * private interface Flavour{
	 * //masz wszystkie funkcje do ustawiania
	 * }
	 * class CreateFlavout{
	 * 	Kiedy two¿ysz od zera produkt
	 * }
	 * class ModifyFlavour{
	 * 	Kiedy modyfikujesz
	 *    Pamiêtaj ¿e nie wszystkie pola musz¹ byc ustawione (choæ raczej bêd¹)
	 *   }
	 * 
	 * 
	 * 
	 */
}
