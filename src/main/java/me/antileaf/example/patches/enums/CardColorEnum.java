package me.antileaf.example.patches.enums;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class CardColorEnum {
	@SpireEnum
	public static AbstractCard.CardColor MAKI_COLOR;

	@SpireEnum(name = "Alice Margatroid")
	public static AbstractCard.CardColor ALICE_MARGATROID_COLOR;

	public static class LibraryEnum {
		@SpireEnum
		public static CardLibrary.LibraryType MAKI_COLOR;

		@SpireEnum(name = "Alice Margatroid")
		public static CardLibrary.LibraryType ALICE_MARGATROID_COLOR;
	}
}
