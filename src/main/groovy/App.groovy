package org.meadowhawk.identicon.service

import groovy.transform.CompileStatic
import org.meadowhawk.identicon.IdenticonGenerator
import org.meadowhawk.identicon.pattern.Monochrome
import org.meadowhawk.identicon.pattern.Patchwork
import org.meadowhawk.identicon.pattern.Trichrome
import org.meadowhawk.identicon.service.pattern.TrichromeDerez
import org.meadowhawk.identicon.util.Helper
import org.meadowhawk.identicon.util.IconSize
import org.commonmark.node.*
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

import java.security.KeyPair

import static spark.Spark.*

@CompileStatic
class App {
    static final String JSON_TYPE = "application/json"
    static String readmeMd = null

    static void main(String[] args) {
        println "Starting server...."
        ProcessBuilder process = new ProcessBuilder()
        int portNum = (process.environment().get("PORT") != null)? Integer.parseInt(process.environment().get("PORT")): 4567
        port(portNum)

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

        get '/simple-icon', { req, resp ->
            resp.type("image/svg+xml")
            resp.status(200)
            getStaticIcon()
        }
    }

    static String getReadme(){
        if(readmeMd == null) {
            readmeMd = new FileService().getReadme()
        }
        Parser parser = Parser.builder().build();
        Node document = parser.parse(readmeMd);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        renderer.render(document)
    }

    static Object getStaticIcon(){
        KeyPair keys = Helper.getKeys()
        byte[] bytes = keys.getPublic().encoded

        IdenticonGenerator.generate(bytes, new Trichrome(), IconSize.SMALL).toString()
    }

    static Object getIcon(){
        KeyPair keys = Helper.getKeys()
        byte[] bytes = keys.getPublic().encoded

        IdenticonGenerator.generate(bytes, new TrichromeDerez(), IconSize.SMALL).toString()
    }
}
