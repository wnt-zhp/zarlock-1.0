package zzDataBase;

public enum Result{
	OK, //Produkt jest ok
	USER_ABORTED, //U¿yszkodnik siê spêka³ ;-)
	NON_FATAL_ERRORS, // B³êdy bazy danych - np. ¿yskodnik nie ma praw do edycji
	FATAL_ERRORS // Du¿y b³¹d - czyli niewy³apany wyj¹tek klasy innej ni¿ ZZIllegalEx
}