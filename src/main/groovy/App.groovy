package org.meadowhawk.identicon.service

import org.meadowhawk.identicon.IdenticonGenerator
import org.meadowhawk.identicon.service.pattern.TrichromeDerez
import org.meadowhawk.identicon.util.Helper
import org.meadowhawk.identicon.util.IconSize
import org.commonmark.node.*
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

import java.security.KeyPair

import static spark.Spark.*

class App {
    static final String JSON_TYPE = "application/json"
    static String readmeMd = null

    static void main(String[] args) {
        println "Starting server...."

        get '/', {req, res ->
            res.status(200)
            res.type("text/html")
            getReadme()
        }
        get '/icon', {req, res ->
            res.type("image/svg+xml")
            res.status(200)
            getIcon()
        }
    }

    static String getReadme(){
        File rmd = new File("readme.md")
        if(readmeMd == null){
            rmd.withReader { reader ->
                readmeMd = reader.text
            }}
        Parser parser = Parser.builder().build();
        Node document = parser.parse(readmeMd);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        renderer.render(document)

    }

    static Object getIcon(){
        KeyPair keys = Helper.getKeys()
        byte[] bytes = keys.getPublic().encoded

        IdenticonGenerator.generate(bytes, new TrichromeDerez(), IconSize.SMALL).toString()
    }
}
