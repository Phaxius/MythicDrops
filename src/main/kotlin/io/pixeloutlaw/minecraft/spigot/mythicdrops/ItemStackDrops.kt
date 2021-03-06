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
package io.pixeloutlaw.minecraft.spigot.mythicdrops

import com.tealcube.minecraft.bukkit.mythicdrops.api.enchantments.CustomEnchantmentRegistry
import com.tealcube.minecraft.bukkit.mythicdrops.api.items.CustomItem
import com.tealcube.minecraft.bukkit.mythicdrops.api.items.CustomItemManager
import com.tealcube.minecraft.bukkit.mythicdrops.api.tiers.Tier
import com.tealcube.minecraft.bukkit.mythicdrops.api.tiers.TierManager
import com.tealcube.minecraft.bukkit.mythicdrops.utils.ChatColorUtil
import com.tealcube.minecraft.bukkit.mythicdrops.utils.MinecraftVersions
import io.pixeloutlaw.minecraft.spigot.hilt.getDisplayName
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack

/**
 * Attempts to get the custom item from this ItemStack that matches the custom items available in the CustomItemManager.
 */
fun ItemStack.getCustomItem(
    customItemManager: CustomItemManager,
    customEnchantmentRegistry: CustomEnchantmentRegistry,
    disableLegacyItemCheck: Boolean
): CustomItem? = getCustomItem(customItemManager.get(), customEnchantmentRegistry, disableLegacyItemCheck)

/**
 * Attempts to get the custom item from this ItemStack that matches the given collection of custom items.
 */
fun ItemStack.getCustomItem(
    customItems: Collection<CustomItem>,
    customEnchantmentRegistry: CustomEnchantmentRegistry,
    disableLegacyItemCheck: Boolean
): CustomItem? {
    val fromPersistentData = getCustomItemFromItemStackPersistentData(this, customItems)
    // we only perform the ItemStack similarity check if disableLegacyItemCheck is false AND we are not on 1.16+
    val canPerformLegacyItemCheck = !disableLegacyItemCheck && !MinecraftVersions.isAtLeastNewerMinecraft116
    return if (canPerformLegacyItemCheck && fromPersistentData == null) {
        getCustomItemFromItemStackSimilarity(
            this,
            customItems,
            customEnchantmentRegistry
        )
    } else {
        fromPersistentData
    }
}

/**
 * Attempts to get the tier from this ItemStack that matches the tiers available in the TierManager.
 *
 * @param tierManager Tier Manager
 */
fun ItemStack.getTier(tierManager: TierManager, disableLegacyItemCheck: Boolean): Tier? =
    getTier(tierManager.get(), disableLegacyItemCheck)

/**
 * Attempts to get the tier from this ItemStack that matches the given collection of tiers.
 *
 * @param tiers tiers to choose from
 */
fun ItemStack.getTier(tiers: Collection<Tier>, disableLegacyItemCheck: Boolean): Tier? {
    val fromPersistentData = getTierFromItemStackPersistentData(this, tiers)
    // we only perform the ItemStack similarity check if disableLegacyItemCheck is false AND we are not on 1.16+
    val canPerformLegacyItemCheck = !disableLegacyItemCheck && !MinecraftVersions.isAtLeastNewerMinecraft116
    return if (canPerformLegacyItemCheck && fromPersistentData == null) {
        getTierFromItemStackDisplayName(this, tiers)
    } else {
        fromPersistentData
    }
}

private fun getCustomItemFromItemStackPersistentData(
    itemStack: ItemStack,
    customItems: Collection<CustomItem>
): CustomItem? {
    return itemStack.getPersistentDataString(mythicDropsCustomItem)
        ?.let { customItemName -> customItems.find { it.name == customItemName } }
}

private fun getTierFromItemStackPersistentData(itemStack: ItemStack, tiers: Collection<Tier>): Tier? {
    return itemStack.getPersistentDataString(mythicDropsTier)?.let { tierName -> tiers.find { it.name == tierName } }
}

private fun getTierFromItemStackDisplayName(itemStack: ItemStack, tiers: Collection<Tier>): Tier? {
    return itemStack.getDisplayName()?.let { displayName ->
        val firstChatColor = ChatColorUtil.getFirstColor(displayName)
        val colors = ChatColor.getLastColors(displayName)
        val lastChatColor = if (colors.contains(ChatColor.COLOR_CHAR)) {
            ChatColor.getByChar(colors.substring(1, 2))
        } else {
            null
        }
        if (firstChatColor == null || lastChatColor == null || firstChatColor == lastChatColor) {
            null
        } else {
            tiers.find { it.displayColor == firstChatColor && it.identifierColor == lastChatColor }
        }
    }
}

private fun getCustomItemFromItemStackSimilarity(
    itemStack: ItemStack,
    customItems: Collection<CustomItem>,
    customEnchantmentRegistry: CustomEnchantmentRegistry
): CustomItem? {
    return customItems.find { it.toItemStack(customEnchantmentRegistry).isSimilar(itemStack) }
}
