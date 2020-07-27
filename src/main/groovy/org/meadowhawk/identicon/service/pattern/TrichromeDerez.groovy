package org.meadowhawk.identicon.service.pattern

import org.meadowhawk.identicon.pattern.Trichrome
import org.meadowhawk.identicon.service.util.SVGDerez

class TrichromeDerez extends Trichrome{
    @Override
    void render(StringWriter writer, byte[] bytes, int width, int height) {
        SVGDerez.generateDerezGrid(writer, bytes, this, width, height)
    }
}
