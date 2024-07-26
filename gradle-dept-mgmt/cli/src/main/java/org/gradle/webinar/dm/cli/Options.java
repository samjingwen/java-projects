package org.gradle.webinar.dm.cli;

import java.io.File;
import picocli.CommandLine;

public class Options {

    @CommandLine.Option(names = {"--input", "-i"}, description="Path to your Git repository")
    private File repositoryPath;

    public void setRepositoryPath(File repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public File getRepositoryPath() {
        return repositoryPath;
    }
}