export type Character = {
    id?: string
    name: string
    level: number
    experience: number
    experiencePoints: number
    coins: number;
    specialization: string[]
    race: string
    profession: string
    lifePoints: number
    lifePointsMax: number
    staminaPoints: number
    staminaPointsMax: number
    attributes: Attributes
    damageBonus: number
	armourClass: number
	fullDefense: number
	resistanceMind: number
	fullResistanceMind: number
	fullResistanceBody: number
	resistanceBody: number
	staminaBonus: number
	attackBonus: number
	defenseBonus: number
	magicBonus: number
	defense: number
	movement: number
};

export type Attributes = {
    strength: number
    dexterity: number
    agility: number
    constitution: number
    intelligence: number
    magicTalent: number
    charisma: number
}