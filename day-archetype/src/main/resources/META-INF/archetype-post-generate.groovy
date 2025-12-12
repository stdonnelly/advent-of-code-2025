import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// This script adds a symlink to the eclipse-formatter.xml file to the path.
// This is because there only needs to be one of this file, but the spotless plugin is requiring it to be in the project folder for all modules.

// Get the path of the output. This method of getting the directory is from
// https://github.com/gantsign/maven-archetypes/blob/master/java-application-maven-archetype/src/main/resources/META-INF/archetype-post-generate.groovy
dir = Paths.get(request.outputDirectory, request.artifactId)

Files.createSymbolicLink(dir.resolve("eclipse-formatter.xml"), Paths.get("../eclipse-formatter.xml"))