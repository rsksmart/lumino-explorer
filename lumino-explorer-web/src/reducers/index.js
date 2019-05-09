import { combineReducers } from 'redux';
import channelReducer from "./channelReducer.js";
import dashboardReducer from "./dashboardReducer";
import searchReducer from "./searchReducer";
import feedReducer from "./feedReducer";
import nodeReducer from "./nodeReducer";

const rootReducer = combineReducers({
    state: (state = {}) => state,
    channelReducer: channelReducer,
    dashboardReducer: dashboardReducer,
    searchReducer: searchReducer,
    feedReducer: feedReducer,
    nodeReducer: nodeReducer
});

export default rootReducer;
