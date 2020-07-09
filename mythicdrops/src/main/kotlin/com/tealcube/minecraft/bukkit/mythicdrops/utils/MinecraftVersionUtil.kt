/*
 * This file is part of MythicDrops, licensed under the MIT License.
 *
 * Copyright (C) 2020 Richard Harrah
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.tealcube.minecraft.bukkit.mythicdrops.utils

/**
 * Utility for determining the version of Bukkit being used.
 */
object MinecraftVersionUtil {
    private val hasPiglinClass = try {
        Class.forName("org.bukkit.entity.Piglin")
        true
    } catch (ex: ClassNotFoundException) {
        false
    }
    private val hasBeeClass = try {
        Class.forName("org.bukkit.entity.Bee")
        true
    } catch (ex: ClassNotFoundException) {
        false
    }
    private val hasGrindstoneInventoryClass = try {
        Class.forName("org.bukkit.inventory.GrindstoneInventory")
        true
    } catch (ex: ClassNotFoundException) {
        false
    }
    private val hasDamageableClass = try {
        Class.forName("org.bukkit.inventory.meta.Damageable")
        true
    } catch (ex: ClassNotFoundException) {
        false
    }

    /**
     * Returns true if the Piglin interface exists, which means we're in 1.16+.
     */
    fun isAtLeastMinecraft116(): Boolean = hasPiglinClass

    /**
     * Returns true if the Bee interface exists, which means we're in 1.15+.
     */
    fun isAtLeastMinecraft115(): Boolean = hasBeeClass

    /**
     * Returns true if the GrindstoneInventory interface exists, which means we're in 1.14+.
     */
    fun isAtLeastMinecraft114(): Boolean = hasGrindstoneInventoryClass

    /**
     * Returns true if the Damageable interface exists, which means we're in 1.13+.
     */
    fun isAtLeastMinecraft113(): Boolean = hasDamageableClass
}
