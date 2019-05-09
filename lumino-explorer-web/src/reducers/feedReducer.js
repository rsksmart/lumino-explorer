import createReducer from './helpers/reducerHelper'
import {FEED_DATA_SUCCEED} from "../actions/types";

const initialState = {
    feedData: [],
};

const feedReducer = createReducer(initialState,
    {
        [FEED_DATA_SUCCEED](state, action) {
            return {
                ...state,
                feedData:  action.data.allFeedData,
            };
        },
    });


export default feedReducer;