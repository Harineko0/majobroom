package net.fabricmc.majobroom;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.majobroom.armors.ArmorFabric;
import net.fabricmc.majobroom.armors.BaseArmor;
import net.fabricmc.majobroom.config.MajoBroomConfig;
import net.fabricmc.majobroom.entity.BroomEntity;
import net.fabricmc.majobroom.items.BroomItem;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class MajoBroom implements ModInitializer {
	public static final String MODID = "majobroom";
	public static final ItemGroup majoGroup = FabricItemGroup.builder()
		.icon(() -> new ItemStack(MajoBroom.broomItem)).build();
	

	//盔甲部分
	public static final ArmorMaterial FABRIC_ARMOR = new ArmorFabric();
	public static final Item broomItem = new BroomItem(new Item.Settings().maxCount(1));
//	group(MajoBroom.majoGroup)
	public static final Item majoCloth = new BaseArmor(FABRIC_ARMOR, ArmorItem.Type.CHESTPLATE);
//	public static final Item majoStocking = new BaseArmor(FABRIC_ARMOR, EquipmentSlot.FEET);
	public static final Item majoHat = new BaseArmor(FABRIC_ARMOR, ArmorItem.Type.HELMET);


	//ItemGroup
	static {
		// REVIEW: Is ItemGroups.NATURAL appropriate?
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(MajoBroom::setItemGroup);
	}
	
	protected static void setItemGroup(FabricItemGroupEntries entries) {
		entries.add(broomItem);
		entries.add(majoHat);
		entries.add(majoCloth);
		//entries.add(majoStocking);
	}

	//实体
	public static final EntityType<BroomEntity> BROOM_ENTITY_ENTITY_TYPE = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier(MODID, "majo_broom"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BroomEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);

	@Override
	public void onInitialize() {

		Registry.register(Registries.ITEM, new Identifier(MODID, "broom_item"), broomItem);
		Registry.register(Registries.ITEM, new Identifier(MODID, "majo_cloth"), majoCloth);
//		Registry.register(Registries.ITEM, new Identifier(MODID, "stocking"), majoStocking);
		Registry.register(Registries.ITEM, new Identifier(MODID, "majo_hat"), majoHat);
//		FabricDefaultAttributeRegistry.register(CUBE, CubeEntity);
//		Registry.register(Registries.ITEM, new Identifier(MODID, "fabric_helmet"), new BaseArmor(FABRIC_ARMOR, EquipmentSlot.HEAD));
		MajoBroomConfig.getInstance();

	}



	public static void main(String[] args) {
		System.out.println("sda");
	}
}
