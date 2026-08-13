#!/usr/bin/env python3
"""Generate a printable, scissors-friendly BIP39 English cut-out sheet PDF.

Source word list: bitcoin/bips bip-0039/english.txt (MIT).
"""

from __future__ import annotations

from pathlib import Path

from reportlab.lib.colors import HexColor, black, white
from reportlab.lib.pagesizes import letter
from reportlab.pdfgen import canvas

ROOT = Path(__file__).resolve().parents[1]
WORDLIST = Path(__file__).resolve().with_name("bip39-english.txt")
OUTPUT = ROOT / "app/src/main/assets/bip39-english-cutout.pdf"

PAGE_SIZE = letter
MARGIN = 36  # 0.5"
COLS = 8
ROWS = 16  # 128 words/page → 16 pages
HEADER_H = 28

LINE = HexColor("#666666")
INDEX = HexColor("#555555")
GUIDE = HexColor("#999999")


def load_words() -> list[str]:
    words = [
        line.strip()
        for line in WORDLIST.read_text(encoding="utf-8").splitlines()
        if line.strip()
    ]
    if len(words) != 2048:
        raise SystemExit(f"expected 2048 words, got {len(words)}")
    return words


def draw_dashed_rect(c: canvas.Canvas, x: float, y: float, w: float, h: float) -> None:
    c.setStrokeColor(LINE)
    c.setDash(2, 2)
    c.setLineWidth(0.6)
    c.rect(x, y, w, h, stroke=1, fill=0)
    c.setDash()


def draw_page(
    c: canvas.Canvas,
    words: list[str],
    page_index: int,
    page_count: int,
) -> None:
    page_w, page_h = PAGE_SIZE
    c.setFillColor(white)
    c.rect(0, 0, page_w, page_h, stroke=0, fill=1)

    grid_top = page_h - MARGIN - HEADER_H
    grid_bottom = MARGIN
    grid_left = MARGIN
    grid_right = page_w - MARGIN
    grid_w = grid_right - grid_left
    grid_h = grid_top - grid_bottom
    cell_w = grid_w / COLS
    cell_h = grid_h / ROWS

    # Header
    c.setFillColor(black)
    c.setFont("Helvetica-Bold", 11)
    c.drawString(MARGIN, page_h - MARGIN - 14, "BIP39 English word list")
    c.setFont("Helvetica", 9)
    c.setFillColor(GUIDE)
    c.drawRightString(
        page_w - MARGIN,
        page_h - MARGIN - 14,
        f"Cut along dashed lines · page {page_index + 1}/{page_count}",
    )
    c.setStrokeColor(GUIDE)
    c.setLineWidth(0.5)
    c.line(MARGIN, grid_top + 6, page_w - MARGIN, grid_top + 6)

    start = page_index * COLS * ROWS
    for row in range(ROWS):
        for col in range(COLS):
            i = start + row * COLS + col
            if i >= len(words):
                return
            x = grid_left + col * cell_w
            # PDF y grows upward; row 0 at top
            y = grid_top - (row + 1) * cell_h
            draw_dashed_rect(c, x, y, cell_w, cell_h)

            # 1-based index for humans (BIP39 index is i, 0-based)
            c.setFillColor(INDEX)
            c.setFont("Helvetica", 7)
            c.drawString(x + 3, y + cell_h - 10, str(i + 1))

            c.setFillColor(black)
            c.setFont("Helvetica-Bold", 10)
            word = words[i]
            c.drawCentredString(x + cell_w / 2, y + cell_h / 2 - 3, word)


def main() -> None:
    words = load_words()
    per_page = COLS * ROWS
    page_count = (len(words) + per_page - 1) // per_page
    OUTPUT.parent.mkdir(parents=True, exist_ok=True)
    c = canvas.Canvas(str(OUTPUT), pagesize=PAGE_SIZE)
    c.setTitle("BIP39 English word list (cut-out)")
    c.setAuthor("Offline Entropy Manual")
    c.setSubject("Printable cut-out sheets from bitcoin/bips bip-0039/english.txt")
    for page_index in range(page_count):
        draw_page(c, words, page_index, page_count)
        c.showPage()
    c.save()
    print(f"wrote {OUTPUT} ({page_count} pages, {len(words)} words)")


if __name__ == "__main__":
    main()
