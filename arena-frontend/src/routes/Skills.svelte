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
  import type { Skill } from '../services/skillService'; 
  import * as SkillService from '../services/skillService'; 

  let sort: keyof Skill = 'name';
  let sortDirection = 'ascending';
  let skills: Skill[] = [];
  let skillTypes = [];
  let open = false;
  let skillInput: Skill = {name: "", leadingAttribute: "", source: "", unskilled: 0, type: ""};
  let leadingAttributes: string[] = ["strength", "dexterity", "agility", "constitution", "intelligence", "magicTalent", "charisma", "look", "willpower"];

	onMount(async () => {
    skills = await SkillService.loadSkills();
    skillTypes = await SkillService.loadSkillTypes();
	});

  function handleSort() {
    skills.sort((a, b) => {
      const [aVal, bVal] = [a[sort], b[sort]][
        sortDirection === 'ascending' ? 'slice' : 'reverse'
      ]();
      if (typeof aVal === 'string' && typeof bVal === 'string') {
        return aVal.localeCompare(bVal);
      }
      return Number(aVal) - Number(bVal);
    });
    skills = skills;
  }

  function addOrUpdateSkill() {
    if(skillInput.id == null) {
      console.log("add "+JSON.stringify(skillInput));
      SkillService.addSkill(skillInput)
        .then((response) => {
          if (response.status == 201) {
            SkillService.loadSkills().then(data => (skills = data));
          }
        })
        .catch((error) => {
            console.log(error);
            SkillService.loadSkills().then(data => (skills = data));
        });
    } else {
      SkillService.updateSkill(skillInput)
      .then(response => {
        if(response.status == 204){
          SkillService.loadSkills().then(data => (skills = data));
        }
      })
      .catch(error => {
          console.log(error);
          SkillService.loadSkills().then(data => (skills = data));
      })
      console.log("update "+JSON.stringify(skillInput));
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
  {#each skills as skill }
    <Row id={skill.id}>
          <Cell>{skill.name}</Cell>
          <Cell>{skill.leadingAttribute}</Cell>
          <Cell>{skill.type}</Cell>
          <Cell numeric>{skill.unskilled}</Cell>
          <Cell>{skill.source}</Cell>
          <Cell>
            <!-- <div style="display: flex; align-items: center;">
            <IconButton class="material-icons" on:click={() => console.log("click")} size="normal">
              <Icon component={Svg} viewBox="0 0 24 24">
                <path fill="currentColor" d={pencil} />
              </Icon>
            </IconButton> -->
            <div on:click={() => {open = true; skillInput = skill}}>Edit</div>
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
                SkillService.deleteSkill(skill)
                .then(() => {SkillService.loadSkills()
                  .then(data => (skills = data))})}
              }>Delete</div>
          <!-- </div> -->
          </Cell>
    </Row>
  {/each}
  <Button on:click={() => {open = true; skillInput = {name: "", leadingAttribute: "", source: "", unskilled: 0, type: ""}}} variant="raised">
    <Label>Add skill</Label>
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
  <Title id="mandatory-title">Add skill</Title>
  <Content id="mandatory-content">
    <Textfield id="skill-name" style="width: 100%;" required 
    helperLine$style="width: 100%;"
    bind:value={skillInput.name}
    label="Name">
    </Textfield>
    <Select id="skill-type" label="Type" style="width: 100%;" required bind:value={skillInput.type}>
      <Option value={skillInput.type} />
      {#each skillTypes as skillType}
        <Option value={skillType}>{skillType}</Option>
      {/each}
    </Select>
    <Select id="skill-attribute" label="Leading attribute" style="width: 100%;" required bind:value={skillInput.leadingAttribute}>
      <Option value={skillInput.leadingAttribute} />
      {#each leadingAttributes as attr}
        <Option value={attr}>{attr}</Option>
      {/each}
    </Select>
    <Textfield id="skill-unskilled" style="width: 100%;" required
    helperLine$style="width: 100%;"
    bind:value={skillInput.unskilled}
    label="Unskilled">
    </Textfield>
    <Textfield id="skill-source" style="width: 100%;" required
    helperLine$style="width: 100%;"
    bind:value={skillInput.source}
    label="Source">
    </Textfield>
  </Content>
  <Actions>
    <Button on:click={() => addOrUpdateSkill()}>
      <BtnLabel>Save</BtnLabel>
    </Button>
    <Button on:click={() => (open = false)}>
      <BtnLabel>Cancel</BtnLabel>
    </Button>
  </Actions>
</Dialog>

<p><em>Hint:</em> Try navigating with the links below, then use your browser's back and forward buttons.</p>

