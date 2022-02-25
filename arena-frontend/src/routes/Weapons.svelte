<script lang="ts">
	import Button, { Label as BtnLabel } from '@smui/button';
	import DataTable,{ Body,Cell,Head,Label,Row } from '@smui/data-table';
	import IconButton, { Icon } from '@smui/icon-button';
  import { mdiPencil as pencil } from '@mdi/js';
  import { Svg } from '@smui/common/elements';
	import Textfield from '@smui/textfield';
	import Select, { Option } from '@smui/select';
	import Dialog, { Title, Content, Actions } from '@smui/dialog';
	import { onMount } from 'svelte';
  import type { Weapon } from '../services/weaponService'; 
  import * as WeaponService from '../services/weaponService'; 

  let sort: keyof Weapon = 'weaponName';
  let sortDirection = 'ascending';
  let weapons: Weapon[] = [];
  let weaponTypes = [];
  let open = false;
  let weaponInput: Weapon = {weaponName: "", weaponType: "", diceCnt: 1, diceType: 6, staticDamage: +1};

	onMount(async () => {
    weapons = await WeaponService.loadWeapons();
    weaponTypes = await WeaponService.loadWeaponTypes();
	});

  function handleSort() {
    weapons.sort((a, b) => {
      const [aVal, bVal] = [a[sort], b[sort]][
        sortDirection === 'ascending' ? 'slice' : 'reverse'
      ]();
      if (typeof aVal === 'string' && typeof bVal === 'string') {
        return aVal.localeCompare(bVal);
      }
      return Number(aVal) - Number(bVal);
    });
    weapons = weapons;
  }

  function addOrUpdateWeapon() {
    if(weaponInput.id == null) {
      console.log("add "+JSON.stringify(weaponInput));
      WeaponService.addWeapon(weaponInput)
        .then((response) => {
          if (response.status == 201) {
            WeaponService.loadWeapons().then(data => (weapons = data));
          }
        })
        .catch((error) => {
            console.log(error);
            WeaponService.loadWeapons().then(data => (weapons = data));
        });
    } else {
      WeaponService.updateWeapon(weaponInput)
      .then(response => {
        if(response.status == 204){
          WeaponService.loadWeapons().then(data => (weapons = data));
        }
      })
      .catch(error => {
          console.log(error);
          WeaponService.loadWeapons().then(data => (weapons = data));
      })
      console.log("update "+JSON.stringify(weaponInput));
    }
  }
</script>

<h2>Skills verwalten</h2>

<DataTable sortable
             bind:sort
             bind:sortDirection
             on:SMUIDataTable:sorted={handleSort}
             table$aria-label="Skill list"
             style="width: 100%;">
  <Head>
    <Row>
      <Cell columnId="weaponName">
        <IconButton class="material-icons">arrow_upward</IconButton>
        <Label>Name</Label>
      </Cell>
      <Cell columnId="weaponType">
        <IconButton class="material-icons">arrow_upward</IconButton>
        <Label>Type</Label>
      </Cell>
      <Cell columnId="damage">
        <IconButton class="material-icons">arrow_upward</IconButton>
        <Label>Schaden</Label>
      </Cell>
    </Row>
  </Head>
  <Body>
  {#each weapons as weapon }
    <Row id={weapon.id}>
          <Cell>{weapon.weaponName}</Cell>
          <Cell>{weapon.weaponType}</Cell>
          <Cell>{weapon.diceCnt == 0 ? "" : weapon.diceCnt + "W" + weapon.diceType} {
            weapon.staticDamage > 0 ? "+ " + weapon.staticDamage : (weapon.staticDamage < 0 ? "- " + weapon.staticDamage * -1 : "")
          }</Cell>
          <Cell>
            <!-- <div style="display: flex; align-items: center;">
            <IconButton class="material-icons" on:click={() => console.log("click")} size="normal">
              <Icon component={Svg} viewBox="0 0 24 24">
                <path fill="currentColor" d={pencil} />
              </Icon>
            </IconButton> -->
            <div on:click={() => {open = true; weaponInput = weapon}}>Edit</div>
          <!-- </div> -->
          </Cell>
          <Cell>
            <!-- <div style="display: flex; align-items: center;">
            <IconButton class="material-icons" on:click={() => console.log("click")} size="normal">
              <Icon component={Svg} viewBox="0 0 24 24">
                <path fill="currentColor" d={pencil} />
              </Icon>
            </IconButton> -->
            <div on:click={() => {
                WeaponService.deleteWeapon(weapon)
                .then(() => {WeaponService.loadWeapons()
                  .then(data => (weapons = data))})}
              }>Delete</div>
          <!-- </div> -->
          </Cell>
    </Row>
  {/each}
  <Button on:click={() => {open = true; weaponInput = {weaponName: "", weaponType: "", diceCnt: 1, diceType: 6, staticDamage: +0}}} variant="raised">
    <Label>Add weapon</Label>
  </Button>

  </Body>
</DataTable>

<Dialog
  bind:open
  scrimClickAction=""
  escapeKeyAction=""
  aria-labelledby="mandatory-title"
  aria-describedby="mandatory-content"
>
  <Title id="mandatory-title">Add weapon</Title>
  <Content id="mandatory-content">
    <Textfield id="weapon-name" style="width: 100%;" required 
    helperLine$style="width: 100%;"
    bind:value={weaponInput.weaponName}
    label="Name"></Textfield>
    <Select id="weapon-weaponType" label="Type" style="width: 100%;" required bind:value={weaponInput.weaponType}>
      <Option value={weaponInput.weaponType} />
      {#each weaponTypes as weaponType}
        <Option value={weaponType}>{weaponType}</Option>
      {/each}
    </Select>
    <Textfield id="weapon-diceCnt" style="width: 100%;" required 
    helperLine$style="width: 100%;"
    bind:value={weaponInput.diceCnt}
    label="Anzahl Würfel">1</Textfield>
    <Textfield id="weapon-diceType" style="width: 100%;" required 
    helperLine$style="width: 100%;"
    bind:value={weaponInput.diceType}
    label="Würfeltyp">6</Textfield>
    <Textfield id="weapon-staticDamage" style="width: 100%;" required 
    helperLine$style="width: 100%;"
    bind:value={weaponInput.staticDamage}
    label="Statischer Schaden">1</Textfield>
  </Content>
  <Actions>
    <Button on:click={() => addOrUpdateWeapon()}>
      <BtnLabel>Save</BtnLabel>
    </Button>
    <Button on:click={() => (open = false)}>
      <BtnLabel>Cancel</BtnLabel>
    </Button>
  </Actions>
</Dialog>

