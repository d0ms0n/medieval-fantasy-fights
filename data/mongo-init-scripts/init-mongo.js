db.createUser({
    user: 'admin',
    pwd: 'admin',
    roles: [
        {
            role: 'readWrite',
            db: 'mff',
        },
    ],
});

db.createCollection('skills', { capped: false });
db.createCollection('weapons', { capped: false });
db.createCollection('characters', { capped: false });

db.skills.insertMany(
[{
  "_id": new ObjectId(),
  "leadingAttribute": "pA",
  "name": "Anführen",
  "type": "SOCIAL",
  "unskilled": 6,
  "source": "KOD5 102"
},{
  "_id": new ObjectId(),
  "name": "Stichwaffen",
  "type": "WEAPON",
  "unskilled": 4,
  "source": ""
},{
  "_id": new ObjectId(),
  "name": "Bögen",
  "type": "WEAPON",
  "unskilled": 0,
  "source": ""
}]);

db.weapon.insertMany(
[{
  "_id": new ObjectId(),
  "diceCount": 1,
  "diceType": 6,
  "name": "Dolch",
  "staticDamage": -1,
  "type": "STABBING_WEAPON",
  "value": 5
},{
  "_id": new ObjectId(),
  "diceCount": 1,
  "diceType": 6,
  "name": "Wurfaxt",
  "staticDamage": 0,
  "type": "POLE_THROWING_WEAPON",
  "range": {
    "close": {
      "min": 0,
      "max": 10
    },
    "medium": {
      "min": "11",
      "max": "20"
    },
    "distant": {
      "min": "21",
      "max": "30"
    }
  },
  "requirements": {
    "strength": "6",
    "dexterity": "6"
  },
  "value": "25"
}
]);