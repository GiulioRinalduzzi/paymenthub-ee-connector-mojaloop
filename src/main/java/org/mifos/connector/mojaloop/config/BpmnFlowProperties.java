package org.mifos.connector.mojaloop.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * The BPMN process ids this connector starts: {@code bpmn.flows.*}.
 *
 * @param partyLookup
 *            the payee party lookup flow
 * @param quote
 *            the payee quote transfer flow
 * @param transactionRequest
 *            the payer transaction request flow
 */
@Validated
@ConfigurationProperties(prefix = "bpmn.flows")
public record BpmnFlowProperties(@NotBlank(message = "bpmn.flows.party-lookup must be set") String partyLookup,
        @NotBlank(message = "bpmn.flows.quote must be set") String quote,
        @NotBlank(message = "bpmn.flows.transaction-request must be set") String transactionRequest) {
}
