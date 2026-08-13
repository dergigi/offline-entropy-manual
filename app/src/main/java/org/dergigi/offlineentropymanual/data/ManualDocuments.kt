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
    )

    fun byId(id: String): ManualDocument =
        all.first { it.id == id }
}
