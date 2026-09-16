package org.mifos.connector.mojaloop.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * The {@code camel.*} setting this connector owns.
 *
 * <p>
 * Only {@code camel.server-port} is bound. Everything else under {@code camel.} belongs to camel-spring-boot and is left alone.
 * </p>
 *
 * @param serverPort
 *            port the Camel REST configuration is given
 */
@Validated
@ConfigurationProperties(prefix = "camel")
public record ConnectorCamelProperties(@Min(value = 1, message = "camel.server-port must be a valid port") @Max(value = 65535,
        message = "camel.server-port must be a valid port") int serverPort) {
}
