public class MineOre imlements IAbility
{  
    @Override
    public void run(Event event) {
    if (event instanceof BlockBreakEvent)
    {
        String uuid = player.getUniqueId().toString();
        String name = "mineore";
        if (Cooldown.isCooledDown(name, uuid))
        {
            int duration = 5;
            int level = Races.getMGPlayer(player).getAbilityLevel(getType());
            if(level > 1) duration = 8;
            if(level > 2) duration = 10;

            int endHealth = 4 - level;

            Cooldown.newCoolDown(name, uuid, 360);
        } else {
            ChatUtil.sendString(player, "You have to wait another " + Cooldown.getRemaining(name, uuid) + " seconds to use this again.");
        }
        }
    }

    @Override
    public void run(Player player)
    {

    }

    @Override
    public String getName() {
        return "Mine Ore";
    }

    @Override
    public AbilityType getType() {
        return AbilityType.MINE_ORE;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public Material getDisplayItem() {
        return Material.DIAMOND_ORE;
    }

    @Override
    public int getPrice(int level) {
        return 2;
    }

    @Override
    public AbilityGroup getGroup() {
        return AbilityGroup.PASSIVE;
    }

    @Override
    public int getCooldown(int level) {
        return 120;
    }

    @Override
    public List<RaceType> getRaces() {
        return Lists.newArrayList(RaceType.DWARF);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public String[] getDescription(int level) {
        String[] desc;

        switch (level) {
            case 1:
                desc = new String[]{"Have haste for 5 seconds."};
                break;
            case 2:
                desc = new String[]{"Your haste lasts for 8 seconds."};
                break;
            case 3:
                desc = new String[]{"Your haste lasts for 10 seconds.", "Haste is now a level higher."};
                break;
            default:
                desc = new String[]{"This is an error!"};
                break;

        }
        return desc;
    }
