# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.6.0] - 2026-08-21

### Added

- Zapstore listing screenshots (status bar cropped).
- About page link to open a new GitHub issue.

## [1.5.0] - 2026-08-14

### Added

- About credits Survival Manual as inspiration, with a link to the project.
- Home "What is entropy?" blurb with a Learn more screen.
- BIP39 seed phrase on that screen opens the bundled cut-out BIP39 word list PDF.
- Airgapped device on that screen opens the BIP39 tool checklist.

## [1.4.0] - 2026-08-14

### Added

- Settings screen (Survival Manual style): Day/Night theme (Day, Night, Dark Night, System) and text size (Tiny through Huge).

### Fixed

- Make the 3-2-1 backup rule look like a link (underlined, same warning red) in the warning list.
- Distinguish Night (charcoal) from Dark Night (true black) themes.

## [1.3.0] - 2026-08-14

### Added

- Screen explaining the 3-2-1 backup rule, linked from the warning list.
- Per-source license label (and link when known) next to website / source PDF.
- Print action for bundled PDFs (viewer toolbar and overflow menu).

### Changed

- README, About, and Zapstore copy note that a hardware wallet or airgapped device is needed for the BIP39 checksum word.
- Warning list: consider the 3-2-1 backup rule.
- Launcher icon uses precomposed mipmaps (Survival Manual style) instead of a circular adaptive mask.
- App drawer label is `Entropy Manual`.

## [1.2.2] - 2026-08-14

### Added

- Before you begin checks on the printer and scissors path (bowl, mix, replace slips, remix after each draw).

### Fixed

- Zapstore publish uses `zapstore.yaml` so Keep a Changelog release notes are included (bare APK publish showed "No notes").
- Hide the BIP39 (bitcoin/bips) author line on the printer and scissors path.

## [1.2.1] - 2026-08-13

### Fixed

- Built-in PDF viewer page fill so documents stay readable in dark mode.
- Built-in PDF viewer scrolling (pinch-zoom was eating scroll gestures).

## [1.2.0] - 2026-08-13

### Added

- README banner image.

### Changed

- App launcher icon to the notebook artwork (transparent foreground on dark green).
- Path screens no longer repeat the subtitle above Before you begin.
- Path screens order footer as Warning, Checksum, then Sources.

### Fixed

- Point Zapstore at `CHANGELOG.md` via `release_notes` so releases show notes instead of "No notes".

## [1.1.0] - 2026-08-13

### Added

- Overflow menu on document list items to open the source website, online PDF, or another app without entering the in-app viewer.
- Share action on document overflow menus.

### Changed

- Put "Open in another app" first in the document overflow menu.

### Fixed

- D8 path icon in dark mode by replacing the light-filled PNG with a tintable vector.

## [1.0.0] - 2026-08-13

### Added

- Offline reader for entropy / seed backup PDFs.
- Home paths by tools at hand: dice, dice and coin, coin, playing cards, D8/D16, printer and scissors.
- Path screens with Before you begin and Take proper precautions.
- Shared safety footer with seed-handling warnings and BIP39 checksum note.
- Airgapped BIP39 tool checklist screen.
- Adaptive launcher icon and splash with dice artwork.
- App footer with semantic version (links to GitHub releases) and git hash (links to the commit).
- Zapstore publishing metadata and release signing support.

[Unreleased]: https://github.com/dergigi/offline-entropy-manual/compare/v1.6.0...HEAD
[1.6.0]: https://github.com/dergigi/offline-entropy-manual/compare/v1.5.0...v1.6.0
[1.5.0]: https://github.com/dergigi/offline-entropy-manual/compare/v1.4.0...v1.5.0
[1.4.0]: https://github.com/dergigi/offline-entropy-manual/compare/v1.3.0...v1.4.0
[1.3.0]: https://github.com/dergigi/offline-entropy-manual/compare/v1.2.2...v1.3.0
[1.2.2]: https://github.com/dergigi/offline-entropy-manual/compare/v1.2.1...v1.2.2
[1.2.1]: https://github.com/dergigi/offline-entropy-manual/compare/v1.2.0...v1.2.1
[1.2.0]: https://github.com/dergigi/offline-entropy-manual/compare/v1.1.0...v1.2.0
[1.1.0]: https://github.com/dergigi/offline-entropy-manual/compare/v1.0.0...v1.1.0
[1.0.0]: https://github.com/dergigi/offline-entropy-manual/releases/tag/v1.0.0
