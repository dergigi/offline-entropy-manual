# Offline Entropy Manual

Offline Android reader for BitBox Diceware materials. Ships with two PDFs and works without a network connection.

Inspired by [SurvivalManual](https://github.com/ligi/SurvivalManual).

## Documents

Bundled under `app/src/main/assets/`:

- [BitBox Diceware How-To](https://bitbox.swiss/bitbox02/BitBox_Diceware_HowTo.pdf)
- [BitBox Diceware Lookup Table](https://bitbox.swiss/bitbox02/BitBox_Diceware_LookupTable.pdf)

These PDFs are published by BitBox Swiss AG. This app redistributes them for offline use with attribution. Rights to the documents remain with BitBox.

## Build

Requires JDK 17+ and the Android SDK (platform 35).

```bash
./gradlew :app:assembleDebug
```

APK output: `app/build/outputs/apk/debug/app-debug.apk`

## License

App code is MIT. Bundled BitBox PDFs are not covered by that license; BitBox Swiss AG retains rights to those documents.
