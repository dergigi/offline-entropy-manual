# Offline Entropy Manual

Offline Android reader for entropy backup guides. Ships with bundled PDFs and works without a network connection.

Inspired by [SurvivalManual](https://github.com/ligi/SurvivalManual).

## Documents

Bundled under `app/src/main/assets/`:

- [BitBox Diceware How-To](https://bitbox.swiss/bitbox02/BitBox_Diceware_HowTo.pdf)
- [BitBox Diceware Lookup Table](https://bitbox.swiss/bitbox02/BitBox_Diceware_LookupTable.pdf)
- [Seed Picker Solitaire](https://jimbojw.github.io/seed-picker-solitaire/seed-picker-solitaire.pdf)

BitBox PDFs are published by BitBox Swiss AG. Seed Picker Solitaire is by Jimbojw. This app redistributes them for offline use with attribution. Rights to the documents remain with their authors.

## Build

Requires JDK 17+ and the Android SDK (platform 35).

```bash
./gradlew :app:assembleDebug
```

APK output: `app/build/outputs/apk/debug/app-debug.apk`

## License

App code is MIT. Bundled PDFs are not covered by that license.
