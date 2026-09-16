package org.mifos.connector.mojaloop.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

/**
 * How this connector reaches the Zeebe broker: {@code zeebe.broker.*} and {@code zeebe.client.*}.
 *
 * @param broker
 *            the broker to connect to
 * @param client
 *            client settings
 */
@Validated
@ConfigurationProperties(prefix = "zeebe")
public record ZeebeProperties(@Valid @DefaultValue Broker broker, @Valid @DefaultValue Client client) {

    /**
     * The broker to connect to: {@code zeebe.broker.*}.
     *
     * @param contactpoint
     *            gateway address, as host:port
     */
    public record Broker(@NotBlank(message = "zeebe.broker.contactpoint must be set") @Pattern(regexp = "^[^:]+:[0-9]+$",
            message = "zeebe.broker.contactpoint must look like host:port") String contactpoint) {
    }

    /**
     * Client settings: {@code zeebe.client.*}.
     *
     * @param maxExecutionThreads
     *            size of the job worker execution thread pool
     * @param evenlyAllocatedMaxJobs
     *            how many jobs a single worker keeps active
     * @param pollInterval
     *            job poll interval in milliseconds
     */
    public record Client(@Min(value = 1, message = "zeebe.client.max-execution-threads must be at least 1") int maxExecutionThreads,
            @Min(value = 1, message = "zeebe.client.evenly-allocated-max-jobs must be at least 1") int evenlyAllocatedMaxJobs,
            @Min(value = 1, message = "zeebe.client.poll-interval must be at least 1 millisecond") int pollInterval) {
    }
}
