package com.gitlab.novakmi.asciidoctorext

import org.asciidoctor.ast.Document
import org.asciidoctor.extension.Postprocessor

// https://gitlab.com/puravida-software/asciidoctor-extensions
public class CopyBlock extends Postprocessor {
    def String process(Document document, String output) {
        output = ReadResources.addExternalJs(output, "https://cdn.jsdelivr.net/npm/clipboard@2.0.6/dist/clipboard.min.js")
        output = ReadResources.addJs(output, "/clipboard.js")
        output = ReadResources.addCss(output, "/clipboard.css")
        return output
    }
}
