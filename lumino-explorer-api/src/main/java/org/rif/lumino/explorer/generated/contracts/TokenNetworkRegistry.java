package org.rif.lumino.explorer.generated.contracts;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Auto generated code.
 *
 * <p><strong>Do not modify!</strong>
 *
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the <a
 * href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class TokenNetworkRegistry extends Contract {
  private static final String BINARY = "Bin file was not provided";

  public static final String FUNC_TOKEN_TO_TOKEN_NETWORKS = "token_to_token_networks";

  public static final String FUNC_SETTLEMENT_TIMEOUT_MAX = "settlement_timeout_max";

  public static final String FUNC_CHAIN_ID = "chain_id";

  public static final String FUNC_CREATEERC20TOKENNETWORK = "createERC20TokenNetwork";

  public static final String FUNC_CONTRACTEXISTS = "contractExists";

  public static final String FUNC_CONTRACT_VERSION = "contract_version";

  public static final String FUNC_SETTLEMENT_TIMEOUT_MIN = "settlement_timeout_min";

  public static final String FUNC_SECRET_REGISTRY_ADDRESS = "secret_registry_address";

  public static final Event TOKENNETWORKCREATED_EVENT =
      new Event(
          "TokenNetworkCreated",
          Arrays.<TypeReference<?>>asList(
              new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));;

  @Deprecated
  protected TokenNetworkRegistry(
      String contractAddress,
      Web3j web3j,
      Credentials credentials,
      BigInteger gasPrice,
      BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  protected TokenNetworkRegistry(
      String contractAddress,
      Web3j web3j,
      Credentials credentials,
      ContractGasProvider contractGasProvider) {
    super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
  }

  @Deprecated
  protected TokenNetworkRegistry(
      String contractAddress,
      Web3j web3j,
      TransactionManager transactionManager,
      BigInteger gasPrice,
      BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  protected TokenNetworkRegistry(
      String contractAddress,
      Web3j web3j,
      TransactionManager transactionManager,
      ContractGasProvider contractGasProvider) {
    super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
  }

  public RemoteCall<String> token_to_token_networks(String param0) {
    final Function function =
        new Function(
            FUNC_TOKEN_TO_TOKEN_NETWORKS,
            Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public RemoteCall<BigInteger> settlement_timeout_max() {
    final Function function =
        new Function(
            FUNC_SETTLEMENT_TIMEOUT_MAX,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<BigInteger> chain_id() {
    final Function function =
        new Function(
            FUNC_CHAIN_ID,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<TransactionReceipt> createERC20TokenNetwork(String _token_address) {
    final Function function =
        new Function(
            FUNC_CREATEERC20TOKENNETWORK,
            Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_token_address)),
            Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<Boolean> contractExists(String contract_address) {
    final Function function =
        new Function(
            FUNC_CONTRACTEXISTS,
            Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(contract_address)),
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    return executeRemoteCallSingleValueReturn(function, Boolean.class);
  }

  public RemoteCall<String> contract_version() {
    final Function function =
        new Function(
            FUNC_CONTRACT_VERSION,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public RemoteCall<BigInteger> settlement_timeout_min() {
    final Function function =
        new Function(
            FUNC_SETTLEMENT_TIMEOUT_MIN,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<String> secret_registry_address() {
    final Function function =
        new Function(
            FUNC_SECRET_REGISTRY_ADDRESS,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public List<TokenNetworkCreatedEventResponse> getTokenNetworkCreatedEvents(
      TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList =
        extractEventParametersWithLog(TOKENNETWORKCREATED_EVENT, transactionReceipt);
    ArrayList<TokenNetworkCreatedEventResponse> responses =
        new ArrayList<TokenNetworkCreatedEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      TokenNetworkCreatedEventResponse typedResponse = new TokenNetworkCreatedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.token_address = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.token_network_address =
          (String) eventValues.getIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Flowable<TokenNetworkCreatedEventResponse> tokenNetworkCreatedEventFlowable(
      EthFilter filter) {
    return web3j
        .ethLogFlowable(filter)
        .map(
            new io.reactivex.functions.Function<Log, TokenNetworkCreatedEventResponse>() {
              @Override
              public TokenNetworkCreatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues =
                    extractEventParametersWithLog(TOKENNETWORKCREATED_EVENT, log);
                TokenNetworkCreatedEventResponse typedResponse =
                    new TokenNetworkCreatedEventResponse();
                typedResponse.log = log;
                typedResponse.token_address =
                    (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.token_network_address =
                    (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
              }
            });
  }

  public Flowable<TokenNetworkCreatedEventResponse> tokenNetworkCreatedEventFlowable(
      DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(TOKENNETWORKCREATED_EVENT));
    return tokenNetworkCreatedEventFlowable(filter);
  }

  @Deprecated
  public static TokenNetworkRegistry load(
      String contractAddress,
      Web3j web3j,
      Credentials credentials,
      BigInteger gasPrice,
      BigInteger gasLimit) {
    return new TokenNetworkRegistry(contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  @Deprecated
  public static TokenNetworkRegistry load(
      String contractAddress,
      Web3j web3j,
      TransactionManager transactionManager,
      BigInteger gasPrice,
      BigInteger gasLimit) {
    return new TokenNetworkRegistry(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  public static TokenNetworkRegistry load(
      String contractAddress,
      Web3j web3j,
      Credentials credentials,
      ContractGasProvider contractGasProvider) {
    return new TokenNetworkRegistry(contractAddress, web3j, credentials, contractGasProvider);
  }

  public static TokenNetworkRegistry load(
      String contractAddress,
      Web3j web3j,
      TransactionManager transactionManager,
      ContractGasProvider contractGasProvider) {
    return new TokenNetworkRegistry(
        contractAddress, web3j, transactionManager, contractGasProvider);
  }

  public static class TokenNetworkCreatedEventResponse {
    public Log log;

    public String token_address;

    public String token_network_address;
  }
}
