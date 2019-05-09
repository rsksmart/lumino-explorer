import createReducer from './helpers/reducerHelper'
import {
    POLL_CHANNELS,
} from "../actions/types";

const initialState = {
    channels: [],
    channelsChanged: false
};

const channelReducer = createReducer(initialState,
    {
        [POLL_CHANNELS](state, action) {
            return {
                ...state,
                channels:  action.data.channels,
                channelsChanged: false

            };
        },
    });


export default channelReducer;
