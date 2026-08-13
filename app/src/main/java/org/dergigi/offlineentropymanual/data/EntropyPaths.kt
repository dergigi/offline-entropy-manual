package org.dergigi.offlineentropymanual.data

data class EntropyPath(
    val id: String,
    val title: String,
    val subtitle: String,
    val documentIds: List<String>,
    val showChecksumNote: Boolean = false,
) {
    val documents: List<ManualDocument>
        get() = documentIds.map(ManualDocuments::byId)
}

object EntropyPaths {
    val all = listOf(
        EntropyPath(
            id = "dice",
            title = "Dice only",
            subtitle = "BitBox Diceware seed backup",
            documentIds = listOf("howto", "lookup"),
        ),
        EntropyPath(
            id = "dice-coin",
            title = "Dice and coin",
            subtitle = "Diceware seed, coin-flip passphrase",
            documentIds = listOf("howto", "lookup", "coinflip-passphrase"),
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
        ),
        EntropyPath(
            id = "cards",
            title = "Playing cards",
            subtitle = "Seed Picker Solitaire",
            documentIds = listOf("solitaire"),
        ),
        EntropyPath(
            id = "printer-scissors",
            title = "Printer and scissors",
            subtitle = "Cut-out BIP39 word list",
            documentIds = listOf("bip39-cutout", "seedsigner-generate"),
            showChecksumNote = true,
        ),
        EntropyPath(
            id = "seedsigner",
            title = "SeedSigner",
            subtitle = "Camera, dice, or calculate checksum word",
            documentIds = listOf("seedsigner-generate", "bip39-cutout"),
            showChecksumNote = true,
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
            showChecksumNote = true,
        ),
    )

    fun byId(id: String): EntropyPath =
        all.first { it.id == id }
}
