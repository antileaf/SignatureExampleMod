package me.antileaf.example.cards.alice;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import me.antileaf.example.patches.enums.CardColorEnum;
import me.antileaf.example.utils.ExampleHelper;
import me.antileaf.signature.card.AbstractSignatureCard;

public class SurpriseSpring extends AbstractSignatureCard {
	public static final String SIMPLE_NAME = SurpriseSpring.class.getSimpleName();
	public static final String ID = ExampleHelper.makeID(SIMPLE_NAME);
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	private static final int COST = 0;
	private static final int HEAL = 2;
//	private static final int ENERGY = 1;
	private static final int UPGRADE_PLUS_HEAL = 1;
	private static final int UPGRADE_PLUS_ENERGY = 1;
	
	public SurpriseSpring() {
		super(
				ID,
				cardStrings.NAME,
				ExampleHelper.getCardImgFilePath(SIMPLE_NAME),
				COST,
				cardStrings.DESCRIPTION,
				AbstractCard.CardType.SKILL,
				CardColorEnum.ALICE_MARGATROID_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.SELF
		);
		
		this.magicNumber = this.baseMagicNumber = HEAL;
//		this.secondaryMagicNumber = this.baseSecondaryMagicNumber = ENERGY;
		this.exhaust = true;
		this.tags.add(AbstractCard.CardTags.HEALING);
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

	}
	
	@Override
	public AbstractCard makeCopy() {
		return new SurpriseSpring();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
//			this.upgradeSecondaryMagicNumber(UPGRADE_PLUS_ENERGY);
			this.upgradeMagicNumber(UPGRADE_PLUS_HEAL);
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}
