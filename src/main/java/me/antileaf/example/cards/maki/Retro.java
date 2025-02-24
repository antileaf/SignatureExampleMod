package me.antileaf.example.cards.maki;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import me.antileaf.example.patches.enums.CardColorEnum;
import me.antileaf.example.utils.ExampleHelper;
import me.antileaf.signature.card.AbstractSignatureCard;
import me.antileaf.signature.utils.SignatureHelper;

public class Retro extends AbstractSignatureCard {
	public static final String SIMPLE_NAME = Retro.class.getSimpleName();
	public static final String ID = ExampleHelper.makeID(SIMPLE_NAME);
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	private static final int COST = 1;
	private static final int MAGIC = 2;
	private static final int UPGRADE_PLUS_MAGIC = 1;

	public Retro() {
		super(
				ID,
				cardStrings.NAME,
				ExampleHelper.getCardImgFilePath(SIMPLE_NAME),
				COST,
				cardStrings.DESCRIPTION,
				CardType.POWER,
				CardColorEnum.MAKI_COLOR,
				CardRarity.UNCOMMON,
				CardTarget.SELF
		);

		this.magicNumber = this.baseMagicNumber = MAGIC;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

	}

	@Override
	public AbstractCard makeCopy() {
		return new Retro();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
			this.initializeDescription();

			if (!SignatureHelper.isUnlocked(this.cardID)) {
				SignatureHelper.unlock(this.cardID, true);
				CardCrawlGame.sound.play("UNLOCK_PING");
			}
		}
	}
}
