import client from "../restClient";
import {FEED_DATA_SUCCEED} from "./types";

export const pollFeed = () =>
    (dispatch, getState) =>
        new Promise((resolve, reject) =>
            client.get("/feed/").then(async response => {
                return resolve(dispatch(pollSucceed(response.data)));
            })
                .catch(error => {
                    console.log(JSON.stringify(error));
                })
        );

const pollSucceed= (allFeedData) => ({
    type: FEED_DATA_SUCCEED,
    data: {
        allFeedData: allFeedData,
    }
});