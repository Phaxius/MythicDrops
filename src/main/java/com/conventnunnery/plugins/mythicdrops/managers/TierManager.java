/*
 * Copyright (C) 2013 Richard Harrah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.conventnunnery.plugins.mythicdrops.managers;

import com.conventnunnery.plugins.mythicdrops.MythicDrops;
import com.conventnunnery.plugins.mythicdrops.api.tiers.Tier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class TierManager {

    private final MythicDrops plugin;
    private final Set<Tier> tiers;

    public TierManager(MythicDrops plugin) {
        this.plugin = plugin;
        tiers = new HashSet<Tier>();
    }

    public void debugTiers() {
        List<String> tierNames = new ArrayList<String>();
        for (Tier t : tiers) {
            tierNames.add(t.getTierName() + " (" + t.getChanceToSpawnOnAMonster() + ")");
        }
        getPlugin().getDebugger().debug(Level.INFO, "Loaded tiers: " + tierNames.toString().replace("[",
                "").replace("]", ""));
    }

    public MythicDrops getPlugin() {
        return plugin;
    }

    public Set<Tier> getTiers() {
        return tiers;
    }
}
