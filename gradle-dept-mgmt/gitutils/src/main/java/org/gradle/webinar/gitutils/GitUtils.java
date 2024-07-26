package org.gradle.webinar.gitutils;

import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class GitUtils implements Closeable {
    private final static Logger LOGGER = LoggerFactory.getLogger(GitUtils.class);
    private final Repository repository;

    public GitUtils(File repositoryLocation) throws IOException {
        repository = new FileRepositoryBuilder()
            .setGitDir(new File(repositoryLocation, ".git"))
            .findGitDir()
            .setMustExist(true)
            .build();
    }

    public Set<String> findCommitters() throws IOException {
        ObjectId head = repository.resolve(Constants.HEAD);
        Set<String> committers = new HashSet<>();
        Deque<RevCommit> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        try (RevWalk revWalk = new RevWalk(repository)) {
            queue.add(revWalk.parseCommit(head));
            while (!queue.isEmpty()) {
                RevCommit pick = revWalk.parseCommit(queue.poll());
                if (visited.add(pick.getId().name())) {
                    PersonIdent authorIdent = pick.getAuthorIdent();
                    committers.add(authorIdent.getEmailAddress());
                    Collections.addAll(queue, pick.getParents());
                }
            }
        }
        LOGGER.debug("Found {} committers", committers.size());
        return committers;
    }

    @Override
    public void close() {
        repository.close();
    }
}