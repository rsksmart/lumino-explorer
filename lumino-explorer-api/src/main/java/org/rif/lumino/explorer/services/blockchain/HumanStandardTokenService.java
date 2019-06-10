package org.rif.lumino.explorer.services.blockchain;

import org.rif.lumino.explorer.generated.contracts.HumanStandardToken;
import org.rif.lumino.explorer.models.documents.Token;
import org.rif.lumino.explorer.services.blockchain.rskaccount.RskAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.annotation.PostConstruct;
import java.math.BigInteger;

@Service
@Component
public class HumanStandardTokenService {

    private static final Logger logger = LoggerFactory.getLogger(HumanStandardTokenService.class);

    @Value("${lumino.contract.tokenNetworkRegistry}")
    private String tokenNetworkRegistryAddress;

    @Value("${rsk.blockchain.endpoint}")
    private String rskBlockchainEndpoint;

    @Autowired
    RskAccountService rskAccountService;

    private HumanStandardToken humanStandardToken;
    private Web3j web3j;
    private BigInteger gasPrice = BigInteger.ZERO;

    @PostConstruct
    public void init() {
        web3j = Web3j.build(new HttpService(rskBlockchainEndpoint));
    }

    public void setTokenInfo(Token token) {

        try {
            humanStandardToken =
                    HumanStandardToken.load(
                            token.getTokenAddress(),
                            web3j,
                            rskAccountService.getRskAccountCredentials(),
                            gasPrice,
                            gasPrice);

            String name = humanStandardToken.name().send();
            String symbol = humanStandardToken.symbol().send();
            BigInteger decimals = humanStandardToken.decimals().send();

            token.setSymbol(symbol);
            token.setName(name);
            token.setDecimals(decimals.intValue());

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
