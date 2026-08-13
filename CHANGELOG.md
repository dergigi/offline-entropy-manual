# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- README banner image.

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

[Unreleased]: https://github.com/dergigi/offline-entropy-manual/compare/v1.1.0...HEAD
[1.1.0]: https://github.com/dergigi/offline-entropy-manual/compare/v1.0.0...v1.1.0
[1.0.0]: https://github.com/dergigi/offline-entropy-manual/releases/tag/v1.0.0
