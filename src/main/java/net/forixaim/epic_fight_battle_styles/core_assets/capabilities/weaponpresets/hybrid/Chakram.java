package net.forixaim.epic_fight_battle_styles.core_assets.capabilities.weaponpresets.hybrid;

import com.mojang.datafixers.util.Pair;
import net.forixaim.epic_fight_battle_styles.core_assets.animations.BattleAnimations;
import net.forixaim.epic_fight_battle_styles.core_assets.capabilities.BattleStyleCategories;
import net.forixaim.epic_fight_battle_styles.core_assets.capabilities.EFBSWeaponCapability;
import net.forixaim.epic_fight_battle_styles.core_assets.capabilities.styles.HeroStyles;
import net.forixaim.epic_fight_battle_styles.core_assets.capabilities.styles.ImperatriceLuminelleStyles;
import net.forixaim.epic_fight_battle_styles.initialization.registry.SkillRegistry;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

import static net.forixaim.epic_fight_battle_styles.core_assets.capabilities.weaponpresets.HelperFunctions.offHandItem;
import static net.forixaim.epic_fight_battle_styles.core_assets.capabilities.weaponpresets.HelperFunctions.skillCheck;

public class Chakram
{
	public static Function<LivingEntityPatch<?>, Style> styleProvider = (entityPatch) ->
	{
		if (offHandItem(entityPatch, BattleStyleCategories.CHAKRAM))
		{
			return CapabilityItem.Styles.TWO_HAND;
		}
		else
		{
			return CapabilityItem.Styles.ONE_HAND;
		}
	};

	public static Function<LivingEntityPatch<?>, Boolean> comboPredicator = (entityPatch) ->
			offHandItem(entityPatch, BattleStyleCategories.CHAKRAM);

	public static Function<Pair<Style, EFBSWeaponCapability.Builder>, EFBSWeaponCapability.Builder> defaultOneHandAttackCycle = (main) ->
	{
		EFBSWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.livingMotionModifier(style, LivingMotions.IDLE, BattleAnimations.SINGLE_CHAKRAM_IDLE);
		builder.livingMotionModifier(style, LivingMotions.WALK, BattleAnimations.SINGLE_CHAKRAM_WALK);
		builder.newStyleCombo(style,
				BattleAnimations.SINGLE_CHAKRAM_AUTO_1,
				BattleAnimations.SINGLE_CHAKRAM_AUTO_2,
				BattleAnimations.SINGLE_CHAKRAM_DASH_ATTACK, BattleAnimations.SINGLE_CHAKRAM_AIR_SLASH
		);
		builder.innateSkill(style, (itemStack) -> SkillRegistry.PRECISION_VERTICAL);
		return builder;
	};
}
