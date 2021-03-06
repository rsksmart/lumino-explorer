package org.rif.lumino.explorer.events;

import org.rif.lumino.explorer.models.EventData;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class LuminoEventRetriever {

  private List<TypeReference<?>> eventFields;
  private String eventName;
  private Web3j web3j;

  public LuminoEventRetriever(
      String eventName,
      List<TypeReference<? extends Type>> eventFields,
      String rskBlockchainEndpoint) {
    this.eventFields = eventFields;
    this.eventName = eventName;
    web3j = Web3j.build(new HttpService(rskBlockchainEndpoint));
  }

  public List<EventData> getLogs(BigInteger from, BigInteger to, String contractAddress)
      throws Exception {
    // Create event object to add it as a Filter
    Event event = createEvent();

    // Encode event signature
    String encodedEventSignature = EventEncoder.encode(event);

    // Apply filter, retrieve the logs
    EthLog filterLogs = applyFilterForEvent(encodedEventSignature, contractAddress, from, to);

    List<EventData> events = new ArrayList<>();

    // Decode event from logs
    for (EthLog.LogResult logResult : filterLogs.getLogs()) {

      Log log = (Log) logResult.get();

      List<String> topics = log.getTopics();

      // The first topic should be the event signature. The next ones the indexed fields
      if (!topics.get(0).equals(encodedEventSignature)) {
        continue;
      }

      // Get indexed values if present
      List<Type> values = new ArrayList<>();
      if (!event.getIndexedParameters().isEmpty()) {
        for (int i = 0; i < event.getIndexedParameters().size(); i++) {
          Type value =
              FunctionReturnDecoder.decodeIndexedValue(
                  topics.get(i + 1), event.getIndexedParameters().get(i));
          values.add(value);
        }
      }

      // Get non indexed values (Decode data)
      values.addAll(FunctionReturnDecoder.decode(log.getData(), event.getNonIndexedParameters()));

      events.add(new EventData(eventName, values, log.getBlockNumber(), contractAddress));
    }
    return events;
  }

  private Event createEvent() {
    return new Event(this.eventName, this.eventFields);
  }

  private EthLog applyFilterForEvent(
      String encodedEventSignature, String contractAddress, BigInteger from, BigInteger to)
      throws Exception {

    EthFilter ethFilter =
        new EthFilter(
            from == null ? DefaultBlockParameterName.EARLIEST : DefaultBlockParameter.valueOf(from),
            to == null ? DefaultBlockParameterName.LATEST : DefaultBlockParameter.valueOf(to),
            contractAddress);

    ethFilter.addSingleTopic(encodedEventSignature);

    return this.web3j.ethGetLogs(ethFilter).send();
  }

  public List<TypeReference<? extends Type>> getEventFields() {
    return eventFields;
  }

  public String getEventName() {
    return eventName;
  }
}
