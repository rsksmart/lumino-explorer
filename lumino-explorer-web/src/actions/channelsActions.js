import client from "../restClient";
import { POLL_CHANNELS} from "./types";

export const pollChannels = () =>
    (dispatch, getState) =>
        new Promise((resolve, reject) =>
            client.get("/channel/", {
                params: {
                    state: 'Opened'
                }
            }).then(response => {
                return resolve(dispatch(pollSucceed(response.data.channels, checkChannelsChanged(getState().channelReducer.channels, response.data.channels))));
            })
                .catch(error => {
                    console.log(JSON.stringify(error));
                })
        );


const pollSucceed = (channels, dataChanged) => (
    {
        type: POLL_CHANNELS,
        data: {response: channels, channelsChanged: dataChanged}
    }
);


const checkChannelsChanged = (prevStateChannels, newStateChannels) => {
    if (prevStateChannels.length !== newStateChannels.length) {
        return true;
    } else {
        return false;
    }
}
