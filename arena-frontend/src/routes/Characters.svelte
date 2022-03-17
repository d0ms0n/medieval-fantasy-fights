<script lang="ts">
  // import DataTable,{ Body,Cell,Head,Label,Row } from '@smui/data-table';
  import { accessToken, userInfo } from "@dopry/svelte-oidc";
  // import IconButton, { Icon } from '@smui/icon-button';
  import Button, { Label as BtnLabel } from "@smui/button";
  import Dialog, { Actions, Content, Title } from "@smui/dialog";
  import { Cell } from "@smui/layout-grid";
  import Select, { Option } from "@smui/select";
  import Textfield from "@smui/textfield";
  import { onMount } from "svelte";
  import DragDropList from "svelte-dragdroplist";
  import CharacterSheetSmall from "../components/characterSheetSmall.svelte";
  import type { Character, CharacterInput } from "../services/characterService";
  import * as CharacterService from "../services/characterService";

  // let sort: keyof Character = 'name';
  // let sortDirection = 'ascending';
  let attributes = [
    "strength",
    "dexterity",
    "agility",
    "constitution",
    "intelligence",
    "charisma",
    "magicTalent",
  ];
  let professions = [];
  let characters: Character[] = [];
  let characterInput: CharacterInput = {
    name: "",
    profession: "",
    attributeOrder: attributes,
  };
  let open = false;
  let token;

  onMount(async () => {
    CharacterService.loadProfessionTypes().then((data) => (professions = data));
  });

  $: if ($accessToken && "sub" in $userInfo) {
    token = $accessToken;
    CharacterService.loadCharacters(token).then((data) => (characters = data));
  }
</script>

<h2>Charaktere</h2>

{#each characters as character, i}
  <CharacterSheetSmall bind:character />
{/each}

<Button on:click={() => (open = true)} variant="raised">
  <BtnLabel>Create Character</BtnLabel>
</Button>

<Dialog
  bind:open
  fullscreen
  aria-labelledby="mandatory-title"
  aria-describedby="mandatory-content"
>
  <!-- <LayoutGrid> -->
  <Title id="mandatory-title">Create character</Title>
  <Content id="mandatory-content">
    <Cell span={12}>
      <Textfield
        id="character-name"
        style="width: 100%;"
        required
        helperLine$style="width: 100%;"
        bind:value={characterInput.name}
        label="Name"
      />
    </Cell>
    <Cell span={12}>
      <Select
        id="character-profession"
        label="Type"
        style="width: 100%;"
        required
        bind:value={characterInput.profession}
      >
        <Option value={characterInput.profession} />
        {#each professions as profession}
          <Option value={profession}>{profession}</Option>
        {/each}
      </Select>
    </Cell>
    <Cell span={12}>
      <DragDropList bind:data={characterInput.attributeOrder} />
    </Cell>
  </Content>
  <Actions>
    <Button
      on:click={() => CharacterService.createCharacter(token, characterInput)}
    >
      <BtnLabel>Save</BtnLabel>
    </Button>
    <Button on:click={() => (open = false)}>
      <BtnLabel>Cancel</BtnLabel>
    </Button>
  </Actions>
  <!-- </LayoutGrid> -->
</Dialog>

<style>
  :global(.dragdroplist) {
    background-color: darkgrey;
    z-index: 7;
  } /* entire component */
  :global(.dragdroplist > .list > div.item) {
    background-color: grey;
    color: brown;
  } /* list item */

  :global(.demo-cell) {
    height: 60px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: var(--mdc-theme-secondary, #333);
    color: var(--mdc-theme-on-secondary, #fff);
  }
</style>
