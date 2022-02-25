import type { Weapon } from "../types/Weapon";

export function loadWeapons(): Promise<Weapon[]> {
    return fetch("/api/weapons")
    .then(response => response.json())
    .then(data => {return data})
    .catch((error) => {
        console.log(error);
        return [];
    });
}

export function loadWeaponTypes(){
    return fetch("/api/sources/weaponTypes")
      .then(response => response.json())
      .then(data => {return data})
      .catch(error => {
          console.log(error);
          return [];
      });
  }

export function addWeapon(
    weapon: Weapon
) {
    return fetch("/api/weapons", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(weapon),
    }).catch(error => console.log(error));
}

export function deleteWeapon(
    weapon: Weapon
) {
    return fetch("/api/weapons/" + weapon.id, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(weapon),
    });
}

export function updateWeapon(weapon: Weapon){
    let weaponId = weapon.id;
    return fetch('/api/weapons/' + weaponId, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(weapon)
      });
}


export { Weapon }

