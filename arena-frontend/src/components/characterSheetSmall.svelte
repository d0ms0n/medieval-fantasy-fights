<script lang="ts">
    import Button,{ Label } from '@smui/button';
    import Card,{ActionButtons,ActionIcons,Actions,Content} from '@smui/card';
    import IconButton,{ Icon } from '@smui/icon-button';
    import LayoutGrid,{ Cell } from '@smui/layout-grid';
    import { createEventDispatcher } from 'svelte';
    import type { Character } from '../types/Character';

    export let character : Character;
    const dispatch = createEventDispatcher();
    let clicked = 0;
    const submit = () => dispatch('joinFight', character.id);
</script>

<div class="card-container">
    <Card>
      <Content>
        <LayoutGrid>
            <Cell>
                <span class="attribute-label">Figur:</span><span class="attribute-value" title="Name der Figur">{character.name}</span><br />
                <span class="attribute-label">Typ:</span><span class="attribute-value">{character.profession}</span><br />
                <span class="attribute-label">Grad:</span><span class="attribute-value">{character.level}</span><br />
                <span class="attribute-label">ES:</span><span class="attribute-value">{character.experience}</span><br />
                <span class="attribute-label">EP:</span><span class="attribute-value">{character.experiencePoints}</span> <br />
            </Cell>
            <Cell>
                <span class="attribute-label">Stärke</span><span>{character.attributes.strength}</span><br />
                <span class="attribute-label">Geschick</span><span title="Geschick der Figur (1-100)">{character.attributes.dexterity}</span><br />
                <span class="attribute-label">Gewandheit</span><span >{character.attributes.agility}</span><br />
                <span class="attribute-label">Konstitution</span><span>{character.attributes.constitution}</span><br />
                <span class="attribute-label">Intelligenz</span><span>{character.attributes.intelligence}</span><br />
                <span class="attribute-label">Zaubertalent</span><span>{character.attributes.magicTalent}</span><br />
                <span class="attribute-label">Charisma</span><span>{character.attributes.charisma}</span><br />
            </Cell>
            <Cell>
                 <span class="attribute-label">Abwehr</span><span>{character.fullDefense}</span><br />
                 <span class="attribute-label">Res.Geist</span><span>{character.fullResistanceMind}</span><br />
                 <span class="attribute-label">Res.Körper</span><span>{character.fullResistanceBody}</span><br />
                 <span class="attribute-label">Bewegungsw.</span><span>{character.movement}</span><br />
                 <span class="attribute-label">LP Max</span><span>{character.lifePointsMax}</span><br />
                 <span class="attribute-label">AP Max</span><span>{character.staminaPointsMax}</span><br />
                 <span class="attribute-label">Rasse</span><span>{character.race}</span><br />
            </Cell>
    </LayoutGrid>
      </Content>
      <Actions>
        <ActionButtons>
          <Button on:click={submit}>
            <Label>Fight</Label>
          </Button>
        </ActionButtons>
        <ActionIcons>
          <IconButton
            on:click={() => clicked++}
            toggle
            aria-label="Add to favorites"
            title="Add to favorites"
          >
            <Icon class="material-icons" on>favorite</Icon>
            <Icon class="material-icons">favorite_border</Icon>
          </IconButton>
          <IconButton
            class="material-icons"
            on:click={() => clicked++}
            title="Share">share</IconButton
          >
          <IconButton
            class="material-icons"
            on:click={() => clicked++}
            title="More options">more_vert</IconButton
          >
        </ActionIcons>
      </Actions>
    </Card>
  </div>
  <br/>

<style>
    .card-container {
        font-size: 0.9em;
        /* background-color: rgb(199, 5, 5); */
    }

    .attribute-label {
        display: inline-block;
        width: 30%;
    }

    .attribute-value {
        display: inline-block;
        width: 70px;
    }
</style>