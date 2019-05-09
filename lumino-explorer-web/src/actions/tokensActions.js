
import axios from 'axios';
import {LEAVE_SUCCEED, POLL_TOKENS, JOIN_SUCCEED} from "./types";
import {retrieveTokensData} from "../lib/tokens/tokensLogic.js";
import {getNetworkJoinable} from "../services/tokensServices";
import {toWei} from "../lib/amounts/weiConversion";
import {getDecimals} from "../lib/tokens/tokensLogic";




export const pollTokens = () =>
    (dispatch, getState) =>
        new Promise((resolve, reject) =>
            client.get("http://localhost:5001/api/v1/tokens").then(async (response)=> {
                console.log(JSON.stringify(response.data.length));
                let tokens = await retrieveTokensData(response.data);
                tokens = await getNetworkJoinable(tokens);
                //  Chequear los que estan en joineando
                return resolve(dispatch(pollSucceed(tokens, checkTokensChanged(getState().tokenReducer.tokens, tokens))));
            })
            .catch(error => {
                console.log(JSON.stringify(error));
            })
        );


const pollSucceed= (tokens, dataChanged) => ({
    type: POLL_TOKENS,
    data: {tokens: tokens, tokensChanged: dataChanged}
})


const checkTokensChanged = (prevStateTokens, newStateTokens) =>{
    if(prevStateTokens.length !== newStateTokens.length){
        return true;
    }else{
        return false;
    }
}
