
## TEST
### AsciiDoc (AsciiDoctor)
```
// build.gradle
plugins {
    id 'org.asciidoctor.convert' version '1.5.8'
}

ext {
    set('snippetsDir', file("build/generated-snippets"))
}

test {
    outputs.dir snippetsDir
}

asciidoctor {
    attributes 'snippets': file(snippetsDir)
    inputs.dir snippetsDir
    dependsOn test
}

task copyDocument(type: Copy) {
    from file("${asciidoctor.outputDir}/html5/")
    into file("${buildDir}/resources/main/static/docs")
    dependsOn asciidoctor
}

dependencies {
    // To use macro "Operation::{identifier}[snippets='...']"
    asciidoctor 'org.springframework.restdocs:spring-restdocs-asciidoctor:2.0.2.RELEASE'
}
```