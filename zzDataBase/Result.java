package zzDataBase;

public enum Result{
	OK, //Produkt jest ok
	USER_ABORTED, //U�yszkodnik si� sp�ka� ;-)
	NON_FATAL_ERRORS, // B��dy bazy danych - np. �yskodnik nie ma praw do edycji
	FATAL_ERRORS // Du�y b��d - czyli niewy�apany wyj�tek klasy innej ni� ZZIllegalEx
}