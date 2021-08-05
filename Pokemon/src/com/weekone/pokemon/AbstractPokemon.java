package com.weekone.pokemon;

public abstract class AbstractPokemon implements PokemonInterface {
	public Pokemon createPokemon(String name, int health, String type) {
		Pokemon p = new Pokemon(name,health,type);
		return p;
	}
	public String pokemonInfo(Pokemon pokemon) {
		String a = "Name: "+ pokemon.getName() + 
				"\nHealth: "+pokemon.getHealth()+
				"\nType: "+pokemon.getType();
		return a;
	}
}


