import type { Skill } from "../types/Skill";

export function loadSkills(): Promise<Skill[]> {
    return fetch("/api/skills")
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
    skill: Skill
) {
    return fetch("/api/skills", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(skill),
    });
}

export function deleteSkill(
    skill: Skill
) {
    return fetch("/api/skills/" + skill.id, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(skill),
    });
}

export function updateSkill(skill: Skill){
    let skillId = skill.id;
    return fetch('/api/skills/' + skillId, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(skill)
      });
}


export { Skill }

