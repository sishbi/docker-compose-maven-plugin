package io.brachu.docker.compose.plugin.test.harness;

import static org.assertj.core.api.Assertions.assertThat;

import io.brachu.johann.DockerCompose;
import org.junit.Test;

public class WaitIT {

    @Test
    public void clusterShouldBeRunning() {
        DockerCompose dockerCompose = DockerCompose.builder().classpath().build();
        assertThat(dockerCompose.isUp()).isTrue();
    }

}
