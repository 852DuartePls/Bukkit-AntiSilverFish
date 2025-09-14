# AntiSilverFish

**Do you hate silverfish?**

Well, I do, but this plugin wasn’t born from pure hatred.
Minecraft 1.21 added the Infestation potion, where players,
with just one well-aimed splash or arrow, can turn a base into swiss cheese if nobody’s watching.  

This is a very straightforward plugin that stops silverfish from spawning inside infested blocks,
from re-entering blocks, and optionally disables the Infestation potion effect entirely.\
So, This plugin gives server owners three simple toggles to keep that chaos in check:

1. Block Silverfish that try to spawn from Infested Blocks.
2. Stop Silverfish from re-entering into Blocks.
3. Disable the Infestation *effect* itself (potions, lingering potions and arrows)

---

### Requirements
- Java 21 or newer
- Any Bukkit-based server
- A permissions plugin (only needed if you want to give non-ops access to `/antisilverfish reload`)

### Installation / Usage
1. Drop the jar into the `plugins` folder and start/restart the server.
2. Edit `plugins/AntiSilverFish/config.yml` to taste (all options are enabled by default).
3. Run `/antisilverfish reload` to apply changes without a restart.

### Commands
`/antisilverfish reload` – reloads the configuration.  
Permission: `antisilverfish.admin`
