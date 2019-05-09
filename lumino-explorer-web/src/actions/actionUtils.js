import {ADDRESS, CHANNEL, TOKEN} from "../lib/search/searchConstants";



 export const mapSuggestions = (searchResults) => {
     let suggestionResult = [];

     if (searchResults.tokens) {
       searchResults.tokens.forEach((token) => {
         let title = token.name + " (" + token.address + ")";
         suggestionResult.push({
           value: title,
           type: TOKEN,
           tokenAddress: token.address,
             symbol: token.symbol,
             networkAddress: token.networkAddress,
             channels : token.channels,
           tokenName: token.name
         })
       });
     }

     if (searchResults.nodes) {
       searchResults.nodes.forEach((node) => {
         let title = node.node_address;

         // if we have a rns address we show that instead
         if(node.rns_address) {
           title = node.rns_address + " (" + title + ")";
         }

         suggestionResult.push({
           value: title,
           type: ADDRESS,
           rnsAddress: node.rns_address,
           nodeAddress: node.node_address,
             channels: node.channels

         })
       });
     }

     if (searchResults.channels) {
       searchResults.channels.forEach((channel) => {
         let title = channel.channel_identifier + " ("
         title += channel.from_address.substring(0, 6)
         title += " <-> "
         title += channel.to_address.substring(0, 6)
         title += ")"

         console.log(title);

         suggestionResult.push({
           value: title,
           type: CHANNEL,
           channelIdentifier: channel.channel_identifier,
           channelFrom: channel.from_address,
           channelTo: channel.to_address,
           channelTokenNetwork: channel.token_network_address,
             tokenName: channel.token_name,
             tokenAddress: channel.token_address,
             tokenSymbol: channel.symbol
         })
       })
     }

     if (suggestionResult.length === 0 ){
         suggestionResult.push({value: "No suggestions"})
     }
     return suggestionResult;

 };
