# Offline Entropy Manual

![Offline Entropy Manual](assets/banner.jpg)

Android app that ships entropy / seed backup guides as offline PDFs. Pick what tools you have (dice, coins, cards, …) and open the matching docs.

Inspired by [SurvivalManual](https://github.com/ligi/SurvivalManual).

Versions follow [Semantic Versioning](https://semver.org/spec/v2.0.0.html). Notable changes are listed in [`CHANGELOG.md`](CHANGELOG.md) using [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

```bash
./gradlew :app:assembleDebug
```

## Release build

Signing uses a local upload keystore. Put these in gitignored `local.properties`:

```properties
OEM_STORE_FILE=/absolute/path/to/upload.jks
OEM_STORE_PASSWORD=…
OEM_KEY_ALIAS=upload
OEM_KEY_PASSWORD=…
```

Then:

```bash
./gradlew :app:assembleRelease
```

## Publishing to Zapstore

Config lives in [`zapstore.yaml`](zapstore.yaml) (includes publisher `pubkey`). Releases are published with [`zsp`](https://github.com/zapstore/zsp).

1. Install `zsp` from [zsp releases](https://github.com/zapstore/zsp/releases) (or `go install github.com/zapstore/zsp@latest`).
2. Cut a GitHub release that includes the signed APK (or build `assembleRelease` locally).
3. Publish with your Nostr key for `npub1dergggklka99wwrs92yz8wdjs952h2ux2ha2ed598ngwu9w7a6fsh9xzpc`:

```bash
export SIGN_WITH='nsec1…'   # or bunker://… or browser
./scripts/zapstore-publish.sh
```

First publish links the APK signing certificate to your Nostr identity (NIP-C1) and whitelists the repo via `zapstore.yaml`.

## License

**App code** is [MIT](LICENSE).

**Bundled guides** are not. They keep whatever license (or all-rights-reserved terms) their authors set. For example, the BitBox Diceware How-To and Lookup Table are [CC BY-SA 4.0](https://creativecommons.org/licenses/by-sa/4.0/). Check each source linked from the About screen before redistributing the PDFs on their own.
