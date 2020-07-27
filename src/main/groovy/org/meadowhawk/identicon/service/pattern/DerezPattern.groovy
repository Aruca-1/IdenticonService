package org.meadowhawk.identicon.service.pattern

import org.meadowhawk.identicon.pattern.Patchwork
import org.meadowhawk.identicon.service.util.SVGDerez

class DerezPattern extends  Patchwork{

    @Override
    void render(StringWriter writer, byte[] bytes, int width, int height) {
        SVGDerez.generateDerezGrid(writer, bytes, this, width, height)
    }

}
