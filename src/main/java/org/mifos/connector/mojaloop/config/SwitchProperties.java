package org.mifos.connector.mojaloop.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

/**
 * The Mojaloop switch settings: {@code switch.*}.
 *
 * <p>
 * These are two different kinds of value, and only one of them is required.
 * </p>
 *
 * <p>
 * The {@code *-host} values are callback URLs. The connector hands them to the switch as
 * {@code X-Lookup-Callback-Url}, {@code X-Quote-Callback-Url} and {@code X-Transfer-Callback-Url}, and the switch calls back on
 * them. An empty one is a failure this platform has already had: an empty {@code switch.transfers-host} left the pod
 * {@code 1/1 Running} while the transfer died on an endpoint that could not be resolved. Those are validated, so a missing value
 * stops the application at startup and names the property.
 * </p>
 *
 * <p>
 * The {@code *-service} values are the {@code Host} header the connector puts on its outgoing calls, set in {@code MojaloopUtil}.
 * A {@code Host} header is only needed when something in front of the switch routes by name; when the switch is reached directly
 * by its service address there is nothing to disambiguate, and empty is the correct value. The gazelle deployment sets all four
 * to empty on purpose, so they are deliberately left unvalidated - requiring them would refuse to start a deployment that works.
 * </p>
 *
 * <p>
 * {@code oracle-host} is empty for the same kind of reason: empty means no oracle is configured.
 * </p>
 *
 * @param alsHost
 *            account lookup callback URL, required
 * @param accountLookupService
 *            Host header for account lookup calls, empty when none is needed
 * @param quotesHost
 *            quotes callback URL, required
 * @param quoteService
 *            Host header for quote calls, empty when none is needed
 * @param transfersHost
 *            transfers callback URL, required
 * @param transferService
 *            Host header for transfer calls, empty when none is needed
 * @param transactionsHost
 *            transaction requests callback URL, required
 * @param transactionRequestService
 *            Host header for transaction request calls, empty when none is needed
 * @param oracleHost
 *            oracle host, empty when no oracle is configured
 */
@Validated
@ConfigurationProperties(prefix = "switch")
public record SwitchProperties(
        @NotBlank(message = "switch.als-host must be set") @Pattern(regexp = "^https?://.+",
                message = "switch.als-host must be an http or https URL") String alsHost,
        @DefaultValue("") String accountLookupService,
        @NotBlank(message = "switch.quotes-host must be set") @Pattern(regexp = "^https?://.+",
                message = "switch.quotes-host must be an http or https URL") String quotesHost,
        @DefaultValue("") String quoteService,
        @NotBlank(message = "switch.transfers-host must be set") @Pattern(regexp = "^https?://.+",
                message = "switch.transfers-host must be an http or https URL") String transfersHost,
        @DefaultValue("") String transferService,
        @NotBlank(message = "switch.transactions-host must be set") @Pattern(regexp = "^https?://.+",
                message = "switch.transactions-host must be an http or https URL") String transactionsHost,
        @DefaultValue("") String transactionRequestService, @DefaultValue("") String oracleHost) {
}
