package com.gitlab.novakmi.asciidoctorext

import org.asciidoctor.Asciidoctor
import org.asciidoctor.jruby.extension.spi.ExtensionRegistry

class NovakmiExtensionRegistry implements ExtensionRegistry {
    void register(Asciidoctor asciidoctor) {
        asciidoctor.javaExtensionRegistry().postprocessor CopyBlock
    }
}

//https://gitlab.com/puravida-software/asciidoctor-extensions
class ReadResources {

    static String addCss(String output, String resource) {
        String css = readResource(resource);
        String replacement = new StringBuffer()
                .append("<style>").append(css).append("</style>")
                .append("</head>")
                .toString();
        return output.replace("</head>", replacement);
    }

    static String addJs(String output, String resource) {
        String javascript = readResource(resource);
        String replacement = new StringBuffer()
                .append("<script type='text/javascript'>").append(javascript).append("</script>")
                .append("</html>")
                .toString();
        return output.replace("</html>", replacement);
    }

    static String addExternalJs(String output, String url) {
        String replacement = new StringBuffer()
                .append("</body>")
                .append("<script type='text/javascript' src='").append(url).append("'></script>")
                .toString();
        return output.replace("</body>", replacement);
    }


    static String readResource(String name) {
        InputStream is = ReadResources.class.getResourceAsStream(name);
        if (is == null)
            return "";
        Reader reader = new InputStreamReader(is);
        try {
            StringWriter writer = new StringWriter();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer)) >= 0) {
                writer.write(buffer, 0, read);
            }
            return writer.toString();
        }
        catch (Exception ex) {
            return "";
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                // Continue
            }
        }
    }

}
