<script>
	import { onMount } from 'svelte';
	import DataTable, { Head, Body, Row, Cell, Label, SortValue, } from '@smui/data-table';
  import IconButton from '@smui/icon-button';

  // type Skill = {
  //   id: number;
  //   name: string;
  //   leadingAttribute: string;
  //   type: string;
  //   unskilled: number;
  //   source: string;
  // };

  let sort = 'name';
  let sortDirection = 'ascending';
  let skills = [];
	onMount(async () => {
		fetch("http://localhost:8082/skills")
            .then(response => response.json())
            .then(data => {
                    console.log(data);
                    skills = data;
            }).catch(error => {
                console.log(error);
                return [];
            });
	});

  function handleSort() {
    items.sort((a, b) => {
      const [aVal, bVal] = [a[sort], b[sort]][
        sortDirection === 'ascending' ? 'slice' : 'reverse'
      ]();
      if (typeof aVal === 'string' && typeof bVal === 'string') {
        return aVal.localeCompare(bVal);
      }
      return Number(aVal) - Number(bVal);
    });
    items = items;
  }
</script>

<h2>Home component</h2>

<p>
    This sample shows how to set up the router with minimum functionality. <br/>
</p>

<DataTable sortable
             bind:sort
             bind:sortDirection
             on:SMUIDataTable:sorted={handleSort}
             table$aria-label="User list"
             style="width: 100%;">
  <Head>
    <Row>
      <Cell numeric columnId="name">
        <IconButton class="material-icons">arrow_upward</IconButton>
                <Label>Name</Label>
      </Cell>
      <Cell>Leading Attribute</Cell>
      <Cell>Type</Cell>
      <Cell numeric>Unskilled</Cell>
      <Cell>Source</Cell>
    </Row>
  </Head>
  <Body>
  {#each skills as skill }
    <Row>
          <Cell>{skill.name}</Cell>
          <Cell>{skill.leadingAttribute}</Cell>
          <Cell>{skill.type}</Cell>
          <Cell numeric>{skill.unskilled}</Cell>
          <Cell>{skill.source}</Cell>
    </Row>
  {/each}
  </Body>
</DataTable>

<p><em>Hint:</em> Try navigating with the links below, then use your browser's back and forward buttons.</p>

