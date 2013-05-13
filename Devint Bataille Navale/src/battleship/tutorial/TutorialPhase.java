package battleship.tutorial;

public enum TutorialPhase {
	
	// Bonjour, capt zoé bla bla
	P1,
	
	// Pour t'entrainer... -> P22
	P2,
	
	// Maintenant, viser case verte (P23) + affichage case verte
	P3,
	
	// Bravo ! + Les bateaux ennemi ... -> tu vas couler ce bateau. (en attente d'un tir réussi)
	P4,
	
	// Une case du bateau a été touchée + son. en attente du bateau coulé
	P5,
	
	// Le bateau a été coulé, + conclusion et fin du tuto
	P6,
	
	// Arrêt du tuto
	P7
}
