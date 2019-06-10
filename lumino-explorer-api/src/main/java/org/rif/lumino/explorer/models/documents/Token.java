package org.rif.lumino.explorer.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "token")
public class Token {
    @Id
    private String tokenAddress;
    private String tokenNetworkAddress;
    private String name;
    private String symbol;
    private Integer decimals;

    public Token(
            String tokenAddress,
            String tokenNetworkAddress,
            String name,
            String symbol,
            Integer decimals) {
        this.tokenAddress = tokenAddress;
        this.tokenNetworkAddress = tokenNetworkAddress;
        this.name = name;
        this.symbol = symbol;
        this.decimals = decimals;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    public String getTokenNetworkAddress() {
        return tokenNetworkAddress;
    }

    public void setTokenNetworkAddress(String tokenNetworkAddress) {
        this.tokenNetworkAddress = tokenNetworkAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getDecimals() {
        return decimals;
    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }
}
