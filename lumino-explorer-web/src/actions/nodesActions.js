import {POLL_NODES} from "./types";
import client from "../restClient";

export const pollNodes = () =>
    (dispatch, getState) =>
        new Promise((resolve, reject) =>
            client.get("/luminoNode/").then(response => {
                return resolve(dispatch(pollSucceed(response.data)));
            })
                .catch(error => {
                    console.log(JSON.stringify(error));
                })
        );

const pollSucceed = (nodes) => (
    {
        type: POLL_NODES,
        data: {nodes: nodes, nodesChanged: false}
    }
);

