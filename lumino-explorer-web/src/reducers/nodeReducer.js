import createReducer from './helpers/reducerHelper'
import {
    POLL_NODES
} from "../actions/types";

const initialState = {
    nodes: [],
    nodesChanged: false
};

const nodeReducer = createReducer(initialState,
    {
        [POLL_NODES](state, action) {
            return {
                ...state,
                nodes:  action.data.nodes,
                nodesChanged: false

            };
        }

    });


export default nodeReducer;
