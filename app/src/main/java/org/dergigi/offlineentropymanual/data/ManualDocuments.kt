package org.dergigi.offlineentropymanual.data

data class ContentLicense(
    val label: String,
    val url: String? = null,
)

data class Attribution(
    val author: String,
    val websiteUrl: String,
    val documentUrl: String,
    val license: ContentLicense,
)

data class ManualDocument(
    val id: String,
    val title: String,
    val subtitle: String,
    val assetFileName: String,
    val attribution: Attribution,
)

object Licenses {
    val CcBySa40 = ContentLicense(
        label = "CC BY-SA 4.0",
        url = "https://creativecommons.org/licenses/by-sa/4.0/",
    )
    val CcByNc = ContentLicense(
        label = "CC BY-NC",
        url = "https://creativecommons.org/licenses/by-nc/4.0/",
    )
    val Apache20 = ContentLicense(
        label = "Apache-2.0",
        url = "https://www.apache.org/licenses/LICENSE-2.0",
    )
    val Mit = ContentLicense(
        label = "MIT",
        url = "https://opensource.org/licenses/MIT",
    )
    val AuthorsTerms = ContentLicense(label = "Author's terms")
}

object ManualDocuments {
    private fun bitbox(documentUrl: String) = Attribution(
        author = "BitBox Swiss AG",
        websiteUrl = "https://bitbox.swiss/",
        documentUrl = documentUrl,
        license = Licenses.CcBySa40,
    )

    private fun jimbojw(documentUrl: String) = Attribution(
        author = "Jimbojw",
        websiteUrl = "https://jimbojw.github.io/seed-picker-solitaire/",
        documentUrl = documentUrl,
        license = Licenses.Apache20,
    )

    private fun bitcoinHole(documentUrl: String) = Attribution(
        author = "The Bitcoin Hole",
        websiteUrl = "https://thebitcoinhole.com/",
        documentUrl = documentUrl,
        license = Licenses.AuthorsTerms,
    )

    private fun simplestBitcoinBook(documentUrl: String) = Attribution(
        author = "The Simplest Bitcoin Book",
        websiteUrl = "https://thesimplestbitcoinbook.net/",
        documentUrl = documentUrl,
        // Site: non-commercial Creative Commons for the book materials.
        license = Licenses.CcByNc,
    )

    private fun entropyPage(documentUrl: String) = Attribution(
        author = "entropy.page",
        websiteUrl = "https://entropy.page/",
        documentUrl = documentUrl,
        license = Licenses.AuthorsTerms,
    )

    private fun bip39Bips(documentUrl: String) = Attribution(
        author = "BIP39 (bitcoin/bips)",
        websiteUrl = "https://github.com/bitcoin/bips/blob/master/bip-0039/english.txt",
        documentUrl = documentUrl,
        license = Licenses.Mit,
    )

    val all = listOf(
        ManualDocument(
            id = "howto",
            title = "Diceware How-To",
            subtitle = "Create a backup with dice",
            assetFileName = "BitBox_Diceware_HowTo.pdf",
            attribution = bitbox("https://bitbox.swiss/bitbox02/BitBox_Diceware_HowTo.pdf"),
        ),
        ManualDocument(
            id = "lookup",
            title = "Diceware Lookup Table",
            subtitle = "Word list for dice rolls",
            assetFileName = "BitBox_Diceware_LookupTable.pdf",
            attribution = bitbox("https://bitbox.swiss/bitbox02/BitBox_Diceware_LookupTable.pdf"),
        ),
        ManualDocument(
            id = "solitaire",
            title = "Seed Picker Solitaire",
            subtitle = "Create a backup with playing cards",
            assetFileName = "seed-picker-solitaire.pdf",
            attribution = jimbojw(
                "https://jimbojw.github.io/seed-picker-solitaire/seed-picker-solitaire.pdf",
            ),
        ),
        ManualDocument(
            id = "coinflip-guide",
            title = "Coin Flip Seed Guide",
            subtitle = "Create a backup with coin flips",
            assetFileName = "coin-flip-seed-guide.pdf",
            attribution = bitcoinHole(
                "https://thebitcoinhole.com/blog/files/coin-flip-seed-guide.pdf",
            ),
        ),
        ManualDocument(
            id = "coinflip-sheet",
            title = "Coin Flip Seed Sheet",
            subtitle = "Worksheet for coin flip seeds",
            assetFileName = "coin-flip-seed-sheet.pdf",
            attribution = bitcoinHole(
                "https://thebitcoinhole.com/blog/files/coin-flip-seed-sheet.pdf",
            ),
        ),
        ManualDocument(
            id = "coinflip-passphrase",
            title = "Coin Flip Passphrase Sheet",
            subtitle = "Worksheet for a coin flip passphrase",
            assetFileName = "coin-flip-passphrase-sheet.pdf",
            attribution = bitcoinHole(
                "https://thebitcoinhole.com/blog/files/coin-flip-passphrase-sheet.pdf",
            ),
        ),
        ManualDocument(
            id = "bip39",
            title = "BIP39 Wordlist",
            subtitle = "Word list for the coin flip guide",
            assetFileName = "bip39-wordlist.pdf",
            attribution = bitcoinHole(
                "https://thebitcoinhole.com/blog/files/bip39-wordlist.pdf",
            ),
        ),
        ManualDocument(
            id = "roll-workshop",
            title = "Roll Your Own Seed Workshop",
            subtitle = "Guide for D8 and D16 dice",
            assetFileName = "roll-your-own-seed-workshop.pdf",
            attribution = simplestBitcoinBook(
                "https://thesimplestbitcoinbook.net/wp-content/uploads/2025/03/FINAL_Roll-Your-Own-Seed-Workshop_8.5x11_03_25_2025.pdf",
            ),
        ),
        ManualDocument(
            id = "entropy-worksheet",
            title = "Seed Worksheet",
            subtitle = "Worksheet for the D8/D16 workshop",
            assetFileName = "entropy-page-worksheet.pdf",
            attribution = entropyPage("https://entropy.page/files/worksheet.pdf"),
        ),
        ManualDocument(
            id = "entropy-dictionary",
            title = "BIP39 Dictionary",
            subtitle = "Word list for the D8/D16 workshop",
            assetFileName = "entropy-page-dictionary.pdf",
            attribution = entropyPage("https://entropy.page/files/dictionary.pdf"),
        ),
        ManualDocument(
            id = "bip39-cutout",
            title = "BIP39 English Cut-Out List",
            subtitle = "Print, cut along the dashed lines, draw from a bowl",
            assetFileName = "bip39-english-cutout.pdf",
            attribution = bip39Bips(
                "https://github.com/bitcoin/bips/blob/master/bip-0039/english.txt",
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
