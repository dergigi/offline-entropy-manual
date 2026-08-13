package org.dergigi.offlineentropymanual.data

data class EntropyPath(
    val id: String,
    val title: String,
    val subtitle: String,
    val documentIds: List<String>,
    val beforeYouBegin: List<String>,
) {
    val documents: List<ManualDocument>
        get() = documentIds.map(ManualDocuments::byId)
}

object EntropyPaths {
    // Adapted from BitBox Diceware, The Bitcoin Hole coin-flip guide, and Seed Picker Solitaire.
    private val privacyChecks = listOf(
        "Work alone in a private room.",
        "No phones or electronics in the room.",
        "Cover cameras and unplug mics.",
        "Do not say numbers or words out loud.",
    )

    private val diceChecks = listOf(
        "Use proper dice.",
        "Roll them properly.",
    )

    private val coinChecks = listOf(
        "Flip from your thumb onto the table.",
        "Vary the height and force.",
    )

    private val cardChecks = listOf(
        "Use an ordinary deck.",
        "Shuffle thoroughly before you start.",
    )

    val all = listOf(
        EntropyPath(
            id = "dice",
            title = "Dice only",
            subtitle = "BitBox Diceware seed backup",
            documentIds = listOf("howto", "lookup"),
            beforeYouBegin = diceChecks + privacyChecks,
        ),
        EntropyPath(
            id = "dice-coin",
            title = "Dice and coin",
            subtitle = "Diceware seed, coin-flip passphrase",
            documentIds = listOf("howto", "lookup", "coinflip-passphrase"),
            beforeYouBegin = diceChecks + coinChecks + privacyChecks,
        ),
        EntropyPath(
            id = "coin",
            title = "Coin only",
            subtitle = "Coin-flip seed and passphrase",
            documentIds = listOf(
                "coinflip-guide",
                "coinflip-sheet",
                "coinflip-passphrase",
                "bip39",
            ),
            beforeYouBegin = coinChecks + privacyChecks,
        ),
        EntropyPath(
            id = "cards",
            title = "Playing cards",
            subtitle = "Seed Picker Solitaire",
            documentIds = listOf("solitaire"),
            beforeYouBegin = cardChecks + privacyChecks,
        ),
        EntropyPath(
            id = "d8-d16",
            title = "8-sided and 16-sided dice",
            subtitle = "Roll Your Own Seed workshop",
            documentIds = listOf(
                "roll-workshop",
                "entropy-worksheet",
                "entropy-dictionary",
            ),
            beforeYouBegin = diceChecks + privacyChecks,
        ),
        EntropyPath(
            id = "printer-scissors",
            title = "Printer and scissors",
            subtitle = "Cut-out BIP39 word list",
            documentIds = listOf("bip39-cutout"),
            beforeYouBegin = privacyChecks,
        ),
    )

    fun byId(id: String): EntropyPath =
        all.first { it.id == id }
}
