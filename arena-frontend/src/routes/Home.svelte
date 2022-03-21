<script lang="ts">
    import Button, { Label as BtnLabel } from '@smui/button';
    import { onMount } from 'svelte';
    import {userInfo, accessToken} from '@dopry/svelte-oidc';
import Characters from './Characters.svelte';

	let socket
    let messages : string[] = ["hello"]
    onMount(() => {

    })

    let user: userInfo

    $: if ($userInfo && 'sub' in $userInfo) {
      user = $userInfo
      start(user.sub)
    }

    function start(userId: string){
        socket = new WebSocket("ws://localhost:8082/game/" + userId)
        socket.addEventListener("open", ()=> {
            console.log("Opened")
        })
        socket.addEventListener("message", (event)=> {
            messages[messages.length] = event.data;
        })
    }

    function joinWithCharacter(charId: string){
        
    }

    function close(){
        socket.close();
    }

    const joinFight = ({detail}) => {
        socket.send("join:"+ detail);
        console.debug("join fight with char "+detail);
    }

</script>

<h2>Home component</h2>
<p>
    {$userInfo.name}
</p>

<Characters on:joinFight="{joinFight}"/>


<Button on:click={() => joinWithCharacter("620ac511c233c3a1b6b6878a")}>
    <BtnLabel>Join Game</BtnLabel>
</Button>
<Button on:click={() => close()}>
    <BtnLabel>Close</BtnLabel>
</Button>
<Button on:click={() => {messages[messages.length] = "test"; console.log(messages)}}>
    <BtnLabel>test</BtnLabel>
</Button>
<div id="chatWindow">
    <ul id="messages">
      {#each messages as message}
        <li>{message}</li>
      {/each}
    </ul>
</div>

