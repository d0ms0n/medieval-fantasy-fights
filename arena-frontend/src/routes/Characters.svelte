<script lang="ts">
    // import DataTable,{ Body,Cell,Head,Label,Row } from '@smui/data-table';
    // import { onMount } from 'svelte';
    // import IconButton, { Icon } from '@smui/icon-button';
    import Button, { Label as BtnLabel } from '@smui/button';
    import {push} from 'svelte-spa-router'

    // let sort: keyof Character = 'name';
    // let sortDirection = 'ascending';
    // let characters: Character[] = [];
    // let open = false;

	// onMount(async () => {
    //     console.log("Home");
	// });

    // function handleSort() {
    //     characters.sort((a, b) => {
    //     const [aVal, bVal] = [a[sort], b[sort]][
    //         sortDirection === 'ascending' ? 'slice' : 'reverse'
    //     ]();
    //     if (typeof aVal === 'string' && typeof bVal === 'string') {
    //         return aVal.localeCompare(bVal);
    //     }
    //     return Number(aVal) - Number(bVal);
    //     });
    //     characters = characters;
    // }

</script>

<h2>Charaktere</h2>

<!-- <DataTable sortable
             bind:sort
             bind:sortDirection
             on:SMUIDataTable:sorted={handleSort}
             table$aria-label="Skill list"
             style="width: 100%;">
  <Head>
    <Row>
      <Cell columnId="name">
        <IconButton class="material-icons">arrow_upward</IconButton>
        <Label>Name</Label>
      </Cell>
      <Cell columnId="leadingAttribute">
        <IconButton class="material-icons">arrow_upward</IconButton>
        <Label>Leading Attribute</Label>
      </Cell>
      <Cell columnId="type">
        <IconButton class="material-icons">arrow_upward</IconButton>
        <Label>Type</Label>
      </Cell>
      <Cell numeric columnId="unskilled">
        <IconButton class="material-icons">arrow_upward</IconButton>
        <Label>Unskilled</Label>
       </Cell>
      <Cell columnId="source">
        <IconButton class="material-icons">arrow_upward</IconButton>
        <Label>Source</Label></Cell>
      <Cell></Cell>
    </Row>
  </Head>
  <Body>
  {#each characters as character }
    <Row id={character.id}>
          <Cell>{character.level}</Cell>
          <Cell>{character.name}</Cell>
          <Cell>{character.profession}</Cell>
    </Row>
  {/each}
  <Button on:click={() => {open = true}} variant="raised">
    <BtnLabel>Add skill</BtnLabel>
  </Button>

  </Body>
</DataTable> -->
<!-- <Button on:click={() => {open = true}} variant="raised">
    <BtnLabel>Import Character</BtnLabel>
</Button> -->

<Button on:click={() => push('/characters/import')}  variant="raised">
    <BtnLabel>Import Character</BtnLabel>
</Button>