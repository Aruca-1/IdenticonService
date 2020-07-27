package org.meadowhawk.identicon.service

import org.meadowhawk.identicon.IdenticonGenerator
import org.meadowhawk.identicon.service.pattern.DerezPattern
import org.meadowhawk.identicon.service.pattern.TrichromeDerez
import org.meadowhawk.identicon.service.util.SVGDerez
import org.meadowhawk.identicon.util.Helper
import org.meadowhawk.identicon.util.IconSize
import spock.lang.Specification

import java.security.KeyPair

class DerezSpec extends Specification{

    def "Generate a Patchwork Styled Identicon"(){
        given:
        KeyPair keys = Helper.getKeys()
        byte[] bytes = keys.getPublic().encoded
        String fileName = "derex.svg"

        when:
        IdenticonGenerator.generateToFile(bytes,new DerezPattern(), new File(fileName), IconSize.SMALL)

        then:
        File derezFile = new File(fileName)
        assert derezFile.exists()
        String derezContent = new FileReader(derezFile).text

        def startStr = "<svg width=\'${SVGDerez.fullWidth}\' height=\'${IconSize.SMALL.size}\' xmlns=\'http://www.w3.org/2000/svg\' xmlns:xlink=\'http://www.w3.org/1999/xlink\' version=\'1.1\'>"
        assert derezContent.startsWith(startStr)
        assert derezContent.endsWith("</svg>")

        File out = new File("patch.svg").withWriter { w->
            w.write(derezContent)
        }
    }

    def "Generate a TrichromeDerez Styled Identicon"(){
        given:
        KeyPair keys = Helper.getKeys()
        byte[] bytes = keys.getPublic().encoded
        String fileName = "derezTri.svg"

        when:
        IdenticonGenerator.generateToFile(bytes,new TrichromeDerez(), new File(fileName), IconSize.SMALL)

        then:
        File derezFile = new File(fileName)
        assert derezFile.exists()
        String derezContent = new FileReader(derezFile).text

        def startStr = "<svg width=\'${SVGDerez.fullWidth}\' height=\'${IconSize.SMALL.size}\' xmlns=\'http://www.w3.org/2000/svg\' xmlns:xlink=\'http://www.w3.org/1999/xlink\' version=\'1.1\'>"
        assert derezContent.startsWith(startStr)
        assert derezContent.endsWith("</svg>")

        File out = new File("patch.svg").withWriter { w->
            w.write(derezContent)
        }
    }
}
