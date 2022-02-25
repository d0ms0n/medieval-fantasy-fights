// Components
import Home from './routes/Home.svelte'
import Name from './routes/Name.svelte'
import Wild from './routes/Wild.svelte'
import NotFound from './routes/NotFound.svelte'
import Skills from './routes/Skills.svelte'
import Weapons from './routes/Weapons.svelte'
import Characters from './routes/Characters.svelte'
import CharacterImporter from './routes/CharacterImporter.svelte'

// Export the route definition object
export default {
    // Exact path
    '/': Home,

    '/skills': Skills,

    '/weapons': Weapons,

    '/characters': Characters,
    '/characters/import': CharacterImporter,

    // Using named parameters, with last being optional
    '/hello/:first/:last?': Name,

    // Wildcard parameter
    // Included twice to match both `/wild` (and nothing after) and `/wild/*` (with anything after)
    '/wild': Wild, 
    '/wild/*': Wild,

    // Catch-all, must be last
    '*': NotFound,
}