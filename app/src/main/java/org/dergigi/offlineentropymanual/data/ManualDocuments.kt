package org.dergigi.offlineentropymanual.data

data class Attribution(
    val author: String,
    val sourceUrl: String,
)

data class ManualDocument(
    val id: String,
    val title: String,
    val subtitle: String,
    val assetFileName: String,
    val attribution: Attribution,
)

object ManualDocuments {
    private val bitbox = Attribution(
        author = "BitBox Swiss AG",
        sourceUrl = "https://bitbox.swiss/",
    )
    private val jimbojw = Attribution(
        author = "Jimbojw",
        sourceUrl = "https://jimbojw.github.io/seed-picker-solitaire/",
    )
    private val bitcoinHole = Attribution(
        author = "The Bitcoin Hole",
        sourceUrl = "https://thebitcoinhole.com/",
    )
    private val simplestBitcoinBook = Attribution(
        author = "The Simplest Bitcoin Book",
        sourceUrl = "https://thesimplestbitcoinbook.net/",
    )
    private val entropyPage = Attribution(
        author = "entropy.page",
        sourceUrl = "https://entropy.page/",
    )

    val all = listOf(
        ManualDocument(
            id = "howto",
            title = "Diceware How-To",
            subtitle = "Create a backup with dice",
            assetFileName = "BitBox_Diceware_HowTo.pdf",
            attribution = bitbox.copy(
                sourceUrl = "https://bitbox.swiss/bitbox02/BitBox_Diceware_HowTo.pdf",
            ),
        ),
        ManualDocument(
            id = "lookup",
            title = "Diceware Lookup Table",
            subtitle = "Word list for dice rolls",
            assetFileName = "BitBox_Diceware_LookupTable.pdf",
            attribution = bitbox.copy(
                sourceUrl = "https://bitbox.swiss/bitbox02/BitBox_Diceware_LookupTable.pdf",
            ),
        ),
        ManualDocument(
            id = "solitaire",
            title = "Seed Picker Solitaire",
            subtitle = "Create a backup with playing cards",
            assetFileName = "seed-picker-solitaire.pdf",
            attribution = jimbojw.copy(
                sourceUrl = "https://jimbojw.github.io/seed-picker-solitaire/seed-picker-solitaire.pdf",
            ),
        ),
        ManualDocument(
            id = "coinflip-guide",
            title = "Coin Flip Seed Guide",
            subtitle = "Create a backup with coin flips",
            assetFileName = "coin-flip-seed-guide.pdf",
            attribution = bitcoinHole.copy(
                sourceUrl = "https://thebitcoinhole.com/blog/files/coin-flip-seed-guide.pdf",
            ),
        ),
        ManualDocument(
            id = "coinflip-sheet",
            title = "Coin Flip Seed Sheet",
            subtitle = "Worksheet for coin flip seeds",
            assetFileName = "coin-flip-seed-sheet.pdf",
            attribution = bitcoinHole.copy(
                sourceUrl = "https://thebitcoinhole.com/blog/files/coin-flip-seed-sheet.pdf",
            ),
        ),
        ManualDocument(
            id = "coinflip-passphrase",
            title = "Coin Flip Passphrase Sheet",
            subtitle = "Worksheet for a coin flip passphrase",
            assetFileName = "coin-flip-passphrase-sheet.pdf",
            attribution = bitcoinHole.copy(
                sourceUrl = "https://thebitcoinhole.com/blog/files/coin-flip-passphrase-sheet.pdf",
            ),
        ),
        ManualDocument(
            id = "bip39",
            title = "BIP39 Wordlist",
            subtitle = "Word list for the coin flip guide",
            assetFileName = "bip39-wordlist.pdf",
            attribution = bitcoinHole.copy(
                sourceUrl = "https://thebitcoinhole.com/blog/files/bip39-wordlist.pdf",
            ),
        ),
        ManualDocument(
            id = "roll-workshop",
            title = "Roll Your Own Seed Workshop",
            subtitle = "Guide for D8 and D16 dice",
            assetFileName = "roll-your-own-seed-workshop.pdf",
            attribution = simplestBitcoinBook.copy(
                sourceUrl = "https://thesimplestbitcoinbook.net/wp-content/uploads/2025/03/FINAL_Roll-Your-Own-Seed-Workshop_8.5x11_03_25_2025.pdf",
            ),
        ),
        ManualDocument(
            id = "entropy-worksheet",
            title = "Seed Worksheet",
            subtitle = "Worksheet for the D8/D16 workshop",
            assetFileName = "entropy-page-worksheet.pdf",
            attribution = entropyPage.copy(
                sourceUrl = "https://entropy.page/files/worksheet.pdf",
            ),
        ),
        ManualDocument(
            id = "entropy-dictionary",
            title = "BIP39 Dictionary",
            subtitle = "Word list for the D8/D16 workshop",
            assetFileName = "entropy-page-dictionary.pdf",
            attribution = entropyPage.copy(
                sourceUrl = "https://entropy.page/files/dictionary.pdf",
            ),
        ),
    )

    fun byId(id: String): ManualDocument =
        all.first { it.id == id }

    /** Authors in first-seen order, each with their documents. */
    fun groupedByAuthor(): List<Pair<String, List<ManualDocument>>> =
        all.groupBy { it.attribution.author }
            .entries
            .map { it.key to it.value }
}
