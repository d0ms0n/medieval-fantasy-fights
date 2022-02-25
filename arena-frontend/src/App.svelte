
<script lang="ts">
	// Import the router component
	// Normally, this would be: `import Router from 'svelte-spa-router'`
	import Router from 'svelte-spa-router'
	// Import the list of routes
	import routes from './routes'
	</script>

<h1>Midgard Arena</h1>
<h2>Menu</h2>

<!-- Navigation links -->
<ul>
    <li><a href="#/">Home</a></li>
    <li><a href="#/skills">Skills verwalten</a></li>
    <li><a href="#/weapons">Waffen verwalten</a></li>
    <li><a href="#/characters">Charaktere verwalten</a></li>
    <li><a href="#/hello/svelte">Say hi!</a></li>
    <li><a href="#/wild/card">Wildcard route</a></li>
    <li><a href="#/does/not/exist">Not found</a></li>
</ul>

<!-- Show the router -->
<Router {routes} />

