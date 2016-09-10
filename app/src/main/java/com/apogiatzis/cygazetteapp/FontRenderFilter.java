package com.apogiatzis.cygazetteapp;

import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

/**
 * Created by andre on 22/08/2016.
 */
public class FontRenderFilter extends RenderFilter {
    public boolean allowText(TextRenderInfo renderInfo) {
        String font = renderInfo.getFont().getPostscriptFontName();
        return font.endsWith("Bold") || font.endsWith("Oblique");
    }
}