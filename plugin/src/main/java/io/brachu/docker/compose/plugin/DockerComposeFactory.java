package io.brachu.docker.compose.plugin;

import java.util.Map;

import io.brachu.johann.DockerCompose;

final class DockerComposeFactory {

    DockerCompose create(Config config) {
        DockerCompose.OngoingBuild.File file = setupBuilder(config);
        DockerCompose.OngoingBuild.Project project = setupFile(file, config);
        DockerCompose.OngoingBuild.Env env = setupProject(project, config);
        DockerCompose.OngoingBuild.Finish finish = setupEnv(env, config);
        return finish.build();
    }

    private DockerCompose.OngoingBuild.File setupBuilder(Config config) {
        String executablePath = config.getExecutablePath();

        if (executablePath != null) {
            return DockerCompose.builder(executablePath);
        } else {
            return DockerCompose.builder();
        }
    }

    private DockerCompose.OngoingBuild.Project setupFile(DockerCompose.OngoingBuild.File file, Config config) {
        return file.absolute(config.getFile());
    }

    private DockerCompose.OngoingBuild.Env setupProject(DockerCompose.OngoingBuild.Project project, Config config) {
        String projectName = config.getProjectName();

        if (projectName != null) {
            return project.projectName(projectName);
        } else {
            return project;
        }
    }

    private DockerCompose.OngoingBuild.Finish setupEnv(DockerCompose.OngoingBuild.Env env, Config config) {
        Map<String, String> configEnv = config.getEnv();

        if (configEnv != null) {
            return env.env(configEnv);
        } else {
            return env;
        }
    }

}
