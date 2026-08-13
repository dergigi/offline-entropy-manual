# Offline Entropy Manual

Offline Android reader for entropy backup guides. Ships with bundled PDFs and works without a network connection.

Inspired by [SurvivalManual](https://github.com/ligi/SurvivalManual).

## Documents

Bundled under `app/src/main/assets/`:

- [BitBox Diceware How-To](https://bitbox.swiss/bitbox02/BitBox_Diceware_HowTo.pdf)
- [BitBox Diceware Lookup Table](https://bitbox.swiss/bitbox02/BitBox_Diceware_LookupTable.pdf)
- [Seed Picker Solitaire](https://jimbojw.github.io/seed-picker-solitaire/seed-picker-solitaire.pdf)
- [Coin Flip Seed Guide](https://thebitcoinhole.com/blog/files/coin-flip-seed-guide.pdf)
- [Coin Flip Seed Sheet](https://thebitcoinhole.com/blog/files/coin-flip-seed-sheet.pdf)
- [Coin Flip Passphrase Sheet](https://thebitcoinhole.com/blog/files/coin-flip-passphrase-sheet.pdf)
- [BIP39 Wordlist](https://thebitcoinhole.com/blog/files/bip39-wordlist.pdf)

Sources: BitBox Swiss AG, Jimbojw, and The Bitcoin Hole. This app redistributes them for offline use with attribution. Rights to the documents remain with their authors.

## Build

Requires JDK 17+ and the Android SDK (platform 35).

```bash
./gradlew :app:assembleDebug
```

APK output: `app/build/outputs/apk/debug/app-debug.apk`

## License

App code is MIT. Bundled PDFs are not covered by that license.
