package io.brachu.docker.compose.plugin;

import java.util.concurrent.TimeUnit;

import org.apache.maven.plugin.MojoFailureException;

final class ConfigValidator {

    static void validate(Config config) throws MojoFailureException {
        validateFile(config);
        validateWait(config);
    }

    private static void validateFile(Config config) throws MojoFailureException {
        if (config.getFile() == null) {
            throw new MojoFailureException("Path to docker-compose.yml file must be set.");
        }
    }

    private static void validateWait(Config config) throws MojoFailureException {
        WaitConfig wait = config.getWait();
        if (wait != null) {
            if (wait.getValue() < 1) {
                throw new MojoFailureException("Wait time value must be greater than zero.");
            }

            if (wait.getUnit() == null) {
                throw new MojoFailureException("Wait time unit must not be null.");
            } else if (wait.getUnit().ordinal() < TimeUnit.SECONDS.ordinal()) {
                throw new MojoFailureException("Wait time unit cannot be smaller than seconds.");
            }
        }
    }

}
