package io.github.bytelab.wormholes;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class WormholeManager {

    public static List<Wormhole> wormholes = new ArrayList<Wormhole>();

    public static void init() {
        (new UpdateTask()).runTaskTimer(Main.getInstance(), 0, 2);
    }

    private static class UpdateTask extends BukkitRunnable {

        @Override
        public void run() {
            //Iterate through all wormholes and perform update.

            for (Wormhole wormhole : wormholes) {
                wormhole.updateInWorld();
            }
        }
    }


}
