import type {Character} from "../types/Character"
import type {CharacterInput} from "../types/CharacterInput"

export function loadCharacters(token: string): Promise<Character[]> {
    return fetch("/api/characters", { credentials: "include", headers: {"Authorization": "Bearer " + token}})
    .then(response => response.json())
    .then(data => {return data})
    .catch((error) => {
        console.log(error);
        return [];
    });
}

export function createCharacter(token: string, characterInput: CharacterInput): Promise<Character> {
    return fetch("/api/characters", {
        method: "POST",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + token },
        body: JSON.stringify(characterInput),
        credentials: "include",
    })
    .then(response => response.json())
    .then(data => {return data})
    .catch((error) => {
        console.log(error);
        return [];
    });
}

export function loadProfessionTypes(){
    return fetch("/api/sources/professions")
      .then(response => response.json())
      .then(data => {return data})
      .catch(error => {
          console.log(error);
          return [];
      });
}

export { Character, CharacterInput }