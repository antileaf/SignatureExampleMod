package me.antileaf.example.cards.maki;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import me.antileaf.example.patches.enums.CardColorEnum;
import me.antileaf.example.utils.ExampleHelper;
import me.antileaf.signature.card.AbstractSignatureCard;

public class PaintballLob extends AbstractSignatureCard {
	public static final String SIMPLE_NAME = PaintballLob.class.getSimpleName();
	public static final String ID = ExampleHelper.makeID(SIMPLE_NAME);
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	private static final int COST = 2;
	private static final int DAMAGE = 5;
	private static final int MAGIC = 1;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int UPGRADE_PLUS_MAGIC = 1;

	public PaintballLob() {
		super(
				ID,
				cardStrings.NAME,
				ExampleHelper.getCardImgFilePath(SIMPLE_NAME),
				COST,
				cardStrings.DESCRIPTION,
				CardType.ATTACK,
				CardColorEnum.MAKI_COLOR,
				CardRarity.BASIC,
				CardTarget.ENEMY
		);

		this.damage = this.baseDamage = DAMAGE;
		this.magicNumber = this.baseMagicNumber = MAGIC;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

	}

	@Override
	public AbstractCard makeCopy() {
		return new PaintballLob();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(UPGRADE_PLUS_DMG);
			this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
			this.initializeDescription();
		}
	}
}
