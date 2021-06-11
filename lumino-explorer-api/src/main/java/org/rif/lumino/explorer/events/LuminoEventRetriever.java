package org.rif.lumino.explorer.events;

import org.rif.lumino.explorer.managers.DashboardManager;
import org.rif.lumino.explorer.models.EventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

public class LuminoEventRetriever {

  private static final Logger logger = LoggerFactory.getLogger(DashboardManager.class);

  private List<TypeReference<?>> eventFields;
  private String eventName;
  private Web3j web3j;
  private BigInteger getLogsBatchBlockSize;

  public LuminoEventRetriever(
      String eventName,
      List<TypeReference<? extends Type>> eventFields,
      String rskBlockchainEndpoint,
      BigInteger getLogsBatchBlockSize) {
    this.eventFields = eventFields;
    this.eventName = eventName;
    web3j = Web3j.build(new HttpService(rskBlockchainEndpoint));
    this.getLogsBatchBlockSize = getLogsBatchBlockSize == null ? BigInteger.valueOf(10000) : getLogsBatchBlockSize;
  }

  public List<EventData> getLogs(BigInteger from, BigInteger to, String contractAddress)
      throws Exception {
    // Create event object to add it as a Filter
    Event event = createEvent();

    // Encode event signature
    String encodedEventSignature = EventEncoder.encode(event);

    // Apply filter, retrieve the logs in batches using threads to not saturate the rsk node
    List<EthLog.LogResult> ethLogs = applyFilterForEvent(encodedEventSignature, contractAddress, from, to).get();

    List<EventData> events = new ArrayList<>();

    // Decode event from logs
    for (EthLog.LogResult logResult : ethLogs) {

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

  @Async
  public Future<List<EthLog.LogResult>> applyFilterForEvent(
      String encodedEventSignature, String contractAddress, BigInteger from, BigInteger to) throws IOException, ExecutionException, InterruptedException {

      logger.info(String.format("Trying to get logs for method %s on contract %s from block %s to block %s",
              encodedEventSignature, contractAddress, from == null ?
                      DefaultBlockParameterName.EARLIEST : from, to == null ? DefaultBlockParameterName.LATEST : to));

      try {
        BigInteger fromBlock = from == null ? this.getBlockNumberFromBlockParameter(DefaultBlockParameterName.EARLIEST) : from;
        BigInteger toBlock = to == null ? this.getBlockNumberFromBlockParameter(DefaultBlockParameterName.LATEST) : to;
        List<EthLog.LogResult> ethLogs = new ArrayList<>();

        BigInteger currentBatchStart = fromBlock;
        BigInteger currentBatchEnd = this.getLogsBatchBlockSize.compareTo(toBlock) > 0 ? toBlock : this.getCurrentBatchEnd(currentBatchStart, toBlock);

        while (currentBatchStart.compareTo(currentBatchEnd) < 0) {
          ethLogs.addAll(this.getEthGetLogsCall(encodedEventSignature, contractAddress, currentBatchStart, currentBatchEnd).get().getLogs());
          currentBatchStart = currentBatchEnd.add(BigInteger.ONE);
          currentBatchEnd = this.getCurrentBatchEnd(currentBatchStart, toBlock);
        }

        return new AsyncResult<>(ethLogs);
      } catch (IOException e) {
        logger.error(String.format("Error trying to get logs for method %s on contract %s from block %s to block %s",
                encodedEventSignature, contractAddress, from == null ?
                        DefaultBlockParameterName.EARLIEST : from, to == null ? DefaultBlockParameterName.LATEST : to), e);
        throw e;
      }
  }

  @Async
  public Future<EthLog> getEthGetLogsCall(String encodedEventSignature, String contractAddress, BigInteger currentBatchStart, BigInteger currentBatchEnd) throws IOException {
      EthFilter ethFilter = new EthFilter(
              DefaultBlockParameter.valueOf(currentBatchStart),
              DefaultBlockParameter.valueOf(currentBatchEnd),
              contractAddress);
      ethFilter.addSingleTopic(encodedEventSignature);

      logger.info(String.format("Trying to get logs for method %s on contract %s from block %s to block %s",
              encodedEventSignature, contractAddress, currentBatchStart, currentBatchEnd));

      try {
        return new AsyncResult<>(this.web3j.ethGetLogs(ethFilter).send());
      } catch (IOException e) {
        logger.error(String.format("Error trying to get logs for method %s on contract %s from block %s to block %s",
                encodedEventSignature, contractAddress, currentBatchStart, currentBatchEnd), e);
        throw e;
      }
  }

  private BigInteger getBlockNumberFromBlockParameter(DefaultBlockParameter blockParameter) throws IOException {
    final EthBlock ethBlock = this.web3j.ethGetBlockByNumber(blockParameter, false).send();
    return ethBlock.getBlock().getNumber();
  }

  private BigInteger getCurrentBatchEnd(BigInteger currentBatchStart, BigInteger to) {
    BigInteger currentBatchEnd = currentBatchStart.add(this.getLogsBatchBlockSize);
    if (currentBatchEnd.compareTo(to) > 0) {
      return to;
    }
    return currentBatchEnd;
  }

  public List<TypeReference<? extends Type>> getEventFields() {
    return eventFields;
  }

  public String getEventName() {
    return eventName;
  }
}
