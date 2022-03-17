import type { Skill } from "../types/Skill";

export function loadSkills(token: string): Promise<Skill[]> {
    return fetch("/api/skills", { credentials: "include", headers: {"Authorization": "Bearer " + token}})
    .then(response => response.json())
    .then(data => {return data})
    .catch((error) => {
        console.log(error);
        return [];
    });
}

export function loadSkillTypes(){
    return fetch("/api/sources/skillTypes")
      .then(response => response.json())
      .then(data => {return data})
      .catch(error => {
          console.log(error);
          return [];
      });
  }

export function addSkill(
    skill: Skill,
    token : string
) {
    return fetch("/api/skills", {
        method: "POST",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + token},
        body: JSON.stringify(skill),
        credentials: "include",
    });
}

export function deleteSkill(
    skill: Skill,
    token: string
) {
    return fetch("/api/skills/" + skill.id, {
        method: "DELETE",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + token },
        body: JSON.stringify(skill),
        credentials: "include",
    });
}

export function updateSkill(skill: Skill, token: string){
    let skillId = skill.id;
    return fetch('/api/skills/' + skillId, {
        method: 'PUT',
        headers: {"Content-Type": "application/json", "Authorization": "Bearer " + token},
        body: JSON.stringify(skill),
        credentials: "include",
      });
}


export { Skill }

