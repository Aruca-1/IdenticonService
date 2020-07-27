package org.meadowhawk.identicon.service.util

import groovy.xml.MarkupBuilder
import org.meadowhawk.identicon.pattern.RenderPattern

class SVGDerez {
    static int fullWidth = 800
    static void generateDerezGrid(Writer writer, byte[] bytes, RenderPattern pattern, int width, int height){

        int colorCt = width * height
        String[] colors = pattern.fillColors(bytes, colorCt)
        createSvg(writer, width, height) { w, h ->
            int x = 10
            int y = 0
            int b = 0
            int hPass = 0
            int hiPass = h/10
            int aDur = 5
            int boxes = w/10

            int totalLoop = aDur + boxes
            def lastMin =  (boxes-1) * 0.4 + (hiPass-1) + aDur + 0.6
            while (y < h) {
                int wPass = 0
                int wiPass = boxes
                def rnd = 0.1
                while (x < w+10) {
                    rect(x: x, y: y, width: 10, height: 10, fill: pattern.renderColor(colors[b])) {
                        animateTransform(attributeName: "transform", type: "translate", from: x, to: fullWidth - 70, dur: "${aDur}s", begin: "${wPass * 0.4+hPass}s" ,
                                repeatCount: "1", fill: "freeze", id: "rect-anim")
                        def dec = wiPass * 0.4 +(hiPass - rnd)  //Needs to stargger a slight amound to sensure they arent uniform? mayybe  add.1?
                        animateTransform(attributeName: "transform", type: "translate", from: fullWidth - 70, to: 0, dur: "${aDur}s", begin: "${lastMin+dec }s" ,
                                repeatCount: "1", fill: "freeze", id: "rect-anim")
                    }
                    wPass ++
                    wiPass --
                    rnd += 0.05
                    x += 10
                    b++
                }
                hPass ++
                hiPass --
                y += 10
                x = 10
            }
        }
    }

    static createSvg(StringWriter writer, int width, int height, Closure content) {
        def markupBuilder = new MarkupBuilder(writer)
        content.setDelegate(markupBuilder)
        markupBuilder.svg(width: fullWidth,height:height, xmlns:'http://www.w3.org/2000/svg', 'xmlns:xlink':'http://www.w3.org/1999/xlink', version:'1.1') {
            desc('Identicon Generator Image shared under CC BY-SA 4.0 - created by lees2bytes')
            rect(x: 0, y: 0, width: fullWidth, height: height, fill: 'rgb(40,40,40)')
            content(width, height)
        }
    }
}
