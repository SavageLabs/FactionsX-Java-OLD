package io.illyria.factionsx.utils.hooks;

import io.illyria.factionsx.internal.FactionsBootstrap;
import org.bukkit.Bukkit;
import org.dynmap.DynmapAPI;
import org.dynmap.markers.MarkerSet;

public class DynmapHook {

    // TO WRITE !! We can do it as an external plugin too, in order to more easily keep up with API changes

    public static DynmapAPI dapi = null;
    public static MarkerSet markerset = null;
    private static String markersetId = "io.illyria.factionsx.markerset";
    // TODO: Make label configurable
    private static String label = "Factions";

    public static boolean setup(FactionsBootstrap plugin) {
        dapi = (DynmapAPI) Bukkit.getServer().getPluginManager().getPlugin("dynmap");
        if (dapi == null) {
            return false;
        }
        markerset = dapi.getMarkerAPI().createMarkerSet(markersetId, label, dapi.getMarkerAPI().getMarkerIcons(), false);
        return true;
    }

    // TODO: write dynmap integration (this will be painful to do)

}
