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
@file:Suppress("detekt.TooManyFunctions")

package io.pixeloutlaw.minecraft.spigot.mythicdrops

import io.pixeloutlaw.minecraft.spigot.hilt.getFromItemMeta
import io.pixeloutlaw.minecraft.spigot.hilt.getThenSetItemMeta
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * Gets all of the [ItemFlag]s from the ItemMeta.
 */
fun ItemStack.getItemFlags(): Set<ItemFlag> = getFromItemMeta { itemFlags }?.toSet() ?: emptySet()

/**
 * Sets the [ItemFlag]s on the ItemMeta.
 *
 * @param itemFlags Flags to set
 */
fun ItemStack.setItemFlags(itemFlags: Set<ItemFlag>) {
    getThenSetItemMeta { itemFlags.forEach { addItemFlags(it) } }
}

/**
 * Attempts to get the highest enchantment off the ItemStack. Returns null if no enchantments are present.
 */
fun ItemStack.getHighestEnchantment(): Enchantment? {
    return enchantments.maxByOrNull { it.value }?.key
}
