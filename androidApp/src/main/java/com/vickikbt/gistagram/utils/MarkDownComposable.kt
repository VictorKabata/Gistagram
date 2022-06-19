package com.vickikbt.gistagram.utils

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.annotation.FontRes
import androidx.annotation.IdRes
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.AndroidView
import br.tiagohm.markdownview.MarkdownView
import br.tiagohm.markdownview.css.styles.Bootstrap

@Composable
fun MarkDownComposable(
    url: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    @FontRes fontResource: Int? = null,
    style: TextStyle = LocalTextStyle.current,
    @IdRes viewId: Int? = null
) {

    AndroidView(
        factory = { context ->
            MarkdownView(context).apply {
                viewId?.let { id = it }
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                setEscapeHtml(false)
                addStyleSheet(GistagramMarkDownStyle())
            }
        },
        update = { markDownView ->
            markDownView.loadMarkdownFromUrl(url)
        }
    )

}

class GistagramMarkDownStyle : Bootstrap() {
    init {
        addRule(
            "body",
            "line-height: 1.8",
            "padding: 8px",
            "text-align:left",
            "color: #fff",
            "background-color: #000"
        )
        addRule("h1", "font-size: 28px")
        addRule("h2", "font-size: 24px")
        addRule("h3", "font-size: 18px")
        addRule("h4", "font-size: 16px")
        addRule("h5", "font-size: 14px")
        addRule("h6", "font-size: 14px")
        addRule("p", "text-align:center")

        addRule(
            "pre",
            "position: relative",
            "padding: 14px 10px",
            "border: 0",
            "border-radius: 3px",
            "background-color: #f6f8fa"
        )
        addRule(
            "pre code",
            "position: relative",
            "line-height: 1.45",
            "background-color: transparent"
        )
        addRule("table tr:nth-child(2n)", "background-color: #f6f8fa")
        addRule("table th", "padding: 6px 13px", "border: 1px solid #dfe2e5")
        addRule("table td", "padding: 6px 13px", "border: 1px solid #dfe2e5")
        addRule(
            "kbd",
            "color: #444d56",
            "font-family: Consolas, \"Liberation Mono\", Menlo, Courier, monospace",
            "background-color: #fcfcfc",
            "border: solid 1px #c6cbd1",
            "border-bottom-color: #959da5",
            "border-radius: 3px",
            "box-shadow: inset 0 -1px 0 #959da5"
        )
        addRule(
            "pre[language]::before",
            "content: attr(language)",
            "position: absolute",
            "top: 0",
            "right: 5px",
            "padding: 2px 1px",
            "text-transform: uppercase",
            "color: #666",
            "font-size: 8.5px"
        )
        addRule("pre:not([language])", "padding: 6px 10px")
        addRule(".footnotes li p:last-of-type", "display: inline")
        addRule(".yt-player", "box-shadow: 0px 0px 12px rgba(0,0,0,0.2)")
        addRule(".scrollup", "background-color: #00BF4C")

        //Highlight.js


        //Highlight.js
        addRule(".hljs-comment", "color: #8e908c")
        addRule(".hljs-quote", "color: #8e908c")

        addRule(".hljs-variable", "color: #c82829")
        addRule(".hljs-template-variable", "color: #c82829")
        addRule(".hljs-tag", "color: #c82829")
        addRule(".hljs-name", "color: #c82829")
        addRule(".hljs-selector-id", "color: #c82829")
        addRule(".hljs-selector-class", "color: #c82829")
        addRule(".hljs-regexp", "color: #c82829")
        addRule(".hljs-deletion", "color: #c82829")

        addRule(".hljs-number", "color: #f5871f")
        addRule(".hljs-built_in", "color: #f5871f")
        addRule(".hljs-builtin-name", "color: #f5871f")
        addRule(".hljs-literal", "color: #f5871f")
        addRule(".hljs-type", "color: #f5871f")
        addRule(".hljs-params", "color: #f5871f")
        addRule(".hljs-meta", "color: #f5871f")
        addRule(".hljs-link", "color: #f5871f")

        addRule(".hljs-attribute", "color: #eab700")

        addRule(".hljs-string", "color: #718c00")
        addRule(".hljs-symbol", "color: #718c00")
        addRule(".hljs-bullet", "color: #718c00")
        addRule(".hljs-addition", "color: #718c00")

        addRule(".hljs-title", "color: #4271ae")
        addRule(".hljs-section", "color: #4271ae")

        addRule(".hljs-keyword", "color: #8959a8")
        addRule(".hljs-selector-tag", "color: #8959a8")
    }
}
