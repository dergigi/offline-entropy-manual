package org.dergigi.offlineentropymanual.data

data class ManualDocument(
    val id: String,
    val title: String,
    val subtitle: String,
    val assetFileName: String,
)

object ManualDocuments {
    val all = listOf(
        ManualDocument(
            id = "howto",
            title = "Diceware How-To",
            subtitle = "Create a backup with dice",
            assetFileName = "BitBox_Diceware_HowTo.pdf",
        ),
        ManualDocument(
            id = "lookup",
            title = "Diceware Lookup Table",
            subtitle = "Word list for dice rolls",
            assetFileName = "BitBox_Diceware_LookupTable.pdf",
        ),
        ManualDocument(
            id = "solitaire",
            title = "Seed Picker Solitaire",
            subtitle = "Create a backup with playing cards",
            assetFileName = "seed-picker-solitaire.pdf",
        ),
        ManualDocument(
            id = "coinflip-guide",
            title = "Coin Flip Seed Guide",
            subtitle = "Create a backup with coin flips",
            assetFileName = "coin-flip-seed-guide.pdf",
        ),
        ManualDocument(
            id = "coinflip-sheet",
            title = "Coin Flip Seed Sheet",
            subtitle = "Worksheet for coin flip seeds",
            assetFileName = "coin-flip-seed-sheet.pdf",
        ),
        ManualDocument(
            id = "coinflip-passphrase",
            title = "Coin Flip Passphrase Sheet",
            subtitle = "Worksheet for a coin flip passphrase",
            assetFileName = "coin-flip-passphrase-sheet.pdf",
        ),
        ManualDocument(
            id = "bip39",
            title = "BIP39 Wordlist",
            subtitle = "Word list for the coin flip guide",
            assetFileName = "bip39-wordlist.pdf",
        ),
    )

    fun byId(id: String): ManualDocument =
        all.first { it.id == id }
}
