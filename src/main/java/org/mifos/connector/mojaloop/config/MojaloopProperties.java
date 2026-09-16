package org.mifos.connector.mojaloop.config;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

/**
 * Switches that change how this connector behaves: {@code mojaloop.*}.
 *
 * @param enabled
 *            when false the connector answers from canned data instead of calling the switch
 * @param perfMode
 *            performance mode, which short-circuits parts of the flow
 * @param perfRespDelay
 *            artificial delay in milliseconds used by performance mode
 */
@Validated
@ConfigurationProperties(prefix = "mojaloop")
public record MojaloopProperties(@DefaultValue("false") boolean enabled, @DefaultValue("false") boolean perfMode,
        @Min(value = 0, message = "mojaloop.perf-resp-delay must be zero or greater") @DefaultValue("0") int perfRespDelay) {
}
