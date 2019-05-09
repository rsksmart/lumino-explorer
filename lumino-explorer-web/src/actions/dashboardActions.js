import client from "../restClient";
import {DASHBOARD_DATA_SUCCEED} from "./types";
import _ from 'lodash';

export const pollDashboard = (tokenNetwork) =>
    (dispatch, getState) =>
        new Promise((resolve, reject) =>
            {
              let url = "/dashboard/";
              let tokenNetworkId =tokenNetwork ? tokenNetwork.value: null 
              if(!_.isEmpty(tokenNetworkId)){
                url += "?token_network_address=";
                url += tokenNetworkId;
              }
                client.get(url).then(async response => {
                  return resolve(dispatch(pollSucceed(response.data,tokenNetwork)));
              }).catch(error => {
                    console.log(JSON.stringify(error));
              })
            }
        );

const pollSucceed= (allData, tokenNetwork) => ({
    type: DASHBOARD_DATA_SUCCEED,
    data: {
        allData: allData,
        selectedTokenNetwork: tokenNetwork,
    }
});
