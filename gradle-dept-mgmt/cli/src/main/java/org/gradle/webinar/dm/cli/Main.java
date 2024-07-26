package org.gradle.webinar.dm.cli;

import org.gradle.webinar.gitutils.GitUtils;
import picocli.CommandLine;

import java.io.IOException;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Options options = CommandLine.populateCommand(new Options(), args);
        try {
            GitUtils utils = new GitUtils(options.getRepositoryPath());
            Set<String> committers = utils.findCommitters();
            System.out.println("Committers:" + committers);
            System.out.println("Total committers: " + committers.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}