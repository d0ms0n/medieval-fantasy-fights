import type { Weapon } from "../types/Weapon";

export function loadWeapons(token: string): Promise<Weapon[]> {
    return fetch("/api/weapons", { credentials: "include", headers: {"Authorization": "Bearer " + token}})
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
    weapon: Weapon, token: string
) {
    return fetch("/api/weapons", {
        method: "POST",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + token },
        body: JSON.stringify(weapon),
        credentials: "include",
    }).catch(error => console.log(error));
}

export function deleteWeapon(
    weapon: Weapon, token: string
) {
    return fetch("/api/weapons/" + weapon.id, {
        method: "DELETE",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + token },
        body: JSON.stringify(weapon),
        credentials: "include",
    });
}

export function updateWeapon(weapon: Weapon, token: string){
    let weaponId = weapon.id;
    return fetch('/api/weapons/' + weaponId, {
        method: 'PUT',
        headers: {"Content-Type": "application/json", "Authorization": "Bearer " + token},
        body: JSON.stringify(weapon),
        credentials: "include",
      });
}


export { Weapon }

