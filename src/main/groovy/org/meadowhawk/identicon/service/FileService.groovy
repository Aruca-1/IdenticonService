package org.meadowhawk.identicon.service

class FileService {
    String getReadme(){
        File rmd = new File("readme.md")
        String resp = ""
        try {
            rmd.withReader { reader ->
                resp = reader.text
            }
        } catch(FileNotFoundException fe){
            try{
                InputStream inStr = getClass().getClassLoader().getResourceAsStream("readme.md")
                resp = inStr?.text
            }catch(Exception e){ println "Failed to get readme.md file."}
        }
        resp
    }
}
