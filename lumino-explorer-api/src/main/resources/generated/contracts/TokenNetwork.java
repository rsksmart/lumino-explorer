package contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class TokenNetwork extends Contract {
    private static final String BINARY = "Bin file was not provided";

    public static final String FUNC_COOPERATIVESETTLE = "cooperativeSettle";

    public static final String FUNC_UNLOCK = "unlock";

    public static final String FUNC_OPENCHANNEL = "openChannel";

    public static final String FUNC_SETTLEMENT_TIMEOUT_MAX = "settlement_timeout_max";

    public static final String FUNC_SECRET_REGISTRY = "secret_registry";

    public static final String FUNC_CHAIN_ID = "chain_id";

    public static final String FUNC_PARTICIPANTS_HASH_TO_CHANNEL_IDENTIFIER = "participants_hash_to_channel_identifier";

    public static final String FUNC_SETTOTALDEPOSIT = "setTotalDeposit";

    public static final String FUNC_CHANNEL_COUNTER = "channel_counter";

    public static final String FUNC_MAX_SAFE_UINT256 = "MAX_SAFE_UINT256";

    public static final String FUNC_CONTRACTEXISTS = "contractExists";

    public static final String FUNC_GETPARTICIPANTSHASH = "getParticipantsHash";

    public static final String FUNC_GETCHANNELINFO = "getChannelInfo";

    public static final String FUNC_SIGNATURE_PREFIX = "signature_prefix";

    public static final String FUNC_GETCHANNELIDENTIFIER = "getChannelIdentifier";

    public static final String FUNC_SETTLECHANNEL = "settleChannel";

    public static final String FUNC_CONTRACT_VERSION = "contract_version";

    public static final String FUNC_SETTLEMENT_TIMEOUT_MIN = "settlement_timeout_min";

    public static final String FUNC_CLOSECHANNEL = "closeChannel";

    public static final String FUNC_DEPOSIT_LIMIT = "deposit_limit";

    public static final String FUNC_CHANNELS = "channels";

    public static final String FUNC_GETCHANNELPARTICIPANTINFO = "getChannelParticipantInfo";

    public static final String FUNC_SETTOTALWITHDRAW = "setTotalWithdraw";

    public static final String FUNC_UPDATENONCLOSINGBALANCEPROOF = "updateNonClosingBalanceProof";

    public static final String FUNC_TOKEN = "token";

    public static final String FUNC_GETUNLOCKIDENTIFIER = "getUnlockIdentifier";

    public static final Event CHANNELOPENED_EVENT = new Event("ChannelOpened", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CHANNELNEWDEPOSIT_EVENT = new Event("ChannelNewDeposit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CHANNELWITHDRAW_EVENT = new Event("ChannelWithdraw", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CHANNELCLOSED_EVENT = new Event("ChannelClosed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event CHANNELUNLOCKED_EVENT = new Event("ChannelUnlocked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NONCLOSINGBALANCEPROOFUPDATED_EVENT = new Event("NonClosingBalanceProofUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event CHANNELSETTLED_EVENT = new Event("ChannelSettled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected TokenNetwork(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TokenNetwork(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TokenNetwork(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TokenNetwork(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> cooperativeSettle(BigInteger channel_identifier, String participant1_address, BigInteger participant1_balance, String participant2_address, BigInteger participant2_balance, byte[] participant1_signature, byte[] participant2_signature) {
        final Function function = new Function(
                FUNC_COOPERATIVESETTLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channel_identifier), 
                new org.web3j.abi.datatypes.Address(participant1_address), 
                new org.web3j.abi.datatypes.generated.Uint256(participant1_balance), 
                new org.web3j.abi.datatypes.Address(participant2_address), 
                new org.web3j.abi.datatypes.generated.Uint256(participant2_balance), 
                new org.web3j.abi.datatypes.DynamicBytes(participant1_signature), 
                new org.web3j.abi.datatypes.DynamicBytes(participant2_signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> unlock(BigInteger channel_identifier, String participant, String partner, byte[] merkle_tree_leaves) {
        final Function function = new Function(
                FUNC_UNLOCK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channel_identifier), 
                new org.web3j.abi.datatypes.Address(participant), 
                new org.web3j.abi.datatypes.Address(partner), 
                new org.web3j.abi.datatypes.DynamicBytes(merkle_tree_leaves)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> openChannel(String participant1, String participant2, BigInteger settle_timeout) {
        final Function function = new Function(
                FUNC_OPENCHANNEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(participant1), 
                new org.web3j.abi.datatypes.Address(participant2), 
                new org.web3j.abi.datatypes.generated.Uint256(settle_timeout)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> settlement_timeout_max() {
        final Function function = new Function(FUNC_SETTLEMENT_TIMEOUT_MAX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> secret_registry() {
        final Function function = new Function(FUNC_SECRET_REGISTRY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> chain_id() {
        final Function function = new Function(FUNC_CHAIN_ID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> participants_hash_to_channel_identifier(byte[] param0) {
        final Function function = new Function(FUNC_PARTICIPANTS_HASH_TO_CHANNEL_IDENTIFIER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setTotalDeposit(BigInteger channel_identifier, String participant, BigInteger total_deposit, String partner) {
        final Function function = new Function(
                FUNC_SETTOTALDEPOSIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channel_identifier), 
                new org.web3j.abi.datatypes.Address(participant), 
                new org.web3j.abi.datatypes.generated.Uint256(total_deposit), 
                new org.web3j.abi.datatypes.Address(partner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> channel_counter() {
        final Function function = new Function(FUNC_CHANNEL_COUNTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> MAX_SAFE_UINT256() {
        final Function function = new Function(FUNC_MAX_SAFE_UINT256, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> contractExists(String contract_address) {
        final Function function = new Function(FUNC_CONTRACTEXISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(contract_address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<byte[]> getParticipantsHash(String participant, String partner) {
        final Function function = new Function(FUNC_GETPARTICIPANTSHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(participant), 
                new org.web3j.abi.datatypes.Address(partner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> getChannelInfo(BigInteger channel_identifier, String participant1, String participant2) {
        final Function function = new Function(FUNC_GETCHANNELINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channel_identifier), 
                new org.web3j.abi.datatypes.Address(participant1), 
                new org.web3j.abi.datatypes.Address(participant2)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<String> signature_prefix() {
        final Function function = new Function(FUNC_SIGNATURE_PREFIX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getChannelIdentifier(String participant, String partner) {
        final Function function = new Function(FUNC_GETCHANNELIDENTIFIER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(participant), 
                new org.web3j.abi.datatypes.Address(partner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> settleChannel(BigInteger channel_identifier, String participant1, BigInteger participant1_transferred_amount, BigInteger participant1_locked_amount, byte[] participant1_locksroot, String participant2, BigInteger participant2_transferred_amount, BigInteger participant2_locked_amount, byte[] participant2_locksroot) {
        final Function function = new Function(
                FUNC_SETTLECHANNEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channel_identifier), 
                new org.web3j.abi.datatypes.Address(participant1), 
                new org.web3j.abi.datatypes.generated.Uint256(participant1_transferred_amount), 
                new org.web3j.abi.datatypes.generated.Uint256(participant1_locked_amount), 
                new org.web3j.abi.datatypes.generated.Bytes32(participant1_locksroot), 
                new org.web3j.abi.datatypes.Address(participant2), 
                new org.web3j.abi.datatypes.generated.Uint256(participant2_transferred_amount), 
                new org.web3j.abi.datatypes.generated.Uint256(participant2_locked_amount), 
                new org.web3j.abi.datatypes.generated.Bytes32(participant2_locksroot)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> contract_version() {
        final Function function = new Function(FUNC_CONTRACT_VERSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> settlement_timeout_min() {
        final Function function = new Function(FUNC_SETTLEMENT_TIMEOUT_MIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> closeChannel(BigInteger channel_identifier, String partner, byte[] balance_hash, BigInteger nonce, byte[] additional_hash, byte[] signature) {
        final Function function = new Function(
                FUNC_CLOSECHANNEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channel_identifier), 
                new org.web3j.abi.datatypes.Address(partner), 
                new org.web3j.abi.datatypes.generated.Bytes32(balance_hash), 
                new org.web3j.abi.datatypes.generated.Uint256(nonce), 
                new org.web3j.abi.datatypes.generated.Bytes32(additional_hash), 
                new org.web3j.abi.datatypes.DynamicBytes(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> deposit_limit() {
        final Function function = new Function(FUNC_DEPOSIT_LIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> channels(BigInteger param0) {
        final Function function = new Function(FUNC_CHANNELS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<Tuple7<BigInteger, BigInteger, Boolean, byte[], BigInteger, byte[], BigInteger>> getChannelParticipantInfo(BigInteger channel_identifier, String participant, String partner) {
        final Function function = new Function(FUNC_GETCHANNELPARTICIPANTINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channel_identifier), 
                new org.web3j.abi.datatypes.Address(participant), 
                new org.web3j.abi.datatypes.Address(partner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple7<BigInteger, BigInteger, Boolean, byte[], BigInteger, byte[], BigInteger>>(
                new Callable<Tuple7<BigInteger, BigInteger, Boolean, byte[], BigInteger, byte[], BigInteger>>() {
                    @Override
                    public Tuple7<BigInteger, BigInteger, Boolean, byte[], BigInteger, byte[], BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, BigInteger, Boolean, byte[], BigInteger, byte[], BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (byte[]) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setTotalWithdraw(BigInteger channel_identifier, String participant, BigInteger total_withdraw, byte[] participant_signature, byte[] partner_signature) {
        final Function function = new Function(
                FUNC_SETTOTALWITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channel_identifier), 
                new org.web3j.abi.datatypes.Address(participant), 
                new org.web3j.abi.datatypes.generated.Uint256(total_withdraw), 
                new org.web3j.abi.datatypes.DynamicBytes(participant_signature), 
                new org.web3j.abi.datatypes.DynamicBytes(partner_signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateNonClosingBalanceProof(BigInteger channel_identifier, String closing_participant, String non_closing_participant, byte[] balance_hash, BigInteger nonce, byte[] additional_hash, byte[] closing_signature, byte[] non_closing_signature) {
        final Function function = new Function(
                FUNC_UPDATENONCLOSINGBALANCEPROOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channel_identifier), 
                new org.web3j.abi.datatypes.Address(closing_participant), 
                new org.web3j.abi.datatypes.Address(non_closing_participant), 
                new org.web3j.abi.datatypes.generated.Bytes32(balance_hash), 
                new org.web3j.abi.datatypes.generated.Uint256(nonce), 
                new org.web3j.abi.datatypes.generated.Bytes32(additional_hash), 
                new org.web3j.abi.datatypes.DynamicBytes(closing_signature), 
                new org.web3j.abi.datatypes.DynamicBytes(non_closing_signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> token() {
        final Function function = new Function(FUNC_TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> getUnlockIdentifier(BigInteger channel_identifier, String participant, String partner) {
        final Function function = new Function(FUNC_GETUNLOCKIDENTIFIER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channel_identifier), 
                new org.web3j.abi.datatypes.Address(participant), 
                new org.web3j.abi.datatypes.Address(partner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public List<ChannelOpenedEventResponse> getChannelOpenedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANNELOPENED_EVENT, transactionReceipt);
        ArrayList<ChannelOpenedEventResponse> responses = new ArrayList<ChannelOpenedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChannelOpenedEventResponse typedResponse = new ChannelOpenedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.participant1 = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.participant2 = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.settle_timeout = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ChannelOpenedEventResponse> channelOpenedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ChannelOpenedEventResponse>() {
            @Override
            public ChannelOpenedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANNELOPENED_EVENT, log);
                ChannelOpenedEventResponse typedResponse = new ChannelOpenedEventResponse();
                typedResponse.log = log;
                typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.participant1 = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.participant2 = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.settle_timeout = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ChannelOpenedEventResponse> channelOpenedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANNELOPENED_EVENT));
        return channelOpenedEventFlowable(filter);
    }

    public List<ChannelNewDepositEventResponse> getChannelNewDepositEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANNELNEWDEPOSIT_EVENT, transactionReceipt);
        ArrayList<ChannelNewDepositEventResponse> responses = new ArrayList<ChannelNewDepositEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChannelNewDepositEventResponse typedResponse = new ChannelNewDepositEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.participant = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.total_deposit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ChannelNewDepositEventResponse> channelNewDepositEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ChannelNewDepositEventResponse>() {
            @Override
            public ChannelNewDepositEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANNELNEWDEPOSIT_EVENT, log);
                ChannelNewDepositEventResponse typedResponse = new ChannelNewDepositEventResponse();
                typedResponse.log = log;
                typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.participant = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.total_deposit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ChannelNewDepositEventResponse> channelNewDepositEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANNELNEWDEPOSIT_EVENT));
        return channelNewDepositEventFlowable(filter);
    }

    public List<ChannelWithdrawEventResponse> getChannelWithdrawEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANNELWITHDRAW_EVENT, transactionReceipt);
        ArrayList<ChannelWithdrawEventResponse> responses = new ArrayList<ChannelWithdrawEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChannelWithdrawEventResponse typedResponse = new ChannelWithdrawEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.participant = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.total_withdraw = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ChannelWithdrawEventResponse> channelWithdrawEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ChannelWithdrawEventResponse>() {
            @Override
            public ChannelWithdrawEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANNELWITHDRAW_EVENT, log);
                ChannelWithdrawEventResponse typedResponse = new ChannelWithdrawEventResponse();
                typedResponse.log = log;
                typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.participant = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.total_withdraw = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ChannelWithdrawEventResponse> channelWithdrawEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANNELWITHDRAW_EVENT));
        return channelWithdrawEventFlowable(filter);
    }

    public List<ChannelClosedEventResponse> getChannelClosedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANNELCLOSED_EVENT, transactionReceipt);
        ArrayList<ChannelClosedEventResponse> responses = new ArrayList<ChannelClosedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChannelClosedEventResponse typedResponse = new ChannelClosedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.closing_participant = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.nonce = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ChannelClosedEventResponse> channelClosedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ChannelClosedEventResponse>() {
            @Override
            public ChannelClosedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANNELCLOSED_EVENT, log);
                ChannelClosedEventResponse typedResponse = new ChannelClosedEventResponse();
                typedResponse.log = log;
                typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.closing_participant = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.nonce = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ChannelClosedEventResponse> channelClosedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANNELCLOSED_EVENT));
        return channelClosedEventFlowable(filter);
    }

    public List<ChannelUnlockedEventResponse> getChannelUnlockedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANNELUNLOCKED_EVENT, transactionReceipt);
        ArrayList<ChannelUnlockedEventResponse> responses = new ArrayList<ChannelUnlockedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChannelUnlockedEventResponse typedResponse = new ChannelUnlockedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.participant = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.partner = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.locksroot = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.unlocked_amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.returned_tokens = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ChannelUnlockedEventResponse> channelUnlockedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ChannelUnlockedEventResponse>() {
            @Override
            public ChannelUnlockedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANNELUNLOCKED_EVENT, log);
                ChannelUnlockedEventResponse typedResponse = new ChannelUnlockedEventResponse();
                typedResponse.log = log;
                typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.participant = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.partner = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.locksroot = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.unlocked_amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.returned_tokens = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ChannelUnlockedEventResponse> channelUnlockedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANNELUNLOCKED_EVENT));
        return channelUnlockedEventFlowable(filter);
    }

    public List<NonClosingBalanceProofUpdatedEventResponse> getNonClosingBalanceProofUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NONCLOSINGBALANCEPROOFUPDATED_EVENT, transactionReceipt);
        ArrayList<NonClosingBalanceProofUpdatedEventResponse> responses = new ArrayList<NonClosingBalanceProofUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NonClosingBalanceProofUpdatedEventResponse typedResponse = new NonClosingBalanceProofUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.closing_participant = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.nonce = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NonClosingBalanceProofUpdatedEventResponse> nonClosingBalanceProofUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, NonClosingBalanceProofUpdatedEventResponse>() {
            @Override
            public NonClosingBalanceProofUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NONCLOSINGBALANCEPROOFUPDATED_EVENT, log);
                NonClosingBalanceProofUpdatedEventResponse typedResponse = new NonClosingBalanceProofUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.closing_participant = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.nonce = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NonClosingBalanceProofUpdatedEventResponse> nonClosingBalanceProofUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NONCLOSINGBALANCEPROOFUPDATED_EVENT));
        return nonClosingBalanceProofUpdatedEventFlowable(filter);
    }

    public List<ChannelSettledEventResponse> getChannelSettledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANNELSETTLED_EVENT, transactionReceipt);
        ArrayList<ChannelSettledEventResponse> responses = new ArrayList<ChannelSettledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChannelSettledEventResponse typedResponse = new ChannelSettledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.participant1_amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.participant2_amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ChannelSettledEventResponse> channelSettledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ChannelSettledEventResponse>() {
            @Override
            public ChannelSettledEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANNELSETTLED_EVENT, log);
                ChannelSettledEventResponse typedResponse = new ChannelSettledEventResponse();
                typedResponse.log = log;
                typedResponse.channel_identifier = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.participant1_amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.participant2_amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ChannelSettledEventResponse> channelSettledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANNELSETTLED_EVENT));
        return channelSettledEventFlowable(filter);
    }

    @Deprecated
    public static TokenNetwork load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenNetwork(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TokenNetwork load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenNetwork(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TokenNetwork load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TokenNetwork(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TokenNetwork load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TokenNetwork(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class ChannelOpenedEventResponse {
        public Log log;

        public BigInteger channel_identifier;

        public String participant1;

        public String participant2;

        public BigInteger settle_timeout;
    }

    public static class ChannelNewDepositEventResponse {
        public Log log;

        public BigInteger channel_identifier;

        public String participant;

        public BigInteger total_deposit;
    }

    public static class ChannelWithdrawEventResponse {
        public Log log;

        public BigInteger channel_identifier;

        public String participant;

        public BigInteger total_withdraw;
    }

    public static class ChannelClosedEventResponse {
        public Log log;

        public BigInteger channel_identifier;

        public String closing_participant;

        public BigInteger nonce;
    }

    public static class ChannelUnlockedEventResponse {
        public Log log;

        public BigInteger channel_identifier;

        public String participant;

        public String partner;

        public byte[] locksroot;

        public BigInteger unlocked_amount;

        public BigInteger returned_tokens;
    }

    public static class NonClosingBalanceProofUpdatedEventResponse {
        public Log log;

        public BigInteger channel_identifier;

        public String closing_participant;

        public BigInteger nonce;
    }

    public static class ChannelSettledEventResponse {
        public Log log;

        public BigInteger channel_identifier;

        public BigInteger participant1_amount;

        public BigInteger participant2_amount;
    }
}
