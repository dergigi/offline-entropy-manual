#!/usr/bin/env python3
"""Build an offline PDF of econoalchemist's SeedSigner Generate-Seed guide.

Source: https://github.com/econoalchemist/SeedSigner (The Unlicense)
"""

from __future__ import annotations

import re
import urllib.request
from pathlib import Path

from fpdf import FPDF

ROOT = Path(__file__).resolve().parents[1]
GUIDE_DIR = Path(__file__).resolve().with_name("seedsigner-guide")
MD_PATH = GUIDE_DIR / "04_Generate-Seed.md"
ASSETS = GUIDE_DIR / "assets"
OUTPUT = ROOT / "app/src/main/assets/seedsigner-generate-seed.pdf"
ASSET_BASE = "https://raw.githubusercontent.com/econoalchemist/SeedSigner/main/assets/"

PAGE_W, PAGE_H = 612, 792  # letter points
MARGIN = 48
CONTENT_W = PAGE_W - 2 * MARGIN
IMG_W = 150  # points per screenshot in a row


class GuidePDF(FPDF):
    def footer(self) -> None:
        self.set_y(-28)
        self.set_font("Helvetica", size=8)
        self.set_text_color(100, 100, 100)
        self.cell(0, 10, f"{self.page_no()}", align="C")


def ensure_assets(md: str) -> None:
    ASSETS.mkdir(parents=True, exist_ok=True)
    names = sorted(set(re.findall(r"assets/(seedgen[^)\"\s]+)", md)))
    for name in names:
        dest = ASSETS / name
        if dest.exists() and dest.stat().st_size > 0:
            continue
        print(f"download {name}")
        urllib.request.urlretrieve(ASSET_BASE + name, dest)


def strip_md_links(text: str) -> str:
    return re.sub(r"\[([^\]]+)\]\([^)]+\)", r"\1", text)


def clean_inline(text: str) -> str:
    text = strip_md_links(text)
    text = text.replace("`", "")
    text = re.sub(r"\*\*([^*]+)\*\*", r"\1", text)
    text = re.sub(r"\*([^*]+)\*", r"\1", text)
    replacements = {
        "\u2018": "'",
        "\u2019": "'",
        "\u201c": '"',
        "\u201d": '"',
        "\u2013": "-",
        "\u2014": "-",
        "\u2026": "...",
        "\u00a0": " ",
    }
    for src, dst in replacements.items():
        text = text.replace(src, dst)
    return text.strip()


def parse_blocks(md: str) -> list[tuple[str, object]]:
    """Return (kind, payload) blocks: h1/h2/p/images."""
    blocks: list[tuple[str, object]] = []
    lines = md.splitlines()
    i = 0
    while i < len(lines):
        line = lines[i].rstrip()
        if not line.strip():
            i += 1
            continue
        if line.startswith("# "):
            blocks.append(("h1", clean_inline(line[2:])))
            i += 1
            continue
        if line.startswith("## "):
            blocks.append(("h2", clean_inline(line[3:])))
            i += 1
            continue
        if '<p align="center">' in line or line.strip() == '<p align="center">':
            imgs: list[str] = []
            while i < len(lines) and "</p>" not in lines[i]:
                imgs += re.findall(r'src="assets/([^"]+)"', lines[i])
                i += 1
            if i < len(lines):
                imgs += re.findall(r'src="assets/([^"]+)"', lines[i])
                i += 1
            if imgs:
                blocks.append(("images", imgs))
            continue
        # paragraph: gather until blank / heading / image block
        para: list[str] = []
        while i < len(lines):
            cur = lines[i].rstrip()
            if not cur.strip():
                break
            if cur.startswith("#") or '<p align="center">' in cur:
                break
            para.append(cur.strip())
            i += 1
        text = clean_inline(" ".join(para))
        if text:
            blocks.append(("p", text))
    return blocks


def add_wrapped(pdf: GuidePDF, text: str, size: int = 11, style: str = "") -> None:
    pdf.set_font("Helvetica", style=style, size=size)
    pdf.set_text_color(20, 20, 20)
    pdf.set_x(MARGIN)
    pdf.multi_cell(CONTENT_W, size * 0.45 + 4, text)


def add_images(pdf: GuidePDF, names: list[str]) -> None:
    # Lay out up to 3 images per row
    row: list[str] = []
    for name in names:
        row.append(name)
        if len(row) == 3:
            _flush_image_row(pdf, row)
            row = []
    if row:
        _flush_image_row(pdf, row)


def _flush_image_row(pdf: GuidePDF, names: list[str]) -> None:
    paths = [ASSETS / n for n in names]
    # Estimate row height from first image aspect
    from PIL import Image

    aspects = []
    for p in paths:
        with Image.open(p) as im:
            aspects.append(im.height / im.width)
    max_aspect = max(aspects)
    img_h = IMG_W * max_aspect
    need = img_h + 16
    if pdf.get_y() + need > PAGE_H - MARGIN:
        pdf.add_page()
        pdf.set_y(MARGIN)

    y = pdf.get_y()
    gap = 12
    total_w = len(names) * IMG_W + (len(names) - 1) * gap
    x = MARGIN + max(0, (CONTENT_W - total_w) / 2)
    for p in paths:
        with Image.open(p) as im:
            aspect = im.height / im.width
        h = IMG_W * aspect
        pdf.image(str(p), x=x, y=y, w=IMG_W, h=h)
        x += IMG_W + gap
    pdf.set_y(y + img_h + 10)


def main() -> None:
    md = MD_PATH.read_text(encoding="utf-8")
    ensure_assets(md)
    blocks = parse_blocks(md)

    pdf = GuidePDF(unit="pt", format="Letter")
    pdf.set_auto_page_break(auto=True, margin=MARGIN)
    pdf.add_page()
    pdf.set_y(MARGIN)

    pdf.set_font("Helvetica", size=9)
    pdf.set_text_color(90, 90, 90)
    pdf.set_x(MARGIN)
    pdf.multi_cell(
        CONTENT_W,
        12,
        "SeedSigner guide by econoalchemist (Unlicense)\n"
        "https://seedsigner.econoalchemist.com/04_Generate-Seed.html",
    )
    pdf.ln(6)

    for kind, payload in blocks:
        if kind == "h1":
            if pdf.get_y() > MARGIN + 40:
                pdf.ln(8)
            if pdf.get_y() > PAGE_H - MARGIN - 80:
                pdf.add_page()
                pdf.set_y(MARGIN)
            add_wrapped(pdf, str(payload), size=16, style="B")
            pdf.ln(4)
        elif kind == "h2":
            pdf.ln(6)
            if pdf.get_y() > PAGE_H - MARGIN - 60:
                pdf.add_page()
                pdf.set_y(MARGIN)
            add_wrapped(pdf, str(payload), size=13, style="B")
            pdf.ln(2)
        elif kind == "p":
            add_wrapped(pdf, str(payload), size=11)
            pdf.ln(3)
        elif kind == "images":
            add_images(pdf, list(payload))

    OUTPUT.parent.mkdir(parents=True, exist_ok=True)
    pdf.output(str(OUTPUT))
    print(f"wrote {OUTPUT} ({OUTPUT.stat().st_size / 1024 / 1024:.1f} MB)")


if __name__ == "__main__":
    main()
