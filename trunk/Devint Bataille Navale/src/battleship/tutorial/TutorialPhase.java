package battleship.tutorial;

public enum TutorialPhase {
	
	// Bonjour, capt zo� bla bla
	P1,
	
	// Pour t'entrainer... -> P22
	P2,
	
	// Maintenant, viser case verte (P23) + affichage case verte
	P3,
	
	// Bravo ! + Les bateaux ennemi ... -> tu vas couler ce bateau. (en attente d'un tir r�ussi)
	P4,
	
	// Une case du bateau a �t� touch�e + son. en attente du bateau coul�
	P5,
	
	// Le bateau a �t� coul�, + conclusion et fin du tuto
	P6,
	
	// Arr�t du tuto
	P7
}
